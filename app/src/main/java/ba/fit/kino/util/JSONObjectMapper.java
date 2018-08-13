package ba.fit.kino.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.format.DateUtils;
import android.util.Log;

import ba.fit.kino.model.filmovi;
import ba.fit.kino.model.korisnik;


// JsonObjectMapper mapira JSON object u klasu koja nam treba npr JSON u pacijent


public class JSONObjectMapper {

    public static korisnik jsonToKorisnik(JSONObject jsonObject) {                                                // JSON u pacijent
        korisnik Korisnik = null;                                                                                        // novi pacijent

        try {
            // Da bi parsali JSON objekt u pacijent moramo hvatat parametre json objekta npr jsonObject.getJSONArray("pacijent").getJSONObject(0).getString("Ime"),
            Korisnik = new korisnik(jsonObject.getJSONArray("korisnik").getJSONObject(0).getInt("korisnikID"),  // +Integere kada vadimo moramo parsat Integer.parseInt tako muha kaze 22:25min
                    jsonObject.getJSONArray("korisnik").getJSONObject(0).getString("username"),
                    jsonObject.getJSONArray("korisnik").getJSONObject(0).getString("ime"),
                    jsonObject.getJSONArray("korisnik").getJSONObject(0).getString("email"),
                    jsonObject.getJSONArray("korisnik").getJSONObject(0).getString("password"),
                    jsonObject.getJSONArray("korisnik").getJSONObject(0).getInt("rolaID"));
        } catch (Exception e) {

            Log.e("jsonToKorisnik", "JSON TO KORISNIK ERROR: " + e.getMessage());                                                    // printanje errora u konzolu

        }
        return Korisnik;                                                                                        // vraca pacijenta

    }

    public static List<korisnik> jsonListaKorisnika(JSONObject jsonObject) {
        List<korisnik> lstKorisnici = new ArrayList<korisnik>();
        try {
            for (int i = 0; i < (jsonObject.getJSONArray("korisnik").length()); i++) {                                        // For petlja prolazi kroz kroz Niz Json objekata doktori od pocetka do kraja i prebacuje ih u listu objekata Doktori


                lstKorisnici.add(new korisnik(jsonObject.getJSONArray("korisnik").getJSONObject(i).getInt("korisnikID"),  // +Integere kada vadimo moramo parsat Integer.parseInt tako muha kaze 22:25min
                        jsonObject.getJSONArray("korisnik").getJSONObject(i).getString("username"),
                        jsonObject.getJSONArray("korisnik").getJSONObject(i).getString("ime"),
                        jsonObject.getJSONArray("korisnik").getJSONObject(i).getString("email"),
                        jsonObject.getJSONArray("korisnik").getJSONObject(i).getString("password"),
                        jsonObject.getJSONArray("korisnik").getJSONObject(i).getInt("rolaID")));
            }
        } catch (JSONException e) {
            Log.e("jsonToKorisnik", e.getMessage());
        }


        return lstKorisnici;
    }


