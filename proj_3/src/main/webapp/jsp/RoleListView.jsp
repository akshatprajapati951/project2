<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.rays.proj3.controller.RoleListCtl"%>
<%@page import="in.co.rays.proj3.controller.BaseCtl"%>
<%@page import="in.co.rays.proj3.util.HtmlUtility"%>

<%@page import="in.co.rays.proj3.dto.RoleDTO"%>
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
<form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">

	<jsp:useBean id="dto" class="in.co.rays.proj3.dto.RoleDTO"
		scope="request"></jsp:useBean>

	<div class="container-fluid">
			<div class="card align-center"
			style="margin:1rem ; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">Role List</h2>
					<div style="margin-bottom: -1%;">

					<%
						List l = (List) request.getAttribute("roleList");

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
						<tr align="center">
							<th></th>
							<td align="center"><input type="submit" name="operation"
								value="<%=RoleListCtl.OP_BACK%>"></td>

						</tr>
					</table>
					<%
						} else {
					%>

					<h3>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					</h3>
					<h3 align="center">
						<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h3>
				</div>
			</div>

			<div class="card-body" style="text-align:left;">
				<div class="form-group">
					<div class="row">
					<label for="roleId" class="col-md-3 mb-2"></label>
					&emsp;&emsp;
					
						<label for="roleId" class="col-md-0 mb-2"
							style="text-align:left;">Role Name</label>
						<div class="col-md-2">
							<%=HtmlUtility.getList("roleId", String.valueOf(dto.getId()), l)%>
						</div>
						<div class="col-md-1">
							<input type="submit" class="btn text-white form-control mb-2"style=" background-color: black;"
								name="operation" value="<%=RoleListCtl.OP_SEARCH%>">
						</div>
						<div class="col-md-1">
							<input type="submit" name="operation"
								class="btn text-white form-control mb-2"style=" background-color: black;"
								value="<%=RoleListCtl.OP_RESET%>">
						</div>
					</div>
				</div>
			</div>

			<table class="table table-bordered table-responsive-sm table-striped">
				<thead>
					<tr>
						<%
							if (!ServletUtility.getErrorMessage(request).contains("No")) {
						%>

						<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk"></th>
						<th>S.No</th>
						<th>Id</th>
						<th>Name</th>
						<th>Description</th>
						<th>Edit</th>
					</tr>
					<%
						}
					%>
				</thead>
				<tbody>

					<%
						int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;

							List list = ServletUtility.getList(request);
							int record = ServletUtility.getRecord(request);
							Iterator<RoleDTO> it = list.iterator();
							while (it.hasNext()) {
								RoleDTO bean1 = it.next();
					%>

					<td>
						<%
							if (bean1.getId() != RoleDTO.ADMIN) {
						%> <input type="checkbox" name="ids" value="<%=bean1.getId()%>"
						onchange="unchk(this)"> <%
 	}
 %>
					</td>
					<td><%=index++%></td>
					<td><%=bean1.getId()%></td>
					<td><%=bean1.getName()%></td>
					<td><%=bean1.getDescription()%></td>
					<td>
						<%
							if (bean1.getId() != RoleDTO.ADMIN) {
						%> <a href="RoleCtl?id=<%=bean1.getId()%>">Edit</a>
					</td>
					<%
						}
					%>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>


			<div class="d-flex justify-content-around mb-3">
				<div class="p-2" style="align: center;">
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						<%if (!(pageNo > 1)) {%> disabled="disabled" <%}%>
						value="<%=RoleListCtl.OP_PREVIOUS%>">
				</div>
				<div class="p-2 ">
					<%
						if (!list.isEmpty()) {
					%>
					<%
						if (RoleDTO.ADMIN == userBean.getRoleId()) {
					%>
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=RoleListCtl.OP_NEW%>">
					<%
						}
							}
					%>
				</div>

				<div class="p-2 ">
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=RoleListCtl.OP_DELETE%>">
				</div>
				<div class="p-2 ">
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=RoleListCtl.OP_NEXT%>"
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


