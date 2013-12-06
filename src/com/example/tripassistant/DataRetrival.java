package com.example.tripassistant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * 
 * This class is used to retrive data from Stubhub.com. It also provides methods
 * for extracting useful info from JSON strings, and generating
 * EntertainmentModel objects, which will be stored into the database.
 * 
 * @author Zhisheng
 * 
 */
public class DataRetrival {

	private final String USER_AGENT = "Mozilla/5.0";
	private static final String HOST_NAME = "https://api.stubhub.com/login";
	private static final String USER_NAME = "zhishengzhou1984@hotmail.com";
	private static final String PASSWORD = "168168";
	private static final int numOfEvents = 50; // Specify how many events should
												// be returned

	/**
	 * get events info from stubhub.com
	 * 
	 * @return a Json String
	 */
	public String getEventList() {

		StringBuilder response = new StringBuilder();
		try {
			String url = "http://www.stubhub.com/listingCatalog/select?q=stubhubDocumentType:event%20AND%20description:New%20York*%20&start=0&rows="
					+ numOfEvents
					+ "&wt=json&indent=on&fl=event_id%20description%20event_date%20venue_name%20latitude%20longitude%20eventLocation_facet_str";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Accept-Encoding", "application/json");
			con.setRequestProperty("Authorization",
					"Bearer 24535b40d2d338477df55ad823809b41");

			response = new StringBuilder();
			Scanner in = new Scanner(con.getInputStream());
			while (in.hasNext()) {
				response.append(in.nextLine());
			}

			in.close();
			con.disconnect();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return response.toString();
	}

	/**
	 * extract events from the JSON string
	 * 
	 * @return a list of EntertainmentModel Objects
	 */
	public List<EntertainmentModel> fromJsonToEM() {
		List<EntertainmentModel> result = new ArrayList<EntertainmentModel>();
		EntertainmentModel event;
		LocationModel location = new LocationModel();
		try {
			String inputString = getEventList();
			JSONObject jsonObject = new JSONObject(inputString);
			JSONObject respObject = jsonObject.getJSONObject("response");
			JSONArray jsonArray = respObject.getJSONArray("docs");
			for (int i = 0; i < jsonArray.length(); ++i) {
				event = new EntertainmentModel();
				JSONObject jObject = jsonArray.getJSONObject(i);
				String desp = jObject.getString("description");
				desp = desp.replaceAll("New York New York Tickets", "New York Tickets");
				desp = desp.substring(0, desp.length() - 8);
				event.setEntmName(desp);
				Log.i("DESCRIPTION", desp);
				String eventDate = jObject.getString("event_date");
				eventDate = eventDate.replace("T", " ");
				eventDate = eventDate.replace("Z", "");
				event.setEntmStartTime(eventDate);

				event.setEntmTravelTime("30 minutes");
				event.setEntmDuration("60 minutes");

				double latitude = jObject.getDouble("latitude");
				String vname = jObject.getString("venue_name");
				double longitude = jObject.getDouble("longitude");
				location.setAddress(vname);
				location.setLongitude(longitude);
				location.setLatitude(latitude);

				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(10);
				df.setMinimumFractionDigits(6);
				event.setEntmLoc(location);
				result.add(event);
			}

		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		return result;
	}

	/**
	 * send a post to https://api.stubhub.com/login, and return access_token.
	 * access_token is needed for getting permission to retrieve data from
	 * stubhub.com. access_token will only be validate within 6 months
	 * 
	 * @return access_token
	 * @throws IOException
	 * @throws ProtocolException
	 * @throws MalformedURLException
	 */
	public String sendPostToServer() {

		String access_token = "";
		try {
			String url = HOST_NAME;
			URL obj = new URL(url);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

			// add requesting header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty(
					"Authorization",
					"basic dmd5RTY5Nmc1Nm1lUEVLb2V5V1hiZjlWbmNJYTpWN19tWER5Zkk0R0phU29Ya2FSaWsyTl9pRklh");
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			String urlParameters = "grant_type=password&username=" + USER_NAME
					+ "&password=" + PASSWORD + "&scope=PRODUCTION";

			// Send post request
			con.setDoOutput(true);
			try {
				DataOutputStream wr = new DataOutputStream(
						con.getOutputStream());
				wr.writeBytes(urlParameters);
				wr.flush();
				wr.close();

				Scanner scanner = new Scanner(con.getInputStream());
				StringBuffer response = new StringBuffer();
				while (scanner.hasNext()) {
					response.append(scanner.nextLine());
				}
				scanner.close();

				JSONObject jsonObject = new JSONObject(response.toString());
				access_token = jsonObject.getString("access_token");

			} catch (IOException ioe) {
				System.out.println("Exception of HttpResponse :" + ioe);
			}

		} catch (ProtocolException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} catch (JSONException je) {
		}
		return access_token;
	}
	
}
