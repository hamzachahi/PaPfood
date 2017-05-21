<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@include file="liens.jsp"%>

</head>
<body class="home1" >
	<%@include file="header.jsp"%>
 <!-- Start Slider area -->
    <section id="slider-area">
    	<div class="container">
    		<!-- slider -->
            <div class="slider-area ">
                <div class="bend niceties preview-1">
                    <div id="ensign-nivoslider" class="slides">   
                        <img class="no-bg" src="assets/img/slider/bg.jpg" alt="slider" title="#slider-direction-1"  />
                        <img class="no-bg" src="assets/img/slider/bg.jpg" alt="slider" title="#slider-direction-2"  />
                        <img class="no-bg" src="assets/img/slider/bg.jpg" alt="slider" title="#slider-direction-3"  />
                    </div>
                    <!-- direction 1 -->
                    <div id="slider-direction-1" class="t-cn slider-direction">
                        <div class="slider-content t-cn s-tb slider-1">
                            <div class="title-container s-tb-c title-compress">
                                <div class="layear-1-1"> <img src="assets/img/slider/slide1.png" alt="slide1" /> </div>
                                <div class="layear-1-2">
                                    <h2>Il Cibo</h2>
                                    <h3>Envie d'un plat en particulier ?</h3>
                                    <h3>Cibo vous le ram&egrave;ne &ccedil;a jusqu'&agrave; chez vous !</h3>
                                    <h3>Rapide et pratique</h3>
                                    <a class="btn-style-1" href="${pageContext.request.contextPath}/order">Commander maintenant !</a>
                                </div>
                            </div>
                        </div>  
                    </div>
                    <!-- direction 2 -->
                    <div id="slider-direction-2" class="slider-direction">
                        <div class="slider-content t-cn s-tb slider-2">
                            <div class="title-container s-tb-c">
                                <div class="layear-1-1"> <img class="slide-image" src="assets/img/slider/slide2.png" alt="slide2" /> </div>
                                <div class="layear-1-2">
                                    <h2 class="title1">Cibo</h2>
                                    <h3 class="title2">La qualit&eacute;</h3>
                                    <p>Avant que chaque plat ne soit propos&eacute;, nous veillons &agrave; ce qu'il respecte notre charte de qualit&eacute; afin de vous proposer le meilleur &agrave; chacune de vos commandes. </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- direction 3 -->
                    <div id="slider-direction-3" class="slider-direction">
                        <div class="slider-content t-lfr s-tb slider-3">
                            <div class="title-container s-tb-c">
                                <div class="layear-1-1">
                                    <h2 class="title1">Bienvenue sur Cibo !</h2>
                                    <h2 class="title2">Commandez sans plus attendre !</h2>
                                    <div class="image-border"></div>
                                    <div class="button-area">
                                        <a href="${pageContext.request.contextPath}/order" class="btn-style-2">Commander !</a>
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
                    <!-- Room promotion -->
                    <div class="room-promotion">
                        <a href="#">
                            <img src="assets/img/room/room1.png" alt="room" />
                            <span class="discount">20% Off</span>
                        </a>
                        <a href="#">
                            <h3 class="room-promotion-title">room promotion</h3>
                        </a>
                       <p>Maecenas dapibus dui felis, eu dapibus augue cursus eu. Lorem ipsum dolor.</p>
                    </div>
                    <!-- End Room Promotion -->
                </div>
                <div class="col-md-4">
                    <!-- Hotel Menu Area -->
                    <div class="home-one-menu-area">
                        <!-- Hotel Menu Title -->
                        <h3 class="menu-title">The menu</h3>
                        <ul class="scrollbar-inner">
                            <!-- Single Menu -->
                            <li>
                                <a href="#">
                                    <img class="alignleft" src="assets/img/menu/menu1.png" alt="menu" />
                                    <span class="restaurant-menu-title">Dimo’s Pizza</span>
                                    <span class="item-price">$24.1</span>
                                    <p class="menu-description">Maecenas dapibus</p>
                                </a>
                            </li>
                            <!-- Single Menu -->
                            <li>
                                <a href="#">
                                    <img class="alignleft" src="assets/img/menu/menu2.png" alt="menu" />
                                    <span class="restaurant-menu-title">Baked Potato</span>
                                    <span class="item-price">$12</span>
                                    <p class="menu-description">Maecenas dapibus</p>
                                </a>
                            </li>
                            <!-- Single Menu -->
                            <li>
                                <a href="#">
                                    <img class="alignleft" src="assets/img/menu/menu3.png" alt="menu" />
                                    <span class="restaurant-menu-title">Salmon Steak</span>
                                    <span class="item-price">$18.9</span>
                                    <p class="menu-description">Maecenas dapibus</p>
                                </a>
                            </li>
                            <!-- Single Menu -->
                            <li>
                                <a href="#">
                                    <img class="alignleft" src="assets/img/menu/menu1.png" alt="menu" />
                                    <span class="restaurant-menu-title">Salmon Steak</span>
                                    <span class="item-price">$14</span>
                                    <p class="menu-description">Maecenas dapibus</p>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- End Hotel Menu -->
                </div>
                <div class="col-md-4">
                    <!-- Deals Area -->
                    <div class="travel-deals">
                        <img src="assets/img/news-1.png" alt="news" />
                        <div class="deals-content">
                            <div class="button-area">
                                <a class="btn-news" href="#">travel deals</a>
                            </div>
                            <a href="#">
                                <h4 class="news-title">Culture, Heritage, and Literary Legends</h4>
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