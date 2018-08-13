package ba.fit.kino.model;

/**
 * Created by adnan_000 on 5.7.2014.
 */

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class korisnik implements Parcelable{

    @Expose
    private Integer korisnikID;
    @Expose
    private String username;
    @Expose
    private String ime;
    @Expose
    private String email;
    @Expose
    private String password;
    @Expose
    private Integer rolaID;

    public Integer getKorisnikID() {
        return korisnikID;
    }


    public void setKorisnikID(Integer korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getUsername() {
        return username;
    }

    public korisnik(String username, String ime, String email, String password) {
        this.username = username;
        this.ime = ime;
        this.email = email;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public korisnik(Integer korisnikID, String username, String ime, String email, String password, Integer rolaID) {
        this.korisnikID = korisnikID;
        this.username = username;
        this.ime = ime;
        this.email = email;
        this.password = password;
        this.rolaID = rolaID;
    }

    public Integer getRolaID() {
        return rolaID;
    }

    public void setRolaID(Integer rolaID) {
        this.rolaID = rolaID;
    }


    public korisnik (Parcel source)
    {
        this.setKorisnikID(source.readInt());
        this.setUsername(source.readString());
        this.setIme(source.readString());
        this.setEmail(source.readString());
        this.setPassword(source.readString());
        this.setRolaID(source.readInt());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags)
    {
        dest.writeInt(this.korisnikID);
        dest.writeString(this.username);
        dest.writeString(this.ime);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeInt(this.rolaID);
    }


    public static final Parcelable.Creator<korisnik> CREATOR = new Parcelable.Creator<korisnik>() {
        @Override
        public korisnik createFromParcel(Parcel source) {
            return new korisnik(source);
        }

        @Override
        public korisnik[] newArray(int size) {
            return new korisnik[size];
        }
    };

}
