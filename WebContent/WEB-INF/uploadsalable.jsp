<div class="booking-form">
	<h1>Modifier l'illustration de cet article</h1>
	<div class="avatar">
		<img src="${mainimage}"
			class="assets/img-circle" alt="pas de photo de profil enregistrée">
	</div>
	<div class="col-md-11">
		<div class="row">
			<form action="uploadsalable" method="POST"
				enctype="multipart/form-data">
				<div class="col-md-6">
					<div class="form-group">					
						<label for="photo">Uploader votre photo :</label> <input
							type="file" name="file" /> <span class="erreur">${formfile.erreurs['fichier']}</span>
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