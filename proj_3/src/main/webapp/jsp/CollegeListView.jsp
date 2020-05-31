<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj3.controller.CollegeListCtl"%>
<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.CollegeDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<section class="content">


		<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="post">


			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.CollegeDTO"
				scope="request"></jsp:useBean>
		
		<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">College List</h2>
					<div style="margin-bottom: -1%;">
				
				
			<%
				List l = (List) request.getAttribute("collegeList");

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
						value="<%=CollegeListCtl.OP_BACK%>"></td>
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
			</div>
			</div>


			<div class="card-body" style="text-align: center;">
			
					<div class="form-group">
						<div class="row">
						&emsp;&emsp;
						<label  class="col-md-1 mb-2"></label>
						<label for="collegeId" class="col-md-0 mb-2"
						style="text-align: left;">College Name</label>
							<div class="col-md-3">
								<%=HtmlUtility.getList("Id", String.valueOf(dto.getId()), l)%>
							</div>
							&emsp;&emsp;&emsp;
                         <label for="city" class="col-md-0 mb-2"
								style="text-align: left;">City</label>
							<div class="col-md-3">
								<input type="text" name="city" class="form-control mb-2"
									id="city" placeholder="Enter User Name"
									value="<%=ServletUtility.getParameter("city", request)%>">
							</div>
							
							 <div class="col-md-1">
								<input type="submit" class="btn text-white form-control mb-2"style=" background-color: black;"
									name="operation" value="<%=CollegeListCtl.OP_SEARCH%>">
							</div>
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn text-white form-control mb-2"style=" background-color: black;"
									value="<%=CollegeListCtl.OP_RESET%>">
			                </div>
			                </div>
			                </div>
			                </div>
			                
			                 <table class="table table-bordered table-responsive-sm table-striped">
			<thead>
					<tr>
					<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk"></th>
						<th>S No</th>
						<th>Name</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>PhoneNo</th>
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

							Iterator<CollegeDTO> it = list.iterator();

							while (it.hasNext()) {

								CollegeDTO bean1 = it.next();
					%>

					<tr align="center">
						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getName()%></td>
						<td><%=bean1.getAddress()%></td>
						<td><%=bean1.getState()%></td>
						<td><%=bean1.getCity()%></td>
						<td><%=bean1.getPhoneNo()%></td>
						<td>
							<%
								if (RoleDTO.ADMIN == userBean.getRoleId()) {
							%> <a href="CollegeCtl?id=<%=bean1.getId()%>">Edit</a> <%
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
					<input type="submit" name="operation"class="btn text-white"style=" background-color: black;"
							 <%if (!(pageNo > 1)) {%>
							disabled="disabled" <%}%>
							value="<%=CollegeListCtl.OP_PREVIOUS%>">
							</div>
							<div class="p-2 ">
							 <%
								if (!list.isEmpty()) {
							%>  <input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
							value="<%=CollegeListCtl.OP_NEW%>">
							<%} %>
							</div>
							
							<div class="p-2">
							<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=CollegeListCtl.OP_DELETE%>">
				</div>
				<div class="p-2">
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=CollegeListCtl.OP_NEXT%>"
						
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
	
			</form>
</section>
<%@ include file="Footer.jsp"%>	
						
	<%-- 
					
						<td align="center"><label>College Name : </label><%=HtmlUtility.getList("Id", String.valueOf(dto.getId()), l)%>
							&emsp; <label>City :</label> <input type="text"  style = "width: 200px; height: 25px;" name="city"
							placeholder="search by city"
							value="<%=ServletUtility.getParameter("city", request)%>">
							&emsp; <input type="submit" name="operation"
							value="<%=CollegeListCtl.OP_SEARCH%>"> &emsp;<input
							type="submit" name="operation"
							value="<%=CollegeListCtl.OP_RESET%>"></td>
					</tr>
				</table>
				<br>

				<table border="1" width="100%">

					<tr>
						<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk"></th>
						<th>S No</th>
						<th>Name</th>
						<th>Address</th>
						<th>State</th>
						<th>City</th>
						<th>PhoneNo</th>
						<th>Edit</th>
					</tr>
					   
					<%
						int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;

							List list = ServletUtility.getList(request);
							int record = ServletUtility.getRecord(request);

							Iterator<CollegeDTO> it = list.iterator();

							while (it.hasNext()) {

								CollegeDTO bean1 = it.next();
					%>
					
					
					
					<tr align="center">
						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getName()%></td>
						<td><%=bean1.getAddress()%></td>
						<td><%=bean1.getState()%></td>
						<td><%=bean1.getCity()%></td>
						<td><%=bean1.getPhoneNo()%></td>
						<td>
							<%
								if (RoleDTO.ADMIN == userBean.getRoleId()) {
							%> <a href="CollegeCtl?id=<%=bean1.getId()%>">Edit</a> <%
 	}
 %>
						</td>
					</tr>
					<%
						}
					%>


				</table>
				<br>
				<table width="100%">
					<tr>
						<td align="left"><input type="submit" name="operation"
							value="<%=CollegeListCtl.OP_PREVIOUS%>" <%if (!(pageNo > 1)) {%>
							disabled="disabled" <%}%>>
						<td align="center">
							<%
								if (!list.isEmpty()) {
							%><input type="submit" name="operation"
							value="<%=CollegeListCtl.OP_NEW%>">
						</td>
						<td align="center"><input type="submit" name="operation"
							value="<%=CollegeListCtl.OP_DELETE%>"></td>
						<%
							}
						%>
						</td>

						<td align="right"><input type="submit" name="operation"
							value="<%=CollegeListCtl.OP_NEXT%>"
							<%if (!(list.size() == Integer.parseInt(PropertyReader.getValue("page.size")))
						|| record == Integer.parseInt(PropertyReader.getValue("page.size"))) {%>
							disabled="disabled" <%}%>></td>
					</tr>
				</table>
				<input type="hidden" name="record" value="<%=record%>"> <input
					type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
		<%
			}
		%>
	</div>
	</center>
	<div>
		<%@ include file="Footer.jsp"%>
	</div>
</body>
</html> --%>