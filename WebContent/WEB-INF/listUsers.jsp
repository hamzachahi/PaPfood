<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tous les utilisateurs</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">Espace Administrateur</h2>
					<h3 class="secondery-title">Tous les utilisateurs</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="booking-form" align="center">
						<div class="table-responsive">
							<table class="table table-hover">
								<tr>
									<th>ID</th>
									<th>Pr&eacute;nom</th>
									<th>Nom</th>
									<th>E-mail</th>
									<th>Num&eacute;ro de t&eacute;l&eacute;phone</th>
									<th>Date d'Inscription</th>
									<th>Profession</th>
									<th>Fonction</th>
									<th>Adresse</th>
								</tr>

								<c:forEach var="user" items="${requestScope['allUsers']}">

									<tr>
										<td><a href="account?cible=${user.id}">${user.id }</a></td>
										<td><a href="account?cible=${user.id}">${user.surname }</a></td>
										<td><a href="account?cible=${user.id}">${user.name }</a></td>
										<td>${user.email }</td>
										<td>${user.phoneNumber }</td>
										<td>${user.dateInscription }</td>
										<td>${user.profession }</td>
										<td>${user.function }</td>
										<td><p>${user.streetNumber }${user.streetName },
												${user.postalCode } ${user.cityName }, ${user.countryName }</p></td>
									</tr>
								</c:forEach>
								<tr>
									<td><b>TOTAL</b></td>
									<td></td>
									<td></td>
									<td><b>${requestScope['total']}</b></td>
								</tr>
							</table>
							<div class="product-pagination">
								<nav>
								<ul class="pagination">${requestScope['pagination']}
								</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>