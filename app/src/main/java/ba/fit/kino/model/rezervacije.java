package ba.fit.kino.model;

/**
 * Created by adnan_000 on 11.7.2014.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

import java.math.BigInteger;

@Generated("org.jsonschema2pojo")
public class rezervacije {

    @Expose
    private Integer rezervacijaID;

    public rezervacije(Integer rezervacijaID, String vrijeme, Integer korisnikID, Integer filmID, BigInteger brojKreditneKartice, Float cijena) {
        this.rezervacijaID = rezervacijaID;
        this.vrijeme = vrijeme;
        this.korisnikID = korisnikID;
        this.filmID = filmID;
        this.brojKreditneKartice = brojKreditneKartice;
        this.cijena = cijena;
    }

    @Expose
    private String vrijeme;
    @Expose
    private Integer korisnikID;
    @Expose
    private Integer filmID;
    @Expose
    private BigInteger brojKreditneKartice;
    @Expose
    private Float cijena;

    public BigInteger getBrojKreditneKartice() {
        return brojKreditneKartice;
    }

    public void setBrojKreditneKartice(BigInteger brojKreditneKartice) {
        this.brojKreditneKartice = brojKreditneKartice;
    }

    public Float getCijena() {
        return cijena;
    }

    public void setCijena(Float cijena) {
        this.cijena = cijena;
    }

    public Integer getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(Integer rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public Integer getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Integer korisnikID) {
        this.korisnikID = korisnikID;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

}