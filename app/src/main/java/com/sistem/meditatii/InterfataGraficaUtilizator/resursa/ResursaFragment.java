package com.sistem.meditatii.InterfataGraficaUtilizator.resursa;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerRaport;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.raport.RaportAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.R;

import java.util.List;

public class ResursaFragment extends Fragment implements  GetResursaSelectedItem{

    EditText numeResursa, cantitateResursa, resursaRaport, tipRersursa,cursResursa, sesiuneResursa;
    Button adaugaResursaNoua, adaugaResursa;

    RecyclerView recyclerView;
    ResursaAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int resursaId = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_resursa, container, false);


        numeResursa =  root.findViewById(R.id.numeResursa);
        cantitateResursa =  root.findViewById(R.id.cantitateResursa);
        resursaRaport =  root.findViewById(R.id.resursaRaport);
        tipRersursa =  root.findViewById(R.id.tipRersursa);
        cursResursa =  root.findViewById(R.id.cursResursa);
        sesiuneResursa =  root.findViewById(R.id.sesiuneResursa);

        adaugaResursaNoua =  root.findViewById(R.id.adaugaResursaNoua);
        adaugaResursa =  root.findViewById(R.id.adaugaResursa);


        adaugaResursaNoua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                numeResursa.setText("");
                cantitateResursa.setText("");
                resursaRaport.setText("");
                tipRersursa.setText("");
                cursResursa.setText("");
            }
        });

        adaugaResursa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isUpdate){

                    boolean updateRaport = new TableControllerResursa(getContext()).updateResursa(
                            new InsertResursaDBModel(
                                    resursaId,
                                    resursaRaport.getText().toString(),
                                    numeResursa.getText().toString(),
                                    Long.parseLong(String.valueOf(cantitateResursa.getText())),
                                    sesiuneResursa.getText().toString(),
                                    resursaRaport.getText().toString(),
                                    tipRersursa.getText().toString()
                            ));

                    if(updateRaport){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{

                    if( numeResursa.getText().toString().isEmpty()
                            || cantitateResursa.getText().toString().isEmpty()
                            || resursaRaport.getText().toString().isEmpty()
                            || tipRersursa.getText().toString().isEmpty()) {


                        cursResursa.setError("Unul dintre campuri nu are datele completate");
                        sesiuneResursa.setError("Unul dintre campuri nu are datele completate");

                    }else{



                        boolean inserareResursa = new TableControllerResursa(getContext()).insertResursaInDatabase(
                                new InsertResursaDBModel(
                                        resursaId,
                                        resursaRaport.getText().toString(),
                                        numeResursa.getText().toString(),
                                        Long.parseLong(String.valueOf(cantitateResursa.getText())),
                                        sesiuneResursa.getText().toString(),
                                        resursaRaport.getText().toString(),
                                        tipRersursa.getText().toString()
                                ));


                        if(inserareResursa){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });

        List<InsertResursaDBModel> citeteResurse = new TableControllerResursa(getContext()).readResursa();
        generateDataList(citeteResurse, getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<InsertResursaDBModel> insertResursaDBModels, Context context) {
        recyclerView = root.findViewById(R.id.resurse_existente);
        recycleAdapter = new ResursaAdapter(insertResursaDBModels, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InsertResursaDBModel> updateRaport = new TableControllerResursa(getContext()).readResursa();
        recycleAdapter.updateData(updateRaport);
    }

    @Override
    public void getSelectedItem(InsertResursaDBModel insertResursaDBModel) {

        isUpdate = true;

        resursaId = insertResursaDBModel.getId();

        numeResursa.setText(insertResursaDBModel.getNumeResursa());
        cantitateResursa.setText(String.valueOf(insertResursaDBModel.getCantitateResursa()));
        resursaRaport.setText(String.valueOf(insertResursaDBModel.getResursaRaport()));
        tipRersursa.setText(String.valueOf(insertResursaDBModel.getTipResursa()));
        cursResursa.setText(String.valueOf(insertResursaDBModel.getResursaCurs()));
        sesiuneResursa.setText(String.valueOf(insertResursaDBModel.getResursaSesiune()));
    }
}
