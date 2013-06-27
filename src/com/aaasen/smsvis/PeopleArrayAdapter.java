package com.aaasen.smsvis;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aaasen.smsvis.util.Person;
import com.aaasen.smsvis.util.SMSStats;

public class PeopleArrayAdapter extends ArrayAdapter<Person> {
	private List<Person> people;
	private final Context context;

	public PeopleArrayAdapter(Context context, int textViewResourceIdList, List<Person> people) {
		super(context, R.layout.person_fragment, people);
		this.context = context;
		this.people = people;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		Person person = people.get(position);
		
		View personView = inflater.inflate(R.layout.person_fragment, parent, false);
	    
		TextView name = (TextView) personView.findViewById(R.id.name);
	    name.setText(person.getName());
		
	    TextView address = (TextView) personView.findViewById(R.id.address);
	    address.setText(person.getAddress());
	    
		TextView messageCount = (TextView) personView.findViewById(R.id.message_count);
	    messageCount.setText(SMSStats.getNumTotal(person.getMessages()) + " messages");

		TextView sentCount = (TextView) personView.findViewById(R.id.sent_count);
	    sentCount.setText("(" + SMSStats.getNumSent(person.getMessages()) + " sent, ");
	    
		TextView receivedCount = (TextView) personView.findViewById(R.id.received_count);
		receivedCount.setText(SMSStats.getNumReceived(person.getMessages()) + " recieved)");
	    
	    personView.setTag(person);
	    
		return personView;

	}
	
}
