<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.controller.ChangePasswordCtl"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>

<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">
	
		<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

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
					<h2 style="text-align: center;">Change Password</h2>
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
							<div class="col-sm-3">Old Password</div>
							<div class="col-sm-5">
								<input type="password" class="form-control mb-2 mr-sm-2"
									id="firstName" name="old Password" placeholder="old Password"
									value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>>
									
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></font>
							</div>
						</div>
					</div>

				
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">New Password</div>
							<div class="col-sm-5">
								<input type="password" class="form-control mb-2 mr-sm-2"
									id="newPassword"  name="newPassword" placeholder="newPassword"
									value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>>
									
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("newPassword", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Confirm Password</div>
							<div class="col-sm-5">
								<input type="password" class="form-control mb-2 mr-sm-2"
									id="confirmPassword" name="confirmPassword" placeholder="confirmPassword"
									value=<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>>
									
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
						</div>
					</div>
					</div>
					<div class="card-footer text-center">
						<div class="form-group">
							<div class="d-flex justify-content-around mb-2">
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=ChangePasswordCtl.OP_UPDATE%>">
								</div>
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=ChangePasswordCtl.OP_RESET%>">
								</div>
							</div>
						</div>
					</div>
				
			</div>
		</form>
	</div>
</section>
<%@ include file="Footer.jsp"%>
					
					
					
				<%-- 	<div id="clearfix"></div>
			<table id="table">
						<tr>
							<th  align="left">Old Password <font style="color: red">*</font></th>
							<td><input type="password" name="oldPassword"
								placeholder="enter old password" style="font: status-bar;"
								value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
						</tr>

						<tr>
							<th align="left">New Password <font style="color: red">*</font></th>
							<td><input type="password" name="newPassword"
								placeholder="enter new password" style="font: status-bar;"
								value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
						</tr>

						<tr>
							<th  align="left">Confirm Password <font style="color: red">*</font></th>
							<td><input type="password" name="confirmPassword"
								placeholder="enter confirm password" style="font: status-bar;"
								value=<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
						</tr>
						<tr  height="40px">
                        <td></td>
						<td >&nbsp;&nbsp;&nbsp;<input type="submit"
							name="operation" value="<%=ChangePasswordCtl.OP_UPDATE%>">
							&nbsp; &nbsp;<input type="submit" name="operation"
							value="<%=ChangePasswordCtl.OP_RESET%>"> &nbsp;
						</td>
						</tr>
						</center>

					</table>
	
	</form>
</div>
	 <div>
 	<%@ include file="Footer.jsp"%>
 	</div>
 </body>
 --%></html>