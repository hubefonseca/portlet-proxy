package com.intelie.iem;

import com.intelie.iem.proxy.Connection;

import javax.portlet.*;
import java.io.IOException;

public class IEMDashboardPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/WEB-INF/jsp/dashboard.jsp";
    private static final String HELP_VIEW = "/WEB-INF/jsp/help.jsp";

    protected DashboardResource dashboardResource = new DashboardResource();

    @Override
    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(NORMAL_VIEW);
        dispatcher.include(request, response);
    }

    @Override
    public void doHelp(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PortletRequestDispatcher dispatcher =
                getPortletContext().getRequestDispatcher(HELP_VIEW);
        dispatcher.include(request, response);
    }

    @Override
    // async requests and responses are processed here
    public void serveResource(ResourceRequest req, ResourceResponse resp) throws PortletException, IOException {

        Authentication authentication = new Authentication();
        String cookie = authentication.ensureAuthentication(req, resp);

        String originalUrl = req.getParameter("originalUrl");

        if (originalUrl.contains("originalUrl")) {
            int pos = originalUrl.indexOf("originalUrl=");
            originalUrl = originalUrl.substring(pos + "originalUrl=".length());
        }
        if (originalUrl.contains("/portal/private/classic/") || originalUrl.contains("/portal/public/classic/")) {
            originalUrl = "cometd/handshake";
        }

        if (originalUrl != null) {
            String urlPrefix = originalUrl.contains("?") ? originalUrl.substring(0, originalUrl.indexOf("?")) : originalUrl;

            if (originalUrl.charAt(0) != '/') {
                originalUrl = "/" + originalUrl;
            }

            Connection connection = new Connection();
            if (originalUrl.contains("cometd") && !originalUrl.contains(".js")) {
                connection.returnCometResponse(originalUrl, req, resp);
            } else if (urlPrefix.endsWith(".jpg") || urlPrefix.endsWith(".gif") || urlPrefix.endsWith(".png")) {
                connection.returnImageResponse(originalUrl, req, resp, cookie, urlPrefix);
            } else {
                connection.returnRestResponse(originalUrl, req, resp, cookie, urlPrefix);
            }
        }
    }

}
