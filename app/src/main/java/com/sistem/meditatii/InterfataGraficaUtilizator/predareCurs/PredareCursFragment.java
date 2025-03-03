package com.sistem.meditatii.InterfataGraficaUtilizator.predareCurs;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPredareCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.PredareCursuri.PredareCursModel_INNER_JOIN;
import com.sistem.meditatii.R;

import java.util.List;

public class PredareCursFragment extends Fragment implements GetPredareCursSelectedItem{

    private Button predareCursNou, predareCurs;
    private Spinner predareCursnumeProfesor, predareCursNumeCurs;

    RecyclerView recyclerView;
    PredareCursAdapter recycleAdapter;
    View root;

    int predareCursId = 0;
    boolean isUpdate= false;

    public int profesorSelectedProfesorId;
    public String profesorCursSelectedItem;

    public int cursSelectedCursId;
    public String cursSelectedCursItem;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_predare_curs, container, false);

        predareCursnumeProfesor = root.findViewById(R.id.predareCursnumeProfesor);
        predareCursNumeCurs = root.findViewById(R.id.predareCursNumeCurs);

        predareCursNou = root.findViewById(R.id.predareCursNou);
        predareCurs = root.findViewById(R.id.predareCurs);


        List<InsertFacturaDBModel.InsertProfesorDBModel> profesori = new TableControllerProfesor(getContext()).readProfesori();
        predareCursnumeProfesor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertFacturaDBModel.InsertProfesorDBModel platiDropDown = (InsertFacturaDBModel.InsertProfesorDBModel) predareCursnumeProfesor.getSelectedItem();

                profesorSelectedProfesorId = platiDropDown.getId();
                profesorCursSelectedItem = String.valueOf(platiDropDown.getNume() + " " + platiDropDown.getPrenume());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (profesori.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, profesori.toArray());
            predareCursnumeProfesor.setAdapter(platiSpinnerAdapter);
        }


        List<InsertCursDBModel> cursuri = new TableControllerCurs(getContext()).readCurs();
        predareCursNumeCurs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                InsertCursDBModel platiDropDown = (InsertCursDBModel) predareCursNumeCurs.getSelectedItem();

                cursSelectedCursId = platiDropDown.getId();
                cursSelectedCursItem = String.valueOf(platiDropDown.getNumeCurs());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (cursuri.size() > 0) {
            ArrayAdapter platiSpinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cursuri.toArray());
            predareCursNumeCurs.setAdapter(platiSpinnerAdapter);
        }


        predareCursNou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUpdate = false;
            }
        });
        predareCurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("isUpdate", String.valueOf(isUpdate));

                if(isUpdate){
//
                    boolean updatePlata = new TableControllerPredareCurs(getContext()).updatePredareCursById(predareCursId,profesorSelectedProfesorId, cursSelectedCursId);

                    if(updatePlata){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();

                }else{

                    boolean insertPredareCurs = new TableControllerPredareCurs(getContext()).insertPredareCurs(profesorSelectedProfesorId, cursSelectedCursId);

                    if(insertPredareCurs){
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }

                    refreshRecyclerView();
                }

            }
        });



        List<PredareCursModel_INNER_JOIN> predareCursuri = new TableControllerPredareCurs(getContext()).getPredariCursuriWithDetails();
        generateDataList(predareCursuri, getContext());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void generateDataList(List<PredareCursModel_INNER_JOIN> insertPredareCursInnerJoin, Context context) {
        recyclerView = root.findViewById(R.id.predare_curs_existenti);
        recycleAdapter = new PredareCursAdapter(insertPredareCursInnerJoin, context, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
    }

    private void refreshRecyclerView() {
        List<PredareCursModel_INNER_JOIN> updatePredareCurs = new TableControllerPredareCurs(getContext()).getPredariCursuriWithDetails();
        recycleAdapter.updateData(updatePredareCurs);
    }

    @Override
    public void getSelectedItem(PredareCursModel_INNER_JOIN predareCurs) {
        isUpdate = true;
        predareCursId = predareCurs.getId();
    }
}
