package com.sistem.meditatii.InterfataGraficaUtilizator.resurseCurs;

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
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseCurs;

import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs.ResurseCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class ResurseCursFragment extends Fragment implements GetResurseCursSelectedItem{


    private Button updateResurseCursNou, updateResurseCurs;
    private Spinner cursNume, reurseNume;

    private int idCurs;
    private int idResursa;

    private int idResurseCurs;
    boolean isUpdate = false;

    String cursResurseMeditatieSesiuneSelectedItem;

    RecyclerView recyclerView;
    ResurseCursAdapter recycleAdapter;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_resurse_curs, container, false);

        updateResurseCursNou = root.findViewById(R.id.updateResurseCursNou);
        updateResurseCurs = root.findViewById(R.id.updateResurseCurs);

        cursNume = root.findViewById(R.id.cursNume);
        reurseNume = root.findViewById(R.id.reurseNume);


        List<InsertCursDBModel> cursuri = new TableControllerCurs(getContext()).readCurs();
        cursNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertCursDBModel countriesDropDown = (InsertCursDBModel) cursNume.getSelectedItem();

                idCurs = countriesDropDown.getId();
                cursResurseMeditatieSesiuneSelectedItem = String.valueOf(countriesDropDown.getNumeCurs());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cursuri.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cursuri.toArray());
            cursNume.setAdapter(countriesSpinnerAdapter);
        }

        List<InsertResursaDBModel> resurse = new TableControllerResursa(getContext()).readResursa();
        reurseNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertResursaDBModel countriesDropDown = (InsertResursaDBModel) reurseNume.getSelectedItem();

                idResursa = countriesDropDown.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (resurse.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, resurse.toArray());
            reurseNume.setAdapter(countriesSpinnerAdapter);
        }


        updateResurseCursNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        updateResurseCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerResurseCurs(getContext()).updateResursaCursById(idResurseCurs,  idCurs, idResursa);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerResurseCurs(getContext()).insertResursaCurs(idCurs, idResursa);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<ResurseCursModel_INNER_JOIN> getResurseMeditatie = new TableControllerResurseCurs(getContext()).getResurseCursWithDetails();
        generateDataList(getResurseMeditatie, getContext());


        return root;
    }

    private void generateDataList(List<ResurseCursModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.resurse_curs_existente);
        recycleAdapter = new ResurseCursAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<ResurseCursModel_INNER_JOIN> updatePredareCurs = new TableControllerResurseCurs(getContext()).getResurseCursWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(ResurseCursModel_INNER_JOIN insertPlataSesiuneModel) {
        isUpdate = true;
        idResurseCurs = insertPlataSesiuneModel.getId();
    }
}
