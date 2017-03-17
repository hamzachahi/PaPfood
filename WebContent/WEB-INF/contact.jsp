<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Contact Page</title>
<link type="text/css" rel="stylesheet" href="form.css" />
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- ANIMATE STYLE  -->
<link href="assets/css/animate.css" rel="stylesheet" />
<!-- FLEXSLIDER STYLE  -->
<link href="assets/css/flexslider.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
<link href="assets/css/inscriptionbase.css" rel="stylesheet" />
</head>
<body>

	<form method="post" action="">
		<fieldset>
			<legend>Message</legend>
			<p>Contactez-nous via ce formulaire</p>
			<div class="just-txt-div">


				<div class="form-group">
					<label for="email")>Votre adresse mail: </label> <input
						class="form-control" type="text" id="email" name="email"
						value="Insérez votre adresse email" /><span
						class="erreur">${form.erreurs['email']}</span>
				</div>

				<div class="form-group">
					<label for="nom">Votre nom: </label> <input class="form-control"
						type="text" name="nom" id="nom" /><span class="erreur">${form.erreurs['nom']}</span>
				</div>

				<div class="form-group">
					<label for="sujet">Sujet: </label> <input class="form-control"
						type="text" name="sujet" id="sujet" /><span class="erreur">${form.erreurs['sujet']}</span>
				</div>

				<div class="form-group">
					<label for="contenu">Votre message:</label>
					<textarea class="form-control" rows="5" id="contenu" name="contenu"></textarea>
				</div>
				<span class="erreur">${form.erreurs['contenu']}</span>

				<button type="submit" class="btn btn-success btn-lg">Envoyer</button>

				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			</div>
		</fieldset>
	</form>
</body>
</html>