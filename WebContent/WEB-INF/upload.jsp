			<div class="booking-form">
						<h1>Modifier votre photo de profil</h1>
						<div class="col-md-11">
							<div class="row">
								<form action="ServletUpload" method="POST"
									enctype="multipart/form-data">
									<div class="col-md-6">
										<div class="form-group">
											<label for="photo">Uploader votre photo :</label> <input
												type="file" id="fichier" name="fichier"
												value="<c:out value="${courtnom}"/>" /> <span
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