package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.Person;
import dao.PersonDao;

public final class FormulaireConnexion {
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS = "motdepasse";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	PersonDao utilisateurDao;
	private static final String ALGO_CHIFFREMENT = "SHA-256";

	public FormulaireConnexion(PersonDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

	public String getResultat() {
		return resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public Person connecterUtilisateur(HttpServletRequest request) throws Exception {
		/* Récupération des champs du formulaire */
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String motDePasse = getValeurChamp(request, CHAMP_PASS);

		Person utilisateur = new Person();
		Person passage = new Person();
		/* Validation du champ email. */
		try {
			validationEmail(email);
		} catch (Exception e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email, false);
		passage.setEmail(email, false);
		/* Validation du champ mot de passe. */
		try {
			validationMotDePasse(motDePasse);
		} catch (Exception e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
		utilisateur.setPassword(motDePasse, false);
		passage.setPassword(motDePasse, false);

		/* Initialisation du résultat global de la validation. */
		if (erreurs.isEmpty()) {
			utilisateur = utilisateurDao.trouver(email, false);
			ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
			passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
			passwordEncryptor.setPlainDigest(false);
			boolean test = passwordEncryptor.checkPassword(motDePasse, utilisateur.getPassword());
			if (test == true) {
				System.out.println("mot de passe correspondant");
				System.out.println("personne connectée : " + utilisateur.toString());
				resultat = "Succès de la connexion.";
				System.out.println(resultat);
			} else {
				utilisateur=passage;
				resultat = "Échec de la connexion. Vérifiez votre mot de passe!";
				System.out.println("Mot de passe incorrect");
				throw new Exception("mot de passe incorrect");

			}

		} else {
			utilisateur=passage;
			resultat = "Échec de la connexion.";
		}

		return utilisateur;
	}

	/**
	 * Valide l'adresse email saisie.
	 */
	private void validationEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	/**
	 * Valide le mot de passe saisi.
	 */
	private void validationMotDePasse(String motDePasse) throws Exception {
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
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
}
