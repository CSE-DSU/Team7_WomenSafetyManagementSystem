package com.example.women_safety_management_system;

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
}