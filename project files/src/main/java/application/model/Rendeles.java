package application.model;

public class Rendeles {
    Integer id;
    String nev;
    Integer ar;
    Integer termekid;
    Integer felhasznalo_id;

    public Rendeles(Integer rendelesid, String termeknev, Integer ar, Integer termekid, Integer felhasznalo_id) {
        this.id=rendelesid;
        this.nev=termeknev;
        this.ar=ar;
        this.termekid=termekid;
        this.felhasznalo_id=felhasznalo_id;
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

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getTermekID() {
        return termekid;
    }

    public void setTermekID(Integer termekID) {
        this.termekid = termekID;
    }

    public Integer getFelhasznalo_id() {
        return felhasznalo_id;
    }

    public void setFelhasznalo_id(Integer felhasznalo_id) {
        this.felhasznalo_id = felhasznalo_id;
    }
}
