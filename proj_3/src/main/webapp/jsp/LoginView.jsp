
<%@page import="in.co.rays.proj3.controller.BaseCtl"%>
<%@page import="in.co.rays.proj3.controller.ORSView"%>
<%@page import="in.co.rays.proj3.controller.LoginCtl"%>
<%@page import="in.co.rays.proj3.util.DataUtility"%>
<%@page import="in.co.rays.proj3.util.ServletUtility"%>
<%@ include file="Header.jsp"%>

<section class="content">
	<div class="container">
		
			<form action="<%=ORSView.LOGIN_CTL%>" method="POST">
<div class="card"
			style="margin: 20 auto; float: none; max-width: 30rem;">
				<div class="card-header  text-white" style=" background-color: black;">
					<h2 style="text-align: center;">Login </h2>
				</div>
				<div style="padding-top: 20px" class="card-body">

		<jsp:useBean id="dto" class="in.co.rays.proj3.dto.UserDTO"
			scope="request"></jsp:useBean>
		
			
			
				<H2 align="center">
				<%String msg= (String)request.getAttribute("message");
				if (msg!=null){
				%>
				
				
				<font color="red"> <%=msg %> </font> 
				
								
				
				<%} %>
					<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				
				
					<font color="red">  <%=ServletUtility.getErrorMessage(request)%> 
					</font>
				</H2>
			


			<input type="hidden" name="id" value="<%=dto.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=dto.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">

			
			<div class="input-group">
						<div class="input-group-prepend">
							<!-- <span class="input-group-text">Login ID </span> -->
							<span class="input-group-text"><i
								class="fa fa-user-circle-o" aria-hidden="true"></i> </span>
						</div>
						<input type="text" class="form-control" placeholder="EMAIL ID"
							name="login"
							value="<%=DataUtility.getStringData(dto.getLogin())%>">
					</div>
					<div class="row text-center" style="padding: 10px;">
						<div class="col-sm-12">
							<font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
						</div>
					</div>
				<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"><i class="fa fa-lock"
								aria-hidden="true"></i></span>
						</div>
						<input type="password" class="form-control" placeholder="Password"
							name="password"
							value="<%=DataUtility.getStringData(dto.getPassword())%>">
					</div>
					<div class="row text-center" style="padding: 10px;">
						<div class="col-sm-12">
							<font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
						</div>
					</div>


				
					<div class="form-group">
						<div class="d-flex justify-content-around mb-2" >
							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=LoginCtl.OP_SIGN_IN%>">
							</div>
							<div class="p-2">
								<input type="submit" class="btn text-white" name="operation"style=" background-color: black;"
									value="<%=LoginCtl.OP_SIGN_UP%>">
							</div>
						</div>
					</div>
					</div>
					<div class="card-footer text-center text-black ">
					<div style=" font-color: black;">
					<a href="<%=ORSView.FORGET_PASSWORD_CTL%>" class="card-link text-black">
						<font color="black">Forget Password ? </font></a>
						</div>
				</div>
				
				</div>
			</form>
		</div>
	

</section>

<%@ include file="Footer.jsp"%>