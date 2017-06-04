<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vos commandes</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-12">

				<div class="restaurent-menu-title">
					<h2 class="primery-title">Espace Administrateur</h2>
					<h3 class="secondery-title">Toutes vos commandes</h3>
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
							<table class="table table-bordered">

								<thead>
									<tr BGCOLOR="black">
										<th>N&deg;</th>
										<th>Code</th>
										<th>Par</th>
										<th>Prix</th>
										<th>Date de commande</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="i" value="0" />
									<c:forEach var="order" items="${requestScope['allOrders'] }">
										<tr BGCOLOR="orange">
											<th BGCOLOR="">${i+1}</th>
											<th>${order.code}</th>
											<th><a href="account?cible=${order.customer.id }">${order.customer.name }</a></th>
											<th>${order.price }&euro;</th>
											<th>${order.dateCommande }</th>
										</tr>
										<tr>
											<th></th>

											<th>N&deg;</th>
											<th>D&eacute;signation</th>
											<th>Quantit&eacute;</th>
										</tr>
										<c:set var="j" value="0" />
										<c:forEach var="elem" items="${order.elements }">
											<tr>
												<td></td>

												<td>${j+1}</td>

												<td><a
													href="details?type=${elem.mProduct.type}&cible=${elem.mProduct.id}">${elem.mProduct.name}</a></td>
												<td>${elem.quantity}</td>
											</tr>
											<c:set var="j" value="${j+1}" />
										</c:forEach>
										<c:set var="i" value="${i+1}" />
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<td><b>TOTAL</b></td>
										<td></td>
										<td></td>
										<td></td>
										<td><b>${requestScope['total']}</b></td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
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
	<%@include file="footer.jsp"%>
</body>
</html>