
<%@page import="com.dev.dao.UserDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>

<%!String id = "";
	String pass = "",gender="",name="",city="",phone="";

	boolean flag = false;

	StringBuffer sb = null;
	String info = "True";
	String info1 = "False";
	
	%>


<%
	sb = new StringBuffer();

	id = request.getParameter("Id");
	pass = request.getParameter("Pass");
	name = request.getParameter("Name");
	gender = request.getParameter("Gender");
	city = request.getParameter("City");
	phone = request.getParameter("Mobile");
	
	System.out.println("  Data is: " + id+"."+pass+"."+name+"."+gender+"."+city+"."+phone);
	
	
	
	boolean idexist = UserDAO.checkUserExistence(id);
	
	System.out.println("  idexist is: " + idexist);
	if(idexist)
	{
		
		 sb.append("UserExist");
	}
	else
	{
		
	boolean val =UserDAO.addUser(id,pass,name,gender,city,phone);
	
	System.out.println("Insert Status  " + val);
	
	
		 
		  if(val)
		  {
		  sb.append(info);
		  }
		  else
		  {
			  sb.append(info1); 
		  } 

	
	String status=sb.toString();
	out.println(sb.toString()); 
	System.out.println("  Status is: " + status);
	}
	
	out.println(sb.toString()); 
%>

