<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="liens.jsp"%>
<title>Détails</title>
</head>
<body class="home2">
	<%@ include file="header.jsp"%>
	<section id="restaurent-menu-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="restaurent-menu-title">
                        <h2 class="primery-title">Monsieur/Madame ${sessionUtilisateur.name} ${sessionUtilisateur.surname}</h2>
                        <h3 class="secondery-title">Voici les d&eacute;tails de l'élément</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="single-restaurant-menu-inner">
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 padding-md-right border-right">
                                <div class="single-menu-title">
                                    <h3 class="title-style-10 text-right color-1">Prix : ${salable.price}</h3>
                                    <h4 class="title-style-11 color-2 text-right">${salable.name}</h4>
                                    <hr class="hr-border-bottom" >
                                </div>
                                <div class="div-disctiption">
                                    <p class="text-right">${salable.description} </p>
                                </div>
                                <div class="single-menu-button-area text-right margin-medioum-top">
                                    <a href="acheter?action=chargerPanier&codearticle=${salable.code}" class="btn-style-3 pull-right">Ajouter au panier</a>
                                </div>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 xs-border-right">
                                <div class="details-menu-image padding-sm-left"><img src="img/menu-details.png" alt=""></div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 border-right padding-md-right padding-xl-top">
                                <div class="content-area border-right-bullet">
                                    <div class="content-title">
                                        <h5 class="title-counter pull-right">Date d&acute;ajout</h5>
                                    </div>
                                    <div class="content-text-area">
                                        <p class="text-right">${salable.addDate}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 col-md-offset-6 border-left padding-md-left">
                                <div class="content-area border-left-bullet">
                                    <div class="content-title">
                                        <h5 class="title-counter pull-left">Par</h5>
                                    </div>
                                    <div class="content-text-area">
                                        <p class="text-left">${owner.name} ${owner.surname}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<%@ include file="footer.jsp"%>
</body>
</html>