package org.apache.jsp.login.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.services.resources.ResourceBundleService;
import java.util.ResourceBundle;
import org.exoplatform.web.login.InitiateLoginServlet;
import org.gatein.common.text.EntityEncoder;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

  String contextPath = request.getContextPath() ;

  String username = request.getParameter("j_username");
  if(username == null) username = "";
 	String password = request.getParameter("j_password");
 	if(password == null) password = "";

  ResourceBundleService service = (ResourceBundleService) PortalContainer.getCurrentInstance(session.getServletContext())
  														.getComponentInstanceOfType(ResourceBundleService.class);
  ResourceBundle res = service.getResourceBundle(service.getSharedResourceBundleNames(), request.getLocale()) ;
  
  Cookie cookie = new Cookie(InitiateLoginServlet.COOKIE_NAME, "");
	cookie.setPath(request.getContextPath());
	cookie.setMaxAge(0);
	response.addCookie(cookie);

  String uri = (String)request.getAttribute("org.gatein.portal.login.initial_uri");

  response.setCharacterEncoding("UTF-8"); 
  response.setContentType("text/html; charset=UTF-8");

      out.write("\n");
      out.write("<!DOCTYPE html \n");
      out.write("    PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n");
      out.write("           \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n");
      out.write("\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("  <head>\n");
      out.write("    <title>Login</title>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>   \n");
      out.write("    <link rel=\"shortcut icon\" type=\"image/x-icon\"  href=\"");
      out.print(contextPath);
      out.write("/favicon.ico\" />\n");
      out.write("    <link rel='stylesheet' type='text/css' href='");
      out.print(contextPath);
      out.write("/login/skin/Stylesheet.css'/>\n");
      out.write("    <script type=\"text/javascript\" src=\"/eXoResources/javascript/eXo.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"/eXoResources/javascript/eXo/portal/UIPortalControl.js\"></script>\n");
      out.write("  </head>\n");
      out.write("  <body style=\"text-align: center; background: #b5b6b6; font-family: arial, tahoma, verdana\">\n");
      out.write("    <div class=\"UILogin\">\n");
      out.write("      <div class=\"LoginHeader\"></div>\n");
      out.write("      <div class=\"LoginContent\">\n");
      out.write("        <div class=\"CenterLoginContent\">\n");
      out.write("          ");
/*Begin form*/
      out.write("\n");
      out.write("          ");

            if(username.length() > 0 || password.length() > 0) {
               EntityEncoder encoder = EntityEncoder.FULL;
               username = encoder.encode(username);

          
      out.write("\n");
      out.write("          <font color=\"red\">");
      out.print(res.getString("UILoginForm.label.SigninFail"));
      out.write("</font>");
}
      out.write("\n");
      out.write("          <form name=\"loginForm\" action=\"");
      out.print( contextPath + "/login");
      out.write("\" method=\"post\" style=\"margin: 0px;\">\n");
      out.write("                ");
 if (uri != null) { 
      out.write("\n");
      out.write("          \t\t<input type=\"hidden\" name=\"initialURI\" value=\"");
      out.print(uri);
      out.write("\"/>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("          \t\t<table> \n");
      out.write("\t              <tr class=\"FieldContainer\">\n");
      out.write("\t\t              <td class=\"FieldLabel\">");
      out.print(res.getString("UILoginForm.label.UserName"));
      out.write("</td>\n");
      out.write("\t\t              <td><input class=\"UserName\" name=\"username\" value=\"");
      out.print(username);
      out.write("\"/></td>\n");
      out.write("\t\t\t          </tr>\n");
      out.write("\t\t            <tr class=\"FieldContainer\" id=\"UIPortalLoginFormControl\" onkeypress=\"eXo.portal.UIPortalControl.onEnterPress(event);\">\n");
      out.write("\t\t              <td class=\"FieldLabel\">");
      out.print(res.getString("UILoginForm.label.password"));
      out.write("</td>\n");
      out.write("\t\t              <td><input class=\"Password\" type=\"password\" name=\"password\" value=\"\"/></td>\n");
      out.write("\t\t            </tr>\n");
      out.write("\t\t            <tr class=\"FieldContainer\" onkeypress=\"eXo.portal.UIPortalControl.onEnterPress(event);\">\n");
      out.write("\t\t              <td class=\"FieldLabel\"><input type=\"checkbox\" name=\"rememberme\" value=\"true\"/></td>\n");
      out.write("\t\t              <td>");
      out.print(res.getString("UILoginForm.label.RememberOnComputer"));
      out.write("</td>\n");
      out.write("\t\t            </tr>\n");
      out.write("\t\t          </table>\n");
      out.write("\t\t          <div id=\"UIPortalLoginFormAction\" class=\"LoginButton\" onclick=\"login();\">\n");
      out.write("\t\t            <table class=\"LoginButtonContainer\">\n");
      out.write("\t\t            \t<tr>\n");
      out.write("\t\t\t              <td class=\"Button\">\n");
      out.write("\t\t\t                <div class=\"LeftButton\">\n");
      out.write("\t\t\t                  <div class=\"RightButton\">\n");
      out.write("\t\t\t                    <div class=\"MiddleButton\">\n");
      out.write("\t\t\t                    \t<a href=\"#\">");
      out.print(res.getString("UILoginForm.label.Signin"));
      out.write("</a>\n");
      out.write("\t\t\t                    </div>\n");
      out.write("\t\t\t                  </div>\n");
      out.write("\t\t\t                </div>\n");
      out.write("\t\t\t              </td>\n");
      out.write("\t\t\t             </tr>\n");
      out.write("\t\t            </table>\n");
      out.write("\t\t          </div>\n");
      out.write("\t\t          <div class=\"ClearLeft\"><span></span></div>\n");
      out.write("\t\t          <script type='text/javascript'>\t\t\t            \n");
      out.write("              function login() {\n");
      out.write("                document.loginForm.submit();                   \n");
      out.write("              }\n");
      out.write("            </script>\n");
      out.write("\t\t        </form>\n");
      out.write("\t\t        ");
/*End form*/
      out.write("\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("    <span style=\"font-size: 11px; color: #3f3f3f; text-align: center\">");
      out.print(res.getString("UILoginForm.label.Copyright"));
      out.write("</span>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
