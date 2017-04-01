package geolocation;

public class LocationInformation {
	private String Latitude;
	private String Longitude;

	private String Country;

	private String City;

	private String State;

	private String GoogleMapUrL;

	private String GoogleMapUrlBubble;

	public LocationInformation() {

	}

	public String getLatitude() {
		return Latitude;
	}

	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getGoogleMapUrL() {
		return GoogleMapUrL;
	}

	public void setGoogleMapUrL(String googleMapUrL) {
		GoogleMapUrL = googleMapUrL;
	}

	public String getGoogleMapUrlBubble() {
		return GoogleMapUrlBubble;
	}

	public void setGoogleMapUrlBubble(String googleMapUrlBubble) {
		GoogleMapUrlBubble = googleMapUrlBubble;
	}

}
