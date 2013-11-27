package com.example.tripassistant;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EntertainmentSelectActivity extends Activity{
	
	private TripAssistantDatabaseHelper db;
	private List<EntertainmentModel> entmList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainment_select);
		
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
		entmList = db.getEntm();
	
		String[] entmArray = new String[entmList.size()];
		for(int i=0; i<entmList.size();++i){
			entmArray[i]=entmList.toString();
			System.out.println(entmArray[i]);
			
		}
		
	    
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		        android.R.layout.simple_list_item_1, entmArray);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
//		 TextView textView = new TextView(this);
//		 textView.setTextSize(20);
//		 textView.setText("Entertainment List");
//		 setContentView(textView);
	}
}
