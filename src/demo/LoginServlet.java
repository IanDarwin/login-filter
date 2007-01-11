package demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * This class is the Login/Logout handler.
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -1456987092097573503L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		String name = request.getParameter("username");
		String pass = request.getParameter("password");

        // Authenticate user
        if (checkCredentia(name, pass)) {
        	System.out.printf("User LOGGED IN successfully as %s%n", name);
			session.setAttribute(LoginStuff.LOGIN_FLAG, name);
			String target = (String) session.getAttribute(LoginStuff.TARGET_URI_KEY);
        	session.removeAttribute(LoginStuff.TARGET_URI_KEY);
			response.sendRedirect(target != null ? target : "/");
        	return;
        }
        System.out.printf("User DID NOT login in as %s/%s%n", name, pass);
        response.sendRedirect(LoginStuff.LOGIN_PAGE);
    }

	/**
	 * WARNING: for now this is bogus, just a demo;
	 * Check that the user has permission to log in to this site.
	 * @param name The user name
	 * @param pass The "secret" password.
	 * @return True if the user name & password are valid here.
	 */
	private boolean checkCredentia(String name, String pass) {
		return name != null && name.equals("framus") &&
        				 pass != null && pass.equals("barstool");
	}
}
