package ba.fit.kino.model;

import android.os.Parcel;
import android.os.Parcelable;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

import java.text.DateFormat;
import java.util.Date;

@Generated("org.jsonschema2pojo")
public class filmovi implements Parcelable{

    @Expose
    private Integer filmID;
    @Expose
    private String naziv;
    @Expose
    private Date datum;
    @Expose
    private Date trajanje;
    @Expose
    private float cijenaKarte;
    @Expose
    private Integer salaID;

//    public filmovi(Integer filmID, String naziv, Date datum, Date trajanje, Float cijenaKarte, Integer salaID) {
//        this.filmID = filmID;
//        this.naziv = naziv;
//        this.datum = datum;
//        this.trajanje = trajanje;
//        this.cijenaKarte = cijenaKarte;
//        this.salaID = salaID;
//    }

    public filmovi(Integer filmID, String naziv, Float cijenaKarte, Integer salaID) {
        this.filmID = filmID;
        this.naziv = naziv;
        this.cijenaKarte = cijenaKarte;
        this.salaID = salaID;
    }

    public Integer getFilmID() {
        return filmID;
    }

    public void setFilmID(Integer filmID) {
        this.filmID = filmID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum() {



        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Date trajanje) {
        this.trajanje = trajanje;
    }

    public Float getCijenaKarte() {
        return cijenaKarte;
    }

    public void setCijenaKarte(Float cijenaKarte) {
        this.cijenaKarte = cijenaKarte;
    }

    public Integer getSalaID() {
        return salaID;
    }

    public void setSalaID(Integer salaID) {
        this.salaID = salaID;
    }


    public filmovi (Parcel source)
    {

        this.setFilmID(source.readInt());
        this.setNaziv(source.readString());
        this.setCijenaKarte(source.readFloat());
        this.setSalaID(source.readInt());

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags)
    {
        dest.writeInt(this.filmID);
        dest.writeString(this.naziv);
        dest.writeFloat(this.cijenaKarte);
        dest.writeInt(this.salaID);
    }


    public static final Parcelable.Creator<filmovi> CREATOR = new Parcelable.Creator<filmovi>() {
        @Override
        public filmovi createFromParcel(Parcel source) {
            return new filmovi(source);
        }

        @Override
        public filmovi[] newArray(int size) {
            return new filmovi[size];
        }
    };


}