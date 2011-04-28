//package com.intelie.iem;
//
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.UsernamePasswordCredentials;
//import org.apache.commons.httpclient.auth.AuthScope;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.client.CommonsClientHttpRequestFactory;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.Assert.assertEquals;
//
//@Ignore
//public class TestDashboardResource {
//
//    @Ignore
//    @Test
//    public void testCometdProxy() {
//        String dashboardUrl = "http://localhost:8080/rest/dashboard/1";
//
//        HttpClient client = new HttpClient();
//        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("admin", "admin");
//        client.getState().setCredentials(AuthScope.ANY, credentials);
//
//        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);
//
//        RestTemplate restTemplate = new RestTemplate(commons);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Cookie", "JSESSIONID=1ip7rxdh76cej;");
////        headers.set("X-Requested-With", "Ext.basex");
//
//        String s = null;
//        s = restTemplate.exchange(dashboardUrl,
//                HttpMethod.GET,
//                new HttpEntity<String>(headers),
//                String.class).getBody();
//
//        System.out.println(s);
//
//    }
//
//    @Ignore
//    @Test
//    public void testprintDashboad() {
//        DashboardResource dashboardResource = new DashboardResource();
//
//        String s = dashboardResource.printDashboard(1);
//
//        System.out.println(s);
//    }
//
//    @Test
//    public void testReplaceUrls() {
//        DashboardResource dashboardResource = new DashboardResource();
//        String html = "<script src=\"ref\"></script><a href=\"/link\">";
//
//        String result = dashboardResource.replaceUrls(html);
//
//        assertEquals("Fail in replacement", "<script src=\"" + DashboardResource.url + "/ref\"></script><a href=\"" + DashboardResource.url + "/link\">", result);
//    }
//
//
//    @Test
//    public void testRemoveTags() {
//        DashboardResource dashboardResource = new DashboardResource();
//        String html = "<html><head><title>teste</title><meta/><script></script></head><body><a href=\"#\"></a><div></div></body></html>";
//
//        String result = dashboardResource.removeTags(html);
//
//        assertEquals("Fail in replacement", "<title>teste</title><script></script><a href=\"#\"></a><div></div>", result);
//    }
//
//    @Test
//    public void testRemoveTitle() {
//        DashboardResource dashboardResource = new DashboardResource();
//        String html = "<html><head><title>teste</title><meta/><script></script></head><body><a href=\"#\"></a><div></div></body></html>";
//
//        String result = dashboardResource.removeTitle(html);
//
//        assertEquals("Fail in replacement", "<html><head><meta/><script></script></head><body><a href=\"#\"></a><div></div></body></html>", result);
//    }
//
//}
