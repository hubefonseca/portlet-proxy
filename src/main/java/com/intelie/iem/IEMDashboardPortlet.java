package com.intelie.iem;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class IEMDashboardPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/WEB-INF/jsp/dashboard.jsp";
    private static final String HELP_VIEW = "/WEB-INF/jsp/help.jsp";

//    public void processAction(ActionRequest request, ActionResponse response) throws PortletException, IOException {
//
//        QName qname = new QName("http:sun.com/events", "Continent");
//        String value = request.getParameter("continent");
//        Continent continent = new Continent();
//        continent.setName(value);
//        ResourceBundle rb = getPortletConfig().getResourceBundle(request.getLocale());
//        continent.setDescription(rb.getString(value));
//        response.setEvent(qname, continent);
//    }

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
        System.out.println(">>> SERVE RESOURCE");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.print("teste!!!!");
        writer.close();
    }
//
//    @Override
//    // parent page delivery
//    public void render(RenderRequest renderRequest, RenderResponse
//            renderResponse) throws PortletException, IOException {
//
////        System.out.println(">>> DO VIEW");
////        if (WindowState.MINIMIZED.equals(request.getWindowState())) {
////            return;
////        }
////
////        if (WindowState.NORMAL.equals(request.getWindowState())) {
////            normalView.include(request, response);
////        } else {
////            maximizedView.include(request, response);
////        }
//
//        System.out.println(">>> rENDER" + renderResponse.createResourceURL());
//        PortletContext context = getPortletContext();
//        PortletRequestDispatcher rd = context.getRequestDispatcher(NORMAL_VIEW);
//        rd.include(renderRequest, renderResponse);
//    }

}
