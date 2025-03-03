package com.sistem.meditatii.InterfataGraficaUtilizator.profesor;

import android.content.Context;
import android.os.Bundle;
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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.R;

import java.util.List;

public class ProfesorFragment extends Fragment implements GetProfesorSelectedItem{

    private TextView nume_profesor, prenume_profesor, telefon_profesor, email_profesor;
    private Button adaugaProfesor, adaugaProfesorNou;


    RecyclerView recyclerView;
    ProfesorAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int profesorId = 0;

    private Spinner angajat_profesor;
    private String angajatiSelectedValue = null;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_profesor, container, false);

        nume_profesor =  root.findViewById(R.id.nume_profesor);
        prenume_profesor =  root.findViewById(R.id.prenume_profesor);
        telefon_profesor =  root.findViewById(R.id.telefon_profesor);
        email_profesor =  root.findViewById(R.id.email_profesor);

        angajat_profesor =  root.findViewById(R.id.angajat_profesor);

        adaugaProfesor =  root.findViewById(R.id.adaugaProfesor);
        adaugaProfesorNou =  root.findViewById(R.id.adaugaProfesorNou);



        List<InsertAngajatDBModel> angajati = new TableControllerAngajat(getContext()).readAngajati();

        angajat_profesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertAngajatDBModel countriesDropDown = (InsertAngajatDBModel) angajat_profesor.getSelectedItem();

                angajatiSelectedValue = countriesDropDown.getNume() + " " + countriesDropDown.getPrenume() ;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (angajati.size() > 0) {
            ArrayAdapter countriesSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, angajati.toArray());
            angajat_profesor.setAdapter(countriesSpinnerAdapter);
        }

        List<InsertFacturaDBModel.InsertProfesorDBModel> citesteProfesor = new TableControllerProfesor(getContext()).readProfesori();
        generateDataList(citesteProfesor, getContext());

        adaugaProfesorNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                nume_profesor.setText("");
                prenume_profesor.setText("");
                telefon_profesor.setText("");
                email_profesor.setText("");
            }
        });
        adaugaProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){

                    boolean updateAngajat = new TableControllerProfesor(getContext()).updateProfesor(new InsertFacturaDBModel.InsertProfesorDBModel(profesorId,
                            String.valueOf(telefon_profesor.getText()),
                            String.valueOf(email_profesor.getText()),
                            String.valueOf(nume_profesor.getText()),
                            String.valueOf(prenume_profesor.getText()),
                            String.valueOf(angajatiSelectedValue)
                    ));

                    if(updateAngajat){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( nume_profesor.getText().toString().isEmpty()
                            || prenume_profesor.getText().toString().isEmpty()
                            || telefon_profesor.getText().toString().isEmpty()
                            || email_profesor.getText().toString().isEmpty()) {

                        nume_profesor.setError("Unul dintre campuri nu are datele completate");
                        prenume_profesor.setError("Unul dintre campuri nu are datele completate");
                        telefon_profesor.setError("Unul dintre campuri nu are datele completate");
                        email_profesor.setError("Unul dintre campuri nu are datele completate");
                    }else{

                        boolean inserareProfesor = new TableControllerProfesor(getContext()).insertProfesorInDatabase(new InsertFacturaDBModel.InsertProfesorDBModel(profesorId,
                                String.valueOf(telefon_profesor.getText()),
                                String.valueOf(email_profesor.getText()),
                                String.valueOf(nume_profesor.getText()),
                                String.valueOf(prenume_profesor.getText()),
                                angajatiSelectedValue));

                        if(inserareProfesor){
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

    private void generateDataList(List<InsertFacturaDBModel.InsertProfesorDBModel> citesteProfesor, Context context) {
        recyclerView = root.findViewById(R.id.profesori_existenti);
        recycleAdapter = new ProfesorAdapter(citesteProfesor, context, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }


    private void refreshRecyclerView() {
        List<InsertFacturaDBModel.InsertProfesorDBModel> updateProfesori = new TableControllerProfesor(getContext()).readProfesori();
        recycleAdapter.updateData(updateProfesori);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertFacturaDBModel.InsertProfesorDBModel insertProfesorDBModel) {
        isUpdate = true;

        profesorId = insertProfesorDBModel.getId();
        nume_profesor.setText(insertProfesorDBModel.getNume());
        prenume_profesor.setText(String.valueOf(insertProfesorDBModel.getPrenume()));
        telefon_profesor.setText(insertProfesorDBModel.getTelefon());
        email_profesor.setText(insertProfesorDBModel.getEmail());
    }
}
