package ba.fit.kino;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ba.fit.kino.R;

import ba.fit.kino.model.UpdateKorisnikActivity;
import ba.fit.kino.model.filmovi;
import ba.fit.kino.model.korisnik;

public class FilmoviActivity extends Activity {

    korisnik primljeni;

    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmovi);
        setTitle("Testni  aktiviti");

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            primljeni = bundle.getParcelable("Korisnik"); // hvatamo objekat
        }




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

}
