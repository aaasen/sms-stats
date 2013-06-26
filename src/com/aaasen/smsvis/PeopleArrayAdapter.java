package com.aaasen.smsvis;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aaasen.smsvis.util.SMS;

public class PeopleArrayAdapter extends ArrayAdapter<SMS> {
	private ArrayList<SMS> messages;
	private final Context context;

	public PeopleArrayAdapter(Context context, int textViewResourceIdList, ArrayList<SMS> messages) {
		super(context, R.layout.person_fragment, messages);
		this.context = context;
		this.messages = messages;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		SMS message = messages.get(position);
		
		View personView = inflater.inflate(R.layout.person_fragment, parent, false);
	    
		TextView name = (TextView) personView.findViewById(R.id.name);
	    name.setText(message.getAddress());

		TextView messageCount = (TextView) personView.findViewById(R.id.message_count);
	    messageCount.setText(message.getBody());
	    
	    personView.setTag(message);
	    
		return personView;

	}
	
}
