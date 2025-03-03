package com.sistem.meditatii.InterfataGraficaUtilizator.resurseGenerareRapoarte;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerRaport;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseGenerareRapoarte;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.resursa.GetResursaSelectedItem;
import com.sistem.meditatii.InterfataGraficaUtilizator.resurseCurs.ResurseCursAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseCurs.ResurseCursModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport.ResurseGenerareRapoarteModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class ResurseGenerareRapoarteFragment extends Fragment implements GetResurseRaportSelectedItem {


    private Button updateResursaRaportNou, updateResursaRaport;
    private Spinner rapoarteRapoarteNume, rapoarteResurseNume;


    private int idRaport;
    private int idResursa;

    private int idResursaRaport;
    boolean isUpdate = false;


    String resursaSelectedItem;
    String raportSelectedItem;

    RecyclerView recyclerView;
    ResurseRaportAdapter recycleAdapter;
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_resurse_generare_rapoarte, container, false);

        updateResursaRaportNou = root.findViewById(R.id.updateResursaRaportNou);
        updateResursaRaport = root.findViewById(R.id.updateResursaRaport);

        rapoarteRapoarteNume = root.findViewById(R.id.rapoarteRapoarteNume);
        rapoarteResurseNume = root.findViewById(R.id.rapoarteResurseNume);

        updateResursaRaportNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        updateResursaRaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerResurseGenerareRapoarte(getContext()).updateResursaGenerareRaportById(idResursaRaport,  idResursa, idRaport);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerResurseGenerareRapoarte(getContext()).insertResursaGenerareRaport(idResursa, idRaport);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<InsertRaportDBModel> rapoarte = new TableControllerRaport(getContext()).readRaport();
        rapoarteRapoarteNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertRaportDBModel countriesDropDown = (InsertRaportDBModel) rapoarteRapoarteNume.getSelectedItem();

                idRaport = countriesDropDown.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (rapoarte.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, rapoarte.toArray());
            rapoarteRapoarteNume.setAdapter(countriesSpinnerAdapter);
        }

        List<InsertResursaDBModel> resurse = new TableControllerResursa(getContext()).readResursa();
        rapoarteResurseNume.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertResursaDBModel countriesDropDown = (InsertResursaDBModel) rapoarteResurseNume.getSelectedItem();

                idResursa = countriesDropDown.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (resurse.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, resurse.toArray());
            rapoarteResurseNume.setAdapter(countriesSpinnerAdapter);
        }


        List<ResurseGenerareRapoarteModel_INNER_JOIN> getResurseRapoarte = new TableControllerResurseGenerareRapoarte(getContext()).getResurseGenerareRapoarteWithDetails();
        generateDataList(getResurseRapoarte, getContext());



        return root;
    }


    private void generateDataList(List<ResurseGenerareRapoarteModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.resurse_rapoarte_existente);
        recycleAdapter = new ResurseRaportAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<ResurseGenerareRapoarteModel_INNER_JOIN> updatePredareCurs = new TableControllerResurseGenerareRapoarte(getContext()).getResurseGenerareRapoarteWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(ResurseGenerareRapoarteModel_INNER_JOIN insertPlataSesiuneModel) {
        isUpdate = true;
        idResursaRaport = insertPlataSesiuneModel.getId();
    }
}
