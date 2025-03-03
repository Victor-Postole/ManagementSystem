package com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneCurs;

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
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPlataSesiune;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPredareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.plataSesiune.PlataSesiuneAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune.PlataSesiuneModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class SesiuneCursFragment extends Fragment implements GetSesiuneCursSelectedItem{

    private Button updateSesiuneCursNou, updateSesiuneCurs;
    private Spinner sesiuneCursNume, sesiuneCursMeditatie;

    RecyclerView recyclerView;
    SesiuneCursAdapter recycleAdapter;
    View root;

    int sesiuneCursId = 0;
    boolean isUpdate= false;

    private int idCurs;
    private String numeCurs;
    private String idSesiuneMeditatie;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_sesiune_curs, container, false);


        updateSesiuneCursNou = root.findViewById(R.id.updateSesiuneCursNou);
        updateSesiuneCurs = root.findViewById(R.id.updateSesiuneCurs);

        sesiuneCursNume = root.findViewById(R.id.sesiuneCursNume);
        sesiuneCursMeditatie = root.findViewById(R.id.sesiuneCursMeditatie);

        List<InsertCursDBModel> cursuri = new TableControllerCurs(getContext()).readCurs();
        sesiuneCursNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertCursDBModel platiDropDown = (InsertCursDBModel) sesiuneCursNume.getSelectedItem();

                idCurs = platiDropDown.getId();
                numeCurs = String.valueOf(platiDropDown.getNumeCurs());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (cursuri.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cursuri.toArray());
            sesiuneCursNume.setAdapter(platiSpinnerAdapter);
        }


        List<InsertSesiuneMeditatieDBModel> sesiuneMeditatie = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();
        sesiuneCursMeditatie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertSesiuneMeditatieDBModel platiDropDown = (InsertSesiuneMeditatieDBModel) sesiuneCursMeditatie.getSelectedItem();

                idSesiuneMeditatie = String.valueOf(platiDropDown.getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sesiuneMeditatie.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, sesiuneMeditatie.toArray());
            sesiuneCursMeditatie.setAdapter(platiSpinnerAdapter);
        }

        updateSesiuneCursNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        updateSesiuneCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerSesiuneCurs(getContext()).updateSesiuneCursById(sesiuneCursId, idCurs, Integer.parseInt(idSesiuneMeditatie));

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerSesiuneCurs(getContext()).insertSesiuneCurs( idCurs, Integer.parseInt(idSesiuneMeditatie));

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<SesiuneCursModel_INNER_JOIN> sesiuneCurs = new TableControllerSesiuneCurs(getContext()).getSesiuniCursuriWithDetails();
        generateDataList(sesiuneCurs, getContext());


        return root;
    }

    private void generateDataList(List<SesiuneCursModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.sesiune_curs_existente);
        recycleAdapter = new SesiuneCursAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<SesiuneCursModel_INNER_JOIN> updatePredareCurs = new TableControllerSesiuneCurs(getContext()).getSesiuniCursuriWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(SesiuneCursModel_INNER_JOIN insertPlataSesiuneModel) {
        isUpdate= true;
        sesiuneCursId = insertPlataSesiuneModel.getId();
    }
}
