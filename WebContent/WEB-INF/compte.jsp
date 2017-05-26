<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Profil</title>
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
					<c:if test="${sessionUtilisateur.id ne owner.id}">
						<h3 class="secondery-title">Profil de : ${owner.surname}
							${owner.name}</h3>
					</c:if>
					<c:if test="${ sessionUtilisateur.id eq owner.id }">
						<h3 class="secondery-title">Votre Profil</h3>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="row">
						<div class="col-md-12">
							<div class="single-blog">
								<img src="${owner.accountPicture}" alt="">
							</div>
						</div>
					</div>
					<div class="row padding-top-md">
						<div class="col-md-4">
							<div class="blog-single-left">
								<div class="blog-date-time">
									<label>Dernière connexion:</label> <span>${lastconnexion}</span>
								</div>
								<div class="blog-comment">
									<label>Moyenne des notes:</label> <span>${moyennenotes}</span>
								</div>
								<div class="blog-comment">
									<label>Nombre de notes:</label> <span>${totalEval}</span>
								</div>
								<div class="blog-authore">
									<label>author:</label> <span>${owner.name}
										${owner.surname}</span>
								</div>
							</div>
						</div>
						<div class="col-md-8">
							<c:forEach var="postr" items="${requestScope['postReal']}">
								<div class="single-blog-content">
									<h2>${postr.title}</h2>
									<p>${postr.content}</p>
									<h3>${postr.datePosted}</h3>
								</div>
							</c:forEach>
							<nav class="alfresco-prev-next">
							<ul class="pager">
								<c:if test="${ starti >= 0 }">
									<li class="previous"><a
										href="account?naviguer=previouspost&starti=${starti}&finali=${finali}&member=${owner.id}">previous
											post</a></li>
								</c:if>
								<li class="center">${numberpost}publications</li>
								<c:if test="${ startf <= (numberpost-1) }">
									<li class="next"><a
										href="account?naviguer=nextpost&startf=${startf}&finalf=${finalf}&member=${owner.id}">next
											post</a></li>
								</c:if>

							</ul>
							</nav>
							<div class="comment-area padding-top-lg">
								<div class="comment-count">
									<span>${totalEval} evaluations</span>
								</div>
								<c:forEach var="evaluation"
									items="${requestScope['listEvaluations']}">
									<ul>
										<li>
											<div class="avatar">
												<img src="${evaluation.author.accountPicture}"
													class="assets/img-circle" alt="">
											</div>
											<div class="comment-content">
												<div class="comment-title">
													<h5 class="user-name">
														<a href="account?cible=${evaluation.author.id}">${evaluation.author.surname}
															${evaluation.author.name}</a>
													</h5>
													<span class="date-time">${evaluation.datePosted}</span>
												</div>
												<div class="blog-comment">
													<label>Note attribuée:</label> <span>${evaluation.note}</span>
												</div>
												<p>${evaluation.comments}</p>
											</div>
										</li>
									</ul>
								</c:forEach>
								<p class="text-left">${totalEval}avis</p>
								<div class="product-pagination">
									<nav>
									<ul class="pagination">${requestScope['pagination']}
									</ul>
									</nav>
								</div>
								<c:if test="${sessionUtilisateur.id eq owner.id}">

									<div class="comment-form">
										<h5>Poster</h5>
										<form method="post" action="account">
											<div class="row">
												<div class="form-group col-md-12">
													<label>Titre/Sujet</label> <input type="text"
														class="form-control" name="sujet"
														placeholder="Saisissez le sujet ou titre">
												</div>
												<div class="form-group col-md-12">
													<label>Rédigez le post</label>
													<textarea class="form-control" rows="3" name="contentpost"></textarea>
												</div>
												<div class="form-group col-md-12">
													<input type="hidden" name="action" value="poster" /> <input
														type="hidden" name="ownerid" value="${owner.id}" />
													<button type="submit" class="btn btn-comments-submit">Enregistrez
														le post</button>
												</div>
											</div>
										</form>
									</div>
									<br />
									<hr />
									<br />
								</c:if>
								<c:if test="${sessionUtilisateur.id ne owner.id}">

									<div class="comment-form">
										<h5>Envoyer un message</h5>
										<form method="post" action="account">
											<div class="row">
												<div class="form-group col-md-12">
													<label>Contactez par message</label>
													<textarea class="form-control" rows="3"
														name="contentmessage"></textarea>
												</div>
												<div class="form-group col-md-12">
													<input type="hidden" name="action" value="contacter" /> <input
														type="hidden" name="ownerid" value="${owner.id}" />
													<button type="submit" class="btn btn-comments-submit">Envoyez
														le message</button>
												</div>
											</div>
										</form>
									</div>
									<br />
									<hr />
									<br />
									<div class="comment-form">
										<h5>Notez</h5>
										<form method="post" action="account">
											<div class="row">
												<div class="form-group col-md-12">
													<label>Evaluation /20</label> <input type="number" min="1"
														max="20" placeholder="15,25" class="form-control"
														name="note">
												</div>
												<div class="form-group col-md-12">
													<label>Tapez la note</label>
													<textarea class="form-control" rows="3" name="contentnote"></textarea>
												</div>
												<div class="form-group col-md-12">
													<input type="hidden" name="action" value="noter" /> <input
														type="hidden" name="ownerid" value="${owner.id}" />
													<button type="submit" class="btn btn-comments-submit">
														Soumettre</button>
												</div>
											</div>
										</form>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- Start Footer Section -->
	<%@include file="footer.jsp"%>
</body>
</html>