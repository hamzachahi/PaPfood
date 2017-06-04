<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>

</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<!-- Start Slider area -->
	<section id="slider-area">
	<div class="container">
		<!-- slider -->
		<div class="slider-area ">
			<div class="bend niceties preview-1">
				<div id="ensign-nivoslider" class="slides">
					<img class="no-bg" src="assets/img/slider/bg.jpg" alt="slider"
						title="#slider-direction-1" /> <img class="no-bg"
						src="assets/img/slider/bg.jpg" alt="slider"
						title="#slider-direction-2" /> <img class="no-bg"
						src="assets/img/slider/bg.jpg" alt="slider"
						title="#slider-direction-3" />
				</div>
				<!-- direction 1 -->
				<div id="slider-direction-1" class="t-cn slider-direction">
					<div class="slider-content t-cn s-tb slider-1">
						<div class="title-container s-tb-c title-compress">
							<div class="layear-1-1">
								<img src="assets/img/slider/slide1.png" alt="slide1" />
							</div>
							<div class="layear-1-2">
								<h2>Cibo's</h2>
								<h3>Envie d'un plat en particulier ?</h3>
								<h3>Cibo's vous le ram&egrave;ne &ccedil;a jusqu'&agrave;
									chez vous !</h3>
								<h3>Rapide et pratique</h3>
								<a class="btn-style-1"
									href="${pageContext.request.contextPath}/acheter">Commander
									maintenant !</a>
							</div>
						</div>
					</div>
				</div>
				<!-- direction 2 -->
				<div id="slider-direction-2" class="slider-direction">
					<div class="slider-content t-cn s-tb slider-2">
						<div class="title-container s-tb-c">
							<div class="layear-1-1">
								<img class="slide-image" src="assets/img/slider/slide2.png"
									alt="slide2" />
							</div>
							<div class="layear-1-2">
								<h2 class="title1">Cibo's</h2>
								<h3 class="title2">La qualit&eacute;</h3>
								<p>Avant que chaque plat ne soit propos&eacute;, nous
									veillons &agrave; ce qu'il respecte notre charte de
									qualit&eacute; afin de vous proposer le meilleur &agrave;
									chacune de vos commandes.</p>
							</div>
						</div>
					</div>
				</div>
				<!-- direction 3 -->
				<div id="slider-direction-3" class="slider-direction">
					<div class="slider-content t-lfr s-tb slider-3">
						<div class="title-container s-tb-c">
							<div class="layear-1-1">
								<h2 class="title1">Bienvenue sur Cibo's !</h2>
								<h2 class="title2">Commandez sans plus attendre !</h2>
								<div class="image-border"></div>
								<div class="button-area">
									<a href="${pageContext.request.contextPath}/acheter"
										class="btn-style-2">Commander !</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- slider end-->
	</div>
	</section>
	<!-- End Slider Section -->
	<section id="home-one-content-area">
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<!-- Hotel Menu Area -->
				<div class="home-one-menu-area">
					<!-- Hotel Menu Title -->
					<h3 class="menu-title">${requestScope.titleSer}</h3>
					<ul class="scrollbar-inner">
						<!-- Single Menu -->
						<c:set var="i" value="0" />
						<c:forEach var="article1" items="${requestScope['listServices']}">
							<li><a
								href="details?type=${article1.type}&cible=${article1.id}"> <img
									class="alignleft"
									src="<c:if test="${not empty article1.mainImage}"><c:out value="${article1.mainImage}"/></c:if><c:if test="${empty article1.mainImage}">assets/img/menu-details.png</c:if>"
									width="100" height="100" alt="offre" /><span
									class="restaurant-menu-title"></span>${article1.name}<span
									class="item-price">${article1.price} &euro;</span>
									<p class="menu-description">${article1.description}</p>
							</a></li>
							<c:set var="i" value="${i+1}" />
						</c:forEach>
					</ul>
				</div>
				<!-- End Hotel Menu -->
			</div>
			<div class="col-md-4">
				<!-- Hotel Menu Area -->
				<div class="home-one-menu-area">
					<!-- Hotel Menu Title -->
					<h3 class="menu-title">${requestScope.titlePro}</h3>
					<ul class="scrollbar-inner">
						<!-- Single Menu -->
						<c:set var="i" value="0" />
						<c:forEach var="article2" items="${requestScope['listProducts']}">
							<li><a
								href="details?type=${article2.type}&cible=${article2.id}"> <img
									class="alignleft"
									src="<c:if test="${not empty article2.mainImage}"><c:out value="${article2.mainImage}"/></c:if><c:if test="${empty article2.mainImage}">assets/img/menu-details.png</c:if>"
									width="100" height="100" alt="offre" /> <span
									class="restaurant-menu-title"></span>${article2.name}<span
									class="item-price">${article2.price} &euro;</span>
									<p class="menu-description">${article2.description}</p>
							</a></li>
							<c:set var="i" value="${i+1}" />
						</c:forEach>
					</ul>
				</div>
				<!-- End Hotel Menu -->
			</div>
			<div class="col-md-4">
				<!-- Deals Area -->
				<div class="travel-deals">
					<img src="assets/img/UNS.png" alt="news" />
					<div class="deals-content">
						<div class="button-area">
							<a class="btn-news"
								href="${pageContext.request.contextPath}/team">Suivez nos
								partenaires</a>
						</div>
						<a href="http://miageprojet2.unice.fr">
							<h4 class="news-title">MIAGE Unice</h4>
						</a>
					</div>
				</div>
				<!-- End Deals -->
			</div>
		</div>
	</div>
	</section>

	<%@include file="footer.jsp"%>
</body>
</html>