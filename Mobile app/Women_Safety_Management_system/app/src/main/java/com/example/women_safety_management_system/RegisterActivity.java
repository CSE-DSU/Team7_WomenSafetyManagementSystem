package com.example.women_safety_management_system;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText editTextUserName, editTextPassword,editTextCell,editTextEmail, editTextConfirmPassword;
	Button btnCreateAccount,bt_login;
	Context context = this;
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter = loginDataBaseAdapter.open();
		
		
		editTextUserName = (EditText)findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
		editTextCell=(EditText) findViewById(R.id.cell);
		editTextEmail=(EditText) findViewById(R.id.email);
		
		
		btnCreateAccount = (Button) findViewById(R.id.buttonCreateAccount);
		bt_login=(Button) findViewById(R.id.button_Login);
		
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent il = new Intent(RegisterActivity.this, LoginActivity.class);
				startActivity(il);
			}
		});
		
		
		
		
		
		
		btnCreateAccount.setOnClickListener(new View.OnClickListener()
		{

			public void onClick(View v) 
			{

				String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();
				String confirmPassword = editTextConfirmPassword.getText().toString();
				String cell = editTextCell.getText().toString();
				String email = editTextEmail.getText().toString();
				
			
				
				if (userName.equals("") || password.equals("")|| confirmPassword.equals("")|| cell.equals("")|| email.equals("")) 
				{

					Toast.makeText(getApplicationContext(), "Please Fill All Details",Toast.LENGTH_LONG).show();
					return;
				}
				
				
				if (!password.equals(confirmPassword))
				{				
						Toast.makeText(getApplicationContext(),"Password does not match", Toast.LENGTH_LONG).show();
						return;
				
				}
				
				if(password.length()<8 )
				{					Toast.makeText(getApplicationContext(), "Please password legth should be contains digit(0-9),special character(@#$^&.!) or char(A-Za-z) ,min 8 character",Toast.LENGTH_LONG).show();
									return;
				}
				if (!isValidEmail(email))
				{
					Toast.makeText(getApplicationContext(), "Invalid Email ID.",Toast.LENGTH_LONG).show();
					return;
				}
								
				else 
				{
					if(isValidPhone(cell))
					{
			           
			           
			            String res=loginDataBaseAdapter.getSinlgeEntry(userName, password);
					 	System.out.println("res"+res);	
					 	Toast.makeText(getApplicationContext(),	"Account Successfully Login... ", Toast.LENGTH_LONG);
					
					 	if(res.equals("SUCCESS"))
					 	{
					 		Toast.makeText(getApplicationContext(), "Already User Exist..", Toast.LENGTH_LONG).show();
					 	}
					 	else{
								loginDataBaseAdapter.insertEntry(userName, password,cell,email);
								Toast.makeText(getApplicationContext(),"Account Successfully Created ", Toast.LENGTH_LONG).show();
								
								Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
								startActivity(i);
								finish();
					 	}
					}
					else{
			            Toast.makeText(getApplicationContext(), "Pls Enter 10 digit Phone Number...", Toast.LENGTH_LONG).show();
			        }

				}
			}
		});
	}
	
	
	
	// validating email id
		private boolean isValidEmail(String email) {
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}
	
		// validating Phone
		public static boolean isValidPhone(String phone)
		    {
		        String expression = "[0-9]{10}$";
		        CharSequence inputString = phone;
		        Pattern pattern = Pattern.compile(expression);
		        Matcher matcher = pattern.matcher(inputString);
		        if (matcher.matches())
		        {
		            return true;
		        }
		        else{
		            return false;
		        }
		    }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		loginDataBaseAdapter.close();
	}
}