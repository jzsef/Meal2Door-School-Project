package application.model;

public class FelhasznaloSzerep {
    private int felhasznaloID;
    private int szerepID;

    public FelhasznaloSzerep(int felhasznaloID, int szerepID) {
        this.felhasznaloID = felhasznaloID;
        this.szerepID = szerepID;
    }

    public int getFelhasznaloID() {
        return felhasznaloID;
    }
    public void setFelhasznaloID(int felhasznaloID) {
        this.felhasznaloID = felhasznaloID;
    }
    public int getSzerepID() {
        return szerepID;
    }
    public void setSzerepID(int szerepID) {
        this.szerepID = szerepID;
    }
}
