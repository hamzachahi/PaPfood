<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Proposer un produit/service</title>
</head>
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?sensor=false">
	
</script>
<body class="home1" onload="initialize()">
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
						<h1>Informations / Votre adresse compl&egrave;te ne sera pas
							communiqu&eacute;e</h1>
						<div>
							<!--  	<a href="proposer?action=afficherSousVendables&begin=0&end=10">
								<li class="btn btn-style-4">Afficher/raffraichir la liste
									de tous les sous-&Eacute;l&Eacute;ments possibles</li>
							</a>-->
						</div>
						<br />
						<div class="row padding-top-lg padding-bottom-lg">
							<div class="col-md-4">
								<!--Map area start -->
								<div id="map"></div>
								<!--Map area end -->
							</div>
							<div class="col-md-8">
								<div class="row">
									<form action="proposer" method="POST"
										enctype="multipart/form-data">
										<div class="form-group col-md-6">
											<label for="nom">Nom :</label> <input class="form-control"
												type="text" id="nom" name="nom" value="${nom}"
												placeholder="Exp : pizza" />
										</div>
										<div class="form-group col-md-6">
											<label for="type">Type :</label><select class="form-control"
												name="type" id="type">
												<option value="product"
													<c:if test="${requestScope.typep == 'produit'}" >selected</c:if>>Produit</option>
												<option value="service"
													<c:if test="${requestScope.typep == 'service'}" >selected</c:if>>Service</option>
											</select>
										</div>
										<div class="form-group col-md-6">
											<label for="prix">Prix</label> <input type="text"
												class="form-control" id="prix" name="prix" value="${prix}"
												placeholder="Exp : 7.00  &euro;" /><br />
										</div>
										<div class="form-group col-md-12">
											<label for="prix"> Description/Remarques </label>
											<textarea rows="5" class="form-control" id="description"
												name="description"
												placeholder="D&eacute;crivez bri&egrave;vement votre produit. D&Icirc;tes s'il contient des allerg&egrave;nes par exemple"><c:out
													value="${requestScope.description}" /></textarea>
										</div>
										<input id="latlng" name="latlng" type="hidden"
											value="<c:if test = "${latlngt != null}"><c:out value = "${latlngt}"/></c:if><c:if test = "${latlngt == null}"> 48.3906042,-4.4869013</c:if>">
										<div class="form-group col-md-6">
											<label for="num"> N&deg; :</label><input class="form-control"
												id="num" name="num" type="text" value="${streetnum}">
										</div>
										<div class="form-group col-md-6">
											<label for="rue"> Rue :</label><input class="form-control"
												id="rue" name="rue" type="text" value="${streetname}">
										</div>
										<div class="form-group col-md-6">
											<label for="adr">Ville / adresse :</label> <input
												class="form-control" id="adr" name="adr" type="text"
												value="${city}">
										</div>
										<div class="form-group col-md-6">
											<label for="cp"> Code postal :</label> <input
												class="form-control" id="cp" name="cp" type="text"
												value="${postcod}">
										</div>
										<div class="form-group col-md-6">
											<label for="dpt"> D&eacute;partement :</label><input
												class="form-control" id="dpt" name="dpt" type="text"
												value="${depart}">
										</div>
										<div class="form-group col-md-6">
											<label for="pays"> Pays :</label><input class="form-control"
												id="pays" name="pays" type="text" value="${country}">
										</div>
										<div class="form-group col-md-6">
											<label for="but"> Localiser :</label> <input id="but"
												class="form-control" type="button" value="Se localiser..."
												onclick="retrieve()">
										</div>
										<c:if test="${!empty requestScope.success}">
											<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
											<p class="succes">${requestScope.success}</p>
										</c:if>
										<div class="form-group col-md-12">
											<input type="hidden" name="action"
												value="${requestScope.actionvalue}" /> <input type="hidden"
												name="tomodify" value="${requestScope.tomodify}" />
											<button type="submit" class="btn btn-style-4">
												<c:out value="${requestScope.buttonvalue}" />
											</button>
										</div>
									</form>
								</div>
								<c:if test="${requestScope.buttonvalue == 'Modifier'}">
									<%@include file="uploadsalable.jsp"%>
								</c:if>
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
											<tr>
												<th>N&ordm;</th>
												<th>Nom</th>
												<th>Prix</th>
												<th>Description</th>
												<th>Type</th>
												<th>D&eacute;tails</th>
												<th>Ajouter</th>
											</tr>
											<c:set var="total" value="0" />
											<c:set var="i" value="0" />

											<c:forEach var="u"
												items="${requestScope['listeDeSalablesCut']}">
												<tr>
													<td>${i+1}</td>
													<td>${u.name}</td>
													<td>${u.price}</td>
													<td>${u.description}</td>
													<td>${u.type}</td>
													<td><a class="btn btn-rooms-book-now"
														href="details?type=${u.type}&cible=${u.id}">+
															D&eacute;tails</a></td>
													<td>
													<td><a href="proposer?action=addSalable&indice=${i}"><button
																class="btn btn-primary btn-	s">+1</button></a></td>


													<!-- On compte le nombre de users -->
													<c:set var="total" value="${total+1}" />
													<c:set var="i" value="${i+1}" />














												</tr>
											</c:forEach>

											<!-- Affichage du solde total dans la dernière ligne du tableau -->
											<tfoot>
												<tr>
													<td><b>TOTAL</b></td>
													<td></td>
													<td><b>${requestScope['total']}
												</tr>

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
	<c:if test="${param['action'] == 'addSalable'}">
		<h3 align="center" class="primery-title">Sous-produits/Services
			rajoutés</h3>
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
												<tr>
													<td><b>N&ordm;</b></td>
													<td><b>Nom</b></td>
													<td><b>Prix</b></td>
													<td><b>Description</b></td>
													<td><b>Type</b></td>
													<td><b>D&eacute;tails</b></td>
													<td><b>Supprimer</b></td>
												</tr>
											</thead>

											<!-- Ici on affiche les lignes, une par utilisateur -->
											<!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
											<c:set var="ii" value="0" />

											<c:forEach var="uu"
												items="${requestScope['listeDeSousSalables']}">
												<tr>
													<td>${ii+1}</td>
													<td>${uu.name}</td>
													<td>${uu.price}</td>
													<td>${uu.description}</td>
													<td>${uu.type}</td>
													<td><a class="btn btn-rooms-book-now"
														href="details?type=${uu.type}&cible=${uu.id}">+
															D&eacute;tails</a></td>
													<td>
													<td><a
														href="proposer?action=removeSalable&index=${ii}"><button
																class="btn btn-primary btn-	s">-1</button></a></td>



													<!-- On compte le nombre de users -->
													<c:set var="ii" value="${ii+1}" />
												</tr>
											</c:forEach>

											<!-- Affichage du solde total dans la dernière ligne du tableau -->
											<tfoot>
												<tr>
													<td><b>TOTAL</b></td>
													<td></td>
													<td><b>${ii}</b></td>
												</tr>
											</tfoot>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if> </section>
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
	<c:if test="${param['action'] == 'removeSalable'}">
		<h3 align="center" class="primery-title">Sous-produits/services
			rajoutés</h3>
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
												<tr>
													<td><b>N&ordm;</b></td>
													<td><b>Nom</b></td>
													<td><b>Prix</b></td>
													<td><b>Description</b></td>
													<td><b>Type</b></td>
													<td><b>D&eacute;tails</b></td>
													<td><b>Supprimer</b></td>
												</tr>
											</thead>

											<!-- Ici on affiche les lignes, une par utilisateur -->
											<!-- cette variable montre comment on peut utiliser JSTL et EL pour calculer -->
											<c:set var="ii" value="0" />

											<c:forEach var="uu"
												items="${requestScope['listeDeSousSalables']}">
												<tr>
													<td>${ii+1}</td>
													<td>${uu.name}</td>
													<td>${uu.price}</td>
													<td>${uu.description}</td>
													<td>${uu.type}</td>
													<td><a class="btn btn-rooms-book-now"
														href="details?type=${uu.type}&cible=${uu.id}">+
															D&eacute;tails</a></td>
													<td><a
														href="proposer?action=removeSalable&index=${ii}"><button
																class="btn btn-primary btn-	s">-1</button></a></td>



													<!-- On compte le nombre de users -->
													<c:set var="ii" value="${ii+1}" />
												</tr>
											</c:forEach>

											<!-- Affichage du solde total dans la dernière ligne du tableau -->
											<tfoot>
												<tr>
													<td><b>TOTAL</b></td>
													<td></td>
													<td><b>${ii}</b></td>
												</tr>
											</tfoot>
										</table>
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