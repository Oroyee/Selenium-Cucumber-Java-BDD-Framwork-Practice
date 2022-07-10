package StepDefinitions;

import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;

import org.junit.Assert;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RESTfulAPITestsSteps {

	int responsecode;
	private static Response response;

	@When("user POST 1 valid email and 1 valid password on register")
	public void user_post_1_valid_email_and_1_valid_password_on_register() {
		RestAssured.baseURI = "https://reqres.in/api";
		 
		String requestBody = "{\n"
        		+ "    \"email\": \"eve.holt@reqres.in\",\n"
        		+ "    \"password\": \"pistol\"\n"
        		+ "}";
        try {
        	response = RestAssured.given().contentType(ContentType.JSON).body(requestBody)
    				.post("register");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@Then("server response 200 along with token")
	public void server_response_200_along_with_token() {
		
		System.out.println("Status Code :" + response.getStatusCode());
		if (response.asString().length()==0)
		{
			System.out.println("Response Fail");
			Assert.fail();
		}
		System.out.println("Response :" + response.asString());
		Assert.assertEquals(200, response.getStatusCode());
		System.out.println("pass");
	}

	@When("user POST only 1 valid email on register")
	public void user_post_only_1_valid_email_on_register() {
		RestAssured.baseURI = "https://reqres.in/api";
		String requestBody = "{\n"
        		+ "    \"email\": \"sydney@fife\"\n"
        		+ "}";
        try {
        	response = RestAssured.given().contentType(ContentType.JSON).body(requestBody)
    				.post("register");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Then("server response 400 along with an error Get User List")
	public void server_response_along_with_an_error_get_user_list() {
		System.out.println("Response :" + response.asString());
		if (response.asString().length()==0)
		{
			System.out.println("Response Fail");
			Assert.fail();
		}
        System.out.println("Status Code :" + response.getStatusCode());
		Assert.assertEquals(400, response.getStatusCode());
		System.out.println("pass");
	}


	@When("user GET on users")
	public void user_get_on_users() throws IOException, InterruptedException {
//		URL url = new URL("https://reqres.in/api/users");
//		HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://reqres.in/api/users"))
//                .build();
//
//        HttpResponse<String> response = client.send(request,
//        HttpResponse.BodyHandlers.ofString());
//
//        System.out.println(response.body());
//        
//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//		connection.setRequestMethod("GET");
//		connection.connect();
//
//		responsecode = connection.getResponseCode();
//		System.out.println(responsecode);
		
		RestAssured.baseURI = "https://reqres.in/api";
		RequestSpecification request = RestAssured.given();
        try {
        	response = RestAssured.given().contentType(ContentType.JSON)
    				.get("users");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
