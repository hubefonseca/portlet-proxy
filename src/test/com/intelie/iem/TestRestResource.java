package com.intelie.iem;


import org.junit.Test;

public class TestRestResource {

    @Test
    public void testGatherDashboardInfo() {
        String json = RestResource.gatherDashboardInfo(1);

        System.out.println(json);
    }

}
