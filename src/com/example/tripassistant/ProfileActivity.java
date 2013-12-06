package com.example.tripassistant;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.View;
import android.widget.TextView;
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376

public class ProfileActivity extends Activity {
	private TripAssistantDatabaseHelper db;
	private UserInfoModel user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		db = new TripAssistantDatabaseHelper(ProfileActivity.this);
		Long uid = PreferenceManager.getDefaultSharedPreferences(this).getLong(UserInfoModel.PREF_TOKEN, 0);
		user = db.getUser(uid);
		TextView usernameTV = (TextView)findViewById(R.id.username);
		usernameTV.setText(user.getUname());
		
		TextView emailTV = (TextView)findViewById(R.id.email);
		emailTV.setText(user.getEmail());
		
	}
	
	
	public void logout(View view){
		PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(UserInfoModel.PREF_TOKEN, 0).commit();
		PreferenceManager.getDefaultSharedPreferences(this).edit().putString(UserInfoModel.PREF_PERIOD, null).commit();
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		
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


}
