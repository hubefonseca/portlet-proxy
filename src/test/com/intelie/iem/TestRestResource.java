package com.intelie.iem;


import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class TestRestResource {

    protected static final String IEM_URL = "http://localhost:8080";
    protected static final String COOKIE = "8d2q559n26rp";

    @Test
    public void testConnect() throws IOException {

        String method = "POST";
        String originalUrl = "/cometd/handshake";

        MultiValueMap parameters = new LinkedMultiValueMap();


        String content = null;

        if (originalUrl.charAt(0) != '/') {
            originalUrl = "/" + originalUrl;
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=" + COOKIE + ";");

        if (originalUrl.contains("cometd")) {

            URL u = new URL(IEM_URL + originalUrl);
            URLConnection connection = u.openConnection();
            connection.setRequestProperty("Cookie", "JSESSIONID=" + COOKIE + ";");

            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
            connection.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Content-Length", "140");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Cookie", "wp-settings-time-1=1294006244; BAYEUX_BROWSER=e3591mlwcpguju1zggmt61sys18wp; JSESSIONID=8d2q559n26rp");
            connection.setRequestProperty("Host", "localhost:8080");
            connection.setRequestProperty("Origin", "http://localhost:8080");
            connection.setRequestProperty("Referer", "http://localhost:8080/");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_7; en-US) AppleWebKit/534.16 (KHTML, like Gecko) Chrome/10.0.648.205 Safari/534.16");
            connection.setRequestProperty("X-Requested-With", "Ext.basex");


            connection.setDoOutput(true);
            connection.setDoInput(true);
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

            String testPayload = "[{\"version\":\"1.0\",\"minimumVersion\":\"0.9\",\"channel\":\"/meta/handshake\",\"supportedConnectionTypes\":[\"long-polling\"],\"id\":1,\"ext\":{\"ack\":true}}]";

            int i = 0;
            int r;
            while (i < testPayload.length()) {
                r = testPayload.charAt(i);
                System.out.print((char) r);
                dos.write(r);
                i++;
            }
            dos.close();

            System.out.println(">>> " + connection.getContentType());
            System.out.println(">>> " + connection.getContentEncoding());

            DataInputStream dis = new DataInputStream(connection.getInputStream());
            StringBuffer str = new StringBuffer();

            int a = dis.read();
            while (a >= 0) {
                System.out.print((char) a);
                str.append((char) a);
                a = dis.read();
            }
            dis.close();
            content = str.toString();

            System.out.println("Content: " + content);

        }


    }

}
