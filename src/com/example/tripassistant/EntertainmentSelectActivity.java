package com.example.tripassistant;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
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
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
//		insertEntmRecord();
		entmList = db.getEntm();
		entmList = getRecommendedEntm(1);

		String[] entmArray = new String[entmList.size()];
		for (int i = 0; i < entmList.size(); ++i) {
			entmArray[i] = entmList.get(i).getEntmName();
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, entmArray);

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);

	}

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
		Log.i("dataSource", result.size() + "");
		db = new TripAssistantDatabaseHelper(EntertainmentSelectActivity.this);
		for(EntertainmentModel entm: result){
			db.insertEntertainment(entm);
		}
		db.close();
	}
}
