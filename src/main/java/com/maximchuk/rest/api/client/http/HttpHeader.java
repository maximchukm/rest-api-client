package com.maximchuk.rest.api.client.http;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class HttpHeader {

    private String name;
    private String value;

    public HttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
