<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app>
<head>
<%@include file="liens.jsp"%>
<title>Compléter votre profil</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>
	<!-- Menu title -->
	<section id="page-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">${sessionUtilisateur.surname}
						${sessionUtilisateur.name}</h2>
					<h3 class="secondery-title">Modifier mes informations</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="booking-form">
						<h1>Complétez vos informations</h1>
						<div class="col-md-11">
							<div class="row">
								<form action="ServletProfile" method="POST" name="myForm">
									<div class="col-md-6">
										<fieldset>
											<legend>Vos informations</legend>
											<div class="form-group">
												<label for="sname">Nom de famille :</label> <input
													type="text" id="sname" class="form-control" name="sname"
													value="${sessionUtilisateur.name}"
													placeholder="Nom de famille..." />
											</div>
											<div class="form-group">
												<label for="sname2">Deuxième nom de famille :</label> <input
													type="text" id="sname2" class="form-control" name="sname2"
													value="${sessionUtilisateur.secondName}"
													placeholder="Deuxième nom de famille..." " />
											</div>
											<div class="form-group">
												<label for="surname">Prénom :</label> <input type="text"
													id="surname" name="surname" class="form-control"
													value="${sessionUtilisateur.surname}"
													placeholder=" Prénom.." />
											</div>

											<div class="form-group">
												<label for="surname2">Deuxième Prénom :</label> <input
													type="text" id="surname2" class="form-control"
													name="surname2" value="${sessionUtilisateur.secondSurname}"
													placeholder="Second prénom.." />
											</div>
										</fieldset>
										<div class="form-group">
											<label for="email">E-mail :</label> <input type="email"
												id="email" class="form-control" name="email"
												value="${sessionUtilisateur.email}" />
											<p class="help-block">Veuillez entrer une adresse mail
												valide</p>
										</div>



										<div class="form-group"
											ng-class="{'has-error':myForm.numtelephone.$invalid && !myForm.numtelephone.$pristine,
											'has-success':myForm.numtelephone.$valid && myForm.numtelephone.$dirty}">
											<label for="numtel">Numéro de téléphone :</label> <input
												type="text" id="numtel" class="form-control"
												name="numtelephone" ng-model="phone" ng-minlength="10"
												ng-maxlength="10" value="${sessionUtilisateur.phoneNumber}"
												placeholder="Exp : 06 12 34 56 78"
												pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" />
											<p class="help-block" ng-show="myForm.numtelephone.$invalid">Veuillez
												taper un N° de 10 chiffres</p>
										</div>


										<div class="form-group"
											ng-class="{'has-error':myForm.numfix.$invalid && !myForm.numfix.$pristine,
											'has-success':myForm.numfix.$valid && myForm.numfix.$dirty}">
											<label for="numfix">Numéro de téléphone fixe :</label> <input
												type="text" id="numfix" name="numfix" class="form-control"
												ng-model="numfix" ng-minlength="10" ng-maxlength="10"
												value="${sessionUtilisateur.telNumber}"
												placeholder="Exp : 06 12 34 56 78"
												pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" />
											<p class="help-block" ng-show="myForm.numfix.$invalid">Veuillez
												taper un N° de 10 chiffres</p>
										</div>

										<div class="form-group">
											<label for="profession">Profession :</label> <input
												type="text" id="profession" name="profession"
												class="form-control"
												value="${sessionUtilisateur.profession}"
												placeholder="Exp : Etudiant" />
										</div>


										<div class="form-group">
											<label for="profil">Votre profil :</label> <select
												name="profil" class="form-control"
												value="${sessionUtilisateur.function}" id="profil">
												<option value="pro">Professionnel</option>
												<option value="perculiar">Particulier</option>
											</select>
										</div>
									</div>
									<div class="col-md-6">

										<fieldset>
											<legend>Votre adresse</legend>
											<div class="form-group">
												<label for="streetnb">Numéro de voie :</label> <input
													type="text" id="streetnb" name="streetnb"
													class="form-control"
													value="${sessionUtilisateur.streetNumber}"
													placeholder="Numéro..">
											</div>
											<div class="form-group">
												<label for="streetname">Nom de voie :</label> <input
													type="text" id="streetname" name="streetname"
													class="form-control"
													value="${sessionUtilisateur.streetName}"
													placeholder=" Exp :Avenue des Fleurs" />
											</div>

											<div class="form-group">
												<label for="city">Ville :</label> <input type="text"
													id="city" class="form-control" name="city"
													value="${sessionUtilisateur.cityName}"
													placeholder="Exp : Nice" />
											</div>

											<div class="form-group">
												<label for="zipcode">Code postal :</label> <input
													type="number" id="zipcode" name="zipcode"
													class="form-control" class="form-control"
													value="${sessionUtilisateur.postalCode}"
													placeholder="Exp : 06000" />
											</div>

											<div class="form-group">
												<label for="country">Pays :</label> <input type="text"
													id="country" name="country" class="form-control"
													value="${sessionUtilisateur.countryName}"
													placeholder="Exp : France" />
											</div>
										</fieldset>

										<fieldset>
											<legend>Réseaux sociaux</legend>
											<div class="form-group">
												<label for="fb">Facebook :</label> <input type="text"
													id="fb" class="form-control"
													value="${sessionUtilisateur.facebookId}" name="fbid" />
											</div>
											<div class="form-group">
												<label for="twi">Twitter :</label> <input type="text"
													id="twi" class="form-control"
													value="${sessionUtilisateur.twitterId}" name="twitterid" />
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
										</fieldset>
										<input type="hidden" name="action" value="completeProfile" /><br />
										<input class="btn btn-style-4 btn-block" " type="submit"
											value="Enregistrer" />
									</div>

								</form>
							</div>
						</div>
					</div>
					<div class="booking-form">
						<h1>Modifier votre photo de profil</h1>
						<div class="col-md-11">
							<div class="row">
								<form action="ServletProfile" method="POST"
									enctype="multipart/form-data">
									<div class="col-md-6">
										<div class="form-group">
											<label for="photo">Uploader votre photo :</label> <input
												type="file" id="fichier" name="fichier"
												value="<c:out value="${fichier.courtnom}"/>" /> <span
												class="erreur">${formfile.erreurs['fichier']}</span>
											<p class="${empty formfile.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
										</div>
									</div>
									<div class="col-md-6">
										<input class="btn btn-style-4 btn-block" type="submit"
											value="Enregistrer" />
									</div>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 	<pre>{{myForm | json}}</pre> --> <%@include file="footer.jsp"%>
</body>
</html>