<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Compléter votre profil</title>
</head>
<body>

	<form action="ServletProfile" method="POST">
		<fieldset>
			<legend>Saisissez vos informations</legend>
			<br />
			<label for="sname">Nom de famille  :</label> <input
				type="text" id="sname" name="sname" value="${sessionUtilisateur.name}" /><br />
			 <label for="sname2">Deuxième nom de famille :</label> <input
				type="text" id="sname2" name="sname2" placeholder="Deuxième nom.." /><br />
			<label for="surname">Prénom :</label> <input type="text" id="surname"
				name="surname" placeholder="Prénom.." /><br /> <label
				for="surname2">Deuxième Prénom :</label> <input type="text"
				id="surname2" name="surname2" placeholder="Second prénom.." /><br />				
				<label for="email">e-mail :</label> <input
				type="text" id="email" name="email" value=" ${sessionUtilisateur.email}" /><br />
			<label for="numtel">Numéro de téléphone :</label> <input type="text"
				id="numtel" name="numtelephone" placeholder="Exp : 06 12 34 56 78"
				pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" /><br />
			<label for="profession">Profession :</label> <input type="text"
				id="profession" name="profession" placeholder="Exp : Etudiant" /> <br />
			<label for="profil">Votre profil :</label> <select name="profil"
				id="profil">
				<option value="pro">Professionnel</option>
				<option value="perculiar">Particulier</option>
			</select> <br /> <label for="photo">Uploader votre photo :</label> <input
				type="file" id="photo" name="photo" /> <br /> <br />
			<fieldset>
				<legend>Votre adresse </legend>
				<label for="streetnb">Numéro de voie :</label> <input type="text"
					id="streetnb" name="streetnb" placeholder="Numéro.."><br />
				<label for="streetname">Nom de voie :</label> <input type="text"
					id="streetname" name="streetname"
					placeholder="Exp : Avenue des Fleurs" /> <br /> <label for="city">Ville
					:</label> <input type="text" id="city" name="city" placeholder="Exp : Nice" /><br />
					<label for="city">Pays
					:</label> <input type="text" id="country" name="country" placeholder="Exp : France" /><br />
				<label for="zipcode">Code postal :</label> <input type="number"
					id="zipcode" name="zipcode" placeholder="Exp : 06000" />
			</fieldset>

			<input type="hidden" name="action" value="completeProfile" /><br />
			<input type="submit" value="Entregistrer" />
			<h1>
             ${sessionUtilisateur.email}
              </h1>
		</fieldset>
	</form>

</body>
</html>