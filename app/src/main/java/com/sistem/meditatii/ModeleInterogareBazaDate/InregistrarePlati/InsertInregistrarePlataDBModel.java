package com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati;


public class InsertInregistrarePlataDBModel {
    private int id;
    private int idFactura;
    private int idPlata;

    public InsertInregistrarePlataDBModel(int id, int idFactura, int idPlata) {
        this.id = id;
        this.idFactura = idFactura;
        this.idPlata = idPlata;
    }

    public int getId() {
        return id;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdPlata() {
        return idPlata;
    }

    @Override
    public String toString() {
        return "InsertInregistrarePlataDBModel{" +
                "id=" + id +
                ", idFactura=" + idFactura +
                ", idPlata=" + idPlata +
                '}';
    }
}
