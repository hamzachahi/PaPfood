<!--header-->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="col-sm-4 logo animated wow fadeInLeft"
				data-wow-delay=".5s">
				<h1>
					<a href="index.html">Yummy <span>Shop</span></a>
				</h1>
			</div>
			<div class="col-sm-4 world animated wow fadeInRight"
				data-wow-delay=".5s">
				<div class="cart box_1">
					<h3>
						<div class="total">
							<span class="simpleCart_total"></span>
						</div>
						<img src="assets/images/cart.png" alt="Mon Panier" />
					</h3>
					<p>
						<a href="${pageContext.request.contextPath}/panier"
							class="simpleCart_empty">Empty Cart</a>
					</p>

				</div>
			</div>
			<div class="col-sm-2 number animated wow fadeInRight"
				data-wow-delay=".5s">
				<span><i class="glyphicon glyphicon-phone"></i>06 41 06 73 15</span>
				<p>Call Junior</p>
			</div>
			<div class="col-sm-2 number animated wow fadeInRight"
				data-wow-delay=".5s">
				<span><a href="${pageContext.request.contextPath}/profile"><i
						class="glyphicon glyphicon-phone"></i>Modifier Mon compte</a></span>
			</div>
			<div class="col-sm-2 search animated wow fadeInRight"
				data-wow-delay=".5s">
				<a class="play-icon popup-with-zoom-anim" href="#small-dialog"><i
					class="glyphicon glyphicon-search"> </i> </a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="container">
		<div class="head-top">
			<div class="n-avigation">

				<nav class="navbar nav_bottom" role="navigation">

					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header nav_2">
						<button type="button"
							class="navbar-toggle collapsed navbar-toggle1"
							data-toggle="collapse" data-target="#bs-megadropdown-tabs">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"></a>
					</div>
					<!-- Collect the nav links, forms, and other content for toggling -->
					<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
						<ul class="nav navbar-nav nav_1">
							<li><a href="${pageContext.request.contextPath}/accueil">Accueil</a></li>
							<li><a href="${pageContext.request.contextPath}/acheter">Acheter
									un produit</a></li>
							<li><a href="${pageContext.request.contextPath}/proposer">Proposer
									un produit</a></li>
							<c:if test="${empty sessionUtilisateur}">
								<li><a href="${pageContext.request.contextPath}/connexion">Se
										connecter</a></li>
							</c:if>

							<c:if test="${not empty sessionUtilisateur}">
								<li><a
									href="${pageContext.request.contextPath}/deconnexion">Se
										déconnecter</a></li>
							</c:if>

							<li class="last"><a
								href="${pageContext.request.contextPath}/contact">Contact</a></li>
						</ul>
					</div>
					<!-- /.navbar-collapse -->

				</nav>
			</div>


			<div class="clearfix"></div>
			<!---pop-up-box---->
			<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
				media="all" />
			<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
			<!---//pop-up-box---->
			<div id="small-dialog" class="mfp-hide">
				<div class="search-top"></div>
			</div>
			<script>
				$(document).ready(function() {
					$('.popup-with-zoom-anim').magnificPopup({
						type : 'inline',
						fixedContentPos : false,
						fixedBgPos : true,
						overflowY : 'auto',
						closeBtnInside : true,
						preloader : false,
						midClick : true,
						removalDelay : 300,
						mainClass : 'my-mfp-zoom-in'
					});

				});
			</script>
			<!---->
		</div>
	</div>
</div>