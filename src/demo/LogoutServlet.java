package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * This class is the Logout handler.
 */
public class LogoutServlet extends HttpServlet {	

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = LoginStuff.getUserName(request);
		if (name == null) {
			System.out.printf("Non-logged-in user got to LogoutServlet from %s%n", request.getRemoteAddr());
		}
		System.out.printf("Logging out user %s%n", name);
		
        request.getSession().removeAttribute(LoginStuff.LOGIN_FLAG);
        
        response.sendRedirect("/");
    }


}
