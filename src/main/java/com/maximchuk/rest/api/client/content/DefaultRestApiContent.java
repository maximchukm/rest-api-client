package com.maximchuk.rest.api.client.content;

/**
 * @author Maxim Maximchuk
 *         date 14.07.2015.
 */
public class DefaultRestApiContent implements RestApiContent {

    private String contentType;
    private byte[] bytes;

    protected DefaultRestApiContent() {
    }

    private DefaultRestApiContent(String contentType, byte[] content) {
        this.contentType = contentType;
        this.bytes = content;
    }

    public static DefaultRestApiContent create(String contentType, byte[] content) {
        return new DefaultRestApiContent(contentType, content);
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
