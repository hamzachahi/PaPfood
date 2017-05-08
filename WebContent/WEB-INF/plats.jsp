<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des plats</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

	<%@ include file="header.jsp"%>

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
			<c:forEach items="${allProducts}" var="product">
				<tr>
					<td>${product.Name }</td>
					<td>${product.Description }</td>
					<td>${product.Price}</td>
					<td><button type="button" class="btn btn-primary btn-xs">+</button></td>
				</tr>
			</c:forEach>


		</tbody>



	</table>

	<%@ include file="footer.jsp"%>
</body>
</html>