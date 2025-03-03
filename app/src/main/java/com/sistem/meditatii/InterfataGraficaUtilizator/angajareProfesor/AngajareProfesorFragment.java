package com.sistem.meditatii.InterfataGraficaUtilizator.angajareProfesor;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerAngajareProfesor;
import com.sistem.meditatii.ModeleInterogareBazaDate.AngajareProfesor.AngajareProfesorModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;

public class AngajareProfesorFragment extends Fragment implements GetAngajareProfesorSelectedItem {

    private Button adaugaAngajareProfesorNou, adaugaAngajareProfesor;
    private Spinner inregistrareAngajat, inregistrareProfesor;

    RecyclerView recyclerView;
    AngajareProfesorAdapter recycleAdapter;
    View root;

    int inregistrareProfesorAngajatId = 0;
    boolean isUpdate= false;

    public int profesorSelectedId;
    public String profesorIdSelectedNume;
    public String profesorIdSelectedPrenume;

    public int angajareSelectedId;
    public String angajareSelectedNume;
    public String angajareSelectedPrenume;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_angajare_profesor, container, false);

        adaugaAngajareProfesorNou = root.findViewById(R.id.adaugaAngajareProfesorNou);
        adaugaAngajareProfesor = root.findViewById(R.id.adaugaAngajareProfesor);

        inregistrareAngajat = root.findViewById(R.id.inregistrareAngajat);
        inregistrareProfesor = root.findViewById(R.id.inregistrareProfesor);

        List<InsertAngajatDBModel> angajati = new TableControllerAngajat(getContext()).readAngajati();
        inregistrareAngajat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertAngajatDBModel angajatiDropDown = (InsertAngajatDBModel) inregistrareAngajat.getSelectedItem();

                angajareSelectedId = angajatiDropDown.getId();
                profesorIdSelectedNume = String.valueOf(angajatiDropDown.getNume());
                profesorIdSelectedPrenume = String.valueOf(angajatiDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (angajati.size() > 0) {
            ArrayAdapter facturaSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, angajati.toArray());
            inregistrareAngajat.setAdapter(facturaSpinnerAdapter);
        }


        List<InsertFacturaDBModel.InsertProfesorDBModel> profesori = new TableControllerProfesor(getContext()).readProfesori();
        inregistrareProfesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertFacturaDBModel.InsertProfesorDBModel profesoriDropDown = (InsertFacturaDBModel.InsertProfesorDBModel) inregistrareProfesor.getSelectedItem();

                profesorSelectedId = profesoriDropDown.getId();
                profesorIdSelectedNume = String.valueOf(profesoriDropDown.getNume());
                profesorIdSelectedPrenume = String.valueOf(profesoriDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (profesori.size() > 0) {
            ArrayAdapter facturaSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, profesori.toArray());
            inregistrareProfesor.setAdapter(facturaSpinnerAdapter);
        }

        adaugaAngajareProfesorNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        adaugaAngajareProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isUpdate){

                    boolean updateAngajareProfesor = new TableControllerAngajareProfesor(getContext()).updateAngajareProfesorById(inregistrareProfesorAngajatId, profesorSelectedId,angajareSelectedId);

                    if(updateAngajareProfesor){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertAngajatNou = new TableControllerAngajareProfesor(getContext()).insertAngajareProfesor(profesorSelectedId, angajareSelectedId);

                    if(insertAngajatNou){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }

            }
        });


        List<AngajareProfesorModel_INNER_JOIN> inregistrareAngajat = new TableControllerAngajareProfesor(getContext()).getAngajariProfesoriWithDetails();
        generateDataList(inregistrareAngajat, getContext());


        Log.e("inregistrareAngajat", String.valueOf(inregistrareAngajat.size()));


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<AngajareProfesorModel_INNER_JOIN> insertAngajareProfesor, Context context) {
        recyclerView = root.findViewById(R.id.angajare_profesori_existenti);
        recycleAdapter = new AngajareProfesorAdapter(insertAngajareProfesor, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<AngajareProfesorModel_INNER_JOIN> updateInregistrarePlata = new TableControllerAngajareProfesor(getContext()).getAngajariProfesoriWithDetails();
        recycleAdapter.updateData(updateInregistrarePlata);
    }

    @Override
    public void getSelectedItem(AngajareProfesorModel_INNER_JOIN angajareProfesorModel) {
        isUpdate = true;
        inregistrareProfesorAngajatId = angajareProfesorModel.getId();
    }
}
