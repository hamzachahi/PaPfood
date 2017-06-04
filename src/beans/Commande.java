package beans;

import java.sql.Date;
import java.util.ArrayList;

public class Commande {
	private Long Id;
	private String Code;
	private Person Customer;
	private String idCart;
	private String adresseFacturation;
	private String adresseExpedition;
	private Double Price;
	private ArrayList<ElementCommand> elements;
	private ArrayList<ElementCommand> toAddElements;
	private ArrayList<ElementCommand> toDeleteElements;
	private Date dateCommande;
	private Date dateLivraison;
	private Integer State;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Person getCustomer() {
		return Customer;
	}

	public void setCustomer(Person Customer) {
		this.Customer = Customer;
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

	public Double getPrice() {
		Price = 0.0;
		for (int i = 0; i < elements.size(); i++) {
			Price = Price + (elements.get(i).getmProduct().getPrice() * elements.get(i).getQuantity());
		}
		return Price;
	}

	public void setPrice() {
		Price = 0.0;
		for (int i = 0; i < elements.size(); i++) {
			Price = Price + (elements.get(i).getmProduct().getPrice() * elements.get(i).getQuantity());
		}
	}

	public ArrayList<ElementCommand> getElements() {
		return elements;
	}

	public void setElements(ArrayList<ElementCommand> elements) {
		this.elements = elements;
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

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public ArrayList<ElementCommand> getToAddElements() {
		return toAddElements;
	}

	public void setToAddElements(ArrayList<ElementCommand> toAddElements) {
		this.toAddElements = toAddElements;
	}

	public ArrayList<ElementCommand> getToDeleteElements() {
		return toDeleteElements;
	}

	public void setToDeleteElements(ArrayList<ElementCommand> toDeleteElements) {
		this.toDeleteElements = toDeleteElements;
	}

	public void setPrice(Double price) {
		Price = price;
	}

}
