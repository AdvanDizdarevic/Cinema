package ba.fit.kino;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ba.fit.kino.R;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.util.HttpManager;
import ba.fit.kino.util.HttpManager2;
import ba.fit.kino.util.JSONParser;

public class RegistracijaActivity extends Activity {

    Button register;
    EditText korisnickoIme;
    EditText ime;
    EditText email;
    EditText lozinka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        setTitle("Registracija korisnika");


        korisnickoIme = (EditText) findViewById(R.id.username);
        ime = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        lozinka = (EditText) findViewById(R.id.password);


        this.register = (Button) findViewById(R.id.registracija);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (korisnickoIme.getText().toString().isEmpty() == false && ime.getText().toString().isEmpty() == false && email.getText().toString().isEmpty() == false &&
                        lozinka.getText().toString().isEmpty() == false) {


                    String usernameString = korisnickoIme.getText().toString();
                    String imeString = ime.getText().toString();
                    String emailString = email.getText().toString();
                    String passwordString = lozinka.getText().toString();


                    if (isValidEmail(emailString) == false) {
                        Toast.makeText(getApplicationContext(), "Format e-mail nije validan!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(passwordString.length()<4)
                    {
                        Toast.makeText(getApplicationContext(), "Lozinka mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(korisnickoIme.length()<4)
                    {
                        Toast.makeText(getApplicationContext(), "Korisnicko ime mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Dodavanje korisnika
                    insertKorisnik(usernameString, imeString, emailString, passwordString, getApplicationContext());
                    korisnickoIme.getText().clear();
                    ime.getText().clear();
                    email.getText().clear();
                    lozinka.getText().clear();



                } else {
                    String poruka = " ";

                    if (korisnickoIme.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite korisnicko ime.");
                    if (ime.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite ime korisnika.");
                    if (email.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite email.");
                    if (lozinka.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite lozinku");

                    Toast.makeText(getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registracija, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static String insertKorisnik(String username,String ime,String email, String password,Context context)
    {
        String provjera = null;
        provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/insertKorisnik.php",
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("ime", ime),
                new BasicNameValuePair("email", email),
                new BasicNameValuePair("password", password)

        );

        if(provjera!=null) {
            Toast.makeText(context, "Uspijesno ste registrovali korisnika!", Toast.LENGTH_SHORT).show();
            provjera = null;
        }
        else
            Toast.makeText(context, "Korisnik nije registrovan!", Toast.LENGTH_SHORT).show();



        return provjera;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}
