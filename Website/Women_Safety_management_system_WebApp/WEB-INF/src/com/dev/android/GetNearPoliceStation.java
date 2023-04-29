package com.dev.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dao.UserDAO;
import com.dev.util.Distance;


public class GetNearPoliceStation extends HttpServlet
{
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException 
		{
	try{
		
		System.out.println("its came inside police station");
		String lat=req.getParameter("lat");
		String lang=req.getParameter("lang");
		PrintWriter out = resp.getWriter();
		double lat1=Double.parseDouble(lat);
		double lon1=Double.parseDouble(lang);
		
		/*ResultSet rs=(ResultSet) UserDAO.getPoliceStationLocations();
		
		while(rs.next())
		{
			String sno=rs.getString(1);
			String lati=rs.getString(5);
			String langi=rs.getString(6);
			
			
			//double distance = Distance.distance(lat1, lon1, Double.parseDouble(lati), Double.parseDouble(langi));
			
			//boolean flag=UserDAO.updatedistance(sno, distance);
		}*/
		
		String nearlocations = UserDAO.getNearPoliceStationLocations(lat1, lon1);
		
		
		
		
		out.println(nearlocations);
		
	}catch (Exception e) {
		// TODO: handle exception
	}
	
}
}
