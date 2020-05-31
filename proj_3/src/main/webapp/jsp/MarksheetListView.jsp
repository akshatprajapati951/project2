<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@page import="in.co.rays.proj3.controller.MarksheetListCtl"%>
<%@page import="in.co.rays.proj3.controller.BaseCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<section class="content">

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
			<jsp:useBean id="dto"
				class="in.co.rays.proj3.dto.MarksheetDTO" scope="request"></jsp:useBean>


<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">Marksheet List</h2>
					<div style="margin-bottom: -1%;">
				
	
			<%
				String emsg = ServletUtility.getErrorMessage(request);
			    String emsg1=ServletUtility.getSuccessMessage(request);
			%>
			<%
				if (!emsg.equalsIgnoreCase("")||!emsg1.equalsIgnoreCase("")) {
					
			%>
      <h2 align="center">
				<font color="green"><%=emsg1%></font>
			</h2>
			<h2 align="center">
				<font color="red"><%=emsg%></font>
			</h2>
			
		
				<table align="center">
					<tr>
					<td style="text-align: center;"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_BACK%>"></td>

				</tr>
			</table>
			<%
				} else {
			%>

			

				<h2 align="center">
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h2>



				<h2 align="center">
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h2>
			</div>
			</div>


			<%
			    List l1=(List) request.getAttribute("marksheetList");
				List l = (List) request.getAttribute("roleList");
			%>

			<div class="card-body" style="text-align: center;">
					<div class="form-group">
						<div class="row">
						<label  class="col-md-1 mb-2"></label>
						<label for="rollId" class="col-md-0 mb-2"
						style="text-align: right;">Student Name</label>
							<div class="col-md-3">
								<%=HtmlUtility.getList("rollId", String.valueOf(dto.getStudentId()), l)%>
							</div>
							&emsp;
						<label for="firstName" class="col-md-0 mb-2"
								style="text-align: right;">Roll No</label>
							<div class="col-md-3">
								<input type="text" name="rollNo" class="form-control mb-2"
									id="rollNo" placeholder="search by rollNo"
									value="<%=ServletUtility.getParameter("rollNo", request)%>">
							</div>
						 <div class="col-md-1">
								<input type="submit" class="btn text-white form-control mb-2"style=" background-color: black;"
									name="operation" value="<%=MarksheetListCtl.OP_SEARCH%>">
							</div>
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn text-white form-control mb-2"style=" background-color: black;"
									value="<%=MarksheetListCtl.OP_RESET%>">
			                </div>
			                </div>
			                </div>
			                </div>
			                
			                <table class="table table-bordered table-responsive-sm table-striped ">
			<thead>
					<tr>
					<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk" ></th>
						<th>S No</th>
						<th>RollNo</th>
						<th>Name</th>
						<th>Physics</th>
						<th>Chemistry</th>
						<th>Maths</th>
						<th>Result</th>
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
							Iterator<MarksheetDTO> it = list.iterator();

							while (it.hasNext()) {

								MarksheetDTO bean1 = it.next();
								String result = "";
								if (bean1.getPhysics() > 30 && bean1.getChemistry() > 30 && bean1.getMaths() > 30) {
									result = "pass";
								} else {
									result = "fail";
								}
					%>

					<tr align="center">
						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getRollNo()%></td>
						<td><%=bean1.getName()%></td>
						<td><%=bean1.getPhysics()%></td>
						<td><%=bean1.getChemistry()%></td>
						<td><%=bean1.getMaths()%></td>
						<td><%=result%></td>
						<td><a href="MarksheetCtl?id=<%=bean1.getId()%>">Edit</a></td>
					</tr>

					<%
						}
					%>
					</tbody>
					</table>
					
					<div class="d-flex justify-content-around mb-3">
				<div class="p-2 " style="align: center;">
					<input type="submit" name="operation"class="btn  text-white"style=" background-color: black;"
							 <%if (!(pageNo > 1)) {%>
							disabled="disabled" <%}%>
							value="<%=MarksheetListCtl.OP_PREVIOUS%>">
							</div>
							<div class="p-2 ">
							 <%
								if (!list.isEmpty()) {
							%> <%
 	if (RoleDTO.ADMIN == userBean.getRoleId()) {
 %> <input type="submit" name="operation" class="btn  text-white"style=" background-color: black;"
							value="<%=MarksheetListCtl.OP_NEW%>">
							<%} }%>
							</div>
							
							<div class="p-2">
							<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=MarksheetListCtl.OP_DELETE%>">
				</div>
				<div class="p-2 ">
					<input type="submit" name="operation" class="btn  text-white"style=" background-color: black;"
						value="<%=MarksheetListCtl.OP_NEXT%>"
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
						
					<%-- <tr>
						<td align="center"><label>Student Name : </label> <%=HtmlUtility.getList("rollId", String.valueOf(bean.getStudentId()), l)%>
							&emsp;<label>Roll No : </label> <input
							type="text" style = "width: 200px; height: 20px;" name="rollNo" placeholder="search by rollNo" 						
							value="<%=ServletUtility.getParameter("rollNo", request)%>">
							&emsp;&emsp;<input type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_SEARCH%>"> &emsp;<input
							type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_RESET%>"></td>
					</tr>
				</table>
				<br>

				<table border="1" width="100%">

					
					<%
						if (!ServletUtility.getErrorMessage(request).contains("No")) {
					%>
					<tr>
						<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk"></th>
						<th>S No</th>
						<th>RollNo</th>
						<th>Name</th>
						<th>Physics</th>
						<th>Chemistry</th>
						<th>Maths</th>
						<th>Result</th>
						<th>Edit</th>
					</tr>
					<%
						}
					%>
					<%
						int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;

							List list = ServletUtility.getList(request);
							int record = ServletUtility.getRecord(request);
							Iterator<MarksheetDTO> it = list.iterator();

							while (it.hasNext()) {

								MarksheetDTO bean1 = it.next();
								String result = "";
								if (bean1.getPhysics() > 30 && bean1.getChemistry() > 30 && bean1.getMaths() > 30) {
									result = "pass";
								} else {
									result = "fail";
								}
					%>

					<tr align="center">
						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getRollNo()%></td>
						<td><%=bean1.getName()%></td>
						<td><%=bean1.getPhysics()%></td>
						<td><%=bean1.getChemistry()%></td>
						<td><%=bean1.getMaths()%></td>
						<td><%=result%></td>
						<td><a href="MarksheetCtl?id=<%=bean1.getId()%>">Edit</a></td>
					</tr>

					<%
						}
					%>
				</table>
				<br>
				<table width="100%">
					<tr>
						<td align="left"><input type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_PREVIOUS%>"
							<%if (!(pageNo > 1)) {%> disabled="disabled" <%}%>>
						<td align="center">
							<%
								if (!list.isEmpty()) {
							%> <%
 	if (RoleDTO.ADMIN == userBean.getRoleId()) {
 %><input type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_NEW%>">
						</td>
						<td align="center"><input type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_DELETE%>"> <%
 	}
 		}
 %></td>
						</td>
						<td align="right"><input type="submit" name="operation"
							value="<%=MarksheetListCtl.OP_NEXT%>"
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
		<%@include file="Footer.jsp"%>
	</div>
</body>
</html> --%>