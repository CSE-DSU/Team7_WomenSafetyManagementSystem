package com.dev.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dev.dao.AdminDAO;
import com.dev.util.Utility;
public class PoliceStation extends HttpServlet {
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException
	{  
		PrintWriter out=response.getWriter();
		ResultSet rs=null; 
		RequestDispatcher rd=null;
		boolean result = false,flag=false;
		String police_station_name="",Address1="",Address2="",Area="",City="",state="",pin="",land_line="",cell_no1="",cell_no2="",remarks="",latitude="",logitude="";
		HttpSession session = request.getSession();
		try{
           String submit=request.getParameter("action");
		   rs=AdminDAO.getpolice();
		   if(submit.equals("get"))
			{
				request.setAttribute("rs", rs);
				rd=request.getRequestDispatcher("/res/JSP/Admin/policestation_list.jsp");
				rd.forward(request, response);
			}
		   if(submit.equals("Add Police"))
		   {
			   System.out.println("Police Station 1");
			if(Utility.parse1(request.getParameter("add")).equals("YES"))
			{
			police_station_name=request.getParameter("police_station_name");
			Address1=request.getParameter("Address1");
			Address2=request.getParameter("Address2");
			Area=request.getParameter("Area");
			City=request.getParameter("City");
			state=request.getParameter("state");
			pin=request.getParameter("pin");
			land_line=request.getParameter("land_line");
			cell_no1=request.getParameter("cell_no1");
			cell_no2=request.getParameter("cell_no2");
			remarks=request.getParameter("remarks");
			latitude=request.getParameter("latitude");
			logitude=request.getParameter("logitude");
			
			flag=AdminDAO.checkpolicestation(police_station_name);
			if(!flag)
			{System.out.println("Police Station 2");
				flag = AdminDAO.addpolicestation(police_station_name, Address1, Address2, Area, City, state, pin, land_line, cell_no1, cell_no2, remarks, latitude, logitude);
				if(flag)
				{
					rs=AdminDAO.getpolicestation();
					request.setAttribute("rs", rs);
					rd=request.getRequestDispatcher("/res/JSP/Admin/policestation_list.jsp?no=1");
					rd.forward(request, response);
				}
				else
				{System.out.println("Police Station 3");
						rd=request.getRequestDispatcher("/res/JSP/Admin/add_policestation.jsp?no=2");
						rd.forward(request, response);
				}
						
			}
			else
			    {System.out.println("Police Station 4");
				        rd=request.getRequestDispatcher("/res/JSP/Admin/add_policestation.jsp?no=3");
				        rd.forward(request, response);
			    }
		    }
		    else
			{
		    	System.out.println("Police Station 6");
				rd=request.getRequestDispatcher("/res/JSP/Admin/add_policestation.jsp");
				rd.forward(request, response);
			}	
		  }
		   
		  if(submit.equals("Edit Police"))
		  {
			  
			  
			  
			  String []chk=request.getParameterValues("chk");
			   System.out.println("chk[]>>>>>>>>>>>"+chk[0]);
			   
			   rs=AdminDAO.Udatpolice(chk[0]);
			   request.setAttribute("rs", rs);
				  rd=request.getRequestDispatcher("/res/JSP/Admin/edit_police.jsp");
			  
				  rd.forward(request, response);
		  
		  }
		  
		  if(submit.equals("edit"))
		  {
			  
			 
				  
		
			    police_station_name=request.getParameter("police_station_name");
				Address1=request.getParameter("Address1");
				Address2=request.getParameter("Address2");
				Area=request.getParameter("Area");
				City=request.getParameter("City");
				state=request.getParameter("state");
				pin=request.getParameter("pin");
				land_line=request.getParameter("land_line");
				cell_no1=request.getParameter("cell_no1");
			
				remarks=request.getParameter("remarks");
				latitude=request.getParameter("latitude");
				logitude=request.getParameter("logitude");
				
			String	code=request.getParameter("chk");
				ArrayList<String> list = new ArrayList<String>();
				list.add(code);
				list.add(police_station_name);
				list.add(Address1);
				list.add(Address2);
				list.add(Area);
				list.add(City);
				list.add(state);
				list.add(pin);
				list.add(land_line);
				list.add(cell_no1);
			
				list.add(remarks);
				list.add(latitude);
				list.add(logitude);
				
				System.out.println("%%%%%%%%%%%%%%"+code);
				flag = AdminDAO.updatepolicestation(list);
				System.out.println("%%%%%%%%%%%%%%");
				if(flag)
				{
					System.out.println("^^^^^^^^^^^^^^^^^^^^");
					rs=AdminDAO.getpolice();
					request.setAttribute("rs", rs);
					rd=request.getRequestDispatcher("/res/JSP/Admin/policestation_list.jsp?no=5");
					rd.forward(request,response);
				}
				else
				{
					
					request.setAttribute("rs", rs);
					rd=request.getRequestDispatcher("/res/JSP/Admin/edit_police.jsp?no=2");
					rd.forward(request,response);
				} 
		     }
			  

		   if(submit.equals("Delete Police"))
		   {
			   String delete=request.getParameter("delete");
			   System.out.println("delete>>>>>>>>>"+delete);
			   String []chk=request.getParameterValues("chk");
			   System.out.println("chk[]>>>>>>>>>>>"+chk[0]);
			
			
				flag = AdminDAO.deletepolicestation(chk[0]);
				if(flag)
				{
					rs=AdminDAO.getpolicestation();
					request.setAttribute("rs", rs);
					rd=request.getRequestDispatcher("/res/JSP/Admin/policestation_list.jsp?no=3");
					rd.forward(request, response);
				}
				else
				{
					
					rd=request.getRequestDispatcher("/res/JSP/Admin/policestation_list.jsp?no=6");
					rd.forward(request, response);
				}
		   }
		}
	
		catch(Exception e)
		{
			System.out.println("Opps's Error is in Admin Police Creation Servlet : ");
			e.printStackTrace();
			
		}
	}
}


		 
		  
		 
		