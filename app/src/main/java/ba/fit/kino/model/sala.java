package ba.fit.kino.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class sala {
    public sala(Integer salaID, String naziv, Integer brojSjedista) {
        this.salaID = salaID;
        this.naziv = naziv;
        this.brojSjedista = brojSjedista;
    }

    public sala(String naziv, Integer brojSjedista) {
        this.naziv = naziv;
        this.brojSjedista = brojSjedista;
    }

    @Expose
    private Integer salaID;
    @Expose
    private String naziv;
    @Expose
    private Integer brojSjedista;

    public Integer getSalaID() {
        return salaID;
    }

    public void setSalaID(Integer salaID) {
        this.salaID = salaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojSjedista() {
        return brojSjedista;
    }

    public void setBrojSjedista(Integer brojSjedista) {
        this.brojSjedista = brojSjedista;
    }

}