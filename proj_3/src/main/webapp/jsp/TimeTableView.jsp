<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj3.controller.TimeTableCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.proj3.util.*"%>
<%@page import="in.co.rays.proj3.dto.TimeTableDTO"%>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">


		<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">

			<jsp:useBean id="dto"
				class="in.co.rays.proj3.dto.TimeTableDTO" scope="request"></jsp:useBean>
				
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
				<div style="margin-bottom: -2%;">
				<%
					if (dto.getId() == 0) {
				%>
				<h2 style="text-align: center;">Add TimeTable</h2>

				<%
					} else {
				%><h2 style="text-align: center;">Update TimeTable</h2>
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
						<div class="col-sm-3">Course Name</div>
						<div class="col-sm-5">
							<%
								List l2 = (List) request.getAttribute("courseList");
							%>

							<%=HtmlUtility.getList("courseId", String.valueOf(dto.getCourseId()), l2)%>
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("courseId", request)%></font>
						</div>
					</div>
				</div>
				
				
				<div class="form-group">
					<div class="row">
						<div class="col-sm-3">Subject Name</div>
						<div class="col-sm-5">
							<%
							List l1 = (List) request.getAttribute("subjectList");
							%>

							<%=HtmlUtility.getList("subjectId", String.valueOf(dto.getSubjectId()), l1)%>
						</div>
						<div class="col-sm-4">
							<font color="red"><%=ServletUtility.getErrorMessage("subjectId", request)%></font>
						</div>
					</div>
				</div>
				
				<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Exam Date</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2"
									readonly="readonly" id="datepicker1"
									value="<%=DataUtility.getDateString(dto.getExamDate())%>" name="date"
									placeholder="enter exam date">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Semester</div>
							<div class="col-sm-5">
								<%
								HashMap map1 = new HashMap();
					
								map1.put("1", "1sem");
								map1.put("2", "2sem");
								map1.put("3", "3sem");
								map1.put("4", "4sem");
								map1.put("5", "5sem");
								map1.put("6", "6sem");
								map1.put("7", "7sem");
								map1.put("8", "8sem");

								String htmlList1 = HtmlUtility.getList("semester", String.valueOf(dto.getSemester()), map1);
							%> <%=htmlList1%>
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("semester", request)%></font>
							</div>
						</div>
					</div>
				
				<div class="form-group">
						<div class="row">
							<div class="col-sm-3">Exam Time</div>
							<div class="col-sm-5">
							<%
								HashMap map = new HashMap();
								
								map.put("10:00PM-01:00PM", "10:00PM-01:00PM");
								map.put("12:00AM-03:00PM", "12:00AM-03:00PM");
								map.put("03:00PM-06:00PM", "03:00PM-06:00PM");

								String htmlList = HtmlUtility.getList("ExamTime", String.valueOf(dto.getExamTime()), map);
							%> <%=htmlList%>
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("ExamTime", request)%></font>
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
											value="<%=TimeTableCtl.OP_UPDATE%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=TimeTableCtl.OP_SAVE%>">
									</div>
									<%
										}
									%>
									<%
										if (dto.getId() > 0) {
									%><div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=TimeTableCtl.OP_CANCEL%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=TimeTableCtl.OP_RESET%>">
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

