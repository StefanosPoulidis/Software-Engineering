package ece.ntua.softeng.api.DELETE;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageResourceWithoutPrivilegeTest {

    @Before
    public void setup() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8090/observatory/api/login");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "user"));
        params.add(new BasicNameValuePair("password", "user"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        System.out.println("The status code is: " + httpResponse.getStatusLine().getStatusCode());

        httpClient.close();
    }

    @Test
    public void delete() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete("http://localhost:8090/observatory/api/frontisthria/1/package/2");
        CloseableHttpResponse response = httpClient.execute(httpDelete);
        Assert.assertThat(response.getStatusLine().getStatusCode(), CoreMatchers.is(400));
        System.out.println("An unprivileged user doesn't have access");

    }

}
