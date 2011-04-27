package com.intelie.iem;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.portlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class IEMDashboardPortlet extends GenericPortlet {

    private static final String NORMAL_VIEW = "/WEB-INF/jsp/dashboard.jsp";
    private static final String HELP_VIEW = "/WEB-INF/jsp/help.jsp";

    String iemUrl = "http://localhost:8080";

    protected DashboardResource dashboardResource = new DashboardResource();

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
        String content = null;

        String originalUrl = req.getParameter("originalUrl");
        if (originalUrl.contains("?")) {
            System.out.println(">>> + " + originalUrl.substring(0, originalUrl.indexOf("?")));
        }

        if (originalUrl != null) {
            if (originalUrl.endsWith(".js") || (originalUrl.contains("?") && originalUrl.substring(0, originalUrl.indexOf("?")).endsWith(".js"))) {
                resp.setContentType("text/javascript");
            } else {
                resp.setContentType("text/html");
            }
            if (originalUrl.charAt(0) != '/') {
                originalUrl = "/" + originalUrl;
            }
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Cookie", "JSESSIONID=c496sbhlxkve;");

            content = restTemplate.exchange(iemUrl + originalUrl,
                    HttpMethod.GET,
                    new HttpEntity<String>(headers),
                    String.class).getBody();

        } else {
            content = dashboardResource.getDashboardInfo(1);
        }

        // Guarantee
        content = ResourceWrapper.replaceAjaxUrls(content, resp.createResourceURL().toString());

        PrintWriter writer = resp.getWriter();
        writer.print(content);
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
