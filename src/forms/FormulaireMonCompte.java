package forms;

import java.awt.Image;
import java.sql.Timestamp;

public class FormulaireMonCompte {
	
	private String Name = "";
	private String secondName = "";
	private String Surname = "";
	private String secondSurname = "";
	private String Profession = "";
	private Timestamp dateInscription = null;
	private String Id = "";
	private String Password = "";
	private String email = "";
	private String phoneNumber = "";
	private String telNumber = "";
	private String facebookId = "";
	private String twitterId = "";
	private String instagramId = "";
	private String linkedinId = "";
	private Image accountPicture = null;
	private Integer streetNumber = 0;
	private String streetName = "";
	private String cityName = "";
	private String countryName = "";
	private String postalCode = "";
	

	private void validationEmail(String email) throws Exception {
		/**
		 * Valide l'adresse email saisie.
		 */
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}
	}

	private void validationMotDePasse(String motDePasse) throws Exception {
		/**
		 * Valide le mot de passe saisi.
		 */
		if (motDePasse != null) {
			if (motDePasse.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractÃ¨res.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}
	
	private void validationTel(String numeroTel) throws Exception {
		/**
		 * Valide le numéro de telephone saisi
		 */
		if (numeroTel.length() < 10 || !(numeroTel.startsWith("0"))) {
			throw new Exception("Le numéro de telephone n'est pas valide.");
		}
	}

}
