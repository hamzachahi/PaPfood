package beans;

import java.util.ArrayList;
import java.util.Date;

public class Invoice {
	private Long Id;
	private String codeInvoice;
	private ArrayList<Salable> listProduct;
	private Date creationDate;
	private Date deliveredDate;
	private java.sql.Date creationSqlDate;
	private java.sql.Date deliveredSqlDate;
	private String Type;
	private Double totalPrice;
	private String PersonName;
	private String societyName;
	private String phoneNumber;
	private String webSite;
	private String headerMessage;
	private String footerMessage;
	private String legalMessage;
	private String personAddress;
	private String destinatorAddress;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCodeInvoice() {
		return codeInvoice;
	}

	public void setCodeInvoice(String codeInvoice, Boolean sqlornot) {
		this.codeInvoice = codeInvoice;
	}

	public ArrayList<Salable> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ArrayList<Salable> listProduct, Boolean sqlornot) {
		this.listProduct = listProduct;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public java.sql.Date getCreationSqlDate() {
		return creationSqlDate;
	}

	public void setCreationSqlDate(java.sql.Date creationSqlDate, Boolean sqlornot) {
		this.creationSqlDate = creationSqlDate;
	}

	public java.sql.Date getDeliveredSqlDate() {
		return deliveredSqlDate;
	}

	public void setDeliveredSqlDate(java.sql.Date deliveredSqlDate, Boolean sqlornot) {
		this.deliveredSqlDate = deliveredSqlDate;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type, Boolean sqlornot) {
		Type = type;
	}

	public Double getTotalPrice(Boolean sqlornot) {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice, Boolean sqlornot) {
		this.totalPrice = totalPrice;
	}

	public String getPersonName() {
		return PersonName;
	}

	public void setPersonName(String personName, Boolean sqlornot) {
		PersonName = personName;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName, Boolean sqlornot) {
		this.societyName = societyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber, Boolean sqlornot) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite, Boolean sqlornot) {
		this.webSite = webSite;
	}

	public String getHeaderMessage() {
		return headerMessage;
	}

	public void setHeaderMessage(String headerMessage, Boolean sqlornot) {
		this.headerMessage = headerMessage;
	}

	public String getFooterMessage() {
		return footerMessage;
	}

	public void setFooterMessage(String footerMessage, Boolean sqlornot) {
		this.footerMessage = footerMessage;
	}

	public String getLegalMessage() {
		return legalMessage;
	}

	public void setLegalMessage(String legalMessage, Boolean sqlornot) {
		this.legalMessage = legalMessage;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress, Boolean sqlornot) {
		this.personAddress = personAddress;
	}

	public String getDestinatorAddress() {
		return destinatorAddress;
	}

	public void setDestinatorAddress(String destinatorAddress, Boolean sqlornot) {
		this.destinatorAddress = destinatorAddress;
	}

}
