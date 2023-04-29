package com.example.women_safety_management_system;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.google.android.gms.location.LocationListener;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class HomeActivity extends Activity  {
	private ToggleButton toggleButton1;  
	LoginDataBaseAdapter loginDataBaseAdapter;
	private String userid;
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;
	double latitude =0;
	double longitude = 0;
	
	private double lati = 0.0;
	private double longi = 0.0; 
	ArrayList<String> PHONE_NUMBER_ArrayList = new ArrayList<String>();
	
	Button btnStop;
	
	SQLiteHelper SQLITEHELPER;
	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter ListAdapter;
	String link;

	private CountDownTimer x;
	
	SharedPreferences pref;
	String userId;
	
	TimerTask mTimerTask;
	  final Handler handler = new Handler();
	  Timer t = new Timer();
		
	  private int nCounter = 0;
	  GPSTracker gps;
	   	 
	  private String lat = "";
		private String lon = ""; 
		public static String thre = "";
		//public static String lati = "";
		public static String longii = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = 
            new StrictMode.ThreadPolicy.Builder().permitAll().build();      
                StrictMode.setThreadPolicy(policy);
         }
		
		
		pref = getApplicationContext().getSharedPreferences("any_prefname",MODE_PRIVATE);
	     userId = pref.getString("username", "");
		
		 gps = new GPSTracker(HomeActivity.this);
			lati = gps.getLatitude();
			longi = gps.getLongitude();
		
		SharedPreferences prfs = getSharedPreferences("any_prefname", Context.MODE_PRIVATE);
		userid = prfs.getString("username", "");
		
		
		SQLITEHELPER = new SQLiteHelper(this);
		
		SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
		SQLITEDATABASE = SQLITEHELPER.getReadableDatabase();
		Toast.makeText(this, "Inside", Toast.LENGTH_LONG).show();
		
		onLocationChanged();
		
Button loc=		(Button) findViewById(R.id.location);
Button zone=	(Button) 		findViewById(R.id.zone);
		
Button auth=	(Button) 		findViewById(R.id.authorize);
		
Button wea=		(Button) 	findViewById(R.id.weather);
		
Button log=	(Button) 		findViewById(R.id.logout);
		
Button pass=	(Button) 		findViewById(R.id.pass);

Button cengine=	(Button) 		findViewById(R.id.cengine);

Button cpolice=	(Button) 		findViewById(R.id.cpolice);

Button panic=	(Button) 		findViewById(R.id.panic);

btnStop = (Button)findViewById(R.id.btnStop);

mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
/*mSensorListener = new ShakeEventListener();*/

toggleButton1=(ToggleButton)findViewById(R.id.toggleButton1);  

toggleButton1.setOnClickListener(new OnClickListener(){  
	
    @Override  
    public void onClick(View view) {
    	String toggletext=  toggleButton1.getText().toString();
		 /*try{*/
    	if(toggletext!=null)
    	Toast.makeText(getApplicationContext(), "toggletextdddqa."+toggletext, Toast.LENGTH_LONG).show();
		  
		  if(toggletext.equals("off"))
		  {
			Toast.makeText(getApplicationContext(), "toggletext."+toggletext, Toast.LENGTH_LONG).show();
			//x.cancel();
			Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
			startActivity(intent); 
		  }
		  else
		  {
			  ShowSQLiteDBdata();
			  Toast.makeText(getApplicationContext(), "on", Toast.LENGTH_LONG).show();
		  }
    }  
      
});  


btnStop.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		t.cancel();
		mTimerTask.cancel();
		btnStop.setVisibility(View.INVISIBLE);
	}
});

cengine.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		new HomeActivity.ExecuteTaskNearHospital().execute( String.valueOf(lati), String.valueOf(longi));
	}
});

cpolice.setOnClickListener(new OnClickListener() {
	
	
	@Override
	public void onClick(View v) {
		new HomeActivity.ExecuteTaskNearPolice().execute( String.valueOf(lati), String.valueOf(longi));
	}
});


auth.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		 Intent intent_author=new Intent(getApplicationContext(),AuthorizeActivity.class);
		 startActivity(intent_author);
	}
});

wea.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent_temp=new Intent(getApplicationContext(),WeatherReportActivity.class);
		startActivity(intent_temp);
	}
});;

loc.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent_loc=new Intent(getApplicationContext(), LocationActivity.class);
		startActivity(intent_loc);
		
	}
});

