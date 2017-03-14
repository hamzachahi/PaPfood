package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.PersonDao;

public final class FormContact {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_SUJET = "sujet";
	private static final String CHAMP_CONTENU = "contenu";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public String validerFormulaire(HttpServletRequest request) {

		/* Récupération des champs du formulaire */
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String nom = getValeurChamp(request, CHAMP_NOM);
		String sujet = getValeurChamp(request, CHAMP_SUJET);
		String contenu = getValeurChamp(request, CHAMP_CONTENU);

		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}

		try {
			validationNom(nom);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}

		try {
			validationSujet(sujet);
		} catch (Exception e) {
			setErreur(CHAMP_SUJET, e.getMessage());
		}

		try {
			validationContenu(contenu);
		} catch (Exception e) {
			setErreur(CHAMP_CONTENU, e.getMessage());
		}

		if (erreurs.isEmpty()) {
			resultat = "Message envoyé";
			return resultat;

		} else {
			resultat = "Echec de l'envoi du message";
			return resultat;
		}

	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	public String getResultat() {
		return resultat;
	}

	// Valide l'adresse email saisie.

	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom == null) {
			throw new Exception("Merci de saisir un nom !");
		}
	}

	private void validationSujet(String sujet) throws Exception {
		if (sujet == null) {
			throw new Exception("Merci de saisir un sujet !");
		}
	}

	private void validationContenu(String contenu) throws Exception {
		if (contenu == null) {
			throw new Exception("Merci d'ajouter du contenu à votre message !");
		}
	}

}
