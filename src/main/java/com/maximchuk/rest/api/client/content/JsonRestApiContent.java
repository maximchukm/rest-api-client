package com.maximchuk.rest.api.client.content;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class JsonRestApiContent extends DefaultRestApiContent {

    private static final String CONTENT_TYPE = "application/json";

    public static RestApiContent create(JSONObject json) {
        return create(CONTENT_TYPE, json.toString().getBytes());
    }

    public static RestApiContent create(JSONArray json) {
        return create(CONTENT_TYPE, json.toString().getBytes());
    }

    public static RestApiContent create(String jsonString) {
        return create(CONTENT_TYPE, jsonString.getBytes());
    }

}
