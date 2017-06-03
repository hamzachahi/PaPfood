
<footer>
	<div id="footer-contact-area">
		<div class="container">
			<!-- Contact section -->
			<div class="row">
				<div class="col-md-4">
					<!-- Phone Contact -->
					<div class="contact-area">
						<div class="contact-icon">
							<i class="fa fa-phone" aria-hidden="true"></i>
						</div>
						<div class="contact-title">Par t&eacute;l&eacute;phone :</div>
						<div class="contact-velue">
							<a href="tel:906-914-6147">+(33) 1 23 45 67 89</a>
						</div>
					</div>
					<!-- End Phone Contact -->
				</div>
				<div class="col-md-4">
					<!-- Email Contact -->
					<div class="contact-area">
						<div class="contact-icon">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</div>
						<div class="contact-title">Par courriel :</div>
						<div class="contact-velue">
							<a href="mailto:email@alfresco.com">contact@cibos.com</a>
						</div>
					</div>
					<!-- End email Contact -->
				</div>
				<div class="col-md-4">
					<!-- Location -->
					<div class="contact-area">
						<div class="contact-icon">
							<i class="fa fa-map-marker" aria-hidden="true"></i>
						</div>
						<div class="contact-title">Par courrier :</div>
						<div class="contact-velue">
							<a
								href="//maps.google.com/maps?daddr=40.765819,-73.975866&amp;ll="
								target="_blank">Campus des Lucioles, Sophia Antipolis</a>
						</div>
					</div>
					<!-- End Location -->
				</div>
			</div>
		</div>
	</div>
	<!-- Copyright area -->
	<div id="footer-area">
		<div class="container border-top">
			<div class="row">
				<div class="col-md-6">
					<p class="text-left">
						<strong>Cibo's</strong> - The Community Culinary Exchange Platform
					</p>
				</div>
				<div class="col-md-6">
					<p class="text-right">
						&copy; Copyright 2017 <a href="http://www.envalab.com/"
							title="envalab" target="_blank"><strong>envalab</strong></a>. All
						Rights Reserved
					</p>
				</div>
			</div>
		</div>
	</div>
</footer>
<!-- RESERVATION -->
<div id="reservation-wrapper">
	<!-- Modal -->
	<div class="modal fade" id="reservationModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="modal-reservation">
						<div class="reservation-image-content">
							<img src="img/reservation.png" alt="reservation" />
						</div>
						<div class="reservation-form">
							<div class="reservation-form-title">
								<h2 class="primery-title">Sous-titre ici</h2>
								<h3 class="secondery-title">Faire une réservation</h3>
							</div>
							<form>
								<div class="form-group col-md-6">
									<label>your name</label> <input type="text"
										class="form-control" placeholder="Put your full Name">
								</div>
								<div class="form-group col-md-6">
									<label>date and time</label> <input type="text"
										class="form-control date-control" placeholder="23/10/2015">
									<input type="text" class="form-control time-control"
										placeholder="21:00">
								</div>
								<div class="form-group col-md-6">
									<label>seats</label> <input type="text" class="form-control"
										placeholder="2 persons">
								</div>
								<div class="form-group col-md-6">
									<label>email</label> <input type="text" class="form-control"
										placeholder="Your email address">
								</div>
								<div class="form-group col-md-12">
									<label>specific question</label>
									<textarea class="form-control" rows="4"
										placeholder="Describe any specific question here..."></textarea>
								</div>
								<div class="form-group col-md-12 text-center">
									<button type="submit" class="btn btn-model-reservation">Commandez
										maintenant!</button>
								</div>
							</form>
						</div>
					</div>
					<!-- .modal-reservation -->
				</div>
				<!-- .modal-body -->
			</div>
			<!-- .modal-content -->
		</div>
		<!-- .modal-dialog -->
	</div>
	<!-- END Modal -->
</div>
<!-- END RESERVATION -->
<!-- Javascript -->
<!-- jquery -->
<script src="assets/js/vendor/jquery-1.12.0.min.js"></script>
<!-- bootstrap js -->
<script src="assets/js/bootstrap.min.js"></script>
<!-- nivo slider -->
<script src="assets/custom-slider/js/jquery.nivo.slider.js"
	type="text/javascript"></script>
<!-- bootstrap select pugins -->
<script src="assets/js/bootstrap-select.js"></script>
<!-- plugins js -->
<script src="assets/js/plugins.js"></script>
<!-- owl.carousel js -->
<script src="assets/js/owl.carousel.min.js"></script>
<!-- mean menu plugins -->
<script src="assets/js/jquery.meanmenu.js"></script>
<!-- nicescroll js -->
<script src="assets/js/jquery.nicescroll.min.js"></script>
<!-- slick slider ls -->
<script src="assets/js/slick.min.js"></script>
<!-- isotope js -->
<script src="assets/js/isotope.pkgd.js"></script>
<script src="assets/js/imagesloaded.pkgd.js"></script>
<!-- flex slider -->
<script src="assets/js/jquery.flexslider-min.js"></script>
<!-- stiky js -->
<script src="assets/js/jquery.sticky.js"></script>
<!-- main js -->
<script src="assets/js/main.js"></script>
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