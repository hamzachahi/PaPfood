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
			<br /> <label for="sname">Nom de famille :</label> <input
				type="text" id="sname" name="sname"
				value="${sessionUtilisateur.name}" placeholder="Nom de famille..." /><br />
			<label for="sname2">Deuxième nom de famille :</label> <input
				type="text" id="sname2" name="sname2"
				value="${sessionUtilisateur.secondName}"
				placeholder="Deuxième nom de famille..." " /><br /> <label
				for="surname">Prénom :</label> <input type="text" id="surname"
				name="surname" value="${sessionUtilisateur.surname}"
				placeholder=" Prénom.." /><br /> <label for="surname2">Deuxième
				Prénom :</label> <input type="text" id="surname2" name="surname2"
				value="${sessionUtilisateur.secondSurname}"
				placeholder="Second prénom.." /><br /> <label for="email">e-mail
				:</label> <input type="text" id="email" name="email"
				value="${sessionUtilisateur.email}" /><br /> <label for="numtel">Numéro
				de téléphone :</label> <input type="text" id="numtel" name="numtelephone"
				value="${sessionUtilisateur.phoneNumber}"
				placeholder="Exp : 06 12 34 56 78"
				pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" /><br />
			<label for="numfix">Numéro de téléphone fixe :</label> <input
				type="text" id="numfix" name="numfix"
				value="${sessionUtilisateur.telNumber}"
				placeholder="Exp : 06 12 34 56 78"
				pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" /><br />
			<label for="profession">Profession :</label> <input type="text"
				id="profession" name="profession"
				value="${sessionUtilisateur.profession}"
				placeholder="Exp : Etudiant" /> <br /> <label for="profil">Votre
				profil :</label> <select name="profil"
				value="${sessionUtilisateur.function}" id="profil">
				<option value="pro">Professionnel</option>
				<option value="perculiar">Particulier</option>
			</select> <br /> <label for="photo">Uploader votre photo :</label> <input
				type="file" id="photo" name="photo" /> <br /> <br />
			<fieldset>
				<legend>Votre adresse </legend>
				<label for="streetnb">Numéro de voie :</label> <input type="text"
					id="streetnb" name="streetnb"
					value="${sessionUtilisateur.streetNumber}" placeholder="Numéro.."><br />
				<label for="streetname">Nom de voie :</label> <input type="text"
					id="streetname" name="streetname"
					value="${sessionUtilisateur.streetName}"
					placeholder=" Exp :
					Avenue des Fleurs" /> <br /> <label
					for="city">Ville :</label> <input type="text" id="city" name="city"
					value="${sessionUtilisateur.cityName}" placeholder="Exp : Nice" /><br />
				<label for="city">Pays :</label> <input type="text" id="country"
					name="country" value="${sessionUtilisateur.countryName}"
					placeholder="Exp : France" /><br /> <label for="zipcode">Code
					postal :</label> <input type="number" id="zipcode" name="zipcode"
					value="${sessionUtilisateur.postalCode}" placeholder="Exp : 06000" />
			</fieldset>
			<br /> <label for="fb">Facebook :</label> <input type="text" id="fb"
				value="${sessionUtilisateur.facebookId}" name="fbid" /> <br /> <label
				for="twi">Twitter :</label> <input type="text" id="twi"
				value="${sessionUtilisateur.twitterId}" name="twitterid" /> <br />
			<label for="insta">Instagram :</label> <input type="text" id="insta"
				value="${sessionUtilisateur.instagramId}" name="instaid" /> <br />
			<label for="linked">LinkedIn :</label> <input type="text" id="linked"
				value="${sessionUtilisateur.linkedinId}" name="linkedid" /> <br />

			<input type="hidden" name="action" value="completeProfile" /><br />
			<input type="submit" value="Entregistrer" />
			<h1></h1>
		</fieldset>
	</form>

</body>
</html>