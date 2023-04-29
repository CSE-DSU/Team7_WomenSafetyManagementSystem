 
package com.dev.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dao.UserDAO;


@SuppressWarnings("serial")
public class Getpolicenumber extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		boolean flag = false;
		String resp="",username="",password="",newPassword="",confirmPassword="";
		PrintWriter out = response.getWriter();
		try 
		{
		 
	String data= UserDAO.getpolicenum();
		    
		
			out.println(data); 
			System.out.println("data : " + data);
		}
		catch (Exception e) 
		{
			System.out.println("Opps,Exception In Android=>ChangeStudentPassword Srevlet : " );
			e.printStackTrace();
				
		}
	}
}
