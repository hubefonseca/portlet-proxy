package com.intelie.iem.proxy;

import com.intelie.iem.ResourceWrapper;
import com.intelie.iem.util.Integration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class Connection {

    public void returnCometResponse(String originalUrl, ResourceRequest req, ResourceResponse resp) throws IOException {
        URL u = new URL(Integration.getIemUrl() + originalUrl);
        URLConnection connection = u.openConnection();

        Enumeration properties = req.getPropertyNames();
        while (properties.hasMoreElements()) {
            String propertyName = (String) properties.nextElement();
            connection.setRequestProperty(propertyName, req.getProperty(propertyName));
        }

        for (Map.Entry<String, String[]> e : req.getParameterMap().entrySet()) {
            connection.setRequestProperty(e.getKey(), e.getValue()[0]);
        }

        connection.setDoOutput(true);
        DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

        BufferedReader reader = req.getReader();
        StringBuffer payload = new StringBuffer();
        int r = reader.read();
        while (r > 0) {
            payload.append((char) r);
            dos.write((char) r);
            r = reader.read();
        }

        PrintWriter writer = resp.getWriter();

        DataInputStream dis = new DataInputStream(connection.getInputStream());

        StringBuffer content = new StringBuffer();
        while (dis.available() != 0) {
            byte a = dis.readByte();
            content.append((char) a);
        }
        writer.print(content.toString());
        dis.close();
        writer.close();
    }

    public void returnImageResponse(String originalUrl, ResourceRequest req, ResourceResponse resp, String cookie, String urlPrefix) throws IOException {
        URL u = new URL(Integration.getIemUrl() + originalUrl);
        URLConnection connection = u.openConnection();

        resp.setCharacterEncoding("ASCII");
        resp.setContentType("image/" + urlPrefix.substring(urlPrefix.length() - 3));

        DataInputStream dis = new DataInputStream(connection.getInputStream());

        List<Byte> content = new ArrayList<Byte>();
        while (dis.available() != 0) {
            byte a = dis.readByte();
            content.add(a);
        }

        PrintWriter writer = resp.getWriter();

        Byte[] bytes = content.toArray(new Byte[]{});
        for (int i = 0; i < bytes.length; i++) {
            writer.write(bytes[i]);
        }

        dis.close();
        writer.close();
    }

    public void returnRestResponse(String originalUrl, ResourceRequest req, ResourceResponse resp, String cookie, String urlPrefix) {
        HttpMethod method = HttpMethod.valueOf(req.getMethod());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);

        Map<String, String[]> parameters = req.getParameterMap();

        try {
            if (urlPrefix.endsWith(".js")) {
                resp.setContentType("text/javascript");
            } else if (urlPrefix.endsWith(".css")) {
                resp.setContentType("text/css");
            } else {
                resp.setContentType("text/html");
            }

            String content = restTemplate.exchange(Integration.getIemUrl() + originalUrl,
                    method,
                    new HttpEntity<String>(headers),
                    String.class,
                    parameters).getBody();

            // Guarantee
            String resourceUrl = resp.createResourceURL().toString();

            content = ResourceWrapper.replaceUrls(content, resourceUrl);
            content = ResourceWrapper.replaceAjaxUrls(content, resourceUrl);

            PrintWriter writer = resp.getWriter();

            byte[] bytes = content.getBytes();
            for (int i = 0; i < bytes.length; i++) {
                writer.write(bytes[i]);
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("URL was: " + urlPrefix);
            e.printStackTrace();
        }
    }

}
