package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@deletePlaceAPI")
	public void beforeScenario() throws IOException {

		stepDefinitions sd = new stepDefinitions();
		if (stepDefinitions.placeId == null) {
			sd.add_place_payload_with(50, "Salem", "0099887765", "Tam", "Siv");
			sd.user_calls_with_http_request("AddPlaceAPI", "Post");
			sd.verify_place_id_created_maps_to_using("Siv", "getPlaceAPI");
		}
	}

}
