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
	protected ArrayList<String> listImage= new ArrayList<>();
	protected ArrayList<Salable> listSubProduct= new ArrayList<Salable>();
	protected ArrayList<Comment> Comments= new ArrayList<Comment>();
	protected Long idProvider;
	protected Date addDate;

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

	@Override
	public String toString() {
		return "MotherProduct [Id=" + Id + ", Code=" + Code + ", Name=" + Name + ", Description=" + Description
				+ ", Price=" + Price + ", mainImage=" + mainImage + ", listImage=" + listImage + ", listSubProduct="
				+ listSubProduct + ", Comments=" + Comments + ", idProvider=" + idProvider + ", add_date=" + addDate
				+ "]";
	}

	

}
