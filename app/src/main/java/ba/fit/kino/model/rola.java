package ba.fit.kino.model;

/**
 * Created by adnan_000 on 5.7.2014.
 */
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class rola {

    @Expose
    private Integer rolaID;
    @Expose
    private String naziv;

    public Integer getRolaID() {
        return rolaID;
    }

    public void setRolaID(Integer rolaID) {
        this.rolaID = rolaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public rola(Integer rolaID, String naziv) {
        this.rolaID = rolaID;
        this.naziv = naziv;
    }

}