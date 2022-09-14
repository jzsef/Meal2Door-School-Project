package application.model;

import java.util.Date;

public class Ertekeles {
    int rendelesid;
    int termekid;
    String termeknev;
    int felhasznalo_id;
    int csillagok;
    String megjegyzes;
    int etterem_id;
    String felhasznalo_nev;

    public Ertekeles(int rendelesid, int termekid, String termeknev, int felhasznalo_id, int csillagok, String megjegyzes, int etterem_id, String felhasznalo_nev) {
        this.rendelesid=rendelesid;
        this.termekid=termekid;
        this.termeknev=termeknev;
        this.felhasznalo_id=felhasznalo_id;
        this.csillagok=csillagok;
        this.megjegyzes=megjegyzes;
        this.etterem_id=etterem_id;
        this.felhasznalo_nev=felhasznalo_nev;
    }

    public Ertekeles() {

    }

    public int getRendelesid() {
        return rendelesid;
    }
    public void setRendelesid(int id) {
        this.rendelesid = id;
    }
    public int getTermekid(){return  termekid;}
    public void setTermekid(int id){this.termekid=id;}
    public String getTermeknev(){return termeknev;}
    public void setTermeknev(String termeknev) {this.termeknev = termeknev;}
    public int getFelhasznalo_id() {return felhasznalo_id;}
    public void setFelhasznalo_id(int felhasznalo_id) {this.felhasznalo_id = felhasznalo_id;}
    public int getCsillagok() {return csillagok;}
    public void setCsillagok(int csillagok) {this.csillagok = csillagok;}
    public String getMegjegyzes() {return megjegyzes;}
    public void setMegjegyzes(String megjegyzes) {this.megjegyzes = megjegyzes;}
    public int getEtterem_id() {return etterem_id;}
    public void setEtterem_id(int etterem_id) {this.etterem_id = etterem_id;}

    public String getFelhasznalo_nev() {
        return felhasznalo_nev;
    }

    public void setFelhasznalo_nev(String felhasznalo_nev) {
        this.felhasznalo_nev = felhasznalo_nev;
    }
}
