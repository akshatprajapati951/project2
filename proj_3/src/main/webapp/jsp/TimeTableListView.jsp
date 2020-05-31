<%@page import="in.co.rays.proj3.util.HtmlUtility"%>
<%@page import="in.co.rays.proj3.util.PropertyReader"%>
<%@page import="in.co.rays.proj3.controller.TimeTableListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.TimeTableDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Date"%>
<%@include file="Header.jsp"%>

<section class="content">

	<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.TimeTableDTO"
			scope="request"></jsp:useBean>


		<div class="container-fluid">
			<div class="card align-center" style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "
					style="background-color: black;">
					<h2 style="text-align: center;">TimeTable List</h2>
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
						<br> <br>
						<table align="center">
							<tr align="center">
								<th></th>
								<td align="center"><input type="submit" name="operation"
									value="<%=TimeTableListCtl.OP_BACK%>"></td>

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




				<%
					List l1 = (List) request.getAttribute("courseList");
						List l = (List) request.getAttribute("subjectList");
				%>



				<div class="card-body" style="text-align: center;">
					<div class="form-group">
						<div class="row">
							<label class="col-md-1 mb-2"></label> &emsp; <label
								for="subjectId" class="col-md-0 mb-2" style="text-align: left;">Subject
								Name</label>
							<div class="col-md-2">
								<%=HtmlUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), l)%>
							</div>
							&emsp; <label for="courseId" class="col-md-0 mb-2"
								style="text-align: left;">Course Name</label>
							<div class="col-md-2">
								<%=HtmlUtility.getList("courseId", String.valueOf(dto.getCourseId()), l1)%>
							</div>

							<label for="exam date" class="col-md-0 mb-2"
								style="text-align: left;">Exam date</label>
							<div class="col-md-2">
								<input type="text" name="date" class="form-control mb-2"
									readonly="readonly" id="datepicker2"
									placeholder="enter exam date"
									value="<%=DataUtility.getDateString(dto.getExamDate())%>">
							</div>
							<div class="col-md-1">
								<input type="submit" class="btn text-white form-control mb-3"
									style="background-color: black;" name="operation"
									value="<%=TimeTableListCtl.OP_SEARCH%>">
							</div>
							<div class="col-md-1">
								<input type="submit" name="operation"
									class="btn text-white form-control mb-2"
									style="background-color: black;"
									value="<%=TimeTableListCtl.OP_RESET%>">
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
							<th>Course</th>
							<th>Subject</th>
							<th>Exam Date</th>
							<th>SEMESTER</th>
							<th>ExamTime</th>
							<th>
								<%
									if (RoleDTO.ADMIN == userBean.getRoleId()) {
								%>Edit<%
									}
								%>
							</th>
						</tr>
					</thead>
					<tbody>
						<%
							int pageNo = ServletUtility.getPageNo(request);
								int pageSize = ServletUtility.getPageSize(request);
								int index = ((pageNo - 1) * pageSize) + 1;
								List list = ServletUtility.getList(request);
								int record = ServletUtility.getRecord(request);
								Iterator<TimeTableDTO> it = list.iterator();

								while (it.hasNext()) {

									TimeTableDTO bean1 = it.next();
						%>
						<tr align="center">

							<td> <input type="checkbox" name="ids"
								value="<%=bean1.getId()%>" onchange="unchk(this)"></td> 
							<td><%=index++%></td>
							<td><%=bean1.getCourseName()%></td>
							<td><%=bean1.getSubjectName()%></td>
							<td><%=DataUtility.getDateString(bean1.getExamDate())%></td>
							<td><%=bean1.getSemester()%></td>
							<td><%=bean1.getExamTime()%></td>

							<td>
								<%
									if (RoleDTO.ADMIN == userBean.getRoleId()) {
								%><a href="TimeTableCtl?id=<%=bean1.getId()%>">Edit</a> <%
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
						<input type="submit" name="operation" class="btn text-white"
							style="background-color: black;" <%if (!(pageNo > 1)) {%>
							disabled="disabled" <%}%>
							value="<%=TimeTableListCtl.OP_PREVIOUS%>">
					</div>
					<div class="p-2 ">
						<%
							if (!list.isEmpty()) {
						%>
						<%
							if (RoleDTO.ADMIN == userBean.getRoleId()) {
						%>
						<input type="submit" name="operation" class="btn text-white"
							style="background-color: black;"
							value="<%=TimeTableListCtl.OP_NEW%>">
						<%
							}
								}
						%>
					</div>

					<div class="p-2 ">
						<input type="submit" name="operation" class="btn text-white"
							style="background-color: black;"
							value="<%=TimeTableListCtl.OP_DELETE%>">
					</div>
					<div class="p-2">
						<input type="submit" name="operation" class="btn text-white"
							style="background-color: black;"
							value="<%=TimeTableListCtl.OP_NEXT%>"
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
		
	</form>
</section>
<%@ include file="Footer.jsp"%>

<%-- 
						<td align="center">Course Name : <%=HtmlUtility.getList("courseId", String.valueOf(dto.getCourseId()), l1)%>

							&emsp; Subject Name : <%=HtmlUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), l)%>

							&emsp; <label>ExamDate : </label><input type="text"  style = "width: 200px; height: 25px;" name="date"
								readonly="readonly" placeholder="enter exam date"
								id="datepicker1"
								value="<%=DataUtility.getDateString(dto.getExamDate())%>">
						</label> &nbsp;&nbsp;<input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_SEARCH%>"> &emsp;<input
							type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_RESET%>">
						</td>
					</tr>
				</table>
				<br>
				<table border="1" width="100%">

					<tr>
						<th style="width: 3cm">Select all <input type="checkbox"
							onclick="selectall(this)" id="chk"></th>
						<th>S No</th>
						<th>Course</th>
						<th>Subject</th>
						<th>Exam Date</th>
						<th>SEMESTER</th>
						<th>ExamTime</th>
						<th>
							<%
								if (RoleDTO.ADMIN == userBean.getRoleId()) {
							%>Edit<%
								}
							%>
						</th>
					</tr>
