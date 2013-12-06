package com.example.tripassistant;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends FragmentActivity {

	private TripAssistantDatabaseHelper db;
	private EntertainmentModel[] entm = new EntertainmentModel[10];
//	private AttractionModel att;
//	private RestaurantModel rest;
	private LocationModel[] loc = new LocationModel[10];
	private UserInfoModel user;
	private ScheduleModel sch;
	
//	private DataRetrival http;

	
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

//		List<ScheduleModel> sl = db.getAllHistory();
//		for(ScheduleModel sm: sl){
//			Log.i("test", sm.getSchEndTime() + " : " + sm.getSchEndTime() + " : " + sm.getEntmList().toString());
//		}
		db.close();	
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
//		insertRecord();
		
//		recom = new RecommendationIntro();
//		List<RecommendedItem> recommendation = recom.ItemBasedRecommender(1, 20);
//		Log.i("SIZE", recommendation.size() + "");
//		Log.i("RECOMMENDATION", recommendation.toString());
		
//		textOperation t = new textOperation();
//		t.writeFile("22,22");
//		t.readFile();
		
//		http = new DataRetrival();
//		List<EntertainmentModel> result = http.fromJsonToEM();
//		Log.i("dataSource", result.size() + "");
//		db = new TripAssistantDatabaseHelper(MainActivity.this);
//		for(EntertainmentModel entm: result){
//			db.insertEntertainment(entm);
//		}
//		db.close();

<<<<<<< HEAD
	}
	
	
	public void storePeriod(View view){
		EditText periodET = (EditText)findViewById(R.id.editText1);
		String period = periodET.getText().toString();
		PreferenceManager.getDefaultSharedPreferences(this).edit().putString(UserInfoModel.PREF_PERIOD, period).commit();
		
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
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
	        case R.id.history:
	            goToHistory();
	            return true;    
	        case R.id.log:
	        	goToSetting();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void goToHistory(){
		Intent intent = new Intent(this,HistoryActivity.class);
		startActivity(intent);
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
