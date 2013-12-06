package com.example.tripassistant;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
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
