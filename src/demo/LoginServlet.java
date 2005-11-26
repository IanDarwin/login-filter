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
	public void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	@Override
	public void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("username");
		String pass = request.getParameter("password");

        // Authenticate user
        boolean logged = name.equals("framus") && pass.equals("barstool");
        if (logged) {
        	System.err.printf("User %s LOGGED IN", name);
        	request.getSession().setAttribute("LOGIN_FLAG", "yoda");
        }
        
        response.sendRedirect(logged ? "/" : "loginfailure.jsp");
    }
}
