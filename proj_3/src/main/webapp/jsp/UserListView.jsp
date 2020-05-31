<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@page import="in.co.rays.proj3.controller.RoleCtl"%>
<%@page import="java.util.Date"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.DataValidator"%>
<%@page import="in.co.rays.proj3.controller.UserListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

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
<%@include file="Header.jsp"%>

 <section class="content">
	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>

		<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">User List</h2>
					<div style="margin-bottom: 1%;">
					<div style="margin-top: -2%;">
				

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
						<br> <br>
						<table align="center">
							<tr>
							
								<td style="text-align: center;">
							<input type="submit" name="operation"
									value="<%=UserListCtl.OP_BACK%>"></td>

						</tr>
						</table>
						
						<%
							} else {
						%>

						<div id="error">

							<h2 align="center">
								<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
							</h2>



							<h2 align="center">
								<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
							</h2>
						</div>

						<%
							List l = (List) request.getAttribute("roleList");
						%>

					</div>
					</div>
				</div>
				<div class="card-body" style="text-align: center;">
				
					<div class="form-group">
						<div class="row">
					
						<label  class="col-md-1 mb-2"></label>

							<label for="firstName" class="col-md-0 mb-2"
								style="align: center;">     First Name</label>
							<div class="col-md-2">
								<input type="text" name="firstName" class="form-control mb-2"
									id="firstName" placeholder="Enter User Name"
									value="<%=ServletUtility.getParameter("firstName", request)%>">
							</div>
&emsp;
							<label for="login" class="col-md-0 mb-2"
								style="text-align: center;">Email ID</label>
							<div class="col-md-2">
								<input type="text" name="login" class="form-control mb-2"
									id="loginid" placeholder="search by email id"
									value="<%=ServletUtility.getParameter("login", request)%>">
							</div>
							&emsp;
							<label for="roleId" class="col-md-0 mb-2">Role Name </label>
							<div class="col-md-2">
								<%=HtmlUtility.getList("roleId", String.valueOf(dto.getRoleId()), l)%>
							</div>
							&emsp;
							<div class="col-md-1">
								<input type="submit" class="btn text-white form-control mb-3"style=" background-color: black;"
									name="operation" value="<%=UserListCtl.OP_SEARCH%>">
							</div>
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn text-white form-control mb-2"style=" background-color: black;"
									value="<%=UserListCtl.OP_RESET%>">
							</div>
						</div>
					</div>
				
				</div>
				<table
					class="table table-bordered table-responsive-sm table-striped table-thead-dark">
					<thead class=thead-info>
						<tr>
							<th style="width: 4cm">Select all <input type="checkbox"
								onclick="selectall(this)" id="chk"></th>
							<th>S No</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>LoginId</th>
							<th>Gender</th>
							<th>DOB</th>
							<th>Role</th>
							<th>Edit</th>
						</tr>
					</thead>
					<tbody>

						<%
							int pageNo = ServletUtility.getPageNo(request);
								int pageSize = ServletUtility.getPageSize(request);
								int index = ((pageNo - 1) * pageSize) + 1;
								List list = ServletUtility.getList(request);
								int record = ServletUtility.getRecord(request);
								Iterator<UserDTO> it = list.iterator();
								while (it.hasNext()) {
									UserDTO bean1 = it.next();
						%>
						<td>
							<%
								if (bean1.getRoleId() != RoleDTO.ADMIN) {
							%> <input type="checkbox" name="ids" value="<%=bean1.getId()%>"
							onchange="unchk(this)"> <%
 	}
 %>
						</td>


						</td>
						<td><%=index++%></td>
						<td><%=bean1.getFirstName()%></td>
						<td><%=bean1.getLastName()%></td>
						<td><%=bean1.getLogin()%></td>
						<td><%=bean1.getGender()%></td>
						<td><%=DataUtility.getDateString(bean1.getDob())%></td>
						<td><%=DataUtility.getStringData(bean1.getRoleName())%></td>
						<td>
							<%
								
										if (bean1.getRoleId() != RoleDTO.ADMIN) {
							%> <a href="UserCtl?id=<%=bean1.getId()%>">Edit</a> <%
 	}
 %>
						</td>
						</tr>

						<%
							}
						%>
					</tbody>
				</table>
				<div class="d-flex justify-content-around mb-2">
					<div class="p-2 " style="align: center;">
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							<%if (!(pageNo > 1)) {%> disabled="disabled" <%}%>
							value="<%=UserListCtl.OP_PREVIOUS%>">
					</div>
					<div class="p-2 ">
						<%
							if (!list.isEmpty()) {
						%>
						<input type="submit" name="operation" class="btn text-white" style=" background-color: black;"
							value="<%=UserListCtl.OP_NEW%>">
						<%
							}
						%>
					</div>

					<div class="p-2 ">
						<input type="submit" name="operation" class="btn text-white" style=" background-color: black;"
							value="<%=UserListCtl.OP_DELETE%>">
					</div>
					<div class="p-2 ">
						<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							value="<%=UserListCtl.OP_NEXT%>"
							<%if (!(list.size() == Integer.parseInt(PropertyReader.getValue("page.size")))
						|| record == Integer.parseInt(PropertyReader.getValue("page.size"))) {%>
							disabled="disabled" <%}%>>


					</div>
				</div>
			</div>
			<input type="hidden" name="record" value="<%=record%>"> <input
				type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<%
							}
			%>
		</div>
	</form>
</section>
<%@ include file="Footer.jsp"%>

