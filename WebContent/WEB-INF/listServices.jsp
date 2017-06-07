<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Liste des services</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<!-- Menu title -->
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">${sessionUtilisateur.surname}
						${sessionUtilisateur.name}</h2>
					<h3 class="secondery-title">Liste des produits</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="row">
						<!-- Single Room -->
						<c:set var="i" value="0" />
						<c:forEach var="article" items="${requestScope['listProduits']}">
							<div
								class="col-lg-6 col-md-6 col-sm-6 col-xs-12 padding-bottom-md">
								<div class="rooms-container">
									<div class="rooms-image">
										<img
											src="<c:if test="${not empty article.mainImage}"><c:out value="${article.mainImage}"/></c:if><c:if test="${empty article.mainImage}">assets/img/menu-details.png</c:if>"
											alt="">
									</div>
									<div class="rooms-footer">
										<div class="price-area">
											<div class="price-from">Price :</div>
											<div class="price">${article.price }</div>
										</div>
										<div class="rooms-content-area">
											<h3>${article.name } <br/><b>Où : </b> ${article.city }, ${article.country }</h3>
											<p>${article.description }.</p>
											<a class="btn btn-rooms-book-now"
												href="services?action=chargerPanier&idarticle=${i}">Ajouter
												au panier</a> <a class="btn btn-rooms-book-now"
												href="details?type=Service&cible=${article.id}">+
												D&eacute;tails</a>
										</div>
									</div>
								</div>
							</div>
							<c:set var="i" value="${i+1}" />
						</c:forEach>
						<div>
							<div>
								<b>TOTAL</b>
							</div>
							<div>
								<b>${requestScope['total']}</b>
							</div>
						</div>
						<!-- end single rooms -->
						<!-- end single rooms -->
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
	</section>
	<%@include file="footer.jsp"%>
</body>
</html>