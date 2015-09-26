package com.maximchuk.rest.api.client.content;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class OctetStreamRestApiContent extends DefaultRestApiContent {

    public static DefaultRestApiContent create(byte[] content) {
        return create("application/octet-stream", content);
    }

}
