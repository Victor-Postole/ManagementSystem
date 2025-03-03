package com.sistem.meditatii.ModeleInterogareBazaDate;

public class InsertPlataModel {
    private int id;
    private int factura;
    private String metodaPlata;
    private String dataPlata;
    private String suma;
    private String sesiuneMeditatii;


    public InsertPlataModel(int id, int factura, String metodaPlata, String dataPlata, String suma, String sesiuneMeditatii) {
        this.id = id;
        this.factura = factura;
        this.metodaPlata = metodaPlata;
        this.dataPlata = dataPlata;
        this.suma = suma;
        this.sesiuneMeditatii = sesiuneMeditatii;
    }

    public int getId() {
        return id;
    }

    public int getFactura() {
        return factura;
    }

    public String getMetodaPlata() {
        return metodaPlata;
    }

    public String getDataPlata() {
        return dataPlata;
    }

    public String getSuma() {
        return suma;
    }

    public String getSesiuneMeditatii() {
        return sesiuneMeditatii;
    }

    @Override
    public String toString() {
        return id +" dataPlata " + dataPlata + "  suma  "  + suma;
    }
}
