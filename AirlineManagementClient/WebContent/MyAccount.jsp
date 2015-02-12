<!DOCTYPE html>
<html lang="en">
<head>
<title>My Account</title>
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
	function newPopup(url)
	{
		var left = (screen.width / 2) - (150);
		var top = (screen.height / 2) - (250);
		popupWindow = window
				.open(
						url,
						'popUpWindow',
						'height=300,width=500,resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=yes,status=no,titlebar=no, left='
								+ left + ',top=' + top + '');
		popupWindow.focus();
	}

	function showTickeSelection()
	{
		$("#ticketSelect").show();
	}

	function validateTicketNo()
	{
		cardNo = $("#cardNo").val();
		var reg = /^[0-9]*$/;
		if (cardNo == "")
		{
			alert("Card Number should not be empty");
			return false;
		} else if (!reg.test(cardNo) )
		{
			alert("Card number should be a  number");
			return false;
		}else if(cardNo.length != 16){
			alert("Card number should be a 16 digit number");
			return false;
		}
	}
</script>

</head>
<body id="page2">
	<%@ page import="beans.Reservation"%>
	<!-- START PAGE SOURCE -->

	<%
		String fName = (String) session.getAttribute("fName");
		boolean signedIn = false;

		Integer roleID = (Integer)session.getAttribute("roleID");

		if (fName != null)
			signedIn = true;
		else
			response.sendRedirect("Home.jsp");
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
								<li><a href="Home.jsp">Home</a></li>
								<!-- <li><a href="JavaScript:newPopup('Login.jsp');">Login</a></li> -->
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
								<li id="menu_active"><a href="MyAccount.jsp">My Account</a></li>
								<% if (roleID == 1){
								%>
								<li><a href="Admin.jsp">Admin</a></li>
								<%} %>
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

	<div class="main">
		<section id="content">
			<article class="col1">
				<div class="pad_1">
					<h2>Your Flight Planner</h2>

					<ul id="categories">
						<li style="border-top: 0;"><a
							href="TravelServlet?task=cancel">Cancel Reservation</a></li>
						<li><a href="TravelServlet?task=getReservations">My Bookings</a></li>
						<li><a href="TravelServlet?task=getTickets">My Tickets</a></li>

					</ul>
				</div>
			</article>
			<article class="col2 pad_left1">
				<!-- 


PUT YOUR MAIN CONTETNT HERE !!!!

<
 -->
				<%
					if (request.getParameter("task") != null && "issueTicket".equals(request.getParameter("task")))
					{
				%>
				<div id="ticketSelect">
					<form action="TravelServlet" method="post" id="ContactForm"
						onsubmit="return validateTicketNo()">
						<h2>Enter your card number for payment</h2>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="cardNo" name="cardNo" />
							</div>
							Credit Card Number:<br />
						</div>

						<input type="hidden" name="task" value="issueTicket" /> <input
							type="submit" value="Confirm Payment" class="button1">
					</form>
				</div>
				<%
					}
				%>
				<%
					if (request.getParameter("message") != null)
					{
				%>
				<form id="contactForm">
					<h2><%=request.getParameter("message")%></h2>
				</form>
				<%
					}
				%>
				<%
					if (request.getParameter("task") != null && "cancel".equals(request.getParameter("task")))
					{
				%>
				<form action="TravelServlet" method="post">
					<%
						Reservation[] reservations = (Reservation[]) session.getAttribute("reservations");
							
						if (reservations != null)
						{
					%>
									<h2>Your Reservations</h2>
					
					<table id="flightDisplay">
					<tr>
						<th>Reservation No</th>
						<th>Airline Number</th>
						<th>Airline Name</th>
						<th>Source</th>
						<th>Destination</th>
						<th># Seats</th>
						<th>Click to Cancel</th>
					</tr>
					
					<%		
						int rowCnt = 0;
							for (Reservation reservation : reservations)
							{
					%>
							<% if (rowCnt%2==0) {%>
					<tr >
					<%} else {%>
					<tr class="alt">
					<% }%>
							<td><%=reservation.getReservationId()%></td>
							<td><%=reservation.getFlightNumber()%></td>
							<td><%=reservation.getAirlineName()%></td>
							<td><%=reservation.getSource()%></td>
							<td><%=reservation.getDestination()%></td>
							<td><%=reservation.getNumberOfSeats()%></td>
							<input type="hidden" name="reservID" value="<%=reservation.getReservationId()%>" />
							<input type="hidden" name="task" value="cancelTicket" />
							<td><input type="submit" value="Cancel" class="button1"></td>
						</tr>
					<%
						rowCnt++;
							}
					%>
				</table><br>
				<%} else {%>
					<h2>No Reservations Found!</h2>
				<%} %>
									</form>
				
				<%
					}
					else if (request.getParameter("task") != null && "showTicket".equals(request.getParameter("task")))
					{
						Reservation ticket = (Reservation) request.getAttribute("ticket");
				%>
				
				<h2>Thank you for booking ticket! Your Ticket Details</h2>
				<table id="flightDisplay">
					<tr>
						<th>Rerservation No</th>
						<th>Airline No</th>
						<th>Airline Name</th>
						<th>Source</th>
						<th>Destination</th>
						<th># of Seats Booked</th>
						<th>Issue Ticket</th>
					</tr>
					<tr>
						<td><%=ticket.getReservationId()%></td>
						<td><%=ticket.getFlightNumber()%></td>
						<td><%=ticket.getAirlineName()%></td>
						<td><%=ticket.getSource()%></td>
						<td><%=ticket.getDestination()%></td>
						<td><%=ticket.getNumberOfSeats()%></td>
					</tr>
				</table><br>
				<%
					}else if(request.getParameter("task") != null && "showReservations".equals(request.getParameter("task"))){
						 Reservation[] reservations = (Reservation[])request.getAttribute("tickets");
						 String toBeIssued = request.getParameter("toBeIssued");
							if(reservations!=null){
				%>			
							<%if(toBeIssued!=null){ %>
							<h2> Make Payment to confirm tickets!</h2>
							<%} else{%>
								<p>Confirmed Ticket List</p>
							<%} %>
							<table id="flightDisplay">
							<tr><th>Reservation ID</th><th>Flight No </th><th>Airline Name</th><th>Source</th><th>Destination</th><th>Seats Booked</th><th>Issue Ticket</th></tr>
				<%				for(Reservation resv : reservations){
				%>
									<tr><td><%=resv.getReservationId()%></td><td><%=resv.getFlightNumber()%></td><td><%=resv.getAirlineName()%></td>
									<td><%=resv.getSource()%></td><td><%=resv.getDestination()%></td><td style="text-align: center;"]><%=resv.getNumberOfSeats()%></td>
									<%if(toBeIssued!=null) {%>
									<form action="TravelServlet" method="get">
											<input type="hidden" name="task" value="ticket">
											<input type="hidden" name="reservID" value="<%=resv.getReservationId()%>"/>
									<td>
											<input type="submit" value="Issue Ticket"/>
									</td>
									</form>
									<%} %>
									</tr>
									
				<%				}%>
							</table>	
				<% 			}
					}
				%>
			</article>
		</section>
	</div>
	<div class="body2">
		<div class="main">
			<footer>
				<div class="footerlink">
					<div style="clear: both;"></div>
				</div>
			</footer>
		</div>
	</div>
	<script type="text/javascript"> Cufon.now(); </script>
	<!-- END PAGE SOURCE -->
</body>
</html>