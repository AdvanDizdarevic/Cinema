package ba.fit.kino.listaFilmovi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ba.fit.kino.R;
import ba.fit.kino.model.korisnik;

/**
 * Created by adnan_000 on 17.7.2014.
 */
public class korisnikItemAdapter extends ArrayAdapter<korisnik>{

    private ArrayList<korisnik> objects;

    public korisnikItemAdapter(Context context, int resource, List<korisnik> objects) {
        super(context, resource, objects);

        this.objects = (ArrayList<korisnik>)objects;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null )
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stditems,null);
        }

        korisnik std = this.objects.get(position);

        if(std != null)
        {
            TextView stdName = (TextView) convertView.findViewById(R.id.title1);

            if(stdName != null)
                stdName.setText(std.getIme());

        }

        return convertView;
    }

}
