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
					<a href="checkout.html">
						<h3>
							<div class="total">
								<span class="simpleCart_total"></span>
							</div>
							<img src="images/cart.png" alt="" />
						</h3>
					</a>
					<p>
						<a href="javascript:;" class="simpleCart_empty">Empty Cart</a>
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
				<span><a href="ServletProfile"><i class="glyphicon glyphicon-phone"></i>Modifier Mon compte</a></span>
				<p>Call Junior</p>
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
							<li><a href="index.html">Home</a></li>
							<li class="dropdown mega-dropdown active"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Women<span
									class="caret"></span></a>
								<div class="dropdown-menu mega-dropdown-menu">
									<div class="container-fluid">
										<!-- Tab panes -->
										<div class="tab-content">
											<div class="tab-pane active" id="men">
												<ul class="nav-list list-inline">
													<li><a href="women.html"><img src="images/t7.jpg"
															class="img-responsive" alt="" /></a></li>
													<li><a href="women.html"><img src="images/t8.jpg"
															class="img-responsive" alt="" /></a></li>
													<li><a href="women.html"><img src="images/t9.jpg"
															class="img-responsive" alt="" /></a></li>
													<li><a href="women.html"><img src="images/t11.jpg"
															class="img-responsive" alt="" /></a></li>
													<li><a href="women.html"><img src="images/t1.jpg"
															class="img-responsive" alt="" /></a></li>
													<li><a href="women.html"><img src="images/t12.jpg"
															class="img-responsive" alt="" /></a></li>
												</ul>
											</div>
										</div>
									</div>
									<!-- Nav tabs -->

								</div></li>
							<li class="dropdown mega-dropdown active"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Catégories<span
									class="caret"></span></a>
								<div class="dropdown-menu mega-dropdown-menu">
									<div class="container-fluid">
										<!-- Tab panes -->
										<div class="tab-content">
											<div class="tab-pane active" id="men">
												<ul class="nav-list list-inline">
													<li><a href="men.html"><img src="images/pizza.png"
															class="img-responsive" alt="" /></a> Italien</li>
													<li><a href="men.html"><img src="images/nem.png"
															class="img-responsive" alt="" /></a>Asiatique</li>
													<li><a href="men.html"><img
															src="images/salade.png" class="img-responsive" alt="" /></a>Salade</li>
													<li><a href="men.html"><img
															src="images/dessert.png" class="img-responsive" alt="" /></a>Dessert</li>
													<!--  <li><a href="men.html"><img src="images/t5.jpg" class="img-responsive" alt=""/></a></li>
												<li><a href="men.html"><img src="images/t6.jpg" class="img-responsive" alt=""/></a></li>
											-->
												</ul>
											</div>

										</div>
									</div>
									<!-- Nav tabs -->

								</div></li>
							<li><a href="products.html">Products</a></li>
							<li><a href="account.html">Se connecter</a></li>
							<li class="last"><a href="contact.html">Contact</a></li>
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