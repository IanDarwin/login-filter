<html>

<head>
<title>another loginfilter demo page</title>
</head>

<body>

<h1>Welcome to 'somepage', <font color='red'><%= request.getRemoteUser() %></font>!</h1>

<p>
If you can see this page, either you logged in successfully,
or you broke security,
or the demo isn't installed correctly!
</p>

<hr/>

<p>In this toy demo, the only thing left to do is to
<a href="logoutServlet">log out again</a>.
</p>

</body>

</html>
