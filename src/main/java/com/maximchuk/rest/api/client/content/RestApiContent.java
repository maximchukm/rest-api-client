package com.maximchuk.rest.api.client.content;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public interface RestApiContent {

    String getContentType();

    byte[] getBytes();

}
