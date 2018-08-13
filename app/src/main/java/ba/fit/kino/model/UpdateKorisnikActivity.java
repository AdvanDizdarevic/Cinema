package ba.fit.kino.model;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.kino.R;
import ba.fit.kino.util.HttpManager2;

public class UpdateKorisnikActivity extends Activity {

    Button AdminRegister;

    EditText AdminIme;
    EditText AdminEmail;
    EditText AdminLozinka;
    korisnik primljeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_korisnik);
        setTitle("Ažuriranje podataka");


        /**********************************************/



        AdminIme = (EditText) findViewById(R.id.AdminName);
        AdminEmail = (EditText) findViewById(R.id.AdminEmail);
        AdminLozinka = (EditText) findViewById(R.id.AdminPassword);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            primljeni = bundle.getParcelable("UpdateKorisnik"); // hvatamo objekat
        }
//        if(primljeni!=null)
//            Log.e("AAAAAAAAAA","AAAAAAAAAAAAA"+primljeni.getIme().toString());


        this.AdminRegister = (Button) findViewById(R.id.AdminRegistracija);
        AdminRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (AdminIme.getText().toString().isEmpty() == false && AdminEmail.getText().toString().isEmpty() == false &&
                        AdminLozinka.getText().toString().isEmpty() == false) {


                    String imeString = AdminIme.getText().toString();
                    String emailString = AdminEmail.getText().toString();
                    String passwordString = AdminLozinka.getText().toString();


                    if (isValidEmail(emailString) == false) {
                        Toast.makeText(getApplicationContext(), "Format e-mail nije validan!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (passwordString.length() < 4) {
                        Toast.makeText(getApplicationContext(), "Lozinka mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    // Dodavanje korisnika
                    if(primljeni.getRolaID().toString().equals("2"))
                    updateKorisnik(primljeni.getIme().toString(), imeString, emailString, passwordString,getApplicationContext(),primljeni.getKorisnikID());
                    else{
                        Toast.makeText(getApplicationContext(), "Morate promijeniti korisnicke podatke u admin panelu!", Toast.LENGTH_SHORT).show();
                        AdminIme.getText().clear();
                        AdminEmail.getText().clear();
                        AdminLozinka.getText().clear();
                        return;
                    }


                    AdminIme.getText().clear();
                    AdminEmail.getText().clear();
                    AdminLozinka.getText().clear();


                } else {
                    String poruka = " ";


                    if (AdminIme.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite ime korisnika.");
                    if (AdminEmail.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite email.");
                    if (AdminLozinka.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite lozinku");

                    Toast.makeText(getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        /********************************************/



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.update_korisnik, menu);
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





    public static String updateKorisnik(String username,String ime,String email, String password,Context context,Integer KorisnikID)
    {
        String provjera = null;
        provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/updateKorisnik.php",
                new BasicNameValuePair("korisnikID", Integer.toString(KorisnikID)),
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("ime", ime),
                new BasicNameValuePair("email", email),
                new BasicNameValuePair("password", password)

        );

        if(provjera!=null) {
            Toast.makeText(context, "Uspijesno ste azurirali podatke!", Toast.LENGTH_SHORT).show();
            provjera = null;


        }
        else
            Toast.makeText(context, "Podatci nisu azurirani!", Toast.LENGTH_SHORT).show();



        return provjera;
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
