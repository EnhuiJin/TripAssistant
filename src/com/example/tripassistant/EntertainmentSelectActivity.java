package com.example.tripassistant;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EntertainmentSelectActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainment_select);
		
		
		 TextView textView = new TextView(this);
		 textView.setTextSize(20);
		 textView.setText("Entertainment List");
		 setContentView(textView);
	}
}
