package com.maximchuk.rest.api.client.core;

import com.maximchuk.rest.api.client.content.JsonRestApiContent;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Maxim Maximchuk
 *         date 12.07.2015.
 */
public class DefaultClientTest extends AbstractTest {

    private DefaultClient client;

    @Before
    public void setUp() throws Exception {
        client = new DefaultClient(SERVER_URL, CONTROLLER_NAME);
    }

    @Test
    public void testExecuteMethod() throws Exception {
        try {
            RestApiMethod method = new RestApiMethod("get", RestApiMethod.Type.GET);
            RestApiResponse response = client.execute(method);
            assertNotNull(response);

            JSONObject json = new JSONObject();
            json.put("testCase", "testExecuteMethod");
            json.put("message", "Hello!");

            method = new RestApiMethod("post", RestApiMethod.Type.POST);
            method.setContent(JsonRestApiContent.create(json));
            response = client.execute(method);
            assertNotNull(response);
            assertEquals(response.getContentType(), "application/json");
            System.out.println(response.getString());

            JSONObject respJson = response.getJSONObject();
            assertEquals(json.toString(), respJson.getJSONObject("json").toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}