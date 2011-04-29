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

    @Test
    public void testReplaceUrls() {
        String html = "<a href=\"/abc\">";

        String result = ResourceWrapper.replaceUrls(html, "resourceUrl?t=1");

        assertEquals("Fail in replacement", "<a href=\"resourceUrl?t=1&originalUrl=/abc\">", result);
    }


    @Test
    public void testSimpleQuotesInReplaceUrls() {
        String html = "html : \"<img height='22' width='53' src='/images/logo.png'/><span>\" + (this.title || \"\") + \"</span>\"";

        String result = ResourceWrapper.replaceUrls(html, "resourceUrl?t=1");

        assertEquals("Fail in replacement", "html : \"<img height='22' width='53' src='resourceUrl?t=1&originalUrl=/images/logo.png'/><span>\" + (this.title || \"\") + \"</span>\"", result);
    }

    @Test
    public void testMatchWithTwoQuotes() {
        String html = "'<img src=\"', this.emptyIcon, '\" class=\"x-tree-ec-icon x-tree-elbow\" />',";

        String result = ResourceWrapper.replaceUrls(html, "resourceUrl");

        assertEquals("Fail in replacement", "'<img src=\"resourceUrl?originalUrl=', this.emptyIcon, '\" class=\"x-tree-ec-icon x-tree-elbow\" />',", result);
    }

}
