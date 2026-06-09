package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ResourcesAPI;
import resources.Utils;
import resources.testDataBuild;

public class stepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	testDataBuild data = new testDataBuild();
	static String placeId;

	@Given("Add place payload with {int} {string} {string} {string} {string}")
	public void add_place_payload_with(int Accuracy, String Address, String Phone, String Language, String Name)
			throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlaceApi(Accuracy, Address, Phone, Language, Name));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		ResourcesAPI resourceAPI = ResourcesAPI.valueOf(resource);
		System.out.println(resourceAPI.getResources());
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("Post"))
			response = res.when().post(resourceAPI.getResources());
		else if (method.equalsIgnoreCase("get"))
			response = res.when().get(resourceAPI.getResources());
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue), expectedValue);
	}

	@Then("Verify Place id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String ExpectedName, String resource) throws IOException {
		placeId = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource, "get");
		String actualName = getJsonPath(response, "name");
		assertEquals(actualName, ExpectedName);
	}
	
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePayload(placeId));
		
	}

}
