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
import resources.Utils;
import resources.testDataBuild;

public class stepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	testDataBuild data = new testDataBuild();

	@Given("Add place payload with {int} {string} {string} {string} {string}")
	public void add_place_payload_with(int Accuracy, String Address, String Phone, String Language, String Name)
			throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlaceApi(Accuracy, Address, Phone, Language, Name));
	}

	@When("User calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("/maps/api/place/add/json").then().spec(resSpec).extract().response();
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue).toString(), expectedValue);
		System.out.println(resp);
	}

}
