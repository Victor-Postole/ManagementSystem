package com.sistem.meditatii.Settings;


public class SingletonUtilizatorModel {
    private int id;
    private String nume;
    private String ultimaConectare;
    private String dataCreareCont;
    private String email;
    private String parola;

    // Static instance of the class
    private static SingletonUtilizatorModel instance;

    // Private constructor to prevent instantiation
    private SingletonUtilizatorModel() {
    }

    // Public static method to provide the single instance of the class
    public static SingletonUtilizatorModel getInstance() {
        if (instance == null) {
            instance = new SingletonUtilizatorModel();
        }
        return instance;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getUltimaConectare() {
        return ultimaConectare;
    }

    public void setUltimaConectare(String ultimaConectare) {
        this.ultimaConectare = ultimaConectare;
    }

    public String getDataCreareCont() {
        return dataCreareCont;
    }

    public void setDataCreareCont(String dataCreareCont) {
        this.dataCreareCont = dataCreareCont;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "SingletonUtilizatorModel{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", ultimaConectare='" + ultimaConectare + '\'' +
                ", dataCreareCont='" + dataCreareCont + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                '}';
    }
}
