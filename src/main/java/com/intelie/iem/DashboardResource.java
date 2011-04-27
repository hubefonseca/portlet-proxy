package com.intelie.iem;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.portlet.RenderResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardResource {

    protected static final String url = "http://localhost:8080";

    public String allDashboards() {
        String url = "http://localhost:8080/rest/dashboard/";

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(commons);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=c496sbhlxkve;");

        String s = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        System.out.println(s);

        return s;
    }

    public String printDashboad(Integer id, RenderResponse renderResponse) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=c496sbhlxkve;");

        String html = restTemplate.exchange(url + "/dashboard?id=" + id,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        String resourceUrl = renderResponse.createResourceURL().toString();

        html = ResourceWrapper.replaceUrls(html, resourceUrl);
        html = ResourceWrapper.removeTags(html, resourceUrl);
        html = ResourceWrapper.removeTitle(html, resourceUrl);

        html = ResourceWrapper.replaceAjaxUrls(html, resourceUrl);

        return html;
    }

    public String getDashboardInfo(Integer id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=c496sbhlxkve;");

        String json = restTemplate.exchange(url + "/rest/dashboard/" + id,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        return json;
    }

}
