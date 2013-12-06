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
<<<<<<< HEAD
	private static final String DATABASE_NAME = "trips_assistant_db_test6";
=======
<<<<<<< HEAD
	private static final String DATABASE_NAME = "trips_assistant_db_test6";
=======
	private static final String DATABASE_NAME = "trips_assistant_db_test2";
>>>>>>> 820766ded428af807fe3d9dba527b6d041116b4c
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d
	
	private static final String TABLE_USERINFO = "userInfo";
	private static final String COLUMN_USERINFO_ID = "uid";
	private static final String COLUMN_USERINFO_UNAME = "uname";
	private static final String COLUMN_USERINFO_EMAIL = "uemail";
	private static final String COLUMN_USERINFO_PW = "upw";
	private static final String COLUMN_USERINFO_CITY = "ucity";

	private static final String TABLE_HISTORY = "history";
	private static final String COLUMN_HISTORY_ID = "hid";
	private static final String COLUMN_HISTORY_STARTTIME = "hstarttime";
	private static final String COLUMN_HISTORY_ENDTIME = "hendtime";
	
	private static final String TABLE_USERHISTORY = "userhistory";
	private static final String COLUMN_USERHISTORY_UID = "uid";
	private static final String COLUMN_USERHISTORY_HID = "hid";
	
	private static final String TABLE_HISTORYENTM = "historyEntm";
	private static final String COLUMN_HISTORYENTM_HID = "hid";
	private static final String COLUMN_HISTORYENTM_EID = "eid";
	
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
		
		db.execSQL("create table " + TABLE_USERINFO + "(" 
				+ COLUMN_USERINFO_ID + " INTEGER PRIMARY KEY  autoincrement, " 
				+ COLUMN_USERINFO_UNAME + " text, "
				+ COLUMN_USERINFO_EMAIL + " text, " 
				+ COLUMN_USERINFO_PW + " text, "
				+ COLUMN_USERINFO_CITY + " text)");
		
		db.execSQL("create table " + TABLE_HISTORY + "(" 
				+ COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY  autoincrement, " 
				+ COLUMN_HISTORY_STARTTIME + " text, " 
				+ COLUMN_HISTORY_ENDTIME + " text)");
		
		db.execSQL("create table " + TABLE_USERHISTORY + "(" 
				+ COLUMN_USERHISTORY_UID + " integer references userInfo(uid), " 
				+ COLUMN_USERHISTORY_HID + " integer references history(hid))");
		
		db.execSQL("create table " + TABLE_HISTORYENTM + "(" 
				+ COLUMN_HISTORYENTM_HID + " integer references history(hid), " 
				+ COLUMN_HISTORYENTM_EID + " integer references entertainment(eid))");
		
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
				+ " double, " + COLUMN_LOCATION_LATITUDE + " double, "	
				+ COLUMN_LOCATION_ADDRESS + " text)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop older table if exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERINFO);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERHISTORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORYENTM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTERTAINMENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTION);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);

		// create tables again
		onCreate(db);
	}
	
	
	
	public UserInfoModel login(String email){
		SQLiteDatabase db = this.getReadableDatabase();
		UserInfoModel user =null;
		String user_check = "select * from " + TABLE_USERINFO + " where uemail = ?";
		Cursor cursor = db.rawQuery(user_check, new String[] {email});
		if(cursor.getCount()!=0){
		cursor.moveToFirst();
		user = new UserInfoModel();
		user.setUname(cursor.getString(1));
		user.setEmail(cursor.getString(2));
		user.setPw(cursor.getString(3));
		user.setCity(cursor.getString(4));
		}
		cursor.close();
		
		return user;
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public UserInfoModel getUser(Long uid){
		SQLiteDatabase db = this.getReadableDatabase();
		UserInfoModel user =null;
		String user_get = "select * from " + TABLE_USERINFO + " where uid = ?";
		String uid2 = uid.toString();
		Cursor cursor = db.rawQuery(user_get,new String[] {uid2});
		if(cursor.getCount()!=0){
			cursor.moveToFirst();
			user = new UserInfoModel();
			user.setUname(cursor.getString(1));
			user.setEmail(cursor.getString(2));
			user.setPw(cursor.getString(3));
			user.setCity(cursor.getString(4));
			}
			cursor.close();
			
			return user;
		
	}
	
	public long getUid(UserInfoModel user){
		SQLiteDatabase db=this.getReadableDatabase();
		long uid=0;
		String uid_get = "select uid from " + TABLE_USERINFO + " where uemail = ?";
		Cursor cursor = db.rawQuery(uid_get, new String[] {user.getEmail()});
		if(cursor.getCount()!=0){
			cursor.moveToFirst();
			uid = cursor.getLong(0);
		}
		return uid;
	}
	
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
	public EntertainmentModel getEntm(long eid){
		SQLiteDatabase db = this.getReadableDatabase();
		String entm_get = "select * from " + TABLE_ENTERTAINMENT + " where eid = ?";
		Cursor cursor = db.rawQuery(entm_get, new String[] {Long.toString(eid)});
		cursor.moveToFirst();
		EntertainmentModel entm = new EntertainmentModel();		
		entm.setEntmName(cursor.getString(1));			
		entm.setEntmTravelTime(cursor.getString(3));
		entm.setEntmStartTime(cursor.getString(4));
		entm.setEntmDuration(cursor.getString(5));			
		long locid = cursor.getLong(2);
		LocationModel loc = getLoc(locid);
		entm.setEntmLoc(loc);
		cursor.close();
		return entm;
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
	public EntertainmentModel getEntmByName(String name){
		SQLiteDatabase db = this.getReadableDatabase();
		String entm_get = "select * from " + TABLE_ENTERTAINMENT + " where ename = ?";
		Cursor cursor = db.rawQuery(entm_get, new String[] {name});
		cursor.moveToFirst();
		EntertainmentModel entm = new EntertainmentModel();		
		entm.setEntmName(cursor.getString(1));			
		entm.setEntmTravelTime(cursor.getString(3));
		entm.setEntmStartTime(cursor.getString(4));
		entm.setEntmDuration(cursor.getString(5));			
		long locid = cursor.getLong(2);
		LocationModel loc = getLoc(locid);
		entm.setEntmLoc(loc);
		cursor.close();
		return entm;
	}
	
=======
>>>>>>> d97e03c469032786111c3345fcd415b4412bcd1d
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
=======
>>>>>>> 1034e85b5f7d96dd604b24fb2ffbc206407c6376
	
	public List<ScheduleModel> getAllHistory(){
		List<ScheduleModel> sList = new ArrayList<ScheduleModel>();
		SQLiteDatabase db = this.getReadableDatabase();
		String getAllHistory_query = "select * from " + TABLE_HISTORY;
		Cursor cursor = db.rawQuery(getAllHistory_query, null);

		// loop through all query results
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			ScheduleModel sch = new ScheduleModel();
			sch.setSchStartTime(cursor.getString(1));
			sch.setSchEndTime(cursor.getString(2));

			long hId = cursor.getInt(0);
			List<EntertainmentModel> eList = getAllHisEntm(hId);
			for (EntertainmentModel eModel : eList) {
				sch.setEntmList(eModel);
			}
			sList.add(sch);
		}
		cursor.close();
		return sList;
	}
	
	public List<EntertainmentModel> getAllHisEntm(long hId){
		List<EntertainmentModel> eList = new ArrayList<EntertainmentModel>();
		SQLiteDatabase db = this.getReadableDatabase();
		String getAllHisEntm_query = "select * from " + TABLE_ENTERTAINMENT
				+ " natural join " + TABLE_HISTORYENTM + " where hid = ?";
		Cursor cursor = db.rawQuery(getAllHisEntm_query,
				new String[] { Long.toString(hId) });

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			EntertainmentModel entm = new EntertainmentModel();		
			entm.setEntmName(cursor.getString(1));			
			entm.setEntmTravelTime(cursor.getString(3));
			entm.setEntmStartTime(cursor.getString(4));
			entm.setEntmDuration(cursor.getString(5));			
			long locid = cursor.getLong(2);
			LocationModel loc = getLoc(locid);
			entm.setEntmLoc(loc);
			eList.add(entm);
		}
		cursor.close();
		return eList;
	}
	
	public long insertUserInfo(UserInfoModel user){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_USERINFO_UNAME, user.getUname());
		cv.put(COLUMN_USERINFO_EMAIL, user.getEmail());
		cv.put(COLUMN_USERINFO_PW, user.getPw());
		cv.put(COLUMN_USERINFO_CITY, user.getCity());

		return getWritableDatabase().insert(TABLE_USERINFO, null, cv);
	}
	
	public long insertHistory(ScheduleModel sch, long uid){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_HISTORY_STARTTIME, sch.getSchStartTime());
		cv.put(COLUMN_HISTORY_ENDTIME, sch.getSchEndTime());
		
		long hId = getWritableDatabase().insert(TABLE_HISTORY, null, cv);
		long userhistory = insertUserHistory(uid, hId);

		List<EntertainmentModel> eList = sch.getEntmList();
		for (EntertainmentModel em : eList) {
			long eId = insertEntertainment(em);
			long historyentm = insertHistoryEntm(hId, eId);
		}

		return hId;
	}
	
	public long insertUserHistory(long uid, long hid){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_USERHISTORY_UID, uid);
		cv.put(COLUMN_USERHISTORY_HID, hid);
		return getWritableDatabase().insert(TABLE_USERHISTORY, null, cv);
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
	
	public long insertHistoryEntm(long hId, long eId){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_HISTORYENTM_HID, hId);
		cv.put(COLUMN_HISTORYENTM_EID, eId);
		return getWritableDatabase().insert(TABLE_HISTORYENTM, null, cv);
	}
	
	public List<EntertainmentModel> getEntm(){
		List<EntertainmentModel> entmList = new ArrayList<EntertainmentModel>();
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from " + TABLE_ENTERTAINMENT, null);

		// loop through all query results
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			EntertainmentModel entm = new EntertainmentModel();
			
			entm.setEntmName(cursor.getString(1));			
			entm.setEntmTravelTime(cursor.getString(3));
			entm.setEntmStartTime(cursor.getString(4));
			entm.setEntmDuration(cursor.getString(5));
			
			// add location
			long locid = cursor.getLong(2);
			LocationModel loc = getLoc(locid);			
			entm.setEntmLoc(loc);

			//System.out.println(entm.toString());
			entmList.add(entm);
		}

		cursor.close();
		return entmList;
	}
	
	public long insertLocation(LocationModel loc){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_LOCATION_LONGITUDE, loc.getLongitude());
		cv.put(COLUMN_LOCATION_LATITUDE, loc.getLatitude());
		cv.put(COLUMN_LOCATION_ADDRESS, loc.getAddress());
		
		long insertLocId = getWritableDatabase().insert(TABLE_LOCATION,null,cv);
		return insertLocId;
	}
	
	public LocationModel getLoc(long locid){
		LocationModel loc = new LocationModel();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("Select * from " + TABLE_LOCATION + " where lid=? ", new String[]{Long.toString(locid)});
		
		
		cursor.moveToFirst();
		loc.setLongitude(cursor.getDouble(1));
		loc.setLatitude(cursor.getDouble(2));
		loc.setAddress(cursor.getString(3));
		
		cursor.close();
		return loc;
	}
	
}

