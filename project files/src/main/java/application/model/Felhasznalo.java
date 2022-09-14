package application.model;

import java.util.Date;

public class Felhasznalo {
    int id;
    String nev;
    String email;
    String jelszo;
    String telefonszam;
    Date szuletesiDatum;
    String cim;
    int egyenleg;
    Szerep szerep;

    public Felhasznalo(int id, String nev, String email, String jelszo, String telefonszam, Date szuletesiDatum, String cim, int egyenleg, Szerep szerep) {
        this.id = id;
        this.nev = nev;
        this.email = email;
        this.jelszo = jelszo;
        this.telefonszam = telefonszam;
        this.szuletesiDatum = szuletesiDatum;
        this.cim = cim;
        this.egyenleg = egyenleg;
        this.szerep = szerep;
    }

    public Felhasznalo() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNev() {
        return nev;
    }
    public void setNev(String nev) {
        this.nev = nev;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJelszo() {
        return jelszo;
    }
    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }
    public String getTelefonszam() {
        return telefonszam;
    }
    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }
    public Date getSzuletesiDatum() {
        return szuletesiDatum;
    }
    public void setSzuletesiDatum(Date szuletesiDatum) {
        this.szuletesiDatum = szuletesiDatum;
    }
    public String getCim() {
        return cim;
    }
    public void setCim(String cim) {
        this.cim = cim;
    }
    public int getEgyenleg() {
        return egyenleg;
    }
    public void setEgyenleg(int egyenleg) {
        this.egyenleg = egyenleg;
    }
    public Szerep getSzerep() {
        return szerep;
    }
    public void setSzerep(Szerep szerep) {
        this.szerep = szerep;
    }
}
