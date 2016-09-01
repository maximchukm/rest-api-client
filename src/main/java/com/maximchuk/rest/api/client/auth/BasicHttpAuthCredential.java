package com.maximchuk.rest.api.client.auth;

/**
 * @author Maxim Maximchuk
 *         date 26.09.2015.
 */
public class BasicHttpAuthCredential implements Credentials {

    private String authString;

    public BasicHttpAuthCredential(String username, String password, Base64Encoder base64encoder) {
        authString = "Basic " + base64encoder.encode(username + ":" + password);
    }

    @Override
    public String getAuthorizationString() {
        return authString;
    }

}
