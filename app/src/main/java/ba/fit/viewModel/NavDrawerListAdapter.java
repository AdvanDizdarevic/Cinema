package ba.fit.viewModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.fit.kino.R;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<NavigacijaMeni> navigacijaMenis;
	
	public NavDrawerListAdapter(Context context, ArrayList<NavigacijaMeni> navigacijaMenis){
		this.context = context;
		this.navigacijaMenis = navigacijaMenis;
	}

	@Override
	public int getCount() {
		return navigacijaMenis.size();
	}

	@Override
	public Object getItem(int position) {
		return navigacijaMenis.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
         
        imgIcon.setImageResource(navigacijaMenis.get(position).getIcon());
        txtTitle.setText(navigacijaMenis.get(position).getTitle());
        
        // displaying count
        // check whether it set visible or not
        if(navigacijaMenis.get(position).getCounterVisibility()){
        	txtCount.setText(navigacijaMenis.get(position).getCount());
        }else{
        	// hide the counter view
        	txtCount.setVisibility(View.GONE);
        }
        
        return convertView;
	}

}
