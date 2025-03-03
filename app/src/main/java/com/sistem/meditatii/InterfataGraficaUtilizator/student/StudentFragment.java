package com.sistem.meditatii.InterfataGraficaUtilizator.student;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.angajat.AngajatAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StudentFragment extends Fragment implements GetStudentSelectedItem{

    private EditText numeStudent,
            prenumeStudent,
            cursStudent,
            telefonStudent,
            utilizatorStudent;

    private Button adaugaStudentNou, adaugaStudent;

    RecyclerView recyclerView;
    StudentAdapter recycleAdapter;
    View root;

    boolean isUpdate= false;
    int studentId = 0;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_student, container, false);

        numeStudent =  root.findViewById(R.id.numeStudent);
        prenumeStudent =  root.findViewById(R.id.prenumeStudent);
        cursStudent =  root.findViewById(R.id.cursStudent);
        telefonStudent =  root.findViewById(R.id.telefonStudent);
        utilizatorStudent =  root.findViewById(R.id.utilizatorStudent);

        adaugaStudentNou =  root.findViewById(R.id.adaugaStudentNou);
        adaugaStudent =  root.findViewById(R.id.adaugaStudent);



        adaugaStudentNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                numeStudent.setText("");
                prenumeStudent.setText("");
                cursStudent.setText("");
                telefonStudent.setText("");
                utilizatorStudent.setText("");
            }
        });
        adaugaStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isUpdate){

                    boolean updateStudent = new TableControllerStudent(getContext()).updateStudent(
                            new InsertStudentDBModel(
                            studentId,
                            String.valueOf(numeStudent.getText()),
                            String.valueOf(prenumeStudent.getText()),
                            String.valueOf(cursStudent.getText()),
                            String.valueOf(utilizatorStudent.getText()),
                            String.valueOf(telefonStudent.getText())
                    ));

                    if(updateStudent){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( numeStudent.getText().toString().isEmpty()
                            || prenumeStudent.getText().toString().isEmpty()
                            || cursStudent.getText().toString().isEmpty()
                            || utilizatorStudent.getText().toString().isEmpty()
                            || telefonStudent.getText().toString().isEmpty()) {

                        numeStudent.setError("Unul dintre campuri nu are datele completate");
                        prenumeStudent.setError("Unul dintre campuri nu are datele completate");
                        cursStudent.setError("Unul dintre campuri nu are datele completate");
                        utilizatorStudent.setError("Unul dintre campuri nu are datele completate");
                        telefonStudent.setError("Unul dintre campuri nu are datele completate");
                    }else{

                        boolean insertStudent = new TableControllerStudent(getContext()).insertStudentInDatabase(
                                new InsertStudentDBModel(
                                        0,
                                        String.valueOf(numeStudent.getText()),
                                        String.valueOf(prenumeStudent.getText()),
                                        String.valueOf(cursStudent.getText()),
                                        String.valueOf(utilizatorStudent.getText()),
                                        String.valueOf(telefonStudent.getText())
                                ));

                        if(insertStudent){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });


        List<InsertStudentDBModel> citesteStudent = new TableControllerStudent(getContext()).readStudent();
        generateDataList(citesteStudent, getContext());

        return root;
    }

    private void generateDataList(List<InsertStudentDBModel> citesteStudent, Context context) {
        recyclerView = root.findViewById(R.id.studenti_existenti);
        recycleAdapter = new StudentAdapter(citesteStudent, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<InsertStudentDBModel> updateAngajati = new TableControllerStudent(getContext()).readStudent();
        recycleAdapter.updateData(updateAngajati);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(InsertStudentDBModel insertStudentDBModel) {

        isUpdate = true;

        studentId = insertStudentDBModel.getId();
        numeStudent.setText(insertStudentDBModel.getNume());
        prenumeStudent.setText(String.valueOf(insertStudentDBModel.getPrenume()));
        cursStudent.setText(String.valueOf(insertStudentDBModel.getCurs()));
        telefonStudent.setText(insertStudentDBModel.getNumarTelefon());
        utilizatorStudent.setText(insertStudentDBModel.getUtilizator());
    }
}
