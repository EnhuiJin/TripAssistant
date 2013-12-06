package com.example.tripassistant;

import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetails extends FragmentActivity implements LocationListener {

	private GoogleMap mMap;
//	private LatLng latLng = new LatLng(40.69401, -73.98671);;
	private TextView detailAddress;
//	private String address = "6 MetroTech Center, Brooklyn, NY 11201";
	private LatLng latLng;
	private String address;
	private String ename;
	private TripAssistantDatabaseHelper db;
	private EntertainmentModel entm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_detail);
		
		Intent intent = getIntent();
		String value = intent.getStringExtra("VALUE");
		db = new TripAssistantDatabaseHelper(EventDetails.this);
		entm = new EntertainmentModel();
		entm = db.getEntmByName(value);
		address = entm.getEntmLoc().getAddress();
		latLng = new LatLng(entm.getEntmLoc().getLatitude(), entm.getEntmLoc().getLongitude());
		ename = entm.getEntmName();
		
		setUpMapIfNeeded();

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());

		if (status != ConnectionResult.SUCCESS) {
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();
		} else {
			setCurrentLocation();
		}

		detailAddress = (TextView) findViewById(R.id.detail_address);
		detailAddress.setText(address);
		detailAddress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mMap.animateCamera(CameraUpdateFactory
						.newLatLngZoom(latLng, 15));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}

	/**
	 * set up Map
	 */
	private void setUpMapIfNeeded() {
		if (mMap != null) {
			return;
		}
		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();

		if (mMap == null) {
			return;
		}
		// Initialize map options
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.addMarker(new MarkerOptions()
				.position(latLng)
				.title(ename)
				.snippet("This is my spot!")
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.getUiSettings().setZoomControlsEnabled(true);
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
	}

	public void setCurrentLocation() {

		mMap.setMyLocationEnabled(true);
		// Getting LocationManager object from System Service LOCATION_SERVICE
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();
		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);
		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {

			onLocationChanged(location);
		}
		locationManager.requestLocationUpdates(provider, 20000, 0, this);
	}

	@Override
	public void onLocationChanged(Location location) {

		TextView tvLocation = (TextView) findViewById(R.id.current_location);
		// Getting latitude of the current location
		double latitude = location.getLatitude();
		// Getting longitude of the current location
		double longitude = location.getLongitude();
		// Creating a LatLng object for the current location
		// Showing the current location in Google Map
		mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,
				longitude)));
		// Zoom in the Google Map
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		// Setting latitude and longitude in the TextView tv_location
		tvLocation.setText("Latitude:" + latitude + ", Longitude:" + longitude);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
}
