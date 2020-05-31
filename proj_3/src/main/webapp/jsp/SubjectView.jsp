<%@page import="in.co.rays.proj3.controller.SubjectCtl"%>
<%@page import="java.util.HashMap"%>

<%@page import="in.co.rays.proj3.controller.SubjectCtl"%>
<%@page import="in.co.rays.proj3.util.*"%>
<%@page import="in.co.rays.proj3.dto.SubjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">
	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.SubjectDTO"
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
				<div style="margin-bottom: -2%;">				<%
					if (dto.getId() == 0) {
				%>
				<h2 style="text-align: center;">Add Subject</h2>

				<%
					} else {
				%><h2 style="text-align: center;">Update Subject</h2>
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
						<div class="col-sm-3">Subject Name</div>
						<div class="col-sm-5">
							<input type="text" class="form-control mb-2 mr-sm-2" id="Name"
								value="<%=DataUtility.getStringData(dto.getName())%>"
								name="Name" placeholder="enter subject Name">
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("Name", request)%></font>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-3">Course Name</div>
						<div class="col-sm-5">
							<%
								List l = (List) request.getAttribute("courseList");
							%>

							<%=HtmlUtility.getList("courseId", String.valueOf(dto.getCourseId()), l)%>
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("courseId", request)%></font>
						</div>
					</div>
				</div>


				<div class="form-group">
					<div class="row">
						<div class="col-sm-3">Subject Description</div>
						<div class="col-sm-5">
							<textarea rows="2" cols="23" maxlength="50" name="Desc"
								class='form-control' placeholder="enter Description"
								style="font: status-bar; resize: none;"><%=DataUtility.getStringData(dto.getDescription())%></textarea>

						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("Desc", request)%></font>
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
								value="<%=SubjectCtl.OP_UPDATE%>">
						</div>
						<%
							} else {
						%>
						<div class="p-2">
							<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
								value="<%=SubjectCtl.OP_SAVE%>">
						</div>
						<%
							}
						%>
						<%
							if (dto.getId() > 0) {
						%><div class="p-2">
							<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
								value="<%=SubjectCtl.OP_CANCEL%>">
						</div>
						<%
							} else {
						%>
						<div class="p-2">
							<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
								value="<%=SubjectCtl.OP_RESET%>">
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
							<th align="left">Subject Name <font style="color: red">*</font></th>
							<td><input type="text" name="Name"
								placeholder="enter Subject name" style="font: status-bar;"
								value="<%=DataUtility.getStringData(dto.getName())%>"><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("Name", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Course <font style="color: red">*</font></th>
							<td><%=HtmlUtility.getList("courseId", String.valueOf(dto.getCourseId()), l)%>
								<font color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
						</tr>
						<tr>
							<th align="left">Subject Description <font style="color: red">*</font></th>
							<td><textarea rows="2" cols="23" maxlength="50" name="Desc"
									placeholder="enter Description"
									style="font: status-bar; resize: none;"><%=DataUtility.getStringData(dto.getDescription())%></textarea><font
								color="red" style="font-size: small;"> <%=ServletUtility.getErrorMessage("Desc", request)%></font></td>
						</tr>
						<tr  height="40px">
							<td></td>
							<td>
								<%
									if (dto.getId() > 0) {
								%>&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="operation"
								value="<%=SubjectCtl.OP_UPDATE%>">&nbsp;&nbsp;&nbsp;&nbsp;
								<%
									} else {
								%>&nbsp;&nbsp;&nbsp;<input type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE%>">&nbsp;&nbsp;&nbsp;<%
									}
									if (dto.getId() > 0) {
								%><input type="submit" name="operation1"
								value="<%=SubjectCtl.OP_CANCEL%>"> <%
 	} else {
 %> <input type="submit" name="operation"
								value="<%=SubjectCtl.OP_RESET%>"> <%
 	}
 %>
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
</html> --%>