pass.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent_cp=new Intent(getApplicationContext(),ChangePass.class);
		startActivity(intent_cp);
		
	}
});

log.setOnClickListener(new OnClickListener() {
	
	
	@Override
	public void onClick(View v) {
		Intent intent_logout=new Intent(getApplicationContext(),LoginActivity.class);
			startActivity(intent_logout);
			
		
	}
});

	}
	
	
	class ExecuteTask extends AsyncTask<String, Integer, String>
    {

        @Override
        protected void onPreExecute() {
         
        }

        @Override
        protected String doInBackground(String... params) {

            String   res=PostData(params);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {

    		Intent callIntent = new Intent(Intent.ACTION_CALL);
    		callIntent.setData(Uri.parse("tel:"+result));
    		startActivity(callIntent);	
    		
        }
    }
	class ExecuteTask1 extends AsyncTask<String, Integer, String>
    {

        @Override
        protected void onPreExecute() {
         
        }

        @Override
        protected String doInBackground(String... params) {

            String   res=PostData1(params);

            return res;
        }

        @Override
        protected void onPostExecute(String result) {

    		Intent callIntent = new Intent(Intent.ACTION_CALL);
    		callIntent.setData(Uri.parse("tel:"+result));
    	
    		startActivity(callIntent);	
    		
        }
    }
	public String PostData(String[] valuse) {
        String s="";
        try
        {
        	System.out.println("Inside PostData!!!!!");
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(Global.URL+"GetNearHospitals");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("latt", valuse[0]));
            list.add(new BasicNameValuePair("langg", valuse[1]));
         
           
        
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponse(httpResponse);

        }
        catch(Exception exception)  {}
        return s;


    }
	public String PostData1(String[] valuse) {
        String s="";
        try
        {
        	System.out.println("Inside PostData!!!!!");
            HttpClient httpClient=new DefaultHttpClient();

            List<NameValuePair> list=new ArrayList<NameValuePair>();
           Toast.makeText(getApplicationContext(), ";;;;;;;;;;;;;", Toast.LENGTH_LONG).show();
            list.add(new BasicNameValuePair("latt", String.valueOf(lati) ));
            list.add(new BasicNameValuePair("langg", String.valueOf(longi) ));
            HttpPost httpPost=new HttpPost(Global.URL+"GetNearPoliceStation");

        
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponse(httpResponse);
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
    
    
    
    public static List<String> splitInChunks(String id, int chunkSize) {
    	String SMSPRETAG="";
    	  ArrayList<String> result = new ArrayList<String>();
    	  int length = id.length();
    	  for (int i = 0, j = 1; i < length; j++, i += chunkSize) {
    	     result.add(SMSPRETAG + j + "," + id.substring(i, Math.min(length, i + chunkSize)));
    	  }
    	  return result;
    	}
    

    
	private void ShowSQLiteDBdata() {

		SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

		cursor = SQLITEDATABASE.rawQuery("SELECT * FROM demoTable where username = '"+userId+"'", null);

		
		PHONE_NUMBER_ArrayList.clear();
		
		if (cursor.moveToFirst()) {
			do {
				
				PHONE_NUMBER_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper.KEY_PhoneNumber)));

				

			} while (cursor.moveToNext());
		}

		

		cursor.close();
	}
	
	
	
	//Police
	class ExecuteTaskNearPolice extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {
     
        }

        @Override
        protected String doInBackground(String... params) {

            String res=PostDatap(params);
            return res;
        }

        @Override
        protected void onPostExecute(String result) 
        {
        	
          	
           	
           try{
		
    	String testsize= "SMS From "+userid+" might met with an accident Tap To view Map Please click the below link http://www.google.com/maps/place/"+lati+","+longi;
        
    	
    	SmsManager smsManager = SmsManager.getDefault();
           	smsManager.sendTextMessage(result.trim(), null, testsize, null, null);
	   
       	
        }catch (Exception e) {
    		e.printStackTrace();
		}
        
        }
    }

    public String PostDatap(String[] valuse) {
        String s="";
        try
        {
        	
            System.out.println("Inside PostData!!!!!");
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(Global.URL+"GetNearPoliceStation");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("lat", valuse[0]));
            list.add(new BasicNameValuePair("lang", valuse[1]));


            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponse(httpResponse);

        }
        catch(Exception exception)  {}
        return s;


    }

    public String readResponsep(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;
    }
	
	
	
    
    
    //Hospital
    class ExecuteTaskNearHospital extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            String res=PostDatah(params);
            return res;
        }

        @Override
        protected void onPostExecute(String result) 
        {
        	try
        	{
        	String testsize= "SMS From "+userid+" might met with an accident, Tap To view Map, Please click the below link http://www.google.com/maps/place/"+lati+","+longi;

        	SmsManager smsManager = SmsManager.getDefault();
	        smsManager.sendTextMessage(result.trim(), null, testsize, null, null);
        	}catch (Exception e) {
        		e.printStackTrace();
			}
        }
    }

    public String PostDatah(String[] valuse) {
        String s="";
        try
        {
            System.out.println("Inside PostData!!!!!");
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(Global.URL+"GetNearHospitals");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("latt", valuse[0]));
            list.add(new BasicNameValuePair("langg", valuse[1]));


            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponseh(httpResponse);
        }
        catch(Exception exception)  {}
        return s;


    }

    public String readResponseh(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;
    }
	
	
    
    
    
  //links
    class ExecuteTaskSendLinks extends AsyncTask<String, Integer, String>
    {

        @Override
        protected String doInBackground(String... params) {
            String res=PostDatahh(params);
            return res;
        }

        @Override
        protected void onPostExecute(String result) 
        {
        	String res = result.trim();
        	if(res.equalsIgnoreCase("true"))
        	{
        		Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_LONG).show();
        	}
        	else {
        		Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
			}
        }
    }

    public String PostDatahh(String[] valuse) {
        String s="";
        try
        {
            System.out.println("Inside PostData!!!!!");
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost(Global.URL+"UserLink");

            List<NameValuePair> list=new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("userid", userid));
            list.add(new BasicNameValuePair("links", link));


            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse httpResponse=  httpClient.execute(httpPost);

            HttpEntity httpEntity=httpResponse.getEntity();
            s= readResponsehh(httpResponse);
        }
        catch(Exception exception)  {}
        return s;


    }

    public String readResponsehh(HttpResponse res) {
        InputStream is = null;
        String return_text = "";
        try {
            is = res.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return_text = sb.toString();
        } catch (Exception e) {

        }
        return return_text;
    }
	
    Runnable rn;
	public void onLocationChanged() {
		  rn = new Runnable() {
			
			@Override
			public void run() {
		 Toast.makeText(getApplicationContext(),"Data from location " + SelectLocation.thre + "\nLong: " + SelectLocation.longii+","+SelectLocation.lati, Toast.LENGTH_LONG).show();	
         
		if(!ZoneActivity.thre.isEmpty()){
		 final double thresholdd = Double.parseDouble(ZoneActivity.thre);
			final double lati = Double.parseDouble(ZoneActivity.lati);
			final double longit = Double.parseDouble(ZoneActivity.longii);
			
					GPSTracker gpsTracker = new GPSTracker(HomeActivity.this);
					double dist=Distance.distanced(gpsTracker.getLatitude(),gpsTracker.getLongitude(),lati, longit, 'K');
					if(dist>=thresholdd){
			        	Toast.makeText(getApplicationContext(),"Women has crossed the zone,"+ "The Location is - \nLat: " + gps.getLatitude() + "\nLong: " + gps.getLongitude(), Toast.LENGTH_LONG).show();
			        	sendSMS();
					}
					
		}
		handler.postDelayed(this, 10000);
				}
			};
			handler.postDelayed(rn, 10000);
			
	}
	
	void sendSMS(){
		android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
		try{
    		for(int i=0;i<PHONE_NUMBER_ArrayList.size();i++)
       	 {
   	        Log.d("Name: ", PHONE_NUMBER_ArrayList.get(i));
   	        
   	        String    phoneNo=    PHONE_NUMBER_ArrayList.get(i);
   	        String sms_msg= "Women has crossed the zone, "+"http://www.google.com/maps/place/"+gps.getLatitude()+","+gps.getLongitude()+"";
               try{
                smsManager.sendTextMessage(phoneNo, null, sms_msg, null, null);
                 handler.removeCallbacks(rn);
            }catch (Exception e) {
                e.printStackTrace();
                    System.out.println("error :");
                }
   	        }
    		
    	}catch (Exception e) {
        		e.printStackTrace();
				System.out.println("error :");
			}
	 
	}
}
