package com.dev.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dao.UserDAO;

public class GetNearHospitals extends HttpServlet
{
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
			{
		try{
			
			System.out.println("its came inside Hospital");
			String lat=req.getParameter("latt");
			String lang=req.getParameter("langg");
			PrintWriter out = resp.getWriter();
			double lat1=Double.parseDouble(lat);
			double lon1=Double.parseDouble(lang);
			
			
			String nearlocations = UserDAO.getNearHospital(lat1, lon1);
			
			
			
			
			out.println(nearlocations);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
