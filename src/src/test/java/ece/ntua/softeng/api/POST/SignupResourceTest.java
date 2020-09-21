package ece.ntua.softeng.api.POST;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SignupResourceTest {

    @Test
    public void post() throws IOException {

        System.out.println("**Testing the sign-in up function**");

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/sign-up");

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "Kostaras"));
        params.add(new BasicNameValuePair("password", "THEMASTER"));
        request.setEntity(new UrlEncodedFormEntity(params));

        System.out.println("Executing the POST request");
        CloseableHttpResponse response = httpClient.execute(request);

        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());
        Assert.assertThat(response.getStatusLine().getStatusCode(), IsEqual.equalTo(200));

        httpClient.close();
    }
}