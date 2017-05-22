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
					<h2 class="primery-title">Nom prenom</h2>
					<h3 class="secondery-title">Profil</h3>
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
								<img src="assets/img/single-blog.png" alt="">
							</div>
						</div>
					</div>
					<div class="row padding-top-md">
						<div class="col-md-4">
							<div class="blog-single-left">
								<div class="blog-date-time">
									<label>date:</label> <span>October 12, 2015</span>
								</div>
								<div class="blog-comment">
									<label>comments:</label> <span>3</span>
								</div>
								<div class="blog-authore">
									<label>author:</label> <span>Esmet Hajrizi</span>
								</div>
							</div>
						</div>
						<div class="col-md-8">
							<div class="single-blog-content">
								<h2>Latin America's 50 Best Restaurants</h2>
								<p>Maecenas ac molestie metus. Nullam rutrum sapien ut
									lectus pharetra, ac porttitor tellus pellentesque. In ipsum
									nibh, pellentesque ut nibh nec, ultricies lobortis velit.
									Vivamus maximus vitae orci ut auctor. Nullam porta odio ex, id
									varius quam dignissim ut. Sed laoreet turpis urna, eget maximus
									justo cursus sit amet.</p>
								<blockquote cite="#">Aenean tempor, magna vel
									venenatis viverra, tortor mi tincidunt purus, a semper ex augue
									nec erat. Maecenas non ante vel est consectetur.</blockquote>
								<p>Fusce lobortis, nibh ac porttitor laoreet, odio tellus
									sagittis mauris, at mattis mi justo non dui. Pellentesque
									convallis consequat luctus. Sed interdum erat at velit
									ultricies, eget hendrerit nisi varius. Praesent a ornare nulla.
								</p>
								<h3>And this is another heading</h3>
								<p>Maecenas ac molestie metus. Nullam rutrum sapien ut
									lectus pharetra, ac porttitor tellus pellentesque. In ipsum
									nibh, pellentesque ut nibh nec, ultricies lobortis velit.
									Vivamus maximus vitae orci ut auctor. Nullam porta odio ex, id
									varius quam dignissim ut. Sed laoreet turpis urna, eget maximus
									justo cursus sit amet. Fusce lobortis, nibh ac porttitor
									laoreet, odio tellus sagittis mauris, at mattis mi justo non
									dui. Pellentesque convallis consequat luctus. Sed interdum erat
									at velit ultricies, eget hendrerit nisi varius. Praesent a
									ornare nulla.</p>
							</div>
							<nav class="alfresco-prev-next">
							<ul class="pager">
								<li class="previous"><a href="#">previous post</a></li>
								<li class="next"><a href="#">next post</a></li>
							</ul>
							</nav>
							<div class="comment-area padding-top-lg">
								<div class="comment-count">
									<span>3 Avis</span>
								</div>
								<ul>
									<li>
										<div class="avatar">
											<img src="assets/img/comments/1.png"
												class="assets/img-circle" alt="">
										</div>
										<div class="comment-content">
											<div class="comment-title">
												<h5 class="user-name">Leroy Hanson</h5>
												<span class="date-time">october 13, 2015 / reply</span>
											</div>
											<p>Maecenas ac molestie metus. Nullam rutrum sapien ut
												lectus pharetra, ac porttitor tellus pellentesque. In ipsum
												nibh, pellentesque ut nibh nec, ultricies lobortis velit.
												Vivamus maximus vitae orci ut auctor.</p>
										</div>
										<ul>
											<li>
												<div class="avatar">
													<img src="assets/img/comments/2.png"
														class="assets/img-circle" alt="">
												</div>
												<div class="comment-content">
													<div class="comment-title">
														<h5 class="user-name">Leroy Hanson</h5>
														<span class="date-time">october 13, 2015 / reply</span>
													</div>
													<p>Maecenas ac molestie metus. Nullam rutrum sapien ut
														lectus pharetra, ac porttitor tellus pellentesque. In
														ipsum nibh, pellentesque ut nibh nec, ultricies lobortis
														velit. Vivamus maximus vitae orci ut auctor.</p>
												</div>
											</li>
										</ul>
									</li>
									<li>
										<div class="avatar">
											<img src="assets/img/comments/3.png"
												class="assets/img-circle" alt="">
										</div>
										<div class="comment-content">
											<div class="comment-title">
												<h5 class="user-name">Leroy Hanson</h5>
												<span class="date-time">october 13, 2015 / reply</span>
											</div>
											<p>Maecenas ac molestie metus. Nullam rutrum sapien ut
												lectus pharetra, ac porttitor tellus pellentesque. In ipsum
												nibh, pellentesque ut nibh nec, ultricies lobortis velit.
												Vivamus maximus vitae orci ut auctor.</p>
										</div>
									</li>
								</ul>
								<div class="comment-form">
									<h5>Soummettre un avis</h5>									
									<form method="post" action="account">
										<div class="row">											
											<div class="form-group col-md-12">
												<label>Rédigez l'avis</label>
												<textarea class="form-control" rows="3"></textarea>
											</div>
											<div class="form-group col-md-12">
												<button type="submit" class="btn btn-comments-submit">Enregistrer
													l'avis</button>
											</div>
										</div>
									</form>
								</div>
								<br />
								<hr />
								<br />
								<div class="comment-form">
									<h5>Envoyer un message</h5>
									<form method="post" action="account">
										<div class="row">
											<div class="form-group col-md-12">
												<label>Contactez par message</label>
												<textarea class="form-control" rows="3"></textarea>
											</div>
											<div class="form-group col-md-12">
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
													max="20" placeholder="15,25" class="form-control">
											</div>
											<div class="form-group col-md-12">
												<label>Tapez la note</label>
												<textarea class="form-control" rows="3"></textarea>
											</div>
											<div class="form-group col-md-12">
												<button type="submit" class="btn btn-comments-submit">
													Soumettre</button>
											</div>
										</div>
									</form>
								</div>
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