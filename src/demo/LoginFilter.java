package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is the Struts "RequestProcessor" for the login mechanism. It must extend
 * TilesRequestProcessor if you are using Tiles, else it should extend the
 * generic Struts RequestProcessor.
 */
public class LoginFilter implements Filter {

	private static final String HOME = "/";

	/** Allow the request if the user is logged in.
	 * Called before every request, so keep it light weight.
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, 
			FilterChain arg2) throws IOException, ServletException {
		

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		Object loggedInToken = request.getSession().getAttribute("LOGIN_FLAG");

		if (loggedInToken != null) {
			// User is logged in
			return;
		}
		
		if (request.getRequestURI().indexOf(HOME) != -1) {
			// User not logged in and trying to get to login page
			return;
		}
			
		try {
			// User not logged in and trying unauthorized access
			// Redirect user to the login page
			response.sendRedirect(response.encodeRedirectURL(HOME));
			return;		
		} catch (IOException e) {
			System.err.println("Caugth exception during non-login redirect" + e);
			throw new RuntimeException(e.toString());	// Do not let them in!
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
}
