package com.example.women_safety_management_system;

import java.util.HashMap;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePass extends Activity {


	String current_pass = "", new_pass = "", loginu = "", confirmpa = "";
	EditText c_pass, new_password, con_pass;
	String output1 = "";
	String result = "";
	Button change;
	String pass = "";
	String response="";
	SessionManager session;
	String email = "";	
	boolean b =false;
	LoginDataBaseAdapter loginDataBaseAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_pass);
      loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		
		session=new SessionManager(getApplicationContext());
		
		
		HashMap<String, String> map=session.getUserDetails();
		
		// Email id and password
					email = map.get(SessionManager.KEY_NAME);
					pass = map.get(SessionManager.KEY_PWD);
					c_pass = (EditText) findViewById(R.id.cntpass);
					new_password = (EditText) findViewById(R.id.npass);
					con_pass = (EditText) findViewById(R.id.conpass);
					

					change = (Button) findViewById(R.id.btnchge);
		
					Toast.makeText(getApplicationContext(), "~"+email+"~"+pass, 19900).show();
					change.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							current_pass = c_pass.getText().toString();
							new_pass = new_password.getText().toString();
							confirmpa = con_pass.getText().toString();
							
							if(current_pass.length()<8 )
							{
								Toast.makeText(getApplicationContext(), "Please password legth should be contains digit(0-9),special character(@#$^&.!) or char(A-Za-z) ,min 8 character",Toast.LENGTH_LONG).show();
								return;
							}
							
							
							if(new_pass.length()<8 )
							{
								Toast.makeText(getApplicationContext(), "Please password legth should be contains digit(0-9),special character(@#$^&.!) or char(A-Za-z) ,min 8 character",Toast.LENGTH_LONG).show();
								return;
							}
							
							
							if(confirmpa.length()<8 )
							{
								Toast.makeText(getApplicationContext(), "Please password legth should be contains digit(0-9),special character(@#$^&.!) or char(A-Za-z) ,min 8 character",Toast.LENGTH_LONG).show();
								return;
							}
							
							
							if(Utility.isNotNull(current_pass) && Utility.isNotNull(new_pass) && Utility.isNotNull(confirmpa))
							{
								Log.d("Start", "onClick process 2");
								Log.d("Start", "onClick process 1");
								
								Log.d("User Details", pass+"."+email);
								
								boolean f = (current_pass.equals(pass));
								
								System.out.println("Status :"+(current_pass.equals(pass)));
								
								if(!f)
								{
									Toast.makeText(getApplicationContext(), "Current password is wrong,please try again!", Toast.LENGTH_LONG).show();
									
								}
								else if(!(new_pass.trim().equals(confirmpa.trim())))
								{
									Toast.makeText(getApplicationContext(), "New and Confirm password is mismatch!", Toast.LENGTH_LONG).show();
									
								}
								else
								{
									//Toast.makeText(getApplicationContext(),email+"~now is in else Condition~"+confirmpa, Toast.LENGTH_LONG).show();
									Toast.makeText(getApplicationContext(),	"Change password Updated Successfully ... ", Toast.LENGTH_LONG);
									
									Log.d("Start", "onClick process 3");
									
									
										
									loginDataBaseAdapter.updateEntry(email, confirmpa);
								 		
								 		
									Intent i = new Intent(getApplicationContext(),HomeActivity.class);
							 		startActivity(i);
									
									
									
								}
								
							}
							else
							{
								
								Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
								
							}
							
							
							
						}
					});
		
		
	}

	
}
