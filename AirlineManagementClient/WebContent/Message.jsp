<!DOCTYPE html>
<%@page import="beans.FlightDetails"%>
<html lang="en">
<head>
<title>AirLines | Aircrafts</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="css/table.css">

<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_italic_600.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_italic_400.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>

<script type="text/javascript">
	function validateFlightSearch()
	{
		var source = $("#source").val();
		var destination = $("#destination").val();
		var noOfSeats = $("#noOfSeats").val();
		var timeReg = /^[01][0-9]|[2][0-3]:[0-5][0-9]$/;
		var reg = /^[0-9]$/;
		var timeVal = $("#time").val();

		if (source == "")
		{
			alert("Source not provided");
			return false;
		} else if (destination == "")
		{
			alert("Source not provided");
			return false;
		} else if (!reg.test(noOfSeats))
		{
			alert('Seats should be a number');
			return false;
		} else if (timeVal == "")
		{
			alert('Mention flight departure time');
		} else if (!timeReg.test(timeVal))
		{
			alert('Invalid time format. (00:00 to 23:59)');
			return false;

		}

	}
</script>

</head>
<body id="page2">
	<!-- START PAGE SOURCE -->

	<%
		String fName = (String) session.getAttribute("fName");
		boolean signedIn = false;

		if (fName != null)
			signedIn = true;
		
		String message = (String) request.getAttribute("message");
	%>
	<div class="body1">
		<div class="main">
			<header>
				<div class="wrapper">
					<h1>
						<a href="Home.jsp" id="logo">AirLines</a><span id="slogan">International
							Travel</span>
					</h1>
					<div class="right">
						<nav>
							<ul id="top_nav">
								<li><a href="#"><img src="images/img1.gif" alt=""></a></li>
								<li><a href="#"><img src="images/img2.gif" alt=""></a></li>
								<li class="bg_none"><a href="#"><img
										src="images/img3.gif" alt=""></a></li>
							</ul>
						</nav>
						<nav>
							<ul id="menu">
								<li id="menu_active"><a href="Home.jsp">Home</a></li>
								<%
									if (signedIn == false)
									{
								%>
								<li><a href="Login.jsp;">Login</a></li>
								<%
									}
									else
									{
								%>
								<li><a href="MyAccount.jsp">My Account</a></li>
								<li><a href="Admin.jsp">Admin</a></li>
								<li><a href="Login.jsp;">SignOut</a></li>
								<%
									}
								%>
							</ul>
						</nav>
					</div>
				</div>
			</header>
		</div>
	</div>
	<div class="main">
		<div id="banner">
			<div class="text1">
				COMFORT<span>Guaranteed</span>
				<%
					if (fName != null)
					{
				%>
				<p>
					Welcome
					<%=fName%>
					!
					<%
					}
				%>
				</p>
			</div>
		</div>
	</div>

	<form id="ContactForm">
		<h2 align="center"><%= message %></h2>
	</form>
	
	<script type="text/javascript">
		Cufon.now();
	</script>
	<!-- END PAGE SOURCE -->
</body>
</html>