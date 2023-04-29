 
package com.dev.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.Collections;
import com.dev.util.Distance;

public class UserDAO 
{
	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	private static UserDAO userDAO=null;
	private UserDAO(){}
	
	public static UserDAO initialize()
	{
		if(userDAO==null)
		{
			userDAO=new UserDAO();
		}
		return userDAO;
	}
	

	public static boolean authenticateUser(String username,String password)
	{
		boolean flag=false;
		String sql = "";
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_user where username='"+username+"' and password='"+password+"'";
			
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static boolean checkUserExistence(String username)
	{
		boolean flag=false;
		String sql = "";
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_user where username='"+username+"'";
			
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static boolean addUser(String username,String password,String name,String sex,String city,String phone)
	{
		boolean flag=false;
		String sql = "";
		int i=0;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "insert into m_user (username,password,name,sex,city,phone) values('"+username+"','"+password+"','"+name+"','"+sex+"','"+city+"','"+phone+"')";
			
			System.out.println(sql);
			i = statement.executeUpdate(sql);
			if(i>0)
			{
				flag=true;
			}
		
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	public static boolean changeUserPassword(String username,String newPassword)
	{
		boolean flag=false;
		String sql="";
		int i=0;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "update m_user set password='"+newPassword+"' where username='"+username+"'";
			
			System.out.println(sql);
			i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	public static ResultSet getPoliceStationLocations()
	{
		boolean flag=false;
		String sql = "";
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from p_police_station";
			
			resultSet = statement.executeQuery(sql);
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static ResultSet getNeibhursLocations()
	{
		boolean flag=false;
		String sql = "";
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_neibhur";
			
			
			resultSet = statement.executeQuery(sql);
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static ResultSet getHospitalsLocations()
	{
		boolean flag=false;
		String sql = "";
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from h_hospital";
			
			
			resultSet = statement.executeQuery(sql);
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return resultSet;
	}
	
	
	
	
	public static String getNearPoliceStationLocations(Double lat1, Double lon1)
	{
		boolean flag=false;
		String sql = "";
		StringBuffer sb=new StringBuffer();
		int k=2;
		
		String concat = null;
		try
		{
			
			ArrayList list=new ArrayList<Object>();
	        ArrayList list1=new ArrayList<Object>();
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from p_police_station";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				String phone=resultSet.getString(10);
				String lati=resultSet.getString(13);
				String langi=resultSet.getString(14);
				
				double distance = Distance.distance(lat1, lon1, Double.parseDouble(lati), Double.parseDouble(langi), 'K');
				
				System.out.println(distance);
				
				list1.add(distance);
				list.add(distance+"~"+lati+"~"+langi+"~"+phone);
				
				
				//double distance = Distance.distance(lat1, lon1, Double.parseDouble(lati), Double.parseDouble(langi));
				//boolean flag=UserDAO.updatedistance(sno, distance);
			}
			
			
			Object obj = Collections.min(list1);

			for (int i=0;i<list.size();i++)
			{
				String value=   list.get(i).toString();
				String[] data=  value.split("~");

				String dist= data[0];
				if(dist.equals(obj.toString()))
				{
					String smalallat=   data[1];
					String smalllong=  data[2];
					String smalldisnamephone=  data[3];
					concat = smalldisnamephone;
				}
				
				System.out.println(concat);
			}
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return concat;
	}
	
	
	
	public static String getNearHospital(Double lat1, Double lon1)
	{
		boolean flag=false;
		String sql = "";
		StringBuffer sb=new StringBuffer();
		int k=2;
		
		String concat = null;
		try
		{
			
			ArrayList list=new ArrayList<Object>();
	        ArrayList list1=new ArrayList<Object>();
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from h_hospital";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				String phone=resultSet.getString(11);
				String lati=resultSet.getString(13);
				String langi=resultSet.getString(14);
				
				double distance = Distance.distance(lat1, lon1, Double.parseDouble(lati), Double.parseDouble(langi), 'K');
				
				System.out.println(distance);
				
				list1.add(distance);
				list.add(distance+"~"+lati+"~"+langi+"~"+phone);
				
			}
			
			
			Object obj = Collections.min(list1);

			for (int i=0;i<list.size();i++)
			{
				String value=   list.get(i).toString();
				String[] data=  value.split("~");

				String dist= data[0];
				if(dist.equals(obj.toString()))
				{
					String smalallat=   data[1];
					String smalllong=  data[2];
					String smalldisnamephone=  data[3];
					concat = smalldisnamephone;
				}
				
				System.out.println("hos: " + concat);
			}
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return concat;
	}
	

	public static String getemgfire()
	{
		boolean flag=false;
		String sql = "";
		String num="";
		int k=2;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_emergency_call_no";
			
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				
				num=	resultSet.getString(3);
			
				
			}
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return num;
	}
	public static String getpolicenum()
	{
		boolean flag=false;
		String sql = "";
		String num="";
		int k=2;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_emergency_call_no";
			
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
				
				num=	resultSet.getString(2);
			
				
			}
			
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return num;
	}
	
	public static String getNeibhurLocations()
	{
		boolean flag=false;
		String sql = "";
		StringBuffer sb=new StringBuffer();
		int k=2;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from m_neibhur where dist < 4000  order by dist asc";
			System.out.println("sql :"+sql);
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
			k=k+1;
				sb.append(resultSet.getString(2)+"("+resultSet.getString(4)+")~"+resultSet.getString(5)+"~"+resultSet.getString(6)+"~"+resultSet.getString(4));
				sb.append("@");
			}
			
			if(k==2)
			{
				sb.append("empty");
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static String getNearHospitalsLocations()
	{
		boolean flag=false;
		String sql = "";
		StringBuffer sb=new StringBuffer();
		int k=2;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "select * from h_hospital where dist < 1000  order by dist asc";
			
			
			resultSet = statement.executeQuery(sql);
			while(resultSet.next())
			{
			k=k+1;
				sb.append(resultSet.getString(2)+"~"+resultSet.getString(13)+"~"+resultSet.getString(14));
				sb.append("@");
			}
			
			if(k==2)
			{
				sb.append("empty");
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	public static boolean updatedistance(String sno,Double dist)
	{
		boolean flag=false;
		String sql="";
		int i=0;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "update p_police_station set dist='"+dist+"' where code='"+sno+"'";
			
			System.out.println(sql);
			i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	public static boolean updateNeibhurdistance(String sno,Double dist)
	{
		boolean flag=false;
		String sql="";
		int i=0;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "update m_neibhur set dist='"+dist+"' where n_code='"+sno+"'";
			
			System.out.println(sql);
			i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static boolean updatedistanceHosp(String sno,Double dist)
	{
		boolean flag=false;
		String sql="";
		int i=0;
		try
		{
			connection=DAO.getConnection();
			statement = connection.createStatement();
			sql = "update h_hospital set dist='"+dist+"' where H_code='"+sno+"'";
			
			System.out.println(sql);
			i = statement.executeUpdate(sql);
			if(i!=0)
			{
				flag=true;
			}
			
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
		}
		return flag;
	}
	
}
