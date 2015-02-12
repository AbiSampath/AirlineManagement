<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/LoginStyle.css">
<title>Insert title here</title>
</head>
<body>
<%@ page import="beans.FlightDetails"%>
<% String disp = request.getParameter("method");
	if("1".equals(disp)){	
%>
<h2>SEARCH FLIGHT</h2>
<form name="flightSearch" action="TravelServlet" method="post">
<p>
	<label for="login">SOURCE:</label> <input type="text" name="source" placeholder="Source"/>
</p>

<p>
 <label for="login">DESTINATION:</label><input type="text" name="destination" placeholder="Destination"/> 
 </p> 
 <input type="hidden" name="method" value="1"/>
 <p class="login-submit">
 <input type="submit" value="Search Flight"/> 
 </p>
</form>


<%}else if("2".equals(disp)){ %>
<table>
<%	FlightDetails[] flights = (FlightDetails[])request.getAttribute("flights");
	if(flights!=null){
%>
	<tr><th>Flight Number</th><th>Airline Name</th><th>SOURCE</th><th>DESTINATION</th><th>NUMBER OF SEATS</th></tr>
<%	int flightCnt=0;
	for(FlightDetails flight : flights){ %>
	<tr><td><%=flight.getFlightNumber() %></td><td><%=flight.getAirlineName() %></td><td><%=flight.getSource() %></td>
	<td><%=flight.getDestination()%></td><td><%=flight.getNumberOfSeats()%></td></tr>
	<tr><td>
	<form action="TravelServlet" method="post">
	<input type="hidden" name="flightCnt" value="<%=flightCnt%>"/>
	<input type="hidden" name="method" value="1"/>
	<input type="submit" value="BOOK"/></form>
	</td></tr>
<%}} %>
</table>
<%} %>
</body>
</html>