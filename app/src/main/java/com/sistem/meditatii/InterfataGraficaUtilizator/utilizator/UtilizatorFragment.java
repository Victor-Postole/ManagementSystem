package com.sistem.meditatii.InterfataGraficaUtilizator.utilizator;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.student.StudentAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UtilizatorFragment extends Fragment implements GetUtilizatorSelectedItem{

    private EditText numeUtilizator,
            parolaUtilizator,
            emailUtilizator,
            dataCreareUtilizator,
            ultimaConectareUtilizator;

    private Button adaugaUtilizatorNou, adaugaUtilizator;

    RecyclerView recyclerView;
    UtilizatorAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int utilizatorId = 0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_utilizator, container, false);

        numeUtilizator =  root.findViewById(R.id.numeUtilizator);
        parolaUtilizator =  root.findViewById(R.id.parolaUtilizator);
        emailUtilizator =  root.findViewById(R.id.emailUtilizator);
        dataCreareUtilizator =  root.findViewById(R.id.dataCreareUtilizator);
        ultimaConectareUtilizator =  root.findViewById(R.id.ultimaConectareUtilizator);

        adaugaUtilizatorNou =  root.findViewById(R.id.adaugaUtilizatorNou);
        adaugaUtilizator =  root.findViewById(R.id.adaugaUtilizator);

        String getCurrentDate = getCurrentDate();

        dataCreareUtilizator.setText(getCurrentDate);
        ultimaConectareUtilizator.setText(getCurrentDate);

        adaugaUtilizatorNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                numeUtilizator.setText("");
                parolaUtilizator.setText("");
                emailUtilizator.setText("");
                dataCreareUtilizator.setText("");
                ultimaConectareUtilizator.setText("");
            }
        });
        adaugaUtilizator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){


                    boolean updateUtilizator = new TableControllerUtilizator(getContext()).updateUtilizator(
                            new InsertUtilizatorDBModel(
                                    utilizatorId,
                                    String.valueOf(numeUtilizator.getText()),
                                    String.valueOf(ultimaConectareUtilizator.getText()),
                                    String.valueOf(dataCreareUtilizator.getText()),
                                    String.valueOf(emailUtilizator.getText()),
                                    String.valueOf(parolaUtilizator.getText())
                            ));

                    if(updateUtilizator){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( numeUtilizator.getText().toString().isEmpty()
                            || ultimaConectareUtilizator.getText().toString().isEmpty()
                            || dataCreareUtilizator.getText().toString().isEmpty()
                            || emailUtilizator.getText().toString().isEmpty()
                            || parolaUtilizator.getText().toString().isEmpty()) {

                        numeUtilizator.setError("Unul dintre campuri nu are datele completate");
                        ultimaConectareUtilizator.setError("Unul dintre campuri nu are datele completate");
                        dataCreareUtilizator.setError("Unul dintre campuri nu are datele completate");
                        emailUtilizator.setError("Unul dintre campuri nu are datele completate");
                        parolaUtilizator.setError("Unul dintre campuri nu are datele completate");
                    }else{

                        boolean insertUtilizator = new TableControllerUtilizator(getContext()).insertUtilizatorInDatabase(
                                new InsertUtilizatorDBModel(
                                        utilizatorId,
                                        String.valueOf(numeUtilizator.getText()),
                                        String.valueOf(ultimaConectareUtilizator.getText()),
                                        String.valueOf(dataCreareUtilizator.getText()),
                                        String.valueOf(emailUtilizator.getText()),
                                        String.valueOf(parolaUtilizator.getText())
                                ));

                        if(insertUtilizator){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });

        List<InsertUtilizatorDBModel> utilizatorDBModels = new TableControllerUtilizator(getContext()).readUtilizator();
        generateDataList(utilizatorDBModels, getContext());

        return root;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    private void generateDataList(List<InsertUtilizatorDBModel> citesteUtilizator, Context context) {
        recyclerView = root.findViewById(R.id.utilizatori_existenti);
        recycleAdapter = new UtilizatorAdapter(citesteUtilizator, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InsertUtilizatorDBModel> updateUtilizatori = new TableControllerUtilizator(getContext()).readUtilizator();
        recycleAdapter.updateData(updateUtilizatori);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertUtilizatorDBModel insertUtilizatorDBModel) {
        isUpdate = true;

        utilizatorId = insertUtilizatorDBModel.getId();
        numeUtilizator.setText(insertUtilizatorDBModel.getNume());
        parolaUtilizator.setText(String.valueOf(insertUtilizatorDBModel.getParola()));
        emailUtilizator.setText(String.valueOf(insertUtilizatorDBModel.getEmail()));
        dataCreareUtilizator.setText(String.valueOf(insertUtilizatorDBModel.getDataCreareCont()));
        ultimaConectareUtilizator.setText(String.valueOf(insertUtilizatorDBModel.getUltimaConectare()));
    }
}
