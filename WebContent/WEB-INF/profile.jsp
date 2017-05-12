<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<title>Compl�ter votre profil</title>
</head>
<body>
	<%@include file="header.jsp"%>

	<div class="row">
		<div class="container">
			<form action="ServletProfile" method="POST" name="myForm">

				<div class="col-md-6">

					<fieldset>
						<legend>Vos informations</legend>
						<div class="form-group">
							<label for="sname">Nom de famille :</label> <input type="text"
								id="sname" class="form-control" name="sname"
								value="${sessionUtilisateur.name}"
								placeholder="Nom de famille..." />
						</div>

						<div class="form-group">
							<label for="sname2">Deuxi�me nom de famille :</label> <input
								type="text" id="sname2" class="form-control" name="sname2"
								value="${sessionUtilisateur.secondName}"
								placeholder="Deuxi�me nom de famille..." " />
						</div>
						<div class="form-group">
							<label for="surname">Pr�nom :</label> <input type="text"
								id="surname" name="surname" class="form-control"
								value="${sessionUtilisateur.surname}" placeholder=" Pr�nom.." />
						</div>

						<div class="form-group">
							<label for="surname2">Deuxi�me Pr�nom :</label> <input
								type="text" id="surname2" class="form-control" name="surname2"
								value="${sessionUtilisateur.secondSurname}"
								placeholder="Second pr�nom.." />
						</div>
					</fieldset>
					<div class="form-group"
						ng-class="{'has-error':!myForm.email.$valid && myForm.$pristine, 'has-success':myForm.email.$valid && myForm.$pristine}">
						<label for="email">E-mail :</label> <input type="text" id="email"
							class="form-control" name="email"
							value="${sessionUtilisateur.email}" />
					</div>

					<p class="help-block"
						ng-show="myForm.email.$error.email && !myForm.$pristine">Veuillez
						entrer une adresse mail valide</p>

					<div class="form-group">
						<label for="numtel">Num�ro de t�l�phone :</label> <input
							type="text" id="numtel" class="form-control" name="numtelephone"
							value="${sessionUtilisateur.phoneNumber}"
							placeholder="Exp : 06 12 34 56 78"
							pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" />
					</div>

					<div class="form-group">
						<label for="numfix">Num�ro de t�l�phone fixe :</label> <input
							type="text" id="numfix" name="numfix" class="form-control"
							value="${sessionUtilisateur.telNumber}"
							placeholder="Exp : 06 12 34 56 78"
							pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" />
					</div>

					<div class="form-group">
						<label for="profession">Profession :</label> <input type="text"
							id="profession" name="profession" class="form-control"
							value="${sessionUtilisateur.profession}"
							placeholder="Exp : Etudiant" />
					</div>


					<div class="form-group">
						<label for="profil">Votre profil :</label> <select name="profil"
							class="form-control" value="${sessionUtilisateur.function}"
							id="profil">
							<option value="pro">Professionnel</option>
							<option value="perculiar">Particulier</option>
						</select>
					</div>
					<div class="form-group">
						<label for="photo">Uploader votre photo :</label> <input
							type="file" id="photo" name="photo" />
					</div>
				</div>
				<div class="col-md-6">

					<fieldset>
						<legend>Votre adresse</legend>
						<div class="form-group">
							<label for="streetnb">Num�ro de voie :</label> <input type="text"
								id="streetnb" name="streetnb" class="form-control"
								value="${sessionUtilisateur.streetNumber}"
								placeholder="Num�ro..">
						</div>
						<div class="form-group">
							<label for="streetname">Nom de voie :</label> <input type="text"
								id="streetname" name="streetname" class="form-control"
								value="${sessionUtilisateur.streetName}"
								placeholder=" Exp :Avenue des Fleurs" />
						</div>

						<div class="form-group">
							<label for="city">Ville :</label> <input type="text" id="city"
								class="form-control" name="city"
								value="${sessionUtilisateur.cityName}" placeholder="Exp : Nice" />
						</div>

						<div class="form-group">
							<label for="zipcode">Code postal :</label> <input type="number"
								id="zipcode" name="zipcode" class="form-control"
								class="form-control" value="${sessionUtilisateur.postalCode}"
								placeholder="Exp : 06000" />
						</div>

						<div class="form-group">
							<label for="country">Pays :</label> <input type="text"
								id="country" name="country" class="form-control"
								value="${sessionUtilisateur.countryName}"
								placeholder="Exp : France" />
						</div>

					</fieldset>

					<div class="form-group">
						<label for="fb">Facebook :</label> <input type="text" id="fb"
							class="form-control" value="${sessionUtilisateur.facebookId}"
							name="fbid" />
					</div>
					<div class="form-group">
						<label for="twi">Twitter :</label> <input type="text" id="twi"
							class="form-control" value="${sessionUtilisateur.twitterId}"
							name="twitterid" />
					</div>
					<div class="form-group">
						<label for="insta">Instagram :</label> <input type="text"
							id="insta" class="form-control"
							value="${sessionUtilisateur.instagramId}" name="instaid" />
					</div>
					<div class="form-group">
						<label for="linked">LinkedIn :</label> <input type="text"
							id="linked" class="form-control"
							value="${sessionUtilisateur.linkedinId}" name="linkedid" />
					</div>
					<input type="hidden" name="action" value="completeProfile" /><br />
					<input class="btn btn-primary btn-block" type="submit"
						value="Enregistrer" />
				</div>

			</form>
		</div>
	</div>

	<pre>{{myForm | json}}</pre>
	<%@include file="footer.jsp"%>

</body>
</html>