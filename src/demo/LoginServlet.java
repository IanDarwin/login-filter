package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is the Login/Logout handler.
 */
public class LoginServlet extends HttpServlet {	

	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("username");
		String pass = request.getParameter("password");

        // Authenticate user
        boolean logged = name != null && name.equals("framus") && pass != null && pass.equals("barstool");
        if (logged) {
        	System.out.printf("User LOGGED IN successfully as %s%n", name);
        	request.getSession().setAttribute(LoginStuff.LOGIN_FLAG, name);
        	response.sendRedirect("/");
        	return;
        }
        System.out.printf("User DID NOT login in as %s/%s%n", name, pass);
        response.sendRedirect("loginfailure.jsp");
    }
}
