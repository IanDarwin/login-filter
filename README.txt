Servlet-Filter-based Login mechanism

This is a skeleton of a Filter-based Login mechanism that I wrote to 
compare with a Struts-based login.

As long as I maintain it, you can get it from my anonymous CVS repository
on cvs.darwinsys.com, login anoncvs, module login-filter. The struts-based
version is there too, as module login-struts.

It is necessarily incomplete (there is no database checking so the login name and
password are hardcoded); in short, it has been cut down to just the bare essentials 
needed to demonstrate this type of login mechanism.

You can use this for anything without fee.

For a more comprehensive implementation of this idea (unrelated to my
implementation), look  for a project called "securityfilter" (which is currently at
http://securityfilter.sourceforge.net/).

All paths have been de-absolutized (removed leading slashes) so you can
use this as an Eclipse Web Tools project without having to wait for
WTP to support virtual hosting.

Ian Darwin
http://www.darwinsys.com/
