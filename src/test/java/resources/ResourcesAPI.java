package resources;

public enum ResourcesAPI {

	AddPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"), 
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	ResourcesAPI(String resource) {
		this.resource = resource;
	}

	public String getResources() {
		return resource;
	}
}
