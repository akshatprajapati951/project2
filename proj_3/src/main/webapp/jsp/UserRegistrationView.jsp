<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">
		<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO"
				scope="request"></jsp:useBean>
				
				<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
					type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
				<input type="hidden" name="modifiedBy"
					value="<%=dto.getModifiedBy()%>"> <input type="hidden"
					name="createdDatetime"
					value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
				<input type="hidden" name="modifiedDatetime"
					value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
				
			
			<div class="card text-center"
				style="margin: 20 auto; float: none; max-width: 40rem;">
				<div class="card-header  text-white"style=" background-color: black;">
					<h2 style="text-align: center;">User Registration</h2>
				<div style="margin-bottom: -2%;">

					<H2>
						<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
						</font>
					</H2>
					<H2>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
						</font>
					</H2>
				</div>
				</div>
				
				<div class="card-body">
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">First Name</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									id="firstName"
									value="<%=DataUtility.getStringData(dto.getFirstName())%>"
									name="firstName" placeholder="First Name">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Last Name</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									id="lastName"
									value="<%=DataUtility.getStringData(dto.getLastName())%>"
									name="lastName" placeholder="Last Name">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Email ID</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" id="login"
									value="<%=DataUtility.getStringData(dto.getLogin())%>"
									name="login" placeholder="Email ID">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Password</div>
							<div class="col-sm-5">
								<input type="password" class="form-control mb-2 mr-sm-2"
									value="<%=DataUtility.getStringData(dto.getPassword())%>"
									name="password" id="password" placeholder="Password">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Confirm Password</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									value="<%=DataUtility.getStringData(dto.getConfirmPassword())%>"
									name="confirmPassword" id="confirmPassword"
									placeholder="Confirm Password">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Mobile No</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
									name="mobileNo" id="mobileNo" maxlength="10"
									placeholder="enter mobile No">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Gender</div>
							<div class="col-sm-5">
								<%
									HashMap<String, String> map = new HashMap<String, String>();
									map.put("Male", "Male");
									map.put("Female", "Female");
									String htmlList = HtmlUtility.getList("gender", dto.getGender(), map);
								%>
								<%=htmlList%>
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Date of Birth</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" readonly="readonly"
									id="datepicker" 
									value="<%=DataUtility.getDateString(dto.getDob())%>" name="dob"
									placeholder="enter dob">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font>
							</div>
						</div>
					</div>
					</div>
					<div class="card-footer text-center">
						<div class="form-group">
							<div class="d-flex justify-content-around mb-2">
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
								</div>
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=UserRegistrationCtl.OP_RESET%>">
								</div>
							</div>
						</div>
					</div>
				
			</div>
		</form>
	</div>
</section>
<%@ include file="Footer.jsp"%>