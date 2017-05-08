<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contactez-nous</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>
<body>
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft animated"
				data-wow-delay=".5s"
				style="visibility: visible; animation-delay: 0.5s; animation-name: slideInLeft;">
				<li><a href="index.html"><span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
				<li class="active">Contact</li>
			</ol>
		</div>
	</div>



	<!-- TOUTE LA DIV CI-DESSOUS A NE PAS CHANGER!! -->
	<div class="contact">
		<div class="container">
			<!--<h3>Contact</h3>-->

			<fieldset>

				<legend>Contactez-nous</legend>

				<div class="contact-grids">
					<div class="contact-form">
						<form action="ServletContact" method="post">
							<div class="contact-bottom">
								<div class="col-md-4 in-contact">
									<label for="nom">Votre nom :</label><br /> <input type="text"
										id="nom" name="name"
										value="${sessionScope['utilisateur.Name'] }" />
								</div>
								<div class="col-md-4 in-contact">
									<label for="email">Email :</label><br /> <input type="email"
										id="email" name="email"
										value="${sessionScope['utilisateur.email'] }" />
								</div>
								<div class="col-md-4 in-contact">
									<label for="teleph">Numéro de téléphone :</label><br /> <input
										type="text" name="phonenumber" id="teleph"
										placeholder="Exp : 06 00 00 00 00"> <br />
								</div>
								<div class="clearfix"></div>
							</div>

							<div class="contact-bottom-top">
								<br /> <label for="msg">Votre message :</label><br />
								<textarea rows="7" cols="80" name="message" id="msg"
									placeholder="taper votre message ici.."> </textarea>
							</div>

							<input type="hidden" name="action" value="nousContacter" /> <input
								type="submit" value="Envoyer">
						</form>
					</div>
			</fieldset>
			<div class="address">
				<div class=" address-more">
					<h2>Adresse</h2>
					<div class="col-md-4 address-grid">
						<i class="glyphicon glyphicon-map-marker"></i>
						<div class="address1">
							<p>2400 Route des Dolines</p>
							<p>TL 19034-88974</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-4 address-grid ">
						<i class="glyphicon glyphicon-phone"></i>
						<div class="address1">
							<p>+885699655</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="col-md-4 address-grid ">
						<i class="glyphicon glyphicon-envelope"></i>
						<div class="address1">
							<p>
								<a href="papfood2017@gmail.com">Nous écrire</a>
							</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--//content-->
	<!--map-->
	<div class="map">
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3279847.2716062404!2d145.46948275!3d-36.60289065!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x6ad4314b7e18954f%3A0x5a4efce2be829534!2sVictoria%2C+Australia!5e0!3m2!1sen!2sin!4v1443674224626"
			width="100%" height="" frameborder="0" style="border: 0"
			allowfullscreen></iframe>
	</div>
	<!--//map-->

</body>
</html>