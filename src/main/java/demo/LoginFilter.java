package demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
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

		String loggedInToken = (String)request.getSession().getAttribute(LoginConstants.LOGIN_FLAG);

		if (loggedInToken != null) {
			// User is logged in, continue processing, override getRemoteUser in request object.
			System.out.printf("user is %s%n", loggedInToken);
			chain.doFilter(new LoginFilterHTTPServletRequest((HttpServletRequest) req, loggedInToken), resp);
			return;
		}

		if (requestURI.indexOf(LoginConstants.LOGIN_PAGE) != -1 ||
			requestURI.indexOf(LoginConstants.LOGIN_SERVLET) != -1 ||
			requestURI.indexOf(LoginConstants.FAVICON) != -1) {
			// User not logged in and trying to log in
			System.out.println("(trying to login)");
			chain.doFilter(req, resp);
			return;
		}

		try {
			// User not logged in and trying unauthorized access
			// Save where the user was trying to get to:
			request.getSession().setAttribute(LoginConstants.TARGET_URI_KEY, requestURI);
			// Redirect user to the login page
			System.out.println("(-->login)");
			response.sendRedirect(LoginConstants.LOGIN_PAGE);
			// DO NOT do chain.doFilter for this case!
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

	/**
	 * Delegating HTTPServletRequest that just overrides a few methods such as getRemoteUser(), since
	 * we are handling our own login...
	 */
	public class LoginFilterHTTPServletRequest extends HttpServletRequestWrapper {

		private String userName;

		public LoginFilterHTTPServletRequest(HttpServletRequest request, String userName) {
			super(request);
			this.userName = userName;
		}

		@Override
		public String getRemoteUser() {
			return this.userName;
		}
	}
}
