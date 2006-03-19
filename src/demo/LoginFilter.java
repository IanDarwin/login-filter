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

	
	public void init(FilterConfig config) throws ServletException {
		System.out.println("LoginFilter.init()");
	}

	/** Allow the request if the user is logged in.
	 * Called before every request, so keep it light weight.
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, 
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String requestURI = request.getRequestURI();
		System.out.printf("LoginFilter.doFilter(): requestURI = %s; ", requestURI);

		String loggedInToken = (String)request.getSession().getAttribute(LoginStuff.LOGIN_FLAG);

		if (loggedInToken != null) {
			// User is logged in, continue processing.
			System.out.printf("user is %s%n", loggedInToken);
			chain.doFilter(req, resp);
			return;
		}		

		if (requestURI.indexOf(LoginStuff.LOGIN_PAGE) != -1 ||
			requestURI.indexOf(LoginStuff.LOGIN_SERVLET) != -1) {
			// User not logged in and trying to log in
			System.out.println("(trying to login)");
			chain.doFilter(req, resp);
			return;
		}
			
		try {
			// User not logged in and trying unauthorized access
			// Redirect user to the login page
			System.out.println("(-->login)");
			response.sendRedirect(LoginStuff.LOGIN_PAGE);
			return;		
		} catch (IOException e) {
			System.out.println();
			System.err.println("Caught exception during non-login redirect" + e);
			throw new RuntimeException(e.toString());	// Do not let them in!
		}
	}

	public void destroy() {
		System.out.println("LoginFilter.destroy()");
	}
}