</thead>
					<tbody>
					<%
						int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;
							List list = ServletUtility.getList(request);
							int record = ServletUtility.getRecord(request);
							Iterator<TimeTableDTO> it = list.iterator();

							while (it.hasNext()) {

								TimeTableDTO bean1 = it.next();
					%>
					<tr align="center">

						<td><input type="checkbox" name="ids"
							value="<%=bean1.getId()%>" onchange="unchk(this)"></td>
						<td><%=index++%></td>
						<td><%=bean1.getCourseName()%></td>
						<td><%=bean1.getSubjectName()%></td>
						<td><%=DataUtility.getDateString(bean1.getExamDate())%></td>
						<td><%=bean1.getSemester()%></td>
						<td><%=bean1.getExamTime()%></td>

						<td>
							<%
								if (RoleDTO.ADMIN == userBean.getRoleId()) {
							%><a href="TimeTableCtl?id=<%=bean1.getId()%>">Edit</a> <%
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
							value="<%=TimeTableListCtl.OP_PREVIOUS%>"
							<%if (!(pageNo > 1)) {%> disabled="disabled" <%}%>>
						<td align="center">
							<%
								if (!list.isEmpty()) {
							%> <%
 	if (RoleDTO.ADMIN == userBean.getRoleId()) {
 %><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_NEW%>">
						</td>
						<td align="center"><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_DELETE%>"> <%
 	}
 		}
 %></td>
						</td>

						<td align="right"><input type="submit" name="operation"
							value="<%=TimeTableListCtl.OP_NEXT%>"
							<%if (!(list.size() == Integer.parseInt(PropertyReader.getValue("page.size")))
						|| record == Integer.parseInt(PropertyReader.getValue("page.size"))) {%>
							disabled="disabled" <%}%>></td>
					</tr>
				</table>
				<input type="hidden" name="record" value="<%=record%>"> <input
					type="hidden" name="pageNo" value="<%=pageNo%>"><input
					type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
		<%
			}
		%>


		</center>
	</div>
	<div>
		<%@ include file="Footer.jsp"%>
	</div>

</body>
</html> --%>