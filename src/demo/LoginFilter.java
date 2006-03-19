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
 * This is the Servlet Filter for the login mechanism. 
 */
public class LoginFilter implements Filter {

	private static final String HOME = "/";
	
	public void init(FilterConfig config) throws ServletException {
		System.out.println("LoginFilter.init()");
	}

	/** Allow the request if the user is logged in.
	 * Called before every request, so keep it light weight.
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, 
			FilterChain chain) throws IOException, ServletException {

		System.out.println("LoginFilter.doFilter()");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		Object loggedInToken = request.getSession().getAttribute("LOGIN_FLAG");

		if (loggedInToken != null) {
			// User is logged in, continue processing.
			chain.doFilter(req, resp);
			return;
		}
		
		if (request.getRequestURI().indexOf(HOME) != -1) {
			// User not logged in and trying to get to login page
			chain.doFilter(req, resp);
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

	public void destroy() {
		System.out.println("LoginFilter.destroy()");
	}
}
