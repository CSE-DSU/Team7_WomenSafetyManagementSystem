package com.example.women_safety_management_system;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AuthorizeActivity extends Activity {

	
	EditText GetName,GetPhoneNumber,GetSubject ;
	Button Submit, ShowValues;
	SQLiteDatabase SQLITEDATABASE;
	String Name, PhoneNumber, Subject, userName ;
	Boolean CheckEditTextEmpty, CheckExistanceorNot;
	String SQLiteQuery ;Cursor cursor;
	SQLiteHelper SQLITEHELPER;
	SharedPreferences pref;
	String userId;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        
        pref = getApplicationContext().getSharedPreferences("any_prefname",MODE_PRIVATE);
        userId = pref.getString("username", "");
       
        SQLITEHELPER = new SQLiteHelper(this);
        
        GetName = (EditText)findViewById(R.id.editText1);
        
        GetPhoneNumber = (EditText)findViewById(R.id.editText2);
        
        GetSubject = (EditText)findViewById(R.id.editText3);
        
        Submit = (Button)findViewById(R.id.button1);
        
        ShowValues = (Button)findViewById(R.id.button2);
        
        Submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				DBCreate();
				
				SubmitData2SQLiteDB();
				
			}
		});
        
        ShowValues.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(AuthorizeActivity.this, ListViewActivity.class);
				startActivity(intent);
				
			}
		});
    }
    
    public void DBCreate(){
    	
    	SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        
    	SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR, subject VARCHAR, username VARCHAR);");
    }
    
    public void SubmitData2SQLiteDB()
    {
    	
        Name = GetName.getText().toString();
        
        PhoneNumber = GetPhoneNumber.getText().toString();
        
        Subject = GetSubject.getText().toString();
        
        userName = userId.trim();
        
        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Subject);
        
        
        if(CheckEditTextEmpty == true)
        {
        	String check=CheckExistanceorNot(PhoneNumber);
        	
        	if(check.equals("NOT EXIST"))
            {
		        SQLiteQuery = "INSERT INTO demoTable (name,phone_number,subject,username) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Subject+"', '"+userName+"');";
		        
		        SQLITEDATABASE.execSQL(SQLiteQuery);
		        
		        Toast.makeText(AuthorizeActivity.this,"Data Submit Successfully", Toast.LENGTH_LONG).show(); 
		        
		        ClearEditTextAfterDoneTask();
            }
        	else
        	{
        		 Toast.makeText(AuthorizeActivity.this," Mobile Number already added..!!", Toast.LENGTH_LONG).show(); 
    		     
        	}
        }
       else {
		
    	   Toast.makeText(AuthorizeActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
	}
    }
    
    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String subject ){
    	
    	 if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(Subject)){
         	
         	CheckEditTextEmpty = false ;
         	
         }
    	else {
			CheckEditTextEmpty = true ;
		}
    }
    
    public String CheckExistanceorNot(String PhoneNumber )
    {
    	// SQLiteQuery = "SELECT * from  demoTable where phone_number='"+PhoneNumber+"');";
	        
    	 SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

 		   cursor = SQLITEDATABASE.rawQuery("SELECT * from  demoTable where phone_number='"+PhoneNumber+"'", null);
 		  if (cursor.getCount() < 1) 
			{
				cursor.close();
				return "NOT EXIST";
			}
			return "SUCCESS";	        
      
   }
    
    public void ClearEditTextAfterDoneTask(){
		
    	GetName.getText().clear();
        GetPhoneNumber.getText().clear();
        GetSubject.getText().clear();
		
	}
}
