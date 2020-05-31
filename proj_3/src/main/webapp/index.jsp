<%@page import="in.co.rays.proj3.controller.ORSView"%>

<!DOCTYPE html>
<html>
<head>

<style>
body, html {
    height: 100%;
    margin: 0;
}

.bg {
    /* The image used */
    background-image: url("image/Website-Design-Background.png");

    /* Full height */
    height: 100%; 

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>



<body class="bg">


	<h1 align="Center">
		<img src="image/customLogo.png" width="318" height="127" border="0">
	</h1>

	<h1 align="Center">
		<font size="10px" color="red">
	
		<a href="<%=ORSView.WELCOME_CTL%>">Online Result System</a> </font> <br> <br>
	</h1>
</body>
</head>
</html>
