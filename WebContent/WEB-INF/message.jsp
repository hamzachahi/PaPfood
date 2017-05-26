<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Messagerie</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<c:if test="${not empty messagem }">
		<section id="page-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="restaurent-menu-title">
						<h2 class="primery-title">${sessionUtilisateur.surname}
							${sessionUtilisateur.name}</h2>
						<h3 class="secondery-title">Votre messagerie</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-inner padding-top-xlg">
						<div class="comment-area padding-top-lg">
							<ul>
								<li>
									<div class="avatar">
										<img src="${messagem.realSender.accountPicture}"
											class="img-circle" alt="">
									</div>
									<div class="comment-content">
										<div class="comment-title">
											<h5 class="user-name">Message de
												${messagem.realSender.surname} ${messagem.realSender.name}</h5>
											<span class="date-time">Envoyé le ${messagem.sentDate}</span>
										</div>
										<p>${messagem.content}</p>
									</div>
								</li>
							</ul>
							<div class="comment-form">
								<h5>Ecrire une réponse</h5>
								<form method="post" action="message">
									<div class="row">
										<div class="form-group col-md-12">
											<label>Répondre à l'expéditeur</label>
											<textarea class="form-control" rows="3" name="contentmessage"></textarea>
										</div>
										<div class="form-group col-md-12">
											<input type="hidden" name="action" value="contacter" /> <input
												type="hidden" name="idreceiver" value="${messagem.receiver}" />
											<button type="submit" class="btn btn-comments-submit">Répondre</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</c:if>
	<c:if test="${not empty sentmessage }">
		<section id="page-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="restaurent-menu-title">
						<h2 class="primery-title">${sessionUtilisateur.surname}
							${sessionUtilisateur.name}</h2>
						<h3 class="secondery-title">Votre messagerie</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="page-inner padding-top-xlg">
						<div class="comment-area padding-top-lg">
							<ul>
								<li>
									<div class="avatar">
										<img src="${sentmessage.realSender.accountPicture}"
											class="img-circle" alt="">
									</div>
									<div class="comment-content">
										<div class="comment-title">
											<h5 class="user-name">Message de
												${sentmessage.realSender.surname}
												${sentmessage.realSender.name}</h5>
											<span class="date-time">Envoyé le
												${sentmessage.sentDate}</span>
										</div>
										<p>${sentmessage.content}</p>
									</div>
								</li>
							</ul>
							<div class="comment-form">
								<h5>Ecrire une réponse</h5>
								<form method="post" action="message">
									<div class="row">
										<div class="form-group col-md-12">
											<label>Répondre à l'expéditeur</label>
											<textarea class="form-control" rows="3" name="contentmessage"></textarea>
										</div>
										<div class="form-group col-md-12">
											<input type="hidden" name="action" value="contacter" /> <input
												type="hidden" name="idreceiver" value="${messagem.receiver}" />
											<button type="submit" class="btn btn-comments-submit">Répondre</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</c:if>
	<c:if test="${not empty listUnreadMessages }">
		<section id="page-title-area">
		<h3 align="left" class="primery-title">Messages non lus</h3>
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
												<th>Date d'envoi</th>
												<th>De :</th>
												<th>+ d'infos</th>
											</tr>
										</thead>

										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="messageur"
												items="${requestScope['listUnreadMessages']}">
												<tr>
													<td>${i+1}</td>
													<td>${messageur.sentDate}</td>
													<td><a class="btn btn-rooms-book-now"
														href="account?cible=${messageur.sender}">+
															${messageur.realSender.surname}
															${messageur.realSender.name}</a></td>
													<td align="center"><a style="color: white;"
														href="message?cible=${messageur.id}&statut=unread&readornot=notread"><button
																class="btn btn-primary btn-	s">Afficher</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalUnreadMessages']}</b></td>
											</tr>
										</tfoot>
									</table>
								</div>
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
	</c:if>
	<c:if test="${not empty listReadMessages }">
		<section id="page-title-area">
		<h3 align="left" class="primery-title">Messages lus</h3>
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
												<th>Date d'envoi</th>
												<th>Date de réception</th>
												<th>De :</th>
												<th>+ d'infos</th>
											</tr>
										</thead>

										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="messager"
												items="${requestScope['listReadMessages']}">
												<tr>
													<td>${i+1}</td>
													<td>${messager.sentDate}</td>
													<td>${messager.receiveDate}</td>
													<td><a class="btn btn-rooms-book-now"
														href="account?cible=${messager.sender}">+
															${messager.realSender.surname}
															${messager.realSender.name}</a></td>
													<td align="center"><a style="color: white;"
														href="message?cible=${messager.id}&statut=read&readornot=read"><button
																class="btn btn-primary btn-	s">Afficher</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalReadMessages']}</b></td>
											</tr>
										</tfoot>
									</table>
								</div>
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
	</c:if>
	<c:if test="${not empty listSendMessages }">
		<section id="page-title-area">
		<h3 align="left" class="primery-title">Messages envoyés</h3>
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
												<th>Date d'envoi</th>
												<th>Date de réception</th>
												<th>A :</th>
												<th>+ d'infos</th>
											</tr>
										</thead>

										<tbody>
											<c:set var="i" value="0" />
											<c:forEach var="messages"
												items="${requestScope['listSendMessages']}">
												<tr>
													<td>${i+1}</td>
													<td>${messages.sentDate}</td>
													<td>${messages.receiveDate}</td>
													<td><a class="btn btn-rooms-book-now"
														href="account?cible=${messages.sender}">+
															${messages.realSender.surname}
															${messages.realSender.name}</a></td>
													<td align="center"><a style="color: white;"
														href="message?cible=${messages.id}&readornot=sentread"><button
																class="btn btn-primary btn-	s">Afficher</button></a></td>
												</tr>
												<c:set var="i" value="${i+1}" />
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td><b>TOTAL</b></td>
												<td></td>
												<td></td>
												<td><b>${requestScope['totalSendMessages']}</b></td>
											</tr>
										</tfoot>
									</table>
								</div>
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
	</c:if>
	<!-- Start Footer Section -->
	<%@include file="footer.jsp"%>
</body>
</html>