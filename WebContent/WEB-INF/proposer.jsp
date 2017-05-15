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
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">${sessionUtilisateur.surname}
						${sessionUtilisateur.name}</h2>
					<h3 class="secondery-title">Fa&Icirc;tes une proposition</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<hr />
					<div class="booking-form">
						<h1>Informations</h1>
						<div>
							<li class="btn btn-style-4"><a
								href="proposer?action=afficherSousVendables&begin=0&end=1${requestScope['nbre']}">Afficher/raffraichir
									la liste de tous les sous-&Eacute;l&Eacute;ments possibles</a></li>
						</div>
						<br />

						<div class="col-md-11">
							<div class="row">
								<form action="ServletPropose" method="POST"
									enctype="multipart/form-data">
									<div class="form-group">
										<label for="nom">Nom :</label> <input class="form-control"
											type="text" id="nom" name="nom" placeholder="Exp : pizza" />
									</div>
									<div class="form-group">
										<label for="type">Type :</label><select class="form-control"
											name="type" id="type">
											<option value="product">Produit</option>
											<option value="service">Service</option>
										</select>
									</div>
									<div class="form-group">
										<label for="prix">Prix</label> <input type="text"
											class="form-control" id="prix" name="prix"
											placeholder="Exp : 7.00  &euro;" /><br />
									</div>
									<div class="form-group">
										<label for="photo">Uploader une photo :</label> <input
											class="form-control" type="file" id="fichier" name="fichier"
											value="<c:out value="${fichier.nom}"/>" /> <span
											class="erreur">${formfile.erreurs['fichier']}</span>
											 <p class="${empty formfile.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>        
									</div>
									<div class="form-group">
										<label for="prix"> Description/Remarques </label>
										<textarea rows="5" class="form-control" id="description"
											name="description"
											placeholder="D&eacute;crivez bri&egrave;vement votre produit. D&Icirc;tes s'il contient des allerg&Egrave;nes par exemple"></textarea>
									</div>
									<c:if test="${!empty requestScope.success}">
										<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
										<p class="succes">${requestScope.success}</p>
									</c:if>
									<input type="hidden" name="action"
										value="proposerProductService" />
									<button type="submit" class="btn btn-style-4">Valider</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>

	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<c:if test="${!empty param['message']}">
						<h2>Reçu message : ${param.message}</h2>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${param['action'] == 'afficherSousVendables'}">
		<h3 align="left" class="primery-title">Liste des
			sous-&eacutel&eacutements trouv&eacutes</h3>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-inner padding-top-lg padding-bottom-lg">
						<div class="row">
							<div class="col-md-12">
								<div class="table-responsive">
									<div class="pagination">
										<table class="table shop-cart">
											<thead>
												<td><b>N&ordm;</b></td>
												<td><b>Code</b></td>
												<td><b>Nom</b></td>
												<td><b>Prix</b></td>
												<td><b>Description</b></td>
												<td><b>Type</b></td>
												<td><b>Ajouter</b></td>


											</thead>

											<!-- Ici on affiche les lignes, une par utilisateur -->
											<!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
											<c:set var="total" value="0" />
											<c:set var="i" value="0" />

											<c:forEach var="u"
												items="${requestScope['listeDeSalablesCut']}">
												<tr>
													<td>${i+1}</td>
													<td>${u.code}</td>
													<td>${u.name}</td>
													<td>${u.price}</td>
													<td>${u.description}</td>
													<td>${u.type}</td>
													<td><a
														href="ServletPropose?action=addSalable&indice=${i}">+</a></td>


													<!-- On compte le nombre de users -->
													<c:set var="total" value="${total+1}" />
													<c:set var="i" value="${i+1}" />

												</tr>
											</c:forEach>

											<!-- Affichage du solde total dans la dernière ligne du tableau -->
											<tfoot>
												<td><b>TOTAL</b></td>
												<td></td>
												<td><b>${requestScope['total']}</b></td>
											</tfoot>
										</table>
										${requestScope['pagination']}
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if> </section>
	<%@include file="footer.jsp"%>
</body>
</html>