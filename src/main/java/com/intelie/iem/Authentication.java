package com.intelie.iem;

import com.intelie.iem.util.Integration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authentication {

    protected static String iemUrl;
    protected static String authenticationUrlSuffix;
    protected static String username;
    protected static String password;

    protected Boolean isAuthenticated = false;

    public Authentication() {
        authenticationUrlSuffix = Integration.getAuthenticationUrl();
        username = Integration.getUsername();
        password = Integration.getPassword();
        iemUrl = Integration.getIemUrl();
    }

    public List<String> getAuthenticatedCookies() {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<String, String>();
        params.put("j_username", username);
        params.put("j_password", password);

        HttpHeaders headers = restTemplate.headForHeaders(iemUrl + authenticationUrlSuffix, params);

        List<String> cookies = headers.get("Set-Cookie");
        return cookies;
    }

    protected String ensureAuthentication(PortletRequest request, PortletResponse response) {
        String setCookie = "";

        boolean hasJSessionId = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("jsessionid")) {
                hasJSessionId = true;
                setCookie = cookie.getName() + "=" + cookie.getValue();
            }
        }

        if (!hasJSessionId) {
            Authentication authentication = new Authentication();
            for (String cookie : authentication.getAuthenticatedCookies()) {
                setCookie += cookie + "; ";
            }
            response.setProperty("Set-Cookie", setCookie);
        }

        return setCookie;
    }

}
