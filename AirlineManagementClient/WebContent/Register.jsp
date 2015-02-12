<%@page import="airlineSystem.AirlineServerProxy"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/LoginStyle.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css" />
<title>Register Me</title>

<script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
<script type="text/javascript">

function validateForm()
{
	var firstName = document.getElementsByName("firstName")[0].value;
	var lastName = document.getElementsByName("lastName")[0].value;
	var email = document.getElementsByName("emailID")[0].value;
	var password =  document.getElementsByName("password")[0].value;
	
	var dob = document.getElementsByName("DOB")[0].value;
	var address = document.getElementsByName("address")[0].value;
	var city = document.getElementsByName("city")[0].value;
	var zipCode = document.getElementsByName("zipCode")[0].value;
	
	
	var passwordFilter =/((?=.*\d)(?=.*[A-Z])(?=.*[@#$%]).{6,20})/;
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var zipCodeFilter1 = /^[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]$/;
    var zipCodeFilter2 = /^[0-9][0-9][0-9][0-9][0-9]$/;
    
    if (!zipCodeFilter1.test(zipCode) && !zipCodeFilter2.test(zipCode))
    {
    	window.alert("Invalid Zipcode. \nPlease enter in format: [0-9][0-9][0-9][0-9][0-9] or \n [0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]");
    	return false;
    }
    
	if (!firstName || !lastName || !email || !password || !dob || !address || !city || !zipCode)
	{
		window.alert("One or more fields are blank ! Please provide the necessary details");
		return false;
	}
	
	if (firstName.length > 32)
	{
		window.alert("First name too long ! Name cannot be more than 32 characters.");
		return false;
	}
	
	if (lastName.length > 32)
	{
		window.alert("Last name too long ! Name cannot be more than 32 characters.");
		return false;
	}
	
	if (!filter.test(email))
	{
		window.alert("Invalid email format !");
		return false;
	}
	
	if (!passwordFilter.test(password))
	{
		window.alert("Password must contains at least one number, an uppercase character and \na special character such as '@#$%''. Length should be at least 6 characters.");
		return false;
	}
	
	return true;
}

function load()
{
	window.resizeTo(600,650);
	window.focus();

}
</script>

</head>
<body">
	<%@ page import="java.util.*"%>
	<%
		airlineSystem.AirlineServerProxy proxy = new airlineSystem.AirlineServerProxy();

		String[] stateList = proxy.fetchStateList();
	%>

	<div align="center" >
	<form name="myForm" action="LoginServlet" method="POST" class="login">
		<p>
      		<label >First Name</label>
      		<input type="text" name="firstName" id="firstName" >
    	</p>
		
		<p>
		    <label >Last Name</label>
      		<input type="text" name="lastName" id="lastName" >
		</p>
		
		<p>
		    <label >Email</label>
      		<input type="text" name="emailID" id="emailID" value="name@example.com">
		</p>
		
		<p>
		    <label >Password</label>
      		<input type="password" name="password" id="password" value="123456">
		</p>
		
		<p>
			 <label >DOB</label>
			<input type="text" id="datepicker" name="DOB" />
		</p>
		
		
		<p>
		    <label >Address</label>
      		<input type="text" name="address" id="address">
		</p>
		
		<p>
		    <label >City</label>
      		<input type="text" name="city" id="city">
		</p>

		<p>
		    <label >Zip Code</label>
      		<input type="text" name="zipCode" id="zipCode">
		</p>

		<p>
			<div style=" display: block; float: left; ">
			<label >State</label>
 			<select name="selectedState" id = "selectedState" style=" margin-top: 10px;">
 				<%
 					for (int i=0; i<stateList.length;i++)
 					{
 				%>
 				<option value=<%=stateList[i] %>><%=stateList[i] %></option>
 				<%
 					}
 				%>
 			</select>
		</div>
		
		</p>

		<br>
		<p align="right">
			<input type="submit" name="Login" value="Register" onclick=" return validateForm(); ">
		</p>

		</form>
	</div>

</body>
</html>