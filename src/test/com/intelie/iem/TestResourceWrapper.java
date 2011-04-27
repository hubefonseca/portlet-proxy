package com.intelie.iem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestResourceWrapper {

    @Test
    public void testReplaceRESTUrls() {
        DashboardResource dashboardResource = new DashboardResource();
        String html = "Ext.Ajax.request({\n" +
                "url  :'rest/dashboard/' + dashboardId,\n" +
                "method :'GET',";

        String result = ResourceWrapper.replaceAjaxUrls(html, "url");

        assertEquals("Fail in replacement", "Ext.Ajax.request({\n" +
                "url: 'url?originalUrl=' + 'rest/dashboard/' + dashboardId,\n" +
                "method :'GET',", result);
    }

    @Test
    public void testCorrectUrlOccurrenceReplacement() {
        String html = "url:url,\n" +
                "success: processSuccess,";

        String result = ResourceWrapper.replaceAjaxUrls(html, "resourceUrl?t=1");

        assertEquals("Fail in replacement", "url: 'resourceUrl?t=1&originalUrl=' + url,\n" +
                "success: processSuccess,", result);
    }

    @Test
    public void testUniqueReplacement() {
        String html = "{url:url}";

        String result = ResourceWrapper.replaceAjaxUrls(html, "resourceUrl");

        assertEquals("Fail in replacement", "{url: 'resourceUrl?originalUrl=' + url}", result);
    }

}
