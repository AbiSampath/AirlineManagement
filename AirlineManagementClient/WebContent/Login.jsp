<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="css/LoginStyle.css">
<script>
function validateForm1()
{
		console.log("Inside");
		var email = document.form1.userName.value;
		var pattern = /^[A-Z0-9._]+@[A-Z0-9]+\.[A-Z]{2,4}$/i;

		console.log(email);
		if (email == "")
		{
			alert("Email ID should not be blank");
			document.form1.userName.focus();
			return false;
		}
		else if (!pattern.test(email))
		{
			alert("Invalid Email Format");
			return false;
		}

		else if (document.form1.password.value == "")
		{
			alert("Password should not be blank");
			document.form1.password.focus();
			return false;
		}
		
		return true;	
}
</script>
</head>

	<form method="post" action="LoginServlet" class="login" name="form1"
		onsubmit="return validateForm1();">
	<%
		String message = (String) request.getAttribute("message");
		
		if (message == null)
		{
			String fName = (String) session.getAttribute("fName");
			if (fName != null)
			{
				System.out.println("Session exists");
				session.invalidate();
				message = "You have been signed out.";
			}
		}

	%>
	
		<p>
			<label for="login">Email:</label> <input type="text" name="userName"
				id="login" value="emp@emp.com">
		</p>

		<p>
			<label for="password">Password:</label> <input type="password"
				name="password" id="password" value="password">
		</p>

		<p class="login-submit">
			<button type="submit" class="login-button" name="Login" value="Login">Login</button>
		</p>
		
		<p> <span style=" display: left"> <a href="Home.jsp">Home</a>
		<span style=" display: block; float: right"> <a href="Register.jsp">Register</a> </span>
		 </span> 
		</p>
		

	<%
	if (message != null)
	{
	%>	
		<section class="about"> <br>
		<br>
		<h1 align="center" style="font-size: 18px"><%= message %>
		</h1>
		<br>
		<br>
		</section>
	<%
	}
	%>
		</form>

</body>
</html>
