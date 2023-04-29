package com.dev.android;

import java.sql.ResultSet;

import com.dev.dao.UserDAO;
import com.dev.util.Distance;


public class Test
{
public static void main(String[] args) {
	
	try{
		ResultSet rs=(ResultSet) UserDAO.getPoliceStationLocations();
		
		double lat1=Double.parseDouble("12.929374");
		double lon1=Double.parseDouble("77.582539");
		
		while(rs.next())
		{
			String sno=rs.getString(1);
			String lati=rs.getString(3);
			String langi=rs.getString(4);
			double distance = Distance.distFrom(lat1, lon1, Double.parseDouble(lati), Double.parseDouble(langi));
			
			boolean flag=UserDAO.updatedistance(sno, distance);
		}
	}catch (Exception e) {
		// TODO: handle exception
	}
	
	
}
}
