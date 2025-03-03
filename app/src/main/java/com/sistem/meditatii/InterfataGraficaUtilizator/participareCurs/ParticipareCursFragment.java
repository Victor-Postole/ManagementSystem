package com.sistem.meditatii.InterfataGraficaUtilizator.participareCurs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerParticipareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.sesiuneCurs.SesiuneCursAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs.ParticipareCursModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.SesiuneCurs.SesiuneCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class ParticipareCursFragment extends Fragment implements GetParticipareCursSelectedItem{


    private Button updateParticipareCursNou, updateParticipareCurs;
    private Spinner participareCursNumeCurs, participareCursStudent;

    RecyclerView recyclerView;
    ParticipareCursAdapter recycleAdapter;
    View root;

    int participareCursId = 0;
    boolean isUpdate= false;

    private int idCurs;
    private String numeCurs;

    private int idNumeStudent;
    private String numeStudent;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually
        root = inflater.inflate(R.layout.fragment_participare_curs, container, false);

        updateParticipareCursNou = root.findViewById(R.id.updateParticipareCursNou);
        updateParticipareCurs = root.findViewById(R.id.updateParticipareCurs);

        participareCursNumeCurs = root.findViewById(R.id.participareCursNumeCurs);
        participareCursStudent = root.findViewById(R.id.participareCursStudent);

        List<InsertCursDBModel> cursuri = new TableControllerCurs(getContext()).readCurs();
        participareCursNumeCurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertCursDBModel platiDropDown = (InsertCursDBModel) participareCursNumeCurs.getSelectedItem();

                idCurs = platiDropDown.getId();
                numeCurs = String.valueOf(platiDropDown.getNumeCurs());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (cursuri.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cursuri.toArray());
            participareCursNumeCurs.setAdapter(platiSpinnerAdapter);
        }

        List<InsertStudentDBModel> studenti = new TableControllerStudent(getContext()).readStudent();
        participareCursStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertStudentDBModel studentDropDown = (InsertStudentDBModel) participareCursStudent.getSelectedItem();

                idNumeStudent = studentDropDown.getId();
                numeStudent = String.valueOf(studentDropDown.getNume() + " " + studentDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (studenti.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, studenti.toArray());
            participareCursStudent.setAdapter(plataSesiuneSpinner);
        }

        updateParticipareCursNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        updateParticipareCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerParticipareCurs(getContext()).updateParticipareCursById(participareCursId, idCurs, idNumeStudent);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerParticipareCurs(getContext()).insertParticipareCurs( idCurs, idNumeStudent);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<ParticipareCursModel_INNER_JOIN> participareCurs = new TableControllerParticipareCurs(getContext()).getParticipariCursWithDetails();
        generateDataList(participareCurs, getContext());

        Log.e("participareCurs", String.valueOf(participareCurs.size()));

        return root;
    }


    private void generateDataList(List<ParticipareCursModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.participare_curs_existente);
        recycleAdapter = new ParticipareCursAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<ParticipareCursModel_INNER_JOIN> updatePredareCurs = new TableControllerParticipareCurs(getContext()).getParticipariCursWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(ParticipareCursModel_INNER_JOIN insertPlataSesiuneModel) {
        participareCursId = insertPlataSesiuneModel.getId();
        isUpdate= true;
    }
}
