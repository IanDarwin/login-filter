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

        // Authenticate user - use our this.login() for demo, or request.login() for real,
		// after setting up a JavaEE Realm.
		try {
			login(name, pass);
		} catch (ServletException ex) {
			System.out.printf("User DID NOT LOGIN in as %s%n", name);
			request.setAttribute("message", "Login failed, please try again");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		// This will make request.getRemoteUser work, with our hack in the filter.
		// Not needed if you are using request.login().
		session.setAttribute(LoginConstants.LOGIN_FLAG, name);
		System.out.printf("User LOGGED IN successfully as %s%n", name);
		String target = (String) session.getAttribute(LoginConstants.TARGET_URI_KEY);
		session.removeAttribute(LoginConstants.TARGET_URI_KEY);
		response.sendRedirect(target != null ? target : ".");
		return;
    }

	/**
	 * This is coded to be similar in API to the Servlet 3.0
	 * "request.login" method, which uses the Java EE Realm.
	 * WARNING: for now this is bogus, just a demo;
	 * Check that the user has permission to log in to this site.
	 * @param name The user name
	 * @param pass The "secret" password.
	 * @return True if the user name & password are valid here.
	 */
	private void login(String name, String pass) throws ServletException {
		if (name != null && name.equals("framus") &&
        				 pass != null && pass.equals("barstool")) {
			return;
		}
		throw new ServletException("Invalid login");
	}
}
