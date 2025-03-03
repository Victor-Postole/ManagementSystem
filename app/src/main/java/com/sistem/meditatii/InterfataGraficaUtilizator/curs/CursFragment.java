package com.sistem.meditatii.InterfataGraficaUtilizator.curs;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CursFragment extends Fragment implements GetCursSelectedItem{

    private EditText numeCurs, meditatiiCurs, dataCurs, locatieCurs;
    private Button adaugaCurs,adaugaCursNou;
    private Spinner profesorCurs, studentCurs, resursaCurs;

    RecyclerView recyclerView;
    CursAdapter recycleAdapter;
    View root;

    int cursID = 0;
    boolean isUpdate= false;

    private String studentSelectedItem = null;
    private String profesorSelectedItem = null;
    private String resursaSelectedItem = null;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_curs, container, false);

        numeCurs = root.findViewById(R.id.numeCurs);
        studentCurs = root.findViewById(R.id.studentCurs);
        meditatiiCurs = root.findViewById(R.id.meditatiiCurs);
        dataCurs = root.findViewById(R.id.dataCurs);
        locatieCurs = root.findViewById(R.id.locatieCurs);
        resursaCurs = root.findViewById(R.id.resursaCurs);
        profesorCurs = root.findViewById(R.id.profesorCurs);

        adaugaCurs = root.findViewById(R.id.adaugaCurs);
        adaugaCursNou = root.findViewById(R.id.adaugaCursNou);

        String currentDate = getCurrentDate();
        dataCurs.setText(currentDate);

        List<InsertFacturaDBModel.InsertProfesorDBModel> profesori = new TableControllerProfesor(getContext()).readProfesori();
        profesori.add(new InsertFacturaDBModel.InsertProfesorDBModel(0 , " " , " ", " ", " "," "));
        profesorCurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertFacturaDBModel.InsertProfesorDBModel profesorDropDown = (InsertFacturaDBModel.InsertProfesorDBModel) profesorCurs.getSelectedItem();

                profesorSelectedItem = String.valueOf(profesorDropDown.getNume() + " " + profesorDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (profesori.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, profesori.toArray());
            profesorCurs.setAdapter(plataSesiuneSpinner);
        }

        List<InsertStudentDBModel> studenti = new TableControllerStudent(getContext()).readStudent();

        studentCurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertStudentDBModel studentDropDown = (InsertStudentDBModel) studentCurs.getSelectedItem();

                studentSelectedItem = String.valueOf(studentDropDown.getNume() + " " + studentDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (studenti.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, studenti.toArray());
            studentCurs.setAdapter(plataSesiuneSpinner);
        }


        List<InsertResursaDBModel> resursa = new TableControllerResursa(getContext()).readResursa();
        resursaCurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertResursaDBModel resursaDropDown = (InsertResursaDBModel) resursaCurs.getSelectedItem();

                resursaSelectedItem = String.valueOf(resursaDropDown.getNumeResursa());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (resursa.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, resursa.toArray());
            resursaCurs.setAdapter(plataSesiuneSpinner);
        }


        adaugaCursNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;

                numeCurs.setText("");
                meditatiiCurs.setText("");
                dataCurs.setText("");
                locatieCurs.setText("");
            }
        });
        adaugaCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(isUpdate){

                    boolean updateCurs = new TableControllerCurs(getContext()).updateCurs(
                            new InsertCursDBModel(cursID,
                                                    numeCurs.getText().toString(),
                                                    profesorSelectedItem,
                                                    resursaSelectedItem,
                                                    dataCurs.getText().toString(),
                                                    meditatiiCurs.getText().toString(),
                                                    studentSelectedItem,
                                                    locatieCurs.getText().toString()
                                    ));

                    if(updateCurs){
                        Toast.makeText(getContext(), "Modificarile au avut loc cu success", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }else{
                    if( numeCurs.getText().toString().isEmpty()
                            || meditatiiCurs.getText().toString().isEmpty()
                            || locatieCurs.getText().toString().isEmpty()
                            || dataCurs.getText().toString().isEmpty()) {

                        numeCurs.setError("Unul dintre campuri nu are datele completate");
                        meditatiiCurs.setError("Unul dintre campuri nu are datele completate");
                        locatieCurs.setError("Unul dintre campuri nu are datele completate");
                        dataCurs.setError("Unul dintre campuri nu are datele completate");
                    }else{



                        boolean inserareCurs = new TableControllerCurs(getContext()).insertCursInDatabase(new InsertCursDBModel(0,
                                numeCurs.getText().toString(),
                                profesorSelectedItem,
                                resursaSelectedItem,
                                dataCurs.getText().toString(),
                                meditatiiCurs.getText().toString(),
                                studentSelectedItem,
                                locatieCurs.getText().toString()
                        ));

                        if(inserareCurs){
                            Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                        }

                        refreshRecyclerView();
                    }
                }

            }
        });

        List<InsertCursDBModel> citesteCursuri = new TableControllerCurs(getContext()).readCurs();
        generateDataList(citesteCursuri, getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<InsertCursDBModel> cursDBModels, Context context) {
        recyclerView = root.findViewById(R.id.cursuri_existente);
        recycleAdapter = new CursAdapter(cursDBModels, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    private void refreshRecyclerView() {
        List<InsertCursDBModel> updateCurs = new TableControllerCurs(getContext()).readCurs();
        recycleAdapter.updateData(updateCurs);
    }

    @Override
    public void getSelectedItem(InsertCursDBModel insertCursDBModel) {

        isUpdate = true;

        cursID = insertCursDBModel.getId();

        numeCurs.setText(insertCursDBModel.getNumeCurs());
        meditatiiCurs.setText(insertCursDBModel.getMeditatie());
        dataCurs.setText(insertCursDBModel.getDataCurs());
        locatieCurs.setText(insertCursDBModel.getLocatieCurs());
    }
}
