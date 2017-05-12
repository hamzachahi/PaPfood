<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Proposer un produit/service</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>

	<form action="ServletPropose" method="POST">

		<fieldset>
			<legend>Informations</legend>
			<br /> <label for="nom">Nom :</label> <input type="text" id="nom"
				name="nom" placeholder="Exp : pizza" /><br /> <label for="type">Type
				:</label><select name="type" id="type">
				<option value="product">Produit</option>
				<option value="service">Service</option>
			</select> <br /> <label for="prix">Prix</label> <input type="text" id="prix"
				name="prix" placeholder="Exp : 7.00" /> &euro;<br />
			Description/Remarques <br />
			<textarea rows="4" cols="40" id="description" name="description"
				placeholder="Décrivez brièvement votre produit. Dites s'il contient des allergènes par exemple"></textarea>
			<br /> <label for="photo">Uploader une photo :</label> <input
				type="file" id="photo" name="photo" /> <br />
			<c:if test="${!empty requestScope.success}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">${requestScope.success}</p>
			</c:if>
			<input type="hidden" name="action" value="proposerProductService" />
			<input type="submit" value="Valider">
		</fieldset>
		<c:if test="${!empty param['message']}">
			<h2>Reçu message : ${param.message}</h2>
		</c:if>
		<fieldset>
			<div id="ajout">
				<c:if test="${param['action'] == 'afficherSousVendables'}">
					<h2>Liste des sous-éléments trouvés</h2>
					<div class="pagination">
						<table class="table table-bordered">
							<!-- La ligne de titre du tableau des comptes -->
							<tr>
								<td><b>N°</b></td>
								<td><b>Code</b></td>
								<td><b>Nom</b></td>
								<td><b>Prix</b></td>
								<td><b>Description</b></td>
								<td><b>Type</b></td>
								<td><b>Ajouter</b></td>


							</tr>

							<!-- Ici on affiche les lignes, une par utilisateur -->
							<!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
							<c:set var="total" value="0" />
							<c:set var="i" value="0" />

							<c:forEach var="u" items="${requestScope['listeDeSalablesCut']}">
								<tr>
									<td>${i+1}</td>
									<td>${u.code}</td>
									<td>${u.name}</td>
									<td>${u.price}</td>
									<td>${u.description}</td>
									<td>${u.type}</td>
									<td><a href="ServletPropose?action=addSalable&indice=${i}">+</a></td>


									<!-- On compte le nombre de users -->
									<c:set var="total" value="${total+1}" />
									<c:set var="i" value="${i+1}" />

								</tr>
							</c:forEach>

							<!-- Affichage du solde total dans la dernière ligne du tableau -->
							<tr>
								<td><b>TOTAL</b></td>
								<td></td>
								<td><b>${requestScope['total']}</b></td>
							</tr>
						</table>
						${requestScope['pagination']}
					</div>
				</c:if>

			</div>
		</fieldset>
	</form>
	<ul>

		<li><a
			href="ServletPropose?action=afficherSousVendables&begin=0&end=${requestScope['nbre']}">Afficher/raffraichir	la liste de tous les utilisateurs</a></li>



	</ul>
	<%@include file="footer.jsp"%>

</body>
</html>