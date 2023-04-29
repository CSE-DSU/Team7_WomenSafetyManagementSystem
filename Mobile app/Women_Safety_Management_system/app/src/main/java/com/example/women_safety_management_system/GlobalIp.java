package com.example.women_safety_management_system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GlobalIp extends Activity {

	private EditText etIpAddress;
	private Button button1;
	
	public static String ip_address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_global_ip);
		
		etIpAddress = (EditText)findViewById(R.id.etIpAddress);
		
		button1 = (Button)findViewById(R.id.button1);
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip_address = etIpAddress.getText().toString().trim();
				
				if(ip_address.equals(""))
				{
					Toast.makeText(getApplicationContext(),"Please, Enter Ip Address", Toast.LENGTH_LONG).show();
		        	
				}
				else {
					Intent intent =  new Intent(GlobalIp.this, RegisterActivity.class);
					startActivity(intent);
				}
				
			}
		});
		
	}

	
}