    public static filmovi jsonToFilmovi(JSONObject jsonObject) {                                                // JSON u pacijent
        filmovi Filmovi = null;


        try {
            Filmovi = new filmovi(jsonObject.getJSONArray("filmovi").getJSONObject(0).getInt("filmID"),

                    jsonObject.getJSONArray("filmovi").getJSONObject(0).getString("naziv"),
//                    jsonObject.getJSONArray("filmovi").getJSONObject(0).get("datum").toString()),
//                   jsonObject.getJSONArray("filmovi").getJSONObject(0).getString("trajanje"),
                    float.class.cast(jsonObject.getJSONArray("filmovi").getJSONObject(0).get("cijenaKarte")),
                            jsonObject.getJSONArray("filmovi").getJSONObject(0).getInt("salaID"));
        } catch (Exception e) {

            Log.e("jsontToFilmovi", "JSON TO FILMOVI ERROR: " + e.getMessage());                                                    // printanje errora u konzolu
        }
        return Filmovi;

    }
}





































	
//	public static List<Doktor> jsonToDoktorList(JSONObject jsonObj){										    // Isto njesra i sa doktorom i ostalim
//
//		List<Doktor> lstDoktori=new ArrayList<Doktor>();
//		try {
//		for(int i=0; i<(jsonObj.getJSONArray("doktori").length()); i++){										// For petlja prolazi kroz kroz Niz Json objekata doktori od pocetka do kraja i prebacuje ih u listu objekata Doktori
//
//
//				lstDoktori.add(new Doktor(jsonObj.getJSONArray("doktori").getJSONObject(i).getInt("DoktorID"),	// ubacuje u listu... mjesto brojeva u nizu proslijedimo '" i "
//						                  jsonObj.getJSONArray("doktori").getJSONObject(i).getString("Titula"),
//						                  jsonObj.getJSONArray("doktori").getJSONObject(i).getString("Ime"),
//						                  jsonObj.getJSONArray("doktori").getJSONObject(i).getString("Prezime")));
//			}
//		}
//		catch (JSONException e) {
//				Log.e("JSONObjToDoktoriList", e.getMessage());
//			}
//
//
//		return lstDoktori;
//	}
//
//	public static List<Odjeljenje> jsonToOdjeljenjaList (JSONObject jsonObj){
//
//		List<Odjeljenje> result=new ArrayList<Odjeljenje>();
//
//		try {
//
//			for(int i=0; i<(jsonObj.getJSONArray("odjeljenja").length());i++){
//
//			result.add(new Odjeljenje(jsonObj.getJSONArray("odjeljenja").getJSONObject(i).getInt("OdjeljenjeID"),
//										jsonObj.getJSONArray("odjeljenja").getJSONObject(i).getString("Naziv")));
//
//			}
//
//
//		} catch (Exception e) {
//			Log.e("GRESKA: jsonToOdjeljenjeList", e.getMessage());
//		}
//
//		return result;
//	}
//
//	public static List<PregledFromJSONObj> jsonToPreglediList (JSONObject jsonObject){
//
//		List<PregledFromJSONObj> pregledi= new ArrayList<PregledFromJSONObj>();
//
//		try {
//			for(int i=0; i<(jsonObject.getJSONArray("Pregledi").length());i++){
//
//				PregledFromJSONObj p= new PregledFromJSONObj(jsonObject.getJSONArray("Pregledi").getJSONObject(i).getInt("PregledID"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("DatumPregleda"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("VrstaPregleda"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("OpisDijagnozePregleda"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getInt("DoktorID"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("Ime"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("Prezime"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("Titula"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("NazivDijagnoze"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getInt("OdjeljenjeID"),
//																	jsonObject.getJSONArray("Pregledi").getJSONObject(i).getString("NazivOdjeljenja"));
//
//				pregledi.add(p);
//
//
//			}
//
//			for(int i=0; i<(jsonObject.getJSONArray("ZakazaniPregledi").length());i++){
//
//				PregledFromJSONObj p= new PregledFromJSONObj(jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getInt("PregledID"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("DatumPregleda"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("VrstaPregleda"),
//						null,
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getInt("DoktorID"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("Ime"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("Prezime"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("Titula"),
//						null,
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getInt("OdjeljenjeID"),
//						jsonObject.getJSONArray("ZakazaniPregledi").getJSONObject(i).getString("NazivOdjeljenja"));
//
//				pregledi.add(p);
//
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return pregledi;
//
//		}
//
//	public static List<TerapijaFromJSONObj> jsonToTerapijeListFromJsonObject(JSONObject jsonObject){
//
//		List<TerapijaFromJSONObj> terapije= new ArrayList<TerapijaFromJSONObj>();
//
//		try {
//			for(int i=0; i<(jsonObject.getJSONArray("terapije").length());i++){
//				terapije.add(new TerapijaFromJSONObj(jsonObject.getJSONArray("terapije").getJSONObject(i).getInt("PropisanaTerapijaID"),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("DatumPocetka"),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("KoristeniLijekovi"),
//						                             Float.parseFloat(jsonObject.getJSONArray("terapije").getJSONObject(i).get("BrojPrimjene").toString()),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("Kolicina"),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("Trajanje"),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("Opis"),
//						                             jsonObject.getJSONArray("terapije").getJSONObject(i).getString("NacinPrimjene")));
//
//			}
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return terapije;
//	}
//}
