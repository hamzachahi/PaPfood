<!DOCTYPE html>
<html>
<head>
<title>Geolocation</title>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=false">
	
</script>
<body onload="initialize()">
	<div id="map"></div>
	<div>
		<!--	latitude, longitude :-->
		<input id="latlng" type="hidden" value="48.3906042,-4.4869013">
		<!-- <input type="button"
			value="Obtenir la ville..." onclick="retrieve()">  Ville /
		adresse :-->
		<input id="adr" type="hidden" value="">
		<!-- <input
			type="button" value="Obtenir le code postal..."
			onclick="codeAddress()"> code postal :-->
		<input id="cp" type="hidden" value="">
		<!-- département :-->
		<input id="dpt" type="hidden" value="">
		<!--pays : -->
		<input id="pays" type="hidden" value="">
		<!-- rue :-->
		<input id="rue" name="rue" type="hidden" value="">
		<!--n° :-->
		<input id="num" name="num" type="hidden" value="">
	</div>

	<div id="map_canvas"></div>
	<script>
		var geocoder;
		var map;
		var infowindow = new google.maps.InfoWindow();
		var marker;
		function initialize() {
			geocoder = new google.maps.Geocoder();
			var latlng = new google.maps.LatLng(48.8566667, 2.3509871);
			var myOptions = {
				zoom : 8,
				center : latlng,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			}
			map = new google.maps.Map(document.getElementById("map"), myOptions);
		}
		function codeLatLng(input) {
			var latlngStr = input.split(",", 2);
			var lat = parseFloat(latlngStr[0]);
			var lng = parseFloat(latlngStr[1]);
			var latlng = new google.maps.LatLng(lat, lng);
			geocoder
					.geocode(
							{
								'latLng' : latlng
							},
							function(results, status) {
								if (status == google.maps.GeocoderStatus.OK) {
									if (results[0]) {
										map.setZoom(11);
										marker = new google.maps.Marker({
											position : latlng,
											map : map
										});
										var elt = results[0].address_components;
										for (i in elt) {
											if (elt[i].types[0] == 'postal_code')
												document.getElementById('cp').value = elt[i].long_name;
											if (elt[i].types[0] == 'locality')
												document.getElementById('adr').value = elt[i].long_name;
											if (elt[i].types[0] == 'administrative_area_level_2')
												document.getElementById('dpt').value = elt[i].long_name;
											if (elt[i].types[0] == 'country')
												document.getElementById('pays').value = elt[i].long_name;
											if (elt[i].types[0] == 'route')
												document.getElementById('rue').value = elt[i].long_name;
											if (elt[i].types[0] == 'street_number')
												document.getElementById('num').value = elt[i].long_name;
										}
										alert('Géolocation effectuée"');
										infowindow
												.setContent(results[0].formatted_address);
										infowindow.open(map, marker);
										map.setCenter(latlng);
									}
								} else {
									alert("Geocoder failed due to: " + status);
								}
							});
		}

		function retrieve() {
			var input = document.getElementById("latlng").value;
			codeLatLng(input);
		}
		function initMap() {
			var map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : -34.397,
					lng : 150.644
				},
				zoom : 6
			});
			var infoWindow = new google.maps.InfoWindow({
				map : map
			});

			// Try HTML5 geolocation.
			if (navigator.geolocation) {
				navigator.geolocation
						.getCurrentPosition(
								function(position) {
									var pos = {
										lat : position.coords.latitude,
										lng : position.coords.longitude
									};
									var marker = new google.maps.Marker({
										position : pos,
										map : map
									});
									document.getElementById('latlng').value = position.coords.latitude
											+ ',' + position.coords.longitude;
									infoWindow.setPosition(pos);
									infoWindow.setContent('Votre position');
									map.setCenter(pos);
								}, function() {
									handleLocationError(true, infoWindow, map
											.getCenter());
								});
			} else {
				// Browser doesn't support Geolocation
				handleLocationError(false, infoWindow, map.getCenter());
			}
		}

		function handleLocationError(browserHasGeolocation, infoWindow, pos) {
			infoWindow.setPosition(pos);
			infoWindow
					.setContent(browserHasGeolocation ? 'Error: The Geolocation service failed.'
							: 'Error: Your browser doesn\'t support geolocation.');
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZR3u_KHeB-p8WN7YsvJG0tAB4dMuaN_8&callback=initMap">
		
	</script>
</body>
</html>