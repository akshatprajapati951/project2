<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="in.co.rays.proj3.controller.ErrorCtl"%>
<%@page isErrorPage="true" %>

<html>

<style>
#table {
	position: absolute;
	left: 35%;
	margin: 0;
	padding-top: 5px;
}

#error {
	margin: 0;
	height: 10px;
	padding-bottom: 5px;
}

th, td {
	padding-bottom: 1px;
	text-align: left;
}

#footer {
	margin-top: 5px;
	position: absolute;
bottom:-20px;
	width: 100%;
}
</style>
<title>Error</title>

<body>
<form action="<%=ORSView.ERROR_CTL%>" method="POST">
	<%
		Object mesg = (Object) request.getAttribute("exception");
	%>
	
	<h1 align= "center">
	<img  src="image/customLogo.png"  width="318" height="127" border="0">
	
	<hr></hr>
	
	<h1 align="center">
	
	
	&nbsp; &nbsp; 
	<img src="image/error.png" width="300" height="125" border="0">
	</h1>
	<h2 align="center">error</h2>

  <h1 align= "center"><input type="submit" name="operation"
   value="<%=ErrorCtl.OP_BACK %>">
    </h1>

	<div id="footer">
<%@ include file="Footer.jsp"%>
</div>
</body>
</html>