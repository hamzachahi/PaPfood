<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des plats</title>
<%@ include file="liens.jsp"%>
</head>
<body class="home1">
	<%@ include file="header.jsp"%>
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">${sessionUtilisateur.surname}
						${sessionUtilisateur.name}</h2>
					<h3 class="secondery-title">Fa&Icirc;tes votre recherche</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<hr />
					<div class="search-area">
						<form action="acheter" method="post">
							<input type="text" name="search" id="search"
								placeholder="Exp: lasagne.." /> <input type="hidden"
								name="action" value="chercherProduit" />
							<button type="submit">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
							<br />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<section id="page-title-area">
	<h3 align="left" class="primery-title">Tous les produits</h3>
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
											<th>N&deg;</th>
											<th>Nom du produit</th>
											<th>Description</th>
											<th>Prix</th>
											<th>D&eacute;tails</th>
											<th>Ajouter au panier</th>
										</tr>
									</thead>

									<tbody>
										<c:set var="i" value="0" />
										<c:forEach var="article"
											items="${requestScope['searchResults']}">
											<tr>
												<td>${i+1}</td>
												<td>${article.mProduct.name }</td>
												<td>${article.mProduct.description }</td>
												<td>${article.mProduct.price}</td>
												<td><a class="btn btn-rooms-book-now"
													href="details?type=${article.mProduct.type}&cible=${article.mProduct.id}">+
														D&eacute;tails</a></td>
												<td align="center"><a style="color: white;"
													href="acheter?action=chargerPanier&codearticle=${article.mProduct.id}&typea=${article.mProduct.type}"><button
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
											<td><b>${requestScope['total']}</b></td>
										</tr>
									</tfoot>
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
	</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>