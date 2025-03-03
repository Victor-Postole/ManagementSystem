package com.sistem.meditatii.ModeleInterogareBazaDate.InregistrarePlati;

public class InregistrarePlataModel_INNER_JOIN {

    private int id;
    private String plata;
    private String factural;

    private int idFactura;
    private int idPlata;

    public InregistrarePlataModel_INNER_JOIN(int id, int idFactura, int idPlata) {
        this.id = id;
        this.idFactura = idFactura;
        this.idPlata = idPlata;
    }

    public InregistrarePlataModel_INNER_JOIN(int id, String plata, String factural) {
        this.id = id;
        this.plata = plata;
        this.factural = factural;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlata() {
        return plata;
    }

    public void setPlata(String plata) {
        this.plata = plata;
    }

    public String getFactural() {
        return factural;
    }

    public void setFactural(String factural) {
        this.factural = factural;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdPlata() {
        return idPlata;
    }

    public void setIdPlata(int idPlata) {
        this.idPlata = idPlata;
    }

    @Override
    public String toString() {
        return "InregistrarePlataModel_INNER_JOIN{" +
                "id=" + id +
                ", plata='" + plata + '\'' +
                ", factural='" + factural + '\'' +
                '}';
    }
}
