<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Contactez-nous</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<!-- Menu title -->
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">Yummy Shop</h2>
					<h3 class="secondery-title">Nous contacter</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="row">
						<div class="col-md-12">
							<div class="title-center text-center contact">
								<h2>Prendre contact avec nous</h2>
								<hr />
								<p>Yummy Shop mais en oeuvre chaque jour des efforts
									surhumains pour vos fournir le meilleur service possible. Vos
									questions sont les bienvenues car elle nous permettent tous les
									jours d'améliorer votre expérience. Nous vous répondrons dans
									les plus brefs délais!</p>
								<div class="social-icon">
									Suivez-nous sur: <span><a href="#"><i
											class="fa fa-facebook-square" aria-hidden="true"></i>facebook</a></span>
									<span><a href="#"><i class="fa fa-twitter-square"
											aria-hidden="true"></i>twitter</a></span>
								</div>
							</div>
						</div>
					</div>
					<hr />
					<div class="row padding-top-lg padding-bottom-lg">
						<div class="col-md-4">
							<!--Map area start -->
							<div id="map"></div>
							<!--Map area end -->
						</div>
						<div class="col-md-8">
							<div class="contact-form">
								<div class="row">
									<form action="ServletContact" method="post">
										<div class="form-group col-md-6">
											<label>Votre nom complet</label> <input type="email"
												class="form-control" id="nom" name="name"
												value="${sessionUtilisateur.surname} ${sessionUtilisateur.name}"
												placeholder="Entrez votre nom complet">
										</div>
										<div class="form-group col-md-6">
											<label>N° de téléphone</label> <input type="text"
												name="phonenumber" id="teleph" class="form-control"
												value="${sessionUtilisateur.phoneNumber}
												placeholder="
												Numéro detéléphone">
										</div>
										<div class="form-group col-md-6">
											<label>email</label> <input type="text" class="form-control"
												id="email" name="email" value="${sessionUtilisateur.email}"
												placeholder="Adresse email">
										</div>
										<div class="form-group col-md-6">
											<label>Sujet</label> <input type="text" class="form-control"
												name="subject" id="subject" placeholder="Sujet">
										</div>
										<div class="form-group col-md-12">
											<label>Question spécifique</label>
											<textarea class="form-control" rows="5" name="message"
												id="msg"
												placeholder="Décrivez n'importe quelle question spécifique ici..."></textarea>
										</div>
										<div class="form-group col-md-12">
											<input type="hidden" name="action" value="nousContacter" />
											<button type="submit" class="btn btn-send-now pull-right">Envoyez
												maintenant!</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!--//map-->
	<!-- Google Map js
    ============================================ -->
	<script>
		function initMap() {
			var mapDiv = document.getElementById('map');
			var map = new google.maps.Map(mapDiv, {
				center : {
					lat : 23.7808874,
					lng : 90.2792365
				},
				zoom : 10,
				scrollwheel : false,
			});
			var marker = new google.maps.Marker({
				position : map.getCenter(),
				animation : google.maps.Animation.BOUNCE,
				icon : 'img/map-marker.png',
				map : map
			});
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC5os6oQBtIiHfG4GbiQaaxv5KjC05o8FU&callback=initMap"></script>
	<!-- main js -->
	<%@include file="footer.jsp"%>

</body>
</html>