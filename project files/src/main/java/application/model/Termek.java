package application.model;

public class Termek {
    Integer id;
    String nev;
    String kategoria;
    Integer ar;
    Integer etteremID;

    public Termek(Integer id, String nev,  String kategoria, Integer ar, Integer etteremID) {
        this.id = id;
        this.nev = nev;
        this.kategoria = kategoria;
        this.ar = ar;
        this.etteremID = etteremID;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNev() {
        return nev;
    }
    public void setNev(String nev) {
        this.nev = nev;
    }
    public String getKategoria() {
        return kategoria;
    }
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
    public Integer getAr() {
        return ar;
    }
    public void setAr(Integer ar) {
        this.ar = ar;
    }
    public Integer getEtteremID() {
        return etteremID;
    }
    public void setEtteremID(Integer etteremID) {
        this.etteremID = etteremID;
    }
}
