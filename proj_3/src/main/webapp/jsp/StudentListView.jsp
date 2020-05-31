<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@page import="in.co.rays.proj3.controller.StudentListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.StudentDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<section class="content">

	<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.StudentDTO"
			scope="request"></jsp:useBean>

<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">Student List</h2>
					<div style="margin-bottom: -1%;">
				

						<%
							String emsg = ServletUtility.getErrorMessage(request);
							String emsg1 = ServletUtility.getSuccessMessage(request);
						%>
						<%
							if (!emsg.equalsIgnoreCase("") || !emsg1.equalsIgnoreCase("")) {
						%>

						<h2 align="center">
							<font color="green"><%=emsg1%></font>
						</h2>

						<h2 align="center">
							<font color="red"><%=emsg%></font>
						</h2>
						<table align="center">
							<tr>

								<td style="text-align: center;"><input type="submit"
									name="operation" value="<%=StudentListCtl.OP_BACK%>"></td>
							</tr>
						</table>

						<%
							} else {
						%>

						<h2>
							<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
						</h2>
						<h2 align="center">
							<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
						</h2>

						<%
							List l = (List) request.getAttribute("collegeList");
						%>

					</div>
				</div>
				<div class="card-body" style="text-align: center;">
				
					<div class="form-group">
						<div class="row">
						<label  class="col-md-1 mb-2"></label>
&emsp;
							<label for="firstName" class="col-md-0 mb-2"
								style="text-align: center;">First Name</label>
							<div class="col-md-2">
								<input type="text" name="firstName" class="form-control mb-2"
									id="firstName" placeholder="Enter User Name"
									value="<%=ServletUtility.getParameter("firstName", request)%>">
							</div>
&emsp;
							<label for="email" class="col-md-0 mb-2"
								style="text-align: center;">Email ID</label>
							<div class="col-md-2">
								<input type="text" name="email" class="form-control mb-2"
									id="loginid" placeholder="search by email id"
									value="<%=ServletUtility.getParameter("email", request)%>">
							</div>
							&emsp;
							<label for="collegeId" class="col-md-0 mb-2">College Name</label>
							<div class="col-md-2">
								<%=HtmlUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), l)%>
							</div>

							<div class="col-md-1">
								<input type="submit" class="btn text-white form-control mb-2"style=" background-color: black;"
									name="operation" value="<%=StudentListCtl.OP_SEARCH%>">
							</div>
						
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn text-white form-control mb-2"style=" background-color: black;"
									value="<%=StudentListCtl.OP_RESET%>">
							</div>
						</div>
				
				</div>
				</div>
				<table
					class="table table-bordered table-responsive-sm table-striped">
					<thead>
						<tr>
							<th style="width: 3cm">Select all <input type="checkbox"
								onclick="selectall(this)" id="chk"></th>
							<th>S No</th>
							<th>College Name</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Date Of Birth</th>
							<th>Mobile No</th>
							<th>Email ID</th>
							<th>Edit</th>
								<%-- <%
									if (RoleDTO.ADMIN == userBean.getRoleId()) {
								%> Edit<%
									}
								%>
							</th> --%>
						</tr>
					</thead>
					<tbody>
						<%
							int pageNo = ServletUtility.getPageNo(request);
								int pageSize = ServletUtility.getPageSize(request);
								int index = ((pageNo - 1) * pageSize) + 1;
								List list = ServletUtility.getList(request);
								int record = ServletUtility.getRecord(request);
								Iterator<StudentDTO> it = list.iterator();

								while (it.hasNext()) {

									StudentDTO bean1 = it.next();
						%>

						<tr align="center">

							<td><input type="checkbox" name="ids"
								value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
							<td><%=index++%></td>
							<td><%=bean1.getCollegeName()%></td>
							<td><%=bean1.getFirstName()%></td>
							<td><%=bean1.getLastName()%></td>
							<td><%=DataUtility.getDateString(bean1.getDob())%></td>
							<td><%=bean1.getMobileNo()%></td>
							<td><%=bean1.getEmail()%></td>
							<td>
								<%
									if (RoleDTO.ADMIN == userBean.getRoleId()) {
								%> <a href="StudentCtl?id=<%=bean1.getId()%>">Edit</a> <%
 	}
 %>
							</td>
						</tr>
						<%
							}
						%>


					</tbody>
				</table>


				<div class="d-flex justify-content-around mb-3">
					<div class="p-2 " style="align: center;">
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							<%if (!(pageNo > 1)) {%> disabled="disabled" <%}%>
							value="<%=StudentListCtl.OP_PREVIOUS%>">
					</div>
					<div class="p-2 ">
						<%
							if (!list.isEmpty()) {
						%>
						<%
							if (RoleDTO.ADMIN == userBean.getRoleId()) {
						%>
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							value="<%=StudentListCtl.OP_NEW%>">
						<%
							}
								}
						%>
					</div>

					<div class="p-2 ">
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							value="<%=StudentListCtl.OP_DELETE%>">
					</div>
					<div class="p-2 ">
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							value="<%=StudentListCtl.OP_NEXT%>"
							<%if (!(list.size() == Integer.parseInt(PropertyReader.getValue("page.size")))
						|| record == Integer.parseInt(PropertyReader.getValue("page.size"))) {%>
							disabled="disabled" <%}%>>


					</div>
				</div>
			</div>


			<input type="hidden" name="record" value="<%=record%>"> <input
				type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">



			<%
				}
			%>
		</div>
	</form>

</section>
<%@ include file="Footer.jsp"%>