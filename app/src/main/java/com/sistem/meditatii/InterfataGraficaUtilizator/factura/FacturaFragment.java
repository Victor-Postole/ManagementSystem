package com.sistem.meditatii.InterfataGraficaUtilizator.factura;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FacturaFragment extends Fragment implements GetSelectedItem {

    private EditText data_emitere, suma_totala, plata, status_plata;
    private Button adaugaFactura, adaugaFacturaNoua;


    RecyclerView recyclerView;
    FacturaAdapter recycleAdapter;
    View root;

    int facturaID = 0;
    boolean isUpdate= false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_factura, container, false);

        data_emitere = root.findViewById(R.id.data_emitere);
        suma_totala = root.findViewById(R.id.suma_totala);
        plata = root.findViewById(R.id.plata);
        status_plata = root.findViewById(R.id.status_plata);
        adaugaFactura = root.findViewById(R.id.adaugaFactura);
        adaugaFacturaNoua = root.findViewById(R.id.adaugaFacturaNoua);

        String currentDate = getCurrentDate();
        data_emitere.setText(currentDate);


        adaugaFacturaNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                suma_totala.setText("");
                plata.setText("");
                status_plata.setText("");
            }
        });

        adaugaFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sumaTotalaString = suma_totala.getText().toString();
                String plataLongString = plata.getText().toString();


              if(isUpdate){

                  boolean updateFactura = new TableControllerFactura(getContext()).updateFactura(new InsertFacturaDBModel(facturaID,data_emitere.getText().toString(), Long.parseLong(sumaTotalaString),  Long.parseLong(plataLongString),  status_plata.getText().toString()));

                  if(updateFactura){
                      Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                  }

                  refreshRecyclerView();
              }else{
                  if( data_emitere.getText().toString().isEmpty()
                          || suma_totala.getText().toString().isEmpty()
                          || plata.getText().toString().isEmpty()
                          || status_plata.getText().toString().isEmpty()) {
                      data_emitere.setError("Unul dintre campuri nu are datele completate");
                      suma_totala.setError("Unul dintre campuri nu are datele completate");
                      plata.setError("Unul dintre campuri nu are datele completate");
                      status_plata.setError("Unul dintre campuri nu are datele completate");
                  }else{



                      boolean inserareFactura = new TableControllerFactura(getContext()).insertFacturaInDatabase(new InsertFacturaDBModel(0,data_emitere.getText().toString(), Long.parseLong(sumaTotalaString),  Long.parseLong(plataLongString),  status_plata.getText().toString()));

                      if(inserareFactura){
                          Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                      }else {
                          Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                      }

                      refreshRecyclerView();
                  }
              }

            }
        });

        List<InsertFacturaDBModel> citesteFacturi = new TableControllerFactura(getContext()).readFacturas();
        generateDataList(citesteFacturi, getContext());

        return root;
    }

    private void generateDataList(List<InsertFacturaDBModel> facturaLista, Context context) {
        recyclerView = root.findViewById(R.id.facturi_existente);
        recycleAdapter = new FacturaAdapter(facturaLista, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    private void refreshRecyclerView() {
        List<InsertFacturaDBModel> updatedFacturiList = new TableControllerFactura(getContext()).readFacturas();
        recycleAdapter.updateData(updatedFacturiList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertFacturaDBModel insertFacturaDBModel) {

        isUpdate = true;

        facturaID = insertFacturaDBModel.getId();
        data_emitere.setText(insertFacturaDBModel.getDataEmitere());
        suma_totala.setText(String.valueOf(insertFacturaDBModel.getSumaTotala()));
        plata.setText(String.valueOf(insertFacturaDBModel.getPlata()));
        status_plata.setText(insertFacturaDBModel.getStatusPlata());
    }
}
