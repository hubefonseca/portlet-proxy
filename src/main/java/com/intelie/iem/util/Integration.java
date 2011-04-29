package com.intelie.iem.util;

public class Integration {

    protected static final String iemUrl = "http://localhost:8080";
    protected static final String authenticationUrl = "/j_spring_security_check";
    protected static final String username = "admin";
    protected static final String password = "admin";

    public static String getIemUrl() {
        return iemUrl;
    }

    public static String getAuthenticationUrl() {
        return authenticationUrl;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

}
