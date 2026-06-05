package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class testDataBuild {

	public AddPlace addPlaceApi(int Accuracy, String Address, String Phone, String Language, String Name) {

		AddPlace a = new AddPlace();
		a.setAccuracy(Accuracy);
		a.setAddress(Address);
		a.setPhone_number(Phone);
		a.setLanguage(Language);
		a.setName(Name);

		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		a.setLocation(loc);

		List<String> ty = new ArrayList<String>();
		ty.add("shoe park");
		ty.add("shop");
		a.setTypes(ty);
		return a;
	}

}
