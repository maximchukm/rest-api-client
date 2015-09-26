package com.maximchuk.rest.api.client.core;

import com.maximchuk.rest.api.client.content.RestApiContent;
import com.maximchuk.rest.api.client.http.HttpHeader;
import com.maximchuk.rest.api.client.util.StringParamBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Maxim Maximchuk
 *         date 18.08.2014.
 */
public final class RestApiMethod {

    protected Type type;
    protected String name;
    protected List<HttpHeader> headers = new ArrayList<HttpHeader>();
    protected boolean forceQueryParams = false;
    protected RestApiContent content;

    protected int timeout = 10000;

    private Map<String, Object> params = new HashMap<String, Object>();

    public RestApiMethod(String name, Type type) {
        this.name = name;
        this.type = type;
        if (type == Type.GET || type == Type.DELETE) {
            forceQueryParams = true;
        }
    }

    public RestApiMethod(Type type) {
        this(null, type);
    }

    public void setTimeout(int timeoutMillis) {
        this.timeout = timeoutMillis;
    }

    public void addHeader(HttpHeader header) {
        headers.add(header);
    }

    public void forceQueryParams(boolean force) {
        forceQueryParams = force;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void putParam(String name, Object value) {
        params.put(name, value);
    }

    public void setContent(RestApiContent content) {
        this.content = content;
    }

    protected String paramString() {
        return new StringParamBuilder(params).build();
    }

    public enum Type {
        GET, POST, PUT, DELETE
    }

}
