<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%> 
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<html>
<!-- <style>
.page {
	min-height: 530px;
}

#clearfix {
	clear: both
}

#table {
	position: absolute;
	left: 35%;
	padding-top: -30px;
	height: auto;
}

#error {
	clear: both;
	left: 35%;
	height: 10px;
	

}

</style>
<body> -->
<%@ include file="Header.jsp"%>
<section class="content">
	<div class="container">
		<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO"
				scope="request"></jsp:useBean>

			<div class="card"
				style="margin: 20 auto; float: none; max-width: 20rem;">
				<div class="card-header  text-white" style=" background-color: black;">
					<div style="text-align: center;">Forget Password</div>
				</div>
				<div class="card-body">
					<div>
						<p>Write your Email ID , We will Send your Password to Your
							Email...</p>
					</div>
					<div style="padding-bottom: 5%;">
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
				<div class="card-footer text-center">
					<div class="input-group mb-ms-3">
						<input type="text" class="form-control" name="login"
							placeholder="   Enter Email Id "
							value="<%=ServletUtility.getParameter("login", request)%>">
						<div class="input-group-append">
							<button class="btn text-white" type="submit" name="operation"style=" background-color: black;"
								value="<%=ForgetPasswordCtl.OP_GO%>">Go</button>
						</div>
					</div>
					<div class="row text-center" style="padding: 10px;">
						<div class="col-sm-12">
							<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>
<%@ include file="Footer.jsp"%>