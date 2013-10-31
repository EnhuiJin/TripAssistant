package com.example.tripassistant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RestaurantSelectActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_select);
		
		TextView textView = new TextView(this);
		 textView.setTextSize(20);
		 textView.setText("Restaurant List");
		 setContentView(textView);
	}
}
