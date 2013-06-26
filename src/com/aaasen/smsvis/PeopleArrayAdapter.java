package com.aaasen.smsvis;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aaasen.smsvis.util.Person;
import com.aaasen.smsvis.util.SMSStats;

public class PeopleArrayAdapter extends ArrayAdapter<Person> {
	private SMSStats stats;
	private final Context context;

	public PeopleArrayAdapter(Context context, int textViewResourceIdList, SMSStats stats) {
		super(context, R.layout.person_fragment, stats.getPeople());
		Log.d("people", Integer.toString(stats.getPeople().size()));
		this.context = context;
		this.stats = stats;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		Person person = stats.getPeople().get(position);
		
		View personView = inflater.inflate(R.layout.person_fragment, parent, false);
	    
		TextView name = (TextView) personView.findViewById(R.id.name);
	    name.setText(person.getName());
		
//	    TextView address = (TextView) personView.findViewById(R.id.address);
//	    address.setText(person.getAddress());
	    
		TextView messageCount = (TextView) personView.findViewById(R.id.message_count);
	    messageCount.setText(Integer.toString(person.getMessages().size()));
	    
	    personView.setTag(person);
	    
		return personView;

	}
	
}
