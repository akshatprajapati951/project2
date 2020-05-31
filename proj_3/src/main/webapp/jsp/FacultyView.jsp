<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj3.controller.FacultyCtl"%>

<%@page import="in.co.rays.proj3.util.*"%>
<%@page import="in.co.rays.proj3.dto.FacultyDTO"%>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">
		<form action="<%=ORSView.FACULTY_CTL%>" method="post">
			
			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.FacultyDTO"
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
			<div style="margin-bottom: -2%;">
				<%
					if (dto.getId() == 0) {
				%>
				<h2 style="text-align: center;">Add Faculty</h2>

				<%
					} else {
				%><h2 style="text-align: center;">Update Faculty</h2>
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
						<div class="col-sm-3">College Name</div>
						<div class="col-sm-5">
							<%
								List l = (List) request.getAttribute("collegeList");
							%>

							<%=HtmlUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), l)%>
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("collegeId", request)%></font>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-3">Subject Name</div>
						<div class="col-sm-5">
							<%
							List l2 = (List) request.getAttribute("subjectList");
							%>

							<%=HtmlUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), l2)%>
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("subjectId", request)%></font>
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
						<div class="col-sm-3">Email Id</div>
						<div class="col-sm-5">
							<input type="text" class="form-control mb-2 mr-sm-2" id="login"
								value="<%=DataUtility.getStringData(dto.getLogin())%>"
								<%=(dto.getId() > 0) ? "readonly" : ""%> name="login"
								placeholder=" enter Email ID">
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-3">Date of Birth</div>
						<div class="col-sm-5">
							<input type="text" class="form-control mb-2 mr-sm-2"
								readonly="readonly" id="datepicker"
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

							<%
								if (dto.getId() > 0) {
							%>


							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=FacultyCtl.OP_UPDATE%>">
							</div>
							<%
								} else {
							%>
							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=FacultyCtl.OP_SAVE%>">
							</div>
							<%
								}
							%>
							<%
								if (dto.getId() > 0) {
							%><div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=FacultyCtl.OP_CANCEL%>">
							</div>
							<%
								} else {
							%>
							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=FacultyCtl.OP_RESET%>">
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
