package ba.fit.viewModel;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;

import ba.fit.kino.R;
import ba.fit.kino.util.HttpManager2;

public class UnosSaleFragment extends Fragment {

    Button Unos;
    EditText Naziv;
    EditText Sjedista;


	public UnosSaleFragment(){}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Naziv  = (EditText) getView().findViewById(R.id.NazivSale);
        Sjedista = (EditText) getView().findViewById(R.id.BrojSjedista);

        /***********************************************/

        this.Unos = (Button) getView().findViewById(R.id.UnosSale);
        Unos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if (Naziv.getText().toString().isEmpty() == false && Sjedista.toString().isEmpty() == false) {


                    String NazivSale = Naziv.getText().toString();
                    Integer BrojSjedista = Integer.parseInt(Sjedista.getText().toString());

                    if(NazivSale.length()<3)
                    {
                        Toast.makeText(getActivity().getApplicationContext(), "Naziv sale mora biti veci od tri karaktera!", Toast.LENGTH_SHORT).show();
                        return;
                    }



                    // Dodavanje sale
                    insertSale(NazivSale, BrojSjedista, getActivity().getApplicationContext());
                    Naziv.getText().clear();
                    Sjedista.getText().clear();




                } else {
                    String poruka = " ";

                    if (Naziv.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite naziv sale.");
                    if (Sjedista.getText().toString().isEmpty() == true)
                        poruka = poruka.concat(" Unesite broj sjedista.");

                    Toast.makeText(getActivity().getApplicationContext(), "Polja nisu popunjena, " + poruka.toString() + " !", Toast.LENGTH_SHORT).show();

                }
            }
        });







        /***********************************************/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_unos_sale, container, false);
         
        return rootView;
    }

    public static String insertSale(String naziv,Integer broj,Context context)
    {
        String provjera = null;
        provjera = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/insertSale.php",
                new BasicNameValuePair("naziv", naziv),
                new BasicNameValuePair("broj", Integer.toString(broj))
        );

        if(provjera!=null) {
            Toast.makeText(context, "Uspijesno ste dodali novu salu!", Toast.LENGTH_SHORT).show();
            provjera = null;
        }
        else
            Toast.makeText(context, "Sala nije dodana!", Toast.LENGTH_SHORT).show();



        return provjera;
    }

}
