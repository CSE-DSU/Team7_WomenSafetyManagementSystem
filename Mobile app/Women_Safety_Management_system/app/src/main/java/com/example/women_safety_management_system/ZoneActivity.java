package com.example.women_safety_management_system;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ZoneActivity extends Activity 
{
	public static String thre = "";
	public static String lati = "";
	public static String longii = "";
	
	private static LatLng fromPosition = null;
	private static LatLng toPosition = null;
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	private GoogleMap map;

	Location mLocation;
	String	userid="";
	EditText editText;
	EditText editText2;
	Button button,memberselection;
	double lat=0.0;
	double lon=0.0;
	
	// GPSTracker class
	GPSTracker gps;
		
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectlocation);
		SharedPreferences prfs = getSharedPreferences("any_prefname", Context.MODE_PRIVATE);
		userid = prfs.getString("username", "");
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		gps = new GPSTracker(this);

		editText = (EditText) findViewById(R.id.addressET);
		button = (Button) findViewById(R.id.addressButton);
		memberselection = (Button) findViewById(R.id.back);
		editText2 = (EditText) findViewById(R.id.thresholde);
		button.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v) 
			{
				
				String address = editText.getText().toString();
				Toast.makeText(getApplicationContext(), "toggletext.6", Toast.LENGTH_LONG).show();
				ArrayList<Double> list = getLatLongFromAddress(address);
				
				 lat = list.get(0);
				 lon = list.get(1);

				 System.out.println("Lat :"+lat+" Longi :"+lon);
				 Toast.makeText(getApplicationContext(), "toggletext.6"+lat+" "+lon, Toast.LENGTH_LONG).show();
				 
			}
		});
		
		memberselection.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
			
			String threshold=	editText2.getText().toString() ;
			
	        double latitude = gps.getLatitude();
	        double longitude = gps.getLongitude();
	        Toast.makeText(getApplicationContext(), " Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
	      
			
		
		loginDataBaseAdapter.updateEntrygeo(threshold, String.valueOf(latitude),String.valueOf(longitude),userid);
		Toast.makeText(getApplicationContext(),"GEO Coverage Coordinates updated Successfully ", Toast.LENGTH_LONG).show();	
		

        String DATA=loginDataBaseAdapter.getvalues(LoginActivity.uidd);
    	Toast.makeText(getApplicationContext(),"DATA "+DATA , Toast.LENGTH_LONG).show();
	      	
    	String data[]=       DATA.split("~");
	        	
	  String threshold1=      	data[0];
	  String lat=      	data[1];	
	  String longi=      	data[2];
	  
	thre =  threshold1;
	  
	lati=  String.valueOf(latitude); 
	
	longii=  String.valueOf(longitude);; 		
			Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
			}
		});
		
	}
	
	private void addGoogleMap() 
	{
		if (map == null) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		}

	}
	private void addMarkers(double lat,double longi,String place)
	{
		final LatLng Locaton = new LatLng(lat,longi);
		
		if (map != null) 
		{
			
			// a draggable marker with title and snippet
			map.addMarker(new MarkerOptions().position(Locaton).title(place).snippet(lat+","+longi).draggable(true));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(
					Locaton, 13));
		}
	}
	
	
	private ArrayList<Double> getLatLongFromAddress(String address)
    {
		
		ArrayList<Double> arrayList = new ArrayList<Double>();
          double lat= 0.0, lng= 0.0;

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());    
        try 
        {
            List<Address> addresses = geoCoder.getFromLocationName(address , 1);
            if (addresses.size() > 0) 
            {            
                GeoPoint p = new GeoPoint((int) (addresses.get(0).getLatitude() * 1E6), 
                        (int) (addresses.get(0).getLongitude() * 1E6));

                lat=p.getLatitudeE6()/1E6;
                lng=p.getLongitudeE6()/1E6;

                Log.d("Latitude", ""+lat);
            Log.d("Longitude", ""+lng);
            
            arrayList.add(lat);
            arrayList.add(lng);
            }
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
		return arrayList;
    }


}

