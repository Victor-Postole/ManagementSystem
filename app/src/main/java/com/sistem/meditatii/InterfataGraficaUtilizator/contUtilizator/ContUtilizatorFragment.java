package com.sistem.meditatii.InterfataGraficaUtilizator.contUtilizator;

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

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerContUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerParticipareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.InterfataGraficaUtilizator.participareCurs.ParticipareCursAdapter;
import com.sistem.meditatii.ModeleInterogareBazaDate.ContUtilizator.ContUtilizatorModel_INNER_JOIN;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.ParticipareCurs.ParticipareCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class ContUtilizatorFragment extends Fragment implements GetContUtilizatorSelectedItem{

    private Button updateContUtilizatorNou, updateContUtilizator;
    private Spinner contNumeUtilizator, contNumeStudent;

    RecyclerView recyclerView;
    ContUtilizatorAdapter recycleAdapter;
    View root;


    private int idNumeStudent;
    private String numeStudent;

    private int idNumeUtilizator;
    private String numeUtilizator;

    int contUtilizatorId = 0;
    boolean isUpdate= false;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the fragment layout manually

        root = inflater.inflate(R.layout.fragment_cont_utilizator, container, false);

        updateContUtilizatorNou = root.findViewById(R.id.updateContUtilizatorNou);
        updateContUtilizator = root.findViewById(R.id.updateContUtilizator);

        contNumeUtilizator = root.findViewById(R.id.contNumeUtilizator);
        contNumeStudent = root.findViewById(R.id.contNumeStudent);


        List<InsertStudentDBModel> studenti = new TableControllerStudent(getContext()).readStudent();
        contNumeStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertStudentDBModel studentDropDown = (InsertStudentDBModel) contNumeStudent.getSelectedItem();

                idNumeStudent = studentDropDown.getId();
                numeStudent = String.valueOf(studentDropDown.getNume() + " " + studentDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (studenti.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, studenti.toArray());
            contNumeStudent.setAdapter(plataSesiuneSpinner);
        }

        List<InsertUtilizatorDBModel> utilizatori = new TableControllerUtilizator(getContext()).readUtilizator();
        contNumeUtilizator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertUtilizatorDBModel studentDropDown = (InsertUtilizatorDBModel) contNumeUtilizator.getSelectedItem();

                idNumeUtilizator = studentDropDown.getId();
                 numeUtilizator = String.valueOf(studentDropDown.getNume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (utilizatori.size() > 0) {
            ArrayAdapter plataSesiuneSpinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, utilizatori.toArray());
            contNumeUtilizator.setAdapter(plataSesiuneSpinner);
        }

        updateContUtilizatorNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });

        updateContUtilizator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){

                    boolean updatePlata = new TableControllerContUtilizator(getContext()).updateContUtilizatorById(contUtilizatorId, idNumeUtilizator, idNumeStudent);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerContUtilizator(getContext()).insertContUtilizator(idNumeUtilizator, idNumeStudent);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });

        List<ContUtilizatorModel_INNER_JOIN> conturiUtilizatori = new TableControllerContUtilizator(getContext()).getConturiUtilizatoriWithDetails();
        generateDataList(conturiUtilizatori, getContext());



        return root;
    }

    private void generateDataList(List<ContUtilizatorModel_INNER_JOIN> inserarePredareCurs, Context context) {
        recyclerView = root.findViewById(R.id.cont_utilizatori_existenti);
        recycleAdapter = new ContUtilizatorAdapter(inserarePredareCurs, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<ContUtilizatorModel_INNER_JOIN> updatePredareCurs = new TableControllerContUtilizator(getContext()).getConturiUtilizatoriWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void getSelectedItem(ContUtilizatorModel_INNER_JOIN insertPlataSesiuneModel) {
        isUpdate= true;
        contUtilizatorId = insertPlataSesiuneModel.getId();
    }
}
