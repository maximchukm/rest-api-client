package com.maximchuk.rest.api.client.content;

import java.util.Map;

/**
 * Created by Maxim Maximchuk
 * date 26-Aug-16.
 */
public class FormUrlEncodedApiContent extends DefaultRestApiContent {

    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

    public static RestApiContent create(Map<String, String> formParams) {
        StringBuilder paramBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            paramBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            paramBuilder.append("&");
        }
        paramBuilder.deleteCharAt(paramBuilder.length() - 1);

        return create(CONTENT_TYPE, paramBuilder.toString().getBytes());
    }

}
