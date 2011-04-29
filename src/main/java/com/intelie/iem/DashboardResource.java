package com.intelie.iem;

import com.intelie.iem.util.Integration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.ArrayList;
import java.util.List;

public class DashboardResource {

    protected static final String dashboardJspUrlSuffix = "/dashboard";
    protected static final String dashboardRestUrlSuffix = "/rest/dashboard";

    public String printDashboard(Integer id, RenderRequest renderRequest, RenderResponse renderResponse) {
        RestTemplate restTemplate = new RestTemplate();

        Authentication authentication = new Authentication();
        String cookie = authentication.ensureAuthentication(renderRequest, renderResponse);

        List<String> cookies = new ArrayList<String>();
        cookies.add(cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.put("Cookie", cookies);

        String html = restTemplate.exchange(Integration.getIemUrl() + dashboardJspUrlSuffix + "?id=" + id + "&renderDashboardTo=dashboard-portlet-div",
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

}
