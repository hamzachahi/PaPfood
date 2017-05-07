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
			<br /> <label for="sname">Nom de famille 2 :</label> <input
				type="text" id="sname" name="sname" placeholder="Deuxième nom.." /><br />
			<label for="surname">Prénom :</label> <input type="text" id="surname"
				name="surname" placeholder="Prénom.." /><br /> <label
				for="surname2">Deuxième Prénom :</label> <input type="text"
				id="surname2" name="surname2" placeholder="Second prénom.." /><br />
			<label for="numtel">Numéro de téléphone :</label> <input type="text"
				id="numtel" name="numtelephone" placeholder="Exp : 06 12 34 56 78" /><br />
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
					id="streetname" name="streetname" placeholder="Exp : Avenue des Fleurs" /> <br />
				<label for="city">Ville :</label> <input type="text" id="city"
					name="city" placeholder="Exp : Nice" /><br /> <label for="zipcode">Code
					postal :</label> <input type="text" id="zipcode" name="zipcode"
					placeholder="Exp : 06000" />
			</fieldset>

			<input type="hidden" name="action" value="completeProfile" /><br />
			<input type="submit" value="Valider" />

		</fieldset>
	</form>

</body>
</html>