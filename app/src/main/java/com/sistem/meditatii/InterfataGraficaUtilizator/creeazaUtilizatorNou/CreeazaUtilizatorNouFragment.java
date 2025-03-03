package com.sistem.meditatii.InterfataGraficaUtilizator.creeazaUtilizatorNou;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;
import com.sistem.meditatii.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreeazaUtilizatorNouFragment extends Fragment {

    private EditText numeUtilizatorNou, parolaUtilizatorNou, emailUtilizatorNou, dataCreareUtilizatorNou;
    private Button adaugaUtilizatorNouuu;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout manually
        View root = inflater.inflate(R.layout.fragment_creeaza_utilizator, container, false);

        numeUtilizatorNou = root.findViewById(R.id.numeUtilizatorNou);
        parolaUtilizatorNou = root.findViewById(R.id.parolaUtilizatorNou);
        emailUtilizatorNou = root.findViewById(R.id.emailUtilizatorNou);
        dataCreareUtilizatorNou = root.findViewById(R.id.dataCreareUtilizatorNou);
        adaugaUtilizatorNouuu = root.findViewById(R.id.adaugaUtilizatorNouuu);

        String getCurrentDate = this.getCurrentDate();
        dataCreareUtilizatorNou.setText(getCurrentDate);

        adaugaUtilizatorNouuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeUtilizatorNou.getText().toString().isEmpty()) {
                    numeUtilizatorNou.setError("Unul dintre campuri nu are datele completate");
                    numeUtilizatorNou.requestFocus();
                } else if (parolaUtilizatorNou.getText().toString().isEmpty()) {
                    parolaUtilizatorNou.setError("Unul dintre campuri nu are datele completate");
                    parolaUtilizatorNou.requestFocus();
                } else if (emailUtilizatorNou.getText().toString().isEmpty()) {
                    emailUtilizatorNou.setError("Unul dintre campuri nu are datele completate");
                    emailUtilizatorNou.requestFocus();
                } else if (dataCreareUtilizatorNou.getText().toString().isEmpty()) {
                    dataCreareUtilizatorNou.setError("Unul dintre campuri nu are datele completate");
                    dataCreareUtilizatorNou.requestFocus();
                } else {
                    boolean insertUtilizator = new TableControllerUtilizator(getContext()).insertUtilizatorInDatabase(
                            new InsertUtilizatorDBModel(
                                    0,
                                    numeUtilizatorNou.getText().toString(),
                                    dataCreareUtilizatorNou.getText().toString(),
                                    dataCreareUtilizatorNou.getText().toString(),
                                    emailUtilizatorNou.getText().toString(),
                                    parolaUtilizatorNou.getText().toString()
                            )
                    );

                    NavController navController = Navigation.findNavController(view);
                    if (insertUtilizator) {
                        Toast.makeText(getContext(), "Operatiunea a avut loc cu success!", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.facturaFragment);

                    } else {
                        Toast.makeText(getContext(), "Operatiunea a esuat!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
