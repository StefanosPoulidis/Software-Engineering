package ece.ntua.softeng.api;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.core.IsEqual;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***************************************************
 * SignupLoginAddfrontisthrioAddpackageTest
 *
 * We are going to use this test to checkout
 * how our api responds to the following use
 * case:
 * Sign-up, Login, Add a frontisthrio, Add a package
 *
 **************************************************/
public class SignupLoginAddfrontisthrioAddpackageTest {

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String username = "Michael";
    private String password = "Angelo";
    private long id = 6;

    @Before
    public void setup() throws IOException {

        System.out.println("**Creating a new user in the DB**");

        /* Setting up a new user */

        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/sign-up");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        request.setEntity(new UrlEncodedFormEntity(params));

        System.out.println("Signing-up a new user");
        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

        /* Logging in the new user's profile */

        request = new HttpPost("http://localhost:8090/observatory/api/login");
        request.setEntity(new UrlEncodedFormEntity(params));

        System.out.println("Logging in to the new user");
        response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

        System.out.println("Finished setting up a new user");

        response.close();
        request.reset();
        params.clear();
    }

    @Test
    public void post() throws IOException {

        /************************* Adding a frontisthrio *************************/

        System.out.println("**Adding a new frontisthrio for the specific user**");
        HttpPost request = new HttpPost("http://localhost:8090/observatory/api/frontisthria");


        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", "ΤοΜακρύτερο"));
        params.add(new BasicNameValuePair("description", "Το μακρύτερο"));
        params.add(new BasicNameValuePair("timestamp", "2012-12-21 12:12:21"));
        request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

        CloseableHttpResponse response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());

        /************************ Adding a package ***********************/

        System.out.println("**Adding a new package to the specific frontisthrio**");

        request.reset();
        request = new HttpPost("http://localhost:8090/observatory/api/frontisthria/"+ Long.toString(id));

        params.clear();
        params.add(new BasicNameValuePair("field", "Humanitarian"));
        params.add(new BasicNameValuePair("price", "331"));
        params.add(new BasicNameValuePair("success_rate", "59"));
        params.add(new BasicNameValuePair("city", "Σαλόνικα"));
        params.add(new BasicNameValuePair("street", "Της Μακρύτερης"));
        params.add(new BasicNameValuePair("street_number", "9"));

        request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

        response = httpClient.execute(request);
        System.out.println("The status code is: " + response.getStatusLine().getStatusCode());
        Assert.assertThat(response.getStatusLine().getStatusCode(), IsEqual.equalTo(200));

    }

    @After
    public void release() throws IOException {

        httpClient.close();
        System.out.println();

    }
}
