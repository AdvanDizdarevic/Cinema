package ba.fit.kino.util;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.util.Date;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import ba.fit.kino.R;
import ba.fit.kino.model.filmovi;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.model.rezervacije;

public class RezervacijaActivity extends Activity {

    filmovi filmoviRezervacija;
    korisnik korisnikRezervacija;


    Button RezervacijaButton;
    TextView KorisnickoIme;
    TextView vrijeme;
    TextView cijena;
    EditText brojKreditneKartice;

    BigInteger brojKreditne;
    java.util.Date date;
    String datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervacija);
        setTitle("Rezervacija filma");


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            filmoviRezervacija = bundle.getParcelable("RezervacijaFilm"); // hvatamo objekat film
            korisnikRezervacija = bundle.getParcelable("RezervacijaKorisnik"); // hvatamo objekat korisnik
        }

        date = new java.util.Date();

        datum = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date);


        KorisnickoIme = (TextView) findViewById(R.id.textViewImeKorisnika);
        KorisnickoIme.setText("Film: " + filmoviRezervacija.getNaziv());

        vrijeme = (TextView) findViewById(R.id.textViewVrijeme);
        vrijeme.setText("Vrijeme: " + datum.toString());

        cijena = (TextView) findViewById(R.id.textViewCijena);
        cijena.setText("Cijena: " + filmoviRezervacija.getCijenaKarte().toString() + " BAM");

        brojKreditneKartice = (EditText) findViewById(R.id.brojKreditneKartice);


        this.RezervacijaButton = (Button) findViewById(R.id.rezervacija);
        RezervacijaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (brojKreditneKartice.getText().toString().isEmpty() == false && brojKreditneKartice.getText().length() == 13) {
                    brojKreditne = new BigInteger(brojKreditneKartice.getText().toString());
                    Log.e("Trenutni broj", "Broj  " + brojKreditne + "  Datum: " + date);


                    RezervacijaKarte rezervacija = new RezervacijaKarte();

                    rezervacija.execute(datum.toString(), korisnikRezervacija.getKorisnikID().toString(), filmoviRezervacija.getFilmID().toString(), brojKreditne.toString(),
                            filmoviRezervacija.getCijenaKarte().toString());


                } else {
                    Toast.makeText(getApplicationContext(), "Broj kreditne kartice nije validan!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private class RezervacijaKarte extends AsyncTask<String, Void, String>
    {
       @Override
        protected String doInBackground(String... params) {

            String provjera = null;
            provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/insertRezervacije.php",
                    new BasicNameValuePair("vrijeme", params[0]),
                    new BasicNameValuePair("korisnikID", params[1]),
                    new BasicNameValuePair("filmID", params[2]),
                    new BasicNameValuePair("brojKreditneKartice",params[3]),
                    new BasicNameValuePair("iznos", params[4])
            );

            return provjera;
        }



        protected void onPostExecute(String provjera)
        {

            if(provjera!=null) {
                Toast.makeText(getApplicationContext(), "Uspijesno ste izvrsili rezervaciju!", Toast.LENGTH_SHORT).show();
                provjera = null;
            }
            else
                Toast.makeText(getApplicationContext(), "Rezervacija nije izvrsena!", Toast.LENGTH_SHORT).show();
        }

    }

}
