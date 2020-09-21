package ece.ntua.softeng.api.POST;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.Name;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FrontisthriaResourceAddFrontisthrioTest {

    private CloseableHttpClient httpClient = HttpClients.createDefault();

    @Before
    public void setup() throws IOException {

        System.out.println("==== Log representation of FrontisthriaResource Test with privileges ====");

        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/login");

        System.out.println("We are going to use frontisthrio with id: 3");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "FR3"));
        params.add(new BasicNameValuePair("password", "FR3"));
        request.setEntity(new UrlEncodedFormEntity(params));

        System.out.println("Executing request to get user and its privileges");
        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

    }

    @Test
    public void post() throws IOException {

        System.out.println("**Testing if a privileged user can add a frontisthrio**");

        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/frontisthria");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", "Ανάπλασις"));
        params.add(new BasicNameValuePair("description", "Χαλαρό εσωτερικό περιβάλλον"));
        params.add(new BasicNameValuePair("timestamp", "2012-12-21 12:12:21"));
        request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

        System.out.println("Executing request to add a new frontisthrio in the DB");
        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

        Assert.assertThat(response.getStatusLine().getStatusCode(), IsEqual.equalTo(200));

    }

    @After
    public void release() throws IOException {

        httpClient.close();
        System.out.println();

    }
}