package com.sistem.meditatii;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.sistem.meditatii.BazaDeDate.PopulareBazaDeDate;
import com.sistem.meditatii.Settings.SingletonUtilizatorModel;
import com.sistem.meditatii.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        SingletonUtilizatorModel singletonUtilizatorModel = SingletonUtilizatorModel.getInstance();

        PopulareBazaDeDate populareBazaDeDate = new PopulareBazaDeDate(getApplicationContext());


        // Initializing NavController using nav_host_fragment
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Use the initialized NavController to navigate
                navController.navigate(R.id.profilFragment);
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.angajareProfesorFragment,
                R.id.angajatFragment,
                R.id.contUtilizatorFragment,
                R.id.cursFragment,
                R.id.facturaFragment,
                R.id.inregistrarePlataFragment,
                R.id.participareCursFragment,
                R.id.plataFragment,
                R.id.plataSesiuneFragment,
                R.id.predareCursFragment,
                R.id.profesorFragment,
                R.id.raportFragment,
                R.id.resursaFragment,
                R.id.resurseCursFragment,
                R.id.resurseGenerareRapoarteFragment,
                R.id.sesiuneMeditatieFragment,
                R.id.sesiuneCursFragment,
                R.id.resurseMeditatieSesiuneFragment,
                R.id.studentFragment,
                R.id.utilizatorFragment)
                .setOpenableLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
