package com.example.women_safety_management_system;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.women_safety_management_system.SendSMSNearPoliceStations.ExecuteTask;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;



import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class package com.example.women_safety_management_system;

		import java.util.HashMap;

		import android.content.Context;
		import android.content.SharedPreferences;
		import android.content.SharedPreferences.Editor;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;

	// Editor for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREF_NAME = "AndroidLogin";

	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";

	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "name";
	public static final String KEY_DATE = "date";
	public static final String KEY_AMOUNT= "amount";
	public static final String KEY_PWD = "pwd";

	public static final String Ques1 = "answer1";
	public static final String Ques2 = "answer2";
	public static final String Ques3 = "answer3";
	public static final String Ques4 = "answer4";
	public static final String Ques5 = "answer5";
	public static final String Ques6 = "answer6";
	public static final String Ques7 = "answer7";
	public static final String Ques8 = "answer8";
	public static final String Ques9 = "answer9";
	public static final String Ques10 = "answer10";


	// Email address (make variable public to access from outside)
	public static final String KEY_EMAIL = "pwd";

	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}


	/**
	 * Create login session
	 * */
	public void createLoginSession(String name,String pwd,String date,String amount){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);

		// Storing name in pref
		editor.putString(KEY_NAME, name);

		// Storing email in pref
		//editor.putString(KEY_EMAIL, email);

		editor.putString(KEY_PWD, pwd);
		editor.putString(KEY_DATE, date);
		editor.putString(KEY_AMOUNT, amount);

		// commit changes
		editor.commit();
	}

	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		user.put(KEY_PWD, pref.getString(KEY_PWD, null));


		// return user
		return user;
	}

	public void logoutUser()
	{
		// Clearing all data from Shared Preferences
		editor.clear();
		editor.commit();
	}

	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}
} extends Activity {
private static final int REQUEST_CODE = 1234;
Button Start;
TextView Speech;
Dialog match_text_dialog;
ListView textlist;
ArrayList<String> matches_text;
String response = "";
double latitude =0;
	double longitude = 0;

	 

	SharedPreferences pref;
	String userId;
	
	SQLiteHelper SQLITEHELPER;
	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter ListAdapter;
	Button button;

	ArrayList<String> PHONE_NUMBER_ArrayList = new ArrayList<String>();
	String   userid="";
	 @Override
		protected void onResume() {

			ShowSQLiteDBdata();

			super.onResume();
		}
	    
	
String s=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.panicmsg);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = 
            new StrictMode.ThreadPolicy.Builder().permitAll().build();      
                StrictMode.setThreadPolicy(policy);
         }
       
        pref = getApplicationContext().getSharedPreferences("any_prefname",MODE_PRIVATE);
	     userId = pref.getString("username", "");
        
        GPSTracker gps = new GPSTracker(PanicButton.this);
 		latitude = gps.getLatitude();
 		longitude = gps.getLongitude();
        
    	SharedPreferences prfs = getSharedPreferences("any_prefname", Context.MODE_PRIVATE);
	   userid = prfs.getString("username", "");
	   
        SQLITEHELPER = new SQLiteHelper(this);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				 new ExecuteTask().execute( String.valueOf(latitude), String.valueOf(longitude));
		    	 
		    	 
			    	Toast.makeText(getApplicationContext(), "PHONE_NUMBER_ArrayList"+PHONE_NUMBER_ArrayList, Toast.LENGTH_LONG).show(); 
			    	
			    	try{
			    	
			    	 
			    	 for(int i=0;i<PHONE_NUMBER_ArrayList.size();i++)
			    	 {
				        Log.d("Name: ", PHONE_NUMBER_ArrayList.get(i));
				        
				        String    phoneNo=    PHONE_NUMBER_ArrayList.get(i);
						
				        String testsize="SMS From " +userid+" is Asking Help To view Map Please click the below link http://www.google.com/maps/place/"+latitude+","+longitude;
					    
				        try{
					        		
					        		 ArrayList<String> parts = (ArrayList<String>) splitInChunks(testsize, 150);
					        		   for (String str2 : parts)
					        		   {
					        		       SmsManager smsManager = SmsManager.getDefault();
					          	           smsManager.sendTextMessage(phoneNo, null, str2, null, null);
					        		  }
					        		
					        	}catch (Exception e) {
				 	        		e.printStackTrace();
									System.out.println("error :");
								}

		PanicButton.this.finish();
					        	/*Intent sendSMStoPolice = new Intent(this,HomeActivity.class);
					    		startActivity(sendSMStoPolice);*/
			    	 
			    	 }   
		     }catch (Exception e) {
				// TODO: handle exception
		    	 e.printStackTrace();
			}
			}
		});
   	 
