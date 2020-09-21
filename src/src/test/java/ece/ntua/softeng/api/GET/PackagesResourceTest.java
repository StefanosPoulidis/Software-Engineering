package ece.ntua.softeng.api.GET;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;



public class PackagesResourceTest {

    @Test
    public void get() {

        String givenMediaType = "application/json";
        HttpUriRequest req = new HttpGet("http://localhost:8090/observatory/api/packages");

        try {
            HttpResponse res = HttpClientBuilder.create().build().execute(req);
            String returnMediaType = ContentType.getOrDefault(res.getEntity()).getMimeType();
            Assert.assertEquals(givenMediaType, returnMediaType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}