<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.rays.proj3.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@page import="in.co.rays.proj3.dto.MarksheetDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@include file="Header.jsp"%>

<section class="content">


			<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
				
			<div class="container-fluid">
			<div class="card align-center"
			style="margin: 20 auto; float: none;">
				<div class="card-header  text-white "style=" background-color: black;">
					<h2 style="text-align: center;">Marksheet Merit List</h2>
					<div style="margin-bottom: -1%;">
				
</div>
</div>
				  <table class="table table-bordered table-responsive-sm table-striped">
			<thead>
					<tr align="center">
						<th>S No</th>
						<th>Roll No</th>
						<th>Name</th>
						<th>Physics</th>
						<th>Chemistry</th>
						<th>Maths</th>
						<th>Percentage</th>

					</tr>
					</thead>
					<tbody>
					<%-- <tr>
						<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
					</tr> --%>
					<%
						int pageNo = ServletUtility.getPageNo(request);
						int pageSize = ServletUtility.getPageSize(request);
						int index = ((pageNo - 1) * pageSize) + 1;

						List list = ServletUtility.getList(request);
						Iterator<MarksheetDTO> it = list.iterator();
						MarksheetDTO bean = null;
						while (it.hasNext()) {

							bean = it.next();

							String result = "";
							if (bean.getPhysics() > 70 && bean.getChemistry() > 70&& bean.getMaths() > 70) {
								float total = bean.getPhysics() + bean.getChemistry() + bean.getMaths();
								float percentage = (total / 300) * 100;
					%>
					<tr align="center">

						<td><%=index++%></td>
						<td><%=bean.getRollNo()%></td>
						<td><%=bean.getName()%></td>
						<td><%=bean.getPhysics()%></td>
						<td><%=bean.getChemistry()%></td>
						<td><%=bean.getMaths()%></td>

						<td><%=Float.parseFloat(new DecimalFormat("##.##").format(percentage))%></td>

					</tr>

					<%
						}
						}
					%>
					</tbody>
				</table>
				
				
				<div class="d-flex justify-content-around mb-3">
				<div class="p-2 ">
							<input type="submit" name="operation" class="btn text-white"style=" background-color: black;"
						value="<%=MarksheetMeritListCtl.OP_BACK%>">
				</div>
				
				<%-- <td align="center"><input type="submit" name="operation"
							value="<%=MarksheetMeritListCtl.OP_BACK%>">
					</tr> --%>
				
				
				
					
					<%
						if(list.size()>0){
					%>
					<div class="p-2 ">
							
					<a class="btn text-white" href="<%=ORSView.JASPER_CTL%>" target="_blank" style=" background-color: black;">
				       Print </a>
				</div>
					
					
					
					<%-- <td style="border-color: transparent;" align="center">
					<a href="<%=ORSView.JASPER_CTL%>" target="_blank" class="btn btn-primary">
				       Print  <span class="glyphicon glyphicon-print" target="_blank"></span></a> --%>
					<%
						}
					%>
			
		
				<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
					type="hidden" name="pageSize" value="<%=pageSize%>">
				</div>
				</div>
				</div>
		
			</form>
</section>
<%@ include file="Footer.jsp"%>