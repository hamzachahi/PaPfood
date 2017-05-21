<header>
	<div id="header-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<!-- Start Social Icon -->
					<div class="social-icon-top">
						<a href="#" class="social-icon-top"><i class="fa fa-facebook"
							aria-hidden="true"></i></a> <a href="#" class="social-icon-top"><i
							class="fa fa-twitter" aria-hidden="true"></i></a> <a href="#"
							class="social-icon-top"><i class="fa fa-instagram"
							aria-hidden="true"></i></a> <a href="#" class="social-icon-top"><i
							class="fa fa-linkedin" aria-hidden="true"></i></a> <a href="#"
							class="social-icon-top"><i class="fa fa-google-plus"
							aria-hidden="true"></i></a> <a href="#" class="social-icon-top"><i
							class="fa fa-rss" aria-hidden="true"></i></a>
					</div>
					<!-- End Social icon -->
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<!-- Connection button -->
							<div class="reservation pull-right">
								<c:if test="${empty sessionUtilisateur}">
									<a class="btn-style-1"
										href="${pageContext.request.contextPath}/connexion">Connexion</a>
								</c:if>

								<c:if test="${not empty sessionUtilisateur}">
									<a class="btn-style-1"
										href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a>
								</c:if>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<!-- Reservation button -->
							<div class="reservation pull-right">
								<a href="#" class="btn-reservation" data-toggle="modal"
									data-target="#reservationModal">Alerte</a>
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<!-- Start Top Search -->
							<div class="search-area">
								<form action="#">
									<input placeholder="Rechercher..." type="text">
									<button type="submit">
										<i class="fa fa-search" aria-hidden="true"></i>
									</button>
								</form>
							</div>
							<!-- End Top Search -->
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="main-menu" class="container-fluid">
			<!-- Header Logo -->
			<div class="header-logo">
				<!-- <a href="index.html"><img src="assets/img/logo.png" alt="logo" /></a> -->
			</div>
			<div class="container">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<!-- Start Menu -->
						<div class="main-menu-area">
							<nav>
								<ul>
									<li><a href="${pageContext.request.contextPath}/accueil">Accueil</a></li>
									<li><a href="${pageContext.request.contextPath}/acheter">Commander</a></li>
									<li><a href="${pageContext.request.contextPath}/proposer">Proposer plat</a></li>
									<li class="mega-menu-active"><a href="#">CiboRelation</a> <!-- Start Mega MEnu -->
										<div class="mega-menu-container megamenu-area">
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<ul>
														<li><h3 class="mega-menu-title">Parrainage</h3></li>
														<li><a href="layout.html">Code promo</a></li>
														<li><a href="boutons.html">Invitez vos amis !</a></li>

													</ul>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<ul>
														<li><h3 class="mega-menu-title">Préférences</h3></li>
														<li><a href="#">Plats favoris</a></li>
														<li><a href="#">Cuistots favoris</a></li>
														<li><a href="#">Notes attribuées</a></li>
														<li><a href="#">Notes reçues</a></li>
													</ul>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
													<div class="mega-menu-image">
														<img src="assets/img/chef.png" alt="chef" />
													</div>
												</div>
											</div>
										</div> <!-- End Mega Menu --></li>
									<li><a href="#">Naviguer</a> <!-- Start Sub Menu -->
										<ul>
											<li><a href="${pageContext.request.contextPath}/profile">Modifier
													mon compte</a></li>
											<li><a
												href="${pageContext.request.contextPath}/services">Liste
													des services</a></li>
											<li><a
												href="${pageContext.request.contextPath}/produits">Catégories</a></li>
											<li><a href="${pageContext.request.contextPath}/panier">Votre
													panier</a></li>
											<li><a
												href="${pageContext.request.contextPath}/mescommandes">Mes
													commandes</a></li>
											<li><a
												href="${pageContext.request.contextPath}/mespropositions">Propositions</a></li>
											<li><a href="${pageContext.request.contextPath}/about">Informations
													sur le site</a></li>
											<li><a href="${pageContext.request.contextPath}/team">Notre
													équipe</a></li>
											<li><a href="room-booking.html">Rejoignez notre équipe de courtiers !</a></li>
										<!-- 	<li><a href="menu-details.html">Menu Details</a></li>
											<li><a href="menu.html">Menu</a></li>  -->
										</ul> <!-- End Sub Menu --></li>
									<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
								</ul>
							</nav>
							<div class="mobile-menu"></div>
							<!-- Start Mini cart -->
							<div class="mini-cart pull-right ">
								<a href="${pageContext.request.contextPath}/panier"> <img
									src="assets/img/ecommerce_cart.png" alt="cart" /> <c:if
										test="${not empty sessionUtilisateur}">
										<span class="item-count">${sessionScope.nbrelementspanier}</span>
									</c:if>
								</a>
							</div>
							<!-- End Mini Cart -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</header>