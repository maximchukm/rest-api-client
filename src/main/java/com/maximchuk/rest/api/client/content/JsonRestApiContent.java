package com.maximchuk.rest.api.client.content;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class JsonRestApiContent extends DefaultRestApiContent {

    public static DefaultRestApiContent create(JSONObject json) {
        return create("application/json", json.toString().getBytes());
    }

    public static DefaultRestApiContent create(JSONArray json) {
        return create("application/json", json.toString().getBytes());
    }

    public static DefaultRestApiContent create(String jsonString) {
        return create("application/json", jsonString.getBytes());
    }

}
