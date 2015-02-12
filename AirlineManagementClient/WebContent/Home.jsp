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

function validateReservConfirmation(){
	var passport = $("#passport").val();
	var nationality = $("#nationality").val();
	
	if(!passport){
		window.alert('PASSPORT NUMBER CANNOT BE EMPTY');
		return false;
	}else if(!nationality){
		window.alert('NATIONALITY CANNOT BE EMPTY');
		return false;
	}
}

	function validateFlightSearch()
	{
		var source = $("#source").val();
		var destination = $("#destination").val();
		var noOfSeats = $("#noOfSeats").val();
		var timeReg = /^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
		var reg = /^[0-9]*$/;
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
	
	function load()
	{
		alert("Page is loaded");
		<%
			Integer isCacheBuilt = (Integer) session.getAttribute("isCacheBuilt");
			
			if (isCacheBuilt== null)
			{
				airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();
				proxy.buildCache();
				session.setAttribute("isCacheBuilt",1);
			}
		%>
	}
</script>

</head>
<body id="page2" onload="buildCache()">
	<!-- START PAGE SOURCE -->

	<%
		String fName = (String) session.getAttribute("fName");
		boolean signedIn = false;

		String message = (String)request.getAttribute("message");
		
		Integer roleID = (Integer)session.getAttribute("roleID");
		if (fName != null)
			signedIn = true;
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
					<%
						 message = request.getParameter("retry");
							if(message!=null){
					%>
					<p><%=message%></p>
					<%}
					%>
					<h2>Your Flight Planner</h2>
					<form id="form_1" action="TravelServlet" method="post"
						onsubmit="return validateFlightSearch()">
						<div class="wrapper">
							Leaving From:
							<div class="bg">
								<input type="text" class="input input1" id="source"
									name="source" placeholder="Enter City or Airport Code"
									onBlur="if(this.value=='') this.value='Enter City or Airport Code'"
									onFocus="if(this.value =='Enter City or Airport Code' ) this.value=''">
							</div>
						</div>
						<div class="wrapper">
							Going To:
							<div class="bg">
								<input type="text" class="input input1" id="destination"
									name="destination" placeholder="Enter City or Airport Code"
									onBlur="if(this.value=='') this.value='Enter City or Airport Code'"
									onFocus="if(this.value =='Enter City or Airport Code' ) this.value=''">
							</div>
						</div>
						<div class="wrapper">
							Departure Time:
							<div class="wrapper">
								<!-- <div class="bg left">
                <input type="text" class="input input2" value="mm/dd/yyyy " onBlur="if(this.value=='') this.value='mm/dd/yyyy '" onFocus="if(this.value =='mm/dd/yyyy ' ) this.value=''">
              </div>-->
								<div class="bg right">
									<input type="text" name="time" id="time" class="input input2"
										placeholder="12:00am"
										onBlur="if(this.value=='') this.value='12:00am'"
										onFocus="if(this.value =='12:00am' ) this.value=''">
								</div>
							</div>
						</div>
						<div class="wrapper">
							<p>Passenger(s):</p>
							<div class="bg left">
								<input type="text" name="noOfSeats" id="noOfSeats"
									class="input input2" placeholder="# passengers"
									onBlur="if(this.value=='') this.value='# passengers'"
									onFocus="if(this.value =='# passengers' ) this.value=''">
							</div>
							<input type="hidden" name="task" value="search" /> <input
								type="submit" class="button2" value="Search">
							<!-- <a href="#" class="button2">go!</a> -->
						</div>
					</form>

				</div>
			</article>
			<article class="col2 pad_left1">
				<%
					if (request.getParameter("task") == null)
					{
				%>
				<form method="post" action="LoginServlet" name="form1">
					<h2>Welcome to AirLine</h2>
					<div class="wrapper">
						<figure class="left marg_right1">
							<img src="images/page2_img1.jpg" alt="">
						</figure>
						<p>
							<strong>Service:</strong> Airline International offers premium
							service to user across the globe. Its been rated as the number 1
							website for flight bookings.
						</p>
					</div>

					<h2>Aircraft Management</h2>
					<div class="wrapper">
						<figure class="right marg_left1">
							<img src="images/page2_img2.jpg" alt="">
						</figure>
						<p>
							<strong>Premium Customers: </strong>Users registered with Airline
							International can avail special discounts and exciting offers.
							Get rescheduling at lowest rates! Click the button to register.
						</p>
						<div style="display: block; float: left">
							<a href="Register.jsp"><input type="button" class="button1"
								value="Register" style="margin-bottom: 20px;"></a>

						</div>
					</div>
				</form>
				<%
					}
					else if (request.getParameter("task") != null && "search".equals(request.getParameter("task")))
					{
				%>
				<%
					if (session.getAttribute("flights") == null)
						{
				%>
				<form id="ContactForm">
				<h2 align="center">There are no Flights available between this source and destinaiton.</h2>
				</form>
				<%
					}
						else
						{
							FlightDetails[] flights = (FlightDetails[]) session.getAttribute("flights");
							int rowNo = 0;
				%>
				<h2>Following Flights Found</h2>

				<form action="TravelServlet" method="post">

				<table id="flightDisplay">
					<tr>
						<th>FLIGHT NO</th>
						<th>FLIGHT NAME</th>
						<th>SOURCE</th>
						<th>DESTINATION</th>
						<th>SEATS AVAILABLE</th>
						<th>FLIGHT TIME</th>
						<th>Click to Reserve</th>
					</tr>

					<%
						for (FlightDetails flight : flights)
								{ 
									//Display all flights here
					%>
						<% if (rowNo%2==0) {%>
					<tr >
					<%} else {%>
					<tr class="alt">
					<% }%>
					
							<td><%=flight.getFlightNumber()%></td>
							<td><%=flight.getAirlineName()%></td>
							<td><%=flight.getSource()%></td>
							<td><%=flight.getDestination()%></td>
							<td><%=flight.getNumberOfSeats()%></td>
							<td><%=flight.getFlightTime()%></td>
							<input type="hidden" name="task" value="reserve" />
							<input type="hidden" name="flightCnt" value="<%=rowNo%>" />
							<td><input type="submit" value="Reserve" class="button1"
								style="width: 100px;" /></td>
						</tr>
					<%
						rowNo++;
								}
							}
					%>
				</table>
			</form>
				
				<br>
				<%
					}
					else if (request.getParameter("task") != null && "confirmReservaton".equals(request.getParameter("task")))
					{
				%>

				<form id="ContactForm" method="post" action="TravelServlet"
					name="form2" onsubmit="return validateReservConfirmation()">
					<h2>Enter BELOW DETAILS AS YOUR IDENTITY WHILE TRAVELLING!</h2>
					<div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="nationality" id="nationality"/>
							</div>
							NATIONALITY:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="passport" id="passport"/>
							</div>
							PASSPORT NUMBER:<br />
						</div>


						<input type="hidden" name="task" value="confirmReservation" /> <input
							type="submit" value="Confirm" class="button2" />
					</div>
				</form>



				<%
					}
				%>
			</article>
		</section>
	</div>
	<div class="body2">
		<div class="main">
			<footer>
				<div class="footerlink"></div>
			</footer>
		</div>
	</div>
	<script type="text/javascript">
		Cufon.now();
	</script>
	<!-- END PAGE SOURCE -->
</body>
</html>