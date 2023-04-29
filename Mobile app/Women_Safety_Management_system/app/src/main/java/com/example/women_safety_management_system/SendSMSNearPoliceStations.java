package com.example.women_safety_management_system;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.women_safety_management_system.HomeActivity.ExecuteTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;








import android.R.drawable;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.telephony.gsm.*;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class SendSMSNearPoliceStations extends Activity implements OnMarkerClickListener, OnMarkerDragListener 
{
	
	private static LatLng fromPosition = null;
	private static LatLng toPosition = null;

	
	private GoogleMap map;

	Location mLocation;
	
	Button button;
	double lat=0.0;
	double lon=0.0;
	double latitude =0;
 	double longitude = 0;
	
	String title="";
	String tit="";
	 String content="";
	String userid="";
	ProgressDialog prgDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewlocation);
		
		SharedPreferences prfs = getSharedPreferences("any_prefname", Context.MODE_PRIVATE);
		   userid = prfs.getString("username", "");
		
		Intent intent = getIntent();
	      Bundle bd = intent.getExtras();       
	      if(bd != null)
	      {
	    	  content  = (String) bd.get("content");
	         
	      }
		
		
		// Instantiate Progress Dialog object
				prgDialog = new ProgressDialog(this);
				// Set Progress Dialog Text
				prgDialog.setMessage("Please wait...");
				// Set Cancelable as False
				prgDialog.setCancelable(false);
				
				button = (Button) findViewById(R.id.ok);
				
				GPSTracker gps = new GPSTracker(SendSMSNearPoliceStations.this);
				latitude = gps.getLatitude();
			 	longitude = gps.getLongitude();
			 	new ExecuteTask().execute( String.valueOf(latitude),String.valueOf(longitude));
			 	
				
		
				button.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v) 
					{
						Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
						startActivity(intent);
					}
				});
				
	}
	
	@SuppressLint("NewApi")
	private void addGoogleMap() 
	{
		// check if we have got the googleMap already
		if (map == null) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			map.setOnMarkerClickListener(this);
			map.setOnMarkerDragListener(this);
		}

	}
	private void addMarkers(double lat,double longi,String title)
	{
		final LatLng Locaton = new LatLng(lat,longi);
		
		if (map != null) 
		{
			
			// a draggable marker with title and snippet
			map.addMarker(new MarkerOptions().position(Locaton).title(title).snippet(lat+","+longi).icon(BitmapDescriptorFactory.fromResource(R.drawable.gmicon2)).draggable(true));

			//map.icon(BitmapDescriptorFactory.fromResource(R.drawable.gmicon));
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(
					Locaton, 13));

		}
	}
	
	
	
	@Override
	public void onMarkerDrag(Marker arg0) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onMarkerDragEnd(Marker marker) {
		// TODO Auto-generated method stub
		
		toPosition = marker.getPosition();
		Toast.makeText(
				getApplicationContext(),
				"Marker " + marker.getTitle() + " dragged from " + fromPosition
						+ " to " + toPosition, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub
		
		fromPosition = marker.getPosition();
		Log.d(getClass().getSimpleName(), "Drag start at: " + fromPosition);
	}

	@Override
	public boolean onMarkerClick(Marker marker)
	{
		
		Log.i("GoogleMapActivity", "onMarkerClick");
		Toast.makeText(getApplicationContext(),
				"Marker Clicked: " + marker.getTitle(), Toast.LENGTH_LONG)
				.show();
		return false;
	}

	
	public void invokeWS() {
		// Show Progress Dialog
		prgDialog.show();
		// Hide Progress Dialog
						prgDialog.hide();
						try 
						{
							// JSON Object
							
								 lat = latitude;
								 lon = longitude;
								 tit=title;
							
								 addMarkers(lat,lon,tit);
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							Toast.makeText(
									getApplicationContext(),
									"Error Occured [Server's JSON response might be invalid]!",
									Toast.LENGTH_LONG).show();
							e.printStackTrace();

						}
					

					// When the response returned by REST has Http response code
					// other than '200'
					
				
	}
	
	  public static List<String> splitInChunks(String id, int chunkSize) {
	    	String SMSPRETAG="";
	    	  ArrayList<String> result = new ArrayList<String>();
	    	  int length = id.length();
	    	  for (int i = 0, j = 1; i < length; j++, i += chunkSize) {
	    	     result.add(SMSPRETAG + j + "," + id.substring(i, Math.min(length, i + chunkSize)));
	    	  }
	    	  return result;
	    	}
	  
	  class ExecuteTask extends AsyncTask<String, Integer, String>
	    {

	        @Override
	        protected void onPreExecute() {
	         
	        }

	        @Override
	        protected String doInBackground(String... params) {
	        	Toast.makeText(getApplicationContext(), "its came inisde execute task", Toast.LENGTH_LONG).show();
	            String   res=PostData(params);

	            return res;
	        }

	        @Override
	        protected void onPostExecute(String result) {

	
				
            	try 
            	{
            		
            	    
            	 String   res= result.replaceAll("\\s+","");   //removing spaces in between the words      	              	 
            	    //res="true";
            	    if(!res.equals("empty"))
            	    {
            	    	String a[]=res.split("@");
            	    	for(int i=0;i<a.length;i++)
            	    	{
            	    		
            	    		String b[]=a[i].split("~");
            	    		
            	    		latitude =  Double.parseDouble(b[2]);
 						 	longitude = Double.parseDouble(b[3]);
 						 	
 						 	String pno=b[4];
 						 	
 						 	 String Test=PalaniTest.getAddressFromLocation(latitude, longitude,SendSMSNearPoliceStations.this);
 					    	
 						 	
 						 	 String testsize="SMS From , "+userid+" "+content+" http://www.google.com/maps/place/"+latitude+","+longitude+" "+Test;
 					          System.out.println("content size :"+testsize.length());
 					              System.out.println("tst============== :"+Test);
 					              String phoneNo=pno;//9900392855
 						        	try{
 						        		
 						        		 ArrayList<String> parts = (ArrayList<String>) splitInChunks(testsize, 150);
 						        		   for (String str2 : parts) {
 						        		       System.out.println("in map send sms");
 						        		        
 						        		       SmsManager smsManager = SmsManager.getDefault();
 						          	           smsManager.sendTextMessage(phoneNo, null, str2, null, null);
 						        		    
 						        		  }
 						        		
 						        	}catch (Exception e) {
 					 	        		e.printStackTrace();
 										System.out.println("error :");
 									}
 					    	 
 						 	
 						 	title=b[0];
 			 			invokeWS();
 			 			
 						 
 						 addGoogleMap();
 						 
 						 addMarkers(lat,lon,tit);
            	    		
            	    		
            	    		
            	    		
            	    	}
            	    	
            	    	
            	    	
				
            	    }else
            	    {
            	    	
            	    }
				
				
            	}catch (Exception e) {
				
				}
				
	    		
	        }
	    }
		public String PostData(String[] valuse) {
	        String s="";
	        try
	        {
	        	System.out.println("Inside PostData!!!!!");
	            HttpClient httpClient=new DefaultHttpClient();
	            HttpPost httpPost=new HttpPost(Global.URL+"GetNearPoliceStation");
	            //Log.i(TAG, "PostData: " + httpPost);

	            List<NameValuePair> list=new ArrayList<NameValuePair>();
	            list.add(new BasicNameValuePair("lat", valuse[0]));
	            list.add(new BasicNameValuePair("lang", valuse[1]));
	            httpPost.setEntity(new UrlEncodedFormEntity(list));
	            HttpResponse httpResponse=  httpClient.execute(httpPost);

	            HttpEntity httpEntity=httpResponse.getEntity();
	            s= readResponse(httpResponse);
	            //Log.i(TAG, "s: " + s);

	        }
	        catch(Exception exception)  {}
	        return s;


	    }
		
		  public String readResponse(HttpResponse res) {
		        InputStream is=null;
		        String return_text="";
		        try {
		            is=res.getEntity().getContent();
		            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(is));
		            String line="";
		            StringBuffer sb=new StringBuffer();
		            while ((line=bufferedReader.readLine())!=null)
		            {
		                sb.append(line);
		            }
		            return_text=sb.toString();
		        } catch (Exception e)
		        {

		        }
		        return return_text;

		    }
		    
	 
}
