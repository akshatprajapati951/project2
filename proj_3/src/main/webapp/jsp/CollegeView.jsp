<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.controller.CollegeCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>

<%@ include file="Header.jsp"%>
<section class="content">
	<div class="container">

		<form action="CollegeCtl" method="POST">
			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.CollegeDTO"
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
				<div class="card-header  text-white" style=" background-color: black;">
				<div style="margin-bottom: -2%;">
					<%
						if (dto.getId() > 0) {
					%>
					<h2 style="text-align: center;">Update College</h2>

					<%
						} else {
					%><h2 style="text-align: center;">Add College</h2>
					<%
						}
					%>
					
						<H2>
							<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font>
						</H2>

						<H2>
							<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font>
						</H2>
					</div>
				</div>

			
					

					<div class="card-body">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">Name</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2" id="name"
										value="<%=DataUtility.getStringData(dto.getName())%>"
										name="name" placeholder="enter  Name">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
								</div>
							</div>
						</div>


						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">Address</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2"
										id="address"
										value="<%=DataUtility.getStringData(dto.getAddress())%>"
										name="address" placeholder="enter college address">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">State</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2" id="state"
										value="<%=DataUtility.getStringData(dto.getState())%>"
										name="state" placeholder="enter state of college">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("state", request)%></font>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">city</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2" id="city"
										value="<%=DataUtility.getStringData(dto.getCity())%>"
										name="city" placeholder="enter  Name">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("city", request)%></font>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">Mobile No</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2"
										value="<%=DataUtility.getStringData(dto.getPhoneNo())%>"
										name="phoneNo" id="phoneNo" maxlength="10"
										placeholder="enter Phone No">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></font>
								</div>
							</div>
						</div>
						</div>
						
						<div class="card-footer text-center">
							<div class="form-group">
								<div class="d-flex justify-content-around mb-2">

									<%
										if (dto.getId() > 0) {
									%>


									<div class="p-2">
										<input type="submit" class="btn  text-white" name="operation"style=" background-color: black;"
											value="<%=CollegeCtl.OP_UPDATE%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=CollegeCtl.OP_SAVE%>">
									</div>
									<%
										}
									%>
									<%
										if (dto.getId() > 0) {
									%><div class="p-2">
										<input type="submit" class="btn  text-white" name="operation"style=" background-color: black;"
											value="<%=CollegeCtl.OP_CANCEL%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=CollegeCtl.OP_RESET%>">
									</div>
									<%
										}
									%>
								</div>
							</div>
						</div>
					</div>
		</form>
	</div>
</section>
<%@ include file="Footer.jsp"%>




<%-- 
							<th align="left">Name <font style="color: red">*</font></th>
							<td><input type="text" name="name"
								placeholder="enter college name" style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getName())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Address <font style="color: red">*</font></th>
							<td><input type="text" name="address"
								placeholder="enter college address" style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getAddress())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
						</tr>
						<tr>
							<th align="left">State <font style="color: red">*</font></th>
							<td><input type="text" name="state"
								placeholder="enter state" style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getState())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
						</tr>
						<tr>
							<th align="left">City <font style="color: red">*</font></th>
							<td><input type="text" name="city" placeholder="enter city"
								style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getCity())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Mobile No <font style="color: red">*</font></th>
							<td><input type="text" name="phoneNo" maxlength="10"
								placeholder="enter phone no" style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getPhoneNo())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
						</tr>
						<tr>
							<td></td>
							<tr></tr>
							<td></td>

							<td>
								<%
									if (dto.getId() > 0) {
								%><input type="submit" name="operation"
								value="<%=CollegeCtl.OP_UPDATE%>">&nbsp;&nbsp;
								<%
									} else {
								%> &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="operation"
								value="<%=CollegeCtl.OP_SAVE%>">&nbsp;&nbsp;&nbsp;
								<%
									}
								%> <%
 	if (dto.getId() > 0) {
 %> <input type="submit" name="operation1"
								value="<%=CollegeCtl.OP_CANCEL%>">
								<%
									} else {
								%> <input type="submit" name="operation"
								value="<%=CollegeCtl.OP_RESET%>"> <%
 	}
 %>
							</td>
						</tr>
						</center>
					</table>
				

	</form>
	</div>
	<div >
 	<%@ include file="Footer.jsp"%>
 	</div>
 </body>
</html> --%>