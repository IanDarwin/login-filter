<html>

<h1>Welcome, <%= demo.LoginStuff.getUserName(request) %>!</h1>

<p>
If you can see this page, either you logged in successfully,
or you broke security, 
or the demo isn't installed correctly!
</p>

<hr/>

<p>In this toy demo, the only thing you can do now is
<a href="/logoutServlet">log out again</a>.
</p>

</html>
