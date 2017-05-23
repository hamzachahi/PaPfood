<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Commande enregistrée</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="restaurent-menu-title">
					<h2 class="primery-title">Commande effectuée</h2>
					<h3 class="secondery-title">Votre facture</h3>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="booking-form" align="center">

						<p>
							<img alt="succès" src="assets/img/pc5eyaGMi.png" width="15%">
						</p>
						<hr
							style="height: 12px; border: 0; box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);">
						<h2>Votre commande a bien été effectuée</h2>
						</br>


						<p>
							<a href="#"><button class="btn btn-style-4 ">Voir/Imprimer
									la facture</button></a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>