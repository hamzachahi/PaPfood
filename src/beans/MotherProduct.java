package beans;

import java.sql.Date;
import java.util.ArrayList;

public abstract class MotherProduct {
	protected Long Id;
	protected String Code;
	protected String Name;
	protected String Description;
	protected Double Price;
	protected String mainImage;
	protected ArrayList<String> listImage = new ArrayList<>();
	protected ArrayList<Salable> listSubProduct = new ArrayList<Salable>();
	protected ArrayList<Comment> Comments = new ArrayList<Comment>();
	protected Long idProvider;
	protected Date addDate;
	private String streetNumber;
	private String StreetName;
	private String City;
	private String postalCode;
	private String Departement;
	private String Country;
	private String latLng;

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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public ArrayList<String> getListImage() {
		return listImage;
	}

	public void setListImage(ArrayList<String> listImage) {
		this.listImage = listImage;
	}

	public ArrayList<Salable> getListSubProduct() {
		return listSubProduct;
	}

	public void setListSubProduct(ArrayList<Salable> listSubProduct) {
		this.listSubProduct = listSubProduct;
	}

	public ArrayList<Comment> getComments() {
		return Comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		Comments = comments;
	}

	public Long getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(Long idProvider) {
		this.idProvider = idProvider;
	}

	public Date getAdd_date() {
		return addDate;
	}

	public void setAdd_date(Date add_date) {
		this.addDate = add_date;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetName() {
		return StreetName;
	}

	public void setStreetName(String streetName) {
		StreetName = streetName;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDepartement() {
		return Departement;
	}

	public void setDepartement(String departement) {
		Departement = departement;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getLatLng() {
		return latLng;
	}

	public void setLatLng(String latLng) {
		this.latLng = latLng;
	}

	@Override
	public String toString() {
		return "MotherProduct [Id=" + Id + ", Code=" + Code + ", Name=" + Name + ", Description=" + Description
				+ ", Price=" + Price + ", mainImage=" + mainImage + ", listImage=" + listImage + ", listSubProduct="
				+ listSubProduct + ", Comments=" + Comments + ", idProvider=" + idProvider + ", addDate=" + addDate
				+ ", streetNumber=" + streetNumber + ", StreetName=" + StreetName + ", City=" + City + ", postalCode="
				+ postalCode + ", Departement=" + Departement + ", Country=" + Country + ", latLng=" + latLng + "]";
	}

}
