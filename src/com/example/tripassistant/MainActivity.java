package com.example.tripassistant;


import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {

	private TripAssistantDatabaseHelper db;
	private EntertainmentModel[] entm = new EntertainmentModel[10];
//	private AttractionModel att;
//	private RestaurantModel rest;
	private LocationModel[] loc = new LocationModel[10];
	private UserInfoModel user;
	private ScheduleModel sch;
	
	private RecommendationIntro recom;

	
	public void insertRecord(){
		loc[0] = new LocationModel(402394,412322,"broadway");
		entm[0] = new EntertainmentModel("king lion","1330","1","2.5",loc[0]);
		entm[1] = new EntertainmentModel("soho", "1111", "11", "22", loc[0]);
		
		user = new UserInfoModel();
		user.setUname("09gavin");
		user.setEmail("gs@gmail.com");
		user.setPw("gs");
		user.setCity("brooklyn");
		
		sch = new ScheduleModel();
		sch.setSchStartTime("1234");
		sch.setSchEndTime("4321");
		sch.setEntmList(entm[0]);
		sch.setEntmList(entm[1]);
	
		db = new TripAssistantDatabaseHelper(MainActivity.this);
		
//		db.insertEntertainment(entm[0]);
//		db.insertEntertainment(entm[1]);
//		db.insertUserInfo(user);
//		db.insertHistory(sch);
		
//		UserInfoModel u = db.login("gs@gmail.com");
//		Log.i("test1", u.getPw() + " " + u.getCity());

		List<ScheduleModel> sl = db.getAllHistory();
		for(ScheduleModel sm: sl){
			Log.i("test", sm.getSchEndTime() + " : " + sm.getSchEndTime() + " : " + sm.getEntmList().toString());
		}
		db.close();	
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		insertRecord();
		
//		recom = new RecommendationIntro();
//		List<RecommendedItem> recommendation = recom.ItemBasedRecommender(1, 2);
//		Log.i("RECOMMENDATION", recommendation.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ts_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.home:
	            goToHome();
	            return true;
	        case R.id.cart:
	            goToCart();
	            return true;
	        case R.id.log:
	        	goToSetting();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void goToHome(){
		Intent intent =new Intent(this,MainActivity.class);
		startActivity(intent);
		
	}
	
	public void goToSetting(){
		Intent intent =new Intent(this,SettingActivity.class);
		startActivity(intent);
	}
	
	public void goToCart(){
		Intent intent =new Intent(this,CartActivity.class);
		startActivity(intent);
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
