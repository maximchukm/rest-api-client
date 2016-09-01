package com.maximchuk.rest.api.client.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Maximchuk
 *         date 14.07.2015.
 */
public class StringParamBuilder {

    private Map<String, Object> params;

    public StringParamBuilder(){
        params = new HashMap<String, Object>();
    }

    public StringParamBuilder(Map<String, Object> params) {
        this.params = params;
    }

    public void putParam(String key, Object value) {
        params.put(key, value);
    }

    public String build() {
        StringBuilder paramBuilder = new StringBuilder();
        for (Map.Entry<String, Object> param: params.entrySet()) {
            paramBuilder.append(urlEncode(param.getKey())).append("=").append(urlEncode(param.getValue().toString()))
                    .append("&");
        }
        if (paramBuilder.length() > 0) {
            paramBuilder.deleteCharAt(paramBuilder.length() - 1);
        }
        return paramBuilder.toString();
    }

    private String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