//        	if(isConnected())
//        	{
//        		//Toast.makeText(getApplicationContext(), "isConnected ", 500).show();
//    			
//        	 Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//         	 intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//         	 RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//         	 startActivityForResult(intent, REQUEST_CODE);
//        	}
//        	else{
//        		Toast.makeText(getApplicationContext(), "Plese Connect to Internet", Toast.LENGTH_LONG).show();
//        	}
}
    
    
    public boolean isConnected()
    { 
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo net = cm.getActiveNetworkInfo();
    if (net!=null && net.isAvailable() && net.isConnected()) {
        return true;
    } else {
        return false; 
    }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
    	    
    match_text_dialog = new Dialog(PanicButton.this);
    
    matches_text = data
		     .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
     
     System.out.println("==================");
     System.out.println("list :"+matches_text);
     System.out.println("==================");
    
     if(matches_text.contains("help")||matches_text.contains("Help")||matches_text.contains("HELP")||matches_text.contains("please help me")||matches_text.contains("help me")||matches_text.contains("help me please"))
     {
    	//Toast.makeText(getApplicationContext(), "its having help", Toast.LENGTH_LONG).show(); 
    	
    	
    	 String Test= PalaniTest.getAddressFromLocation(latitude, longitude,PanicButton.this);
    	
    	 new PanicButton.ExecuteTask().execute( String.valueOf(latitude), String.valueOf(longitude));
    	 
    	 
    	Toast.makeText(getApplicationContext(), "PHONE_NUMBER_ArrayList"+PHONE_NUMBER_ArrayList, Toast.LENGTH_LONG).show(); 
    	
    	try{
    	
    	 
    	 for(int i=0;i<PHONE_NUMBER_ArrayList.size();i++)
    	 {
	        Log.d("Name: ", PHONE_NUMBER_ArrayList.get(i));
	        
	        String    phoneNo=    PHONE_NUMBER_ArrayList.get(i);
			
	        String testsize="SMS From " +userid+" is Asking Help To view Map Please click the below link http://www.google.com/maps/place/"+latitude+","+longitude;
		    
	        try{
		        		
		        		 ArrayList<String> parts = (ArrayList<String>) splitInChunks(testsize, 150);
		        		   for (String str2 : parts)
		        		   {
		        		       SmsManager smsManager = SmsManager.getDefault();
		          	           smsManager.sendTextMessage(phoneNo, null, str2, null, null);
		        		  }
		        		
		        	}catch (Exception e) {
	 	        		e.printStackTrace();
						System.out.println("error :");
					}

		PanicButton.this.finish();
		        	/*Intent sendSMStoPolice = new Intent(this,HomeActivity.class);
		    		startActivity(sendSMStoPolice);*/
	        }
     
     }catch (Exception e) {
		// TODO: handle exception
    	 e.printStackTrace();
	}
     
     }
     else
     {
    	 Toast.makeText(getApplicationContext(), "Please Speak Again", Toast.LENGTH_LONG).show(); 
    	 
    	 Intent passwordIntent = new Intent(this,PanicButton.class);
 		startActivity(passwordIntent);
     }
     
     }
     super.onActivityResult(requestCode, resultCode, data);
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
	
		  
		  class ExecuteTask extends AsyncTask<String, Integer, String>
		    {

		        @Override
		        protected void onPreExecute() {

		        }

		        @Override
		        protected String doInBackground(String... params) {

		            String res=PostData(params);
		            return res;
		        }

		        @Override
		        protected void onPostExecute(String result) 
		        {
		        	String Test = PalaniTest.getAddressFromLocation(latitude, longitude, PanicButton.this);
		        	String testsize= "SMS From , "+userid+"is Asking Help To view Map Please click the below link http://www.google.com/maps/place/"+latitude+","+longitude+" "+Test;
			        
		        	String phoneNo=result.trim();
		        	try{
		        	SmsManager smsManager = SmsManager.getDefault();
	   	           	smsManager.sendTextMessage(phoneNo, null, testsize, null, null);
		        	}catch (Exception e) {
						// TODO: handle exception
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

}