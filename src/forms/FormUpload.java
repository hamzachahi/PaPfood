package forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.Fichier;

public final class FormUpload {
	private static final String CHAMP_DESCRIPTION = "description";
	private static final String CHAMP_FICHIER = "fichier";
	private static final int TAILLE_TAMPON = 10240; // 10 ko
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private Boolean descriptionOrNot;

	public FormUpload(Boolean descriptionOrNot) {
		this.descriptionOrNot = descriptionOrNot;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Fichier enregistrerFichier(HttpServletRequest request, String chemin) {
		Fichier fichier = new Fichier();
		String description = "";
		if (descriptionOrNot == true) {
			description = getValeurChamp(request, CHAMP_DESCRIPTION);
		}

		String nomFichier = null;
		InputStream contenuFichier = null;
		try {
			Part part = request.getPart(CHAMP_FICHIER);

			nomFichier = getNomFichier(part);

			if (nomFichier != null && !nomFichier.isEmpty()) {

				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
						.substring(nomFichier.lastIndexOf('\\') + 1);
				fichier.setCourtNom(nomFichier);

				contenuFichier = part.getInputStream();

			}
		} catch (IllegalStateException e) {

			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Les données envoyées sont trop volumineuses.");
		} catch (IOException e) {

			e.printStackTrace();
			setErreur(CHAMP_FICHIER, "Erreur de configuration du serveur.");
		} catch (ServletException e) {

			e.printStackTrace();
			setErreur(CHAMP_FICHIER,
					"Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
		}

		if (erreurs.isEmpty()) {
			if (descriptionOrNot == true) {
				try {
					validationDescription(description);
				} catch (Exception e) {
					setErreur(CHAMP_DESCRIPTION, e.getMessage());
				}
				fichier.setDescription(description);
			}
			try {
				validationFichier(nomFichier, contenuFichier);
			} catch (Exception e) {
				setErreur(CHAMP_FICHIER, e.getMessage());
			}
			fichier.setNom(nomFichier);
		}

		if (erreurs.isEmpty()) {
			try {
				fichier.setNom(ecrireFichier(contenuFichier, nomFichier, chemin));
			} catch (Exception e) {
				setErreur(CHAMP_FICHIER, "Erreur lors de l'écriture du fichier sur le disque.");
			}
		}

		if (erreurs.isEmpty()) {
			resultat = "Succès de l'envoi du fichier.";
		} else {
			resultat = "Échec de l'envoi du fichier.";
		}

		return fichier;
	}

	private void validationDescription(String description) throws Exception {
		if (description != null) {
			if (description.length() < 15) {
				throw new Exception("La phrase de description du fichier doit contenir au moins 15 caractères.");
			}
		} else {
			throw new Exception("Merci d'entrer une phrase de description du fichier.");
		}
	}

	private void validationFichier(String nomFichier, InputStream contenuFichier) throws Exception {
		if (nomFichier == null || contenuFichier == null) {
			throw new Exception("Merci de sélectionner un fichier à envoyer.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	private static String getNomFichier(Part part) {

		System.out.println("Nom : " + part.getName());
		for (String contentDisposition : part.getHeader("Content-Disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {

				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	private String ecrireFichier(InputStream contenu, String nomFichier, String chemin) throws Exception {
		Date aujourdhui = new Date();
		DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		String cheminComplet = chemin + nomFichier + mediumDateFormat.format(aujourdhui);
		System.out.println("chemin complet : " + cheminComplet);
		System.out.println("Je commence à écrire le fichier");
		try {
			entree = new BufferedInputStream(contenu, TAILLE_TAMPON);
			System.out.println("Je crée le buffer entrée");
			File file = new File(chemin.replace("s/", "s"));
			if (!file.exists()) {
				System.out.println("Le chemin spécifié n'existe pas!");
				file.mkdirs();
			}
			sortie = new BufferedOutputStream(new FileOutputStream(new File(cheminComplet)), TAILLE_TAMPON);

			System.out.println("Buffereds créés");

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur = 0;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
				System.out.println("sortie fermée");

			} catch (IOException ignore) {
			}
			try {
				entree.close();
				System.out.println("entrée fermée");
			} catch (IOException ignore) {
			}
		}
		return cheminComplet;
	}
}