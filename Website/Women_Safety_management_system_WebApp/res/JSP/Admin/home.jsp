<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.dev.dao.AdminDAO"%>
<%@page import="com.dev.util.Utility"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title> Admin Home </title>

<link href="<%=request.getContextPath()%>/res/CSS/centerd_menu_tab.css" rel="stylesheet" type="text/css"/>

<%-- Nav Menu (Starts) --%>
<link href="<%=request.getContextPath()%>/res/CSS/message.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/res/JS/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/JS/script.js"></script>

<script>
    history.forward();
</script>
<style type="">

</style>

</head>

<body onload="startTimer()" class="back">
<%-- <img src="<%=request.getContextPath()%>/res/Images/Women.png" style="width:100%" height="80"  alt=""/> --%>
<strong><h1 style="width:100%; font-size:500%;color:white;text-align:center;">Women Safety Management System</h1></strong>

<div id="centeredmenu" style="margin-top: 1px;">
    <ul>
	      
         <li><a href="<%=request.getContextPath()%>/res/JSP/Admin/home.jsp">Home</a></li>
	     <li><a href="<%=request.getContextPath()%>/HospitalCreation?action=get" target="myFrame">Hospital</a></li>
	     <li><a href="<%=request.getContextPath()%>/PoliceStation?action=get" target="myFrame">Police Station</a></li>
	      <li><a href="<%=request.getContextPath()%>/index.jsp?no=2">Sign Out</a></li>
    </ul>
    
</div>
<div class="Content" style="position: absolute; height: 100%; width: 100%; margin-left: -2%;margin-top:22px;">
<iframe src="<%=request.getContextPath()%>/res/JSP/Admin/default.jsp" name="myFrame" style="height: 100%; width: 100%"></iframe>
</div>


<%-- <div class="Content" style="width:100vw; height:100vh;">
<iframe src="<%=request.getContextPath()%>/res/JSP/Admin/default.jsp" name="myFrame"></iframe>
</div>
 --%>
<%
	int no=Utility.parse(request.getParameter("no"));
		if(no==1)
		{
%>
			<div class="info" id="message" style="position:absolute;top:600px;left:50px">
				<p>Welcome! <%=AdminDAO.getAdminName((String)session.getAttribute("username"))%></p>
			</div>
		<%}
%>

</body>
</html>
