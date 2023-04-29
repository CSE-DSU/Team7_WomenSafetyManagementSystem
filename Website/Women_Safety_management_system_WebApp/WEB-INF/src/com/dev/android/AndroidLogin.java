package com.dev.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dao.UserDAO;


@SuppressWarnings("serial")
public class AndroidLogin extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		boolean flag = false;
		String resp="",username="",password="";
		PrintWriter out = response.getWriter();
		try 
		{
			username = request.getParameter("username");
			password = request.getParameter("password");
			//System.out.println("Username : " + username);
			//System.out.println("Username : " + password);
			
			flag = UserDAO.authenticateUser(username, password);
		    
			resp = Boolean.toString(flag);
			
			out.println(resp);//Returing Back The Response
			System.out.println("Student Login Response : " + resp);
		}
		catch (Exception e) 
		{
			System.out.println("Opps,Exception In Android=>StudentLogin Srevlet : " );
			e.printStackTrace();
				
		}
	}
}
