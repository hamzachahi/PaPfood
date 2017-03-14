<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Page</title>
</head>
<body>

	<form method="post" action="">
		<fieldset>
			<legend>Message</legend>
			<p>Contactez-nous via ce formulaire</p>
			<div class="just-txt-div">


				<div class="form-group">
					<label for="email")>Votre adresse mail: </label> <input
						class="form-control" type="email" id="email" name="email"
						value="<c:out value="${form.email}"/>" size="20" maxlength="60" />
				</div>

				<div class="form-group">
					<label for="nom">Votre nom: </label> <input class="form-control"
						type="text" name="nom" id="nom" />
				</div>

				<div class="form-group">
					<label for="sujet">Sujet: </label> <input class="form-control"
						type="text" name="sujet" id="sujet" />
				</div>

				<div class="form-group">
					<label for="contenu">Contenu</label>
					<textarea class="form-control" rows="5" id="contenu" name="contenu"></textarea>
				</div>

				<button type="submit" class="btn btn-success btn-lg">Envoyer</button>

			</div>
		</fieldset>
	</form>
</body>
</html>