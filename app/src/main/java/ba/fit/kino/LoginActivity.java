package ba.fit.kino;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import ba.fit.kino.listaFilmovi.ListaActivity;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.util.HttpManager;
import ba.fit.kino.util.HttpManager2;
import ba.fit.kino.util.JSONObjectMapper;
import ba.fit.kino.util.JSONParser;


public class LoginActivity extends Activity {

    Button btnSignIn;
    Button btnRegistracija;
    EditText txtKorisnickoIme;
    EditText txtPassword;
    korisnik k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getActionBar().setDisplayHomeAsUpEnabled(true);








        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        txtKorisnickoIme=(EditText)findViewById(R.id.txtLogin);
        txtPassword=(EditText)findViewById(R.id.txtPassword);




        this.btnSignIn = (Button)findViewById(R.id.btnSignin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                if (txtKorisnickoIme.getText().toString().isEmpty() == false || txtPassword.getText().toString().isEmpty() == false ||
                        txtKorisnickoIme.getText().toString().isEmpty() == false && txtPassword.getText().toString().isEmpty() == false) {

                    String uname = txtKorisnickoIme.getText().toString();
                    String pass = txtPassword.getText().toString();


                     k = getKorisnik(uname, pass);

                    if (k != null) {



                        if (txtKorisnickoIme.getText().toString().equals(k.getUsername().toString()) && txtPassword.getText().toString().equals(k.getPassword().toString())) {
                            Toast.makeText(getApplicationContext(), "Uspiješno ste se logovali!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ListaActivity.class);
                            intent.putExtra("Korisnik",k);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        if(txtKorisnickoIme.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty())
                        {
                            String praznoPolje;
                            if(txtKorisnickoIme.getText().toString().isEmpty())
                                praznoPolje = " ,Unesite korisničko ime";
                            else
                                praznoPolje = " ,Unesite lozinku";
                        Toast.makeText(getApplicationContext(), "Jedno polje nije popunjeno"+praznoPolje.toString()+" !", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Takav korisnik ne postoji!", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(txtPassword.getText().toString().isEmpty() && txtKorisnickoIme.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Popunite sva polja!", Toast.LENGTH_SHORT).show();
                }
            }


//
        });


        this.btnRegistracija = (Button)findViewById(R.id.btnRegistracija);
        btnRegistracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Registracija novog korisnika!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RegistracijaActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.login, menu);
//        return true;



        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_action_bar, menu);



        //menu.getItem(0).setTitle("Administracijski login");



        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.AdminLogin:
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

//    public List<korisnik> getKorisnikLista(String username,String password)
//    {
//        String str = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/getAllKorisnik.php");
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//
//        params.add(new BasicNameValuePair("username",username));
//        params.add(new BasicNameValuePair("password",password));
//
//
//        Gson gson = new GsonBuilder().create();
//        Type listType = new TypeToken<ArrayList<korisnik>>(){}.getType();
//
//        List<korisnik> result = gson.fromJson(str, listType);
//
//        return result;
//    }


    public static korisnik getKorisnik(String username, String password)
    {
        String str = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/getLogin.php",
                new BasicNameValuePair("username", username),
                new BasicNameValuePair("password", password)
        );
        Gson gson = new GsonBuilder().create();
        korisnik[] result = gson.fromJson(str, korisnik[].class);

        if (result.length > 0)
            return result[0];
        else
            return null;
    }



}
