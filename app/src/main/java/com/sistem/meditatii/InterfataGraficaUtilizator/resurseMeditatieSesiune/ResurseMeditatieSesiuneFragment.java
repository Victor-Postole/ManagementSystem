package com.sistem.meditatii.InterfataGraficaUtilizator.resurseMeditatieSesiune;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneCurs.SesiuneCursAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseSesiuneMeditatieModel.ResurseSesiuneMeditatieModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class ResurseMeditatieSesiuneFragment extends Fragment implements GetResurseMeditatieSesiuneSelectedItem {

    private Button updateResurseMeditatieNou, updateResurseMeditatie;
    private Spinner resurseMeditatieSesiuneMeditatie, resurseMeditatieSesiuneMeditatieResursaNume;

    private int idSesiuneMeditatie;
    private int idResursa;

    RecyclerView recyclerView;
    ResurseMeditatieSesiuneAdapter recycleAdapter;
    View root;

    int resurseSesiuneMeditatieId = 0;
    boolean isUpdate= false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_resurse_meditatie_sesiune, container, false);

        updateResurseMeditatieNou = root.findViewById(R.id.updateResurseMeditatieNou);
        updateResurseMeditatie = root.findViewById(R.id.updateResurseMeditatie);

        resurseMeditatieSesiuneMeditatie = root.findViewById(R.id.resurseMeditatieSesiuneMeditatie);
        resurseMeditatieSesiuneMeditatieResursaNume = root.findViewById(R.id.resurseMeditatieSesiuneMeditatieResursaNume);

        List<InsertSesiuneMeditatieDBModel> sesiuneMeditatie = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();
        resurseMeditatieSesiuneMeditatie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertSesiuneMeditatieDBModel platiDropDown = (InsertSesiuneMeditatieDBModel) resurseMeditatieSesiuneMeditatie.getSelectedItem();

                idSesiuneMeditatie = platiDropDown.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sesiuneMeditatie.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, sesiuneMeditatie.toArray());
            resurseMeditatieSesiuneMeditatie.setAdapter(platiSpinnerAdapter);
        }


        List<InsertResursaDBModel> resurse = new TableControllerResursa(getContext()).readResursa();
        resurseMeditatieSesiuneMeditatieResursaNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertResursaDBModel countriesDropDown = (InsertResursaDBModel) resurseMeditatieSesiuneMeditatieResursaNume.getSelectedItem();

                idResursa = countriesDropDown.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (resurse.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, resurse.toArray());
            resurseMeditatieSesiuneMeditatieResursaNume.setAdapter(countriesSpinnerAdapter);
        }


        updateResurseMeditatieNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        updateResurseMeditatie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerResurseSesiuneMeditatie(getContext()).updateResursaSesiuneMeditatieById(resurseSesiuneMeditatieId, resurseSesiuneMeditatieId, idResursa);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerResurseSesiuneMeditatie(getContext()).insertResursaSesiuneMeditatie(resurseSesiuneMeditatieId, idResursa);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<ResurseSesiuneMeditatieModel_INNER_JOIN> getResurseMeditatie = new TableControllerResurseSesiuneMeditatie(getContext()).getResurseSesiuniMeditatieWithDetails();
        generateDataList(getResurseMeditatie, getContext());



        return root;
    }

    private void generateDataList(List<ResurseSesiuneMeditatieModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.resurse_meditatie_existente);
        recycleAdapter = new ResurseMeditatieSesiuneAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<ResurseSesiuneMeditatieModel_INNER_JOIN> updatePredareCurs = new TableControllerResurseSesiuneMeditatie(getContext()).getResurseSesiuniMeditatieWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(ResurseSesiuneMeditatieModel_INNER_JOIN insertPlataSesiuneModel) {
         resurseSesiuneMeditatieId = insertPlataSesiuneModel.getId();
         isUpdate= true;

    }
}
