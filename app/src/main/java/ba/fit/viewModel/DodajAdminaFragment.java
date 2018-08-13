package ba.fit.viewModel;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.kino.FilmoviActivity;
import ba.fit.kino.R;
import ba.fit.kino.util.HttpManager2;

public class DodajAdminaFragment extends Fragment {

    Button AdminRegister;
    EditText AdminKorisnickoIme;
    EditText AdminIme;
    EditText AdminEmail;
    EditText AdminLozinka;

	
	public DodajAdminaFragment(){ }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        AdminKorisnickoIme = (EditText) getView().findViewById(R.id.AdminUsername);
        AdminIme = (EditText) getView().findViewById(R.id.AdminName);
        AdminEmail = (EditText) getView().findViewById(R.id.AdminEmail);
        AdminLozinka = (EditText) getView().findViewById(R.id.AdminPassword);




        this.AdminRegister = (Button) getView().findViewById(R.id.AdminRegistracija);
        AdminRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {





                if (AdminKorisnickoIme.getText().toString().isEmpty() == false && AdminIme.getText().toString().isEmpty() == false && AdminEmail.getText().toString().isEmpty() == false &&
                        AdminLozinka.getText().toString().isEmpty() == false) {


                    String usernameString = AdminKorisnickoIme.getText().toString();
                    String imeString = AdminIme.getText().toString();
                    String emailString = AdminEmail.getText().toString();
                    String passwordString = AdminLozinka.getText().toString();


                    if (isValidEmail(emailString) == false) {
                        Toast.makeText(getActivity().getApplicationContext(), "Format e-mail nije validan!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(passwordString.length()<4)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Lozinka mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(AdminKorisnickoIme.length()<4)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Korisnicko ime mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Dodavanje korisnika
                    insertKorisnik(usernameString, imeString, emailString, passwordString, getActivity().getApplicationContext());
                     AdminKorisnickoIme.getText().clear();
                     AdminIme.getText().clear();
                     AdminEmail.getText().clear();
                     AdminLozinka.getText().clear();





                } else {
                    String poruka = " ";

                    if (AdminKorisnickoIme.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite korisnicko ime.");
                    if (AdminIme.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite ime korisnika.");
                    if (AdminEmail.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite email.");
                    if (AdminLozinka.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite lozinku");

                    Toast.makeText(getActivity().getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }




            }
        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dodaj_admina, container, false);

        return rootView;
    }




    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



    public static String insertKorisnik(String username,String ime,String email, String password,Context context)
    {
        String provjera = null;
        provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/insertAdministrator.php",
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




}
