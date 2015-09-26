package com.maximchuk.rest.api.client.content;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class TextApiContent extends DefaultRestApiContent {

    public static DefaultRestApiContent create(String text) {
        return create("text/plain", text.getBytes());
    }

}
