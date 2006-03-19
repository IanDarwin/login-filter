<html>
<head>
	<title>Login</title>
</head>

<body>
<h3>Login</h3>
<hr>

<form method="post" action="/loginServlet" >
  
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
