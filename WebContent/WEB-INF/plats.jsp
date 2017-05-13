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
	<div id="small-dialog" class="mfp-hide">
		<div class="search-top">
			<div class="login">
				<form action="ServletAcheter" method="post">
					<label for="search"></label> <br /> <input type="text"
						name="search" id="search" placeholder="Exp: lasagne.." /> <input
						type="hidden" name="action" value="chercherProduit" />
					<button type="submit" class="btn btn-info">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
					<br />
				</form>
			</div>
		</div>
	</div>

	<h3>Tous les produits</h3>

	<table class="table table-hover">
		<thead>
			<tr>
				<th>Nom du produit</th>
				<th>Description</th>
				<th>Prix</th>
				<th>Ajouter au panier</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="article" items="${requestScope['allArticles']}">
				<tr>
					<td>${article.name }</td>
					<td>${article.description }</td>
					<td>${article.price}</td>
					<td align="center"><button class="btn btn-primary btn-	s">
							<a style="color: white;"
								href="\ServletPanier?action=chargerPanier&idarticle=${article.id}">+1</a>
						</button></td>
				</tr>
			</c:forEach>


		</tbody>

	</table>

	<%@ include file="footer.jsp"%>
</body>
</html>