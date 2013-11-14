package com.example.tripassistant;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TripAssistantDatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "trips_assistant";

	private static final String TABLE_ENTERTAINMENT = "entertainment";
	private static final String COLUMN_ENTERTAINMENT_ID = "eid";
	private static final String COLUMN_ENTERTAINMENT_NAME = "ename";
	private static final String COLUMN_ENTERTAINMENT_LID = "lid";
	private static final String COLUMN_ENTERTAINMENT_TRAVELTIME = "eTravelTime";
	private static final String COLUMN_ENTERTAINMENT_STARTTIME = "eStartTime";
	private static final String COLUMN_ENTERTAINMENT_DURATION = "eDuration";

	private static final String TABLE_ATTRACTION = "attraction";
	private static final String COLUMN_ATTRACTION_ID = "aid";
	private static final String COLUMN_ATTRACTION_NAME = "aname";
	private static final String COLUMN_ATTRACTION_LID = "lid";
	private static final String COLUMN_ATTRACTION_TRAVELTIME = "aTravelTime";
	private static final String COLUMN_ATTRACTION_DURATION = "aDuration";

	private static final String TABLE_RESTAURANT = "restaurant";
	private static final String COLUMN_RESTAURANT_ID = "rid";
	private static final String COLUMN_RESTAURANT_NAME = "rname";
	private static final String COLUMN_RESTAURANT_LID = "lid";
	private static final String COLUMN_RESTAURANT_TRAVELTIME = "rTravelTime";
	private static final String COLUMN_RESTAURANT_DURATION = "rDuration";
	
	private static final String TABLE_LOCATION = "location";
	private static final String COLUMN_LOCATION_ID = "lid";
	private static final String COLUMN_LOCATION_LONGITUDE = "longitude";
	private static final String COLUMN_LOCATION_LATITUDE = "latitude";
	private static final String COLUMN_LOCATION_ADDRESS = "address";
	
	
	
	
	public TripAssistantDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create trip table
		db.execSQL("create table " + TABLE_ENTERTAINMENT + "(" + COLUMN_ENTERTAINMENT_ID
				+ " INTEGER PRIMARY KEY  autoincrement, " + COLUMN_ENTERTAINMENT_NAME
				+ " text, " + COLUMN_ENTERTAINMENT_LID + " integer references location(lid), "
				+ COLUMN_ENTERTAINMENT_TRAVELTIME + " text, "
				+ COLUMN_ENTERTAINMENT_STARTTIME +" text, "
				+COLUMN_ENTERTAINMENT_DURATION + " text)");

		db.execSQL("create table " + TABLE_ATTRACTION + "(" + COLUMN_ATTRACTION_ID
				+ " INTEGER PRIMARY KEY  autoincrement, " + COLUMN_ATTRACTION_NAME
				+ " text, " + COLUMN_ATTRACTION_LID + " INTEGER, "
				+ COLUMN_ATTRACTION_TRAVELTIME + " text, "
				+ COLUMN_ATTRACTION_DURATION + " text)");

		db.execSQL("create table " + TABLE_RESTAURANT + "(" + COLUMN_RESTAURANT_ID
				+ " INTEGER PRIMARY KEY  autoincrement, " + COLUMN_RESTAURANT_NAME
				+ " text, " + COLUMN_RESTAURANT_LID + " INTEGER, "
				+ COLUMN_RESTAURANT_TRAVELTIME + " text, "
				+ COLUMN_RESTAURANT_DURATION + " text)");
		
		db.execSQL("create table " + TABLE_LOCATION + "(" + COLUMN_LOCATION_ID
				+ " INTEGER PRIMARY KEY  autoincrement, " + COLUMN_LOCATION_LONGITUDE
				+ " text, " + COLUMN_LOCATION_LATITUDE + " text, "	
				+ COLUMN_LOCATION_ADDRESS + " text)");
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop older table if exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTERTAINMENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);

		// create tables again
		onCreate(db);
	}
	
	public long insertLocation(LocationModel loc){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_LOCATION_LONGITUDE, loc.getLongitude());
		cv.put(COLUMN_LOCATION_LATITUDE, loc.getLatitude());
		cv.put(COLUMN_LOCATION_ADDRESS, loc.getAddress());
		
		long insertLocId = getWritableDatabase().insert(TABLE_LOCATION,null,cv);
		return insertLocId;
	}

	public long insertEntertainment(EntertainmentModel entm) {
		
		
		ContentValues cv = new ContentValues();
		long insertLocId =insertLocation(entm.getEntmLoc());
		
		cv.put(COLUMN_ENTERTAINMENT_NAME, entm.getEntmName());
		cv.put(COLUMN_ENTERTAINMENT_LID, insertLocId);
		cv.put(COLUMN_ENTERTAINMENT_TRAVELTIME, entm.getEntmTravelTime());
		cv.put(COLUMN_ENTERTAINMENT_STARTTIME, entm.getEntmStartTime());
		cv.put(COLUMN_ENTERTAINMENT_DURATION, entm.getEntmDuration());
		// return id of new trip
		long insertEntmId = getWritableDatabase().insert(TABLE_ENTERTAINMENT, null, cv);

		
		return insertEntmId;

	}

	

	
}

