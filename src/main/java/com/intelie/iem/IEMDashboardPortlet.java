package com.intelie.iem;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class IEMDashboardPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/normal.jsp";
    private static final String MAXIMIZED_VIEW = "/maximized.jsp";
    private static final String HELP_VIEW = "/help.jsp";

    @Override
    // async requests and responses are processed here
    public void serveResource(ResourceRequest req, ResourceResponse resp) throws PortletException, IOException {
        System.out.println(">>> SERVE RESOURCE");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("teste!!!!");
        writer.close();
    }

    @Override
    // parent page delivery
    public void render(RenderRequest renderRequest, RenderResponse
            renderResponse) throws PortletException, IOException {

//        System.out.println(">>> DO VIEW");
//        if (WindowState.MINIMIZED.equals(request.getWindowState())) {
//            return;
//        }
//
//        if (WindowState.NORMAL.equals(request.getWindowState())) {
//            normalView.include(request, response);
//        } else {
//            maximizedView.include(request, response);
//        }

        System.out.println(">>> rENDER" + renderResponse.createResourceURL());
        PortletContext context = getPortletContext();
        PortletRequestDispatcher rd = context.getRequestDispatcher(NORMAL_VIEW);
        rd.include(renderRequest, renderResponse);
    }

}
