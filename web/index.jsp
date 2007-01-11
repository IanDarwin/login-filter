<html>

<head>
<title>the loginfilter demo page</title>
</head>

<body>

<h1>Welcome, <font color='green'><%= request.getRemoteUser() %></font>!</h1>

<p>
If you can see this page, either you logged in successfully,
or you broke security,
or the demo isn't installed correctly!
</p>

<hr/>

<p>In this toy demo, the only things you can do now are
<a href="somepage.jsp">visit this test page</a> or
<a href="logoutServlet">log out</a>.
</p>

</body>

</html>
