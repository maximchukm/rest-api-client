package com.maximchuk.rest.api.client.core;

import com.maximchuk.rest.api.client.auth.Credentials;
import com.maximchuk.rest.api.client.http.HttpException;
import com.maximchuk.rest.api.client.http.HttpHeader;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Maxim Maximchuk
 *         date 12.07.2015.
 */
public class DefaultClient {

    private String serverUrl;
    private String controllerName;
    private Credentials credentials;

    public DefaultClient(String serverUrl, String controllerName) {
        this.serverUrl = serverUrl;
        this.controllerName = controllerName;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public RestApiResponse execute(RestApiMethod method) throws IOException, HttpException {
        StringBuilder urlBuilder = new StringBuilder(serverUrl);
        if (controllerName != null) {
            urlBuilder.append("/").append(controllerName);
        }
        if (method.name != null) {
            urlBuilder.append("/").append(method.name);
        }
        if (method.forceQueryParams) {
            urlBuilder.append("?").append(method.paramString());
        }
        HttpURLConnection connection =
                (HttpURLConnection) new URL(urlBuilder.toString()).openConnection();
        try {
            connection.setRequestMethod(method.type.name());
            connection.setConnectTimeout(method.timeout);
            if (credentials != null) {
                connection.setRequestProperty("Authorization", credentials.getAuthorizationString());
            }
            for (HttpHeader header : method.headers) {
                connection.setRequestProperty(header.getName(), header.getValue());
            }
            if (method.content != null) {
                connection.setRequestProperty("Content-Type", method.content.getContentType());
                writeRequest(connection, method.content.getBytes());
            } else if (!method.forceQueryParams) {
                writeRequest(connection, method.paramString().getBytes());
            }
            RestApiResponse restApiResponse = new RestApiResponse(connection);
            if (restApiResponse.getStatusCode() >= 200 && restApiResponse.getStatusCode() < 400) {
                return restApiResponse;
            } else {
                throw new HttpException(restApiResponse);
            }
        } finally {
            connection.disconnect();
        }
    }

    private void writeRequest(HttpURLConnection connection, byte[] bytes) throws IOException {
        connection.setDoOutput(true);
        OutputStream out = connection.getOutputStream();
        try {
            out.write(bytes);
        } finally {
            out.close();
        }
    }

}
