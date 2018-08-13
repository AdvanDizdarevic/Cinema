package ba.fit.kino;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.kino.R;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.util.HttpManager2;
import ba.fit.viewModel.NavDrawerListAdapter;
import ba.fit.viewModel.NavigacijaMeni;

public class AdminLoginActivity extends Activity {



    Button btnSignInAdmin;
    EditText txtKorisnickoImeAdmin;
    EditText txtPasswordAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        setTitle("Administratorski login");



        txtKorisnickoImeAdmin=(EditText)findViewById(R.id.txtAdminUsername);
        txtPasswordAdmin=(EditText)findViewById(R.id.txtAdminPassword);

        this.btnSignInAdmin = (Button)findViewById(R.id.btnAdminSignin);
        btnSignInAdmin.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                if (txtKorisnickoImeAdmin.getText().toString().isEmpty() == false || txtPasswordAdmin.getText().toString().isEmpty() == false ||
                txtKorisnickoImeAdmin.getText().toString().isEmpty() == false && txtPasswordAdmin.getText().toString().isEmpty() == false) {

                    String uname = txtKorisnickoImeAdmin.getText().toString();
                    String pass = txtPasswordAdmin.getText().toString();


                    korisnik k = getKorisnik(uname, pass);

                    if (k != null) {



                        if (txtKorisnickoImeAdmin.getText().toString().equals(k.getUsername().toString()) && txtPasswordAdmin.getText().toString().equals(k.getPassword().toString())) {
                            Toast.makeText(getApplicationContext(), "Uspiješno ste se logovali!", Toast.LENGTH_SHORT).show();

                            /*********************************/

//                            Intent intent = new Intent(MainActivity.this,
//                                    TargetActivity.class);
//                            intent.putExtra("message", message);
//                            startActivity(intent);


                            /*********************************/


                            Intent intent = new Intent(getApplicationContext(), AdminPanelActivity.class);
                            intent.putExtra("poslaniAdmin",k);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        if(txtKorisnickoImeAdmin.getText().toString().isEmpty() || txtPasswordAdmin.getText().toString().isEmpty())
                        {
                            String praznoPolje;
                            if(txtKorisnickoImeAdmin.getText().toString().isEmpty())
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
                    if(txtPasswordAdmin.getText().toString().isEmpty() && txtKorisnickoImeAdmin.getText().toString().isEmpty())
                        Toast.makeText(getApplicationContext(), "Popunite sva polja!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    public static korisnik getKorisnik(String username, String password)
    {
        String str = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/getAdminKorisnik.php",
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
