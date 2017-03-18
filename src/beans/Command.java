package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Command {
	private int Id;
	private String Code;
	private String idCustomer;
	private String idCart;
	private String adresseFacturation;
	private String adresseExpedition;
	private String modePaiement;
	private ArrayList<ElementCommand> elements;
	private ArrayList<String> listIdFournisseurs;
	private Date dateCommande;
	private Date dateLivraison;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}

	public String getIdCart() {
		return idCart;
	}

	public void setIdCart(String idCart) {
		this.idCart = idCart;
	}

	public String getAdresseFacturation() {
		return adresseFacturation;
	}

	public void setAdresseFacturation(String adresseFacturation) {
		this.adresseFacturation = adresseFacturation;
	}

	public String getAdresseExpedition() {
		return adresseExpedition;
	}

	public void setAdresseExpedition(String adresseExpedition) {
		this.adresseExpedition = adresseExpedition;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public ArrayList<ElementCommand> getElements() {
		return elements;
	}

	public void setElements(ArrayList<ElementCommand> elements) {
		this.elements = elements;
	}

	public ArrayList<String> getListIdFournisseurs() {
		return listIdFournisseurs;
	}

	public void setListIdFournisseurs(ArrayList<String> listIdFournisseurs) {
		this.listIdFournisseurs = listIdFournisseurs;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Date getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

}
