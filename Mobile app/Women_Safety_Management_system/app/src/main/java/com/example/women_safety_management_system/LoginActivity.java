package com.example.women_safety_management_system;


import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText editTextUserName, editTextPassword, editTextConfirmPassword;
	Button buttonSignIn,buttonforgot;
	Context context = this;
	LoginDataBaseAdapter loginDataBaseAdapter;
	SessionManager session;
	String userName,password;
public static String uidd="";	
	
	static int loginstatus=0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		
		editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
		editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);		
		buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
		
		
		session = new SessionManager(getApplicationContext());
		
		
		
		
		buttonSignIn.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View v)
			{

				 userName = editTextUserName.getText().toString();
				 password = editTextPassword.getText().toString();
				
				if (userName.equals("") || password.equals(""))
				{

					Toast.makeText(getApplicationContext(), "Field required filed !!!",Toast.LENGTH_LONG).show();							
					return;
				}
			 else {

					
				 	String res=loginDataBaseAdapter.getSinlgeEntry(userName, password);
				 	System.out.println("res"+res);	
				 	
						
				 	if(res.equals("SUCCESS"))
				 	{
				 		Toast.makeText(getApplicationContext(),	"Account Successfully Login... ", Toast.LENGTH_LONG);
				 		 session.createLoginSession(userName,password,null,null);
				 		  loginstatus=1;
				 		 uidd=userName;
				 		   SharedPreferences pref = getApplicationContext().getSharedPreferences("any_prefname",MODE_PRIVATE);
	            		   Editor editor = pref.edit();
	            		   editor.putString("username",userName);
	            		   editor.commit();
	            		   
	            		  
				  
				 		Intent i = new Intent(LoginActivity.this,HomeActivity.class);
				 		startActivity(i);
			  }
			  else
			  {
				  Toast.makeText(getApplicationContext(), "INCORRECT USERID AND PASSWORD!!", 1500).show();
				  Intent i = new Intent(LoginActivity.this,LoginActivity.class);
					startActivity(i);
			  }
					finish();

				}
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		loginDataBaseAdapter.close();
	}
}