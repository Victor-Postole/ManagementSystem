package com.sistem.meditatii.ModeleInterogareBazaDate.ResurseRaport;

public class ResurseGenerareRapoarteModel_INNER_JOIN {
    private int id;
    private int idResursa;
    private int idRaport;

    private String numeResursa;
    private String numeRaport;


    public ResurseGenerareRapoarteModel_INNER_JOIN(int id, int idResursa, int idRaport, String numeResursa, String numeRaport) {
        this.id = id;
        this.idResursa = idResursa;
        this.idRaport = idRaport;
        this.numeResursa = numeResursa;
        this.numeRaport = numeRaport;
    }

    public ResurseGenerareRapoarteModel_INNER_JOIN(int id, int idResursa, int idRaport) {
        this.id = id;
        this.idResursa = idResursa;
        this.idRaport = idRaport;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdResursa(int idResursa) {
        this.idResursa = idResursa;
    }

    public void setIdRaport(int idRaport) {
        this.idRaport = idRaport;
    }

    public String getNumeResursa() {
        return numeResursa;
    }

    public void setNumeResursa(String numeResursa) {
        this.numeResursa = numeResursa;
    }

    public String getNumeRaport() {
        return numeRaport;
    }

    public void setNumeRaport(String numeRaport) {
        this.numeRaport = numeRaport;
    }



    public int getId() {
        return id;
    }

    public int getIdResursa() {
        return idResursa;
    }

    public int getIdRaport() {
        return idRaport;
    }

    @Override
    public String toString() {
        return "ResurseGenerareRapoarteModel{" +
                "id=" + id +
                ", idResursa=" + idResursa +
                ", idRaport=" + idRaport +
                '}';
    }
}
