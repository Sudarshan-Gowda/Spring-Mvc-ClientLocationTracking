package com.star.sud;

/*@Author Sudarshan*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GoogleMapLocation {

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static String readJsonFromUrl(String url) throws IOException {

		String jsonText = "";
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			jsonText = readAll(rd);

		} finally {
			is.close();
		}
		return adres(jsonText);
	}

	public static String adres(String jsonaddresss) {

		String locationAddr = "";
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonaddresss);
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray msg = (JSONArray) jsonObject.get("results");
			JSONObject jobj = (JSONObject) parser.parse(msg.get(1).toString()); // Parsse
			locationAddr = jobj.get("formatted_address").toString();

		} catch (Exception e) {
			locationAddr = "Error In Address" + e;
		}
		return locationAddr;
	}

	public static String getLocationAddress(String lattitude, String longitide) {

		String locationAddress = "";
		try {

			String urlAddr = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + lattitude + "," + longitide
					+ "&key=AIzaSyDp68nuNrLzuErgL5w0Rd_fDWtwxwM_R_o";

			locationAddress = readJsonFromUrl(urlAddr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return locationAddress;
	}

}
