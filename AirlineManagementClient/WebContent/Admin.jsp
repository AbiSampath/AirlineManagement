<!DOCTYPE html>
<html lang="en">
<head>
<title>Admin</title>
<meta charset="utf-8">
<link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
<link rel="stylesheet" href="css/layout.css" type="text/css" media="all">
<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
<link rel="stylesheet" href="css/table.css" type="text/css" media="all">

<%@ page import="beans.Employee"%>
<%@ page import="beans.Traveller"%>
<%@ page import="beans.Reservation"%>
<%@ page import="beans.FlightDetails"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@page import="servlets.LoginServlet"%>

<%@ page session="true"%>

<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/cufon-replace.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_italic_600.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_italic_400.font.js"></script>
<script type="text/javascript" src="js/Myriad_Pro_400.font.js"></script>

<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/jquery-ui.min.js"></script>

<style type="text/css">
table {
	border: 1px;
	width: 50%;
	cellspacing: 5px;
	cellpadding: 8px;
}

table th {
	bgcolor: #005fbf;
}

table td {
	bgcolor: #003f7f;
}
</style>
<script type="text/javascript">
	function validateEmployeeDetails()
	{
		var fName =  document.getElementsByName("firstName")[0].value; 		
		var lastName = document.getElementsByName("lastName")[0].value;
		var email = document.getElementsByName("email")[0].value;		
		var address = document.getElementsByName("address")[0].value;		
		var password = document.getElementsByName("password")[0].value;		
		var city = document.getElementsByName("city")[0].value;		
		var DOB = document.getElementsByName("DOB")[0].value;		
		var position = document.getElementsByName("position")[0].value;		
		var workDescription = document.getElementsByName("workDescription")[0].value;		
		var state = document.getElementsByName("selectedState")[0].value;		
		var zipCode = document.getElementsByName("zipCode")[0].value;		

		if (!fName || !lastName || !email || !address || !password || !city || !DOB || !position || !workDescription || !state || !zipCode)
		{
			alert("One or more fields are blank!");
			return false;
		}
		
		

	}

	function validateUpdateTraveler()
	{
		var email = $("#email").val();
		var zipCode = document.getElementById("zip");		
		var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!email)
		{
			window.alert('Customer email id cannot be empty');
			return false;
		}else if(!filter.test(email)){
			window.alert('Invalid Email Format');
			return false;
		}else if(zipCode){
			if (!zipCodeFilter1.test(zipCode) && !zipCodeFilter2.test(zipCode)){
				window.alert("Invalid Zipcode. \nPlease enter in format: [0-9][0-9][0-9][0-9][0-9] or \n [0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]");
		    	return false;
			}
		}
			
	}

	function validateUpdateEmployee()
	{
		var pattern = /[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]/;
		var empId = document.getElementsByName("empId")[0].value;

		var fname = document.getElementsByName("fname")[0].value;
		var lname = document.getElementsByName("lname")[0].value;
		var workDesc = document.getElementsByName("workDes")[0].value;
		
		var position = document.getElementsByName("position")[0].value;
		var address = document.getElementsByName("address")[0].value;
		var city = document.getElementsByName("city")[0].value;
		var zip= document.getElementsByName("zip")[0].value;
		var email = document.getElementsByName("email")[0].value;
		var state = document.getElementsByName("selectedState")[0].value;
		
		if (!empId || !pattern.test(empId))
		{
			alert("Please provide a valid SSN.\n Format:[0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]");
			return false;
		}
		
		var newEmpId = empId.replace(/-/g, "");
	
		console.log("newEmpId "+newEmpId);
		
		if (isNaN(newEmpId))
		{
			alert("employee id is not a number");
			return false;
		}
		
		if (!fname && !lname && !workDesc && !position && !address && !city && !zip && !email && !state)
		{
			alert("Please provide one or more fields along with employee ID for update.");
			return false;
		}
		
	}

	function validateSearchFlight()
	{
		var flghtNo = $("#flightNo").val();
		var flightTime = $("#flightTime").val();
		var seats = $("#seats").val();
		var reg = /^[0-9]*$/;
		var timeReg = /^[01][0-9]|[2][0-3]:[0-5][0-9]$/;
		
		 if (flghtNo)
		{
			if (!reg.test(flghtNo))
			{
				alert('Flight number should be a number');
				return false;
			}
		} else if (flightTime)
		{
			if (!timeReg.test(flightTime))
			{
				alert('Invalid time format. (00:00 to 23:59)');
				return false;
			}
		} else if (seats)
		{
			if (!reg.test(seats))
			{
				alert('seats should be number');
				return false;
			}
		} else if (source || destination)
		{
			if (source == "" || destination == "")
			{
				alert('Source or destination is invalid name');
			}
		}

	}

	function validateUpdateFlight()
	{
		var flghtNo = $("#flightNo").val();
		var flightTime = $("#flightTime").val();
		var seats = $("#seats").val();
		var source = $("#source").val();
		var destination = $("#destination").val();

		var reg = /^[0-9]$/;
		var timeReg = /^[01][0-9]|[2][0-3]:[0-5][0-9]$/;
		if (!flghtNo)
		{
			alert('Flight number should  not be blank');
			return false;
		} else if (flghtNo)
		{
			if (!reg.test(flghtNo))
			{
				alert('Flight number should  be a number');
				return false;
			}
		} else if (flightTime)
		{
			if (!timeReg.test(flightTime))
			{
				alert('Invalid time format. (00:00 to 23:59)');
				return false;
			}
		} else if (seats)
		{
			if (!reg.test(seats))
			{
				alert('seats should be number');
				return false;
			}
		} else if (source || destination)
		{
			if (source == "" || destination == "")
			{
				alert('Source or destination is invalid name');
			}
		}
	}
