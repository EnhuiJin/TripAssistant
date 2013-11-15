package com.example.tripassistant;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private TripAssistantDatabaseHelper db;
	private EntertainmentModel entm;
	private LocationModel loc;
	
	
	
	public void insertRecord(EntertainmentModel entm){
		db = new TripAssistantDatabaseHelper(MainActivity.this);
		db.insertEntertainment(entm);
		db.close();
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	//add data here
	public void entertainmentSelect(View view) {
		loc = new LocationModel(402394,412322,"broadway");
		entm = new EntertainmentModel("king lion",1330,1,2.5,loc);
		insertRecord(entm);
		Intent intent = new Intent(this, EntertainmentSelectActivity.class);
		startActivity(intent);
	}

	public void attractionSelect(View view) {
		Intent intent = new Intent(this, AttractionSelectActivity.class);
		startActivity(intent);
	}

	public void restaurantSelect(View view) {
		Intent intent = new Intent(this, RestaurantSelectActivity.class);
		startActivity(intent);
	}

}
