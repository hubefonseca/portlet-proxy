package com.intelie.iem;

import com.intelie.iem.util.Integration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.portlet.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Map;

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

        PrintWriter writer = resp.getWriter();

        String originalUrl = req.getParameter("originalUrl");
        if (originalUrl.contains("?")) {
            System.out.println(">>> + " + originalUrl.substring(0, originalUrl.indexOf("?")));
        }

        String resourceUrl = resp.createResourceURL().toString();
        boolean deum = originalUrl.contains("/portal/private/classic/") || originalUrl.contains("/portal/public/classic/");
        if (deum) {
            System.out.println("ITS A PROBLEM!!!");
            originalUrl = "cometd/handshake";
        }

        if (originalUrl != null) {

            if (originalUrl.charAt(0) != '/') {
                originalUrl = "/" + originalUrl;
            }

            StringBuffer payload;
            if (originalUrl.contains("cometd/handshake")) {

                URL u = new URL(Integration.getIemUrl() + originalUrl);
                URLConnection connection = u.openConnection();

                Enumeration properties = req.getPropertyNames();
                while (properties.hasMoreElements()) {
                    String propertyName = (String) properties.nextElement();
                    connection.setRequestProperty(propertyName, req.getProperty(propertyName));
                    System.out.println(propertyName + " :  " + req.getProperty(propertyName));
                }
//                connection.setRequestProperty("Accept", "application/json");
//                connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
//                connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
//                connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
//                connection.setRequestProperty("Connection", "keep-alive");
//                connection.setRequestProperty("Content-Length", "140");
//                connection.setRequestProperty("Content-Type", "application/json");
//                connection.setRequestProperty("Cookie", cookie);
////                connection.setRequestProperty("Cookie", "wp-settings-time-1=1294006244; BAYEUX_BROWSER=e3591mlwcpguju1zggmt61sys18wp; JSESSIONID=a6lhnat72sp");
//                connection.setRequestProperty("Host", "localhost:8080");
//                connection.setRequestProperty("Origin", "http://localhost:8080");
//                connection.setRequestProperty("Referer", "http://localhost:8080/");
//                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.205 Safari/534.16");
//                connection.setRequestProperty("X-Requested-With", "Ext.basex");

                for (Map.Entry<String, String[]> e : req.getParameterMap().entrySet()) {
                    System.out.println(e.getKey() + ": " + e.getValue()[0]);
                    connection.setRequestProperty(e.getKey(), e.getValue()[0]);
                }

                connection.setDoOutput(true);
                connection.setDoInput(true);
                DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

                BufferedReader reader = req.getReader();
                payload = new StringBuffer();
                int r = reader.read();
                while (r > 0) {
                    payload.append((char) r);
                    dos.write((char) r);
                    r = reader.read();
                }

                if (!payload.toString().isEmpty()) {
                    System.out.println("Wrote to output stream: " + payload);
                }

                System.out.println(">>> " + connection.getContentType());
                System.out.println(">>> " + connection.getContentEncoding());

                resp.setContentType("application/json;charset=UTF-8");

                DataInputStream dis = new DataInputStream(connection.getInputStream());

                StringBuffer content = new StringBuffer();
                while (dis.available() != 0) {
                    byte a = dis.readByte();
                    System.out.print((char) a);
                    content.append((char) a);
                }
                writer.print(content.toString());
                dis.close();
                writer.close();
                return;

            } else {
                String content = null;

                if (originalUrl.endsWith(".js") || (originalUrl.contains("?") && originalUrl.substring(0, originalUrl.indexOf("?")).endsWith(".js"))) {
                    resp.setContentType("text/javascript");
                } else {
                    resp.setContentType("text/html");
                }

                HttpMethod method = HttpMethod.valueOf(req.getMethod());
                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders headers = new HttpHeaders();
                headers.set("Cookie", cookie);

                Map<String, String[]> parameters = req.getParameterMap();

                try {
                    content = restTemplate.exchange(Integration.getIemUrl() + originalUrl,
                            method,
                            new HttpEntity<String>(headers),
                            String.class,
                            parameters).getBody();

                    // Guarantee
                    content = ResourceWrapper.replaceAjaxUrls(content, resourceUrl);
                    writer.write(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        writer.close();
    }

}
