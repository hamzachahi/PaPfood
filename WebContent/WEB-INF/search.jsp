<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Résultat de la recherche</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<c:if test="${!empty param['messageProuct']}">
						<h2>Reçu message : ${param.messageProduct}</h2>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	</section>
	<c:if test="${not empty searchProductResults}">
		<section id="page-title-area">
		<h3 align="center" class="primery-title">Produits trouvés :</h3>
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
												<th>N°</th>
												<th>Nom du produit</th>
												<th>Description</th>
												<th>Prix</th>
												<th>D&eacute;tails</th>
												<th>Ajouter au panier</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="product"
												items="${requestScope['searchProductResults']}">
												<tr>
													<td>${i+1}</td>
													<td>${product.name }</td>
													<td>${product.description }</td>
													<td>${product.price}</td>
													<td><a class="btn btn-rooms-book-now"
														href="details?type=${product.type}&cible=${product.id}">+
															D&eacute;tails</a></td>
													<td align="center"><a style="color: white;"
														href="search?action=chargerPanier&codearticle=${product.id}&typea=${product.type}"><button
																class="btn btn-primary btn-	s">+1</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalProduct']}</b></td>
											</tr>
										</tfoot>
									</table>
									<div class="product-pagination">
										<nav>
										<ul class="pagination">${requestScope['paginationProduct']}
										</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	</c:if>
	<c:if test="${not empty searchServiceResults}">
		<section id="page-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="restaurent-menu-title">
						<c:if test="${!empty param['messageService']}">
							<h2>Reçu message : ${param.messageService}</h2>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		</section>
		<section id="page-title-area">
		<h3 align="center" class="primery-title">Services trouvés</h3>
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
												<th>N°</th>
												<th>Nom du produit</th>
												<th>Description</th>
												<th>Prix</th>
												<th>D&eacute;tails</th>
												<th>Ajouter au panier</th>
											</tr>
										</thead>
										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="service"
												items="${requestScope['searchServiceResults']}">
												<tr>
													<td>${i+1}</td>
													<td>${service.name }</td>
													<td>${service.description }</td>
													<td>${service.price}</td>
													<td><a class="btn btn-rooms-book-now"
														href="details?type=${service.type}&cible=${service.id}">+
															D&eacute;tails</a></td>
													<td align="center"><a style="color: white;"
														href="search?action=chargerPanier&codearticle=${service.id}&typea=${service.type}"><button
																class="btn btn-primary btn-	s">+1</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalService']}</b></td>
											</tr>
										</tfoot>
									</table>
									<div class="product-pagination">
										<nav>
										<ul class="pagination">${requestScope['paginationService']}
										</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	</c:if>
	<c:if test="${not empty searchPersonResults}">
		<section id="page-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="restaurent-menu-title">
						<c:if test="${!empty param['messagePerson']}">
							<h2>Reçu message : ${param.messagePerson}</h2>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		</section>
		<section id="page-title-area">
		<h3 align=right class="primery-title">Personnes trouvées :</h3>
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
												<th>N°</th>
												<th>Nom</th>
												<th>Pr&eacute;nom</th>
												<th>Profession</th>
												<th>Voir le profil</th>
											</tr>
										</thead>

										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="person"
												items="${requestScope['searchPersonResults']}">
												<tr>
													<td>${i+1}</td>
													<td>${person.name }</td>
													<td>${person.surname }</td>
													<td>${person.profession}</td>
													<td align="center"><a style="color: white;"
														href="account?cible=${person.id}"><button
																class="btn btn-primary btn-	s">Profil</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalPerson']}</b></td>
											</tr>
										</tfoot>
									</table>
									<div class="product-pagination">
										<nav>
										<ul class="pagination">${requestScope['paginationPerson']}
										</ul>
										</nav>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>
	</c:if>
	<%@include file="footer.jsp"%>
</body>
</html>