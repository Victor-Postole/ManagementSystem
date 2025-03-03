package com.sistem.meditatii.BazaDeDate;

import android.content.Context;

import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerAngajat;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerFactura;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerPlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerRaport;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerResursa;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerStudent;
import com.sistem.meditatii.BazaDeDate.InterogariBazaDate.TableControllerUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerAngajareProfesor;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerContUtilizator;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerInregistrarePlata;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerParticipareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPlataSesiune;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerPredareCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseCurs;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseGenerareRapoarte;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerResurseSesiuneMeditatie;
import com.sistem.meditatii.BazaDeDate.InterogariBazeDateOrdinN_la_N.TableControllerSesiuneCurs;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertAngajatDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertCursDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertFacturaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertPlataModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertRaportDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertResursaDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertSesiuneMeditatieDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertStudentDBModel;
import com.sistem.meditatii.ModeleInterogareBazaDate.InsertUtilizatorDBModel;

public class PopulareBazaDeDate {

    private final TableControllerFactura tableControllerFactura;
    private final TableControllerAngajat tableControllerAngajat;
    private final TableControllerProfesor tableControllerProfesor;
    private final TableControllerPlata tableControllerPlata;
    private final TableControllerRaport tableControllerRaport;
    private final TableControllerCurs tableControllerCurs;
    private final TableControllerSesiuneMeditatie tableControllerSesiuneMeditatie;
    private final TableControllerUtilizator tableControllerUtilizator;
    private final TableControllerStudent tableControllerStudent;
    private final TableControllerResursa tableControllerResursa;
    private final TableControllerInregistrarePlata tableControllerInregistrarePlata;
    private final TableControllerAngajareProfesor tableControllerAngajareProfesor;
    private final TableControllerPredareCurs tableControllerPredareCurs;
    private final TableControllerSesiuneCurs tableControllerSesiuneCurs;
    private final TableControllerPlataSesiune tableControllerPlataSesiune;
    private final TableControllerResurseSesiuneMeditatie tableControllerResurseSesiuneMeditatie;
    private final TableControllerResurseGenerareRapoarte tableControllerResurseGenerareRapoarte;
    private final TableControllerParticipareCurs tableControllerParticipareCurs;
    private final TableControllerResurseCurs tableControllerResurseCurs;
    private final TableControllerContUtilizator tableControllerContUtilizator;

    public PopulareBazaDeDate(Context context) {
        tableControllerFactura = new TableControllerFactura(context);
        tableControllerAngajat = new TableControllerAngajat(context);
        tableControllerProfesor = new TableControllerProfesor(context);
        tableControllerPlata = new TableControllerPlata(context);
        tableControllerRaport = new TableControllerRaport(context);
        tableControllerCurs = new TableControllerCurs(context);
        tableControllerSesiuneMeditatie = new TableControllerSesiuneMeditatie(context);
        tableControllerUtilizator = new TableControllerUtilizator(context);
        tableControllerStudent = new TableControllerStudent(context);
        tableControllerResursa = new TableControllerResursa(context);
        tableControllerInregistrarePlata = new TableControllerInregistrarePlata(context);
        tableControllerAngajareProfesor = new TableControllerAngajareProfesor(context);
        tableControllerPredareCurs = new TableControllerPredareCurs(context);
        tableControllerSesiuneCurs = new TableControllerSesiuneCurs(context);
        tableControllerPlataSesiune = new TableControllerPlataSesiune(context);
        tableControllerResurseSesiuneMeditatie = new TableControllerResurseSesiuneMeditatie(context);
        tableControllerResurseGenerareRapoarte = new TableControllerResurseGenerareRapoarte(context);
        tableControllerParticipareCurs = new TableControllerParticipareCurs(context);
        tableControllerResurseCurs = new TableControllerResurseCurs(context);
        tableControllerContUtilizator = new TableControllerContUtilizator(context);

        insertData();
    }

    private void insertData() {
        insertFacturaData();
        insertAngajatData();
        insertProfesorData();
        insertPlataData();
        insertRaportData();
        insertCursData();
        insertSesiuneMeditatieData();
        insertUtilizatorData();
        insertStudentData();
        insertResursaData();
        insertInregistrarePlataData();
        insertAngajareProfesorData();
        insertPredareCursData();
        insertSesiuneCursData();
        insertPlataSesiuneData();
        insertResurseSesiuneMeditatieData();
        insertResurseGenerareRapoarteData();
        insertParticipareCursData();
        insertResurseCursData();
        insertContUtilizatorData();
    }

