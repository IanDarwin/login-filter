package demo;

import javax.servlet.http.HttpServletRequest;

public class LoginStuff {
	
	public static final String LOGIN_FLAG = "LOGIN_FLAG";
	public static final String LOGIN_PAGE = "/login.jsp";
	public static final String LOGIN_SERVLET = "/loginServlet";

	/** 
	 * Extract the user name from the session
	 * @param request
	 * @return the user name, or null if the user is not logged in.
	 */
	public static String getUserName(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(LoginStuff.LOGIN_FLAG);
	}
}