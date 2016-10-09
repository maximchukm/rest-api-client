package com.maximchuk.rest.api.client.http;

import com.maximchuk.rest.api.client.core.RestApiResponse;

/**
 * @author Maxim L. Maximchuk
 *         Date: 11/20/13
 */
public class HttpException extends Exception {

    private int errorCode;

    public HttpException(RestApiResponse restApiResponse) {
        super(restApiResponse.getString());
        this.errorCode = restApiResponse.getStatusCode();
    }

    public HttpException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public HttpException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
