package com.maximchuk.rest.api.client.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
            RestApiMethod method = new RestApiMethod("sync-case", RestApiMethod.Type.GET);
            method.putParam("device_id", DEVICE_ID);
            method.putParam("case", "all");
            RestApiResponse response = client.executeMethod(method);
            Assert.assertTrue(response != null);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}