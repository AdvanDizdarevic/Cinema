package ba.fit.viewModel;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import java.util.Date;

import ba.fit.kino.R;
import ba.fit.kino.util.HttpManager2;

public class UnosFilmaFragment extends Fragment {

    Button Unos;
    EditText Naziv;
    EditText CijenaKarte;


	
	public UnosFilmaFragment(){}


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Naziv  = (EditText) getView().findViewById(R.id.NazivFilma);
        CijenaKarte = (EditText) getView().findViewById(R.id.CijenaKarte);


        /***********************************************/

        this.Unos = (Button) getView().findViewById(R.id.UnosFilma);
        Unos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (Naziv.getText().toString().isEmpty() == false && CijenaKarte.toString().isEmpty() == false) {


                    String NazivFilma = Naziv.getText().toString();
                    Float Cijenakarte = Float.valueOf(CijenaKarte.getText().toString());



                    if(NazivFilma.length()<2)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Naziv filma mora biti veci od dva karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }



                    // Dodavanje sale
                    insertFilma(NazivFilma, Cijenakarte, getActivity().getApplicationContext());
                    Naziv.getText().clear();
                    CijenaKarte.getText().clear();




                } else {
                    String poruka = " ";

                    if (Naziv.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite naziv filma.");
                    if (CijenaKarte.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite cijenu filma.");

                    Toast.makeText(getActivity().getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }
            }
        });







        /***********************************************/


    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_unos_filma, container, false);
         
        return rootView;
    }

    public static String insertFilma(String naziv,Float cijenaKarte,Context context)
    {
        String provjera = null;
        provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/insertFilmovi.php",
                new BasicNameValuePair("naziv", naziv),
                new BasicNameValuePair("cijenaKarte", cijenaKarte.toString())
        );

        if(provjera!=null) {
            Toast.makeText(context, "Uspijesno ste dodali novi film!", Toast.LENGTH_SHORT).show();
            provjera = null;
        }
        else
            Toast.makeText(context, "Film nije dodan!", Toast.LENGTH_SHORT).show();



        return provjera;
    }


}
