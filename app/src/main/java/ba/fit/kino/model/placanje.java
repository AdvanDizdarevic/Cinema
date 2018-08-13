package ba.fit.kino.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class placanje {
    public placanje(Integer placanjeID, Integer brojKartice, Integer iznos, Integer stanje, Integer rezervacijaID) {
        this.placanjeID = placanjeID;
        this.brojKartice = brojKartice;
        this.iznos = iznos;
        this.stanje = stanje;
        this.rezervacijaID = rezervacijaID;
    }

    @Expose
    private Integer placanjeID;
    @Expose
    private Integer brojKartice;
    @Expose
    private Integer iznos;
    @Expose
    private Integer stanje;
    @Expose
    private Integer rezervacijaID;

    public Integer getPlacanjeID() {
        return placanjeID;
    }

    public void setPlacanjeID(Integer placanjeID) {
        this.placanjeID = placanjeID;
    }

    public Integer getBrojKartice() {
        return brojKartice;
    }

    public void setBrojKartice(Integer brojKartice) {
        this.brojKartice = brojKartice;
    }

    public Integer getIznos() {
        return iznos;
    }

    public void setIznos(Integer iznos) {
        this.iznos = iznos;
    }

    public Integer getStanje() {
        return stanje;
    }

    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

    public Integer getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(Integer rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

}