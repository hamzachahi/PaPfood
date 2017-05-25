<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="liens.jsp"%>
<title>Login/Sign-In</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<link rel="stylesheet" href="assets/css/style.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

<script type="text/javascript" src="assets/js/facebook.js"></script>

</head>

<body class="home1">
	<div class="logmod">
		<div class="logmod__wrapper">
			<span class="logmod__close">Fermer</span>
			<div class="logmod__container">
				<ul class="logmod__tabs">
					<li data-tabtar="lgm-2"><a href="#">Connexion</a></li>
					<li data-tabtar="lgm-1"><a href="#">Inscription</a></li>
				</ul>
				<div class="logmod__tab-wrapper">
					<div class="logmod__tab lgm-1">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Entrez vos
								informations personnellees <strong>pour créer votre
									compte</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" method="post" action="inscription"
								class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="nom">Pseudo/Nom de
											famille*</label> <input class="string optional" maxlength="255"
											id="nom" name="nom" placeholder="Votre Pseudo/Nom de famille"
											type="text" size="50" /> <span class="erreur">${form.erreurs['nom']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="email">Email*</label> <input
											class="string optional" maxlength="255" id="user-email"
											name="email" placeholder="Email" type="email" size="50" /> <span
											class="erreur">${form.erreurs['email']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input string optional">
										<label class="string optional" for="motdepasse">Mot de
											passe *</label> <input class="string optional" maxlength="255"
											id="motdepasse" name="motdepasse" placeholder="Password"
											type="text" size="50" /> <span class="erreur">${form.erreurs['motdepasse']}</span>
									</div>
									<div class="input string optional">
										<label class="string optional" for="confirmation">Répetez
											mot de passe *</label> <input class="string optional" maxlength="255"
											id="confirmation" name="confirmation"
											placeholder="Repeat password" type="text" size="50" /> <span
											class="erreur">${form.erreurs['confirmation']}</span>
									</div>
								</div>
								<div class="simform__actions">
									<input type="submit" value="Inscription" class="sumbit" /> <span
										class="simform__actions-sidetext">En créant votre
										compte vous êtes d'accord sur <a class="special" href="#"
										target="_blank" role="link">les termes & conditions
											générales</a>
									</span>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" class="connect facebook">
									<div class="connect__icon">
										<i class="fa fa-facebook"></i>
									</div>
									<div class="connect__context">
										<span>Créez un compte <strong>avec Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
										<i class="fa fa-google-plus"></i>
									</div>
									<div class="connect__context">
										<span>Créez un compte <strong>Google+</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
					<div class="logmod__tab lgm-2">
						<div class="logmod__heading">
							<span class="logmod__heading-subtitle">Entrez votre
								adresse email et votre mot de passe <strong>pour vous
									connecter</strong>
							</span>
						</div>
						<div class="logmod__form">
							<form accept-charset="utf-8" method="post" action="connexion"
								class="simform">
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-name">Email*</label>
										<input class="string optional" maxlength="255" id="email"
											name="email" placeholder="Email" type="email" size="50"
											value="<c:out value="${utilisateur.email}"/>" /> <span
											class="erreur">${form.erreurs['email']}</span>
									</div>
								</div>
								<div class="sminputs">
									<div class="input full">
										<label class="string optional" for="user-pw">Mot de
											passe *</label> <input class="string optional" maxlength="255"
											id="motdepasse" name="motdepasse" placeholder="Password"
											type="password" size="50" /> <span class="hide-password">Rendre
											visible</span> <span class="erreur">${form.erreurs['motdepasse']}</span>
									</div>
								</div>
								<div>
									<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

									<c:if test="${!empty sessionScope.sessionUtilisateur}">
										<p class="succes">Vous êtes connecté(e) avec l'adresse :
											${sessionScope.sessionUtilisateur.email}</p>
									</c:if>
								</div>
								<div class="simform__actions">
									<input type="submit" value="Connexion" class="sumbit" /> <span
										class="simform__actions-sidetext"><a class="special"
										role="link" href="#">Mot de passe oublié?<br>Cliquez
											ici
									</a></span>
								</div>
							</form>
						</div>
						<div class="logmod__alter">
							<div class="logmod__alter-container">
								<a href="#" class="connect facebook">
									<div class="connect__icon" data-max-rows="1" data-size="xlarge"
										data-show-faces="true" onlogin="renderData();" data-scope=""
										data-auto-logout-link="true">
										<i class="fa fa-facebook"></i>
									</div>
									<div class="connect__context">
										<span>Connectez-vous avec <strong>Facebook</strong></span>
									</div>
								</a> <a href="#" class="connect googleplus">
									<div class="connect__icon">
										<i class="fa fa-google-plus"></i>
									</div>
									<div class="connect__context">
										<span>Connectez-vous avec <strong>Google+</strong></span>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="assets/js/index.js"></script>
	<script type="text/javascript">
		function renderData() {
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "ServletConnect",
				dataType : "json",
				success : function() {
					console.log('VOOOOOIIIIIILAAAAA');
				}
			});
		}
	</script>


</body>
</html>
