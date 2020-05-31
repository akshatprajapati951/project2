<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
<%@page import="in.co.rays.proj3.controller.LoginCtl"%>
<%@page import="in.co.rays.proj3.dto.UserDTO"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>


<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="http://localhost:8080/proj_3/js/jquery-ui.css">
<script src="http://localhost:8080/proj_3/js/jquery-ui.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script>
	function noSunday(date) {
		var day = date.getDay();
		return [ (day > 0), '' ];
	};

	$(document).ready(function() {
		$(function() {
			$("#datepicker").datepicker({
				changeMonth : true,
				changeYear : true,
				yearRange : "-70:-18",
				maxDate : 0,
			});
		});
		$(function() {
			$("#datepicker1").datepicker({
				changeMonth : true,
				changeYear : true,
				yearRange : "2018:2025",
				minDate : 0,
				beforeShowDay : noSunday,

			});
		});
		$(function() {
			$("#datepicker2").datepicker({
				changeMonth : true,
				changeYear : true,
				yearRange : "2018:2025",
				beforeShowDay : noSunday,

			});
		});

	});
</script>
<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
}

html {
	height: 100%;
}

body {
	/*  background-color: #EDC9AF;  */
	display: flex;
	flex-direction: column;
	height: 100%;
}

.fa.fa-navicon {
	color: white;
}

footer {
	margin: 0rem;
	padding: 1rem;
	text-align: center;
	color: white;
	background-color: black;
}

header {
	margin: 0;
	padding: 0;
}

.content {
	flex: 1;
}

.table thead th {
	text-align: center;
}
</style>
</head>


<script type="text/javascript">
	function selectall(frm) {
		var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		for (var i = 0; i < checkboxes.length; i++) {
			checkboxes[i].checked = document.getElementById("chk").checked;
		}
	}
	function unchk(frm) {
		document.getElementById("chk").checked = false;
	}
</script>

<body>
	<header>
		<nav
			class="navbar  navbar-expand-lg  navbar-static-top navbar-inverse"
			style="padding-bottom: 0rem; background-color: black;">
			<div class="container-fluid">

				<a class="navbar-brand"> <img
					src="<%=ORSView.APP_CONTEXT%>/image/newlogo.png"
					class="img-responsive" width="100" border="0">
				</a>




				<button class=" navbar navbar-toggler " type="button"
					data-toggle="collapse" data-target="#collapsibleNavbar">

					<span class="navbar-toggler-icon "> <i class="fa fa-navicon"></i></span>
				</button>

				<div class="collapse navbar-collapse justify-content-center "
					id="collapsibleNavbar">
					<%
						UserDTO userBean = (UserDTO) session.getAttribute("user");

						if (userBean != null) {

							if (userBean.getRoleId() == 1) {
					%>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> User<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.USER_CTL%>">Add
								User</a> <a class="dropdown-item" href="<%=ORSView.USER_LIST_CTL%>">User
								List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Role<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.ROLE_CTL%>">Add
								Role</a> <a class="dropdown-item" href="<%=ORSView.ROLE_LIST_CTL%>">Role
								List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Student<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.STUDENT_CTL%>">Add
								Student</a> <a class="dropdown-item"
								href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> College<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.COLLEGE_CTL%>">Add
								College</a> <a class="dropdown-item"
								href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Marksheet<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.MARKSHEET_CTL%>">Add
								Marksheet</a> <a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> <a
								class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>">
								GET Marksheet </a> <a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit
								List</a>


						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Faculty<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.FACULTY_CTL%>">Add
								Faculty</a> <a class="dropdown-item"
								href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Course<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.COURSE_CTL%>">Add
								Course</a> <a class="dropdown-item"
								href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>

						</div>
					</div>
					<div class="dropdown text-white">
					<a class="dropdown-toggle" data-toggle="dropdown"> Subject<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.SUBJECT_CTL%>">Add
								Subject</a> <a class="dropdown-item"
								href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Timetable<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							<a class="dropdown-item" href="<%=ORSView.TIMETABLE_CTL%>">Add
								Timetable</a> <a class="dropdown-item"
								href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a>

						</div>
					</div>


					
<%
							}else{
%>
<div class="collapse navbar-collapse justify-content-center "
					id="collapsibleNavbar">
<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Course<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							 <a class="dropdown-item"
								href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a>

						</div>
					</div>
					<div class="dropdown text-white">
					<a class="dropdown-toggle" data-toggle="dropdown"> Subject<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							 <a class="dropdown-item"
								href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a>

						</div>
					</div>
					<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Timetable<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							 <a class="dropdown-item"
								href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a>

						</div>
					</div>

<div class="dropdown text-white">
						<a class="dropdown-toggle" data-toggle="dropdown"> Marksheet<span
							class="caret"></span>
						</a>&emsp;
						<div class="dropdown-menu" style="background-color: #8da0a3;">
							 <a
								class="dropdown-item" href="<%=ORSView.GET_MARKSHEET_CTL%>">
								GET Marksheet </a> <a class="dropdown-item"
								href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit
								List</a>
								</div>
								
<%
							}	
						}
					%>

					<%
						if (userBean == null) {
					%>
					<div class="collapse navbar-collapse justify-content-end "
						id="collapsibleNavbar">

						<a class=" nav-link text-white" href="<%=ORSView.LOGIN_CTL%>"><span><i
								class="fa fa-user-circle-o" aria-hidden="true"></i> </span>Sign In</a> <a
							class="nav-link text-white"
							href="<%=ORSView.USER_REGISTRATION_CTL%>"><span><i
								class="fa fa-id-card-o" aria-hidden="true"></i> </span> Sign Up</a>

						<%
							} else {
						%>
					</div>
					<div class="collapse navbar-collapse justify-content-end "
						id="collapsibleNavbar">
						<div class="dropdown  text-white">
							<a data-toggle="dropdown"> <%
 	if (userBean != null) {
 %> Hello: <%=userBean.getFirstName()%>(<%=session.getAttribute("role")%>)
								<%
 	}
 %>

							</a>

							<div class="dropdown-menu  " style="background-color: #8da0a3;">

								<a class="dropdown-item" href="<%=ORSView.MY_PROFILE_CTL%>">Edit
									My Profile</a> <a class="dropdown-item"
									href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</a> <a
									class="dropdown-item" href="<%=ORSView.JAVA_DOC_VIEW%>"
									target="blank">Java Doc</a> <a class="dropdown-item"
									href="<%=ORSView.LOGIN_CTL%>?operation=logout">Logout</a>

							</div>
						</div>
					</div>
					<%
							}
						
					%>

				</div>

			</div>
			</div>


			</div>




		</nav>
	</header>