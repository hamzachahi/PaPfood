<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Votre panier</title>
<%@ include file="liens.jsp"%>
</head>
<body class="home1">
	<%@ include file="header.jsp"%>
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<c:if test="${!empty sessionUtilisateur}">
						<h2 class="primery-title">${sessionUtilisateur.surname}
							${sessionUtilisateur.name}</h2>
					</c:if>
					<h3 class="secondery-title">Votre panier</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-lg padding-bottom-lg">
					<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table class="table shop-cart">
									<thead>
										<tr>
											<th>Image</th>
											<th>Nom</th>
											<th>Prix</th>
											<th>Supprimer</th>

										</tr>
									</thead>

									<tbody>
										<c:forEach var="article"
											items="${requestScope['articlesPanier']}">
											<tr>
												<td>${article.mainImage }</td>
												<td>${article.name }</td>
												<td><span class='cart-price'>${article.price }</span></td>
												<td><span class="item-remove"><a
														href="ServletPropose?action=addSalable&indice=${i}">+</a></span></td>
											</tr>
										</c:forEach>
									</tbody>

									<tfoot>
										<tr>
											<th>Sous-total: ${requestScope.total} &euro;</th>
											<td align="right">
												<button class="btn btn-style-4">Valider votre
													panier</button>
											</td>
										</tr>
									</tfoot>
								</table>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4">
							<div class="shiping-address">
								<h5 class="shiping-address">calculate shipping</h5>
								<div class="shipping-address-inner">
									<form>
										<div class="form-group">
											<select class="form-control">
												<option>United States</option>
											</select>
										</div>
										<div class="form-group">
											<input type="text" class="form-control"
												placeholder="State / Province">
										</div>
										<div class="form-group">
											<input type="text" class="form-control"
												placeholder="Postcode / Zip">
										</div>
										<button type="submit" class="btn btn-style-4">Update</button>
									</form>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-md-offset-2">
							<div class="cart-calculation">
								<div class="cart-title">
									<h5 class="cart-title">cart totals</h5>
								</div>
								<table class="table cart-total">
									<tr>
										<td>subtotal:</td>
										<td>${requestScope.total} &euro;</td>
									</tr>
									<tr>
										<td>shipping:</td>
										<td>Free shiping</td>
									</tr>
									<tr>
										<td>order total:</td>
										<td>${requestScope.total} &euro;</td>
									</tr>
									<tr class="total-row">
										<td>total</td>
										<td>${requestScope.total} &euro;</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<%@ include file="footer.jsp"%>
</body>
</html>