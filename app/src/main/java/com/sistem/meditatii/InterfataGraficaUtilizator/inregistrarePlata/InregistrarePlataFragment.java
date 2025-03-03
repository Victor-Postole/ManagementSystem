package com.sistem.meditatii.InterfataGraficaUtilizator.inregistrarePlata;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerPlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerInregistrarePlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneMeditatie.SesiuneMeditatieAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InregistrarePlataModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati.InsertInregistrarePlataDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.R;

import java.util.List;

public class InregistrarePlataFragment extends Fragment implements GetInregistrarePlataSelectedItem{


    private Button adaugaInregistrarePlataNoua, adaugaInregistrarePlata;
    private Spinner inregistrarePlataFactura, inregistrarePlata;

    RecyclerView recyclerView;
    InregistrarePlataAdapter recycleAdapter;
    View root;

    int inregistrarePlataId = 0;
    boolean isUpdate= false;

    public int facturaSelectedId;
    public String facturaSelctedItem;

    public int plataSelectedId;
    public String plataSelectedItem;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        root = inflater.inflate(R.layout.fragment_inregistrare_plata, container, false);

        inregistrarePlataFactura = root.findViewById(R.id.inregistrarePlataFactura);
        inregistrarePlata = root.findViewById(R.id.inregistrarePlata);

        adaugaInregistrarePlataNoua = root.findViewById(R.id.adaugaInregistrarePlataNoua);
        adaugaInregistrarePlata = root.findViewById(R.id.adaugaInregistrarePlata);

        adaugaInregistrarePlataNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        adaugaInregistrarePlata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){
//
                    boolean updatePlata = new TableControllerInregistrarePlata(getContext()).updateInregistrarePlataById(inregistrarePlataId,facturaSelectedId, plataSelectedId);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

               refreshRecyclerView();

                }else{

                    boolean insertSesiunePlataNoua = new TableControllerInregistrarePlata(getContext()).insertInregistrarePlata(facturaSelectedId, plataSelectedId);

                    if(insertSesiunePlataNoua){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                 refreshRecyclerView();
                }

           }
        });


        List<InsertFacturaDBModel> facturi = new TableControllerFactura(getContext()).readFacturas();
        inregistrarePlataFactura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertFacturaDBModel facturaDropDown = (InsertFacturaDBModel) inregistrarePlataFactura.getSelectedItem();

                facturaSelectedId = facturaDropDown.getId();
                facturaSelctedItem = String.valueOf(facturaDropDown.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (facturi.size() > 0) {
            ArrayAdapter facturaSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, facturi.toArray());
            inregistrarePlataFactura.setAdapter(facturaSpinnerAdapter);
        }

        List<InsertPlataModel> plati = new TableControllerPlata(getContext()).readPlati();
        inregistrarePlata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertPlataModel platiDropDown = (InsertPlataModel) inregistrarePlata.getSelectedItem();

                plataSelectedId = platiDropDown.getId();
                plataSelectedItem = String.valueOf(platiDropDown.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (plati.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, plati.toArray());
            inregistrarePlata.setAdapter(platiSpinnerAdapter);
        }

        List<InregistrarePlataModel_INNER_JOIN> inregistrarePlataModelInnerJoins = new TableControllerInregistrarePlata(getContext()).getInregistrariPlataWithDetails();
        generateDataList(inregistrarePlataModelInnerJoins, getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<InregistrarePlataModel_INNER_JOIN> insertSesiuneMeditatieDBModels, Context context) {
        recyclerView = root.findViewById(R.id.inregistrare_plati_existente);
        recycleAdapter = new InregistrarePlataAdapter(insertSesiuneMeditatieDBModels, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InregistrarePlataModel_INNER_JOIN> updateInregistrarePlata = new TableControllerInregistrarePlata(getContext()).getInregistrariPlataWithDetails();
        recycleAdapter.updateData(updateInregistrarePlata);
    }

    @Override
    public void getSelectedItem(InregistrarePlataModel_INNER_JOIN insertInregistrarePlataDBModel) {
        isUpdate = true;
        inregistrarePlataId = insertInregistrarePlataDBModel.getId();
    }
}
