package com.sistem.meditatii.InterfataGraficaUtilizator.angajat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.factura.FacturaAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;

public class AngajatFragment extends Fragment implements  GetAngajatSelectedItem{

    private TextView nume_angajat, prenume_angajat, cnp_angajat, telefon_angajat, email_angajat;
    private Button adaugaAngajat, adaugaAngajatNou;

    RecyclerView recyclerView;
    AngajatAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int angajatId = 0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_angajat, container, false);

        nume_angajat =  root.findViewById(R.id.nume_angajat);
        prenume_angajat =  root.findViewById(R.id.prenume_angajat);
        cnp_angajat =  root.findViewById(R.id.cnp_angajat);
        telefon_angajat =  root.findViewById(R.id.telefon_angajat);
        email_angajat =  root.findViewById(R.id.email_angajat);

        adaugaAngajat =  root.findViewById(R.id.adaugaAngajat);
        adaugaAngajatNou =  root.findViewById(R.id.adaugaAngajatNou);

        List<InsertAngajatDBModel> citesteAngajat = new TableControllerAngajat(getContext()).readAngajati();
        generateDataList(citesteAngajat, getContext());

        adaugaAngajatNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                nume_angajat.setText("");
                prenume_angajat.setText("");
                cnp_angajat.setText("");
                telefon_angajat.setText("");
                email_angajat.setText("");
            }
        });

        adaugaAngajat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){

                    boolean updateAngajat = new TableControllerAngajat(getContext()).updateAngajat(new InsertAngajatDBModel(angajatId,
                            String.valueOf(telefon_angajat.getText()),
                            String.valueOf(cnp_angajat.getText()),
                            String.valueOf(email_angajat.getText()),
                            String.valueOf(nume_angajat.getText()),
                            String.valueOf(prenume_angajat.getText())
                    ));

                    if(updateAngajat){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( nume_angajat.getText().toString().isEmpty()
                            || prenume_angajat.getText().toString().isEmpty()
                            || cnp_angajat.getText().toString().isEmpty()
                            || email_angajat.getText().toString().isEmpty()
                            || telefon_angajat.getText().toString().isEmpty()) {

                        nume_angajat.setError("Unul dintre campuri nu are datele completate");
                        prenume_angajat.setError("Unul dintre campuri nu are datele completate");
                        cnp_angajat.setError("Unul dintre campuri nu are datele completate");
                        email_angajat.setError("Unul dintre campuri nu are datele completate");
                        telefon_angajat.setError("Unul dintre campuri nu are datele completate");
                    }else{



                        boolean inserareAngajat = new TableControllerAngajat(getContext()).insertAngajatInDatabase(new InsertAngajatDBModel(0,
                                                                                                                            String.valueOf(telefon_angajat.getText()),
                                                                                                                            String.valueOf(cnp_angajat.getText()),
                                                                                                                            String.valueOf(email_angajat.getText()),
                                                                                                                            String.valueOf(nume_angajat.getText()),
                                                                                                                            String.valueOf(prenume_angajat.getText())
                                                                                                                    ));

                        if(inserareAngajat){
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

    private void generateDataList( List<InsertAngajatDBModel> citesteAngajat, Context context) {
        recyclerView = root.findViewById(R.id.angajati_existenti);
        recycleAdapter = new AngajatAdapter(citesteAngajat, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InsertAngajatDBModel> updateAngajati = new TableControllerAngajat(getContext()).readAngajati();
        recycleAdapter.updateData(updateAngajati);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertAngajatDBModel insertAngajatDBModel) {
        isUpdate = true;

        angajatId = insertAngajatDBModel.getId();
        nume_angajat.setText(insertAngajatDBModel.getNume());
        prenume_angajat.setText(String.valueOf(insertAngajatDBModel.getPrenume()));
        cnp_angajat.setText(String.valueOf(insertAngajatDBModel.getCnp()));
        telefon_angajat.setText(insertAngajatDBModel.getTelefon());
        email_angajat.setText(insertAngajatDBModel.getEmail());
    }
}
