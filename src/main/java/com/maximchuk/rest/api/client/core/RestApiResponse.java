package com.maximchuk.rest.api.client.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

/**
 * @author Maxim Maximchuk
 *         date 14.07.2015.
 */
public class RestApiResponse {

    private static final String CONTENT_DISPOSITION_HEADER = "Content-Disposition";
    private static final String FILENAME_PREF = "filename=";

    public static final String ANDROID_401_MESSAGE = "No authentication challenges found";
    public static final String ANDROID_NULL_TOKEN = "Received authentication challenge is null";

    private int statusCode;
    private String contentType;
    private byte[] content;
    private FileEntity fileEntity;

    public RestApiResponse(HttpURLConnection connection) throws IOException {
        try {
            this.statusCode = connection.getResponseCode();
            this.contentType = connection.getContentType();
        } catch (IOException e) { // android hook for 401
            if (e.getMessage() != null
                    && (e.getMessage().equals(ANDROID_401_MESSAGE) || e.getMessage().equals(ANDROID_NULL_TOKEN))) {
                statusCode = 401;
                return;
            } else {
                throw e;
            }
        }

        InputStream is;
        try {
            is = connection.getInputStream();
        } catch (IOException e) {
            is = connection.getErrorStream();
        }
        if (is != null) {
            byte[] buf = new byte[2048];

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try {
                int len;
                while ((len = is.read(buf)) != -1) {
                    bout.write(buf, 0, len);
                }
                content = bout.toByteArray();
            } finally {
                is.close();
            }
        }

        String contentDispositionHeader = connection.getHeaderField(CONTENT_DISPOSITION_HEADER);

        if (contentDispositionHeader != null) {
            fileEntity = new FileEntity(contentDispositionHeader.substring(contentDispositionHeader.indexOf(FILENAME_PREF) + FILENAME_PREF.length())
                    .replace("\"", ""), content);
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public byte[] getBytes() {
        return content;
    }

    public String getString() {
        try {
            return content != null? new String(content, "UTF8"): null;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject getJSONObject() {
        return new JSONObject(getString());
    }

    public JSONArray getJSONArray() {
        return new JSONArray(getString());
    }

    public FileEntity getFileEntity() {
        return fileEntity;
    }

    public boolean isFile() {
        return fileEntity != null;
    }
}
