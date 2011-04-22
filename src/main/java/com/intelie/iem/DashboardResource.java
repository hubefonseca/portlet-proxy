package com.intelie.iem;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class DashboardResource {
//
//    protected String authenticate() {
//
//    }

    public String allDashboards() {

        System.out.println("Teste 3!!");

        HttpClient client = new HttpClient();
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
        client.getState().setCredentials(AuthScope.ANY, credentials);

        String url = "http://localhost:8080/rest/dashboard/";

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);

        RestTemplate restTemplate = new RestTemplate(commons);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=14hahz237001c;");
//        headers.set("X-Requested-With", "Ext.basex");

        String s = null;
        s = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        System.out.println(s);

        return s;
    }

}
