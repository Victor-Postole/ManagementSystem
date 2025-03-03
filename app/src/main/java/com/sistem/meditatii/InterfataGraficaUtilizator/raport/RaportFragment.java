package com.sistem.meditatii.InterfataGraficaUtilizator.raport;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerRaport;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.factura.FacturaAdapter;
import com.sistem.meditatii.InterfataGraficaUtilizator.plata.PlataAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RaportFragment extends Fragment implements GetRaportSelectedItem {

    private EditText tipRaport, dataRaport, detaliiRaport, resurseRaport;
    private Button adaugaRaportNou, adaugaRaport;

    RecyclerView recyclerView;
    RaportAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int raportId = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
         root = inflater.inflate(R.layout.fragment_raport, container, false);

        tipRaport =  root.findViewById(R.id.tipRaport);
        dataRaport =  root.findViewById(R.id.dataRaport);
        detaliiRaport =  root.findViewById(R.id.detaliiRaport);
        resurseRaport =  root.findViewById(R.id.resurseRaport);

        adaugaRaportNou =  root.findViewById(R.id.adaugaRaportNou);
        adaugaRaport =  root.findViewById(R.id.adaugaRaport);

        String currentDate = getCurrentDate();
        dataRaport.setText(currentDate);

        adaugaRaportNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                tipRaport.setText("");
                dataRaport.setText("");
                detaliiRaport.setText("");
                resurseRaport.setText("");
            }
        });

        adaugaRaport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isUpdate){

                    boolean updateRaport = new TableControllerRaport(getContext()).updateRaport(
                            new InsertRaportDBModel(raportId,
                                        String.valueOf( resurseRaport.getText()),
                                        String.valueOf( detaliiRaport.getText()),
                                        String.valueOf( dataRaport.getText()),
                                        String.valueOf( tipRaport.getText())
                                    ));

                    if(updateRaport){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( resurseRaport.getText().toString().isEmpty()
                            || detaliiRaport.getText().toString().isEmpty()
                            || dataRaport.getText().toString().isEmpty()
                            || tipRaport.getText().toString().isEmpty()) {
                        resurseRaport.setError("Unul dintre campuri nu are datele completate");
                        detaliiRaport.setError("Unul dintre campuri nu are datele completate");
                        dataRaport.setError("Unul dintre campuri nu are datele completate");
                        tipRaport.setError("Unul dintre campuri nu are datele completate");
                    }else{



                        boolean inserareRaport = new TableControllerRaport(getContext()).insertRaportInDatabase (
                                new InsertRaportDBModel(raportId,
                                String.valueOf( resurseRaport.getText()),
                                String.valueOf( detaliiRaport.getText()),
                                String.valueOf( dataRaport.getText()),
                                String.valueOf( tipRaport.getText())
                        ));

                        if(inserareRaport){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });

        List<InsertRaportDBModel> citesteRaport = new TableControllerRaport(getContext()).readRaport();
        generateDataList(citesteRaport, getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<InsertRaportDBModel> raportDBModelList, Context context) {
        recyclerView = root.findViewById(R.id.rapoarte_existente);
        recycleAdapter = new RaportAdapter(raportDBModelList, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InsertRaportDBModel> updateRaport = new TableControllerRaport(getContext()).readRaport();
        recycleAdapter.updateData(updateRaport);
    }

    @Override
    public void getSelectedItem(InsertRaportDBModel insertRaportDBModel) {
        isUpdate = true;

        raportId = insertRaportDBModel.getId();

        tipRaport.setText(insertRaportDBModel.getTipRaport());
        dataRaport.setText(insertRaportDBModel.getDataGenerare());
        detaliiRaport.setText(String.valueOf(insertRaportDBModel.getDetalii()));
        resurseRaport.setText(String.valueOf(insertRaportDBModel.getResursa()));
    }
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
