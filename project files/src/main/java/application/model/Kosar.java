package application.model;

import java.util.ArrayList;
import java.util.List;

public class Kosar {
    private List<Termek> termekek = new ArrayList<>();
    String price;
    public List<Termek> getTermekek() {
        return termekek;
    }
    public void setTermekek(List<Termek> termekek) {
        this.termekek = termekek;
    }
    public Kosar(){
        this.price=sum();
    }
    public String sum(){
        int sum_price=0;
        for(int i=0;i<termekek.size();i++){
            sum_price+=termekek.get(i).ar;
        }
        String ret=""+sum_price;
        this.price=ret;
        return ret;
    }
    public String getPrice(){
        sum();
        return this.price;
    }
}
