<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Votre panier</title>
<%@ include file="liens.jsp"%>
</head>
<body>
	<%@ include file="header.jsp"%>

	<h2>Votre panier : ${requestScope.nbArticles} articles</h2>
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Image</th>
				<th>Nom</th>
				<th>Prix</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="article" items="${requestScope['articlesPanier']}">
				<tr>
					<td>${article.mainImage }</td>
					<td>${article.name }</td>
					<td>${article.price }</td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
			<tr>
				<th>Sous-total: ${requestScope.total} &euro;</th>
				<td align="right">
					<button class="btn btn-success">Valider votre panier</button>
				</td>
			</tr>
		</tfoot>
	</table>
	<br />

	<%@ include file="footer.jsp"%>
</body>
</html>