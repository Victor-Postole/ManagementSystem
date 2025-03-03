package com.sistem.meditatii.ModeleInterogareBazaDate;


public class InsertFacturaDBModel {
    private int id;
    private String dataEmitere;
    private long sumaTotala;
    private long plata;
    private String statusPlata;

    public InsertFacturaDBModel(int id, String dataEmitere, long sumaTotala, long plata, String statusPlata) {
        this.id = id;
        this.dataEmitere = dataEmitere;
        this.sumaTotala = sumaTotala;
        this.plata = plata;
        this.statusPlata = statusPlata;
    }

    public int getId() {
        return id;
    }

    public String getDataEmitere() {
        return dataEmitere;
    }

    public long getSumaTotala() {
        return sumaTotala;
    }

    public long getPlata() {
        return plata;
    }

    public String getStatusPlata() {
        return statusPlata;
    }

    @Override
    public String toString() {
        return  "Nr factura: " + String.valueOf(id);
    }

    public static class InsertProfesorDBModel {
        private int id;
        private String telefon;
        private String email;
        private String nume;
        private String prenume;
        private String angajat;

        public InsertProfesorDBModel(int id, String telefon, String email, String nume, String prenume, String angajat) {
            this.id = id;
            this.telefon = telefon;
            this.email = email;
            this.nume = nume;
            this.prenume = prenume;
            this.angajat = angajat;
        }

        public int getId() {
            return id;
        }

        public String getTelefon() {
            return telefon;
        }

        public String getEmail() {
            return email;
        }

        public String getNume() {
            return nume;
        }

        public String getPrenume() {
            return prenume;
        }

        public String getAngajat() {
            return angajat;
        }

        @Override
        public String toString() {
            return nume +  " " + prenume;
        }
    }
}
