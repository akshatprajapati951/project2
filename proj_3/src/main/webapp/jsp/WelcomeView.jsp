<%@page import="in.co.rays.proj3.controller.ORSView"%>
<title>proj_3</title>
<!-- head>
 <style>
#table {
	position: absolute;
	left: 35%;
	margin: 0;
	padding-top: 10px;
}

#error {
	margin: 0;
	height: 10px;
	padding-bottom: 10px;
}

th, td {
	padding-bottom: 8px;
	text-align: left;
}
#footer{
position:fixed;
bottom:0;
width:100%;
background-color:primaryblue;
} 

/* body, html {
    height: 100%;
    margin: 0;
}

.bg {
    /* The image used */
    background-image: url("image/Website-Design-Background.png");

    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: repeat;
    background-size: cover;
}


 */
</style> 

</head>
<html>
<body class="bg">
	 <body>
 -->	
 
 
		
			<%@ include file="Header.jsp"%>
			<section class="content">
	<div class="container">
	
  <form action="<%=ORSView.WELCOME_CTL%>">
		
			<br><br><br><br><br>
			<center>
			<h1 >
				<font size="10px" color="black">Welcome to ORS </font>
			</h1>

			<%-- <%
				UserDTO beanUserBean = (UserDTO) session.getAttribute("user");
				if (beanUserBean != null) {
					if (beanUserBean.getRoleId() == RoleDTO.STUDENT) {
			%>

			<h1 align="center"><a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click
				here to see your Marksheet </a> </h1>

			<%
				}
				}
			%>

 --%>	</center>	
	
	</form>
		</div>
	

</section>

<%@ include file="Footer.jsp"%>