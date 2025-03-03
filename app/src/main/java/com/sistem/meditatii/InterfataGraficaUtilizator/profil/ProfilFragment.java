package com.sistem.meditatii.InterfataGraficaUtilizator.profil;

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
import com.sistem.meditatii.Settings.SingletonUtilizatorModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfilFragment extends Fragment {

    private EditText numeProfil, parolaProfil, emailProfil, dataProfil;
    private Button updateProfil, logOutProfil;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout manually
        View root = inflater.inflate(R.layout.fragment_profil, container, false);

        numeProfil = root.findViewById(R.id.numeProfil);
        parolaProfil = root.findViewById(R.id.parolaProfil);
        emailProfil = root.findViewById(R.id.emailProfil);
        dataProfil = root.findViewById(R.id.dataProfil);
        updateProfil = root.findViewById(R.id.updateProfil);
        logOutProfil = root.findViewById(R.id.logOutProfil);

        String getCurrentDate = this.getCurrentDate();
        dataProfil.setText(getCurrentDate);

        SingletonUtilizatorModel singletonUtilizatorModel = SingletonUtilizatorModel.getInstance();
        numeProfil.setText(singletonUtilizatorModel.getNume());
        parolaProfil.setText(singletonUtilizatorModel.getParola());
        emailProfil.setText(singletonUtilizatorModel.getEmail());
        dataProfil.setText(singletonUtilizatorModel.getDataCreareCont());

        updateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numeProfil.getText().toString().isEmpty()) {
                    numeProfil.setError("Unul dintre campuri nu are datele completate");
                    numeProfil.requestFocus();
                } else if (parolaProfil.getText().toString().isEmpty()) {
                    parolaProfil.setError("Unul dintre campuri nu are datele completate");
                    parolaProfil.requestFocus();
                } else if (emailProfil.getText().toString().isEmpty()) {
                    emailProfil.setError("Unul dintre campuri nu are datele completate");
                    emailProfil.requestFocus();
                } else if (dataProfil.getText().toString().isEmpty()) {
                    dataProfil.setError("Unul dintre campuri nu are datele completate");
                    dataProfil.requestFocus();
                } else {
                    boolean insertUtilizator = new TableControllerUtilizator(getContext()).updateUtilizator(
                            new InsertUtilizatorDBModel(
                                    singletonUtilizatorModel.getId(),
                                    numeProfil.getText().toString(),
                                    dataProfil.getText().toString(),
                                    dataProfil.getText().toString(),
                                    emailProfil.getText().toString(),
                                    parolaProfil.getText().toString()
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
        logOutProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.loginFragment);
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
