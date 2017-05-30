<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="liens.jsp"%>
<title>Login/Sign-In</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<link rel="stylesheet" href="assets/css/style.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script type="text/javascript" src="assets/js/facebook.js"></script>

</head>

<body class="home1" onload="initialize()">
	<div class="logmod">
		<div class="logmod__wrapper">
			<span class="logmod__close">Fermer</span>
			<div class="logmod__container">
				<ul class="logmod__tabs">
					<li data-tabtar="lgm-2"><a href="#">Connexion</a></li>
					<li data-tabtar="lgm-1"><a href="#">Inscription</a></li>
				</ul>
				<div class="logmod__tab-wrapper">
					<div class="logmod__tab lgm-1">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Entrez vos
								informations personnellees <strong>pour créer votre
									compte</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" method="post" action="inscription"
								class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="nom">Pseudo/Nom de
											famille*</label> <input class="string optional" maxlength="255"
											id="nom" name="nom" placeholder="Votre Pseudo/Nom de famille"
											type="text" size="50" /> <span class="erreur">${form.erreurs['nom']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="email">Email*</label> <input
											class="string optional" maxlength="255" id="user-email"
											name="email" placeholder="Email" type="email" size="50" /> <span
											class="erreur">${form.erreurs['email']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="motdepasse">Mot de
											passe *</label> <input class="string optional" maxlength="255"
											id="motdepasse" name="motdepasse" placeholder="Password"
											type="text" size="50" /> <span class="erreur">${form.erreurs['motdepasse']}</span>
									</div>
									<div class="input string optional">
										<label class="string optional" for="confirmation">Répetez
											mot de passe *</label> <input class="string optional" maxlength="255"
											id="confirmation" name="confirmation"
											placeholder="Repeat password" type="text" size="50" /> <span
											class="erreur">${form.erreurs['confirmation']}</span>
									</div>
								</div>
								<div class="simform__actions">
									<input type="submit" value="Inscription" class="sumbit" /> <span
										class="simform__actions-sidetext">En créant votre
										compte vous êtes d'accord sur <a class="special" href="#"
										target="_blank" role="link">les termes & conditions
											générales</a>
									</span>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" class="connect facebook">
									<div class="connect__icon">
										<i class="fa fa-facebook"></i>
									</div>
									<div class="connect__context">
										<span>Créez un compte <strong>avec Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
										<i class="fa fa-google-plus"></i>
									</div>
									<div class="connect__context">
										<span>Créez un compte <strong>Google+</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
					<div class="logmod__tab lgm-2">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Entrez votre
								adresse email et votre mot de passe <strong>pour vous
									connecter</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" method="post" action="connexion"
								class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-name">Email*</label>
										<input class="string optional" maxlength="255" id="email"
											name="email" placeholder="Email" type="email" size="50"
											value="<c:out value="${utilisateur.email}"/>" /> <input
											id="rue" name="rue" type="hidden" value=""> <input
											id="num" name="num" type="hidden" value=""> <input
											id="cp" name="cp" type="hidden" value=""> <input
											id="dpt" name="dpt" type="hidden" value=""> <input
											id="pays" name="pays" type="hidden" value=""> <input
											id="adr" name="adr" type="hidden" value=""> <span
											class="erreur">${form.erreurs['email']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-pw">Mot de
											passe *</label> <input class="string optional" maxlength="255"
											id="motdepasse" name="motdepasse" placeholder="Password"
											type="password" size="50" /> <span class="hide-password">Rendre
											visible</span> <span class="erreur">${form.erreurs['motdepasse']}</span>
									</div>
								</div>
								<div>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

									<c:if test="${!empty sessionScope.sessionUtilisateur}">
										<p class="succes">Vous êtes connecté(e) avec l'adresse :
											${sessionScope.sessionUtilisateur.email}</p>
									</c:if>
								</div>
								<div class="simform__actions">
									<input type="submit" value="Connexion" class="sumbit" /> <span
										class="simform__actions-sidetext"><a class="special"
										role="link" href="#">Mot de passe oublié?<br>Cliquez
											ici
									</a></span>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" onclick="retrieve()" class="connect googleplus">
									<div class="connect__icon">
										<i class="fa fa-map-marker" aria-hidden="true"></i>
									</div>
									<div class="connect__context">
										<span><strong>Me géolocaliser</strong></span>
									</div>
								</a> <a href="#" class="connect facebook">
									<div class="connect__icon" data-max-rows="1" data-size="xlarge"
										data-show-faces="true" onlogin="" data-scope=""
										data-auto-logout-link="true">
										<i class="fa fa-facebook"></i>
									</div>
									<div class="connect__context">
										<span>Connectez-vous avec <strong>Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
										<i class="fa fa-google-plus"></i>
									</div>
									<div class="connect__context">
										<span>Connectez-vous avec <strong>Google+</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="map.jsp"%>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="assets/js/index.js"></script>
	<script src="assets/js/facebook.js"></script>
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
			alert('Veuillez cliquez sur le bouton "Me Géolocaliser" pour améliorer votre expérience de navigation"');

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
										alert('Géolocation effectuée"');

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
