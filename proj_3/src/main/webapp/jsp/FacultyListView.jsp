<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@page import="in.co.rays.proj3.controller.FacultyListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.FacultyDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@include file="Header.jsp"%>

<section class="content">

<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

	<jsp:useBean id="dto" class="in.co.rays.proj3.dto.FacultyDTO"
		scope="request"></jsp:useBean>
		


		<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">Faculty List</h2>
					<div style="margin-bottom: -1%;">
				
	<%
	List l = (List) request.getAttribute("collegeList");
	String emsg=ServletUtility.getErrorMessage(request);
	String emsg1=ServletUtility.getSuccessMessage(request);
	
	%>
	<%
	if(!emsg.equalsIgnoreCase("")||!emsg1.equalsIgnoreCase("")){
	
	%>
	<h2 align="center">
				<font color="green"><%=emsg1%></font>
			</h2>
<h2 align="center">
				<font color="red"><%=emsg%></font>
			</h2>
			
			<table align="center">
				<tr align="center">
					
					<td align="center"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_BACK%>"></td>
</tr>
</table>
			
			<%
			
			}else{
				
			%>
			
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>
			<h3 align="center">
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
		</div>
		</div>
		
		
		<div class="card-body" style="text-align: center;">
					<div class="form-group">
						<div class="row">
						<label  class="col-md-1 mb-2"></label>
						&emsp;
							<label for="firstName" class="col-md-0 mb-2"
								style="text-align: center;">Faculty Name</label>
							<div class="col-md-2">
								<input type="text" name="firstName" class="form-control mb-2"
									id="firstName" placeholder="Enter Faculty Name"
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
                               		<label for="collegeId" class="col-md-0 mb-2">College Name</label>
							<div class="col-md-2">
								<%=HtmlUtility.getList("collegeId", String.valueOf(dto.getCollegeId()), l)%>
							</div>
			                <div class="col-md-1">
								<input type="submit" class="btn btn-info form-control mb-2"style=" background-color: black;"
									name="operation" value="<%=FacultyListCtl.OP_SEARCH%>">
							</div>
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn btn-info form-control mb-2"style=" background-color: black;"
									value="<%=FacultyListCtl.OP_RESET%>">
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
						<th>College Name</th>
						<!-- <th>Course</th> -->
						<th>Subject</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Date Of Birth</th>
						<th>Mobile No</th>
						<th>Login Id</th>
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
						Iterator<FacultyDTO> it = list.iterator();

						while (it.hasNext()) {

							FacultyDTO bean1 = it.next();
					%>
					<tr>

                     
						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getCollegeName()%></td>
						<%-- <td><%=bean1.getName()%></td> --%> 
						<td><%=bean1.getSubjectName()%></td>
						<td><%=bean1.getFirstName()%></td>
						<td><%=bean1.getLastName()%></td>
						<td><%=DataUtility.getDateString(bean1.getDob())%></td>
						<td><%=bean1.getMobileNo()%></td>
						<td><%=bean1.getLogin()%></td>
						<td><a href="FacultyCtl?id=<%=bean1.getId()%>">Edit</a></td>
					</tr>
					<%
						}
					%>
					</tbody>
					</table>
					
					<div class="d-flex justify-content-around mb-3">
				<div class="p-2 " style="align: center;">
					<input type="submit" name="operation"class="btn text-white "style=" background-color: black;"
							 <%if (!(pageNo > 1)) {%>
							disabled="disabled" <%}%>
							value="<%=FacultyListCtl.OP_PREVIOUS%>">
							</div>
							<div class="p-2 ">
							 <%
								if (!list.isEmpty()) {
							%> <%
 	if (RoleDTO.ADMIN == userBean.getRoleId()) {
 %> <input type="submit" name="operation" class="btn text-white "style=" background-color: black;"
							value="<%=FacultyListCtl.OP_NEW%>">
							<%} }%>
							</div>
							
							<div class="p-2">
							<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=FacultyListCtl.OP_DELETE%>">
				</div>
				<div class="p-2">
					<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=FacultyListCtl.OP_NEXT%>"
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
					