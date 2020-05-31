<%@page import="in.co.rays.proj3.controller.RoleCtl"%>
<%@page import="in.co.rays.proj3.controller.BaseCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ include file="Header.jsp"%>
<section class="content">
<div class="container">

	<form action="<%=ORSView.ROLE_CTL%>" method="post">
		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.RoleDTO"
			scope="request"></jsp:useBean>
		<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=dto.getModifiedBy()%>"> <input type="hidden"
			name="createdDatetime"
			value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
		<input type="hidden" name="modifiedDatetime"
			value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">


		<div class="card text-center"
			style="margin: 40 auto; float: none; max-width: 40rem;">
			<div class="card-header  text-white" style=" background-color: black;">
			<div style="margin-bottom: -2%;">
				<%
					if (dto.getId() >= 1) {
				%>
				<h2 style="text-align: center;">Update Role</h2>

				<%
					} else {
				%><h2 style="text-align: center;">Add Role</h2>
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
							<div class="col-sm-3">Name</div>
							<div class="col-sm-5">
								<input type="text" class="form-control mb-2 mr-sm-2" id="name"
									value="<%=DataUtility.getStringData(dto.getName())%>"
									name="name" placeholder=" enter Name">
							</div>
							<div class="col-sm-4">
								<font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
						</div>
					</div>
				
						<div class="form-group">
							<div class="row">
								<div class="col-sm-3">Description</div>
								<div class="col-sm-5">
									<input type="text" class="form-control mb-2 mr-sm-2"
										id="description"
										value="<%=DataUtility.getStringData(dto.getDescription())%>"
										name="description" placeholder=" enter description">
								</div>
								<div class="col-sm-4">
									<font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font>
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
										<input type="submit" class="btn text-white" name="operation" style=" background-color: black;"
											value="<%=RoleCtl.OP_UPDATE%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=RoleCtl.OP_SAVE%>">
									</div>
									<%
										}
									%>
									<%
										if (dto.getId() > 0) {
									%><div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=RoleCtl.OP_CANCEL%>">
									</div>
									<%
										} else {
									%>
									<div class="p-2">
										<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
											value="<%=RoleCtl.OP_RESET%>">
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


