package com.intelie.iem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceWrapper {

    public static String replaceAjaxUrls(String html, String resourceUrl) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("url[ ]*?:([^)]*?)(,|})", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String ref = matcher.group(1);
            String endingChar = matcher.group(2);

            String separator = resourceUrl.contains("?") ? "&" : "?";
            String replacement = "url: '" + resourceUrl + separator + "originalUrl=' + " + "" + ref + endingChar;

            matcher.appendReplacement(sb, replacement);
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String replaceUrls(String html, String resourceUrl) {
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("(src|href)=\"(.*?)\"");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String refType = matcher.group(1);
            String ref = matcher.group(2);

            if (!ref.contains(resourceUrl)) {
                String separator = resourceUrl.contains("?") ? "&" : "?";
                String replacement = refType + "=\"" + resourceUrl + separator + "originalUrl=" + ref + "\"";

                matcher.appendReplacement(sb, replacement);
            }
        }

        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String removeTags(String html, String resourceUrl) {
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

    public static String removeTitle(String html, String resourceUrl) {
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
