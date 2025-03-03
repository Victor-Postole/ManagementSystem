package com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneMeditatie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SesiuneMeditatieFragment extends Fragment implements GetSesiuneMeditatieSelectedItem {

    private EditText plataResurseMeditatieSesiune, dataResurseMeditatieSesiune, oraInceputResurseMeditatieSesiune, oraSfarsitResurseMeditatieSesiune;
    private Button adaugaResurseMeditatieSesiuneNou, adaugaResurseMeditatieSesiune;
    private Spinner cursResurseMeditatieSesiune, resursaResurseMeditatieSesiune;

    RecyclerView recyclerView;
    SesiuneMeditatieAdapter recycleAdapter;
    View root;


    private String cursResurseMeditatieSesiuneSelectedItem = null;
    private String resursaResurseMeditatieSesiuneSelectedItem = null;


    int sesiuneMeditatieID = 0;
    boolean isUpdate= false;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_sesiune_meditatie, container, false);


        //plataResurseMeditatieSesiune = root.findViewById(R.id.plataResurseMeditatieSesiune);
        dataResurseMeditatieSesiune = root.findViewById(R.id.dataResurseMeditatieSesiune);
        oraInceputResurseMeditatieSesiune = root.findViewById(R.id.oraInceputResurseMeditatieSesiune);
        oraSfarsitResurseMeditatieSesiune = root.findViewById(R.id.oraSfarsitResurseMeditatieSesiune);
        cursResurseMeditatieSesiune = root.findViewById(R.id.cursResurseMeditatieSesiune);
        resursaResurseMeditatieSesiune = root.findViewById(R.id.resursaResurseMeditatieSesiune);

        adaugaResurseMeditatieSesiuneNou = root.findViewById(R.id.adaugaResurseMeditatieSesiuneNou);
        adaugaResurseMeditatieSesiune = root.findViewById(R.id.adaugaResurseMeditatieSesiune);

        List<InsertCursDBModel> cursuri = new TableControllerCurs(getContext()).readCurs();
        cursResurseMeditatieSesiune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertCursDBModel countriesDropDown = (InsertCursDBModel) cursResurseMeditatieSesiune.getSelectedItem();

                cursResurseMeditatieSesiuneSelectedItem = String.valueOf(countriesDropDown.getNumeCurs());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cursuri.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cursuri.toArray());
            cursResurseMeditatieSesiune.setAdapter(countriesSpinnerAdapter);
        }

        List<InsertResursaDBModel> resurse = new TableControllerResursa(getContext()).readResursa();
        resursaResurseMeditatieSesiune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertResursaDBModel countriesDropDown = (InsertResursaDBModel) resursaResurseMeditatieSesiune.getSelectedItem();

                resursaResurseMeditatieSesiuneSelectedItem = String.valueOf(countriesDropDown.getNumeResursa());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (resurse.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, resurse.toArray());
            resursaResurseMeditatieSesiune.setAdapter(countriesSpinnerAdapter);
        }


        String currentDate = getCurrentDate();
        dataResurseMeditatieSesiune.setText(currentDate);

        adaugaResurseMeditatieSesiuneNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                plataResurseMeditatieSesiune.setText("");
                dataResurseMeditatieSesiune.setText("");
                oraInceputResurseMeditatieSesiune.setText("");
                oraSfarsitResurseMeditatieSesiune.setText("");
            }
        });
        adaugaResurseMeditatieSesiune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){

                    boolean updateSesiuneMeditatie = new TableControllerSesiuneMeditatie(getContext()).updateSesiuneMeditatie(
                        new InsertSesiuneMeditatieDBModel(
                                sesiuneMeditatieID,
                                resursaResurseMeditatieSesiuneSelectedItem,
                                cursResurseMeditatieSesiuneSelectedItem,
                                oraInceputResurseMeditatieSesiune.getText().toString(),
                                oraSfarsitResurseMeditatieSesiune.getText().toString(),
                                dataResurseMeditatieSesiune.getText().toString(),
                               "urmeaza sa se incaseze"
                        ));

                    if(updateSesiuneMeditatie){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( dataResurseMeditatieSesiune.getText().toString().isEmpty()
                            || oraInceputResurseMeditatieSesiune.getText().toString().isEmpty()
                            || oraSfarsitResurseMeditatieSesiune.getText().toString().isEmpty()) {


                        dataResurseMeditatieSesiune.setError("Unul dintre campuri nu are datele completate");
                        oraInceputResurseMeditatieSesiune.setError("Unul dintre campuri nu are datele completate");
                        oraSfarsitResurseMeditatieSesiune.setError("Unul dintre campuri nu are datele completate");
                    }else{



                        boolean insertSesiuneMeditatieInDatabase = new TableControllerSesiuneMeditatie(getContext()).insertSesiuneMeditatieInDatabase(
                                new InsertSesiuneMeditatieDBModel(
                                        0,
                                        resursaResurseMeditatieSesiuneSelectedItem,
                                        cursResurseMeditatieSesiuneSelectedItem,
                                        oraInceputResurseMeditatieSesiune.getText().toString(),
                                        oraSfarsitResurseMeditatieSesiune.getText().toString(),
                                        dataResurseMeditatieSesiune.getText().toString(),
                                        "urmeaza sa se incaseze"
                                ));

                        if(insertSesiuneMeditatieInDatabase){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });

        List<InsertSesiuneMeditatieDBModel> sesiuneMeditatieDBModels = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();
        generateDataList(sesiuneMeditatieDBModels, getContext());


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<InsertSesiuneMeditatieDBModel> insertSesiuneMeditatieDBModels, Context context) {
        recyclerView = root.findViewById(R.id.sesiune_meditatie_existente);
        recycleAdapter = new SesiuneMeditatieAdapter(insertSesiuneMeditatieDBModels, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public  String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    private void refreshRecyclerView() {
        List<InsertSesiuneMeditatieDBModel> updateCurs = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();
        recycleAdapter.updateData(updateCurs);
    }


    @Override
    public void getSelectedItem(InsertSesiuneMeditatieDBModel insertSesiuneMeditatieDBModel) {

        isUpdate = true;
        sesiuneMeditatieID = insertSesiuneMeditatieDBModel.getId();


        dataResurseMeditatieSesiune.setText(insertSesiuneMeditatieDBModel.getDataSesiune());
        oraInceputResurseMeditatieSesiune.setText(insertSesiuneMeditatieDBModel.getOraInceput());
        oraSfarsitResurseMeditatieSesiune.setText(insertSesiuneMeditatieDBModel.getOraSfarsit());
    }
}