    private void insertFacturaData() {
        // Insert sample data into factura table
        tableControllerFactura.insertFacturaInDatabase(new InsertFacturaDBModel(0, "2024-04-20", 350L, 350L, "ACHITAT"));
    }

    private void insertAngajatData() {
        // Insert sample data into angajat table
        tableControllerAngajat.insertAngajatInDatabase(new InsertAngajatDBModel(0, "0313512131", "192321312113", "example@example.com", "mihai", "popescu"));
    }

    private void insertProfesorData() {
        // Insert sample data into profesor table
        tableControllerProfesor.insertProfesorInDatabase(new InsertFacturaDBModel.InsertProfesorDBModel(0, "0313512131", "example@example.com", "mihai", "popescu", "angajat"));
    }

    private void insertPlataData() {
        // Insert sample data into plata table
        tableControllerPlata.insertPlataInDatabase(new InsertPlataModel(0, 1, "card", "2024-10-01", "250", "seara"));
    }

    private void insertRaportData() {
        // Insert sample data into raport table
        tableControllerRaport.insertRaportInDatabase(new InsertRaportDBModel(0, "resursa", "detalii",  "2024-06-25" , "Type A"));
    }

    private void insertCursData() {
        // Insert sample data into curs table
        tableControllerCurs.insertCursInDatabase(new InsertCursDBModel(0, "Prof A", "Resource A", "2024-06-25", "2024-10-03", "matematica", "Mihai", "Mall"));
    }

    private void insertSesiuneMeditatieData() {
        // Insert sample data into sesiune_meditatie table
        tableControllerSesiuneMeditatie.insertSesiuneMeditatieInDatabase(new InsertSesiuneMeditatieDBModel(0, "Course A", "matematica" , "2024-06-25 10:00:00", "2024-06-25 12:00:00", "2024-06-25", "Paid"));
    }

    private void insertUtilizatorData() {
        // Insert sample data into utilizator table
        tableControllerUtilizator.insertUtilizatorInDatabase(new InsertUtilizatorDBModel(0, "Mihai popescu", "2023-10-10", "2023-10-10", "email@example.com", "parola1234"));
    }

    private void insertStudentData() {
        // Insert sample data into student table
        tableControllerStudent.insertStudentInDatabase(new InsertStudentDBModel(0, "Mihai", "popescu", "matematica", "2001-10-20", "20202031"));
    }

    private void insertResursaData() {
        // Insert sample data into resursa table
        tableControllerResursa.insertResursaInDatabase(new InsertResursaDBModel(0, "resursa curs", "cantitate resursa", 100L, "diminata", "resursa raport","fizica"));
    }

    private void insertInregistrarePlataData() {
        // Insert sample data into inregistrare_plata table
        tableControllerInregistrarePlata.insertInregistrarePlata(1, 1);
    }

    private void insertAngajareProfesorData() {
        // Insert sample data into angajare_profesor table
        tableControllerAngajareProfesor.insertAngajareProfesor(1, 1);
    }

    private void insertPredareCursData() {
        // Insert sample data into predare_curs table
        tableControllerPredareCurs.insertPredareCurs(1, 1);
    }

    private void insertSesiuneCursData() {
        // Insert sample data into sesiune_curs table
        tableControllerSesiuneCurs.insertSesiuneCurs(1, 1);
    }

    private void insertPlataSesiuneData() {
        // Insert sample data into plata_sesiune table
        tableControllerPlataSesiune.insertPlataSesiune(1, 1);
    }

    private void insertResurseSesiuneMeditatieData() {
        // Insert sample data into resurse_sesiune_meditatie table
        tableControllerResurseSesiuneMeditatie.insertResursaSesiuneMeditatie(1, 1);
    }

    private void insertResurseGenerareRapoarteData() {
        // Insert sample data into resurse_generare_rapoarte table
        tableControllerResurseGenerareRapoarte.insertResursaGenerareRaport(1, 1);
    }

    private void insertParticipareCursData() {
        // Insert sample data into participare_curs table
        tableControllerParticipareCurs.insertParticipareCurs(1, 1);
    }

    private void insertResurseCursData() {
        // Insert sample data into resurse_curs table
        tableControllerResurseCurs.insertResursaCurs(1, 1);
    }

    private void insertContUtilizatorData() {
        // Insert sample data into cont_utilizator table
        tableControllerContUtilizator.insertContUtilizator(1, 1);
    }
}
