package com.example.women_safety_management_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter {
	static final String DATABASE_NAME = "db_csecurity.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	static final String DATABASE_CREATE = "CREATE TABLE " + "m_roll" + "( "	
											+ "ROLL_ID" + " integer PRIMARY KEY AUTOINCREMENT,"
											+ "USERNAME  text,PASSWORD text,CELL text,EMAIL text,THRESHOLD text,LATITUDE text,LONGITUDE text); ";

	public SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;

	public LoginDataBaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null,DATABASE_VERSION);
	}

	public LoginDataBaseAdapter open() throws SQLException
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		db.close();
	}

	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}

	public void insertEntry(String userName, String password,String cell,String email)
	{
		ContentValues newValues = new ContentValues();
		newValues.put("USERNAME", userName);
		newValues.put("PASSWORD", password);
		newValues.put("CELL", cell);
		newValues.put("EMAIL", email);
		db.insert("m_roll", null, newValues);
		

	}

	public int deleteRuleEntry(int role_id) 
	{

		String where = "ROLL_ID=?";
		int numberOFEntriesDeleted = db.delete("m_rule", where,new String[] { String.valueOf(role_id) });
		return numberOFEntriesDeleted;
		
	}
	
	public String getSinlgeEntry(String userName,String pwd)
	{
		Cursor cursor = db.query("m_roll", null, " USERNAME=?and PASSWORD=?",new String[] { userName,pwd }, null, null, null);
		if (cursor.getCount() < 1) 
		{
			cursor.close();
			return "NOT EXIST";
		}
				return "SUCCESS";
	}
	
	public String getSinlgeEntryCheck(String userName)
	{
		Cursor cursor = db.query("m_roll", null, " USERNAME=?",new String[] { userName }, null, null, null);
		if (cursor.getCount() < 1) 
		{
			cursor.close();
			return "NOT EXIST";
		}
		return "SUCCESS";
	}
	
	public String getSinlgeEntry_RULE(String userName,String pwd)
	{
		Cursor cursor = db.query("m_roll", null, " USERNAME=?and PASSWORD=?",new String[] { userName,pwd }, null, null, null);
		if (cursor.getCount() < 1) 
		{
			cursor.close();
			return "NOT EXIST";
		}
				return "SUCCESS";
	}
	
	
	
	public int getUserId(String userName) {
	   
	    String query = "SELECT ROLL_ID FROM m_roll WHERE USERNAME = ?";
	    String[] parameters = new String[] { userName };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	        return cursor.getInt(0);
	    else
	        return -1;
	}
	
	public String getUserMobile(String userName) {
		   
	    String query = "SELECT CELL FROM m_roll WHERE USERNAME = ?";
	    String[] parameters = new String[] { userName };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	        return cursor.getString(0);
	    else
	        return null;
	}
	
	public String getUserPassword(String userName) {
		   
	    String query = "SELECT PASSWORD FROM m_roll WHERE USERNAME = ?";
	    String[] parameters = new String[] { userName };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	        return cursor.getString(0);
	    else
	        return null;
	}
	
	
	public int insertRuleTable(String userName) {
		   
	    String query = "SELECT ROLL_ID FROM m_roll WHERE USERNAME = ?";
	    String[] parameters = new String[] { userName };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	        return cursor.getInt(0);
	    else
	        return -1;
	}
	

	public String getViewRuleTable(int rollid) {
		   
	    String query = "SELECT * FROM m_rule WHERE ROLL_ID = ?";
	    String[] parameters = new String[] { String.valueOf(rollid) };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	    {
	    	System.out.println(""+cursor.getInt(0)+"|"+cursor.getInt(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3));
	        System.out.println(""+cursor.getString(4)+"|"+cursor.getString(5)+"|"+cursor.getString(6)+"|"+cursor.getString(7)+"|"+cursor.getString(8)+"|"+cursor.getString(9)+"|"+cursor.getString(10));
	       
	        return cursor.getInt(0)+"~"+cursor.getInt(1)+"~"+cursor.getString(2)+"~"+cursor.getString(3)+"~"+cursor.getString(4)+"~"+cursor.getString(5)+"~"+cursor.getString(6)+"~"+cursor.getString(7)+"~"+cursor.getString(8)+"~"+cursor.getString(9)+"~"+cursor.getString(10);
	        
	    }
	    else
	        return "NoRecordFound";
	}
	
	public String getvalues(String id ) {
		   
	    String query = "SELECT THRESHOLD,LATITUDE,LONGITUDE FROM m_roll WHERE USERNAME = ?";
	    String[] parameters = new String[] { String.valueOf(id) };
	    Cursor cursor = db.rawQuery(query, parameters);
	    if (cursor.moveToFirst())
	    {
	       
	        return cursor.getString(0)+"~"+cursor.getString(1)+"~"+cursor.getString(2);
	        
	    }
	    else
	        return "NoRecordFound";
	}
	public void updateEntry(String userName, String password) {
		ContentValues updatedValues = new ContentValues();
		updatedValues.put("USERNAME", userName);
		updatedValues.put("PASSWORD", password);

		String where = "USERNAME = ?";
		db.update("m_roll", updatedValues, where, new String[] { userName });
	}
	
	public boolean updateEntrygeo(String thre, String lat,String longi,String uid) {
		boolean updataStatus = false;
		Cursor c = db.query("m_roll", new String[] { "USERNAME" }, "USERNAME=?", new String[] { uid }, null, null, null);
		if(c!=null)
		{
			ContentValues updatedValues = new ContentValues();
			updatedValues.put("LATITUDE", lat);
			updatedValues.put("LONGITUDE", longi);
			updatedValues.put("THRESHOLD", thre);
			String where = "USERNAME = ?";
			db.update("m_roll", updatedValues, "USERNAME=?", new String[] { uid });
			updataStatus = true;
		}
		else {
			ContentValues cv = new ContentValues();
			cv.put("LATITUDE", lat);
			cv.put("LONGITUDE", longi);
			cv.put("THRESHOLD", thre);
			db.insert("m_roll",null, cv);
			updataStatus = true;
		}
		return updataStatus;
	}

	public void insertRuleTable(int roll_id, String username,String start_time, String end_time, String action,String device_status, String location, String latitude,String longitude,String distance)
	{

		System.out.println("===========insertion ========");
		
		ContentValues newValues = new ContentValues();
		newValues.put("ROLL_ID", roll_id);
		newValues.put("RULE_NAME", username);
		newValues.put("TIME_START", start_time);
		newValues.put("TIME_END", end_time);		
		newValues.put("RULE_DEVICE", device_status);
		newValues.put("RULE_ACTION", action);
		newValues.put("RULE_LOCATION", location);
		newValues.put("RULE_LATITUDE", latitude);
		newValues.put("RULE_LONGITUDE", longitude);
		newValues.put("DISTANCE", distance);
		db.insert("m_rule", null, newValues);
		
		
		
	}
	
}