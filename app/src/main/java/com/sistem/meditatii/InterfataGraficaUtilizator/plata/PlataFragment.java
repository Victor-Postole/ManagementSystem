package com.sistem.meditatii.InterfataGraficaUtilizator.plata;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerPlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PlataFragment extends Fragment implements GetPlataSelectedItem {

    private EditText  plata_metoda, plata_data, plata_suma;
    private Button adaugaPlata, adaugaPlataNoua;
    private Spinner plata_factura, plata_sesiune;

    RecyclerView recyclerView;
    PlataAdapter recycleAdapter;
    View root;


    private String facturaSelectedItem = null;
    private String plataSesiuneSelectedItem = null;

    boolean isUpdate= false;
    int plataId = 0;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
         root = inflater.inflate(R.layout.fragment_plata, container, false);

        plata_factura =  root.findViewById(R.id.plata_factura);
        plata_metoda =  root.findViewById(R.id.plata_metoda);
        plata_data =  root.findViewById(R.id.plata_data);
        plata_suma =  root.findViewById(R.id.plata_suma);
        plata_sesiune =  root.findViewById(R.id.plata_sesiune);

        adaugaPlata =  root.findViewById(R.id.adaugaPlata);
        adaugaPlataNoua =  root.findViewById(R.id.adaugaPlataNoua);

        List<InsertFacturaDBModel> facturi = new TableControllerFactura(getContext()).readFacturas();

        plata_factura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertFacturaDBModel countriesDropDown = (InsertFacturaDBModel) plata_factura.getSelectedItem();

                facturaSelectedItem = String.valueOf(countriesDropDown.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (facturi.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, facturi.toArray());
            plata_factura.setAdapter(countriesSpinnerAdapter);
        }

        List<InsertSesiuneMeditatieDBModel> seisuneMeditatie = new TableControllerSesiuneMeditatie(getContext()).readSesiuneMeditatie();

        plata_sesiune.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertSesiuneMeditatieDBModel countriesDropDown = (InsertSesiuneMeditatieDBModel) plata_sesiune.getSelectedItem();

                plataSesiuneSelectedItem = String.valueOf(countriesDropDown.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (seisuneMeditatie.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, seisuneMeditatie.toArray());
            plata_sesiune.setAdapter(plataSesiuneSpinner);
        }

        List<InsertPlataModel> citesteProfesor = new TableControllerPlata(getContext()).readPlati();
        generateDataList(citesteProfesor, getContext());

        String currentDate = getCurrentDate();
        plata_data.setText(currentDate);

        adaugaPlataNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                plata_metoda.setText("");
                plata_data.setText("");
                plata_suma.setText("");
            }
        });

        adaugaPlata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){

                    boolean updateAngajat = new TableControllerPlata(getContext()).updatePlata(
                            new InsertPlataModel(plataId,
                                    Integer.parseInt(facturaSelectedItem),
                                    String.valueOf(plata_metoda.getText()),
                                    String.valueOf( plata_data.getText()),
                                    String.valueOf(plata_suma.getText()),
                                    plataSesiuneSelectedItem
                                    )
                    );

                    if(updateAngajat){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( plata_metoda.getText().toString().isEmpty()
                            || plata_data.getText().toString().isEmpty()
                            || plata_suma.getText().toString().isEmpty()) {



                        plata_metoda.setError("Unul dintre campuri nu are datele completate");
                        plata_data.setError("Unul dintre campuri nu are datele completate");
                        plata_suma.setError("Unul dintre campuri nu are datele completate");

                    }else{

                        boolean inserarePlata =  new TableControllerPlata(getContext()).insertPlataInDatabase(
                                new InsertPlataModel(plataId,
                                        Integer.parseInt(facturaSelectedItem),
                                        String.valueOf(plata_metoda.getText()),
                                        String.valueOf( plata_data.getText()),
                                        String.valueOf(plata_suma.getText()),
                                        plataSesiuneSelectedItem
                                )
                        );

                        if(inserarePlata){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });


        return root;
    }

    private void generateDataList( List<InsertPlataModel> citestePlata, Context context) {
        recyclerView = root.findViewById(R.id.plata_existenta);
        recycleAdapter = new PlataAdapter(citestePlata, context, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }


    private void refreshRecyclerView() {
        List<InsertPlataModel> uplataPlata = new TableControllerPlata(getContext()).readPlati();
        recycleAdapter.updateData(uplataPlata);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertPlataModel insertPlataModel) {
        isUpdate = true;

        plataId = insertPlataModel.getId();

        plata_metoda.setText(insertPlataModel.getMetodaPlata());
        plata_data.setText(String.valueOf(insertPlataModel.getDataPlata()));
        plata_suma.setText(String.valueOf(insertPlataModel.getSuma()));

    }
}
