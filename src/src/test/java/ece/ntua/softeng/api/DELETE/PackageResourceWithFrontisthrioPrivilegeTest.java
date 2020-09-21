package ece.ntua.softeng.api.DELETE;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageResourceWithFrontisthrioPrivilegeTest {

    private long frontisthrioId;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @Before
    public void setup() throws IOException {

        System.out.println("==== Log representation of PackageResource Test with privileges ====");

        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/login");

        frontisthrioId = 3;
        System.out.println("We are going to use frontisthrio with id: " + frontisthrioId);

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "FR"+frontisthrioId));
        params.add(new BasicNameValuePair("password", "FR"+frontisthrioId));
        request.setEntity(new UrlEncodedFormEntity(params));

        System.out.println("Executing request to get user and its privileges");
        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

    }

    @Test
    public void deleteFromOwn() throws IOException {

        System.out.println("**Testing if a frontisthrio can delete its own packages**");

        System.out.println("Requesting DELETE of a package(resource) from frontisthrio 3 with id 7");
        HttpDelete request = new HttpDelete("http://localhost:8090/observatory/api/frontisthria/3/package/7");
        CloseableHttpResponse response = httpClient.execute(request);

        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());
        Assert.assertThat(response.getStatusLine().getStatusCode(), IsEqual.equalTo(200));

    }

    @Test
    public void deleteFromOther() throws IOException {

        System.out.println("**Testing if a frontisthrio can delete a package from another frontisthrio**");

        HttpDelete request = new HttpDelete("http://localhost:8090/observatory/api/frontisthria/3/package/7");

        System.out.println("Requesting DELETE of a package(resource) from frontisthrio 2 with id 5");
        CloseableHttpResponse response = httpClient.execute(request);
        Assert.assertThat(response.getStatusLine().getStatusCode(), IsEqual.equalTo(404));

    }

    @After
    public void release() throws IOException {

        httpClient.close();
        System.out.println();

    }
}
