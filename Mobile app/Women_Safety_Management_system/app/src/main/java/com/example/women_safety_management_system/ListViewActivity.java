package com.example.women_safety_management_system;


import java.util.ArrayList;


import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends Activity implements SQLiteListAdapter.OnItemListener {

	SQLiteHelper SQLITEHELPER;
	SQLiteDatabase SQLITEDATABASE;
	Cursor cursor;
	SQLiteListAdapter ListAdapter;

	ArrayList<String> ID_ArrayList = new ArrayList<String>();
	ArrayList<String> NAME_ArrayList = new ArrayList<String>();
	ArrayList<String> PHONE_NUMBER_ArrayList = new ArrayList<String>();
	ArrayList<String> SUBJECT_ArrayList = new ArrayList<String>();
	ListView LISTVIEW;
	
	SharedPreferences pref;
	String userId;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		 pref = getApplicationContext().getSharedPreferences("any_prefname",MODE_PRIVATE);
	     userId = pref.getString("username", "");

		LISTVIEW = (ListView) findViewById(R.id.listView1);

		SQLITEHELPER = new SQLiteHelper(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		ShowSQLiteDBdata();
	}

	private void ShowSQLiteDBdata() {

		SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();

		cursor = SQLITEDATABASE.rawQuery("SELECT * FROM demoTable where username = '"+userId+"' ", null);

		ID_ArrayList.clear();
		NAME_ArrayList.clear();
		PHONE_NUMBER_ArrayList.clear();
		SUBJECT_ArrayList.clear();

		if (cursor.moveToFirst()) {
			do {
				ID_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper.KEY_ID)));

				NAME_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper.KEY_Name)));

				PHONE_NUMBER_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper.KEY_PhoneNumber)));

				SUBJECT_ArrayList.add(cursor.getString(cursor
						.getColumnIndex(SQLiteHelper.KEY_Subject)));

			} while (cursor.moveToNext());
		}

		ListAdapter = new SQLiteListAdapter(ListViewActivity.this,

		ID_ArrayList, NAME_ArrayList, PHONE_NUMBER_ArrayList, SUBJECT_ArrayList, this);

		LISTVIEW.setAdapter(ListAdapter);

		cursor.close();

	}

	@Override
	public void onItemClick(String id, int position) {
		int status = deleteId(id);
				if (status ==1){
					Toast.makeText(this,"Item deleted successfully: ", Toast.LENGTH_SHORT).show();
					ID_ArrayList.remove(position);
					ListAdapter.notifyDataSetChanged();
				}
	}

	public int deleteId(String id) {
		SQLiteDatabase db = SQLITEHELPER.getWritableDatabase();
		int delete = db.delete("demoTable", "id=?", new String[]{id});
		db.close();
		return delete;
	}

}
