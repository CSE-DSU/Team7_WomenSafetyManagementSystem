<%@page import="com.dev.util.Utility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Investigator Home</title>

<link href="<%=request.getContextPath() %>/res/CSS/list.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/res/CSS/message.css" rel="stylesheet" type="text/css" />
 <style>
 body{
 background-color:#9f9f9f;
 background-image:url('<%=request.getContextPath() %>/res/CSS/images/index.jpg');
 }
 </style>
</head>

<body>

<section id="content">

<h1 id="wrapper">ADMIN HOME PAGE</h1>

<div style="position:relative;width:640px;height:400px;top:-125px;">
<%-- <img alt="empty" src="<%=request.getContextPath() %>/res/CSS/images/index.jpg"> --%>
</div>	
	

</section>	

<%
	if(Utility.parse(request.getParameter("no"))==1)
    {
%>
    	<div class="success" id="message" style="position:absolute;top:421px;left:11px">	
    		<p>Requested Process Cancel Successfully.....</p>
    	</div>			
<%
    }
%>
</body>
</html>