</script>

</head>
<body id="page2">
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
					<h2>Your Flight Planner</h2>

					<ul id="categories">
						<li style="border-top: 0;"><a
							href="Admin.jsp?task=addEmployee" id="createEmp">Create New
								Employee</a></li>
						<li><a href="Admin.jsp?task=deleteEmployee" id="delEmp">Delete
								Employee</a></li>
						<li><a href="Admin.jsp?task=deleteCustomer">Delete
								Customer</a></li>
						<li><a href="EmployeeServlet?task=4">List All Employees</a></li>
						<li><a href="EmployeeServlet?task=5">List All Customers</a></li>
						<li><a href="EmployeeServlet?task=6">List All
								Reservations</a></li>
						<li><a href="EmployeeServlet?task=7">List All Flights</a></li>
						<li><a href="Admin.jsp?task=searchFlight">Search Flight</a></li>
						<li><a href="EmployeeServlet?task=10">Update Flight Info</a></li>
						<li><a href="EmployeeServlet?task=8">Update Customer Info</a></li>
						<li><a href="EmployeeServlet?task=9">Update Employee Info</a></li>
						<li><a href="Admin.jsp?task=searchEmployee"
							id="searchEmployee">Search Employee</a></li>
						<li><a href="Admin.jsp?task=searchTraveller">Search Traveller</a></li>


					</ul>
				</div>
			</article>
			<article class="col2 pad_left1">
				<%
					String message = request.getParameter("message");
					if (message != null)
					{
				%>
				<p><%=message%></p>
				<%
					}
				%>

				<!-- ************************************************************************** -->
				<%
					if (request.getParameter("task") != null && "addEmployee".equals(request.getParameter("task")))
					{
				%>
				<div id="div1">
					<%
						airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();
							//proxy.setEndpoint("http://localhost:8080/Airline_Management/services/AirlineServer?wsdl");

							String[] stateList = proxy.fetchStateList();
					%>

					<h2>Contact Form</h2>
					<form id="ContactForm" method="post" action="LoginServlet"
						name="form1">
						<div>
							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="firstName" />
								</div>
								First Name:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="lastName" />
								</div>
								Last Name:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="email" class="input" name="email" />
								</div>
								Your E-mail:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="password" class="input" name="password" />
								</div>
								Password:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="address" />
								</div>
								Address:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type=text class="input" name="city" />
								</div>
								City:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="date" class="input" name="DOB" />
								</div>
								Date Of Birth:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="position" />
								</div>
								Designation:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="workDescription" />
								</div>
								Work Description:<br />
							</div>


							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="zipCode" />
								</div>
								Zip Code:<br />
							</div>

							<div class="wrapper">
								<div class="bg">
									<span style="display: block; float: left;"> <select
										name="selectedState" id="selectedState"
										style="margin-top: 10px;">
											<%
												for (int i = 0; i < stateList.length; i++)
													{
											%>
											<option value=<%=stateList[i]%>><%=stateList[i]%></option>
											<%
												}
											%>
									</select>
									</span>
								</div>
								State:<br />
							</div>

							<br> <input type="submit" name="Login" class="button1"
								value="Add Employee" onclick="return validateEmployeeDetails();" style="margin-bottom: 20px;"><br>
						</div>
					</form>

				</div>
				<%
					}
				%>

				<!-- *************************************Delete Employee************************************* -->

				<%
					if (request.getParameter("task") != null && "deleteEmployee".equals(request.getParameter("task")))
					{
				%>

				<div id="div2">
					<form id="ContactForm" method="post" action="LoginServlet"
						name="form2">
						<h2>Enter email ID to delete User!</h2>
						<div>
							<div class="wrapper">
								<div class="bg">
									<input type="email" class="input" name="emailID" />
								</div>
								Email ID:<br />
							</div>

							<br> <input type="submit" name="Login" class="button1"
								value="Delete Employee" style="margin-bottom: 20px;"><br>
						</div>
					</form>
				</div>
				<%
					}
				%>


				<!-- *************************************Delete Customer************************************* -->

				<%
					if (request.getParameter("task") != null && "deleteCustomer".equals(request.getParameter("task")))
					{
				%>

				<div id="div2">
					<form id="ContactForm" method="post" action="LoginServlet"
						name="form2">
						<h2>Enter email ID to delete customer!</h2>
						<div>
							<div class="wrapper">
								<div class="bg">
									<input type="email" class="input" name="emailID" />
								</div>
								Email ID:<br />
							</div>

							<br> <input type="submit" name="Login" class="button1"
								value="Delete Customer" style="margin-bottom: 20px;"><br>
						</div>
					</form>
				</div>
				<%
					}
				%>

				<!-- *********************************List ALL Employee***************************************** -->
				<%if (request.getParameter("task") != null && "listAllEmp".equals(request.getParameter("task")))
					{	
								if(request.getAttribute("employees")!=null){
										Employee[] employees = (Employee[])request.getAttribute("employees");
				%>						
										<table id ="flightDisplay">	
											<tr><th>FIRST NAME</th><th>LAST NAME</th><th>DATE OF BIRTH</th><th>ADDRESS</th><th>EMPLOYEE ID</th>
											<th>WORK DESCRIPTION</th><th>POSTION</th><th>HIRE DATE</th></tr>					
				<% 						for(Employee emp : employees){
				%>
											<tr><td><%=emp.getFirstName() %></td><td><%=emp.getLastName()%></td><td><%=emp.getDateOfBirth()%></td><td><%=emp.getAddress()%></td>
											<td><%=emp.getEmployeeID()%></td><td><%=emp.getWorkDescription()%></td><td><%=emp.getPosition()%></td><td><%=emp.getHireDate()%></td></tr>	
				<%						}%>
										</table>
				<% 				}
					}%>
				<!-- *********************************End List all Employee***************************************** -->


				<!-- *********************************Search Employee***************************************** -->
				<%
					if (request.getParameter("task") != null && "searchEmployee".equals(request.getParameter("task")))
					{
				%>
				<!-- Abinaya's changes for Search Employee starts here-->
				<div id="div10">
					<form id="ContactForm" action="LoginServlet" method="POST"
						name="form10">
						<h2>Search Employee</h2>
						<div>
							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="empID" id="empID" />
								</div>
								Employee ID:<br />
							</div>
							<div class="wrapper">
								<div class="bg">
									<input type="text" class="input" name="empDesc"
										id="description" />
								</div>
								Work Description:<br />
							</div>
							<div class="wrapper">
								<div class="bg">
									<input type="date" class="input" name="hireDate" id="hireDate" />
								</div>
								Hired Date:<br />
							</div>

							<br> <input type="button" name="Login" class="button1"
								value="Search Employee" onclick="searchEmp()"
								style="margin-bottom: 20px;"><br> <br>
						</div>
					</form>
				</div>
				<div class="bg" id="searchDispEmp"></div>
				<script>
					function searchEmp()
					{
						var xmlHttpReq;
						// Mozilla/Safari
						if (window.XMLHttpRequest)
						{
							xmlHttpReq = new XMLHttpRequest();
						}
						// IE
						else if (window.ActiveXObject)
						{
							xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
						}
						xmlHttpReq.onreadystatechange = function()
						{
							if (xmlHttpReq.readyState == 4)
							{
								var ajaxDisplay = document
										.getElementById('searchDispEmp');
								ajaxDisplay.innerHTML = xmlHttpReq.responseText;
							}
						}
						var param = "empID="
								+ document.getElementById('empID').value
								+ "&description="
								+ document.getElementById('description').value
								+ "&hireDate="
								+ document.getElementById('hireDate').value
								+ "&Login=Search Employee";
						xmlHttpReq.open("POST", "LoginServlet?" + param, true);
						xmlHttpReq.setRequestHeader("Content-length",
								param.length);
						xmlHttpReq.send(null);

					}
				</script>
				<%
					}
				%>
				<!-- End of changes by Abinaya -->


				<!-- SHRUTHI CHANGES -->
						<%if(request.getParameter("task")!=null && "searchTraveller".equals(request.getParameter("task"))){ %>
				<form  id="ContactForm" action="EmployeeServlet" method="post">
						<h2>SEARCH TRAVELLER DETAILS</h2>
									<div>
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="firstName" id="firstName" />
											</div>
											FISRT NAME:<br />
										</div> 
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="lastName" id="lastName" />
											</div>
											LAST NAME:<br />
										</div> 
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="emailID" id="emailID" />
											</div>
											EMAIL ID:<br />
										</div> 
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="passportNo" id="passportNo" />
											</div>
											PASSPORT NO:<br />
										</div>
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="travellerId" id="travellerId" />
											</div>
											TRAVELLER ID:<br />
										</div> 
										<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="nationality" id="nationality" />
											</div>
											NATIONALITY:<br />
										</div><br/>
										<br/>
										<input type="hidden" name="task" value="12"/>
											<div class="wrapper">
												<div class="bg">
												<input type="submit" class="input"  value="Search" /><br />
												<input type="reset" class="input"  value="Clear" />
												</div>
											</div>
										</div>	
									</form>
					<%} %>
					
					<%if(request.getParameter("task")!=null && "searchTravellerForAttrib".equals(request.getParameter("task"))){
						Traveller[] travellers = (Traveller[])request.getAttribute("straveller");
						if(travellers!=null){%>
							<table id="flightDisplay">
									<tr><th>  FIRST NAME  </th>
									<th>  LAST NAME  </th>
									<th >  Email ID  </th>
									<th >  PASSPORT NO  </th>
									<th >  TravellerID  </th>
									<th >  NATIONALITY  </th>
									</tr>
							<%
					   			for(Traveller traveller : travellers){%>
					   				<tr><td style="width: 50px;"><%=traveller.getFirstName()%></td>
					   				<td style="width: 50px;"><%=traveller.getLastName()%></td>
					   				<td style="width: 100px;"><%=traveller.getEmailID()%></td>
					   				<td style="width: 50px;"><%=traveller.getPassportNo() %></td>
					   				<td style="width: 75px;"><%=traveller.getTravellerId()%></td>
					   				<td style="width: 75px;"><%=traveller.getNationality()%></td>
					   				</tr>
					   			<%} %>		
						<%}else{%>
							<p>NO Traveller FOUND FOR THE GIVEN PARAMETERS</p>
						<%}
						}%>
				<!-- *********************************** Search Flights -- Pradyumna*************************************** -->
				<%
					if (request.getParameter("task") != null && "searchFlightForAttrib".equals(request.getParameter("task")))
					{
						FlightDetails[] flights = (FlightDetails[]) request.getAttribute("sflights");
						if (flights != null)
						{
				%>
				<br>
				<table id="flightDisplay">
					<tr>
						<th>Flight Number</th>
						<th>Airline Name</th>
						<th>Source</th>
						<th>Destination</th>
						<th>Seats Available</th>
						<th>Flight Time</th>
					</tr>
					<%
						for (FlightDetails flight : flights)
								{
									int rowCount = 0;
									if (rowCount % 2 == 0)
									{
					%>
					<tr>
						<%
							}
										else
										{
						%>
					
					<tr class="alt">
						<%
							}
						%>
						<td><%=flight.getFlightNumber()%></td>
						<td><%=flight.getAirlineName()%></td>
						<td><%=flight.getSource()%></td>
						<td><%=flight.getDestination()%></td>
						<td><%=flight.getNumberOfSeats()%></td>
						<td><%=flight.getFlightTime()%></td>
					</tr>
					<%
						rowCount++;
								}
					%>
				</table>
				<br>
				<%
					}
						else
						{
				%>
				<form id="ContactForm">
					<h2>NO FLIGHTS FOUND FOR THE GIVEN PARAMETERS</h2>
				</form>
				<%
					}
					}
				%>
				<!-- search flight ends -->


				<!-- *********************************** Search Flights based on Attributes -- Pradyumna*************************************** -->
				<!-- SEARCH FLIGHT BASED ON ATTRIBUTES -->
				<%
					if (request.getParameter("task") != null && "searchFlight".equals(request.getParameter("task")))
					{
				%>
				<form id="ContactForm" action="EmployeeServlet" method="post"
					onsubmit="return validateSearchFlight()">
					<h2>UPDATE FLIGHT DETAILS</h2>
					<div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightNo" id="flightNo" />
							</div>
							FLIGHT NUMBER:<br />
						</div>
						,
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightName"
									id="flightName" />
							</div>
							AIRLINE NAME:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="source" id="source" />
							</div>
							SOURCE:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="destination"
									id="destination" />
							</div>
							DESTINATION*:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightTime"
									id="flightTime" />
							</div>
							FLIGHT TIME:<br />
						</div>
						<input type="hidden" name="task" value="11" />
						<div class="wrapper">
							<div class="bg">
								<input type="submit" class="button1" value="Search" /> <input
									type="reset" class="button1" value="Clear" />
							</div>
						</div>
					</div>
				</form>
				<%
					}
				%>


				<!-- *********************************** Update Flights --*************************************** -->
				<!-- UPDATE FLIGHT DETAILS  -->
				<%
					if (request.getParameter("task") != null && "10".equals(request.getParameter("task")))
					{
				%>

				<form id="ContactForm" action="EmployeeServlet" method="post"
					onsubmit="return validateUpdateFlight()">
					<h2>UPDATE FLIGHT DETAILS</h2>
					<div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightNo" id="flightNo" />
							</div>
							FLIGHT NUMBER*:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightName"
									id="flightName" />
							</div>
							AIRLINE NAME:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="source" id="source" />
							</div>
							SOURCE:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="destination"
									id="destination" />
							</div>
							DESTINATION*:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="flightTime"
									id="flightTime" />
							</div>
							FLIGHT TIME:<br />
						</div>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" name="seats" id="seats" />
							</div>
							NUMBER OF SEATS:<br />
						</div>
						<input type="hidden" name="task" value="10" />
						<div class="wrapper">
							<div class="bg">
								<input type="submit" class="button1" value="Update" /> <input
									type="reset" class="button1" value="Clear" />
							</div>
						</div>
					</div>
				</form>
				<%
					}
				%>
				<!-- UPDATE FLIGHT DETAILS ENDS:PRADYUMNA -->


				<!-- *********************************** TRAVELLER INFO -- RUSHAB*************************************** -->
				<!-- UPDATE TRAVELLER INFO : RUSHAB CHANGES -->
				<%
					if (request.getParameter("task") != null && "8".equals(request.getParameter("task")))
					{
				%>
				<div class="update customer">
					<h2>Update Traveller Information</h2>
					<%
						airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();
							//proxy.setEndpoint("http://localhost:8080/Airline_Management/services/AirlineServer?wsdl");

							String[] stateList = proxy.fetchStateList();
					%>
					
					<form action="EmployeeServlet" method="post" id="ContactForm"
						onsubmit="return validateUpdateTraveler()">

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="email" name="email" />
							</div>
							EMAIL ID:<br />
						</div>
						

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="fname" name="fname" />
							</div>
							FIRST NAME:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="lname" name="lname" />
							</div>
							LAST NAME:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="address" name="address" />
							</div>
							ADDRESS:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="city" name="city" />
							</div>
							CITY:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="zip" name="zip" />
							</div>
							ZIPCODE:<br />
						</div>

						
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="dob"
									name="dob" />
							</div>
							DATE OF BIRTH :<br />
						</div>
						
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="nationality"
									name="nationality" />
							</div>
							NATIONALITY:<br />
						</div>
						
						<div class="wrapper">
											<div class="bg">
												<input type="text" class="input" name="passport" id="passport" />
											</div>
											PASSPORT:<br />
						</div><br/>
						
						
						<div class="wrapper">
								<div class="bg">
									<span style="display: block; float: left;"> <select
										name="selectedState" id="selectedState"
										style="margin-top: 10px;">
											<%
												for (int i = 0; i < stateList.length; i++)
												{
											%>
											<option value=<%=stateList[i]%>><%=stateList[i]%></option>
											<%
												}
											%>
									</select>
									</span>
								</div>
								State:<br />
							</div>

						<input type="hidden" name="task" value="8" /> <input
							type="submit" value="UPDATE" class="button1">

					</form>

				</div>
				<%
					}
				%>

				<!-- *********************************** Update Employee -- RUSHAB*************************************** -->
				<!--UPDATE EMPLOYEE INFO-->
				<%
					if (request.getParameter("task") != null && "9".equals(request.getParameter("task")))
					{
						airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();
						//proxy.setEndpoint("http://localhost:8080/Airline_Management/services/AirlineServer?wsdl");

						String[] stateList = proxy.fetchStateList();

				%>
				<div class="update employee">
					<form id="ContactForm" action="EmployeeServlet" method="post"
						onsubmit="return validateUpdateEmployee()">
					<h2>Please provide Employee details to update.</h2>
						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="empId" name="empId" />
							</div>
							EMPLOYEE ID*:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="fname" name="fname" />
							</div>
							FIRST NAME:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="lname" name="lname" />
							</div>
							LAST NAME:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="workDes" name="workDes" />
							</div>
							WORK DESCRIPTION:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="position" name="position" />
							</div>
							POSITION:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="address" name="address" />
							</div>
							ADDRESS:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="city" name="city" />
							</div>
							CITY:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="zip" name="zip" />
							</div>
							ZIPCODE:<br />
						</div>

						<div class="wrapper">
							<div class="bg">
								<input type="text" class="input" id="email" name="email" />
							</div>
							EMAIL ID:<br />
						</div>
						
						<div class="wrapper">
							<div class="bg">
								<input type="date" class="input" id="DOB" name="DOB" />
							</div>
							DATE OF BIRTH:<br />
						</div>
						
						<div class="wrapper">
								<div class="bg">
									<span style="display: block; float: left;"> <select
										name="selectedState" id="selectedState"
										style="margin-top: 10px;">
											<%
												for (int i = 0; i < stateList.length; i++)
												{
											%>
											<option value=<%=stateList[i]%>><%=stateList[i]%></option>
											<%
												}
											%>
									</select>
									</span>
								</div>
								State:<br />
						</div>
						
						<input type="hidden" name="task" value="9" /> <input
							type="submit" value="UPDATE" class="button1">
							
						<br><br><br><h3>PS: Employee ID is mandatory and value cannot be updated</h3>	
					</form>

				</div>
				<%
					}
				%>
				<!-- *********************************** LIST ALL RESERVATIONS-- RUSHAB*************************************** -->

				<!--LIST ALL RESERVATIONS-->
				<%
					if (request.getParameter("task") != null && "6".equals(request.getParameter("task")))
					{
				%>
				<div class="list reservations">
					<form action="EmployeeServlet" method="post" id="ContactForm">
						<h2>Following Reservations found!</h2>
						<table id="flightDisplay">
							<tr>
								<th>ID</th>
								<th>Flight No.</th>
								<th>Airlines</th>
								<th>Source</th>
								<th>Destination</th>
								<th>Seats</th>
								<th>Email</th>
							</tr>

							<%
								Reservation[] reservations = (Reservation[]) session.getAttribute("reservations");
									int rowCnt = 0;
									for (Reservation reservation : reservations)
									{
										if (rowCnt % 2 == 0)
										{
							%>
							<tr>
								<%
									}
											else
											{
								%>
							
							<tr class="alt">
								<%
									}
								%>
								<td><%=reservation.getReservationId()%></td>
								<td><%=reservation.getFlightNumber()%></td>
								<td><%=reservation.getAirlineName()%></td>
								<td><%=reservation.getSource()%></td>
								<td><%=reservation.getDestination()%></td>
								<td><%=reservation.getNumberOfSeats()%></td>
								<td><%=reservation.getEmail()%></td>
							</tr>
							<%
								rowCnt++;
									}
							%>
						</table>
						<br>
					</form>

				</div>
				<%
					}
				%>
				<!--LIST ALL RESERVATIONS-->

				<!-- *********************************** LIST ALL FLIGHTS-- RUSHAB*************************************** -->

				<!--LIST ALL FLIGHTS-->
				<%
					if (request.getParameter("task") != null && "7".equals(request.getParameter("task")))
					{
				%>
				<div class="list flights">


						<%
								FlightDetails[] flights = (FlightDetails[]) request.getAttribute("flights");
									if (flights!= null)	
									{	
										int rowCnt = 0;
							%>
						<br><table id="flightDisplay">
							<tr>
								<th>Flight No.</th>
								<th>Airlines</th>
								<th>Source</th>
								<th>Destination</th>
								<th>Seats</th>
								<th>Crew ID</th>
								<th>Passenger Details</th>
							</tr>
							<%			
										
										for (FlightDetails flight : flights)
										{
											if(rowCnt%2==0){
								%>

							<tr>
							<%} else{ %>
							<tr class="alt">
							<%} %>
								<td><%=flight.getFlightNumber()%></td>
								<td><%=flight.getAirlineName()%></td>
								<td><%=flight.getSource()%></td>
								<td><%=flight.getDestination()%></td>
								<td><%=flight.getNumberOfSeats()%></td>
								<td><%=flight.getCrewId()%></td>
								<form action="EmployeeServlet" method="get">
									<input type="hidden" value="11" name="task"/>
									<input type="hidden" name="flightNo" value="<%=flight.getFlightNumber()%>"/>
								<td>
									<input type="submit" value="Passenger Details"/>
								</td>
								</form>
								</tr>
								<%
									rowCnt++;
										}
									} else{
								%>
								<h2>No Flights Found</h2>
								<%} %>
							
						</table><br>

				</div>
				<%
					}
				%>
				<!--LIST ALL FLIGHTS-->
				<!-- RUSHAB CHANGES -->
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
	<script type="text/javascript">
		Cufon.now();
	</script>
	<!-- END PAGE SOURCE -->
</body>
</html>