
<%@page import="in.co.rays.proj3.controller.GetMarksheetCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<section class="content">
	<div class="container">
		<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

			<jsp:useBean id="dto" class="in.co.rays.proj3.dto.MarksheetDTO"
				scope="request"></jsp:useBean>
			<input type="hidden" name="id" value="<%=dto.getId()%>">
<div class="card text-center"
				style="margin: 20 auto; float: none; max-width: 40rem;">
				<div class="card-header  text-white" style=" background-color: black;">
				<div style="margin-bottom: -2%;">
					<h2><div style="text-align: center;">Get MarkSheet</div></h2>

						<H2>
							<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
							</font>
						</H2>
						<H2>
							<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
							</font>
						</H2>
					</div>
				</div>

				<%
					if (dto.getRollNo() != null && dto.getRollNo().trim().length() > 0) {
				%>
				<div class="card-body">
					<div class="input-group mb-ms-3">
						<input type="text" class="form-control" name="rollNo"
							placeholder="   Enter Roll Number "
							value="<%=ServletUtility.getParameter("rollNo", request)%>">
						<div class="input-group-append">
							<button class="btn text-white" type="submit" name="operation" style=" background-color: black;"
								value="<%=GetMarksheetCtl.OP_GO%>">Go</button>
						</div>
					</div>
					<div class="row text-center" style="padding: 10px;">
						<div class="col-sm-12">
							<font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font>
						</div>
					</div>
				</div>
				<table
					class="table table-bordered table-responsive-sm table-striped">

					<tr align="center" style='background-color: #555; color: white'>
						<td style='min-width: 95px'>Roll No</td>
						<td style='min-width: 95px'><%=DataUtility.getStringData(dto.getRollNo())%></td>

						<td style='min-width: 50px'>Name</td>
						<td style='min-width: 10px'><%=DataUtility.getStringData(dto.getName())%></td>
					</tr>
				</table>
				<table
					class="table table-bordered table-responsive-sm table-striped">
					<tr align="center" style='background-color: #f1f1f1'>
						<td>Subject Name</td>
						<td>Total Marks</td>
						<td>Obtained Marks</td>
						<td>Distinction</td>
					</tr>
					<tr align="center">
						<td>Physics</td>
						<td>100</td>
						<td>
							<%
								if (dto.getPhysics() < 33) {
							%> <%=(dto.getPhysics())%> <%
 	
 %> <font color="red">*</font> <%
 	} else {
 %> <%=(dto.getPhysics())%> <%
 	}
 %>
						</td>
						<td width="150px"><%=(dto.getPhysics() > 70) ? "Distinction" : " "%></td>
					</tr>
					<tr align="center">
						<td>Chemistry</td>
						<td>100</td>
						<td>
							<%
								if (dto.getChemistry() < 33) {
							%> <%=(dto.getChemistry())%> <%
 	
 %> <font color="red">*</font> <%
 	} else {
 %> <%=(dto.getChemistry())%> <%
 	}
 %>
						</td>
						<td><%=(dto.getChemistry() > 70) ? "Distinction" : ""%></td>

					</tr>
					<tr align="center">
						<td>Maths</td>
						<td>100</td>
						<td>
							<%
								if (dto.getMaths() < 33) {
							%> <%=(dto.getMaths())%> <%
 	
 %> <font color="red">*</font> <%
 	} else {
 %> <%=(dto.getMaths())%> <%
 	}
 %>
						</td>
						<td><%=(dto.getMaths() > 70) ? "Distinction" : ""%></td>

					</tr>
					<tr>
						<td height="20px"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr align="center">
						<td></td>
						<td>(Total Marks) <font color="green">300</font></td>
						<td>(Obtained Marks ) <font color="red"><%=(dto.getChemistry()) + (dto.getMaths()) + (dto.getPhysics())%></font></td>
						<td></td>
				</table>

				<table
					class="table table-bordered table-responsive-sm table-striped">
					<tr align="center">
						<td width="450px">Percentage</td>
						<td><%=Float.valueOf((dto.getChemistry()) + (dto.getMaths()) + (dto.getPhysics())) / 3%>%</td>
					</tr>
					<tr align="center">

						<td>Grade</td>
						<td>
							<%
								if ((dto.getChemistry()) + (dto.getMaths()) + (dto.getPhysics()) * 1 / 3 >= 90) {
							%>A+<%
								} else if ((dto.getChemistry()) + (dto.getMaths()) + (dto.getPhysics()) * 1 / 3 >= 80) {
							%> A<%
								} else if ((dto.getChemistry()) + (dto.getMaths()) + (dto.getPhysics()) * 1 / 3 >= 70) {
							%>B <%
								} else {
							%>C<%
								}
							%>
						</td>
					</tr>
					<tr align="center">
						<td>Result</td>
						<td>
							<%
								if ((dto.getMaths()) >= 33 && (dto.getPhysics()) >= 33 && (dto.getChemistry()) >= 33) {
							%> <font color="green">PASS</font> <%
 	} else {
 %> <font color="RED">FAIL</font> <%
 	}
 %>
						</td>
					</tr>
				</table >
				<div class="form-group">
						<div class="d-flex justify-content-around mb-2">
							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=GetMarksheetCtl.OP_BACK%>">
							</div>
				<%
					} else {
				%>
				<div class="card-body">
					<div class="input-group mb-ms-3">
						<input type="text" class="form-control" name="rollNo"
							placeholder="   Enter Roll Number "
							value="<%=ServletUtility.getParameter("rollNo", request)%>">
						<div class="input-group-append">
							<button class="btn text-white" type="submit" name="operation"style=" background-color: black;"
								value="<%=GetMarksheetCtl.OP_GO%>">Go</button>
						</div>
					</div>
					<div class="row text-center" style="padding: 10px;">
						<div class="col-sm-12">
							<font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font>
						</div>
					</div>
					<div class="form-group">
						<div class="d-flex justify-content-around mb-2">
							<div class="p-2">
								<button class="btn text-white" type="submit" name="operation"style=" background-color: black;"
								value="<%=GetMarksheetCtl.OP_RESET%>">Reset</button>
							</div>
							</div>
							</div>
				</div>
				<%
					}
				%>
				
			</div>
		</form>
	</div>
</section>

<%@include file="Footer.jsp"%>