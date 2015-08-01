<html>
<head>
	<title>Please Log In</title>
</head>

<body>
<h3>Login</h3>
<hr>

<%
	String mesg = (String)request.getAttribute("message");
	if (mesg != null) {
		out.println("<p style='color:red;'>" + mesg + "</p>");
		request.removeAttribute("message");
	}
%>

<form method="post" action="loginServlet" >

<table border="0">
  <tr>
    <td>Username </td>
    <td>
		<input type="text" name="username"/>
         <em><font size="-2">(required)</font></em>
    </td>
  </tr>
  <tr>
    <td>Password </td>
    <td>
         <input type="password" name="password"/>
         <em><font size="-2">(required)</font></em>
    </td>
  </tr>
</table>

  <p>
    <input type="submit" value="Login"/>
  </p>

</form>

<p>Thanks for logging in to our site.</p>

</body>
</html>
