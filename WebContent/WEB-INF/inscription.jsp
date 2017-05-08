<div class="breadcrumbs">
	<div class="container">
		<ol class="breadcrumb breadcrumb1 animated wow slideInLeft animated"
			data-wow-delay=".5s"
			style="visibility: visible; animation-delay: 0.5s; animation-name: slideInLeft;">
			<li><a href="index.html"><span
					class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
			<li class="active">Inscription</li>
		</ol>
	</div>
</div>
<div class="container">
	<div class="register">
		<h2>Inscription</h2>
		<form method="post" action="inscription">
			<fieldset>
				<legend>Inscription</legend>
				<p>Vous pouvez vous inscrire via ce formulaire.</p>

				<label for="email">Adresse email <span class="requis">*</span></label>
				<input type="email" id="email" name="email"
					placeholder="votre adresse email"/>
					 <span class="erreur">${form.erreurs['email']}</span>
				<br /> <label for="motdepasse">Mot de passe <span
					class="requis">*</span></label> <input type="password" id="motdepasse"
					name="motdepasse" value="" size="20" maxlength="20" /> <span
					class="erreur">${form.erreurs['motdepasse']}</span> <br /> <label
					for="confirmation">Confirmation du mot de passe <span
					class="requis">*</span></label> <input type="password" id="confirmation"
					name="confirmation" value="" size="20" maxlength="20" /> <span
					class="erreur">${form.erreurs['confirmation']}</span> <br /> <label
					for="nom">Nom de famille</label> <input type="text" id="nom"
					name="nom" placeholder="Votre nom de famille"/><span class="erreur">${form.erreurs['nom']}</span>
				<br /> <input type="submit" value="Inscription" class="sansLabel" />
				<br />

				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
			</fieldset>
		</form>
	</div>
					   	<%@ include file="/WEB-INF/map.jsp" %>
	
</div>
</div>
