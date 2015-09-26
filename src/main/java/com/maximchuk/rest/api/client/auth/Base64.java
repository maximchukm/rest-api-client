package com.maximchuk.rest.api.client.auth;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public interface Base64 {

    String encode(String source);

    String decode(String base64String);

}
