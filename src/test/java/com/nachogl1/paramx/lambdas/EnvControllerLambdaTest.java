package com.nachogl1.paramx.lambdas;


import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class EnvControllerLambdaTest extends LambdaTest {

    @BeforeEach
    public void setUp() {
        handler = new StreamLambdaHandler();
        lambdaContext = new MockLambdaContext();
    }

    @Test
    public void returnCorrectValueForEnv() {
        InputStream requestStream = new AwsProxyRequestBuilder("/env", HttpMethod.GET)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .buildStream();
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

        handle(requestStream, responseStream);
        AwsProxyResponse response = readResponse(responseStream);

        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());
        assertFalse(response.isBase64Encoded());
        assertEquals(response.getBody(), "test");
        assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
        assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
    }


}
