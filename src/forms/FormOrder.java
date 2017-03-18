package forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import beans.Command;

public class FormOrder {

	private static final String CHAMP_ADR_FAC = "billingaddress";
	private static final String CHAMP_ADR_EXPED = "shippingaddress";
	private static final String CHAMP_MODE_PAY = "paymentmode";

	private Map<String, String> erreurs = new HashMap<String, String>();
	private String resultat;

	public Command order(HttpServletRequest request) {

		/* Récupération des champs du formulaire */
		String adresseFacturation = getValeurChamp(request, CHAMP_ADR_FAC);
		String adresseExped = getValeurChamp(request, CHAMP_ADR_EXPED);
		String modePaiement = getValeurChamp(request, CHAMP_MODE_PAY);

		Command commande = new Command();

		try {
			validationAdrExp(adresseExped);
		} catch (Exception e) {
			erreurs.put(CHAMP_ADR_EXPED, e.getMessage());
		}

		try {
			validationAdrFac(adresseFacturation);
		} catch (Exception e) {
			erreurs.put(CHAMP_ADR_FAC, e.getMessage());
		}

		commande.setAdresseExpedition(adresseExped);
		commande.setAdresseFacturation(adresseFacturation);
		commande.setModePaiement(modePaiement);

		return commande;

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

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	private void validationAdrFac(String adresseFac) throws Exception {
		if (adresseFac == null) {
			throw new Exception("Merci de saisir une adresse !");
		}
	}

	private void validationAdrExp(String adresseExp) throws Exception {
		if (adresseExp == null) {
			throw new Exception("Merci de saisir une adresse !");
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

}
