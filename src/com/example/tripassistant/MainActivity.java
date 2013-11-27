package com.example.tripassistant;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private TripAssistantDatabaseHelper db;
	private EntertainmentModel[] entm = new EntertainmentModel[10];
	private AttractionModel att;
	private RestaurantModel rest;
	private LocationModel[] loc = new LocationModel[10];
	
	
	
	public void insertRecord(){
		loc[0] = new LocationModel(402394,412322,"broadway");
		entm[0] = new EntertainmentModel("king lion","1330","1","2.5",loc[0]);
	
		db = new TripAssistantDatabaseHelper(MainActivity.this);
		
		db.insertEntertainment(entm[0]);
		db.close();
		
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//insertRecord();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	//add data here
	public void entertainmentSelect(View view) {
		
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
