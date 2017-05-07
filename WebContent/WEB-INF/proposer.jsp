<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proposer un produit/service</title>
</head>
<body>


	<form action="ServletPropose" method="POST">

		<fieldset>
			<legend>Informations</legend>
			<br /> <label for="nom">Nom :</label> <input type="text" id="nom"
				name="nom" placeholder="Exp : pizza" /><br /> <label for="type">Type
				:</label><select name="type" id="type">
				<option value="product">Produit</option>
				<option value="service">Service</option>
			</select> <br /> <label for="prix">Prix</label> <input type="text" id="prix"
				name="prix" placeholder="Exp : 7.00" /> &euro;<br />
			<div id="ajout">
				<label for="products">Se compose de :</label><br /> <br /> <select
					name="produits" id="products" multiple="multiple">
					<option value="tomatoes">Tomates</option>
					<option value="cheese">Fromage</option>
					<option value="oliveoil">Huile d'olive</option>
					<option value="tuna">Thon</option>
					<option value="salt">Sel</option>
					<option value="pepper">Poivre</option>
				</select>
				<p>Appuyer Ctrl pour selectionner plusieurs</p>

			</div>

			Description/Remarques <br />
			<textarea rows="4" cols="40" id="description" name="description"
				placeholder="Décrivez brièvement votre produit. Dites s'il contient des allergènes par exemple"></textarea>
			<br /> <label for="photo">Uploader une photo :</label> <input
				type="file" id="photo" name="photo" /> <br /> <input type="hidden"
				name="action" value="proposerProductService" /> <input
				type="submit" value="Valider">
		</fieldset>

	</form>

</body>
</html>