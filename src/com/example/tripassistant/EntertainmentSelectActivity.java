package com.example.tripassistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
<<<<<<< HEAD
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
=======
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EntertainmentSelectActivity extends Activity {

	private TripAssistantDatabaseHelper db;
	private List<EntertainmentModel> entmList;
	private RecommendationIntro recom;
	private DataRetrival http;
	public static final int ENTM_SIZE = 20;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.entertainment_select);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
//		insertEntmRecord();
		entmList = db.getEntm();
<<<<<<< HEAD
//		entmList = getRecommendedEntm(1);
=======
=======
	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	}

		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
//		insertEntmRecord();
//		entmList = db.getEntm();
>>>>>>> 820766ded428af807fe3d9dba527b6d041116b4c
		entmList = getRecommendedEntm(1);
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d

		String[] entmArray = new String[entmList.size()];
		for (int i = 0; i < entmList.size(); ++i) {
			entmArray[i] = entmList.get(i).getEntmName();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, entmArray);

		ListView listView = (ListView) findViewById(R.id.listView1);
<<<<<<< HEAD
		listView.setAdapter(adapter);	
		listView.setOnItemClickListener(listener);
	}
	
	private OnItemClickListener listener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            Intent intent = new Intent(EntertainmentSelectActivity.this, EventDetails.class);
            String value = (String)parent.getItemAtPosition(position);
            intent.putExtra("VALUE", value);
            startActivity(intent);
        }
    };
	
=======
		listView.setAdapter(adapter);
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d

	public List<EntertainmentModel> getRecommendedEntm(long uid) {
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);

		recom = new RecommendationIntro();
		List<EntertainmentModel> allList = new ArrayList<EntertainmentModel>();
		List<RecommendedItem> recommendation = recom.ItemBasedRecommender(uid,
				20);
		for (RecommendedItem item : recommendation) {
			long eid = item.getItemID();
			allList.add(db.getEntm(eid));
		}
		
		if (allList.size() < ENTM_SIZE) {
			int size = ENTM_SIZE - allList.size();
			http = new DataRetrival();
			List<EntertainmentModel> el = http.fromJsonToEM();
//			List<EntertainmentModel> el = db.getEntm();
			el = db.getEntm();
			for (int i = 0; i < size; i++) {
				allList.add(el.get(el.size() - 1 - i));
			}
		}
		db.close();
		return allList;
	}
	
	public void insertEntmRecord(){
		http = new DataRetrival();
		List<EntertainmentModel> result = http.fromJsonToEM();
//		Log.i("dataSource", result.size() + "");
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
		for(EntertainmentModel entm: result){
			db.insertEntertainment(entm);
		}
		db.close();
	}

	public List<EntertainmentModel> getRecommendedEntm(long uid) {
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);

		recom = new RecommendationIntro();
		List<EntertainmentModel> allList = new ArrayList<EntertainmentModel>();
		List<RecommendedItem> recommendation = recom.ItemBasedRecommender(uid,
				20);
		for (RecommendedItem item : recommendation) {
			long eid = item.getItemID();
<<<<<<< HEAD
=======
			Log.i("EID", eid + "");
>>>>>>> 820766ded428af807fe3d9dba527b6d041116b4c
			allList.add(db.getEntm(eid));
		}
		
		if (allList.size() < ENTM_SIZE) {
			int size = ENTM_SIZE - allList.size();
			http = new DataRetrival();
			List<EntertainmentModel> el = http.fromJsonToEM();
//			List<EntertainmentModel> el = db.getEntm();
			el = db.getEntm();
			for (int i = 0; i < size; i++) {
				allList.add(el.get(el.size() - 1 - i));
			}
		}
		db.close();
		return allList;
	}
	
	public void insertEntmRecord(){
		http = new DataRetrival();
		List<EntertainmentModel> result = http.fromJsonToEM();
		Log.i("dataSource", result.size() + "");
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
		for(EntertainmentModel entm: result){
			db.insertEntertainment(entm);
		}
		db.close();
	}
}
