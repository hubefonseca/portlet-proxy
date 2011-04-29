package com.intelie.iem.integration;

import com.intelie.iem.Authentication;
import org.junit.Test;

public class TestAuthentication {

    @Test
    public void testAuthenticate() {
        Authentication authentication = new Authentication();

        authentication.getAuthenticatedCookies();
    }

}
