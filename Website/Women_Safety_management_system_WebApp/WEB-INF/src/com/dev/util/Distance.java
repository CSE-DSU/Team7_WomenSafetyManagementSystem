package com.dev.util;

public class Distance {
	
	public static void main(String[] args) {
		double bd=	distance(12.922275, 77.567049, 12.922009, 77.568347,'K') ;	
		System.out.println(bd);
	}

	// 1.	
    //Best Distance Calculation Formulla(Starts) 
	public static double distFrom(double lat1, double lng1, double lat2, double lng2) 
	{
		//double earthRadius = 3958.75;//In Miles
		//double earthRadius = 6371;//In Km(Kilo meteres)
		double earthRadius = 6371 * 1000;//In m(meter)
	    double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	           Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	           Math.sin(dLng/2) * Math.sin(dLng/2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    return   earthRadius * c;
   }
	
	   /* Best Distance Calculation Formulla(Ends) */	
	
	// 2.	
		
	/*
	 *  Passed to function:    
	 *   lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  
	 *   lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)
	 *   
	 *  unit = the unit you desire for results   
	 *  		where: 'M' is statute miles  
	 *  		'K' is kilometers (default)  
	 *  		'N' is nautical miles                                                           
	*/
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		  double theta = lon1 - lon2;
		  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		  dist = Math.acos(dist);
		  dist = rad2deg(dist);
		  dist = dist * 60 * 1.1515;
		  if (unit == 'K') {
		    dist = dist * 1.609344;
		  } else if (unit == 'N') {
		  dist = dist * 0.8684;
		    }
		  return (dist);
		}
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::  This function converts decimal degrees to radians             :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
		}
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		/*::  This function converts radians to decimal degrees             :*/
		/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
		private static double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
		}

		
}
