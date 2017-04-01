package geolocation;

import javax.servlet.http.HttpServletRequest;
import javaQuery.importClass.javaQueryBundle;
import javaQuery.j2ee.GeoLocation;

public class Geolocalization {
	public static LocationInformation getGeoLocationInformation(HttpServletRequest request) {
		LocationInformation location=new LocationInformation();
		request.getHeader("VIA");
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		GeoLocation $gl = javaQueryBundle.createGeoLocation();
		$gl.MAPTargetByIP("117.204.232.104", "This is Demo. You can set even NULL");
		location.setCity(GeoLocation.City);
		location.setCountry(GeoLocation.Country);
		location.setGoogleMapUrL(GeoLocation.GoogleMap_URL);
		location.setGoogleMapUrlBubble(GeoLocation.GoogleMap_URL_Bubble);
		location.setLatitude(GeoLocation.Latitude);
		location.setLongitude(GeoLocation.Longitude);
		location.setState(GeoLocation.State);
		System.out.println(GeoLocation.Latitude);
		System.out.println(GeoLocation.Longitude);
		System.out.println(GeoLocation.Country);
		System.out.println(GeoLocation.City);
		System.out.println(GeoLocation.State);
		System.out.println(GeoLocation.GoogleMap_URL);
		System.out.println(GeoLocation.GoogleMap_URL_Bubble);
		return location;

	}
}
