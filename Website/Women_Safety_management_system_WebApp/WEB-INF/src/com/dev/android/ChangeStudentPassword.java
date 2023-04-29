
package com.dev.android;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.dao.UserDAO;


@SuppressWarnings("serial")
public class ChangeStudentPassword extends HttpServlet
{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		boolean flag = false;
		String resp="",username="",password="",newPassword="",confirmPassword="";
		PrintWriter out = response.getWriter();
		try 
		{
		    username = request.getParameter("un");
		    password = request.getParameter("old_Pwd");
		    newPassword = request.getParameter("new_Pwd");
		    confirmPassword = request.getParameter("coinfirm_Pwd");
			
		    //System.out.println("Username: " +username+ "\n" +"Password:"  +password);
		    //System.out.println("New Password: " +newPassword+ "\n" +"Confirm Password:"  +confirmPassword);
		    
			flag = UserDAO.changeUserPassword(username, newPassword);
		    
			//resp = Boolean.toString(flag);
			//out.println(resp);//Returing Back The Response
			
			out.println(flag); 
			System.out.println("Change Student Password Response : " + resp);
		}
		catch (Exception e) 
		{
			System.out.println("Opps,Exception In Android=>ChangeStudentPassword Srevlet : " );
			e.printStackTrace();
				
		}
	}
}
