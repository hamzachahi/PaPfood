package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Command {
	private int Id;
	private String Code;
	private String idCustomer;
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
