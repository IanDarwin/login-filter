package demo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * UserNameTag - print the logged-in user's name.
 */
public class UserNameTag extends BodyTagSupport {

	private static final long serialVersionUID = 10282714617838262L;

	/** Invoked at the tag start boundary; does the work */
	public int doStartTag() throws JspException {

		final HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		final JspWriter out = pageContext.getOut();

		Object name = request.getSession().getAttribute(LoginStuff.LOGIN_FLAG);
		
		try {
		if (name == null) {
			out.print("(not logged in)");
		} else {
			out.print(name);
		}
		out.flush();
		} catch (IOException e) {
			System.out.println("UserNameTag.doStartTag(): ERROR: Caught " + e);
		}
		return SKIP_BODY;
	}
}

