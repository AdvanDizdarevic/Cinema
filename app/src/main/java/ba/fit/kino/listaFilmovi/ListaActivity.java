package ba.fit.kino.listaFilmovi;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ba.fit.kino.R;
import ba.fit.kino.model.UpdateKorisnikActivity;
import ba.fit.kino.model.filmovi;
import ba.fit.kino.model.korisnik;
import ba.fit.kino.util.HttpManager;
import ba.fit.kino.util.HttpManager2;
import ba.fit.kino.util.JSONObjectMapper;
import ba.fit.kino.util.JSONParser;
import ba.fit.kino.util.RezervacijaActivity;

public class ListaActivity extends Activity {

    ListView lstView ;
    filmoviItemAdapter  stdAdapter;
    korisnik primljeni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        setTitle("Lista filmova");

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            primljeni = bundle.getParcelable("Korisnik"); // hvatamo objekat
        }

        Log.e("Primljeni korisnik", primljeni.getEmail().toString() );


        lstView = (ListView) findViewById(R.id.listaKorisnika);


//        ArrayList<String> lstNames = new ArrayList<String>();
//
//        for(korisnik item:ListaKorisnika)
//            lstNames.add(item.getIme());



        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),String.valueOf(((filmovi)lstView.getItemAtPosition(position)).getNaziv()), Toast.LENGTH_SHORT).show();

                 filmovi Film = ((filmovi)lstView.getItemAtPosition(position));    //- Hvatanje cijelog objekta
                 Log.e("FILM ::::", Film.getNaziv().toString());


                Intent intent = new Intent(getApplicationContext(), RezervacijaActivity.class);
                intent.putExtra("RezervacijaFilm", Film);
                intent.putExtra("RezervacijaKorisnik", primljeni);
                startActivity(intent);



            }
        });


        List<filmovi> stdList = getFilmovi(null, null);
//        for(filmovi item : stdList)
//            Log.e("Korisnik iz Activitya: ",item.getNaziv().toString());


        stdAdapter = new filmoviItemAdapter(getApplicationContext(),R.layout.stditems, stdList);


        lstView.setAdapter(stdAdapter);

   }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filmovi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case R.id.KorisnikUpdatePodataka:
                Intent intent = new Intent(getApplicationContext(), UpdateKorisnikActivity.class);
                intent.putExtra("UpdateKorisnik",primljeni);
                startActivity(intent);
                break;
        }
        return true;
    }



    public List<filmovi> getFilmovi(String username,String password)
    {
        String str = HttpManager2.responseFromUrlPost("http://hci013.app.fit.ba/kino/web_services/getModificiraniFilmovi.php");
        List<NameValuePair> params = new ArrayList<NameValuePair>();


        params.add(new BasicNameValuePair("username",username));
        params.add(new BasicNameValuePair("password",password));


        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<filmovi>>(){}.getType();

        List<filmovi> result = gson.fromJson(str, listType);

//        for(filmovi item : result)
//            Log.e("Korisnik: ",item.getNaziv().toString());

        return result;
    }



}
