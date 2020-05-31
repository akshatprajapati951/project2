<%@page import="in.co.rays.proj3.controller.MyProfileCtl"%>
<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ page errorPage="ORSView.ERROR_VIEW"%>
<script type="text/javascript" src="../js/calendar.js"></script>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">
	<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post" >

		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>
			
			 <input type="hidden" name="id" value="<%=dto.getId()%>">
			<input type="hidden" name="createdBy"
				value="<%=dto.getCreatedBy()%>"> <input type="hidden"
				name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
				type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
	


		<div class="card text-center"
				style="margin: 20 auto; float: none; max-width: 40rem;">
				<div class="card-header text-white"style=" background-color: black;">
					<h2 style="text-align: center;">My Profile</h2>
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
							<div class="col-sm-3">login ID</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" id="login"
									value="<%=DataUtility.getStringData(dto.getLogin())%>"
									<%=(dto.getId() > 0) ? "readonly" : ""%> name="login"
									placeholder="Enter Login Id">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
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
										value="<%=MyProfileCtl.OP_UPDATE%>">
								</div>
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
								</div>
							</div>
						</div>
					</div>
				
			</div>
		</form>
	</div>
</section>
<%@ include file="Footer.jsp"%>
					
					<%-- <th  align="left">LoginId <font style="color: red">*</font></th>
					<td><input type="text" name="login"
						value="<%=DataUtility.getStringData(dto.getLogin())%>"
						readonly="readonly" placeholder="enter login id"
						style="font: status-bar;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>

				<tr>
					<th  align="left">First Name <font style="color: red">*</font></th>
					<td><input type="text" name="firstName"
						value="<%=DataUtility.getStringData(dto.getFirstName())%>"
						placeholder="enter first name" style="font: status-bar;"><font
						color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th  align="left">Last Name <font style="color: red">*</font></th>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getStringData(dto.getLastName())%>"
						placeholder="enter last name" style="font: status-bar;"><font
						color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th  align="left">Gender <font style="color: red">*</font></th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("N/A", "Select");
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HtmlUtility.getList("gender", dto.getGender(), map);
						%> <%=htmlList%> <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
					</td>
				</tr>
				<tr>
					<th  align="left">Mobile No <font style="color: red">*</font></th>
					<td><input type="text" name="mobileNo" maxlength="10"
						value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
						placeholder="enter mobile number" style="font: status-bar;"><font
						color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>

				<tr>
					<th  align="left">Date Of Birth <font style="color: red">*</font></th>
					<td><input type="text" name="dob" readonly="readonly"
						value="<%=DataUtility.getDateString(dto.getDob())%>"
						id="datepicker" placeholder="pick a date"
						style="font: status-bar;"> <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>

				<tr height="40px">
					<td ></td>
					<td><input type="submit" name="operation"
						value="<%=MyProfileCtl.OP_UPDATE%>"> &nbsp;&nbsp;<input
						type="submit" name="operation"
						value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>"> &nbsp;
					</td>
				</tr>
			</table>
			</form>
		</center>
		<br><br><br>
		</div>
		<div >
	<%@ include file="Footer.jsp"%>
</div>
</body>
</html> --%>