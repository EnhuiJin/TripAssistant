package com.example.tripassistant;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SettingActivity extends Activity {
	
	private TripAssistantDatabaseHelper db;
	private UserInfoModel user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
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
	
	public void signIn(View view){
		EditText emailET = (EditText)findViewById(R.id.editText5);
		EditText passwordET = (EditText)findViewById(R.id.editText4);
		String email = emailET.getText().toString();
		String password = passwordET.getText().toString();
		
		db = new TripAssistantDatabaseHelper(SettingActivity.this);
		user = db.login(email);
		
		if(user!=null && password.equals(user.getPw())){
			long uid = db.getUid(user);
			
			PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(UserInfoModel.PREF_TOKEN, uid).commit();
			viewProfile();
		}else{
			AlertDialogFragment alert = new AlertDialogFragment();
			alert.show(getFragmentManager(), "alert");
			
		}
			
		
	}
	
	public void signUp(View view){
		EditText emailET = (EditText)findViewById(R.id.editText1);
		EditText passwordET = (EditText)findViewById(R.id.editText3);
		EditText unameET = (EditText)findViewById(R.id.editText6);
		
		String email = emailET.getText().toString();
		String password = passwordET.getText().toString();
		String uname = unameET.getText().toString();
		
		db = new TripAssistantDatabaseHelper(SettingActivity.this);
		user = db.login(email);
		
		if(user!=null){
			CheckUserDuplicateFragment alert = new CheckUserDuplicateFragment();
			alert.show(getFragmentManager(), "user_check");
			emailET.setText("");
			passwordET.setText("");
			unameET.setText("");
			
		}else{
			UserInfoModel user2 = new UserInfoModel();
			user2.setEmail(email);
			user2.setPw(password);
			user2.setUname(uname);
			user2.setCity("New York");
			db.insertUserInfo(user2);
			viewProfile();
			
		}
		
		db.close();
		
	}
	
	public void viewProfile(){
		
		Intent intent=new Intent(this,ProfileActivity.class);
		startActivity(intent);
	}


}
