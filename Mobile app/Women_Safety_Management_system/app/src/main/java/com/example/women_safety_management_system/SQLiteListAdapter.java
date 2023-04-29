package com.example.women_safety_management_system;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SQLiteListAdapter extends BaseAdapter {

	Context context;
	ArrayList<String> userID;
	ArrayList<String> UserName;
	ArrayList<String> User_PhoneNumber;
	ArrayList<String> UserSubject;
	OnItemListener listener;
	SQLiteHelper SQLITEHELPER;

	public interface OnItemListener{
		void onItemClick(String id, int position);
	}
	public SQLiteListAdapter(Context context2, ArrayList<String> id,
							 ArrayList<String> name, ArrayList<String> phone,
							 ArrayList<String> subject, OnItemListener listener) {

		this.context = context2;
		this.userID = id;
		this.UserName = name;
		this.User_PhoneNumber = phone;
		this.UserSubject = subject;
		this.listener = listener;
		SQLITEHELPER = new SQLiteHelper(context2);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return userID.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(final int position, View women, ViewGroup parent) {

		Holder holder;

		LayoutInflater layoutInflater;

		if (women == null) {
			layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			women = layoutInflater.inflate(R.layout.listviewdatalayout, null);

			holder = new Holder();

			holder.textviewid = (TextView) women.findViewById(R.id.textViewID);
			holder.textviewname = (TextView) women
					.findViewById(R.id.textViewNAME);
			holder.textviewphone_number = (TextView) women
					.findViewById(R.id.textViewPHONE_NUMBER);
			holder.textviewsubject = (TextView) women
					.findViewById(R.id.textViewSUBJECT);
			holder.imageViewDelete = (ImageView) women.findViewById(R.id.delete);

			women.setTag(holder);

		} else {

			holder = (Holder) women.getTag();
		}
		holder.textviewid.setText(userID.get(position));
		holder.textviewname.setText(UserName.get(position));
		holder.textviewphone_number.setText(User_PhoneNumber.get(position));
		holder.textviewsubject.setText(UserSubject.get(position));


		holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				listener.onItemClick(userID.get(position), position);
			}
		});

		return women;
	}

	public class Holder {
		TextView textviewid;
		TextView textviewname;
		TextView textviewphone_number;
		TextView textviewsubject;
		ImageView imageViewDelete;
	}


}