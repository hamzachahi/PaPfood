<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="liens.jsp"%>
<title>Connexion</title>
</head>
<body>

	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

	<script type="text/javascript" src="assets/js/facebook.js"></script>

	<div id="fb-root"></div>

	<!--  formulaire de connexion -->
	<form method="post" action="connexion">
		<fieldset>
			<legend>Connexion</legend>
			<p>Vous pouvez vous connecter via ce formulaire.</p>

			<label for="nom">Adresse email <span class="requis">*</span></label>
			<input type="email" id="email" name="email"
				value="<c:out value="${utilisateur.email}"/>" size="20"
				maxlength="60" /> <span class="erreur">${form.erreurs['email']}</span>


			<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
			<input type="password" id="motdepasse" name="motdepasse" value=""
				size="20" maxlength="20" /> <span class="erreur">${form.erreurs['motdepasse']}</span>


			<input type="submit" value="Connexion" class="sansLabel" />


			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.email}</p>
			</c:if>
		</fieldset>
	</form>


	<div class="fb-login-button" data-max-rows="1" data-size="xlarge"
		data-show-faces="true" onlogin="renderData();" data-scope=""
		data-auto-logout-link="true"></div>


	<div id="status"></div>


	<p id="fb_login_button_1">
		<fb:login-button size="medium" scope="user_about_me">Sign in using Facebook</fb:login-button>
	</p>

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
		<%@include file="footer.jsp"%>
	
</body>