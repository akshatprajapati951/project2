<%@page import="in.co.rays.proj3.util.DataValidator"%>
<%@page import="in.co.rays.proj3.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<section class="content">
	<div class="container">

		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.MarksheetDTO"
				scope="request"></jsp:useBean>

			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

			<%
				
			%>

		<div class="card text-center"
				style="margin: 20 auto; float: none; max-width: 40rem;">
				<div class="card-header  text-white" style=" background-color: black;">
				<div style="margin-bottom: -2%;">
					<%
						if (dto.getId() == 0) {
					%>
					<h2 style="text-align: center;">Add Marksheet</h2>

					<%
						} else {
					%><h2 style="text-align: center;">Update Marksheet</h2>
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
							<div class="col-sm-3">Student Rollno</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" id="rollNo"
									value="<%=DataUtility.getStringData(dto.getRollNo())%>"
									name="rollNo" placeholder=" enter student's rollNo">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Student Name</div>
							<div class="col-sm-5">
								<%
									List l = (List) request.getAttribute("studentList");
								%>

								<%=HtmlUtility.getList("studentId", String.valueOf(dto.getStudentId()), l)%>
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("studentId", request)%></font>
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Physics Marks</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									id="physics" name="physics" placeholder=" enter Physics Marks"
									<%if (DataUtility.getStringData(dto.getPhysics()).equals("0")) {%>
									value="<%=DataUtility.getStringData("")%>" <%} else {%>
									value="<%=DataUtility.getStringData(dto.getPhysics())%>" <%}%>>

							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("physics", request)%></font>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Chemistry Marks</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									id="chemistry" name="chemistry"
									placeholder=" enter chemistry Marks"
									<%if (DataUtility.getStringData(dto.getChemistry()).equals("0")) {%>
									value="<%=DataUtility.getStringData("")%>" <%} else {%>
									value="<%=DataUtility.getStringData(dto.getChemistry())%>"
									<%}%>>

							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("chemistry", request)%></font>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Maths Marks</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" id="maths"
									name="maths" placeholder=" enter Physics Marks"
									<%if (DataUtility.getStringData(dto.getMaths()).equals("0")) {%>
									value="<%=DataUtility.getStringData("")%>" <%} else {%>
									value="<%=DataUtility.getStringData(dto.getMaths())%>" <%}%>>

							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("maths", request)%></font>
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
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=MarksheetCtl.OP_UPDATE%>">
								</div>
								<%
									} else {
								%>
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=MarksheetCtl.OP_SAVE%>">
								</div>
								<%
									}
								%>
								<%
									if (dto.getId() > 0) {
								%><div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=MarksheetCtl.OP_CANCEL%>">
								</div>
								<%
									} else {
								%>
								<div class="p-2">
									<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
										value="<%=MarksheetCtl.OP_RESET%>">
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
							<th align="left">Student Rollno <font style="color: red">*</font></th>
							<td><input type="text" name="rollNo"
								placeholder="enter student's rollNo" maxlength="8"
								style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getRollNo())%>"
								<%=(dto.getId() > 0) ? "readonly" : ""%>> <font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Select Name <font style="color: red">*</font></th>
							<td><%=HtmlUtility.getList("studentId", String.valueOf(dto.getStudentId()), l)%>
								<font color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Physics Marks <font style="color: red">*</font></th>
							<td><input type="text" name="physics"
								placeholder="enter physics marks" style="font: status-bar;"
								<%if (DataUtility.getStringData(dto.getPhysics()).equals("0")) {%>
								value="<%=DataUtility.getStringData("")%>" <%} else {%>
								value="<%=DataUtility.getStringData(dto.getPhysics())%>" <%}%>><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Chemistry Marks <font style="color: red">*</font></th>
							<td><input type="text" name="chemistry"
								placeholder="enter chemistry marks" style="font: status-bar;"
								<%if (DataUtility.getStringData(dto.getChemistry()).equals("0")) {%>
								value="<%=DataUtility.getStringData("")%>" <%} else {%>
								value="<%=DataUtility.getStringData(dto.getChemistry())%>"
								<%}%>><font color="red" style="font-size: small;">
									<%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Maths Marks <font style="color: red">*</font></th>
							<td><input type="text" name="maths"
								placeholder="enter maths marks" style="font: status-bar;"
								<%if (DataUtility.getStringData(dto.getMaths()).equals("0")) {%>
								value="<%=DataUtility.getStringData("")%>" <%} else {%>
								value="<%=DataUtility.getStringData(dto.getMaths())%>" <%}%>><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

						</tr>
						<td ></td>
						<tr></tr>
						<td></td>
						<td> <%
 	if (!(dto.getId() > 0)) {
 %> &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="operation"
							value="<%=MarksheetCtl.OP_SAVE%>">&nbsp;
							&nbsp;<%
								} else {
							%><input type="submit" name="operation"
							value="<%=MarksheetCtl.OP_UPDATE%>">&nbsp; &nbsp;
							&nbsp; <%
 	}
 %> <%
 	if (dto.getId() > 0) {
 %><input type="submit" name="operation1"
							value="<%=MarksheetCtl.OP_CANCEL%>"> <%
 	} else {
 %> <input type="submit" name="operation"
							value="<%=MarksheetCtl.OP_RESET%>"> <%
 	}
 %>
						</td>
</center>
					</table>
				
	
	</form>
</div>
	<div >
 	<%@ include file="Footer.jsp"%>
 	</div>
 </body>
</html> --%>