package com.sistem.meditatii.InterfataGraficaUtilizator.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;
import com.sistem.meditatii.R;
import com.sistem.meditatii.Settings.SingletonUtilizatorModel;

public class LoginFragment extends Fragment {

    private EditText userNameLogin, passwordNameLogin;
    private Button loginButton, createAccount;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        userNameLogin = root.findViewById(R.id.userNameLogin);
        passwordNameLogin = root.findViewById(R.id.passwordNameLogin);
        createAccount = root.findViewById(R.id.createAccount);
        loginButton = root.findViewById(R.id.loginButton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment_to_creeazaUtilizatorNouFragment);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle login logic here
                Log.d("LoginFragment", "Login button clicked");
                String username = userNameLogin.getText().toString().trim();
                String password = passwordNameLogin.getText().toString().trim();

                // Add your login validation logic here

                if (validateLogin(getContext(), username, password)) {
                    // Navigate to the next fragment after successful login
                    try {
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.facturaFragment);
                    }catch (Exception e){

                    }
                } else {
                    // Show login error
                    Log.d("LoginFragment", "Login failed for user: " + username);
                }
            }
        });

        return root;
    }

    private boolean validateLogin(Context context,String username, String password) {
        // Implement your login validation logic here
        // For now, return true for demonstration purposes

        InsertUtilizatorDBModel insertUtilizatorDBModel = new TableControllerUtilizator(context).getUtilizatorByName(username);

        // Check if user details are found and if the password matches
        if (insertUtilizatorDBModel != null) {
            if (insertUtilizatorDBModel.getNume().equals(username) && insertUtilizatorDBModel.getParola().equals(password)) {

                SingletonUtilizatorModel singletonUtilizatorModel = SingletonUtilizatorModel.getInstance();
                singletonUtilizatorModel.setNume(insertUtilizatorDBModel.getNume());
                singletonUtilizatorModel.setEmail(insertUtilizatorDBModel.getEmail());
                singletonUtilizatorModel.setId(insertUtilizatorDBModel.getId());
                singletonUtilizatorModel.setParola(insertUtilizatorDBModel.getParola());
                singletonUtilizatorModel.setDataCreareCont(insertUtilizatorDBModel.getDataCreareCont());
                singletonUtilizatorModel.setDataCreareCont(insertUtilizatorDBModel.getDataCreareCont());

                return true;
            }
        }

       return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
