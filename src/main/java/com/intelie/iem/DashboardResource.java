package com.intelie.iem;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DashboardResource {

    protected static final String url = "http://localhost:8080";

    public String allDashboards() {
        String url = "http://localhost:8080/rest/dashboard/";

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(commons);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=1x2xkd5kv6yas;");

        String s = null;
        s = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        System.out.println(s);

        return s;
    }

    public String printDashboad(Integer id) {
        String url = "http://localhost:8080/dashboard?id=" + id;

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", "JSESSIONID=1x2xkd5kv6yas;");

        String html = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class).getBody();

        html = replaceUrls(html);
        html = removeTags(html);
        html = removeTitle(html);

        html = replaceRESTUrls(html);

        return html;
    }

    protected String replaceRESTUrls(String html) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("Ajax\\.request.*?url.*?:(.*?),", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String all = matcher.group();
            String rel = matcher.group(1);
            matcher.appendReplacement(sb, all.replace(rel, "window.location.pathname, params: {type: 'dashboard-properties', dashboardId: 1}"));
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    protected String replaceUrls(String html) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("(src|href)=\"(.*?)\"");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String rel = matcher.group(1);
            String ref = matcher.group(2);
            String slash = "";
            if (!ref.startsWith("/")) {
                slash = "/";
            }
            matcher.appendReplacement(sb, rel + "=\"" + url + slash + ref + "\"");
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    protected String removeTags(String html) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("<(html|/html|head|/head|body|/body|meta|/meta|!DOCTYPE).*?>");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String rel = matcher.group();
            matcher.appendReplacement(sb, "");
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    protected String removeTitle(String html) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("<title.*?/title>");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String rel = matcher.group();
            matcher.appendReplacement(sb, "");
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

}
