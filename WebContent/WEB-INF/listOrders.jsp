<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Toutes les commandes</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="restaurent-menu-title">
					<h2 class="primery-title">Espace Administrateur</h2>
					<h3 class="secondery-title">Toutes les commandes</h3>
				</div>
			</div>
		</div>
	</div>



	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="booking-form" align="center">
						<div class="table-responsive"></div>
						<table class="table table-bordered">

							<thead>
								<tr>
									<th>ID Commande</th>
									<th>Code</th>
									<th>Par</th>
									<th>Prix</th>
									<th>Date de commande</th>
								</tr>
							</thead>

							<tbody>
								<c:forEach var="order" items="${requestScope['allOrders'] }">
									<tr>
										<td>${order.id }</td>
										<td>${order.code}</td>
										<td><a href="users?id=${order.costumer.id }">${order.costumer.name }</a></td>
										<td>${order.price }&euro;</td>
										<td>${order.dateCommande }</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<%@include file="footer.jsp"%>
</body>
</html>