package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Person;
import dao.PersonDao;

public class FormulaireMonCompte {

	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_NOM = "sname";
	private static final String CHAMP_NOM_2 = "sname2";
	private static final String CHAMP_PRENOM = "surname";
	private static final String CHAMP_PRENOM_2 = "surname2";
	private static final String CHAMP_TEL = "numtelephone";
	private static final String CHAMP_TEL_2 = "numfix";
	private static final String CHAMP_PROF = "profession";
	private static final String CHAMP_PROFIL = "profil";
	private static final String CHAMP_STREET_NB = "streetnb";
	private static final String CHAMP_STREET_NAME = "streetname";
	private static final String CHAMP_CITY = "city";
	private static final String CHAMP_ZIP = "zipcode";
	private static final String CHAMP_COUNTRY = "country";
	private static final String CHAMP_FB = "fbid";
	private static final String CHAMP_TWITTER = "twitterid";
	private static final String CHAMP_INSTA = "instaid";
	private static final String CHAMP_LINKED = "linkedid";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private PersonDao utilisateurDao;

	public Person updateUserData(HttpServletRequest request) {

		// on récupère toutes les données du formulaire à l'aide de la requête
		String nom = getValeurChamp(request, CHAMP_NOM);
		String nom2 = getValeurChamp(request, CHAMP_NOM_2);
		String prenom1 = getValeurChamp(request, CHAMP_PRENOM);
		String prenom2 = getValeurChamp(request, CHAMP_PRENOM_2);
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String tel1 = getValeurChamp(request, CHAMP_TEL);
		String tel2 = getValeurChamp(request, CHAMP_TEL_2);
		String profess = getValeurChamp(request, CHAMP_PROF);
		String profil = getValeurChamp(request, CHAMP_PROFIL);
		String numvoie = getValeurChamp(request, CHAMP_STREET_NB);
		String nomvoie = getValeurChamp(request, CHAMP_STREET_NAME);
		String ville = getValeurChamp(request, CHAMP_CITY);
		String codepostal = getValeurChamp(request, CHAMP_ZIP);
		String pays = getValeurChamp(request, CHAMP_COUNTRY);
		String fb = getValeurChamp(request, CHAMP_FB);
		String twitter = getValeurChamp(request, CHAMP_TWITTER);
		String insta = getValeurChamp(request, CHAMP_INSTA);
		String linked = getValeurChamp(request, CHAMP_LINKED);

		Person utilisateur = new Person();
		HttpSession session = request.getSession();

		// on récupère l'utilisateur de la session
		utilisateur = (Person) session.getAttribute("sessionUtilisateur");

		try {

			// on met à jour ses infos

			traiterNom(nom, utilisateur);
			traiterNom2(nom2, utilisateur);
			traiterEmail(email, utilisateur);
			traiterTelephone1(tel1, utilisateur);
			traiterTelephone2(tel2, utilisateur);
			utilisateur.setSurname(prenom1, false);
			utilisateur.setSecondSurname(prenom2, false);
			utilisateur.setProfession(profess, false);
			utilisateur.setFunction(profil);
			utilisateur.setStreetNumber(numvoie, false);
			utilisateur.setStreetName(nomvoie, false);
			utilisateur.setCityName(ville, false);
			utilisateur.setPostalCode(codepostal, false);
			utilisateur.setCountryName(pays, false);
			utilisateur.setFacebookId(fb, false);
			utilisateur.setTwitterId(twitter, false);
			utilisateur.setInstagramId(insta, false);
			utilisateur.setLinkedinId(linked, false);

			if (erreurs.isEmpty()) {
				System.out.println("OK");
				utilisateurDao.modifyPersonalInformation(utilisateur);
				resultat = "Données bien mises à jour";

			} else {
				resultat = "Données non mises à jour!";
			}

		} catch (Exception e) {
			resultat = "Échec de mise à jour de votre profil : une erreur imprévue est survenue, merci de réessayer.";
			e.printStackTrace();
		}

		return utilisateur;
	}

	private void traiterTelephone1(String telephone, Person utilisateur) {
		try {
			validationTel(telephone);
		} catch (Exception e) {
			setErreur(CHAMP_TEL, e.getMessage());
		}
		utilisateur.setPhoneNumber(telephone, false);
	}

	private void traiterTelephone2(String telephone, Person utilisateur) {
		try {
			validationTel(telephone);
		} catch (Exception e) {
			setErreur(CHAMP_TEL_2, e.getMessage());
		}
		utilisateur.setTelNumber(telephone, false);
	}

	private void traiterEmail(String email, Person utilisateur) throws Exception {
		try {
			validationEmail(email);
		} catch (ValidExceptionForm e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		utilisateur.setEmail(email, false);
	}

	private void traiterNom2(String nomFamille, Person utilisateur) {

		try {
			validationNom(nomFamille);
		} catch (Exception e) {
			setErreur(CHAMP_NOM_2, e.getMessage());
		}
		utilisateur.setSecondName(nomFamille, false);

	}

	private void traiterNom(String nomFamille, Person utilisateur) {

		try {
			validationNom(nomFamille);
		} catch (Exception e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		utilisateur.setName(nomFamille, false);

	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	/* Validation du nom */
	private void validationNom(String nom) throws ValidExceptionForm {
		if (nom != null && nom.length() < 2) {
			throw new ValidExceptionForm("Veuillez taper au moins 2 caractères.");
		}
	}

	// validation du mail en back-end
	@SuppressWarnings("unused")
	private void validationEmail(String email) throws Exception {
		/**
		 * Valide l'adresse email saisie.
		 */
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	/* Validation du nom */
	private void validationNomFamille(String nomFamille) throws ValidExceptionForm {
		if (nomFamille != null && nomFamille.length() < 3) {
			throw new ValidExceptionForm("Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	@SuppressWarnings("unused")
	private void validationTel(String numeroTel) throws Exception {
		/**
		 * Valide le num�ro de telephone saisi
		 */
		if (numeroTel.length() < 10 || !(numeroTel.startsWith("0"))) {
			throw new Exception("Le numéro de telephone n'est pas valide.");
		}
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

}