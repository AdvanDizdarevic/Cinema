package ba.fit.viewModel;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.kino.AdminPanelActivity;
import ba.fit.kino.R;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.util.HttpManager2;

public class PromjenaKorisnickihPodatakaAdminFragment extends Fragment {
	
	public PromjenaKorisnickihPodatakaAdminFragment(){}

    Button AdminRegister;

    EditText AdminIme;
    EditText AdminEmail;
    EditText AdminLozinka;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       final korisnik Korisnik = ((AdminPanelActivity) getActivity()).FragmentAdmin();
        Log.e("Korisnik", " Korisnik : " + Korisnik.getIme().toString());

        /**********************************************/



        AdminIme = (EditText) getView().findViewById(R.id.AdminName);
        AdminEmail = (EditText) getView().findViewById(R.id.AdminEmail);
        AdminLozinka = (EditText) getView().findViewById(R.id.AdminPassword);




        this.AdminRegister = (Button) getView().findViewById(R.id.AdminRegistracija);
        AdminRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (AdminIme.getText().toString().isEmpty() == false && AdminEmail.getText().toString().isEmpty() == false &&
                        AdminLozinka.getText().toString().isEmpty() == false) {


                    String imeString = AdminIme.getText().toString();
                    String emailString = AdminEmail.getText().toString();
                    String passwordString = AdminLozinka.getText().toString();


                    if (isValidEmail(emailString) == false) {
                        Toast.makeText(getActivity().getApplicationContext(), "Format e-mail nije validan!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (passwordString.length() < 4) {
                        Toast.makeText(getActivity().getApplicationContext(), "Lozinka mora biti veca od 4 karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    // Dodavanje korisnika
                    updateKorisnik(Korisnik.getIme().toString(), imeString, emailString, passwordString, getActivity().getApplicationContext(),Korisnik.getKorisnikID());

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

                    Toast.makeText(getActivity().getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }
            }
        });

        /********************************************/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_promjena_korisnickih_podataka_admin, container, false);
         
        return rootView;
    }


            public static String updateKorisnik(String username,String ime,String email, String password,Context context,Integer KorisnikID)
            {
                String provjera = null;
                provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/updateAdmin.php",
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
