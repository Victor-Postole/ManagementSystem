package com.sistem.meditatii.InterfataGraficaUtilizator.plataSesiune;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerPlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPlataSesiune;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPredareCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.PlataSesiune.PlataSesiuneModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class PlataSesiuneFragment extends Fragment implements GetPlataSesiuneSelectedItem{

    private Button plataSesiuneNou, plataSesiune;
    private Spinner plataSesiuneNumarFactura, plataSesiuneMeditatie;

    RecyclerView recyclerView;
    PlataSesiuneAdapter recycleAdapter;
    View root;

    int plataSesiuneId = 0;
    boolean isUpdate= false;

    private long idPlata;
    private String idSesiuneMeditatie;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_plata_sesiune, container, false);

        plataSesiuneNou = root.findViewById(R.id.plataSesiuneNou);
        plataSesiune = root.findViewById(R.id.plataSesiune);

        plataSesiuneNumarFactura = root.findViewById(R.id.plataSesiuneNumarFactura);
        plataSesiuneMeditatie = root.findViewById(R.id.plataSesiuneMeditatie);

        List<InsertPlataModel> plati = new TableControllerPlata(getContext()).readPlati();
        plataSesiuneNumarFactura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertPlataModel platiDropDown = (InsertPlataModel) plataSesiuneNumarFactura.getSelectedItem();

                idPlata = platiDropDown.getId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (plati.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, plati.toArray());
            plataSesiuneNumarFactura.setAdapter(platiSpinnerAdapter);
        }

        List<InsertSesiuneMeditatieDBModel> sesiuneMeditatie = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();
        plataSesiuneMeditatie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertSesiuneMeditatieDBModel platiDropDown = (InsertSesiuneMeditatieDBModel) plataSesiuneMeditatie.getSelectedItem();

                idSesiuneMeditatie = String.valueOf(platiDropDown.getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (sesiuneMeditatie.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, sesiuneMeditatie.toArray());
            plataSesiuneMeditatie.setAdapter(platiSpinnerAdapter);
        }

        plataSesiuneNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        plataSesiune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerPlataSesiune(getContext()).updatePlataSesiuneById(plataSesiuneId, (int) idPlata, Integer.parseInt(idSesiuneMeditatie));

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerPredareCurs(getContext()).insertPredareCurs( (int) idPlata, Integer.parseInt(idSesiuneMeditatie));

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });


        List<PlataSesiuneModel_INNER_JOIN> platiSesiuni = new TableControllerPlataSesiune(getContext()).getPlatiSesiuniWithDetails();
        generateDataList(platiSesiuni, getContext());


        return root;
    }

    private void generateDataList(List<PlataSesiuneModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.plati_sesiune_existente);
        recycleAdapter = new PlataSesiuneAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<PlataSesiuneModel_INNER_JOIN> updatePredareCurs = new TableControllerPlataSesiune(getContext()).getPlatiSesiuniWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(PlataSesiuneModel_INNER_JOIN insertPlataSesiuneModel) {
        isUpdate = true;
        plataSesiuneId = insertPlataSesiuneModel.getId();
    }
}
