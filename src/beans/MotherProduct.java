package beans;

import java.awt.Image;
import java.util.ArrayList;

public class MotherProduct {
	protected Long Id;
	protected String Code;
	protected String Name;
	protected String Description;
	protected Double Price;
	protected Image mainImage;
	protected ArrayList<Image> listImage;
	protected ArrayList<Product> listSubProduct;

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

	public Image getMainImage() {
		return mainImage;
	}

	public void setMainImage(Image mainImage) {
		this.mainImage = mainImage;
	}

	public ArrayList<Image> getListImage() {
		return listImage;
	}

	public void setListImage(ArrayList<Image> listImage) {
		this.listImage = listImage;
	}

	public ArrayList<Product> getListSubProduct() {
		return listSubProduct;
	}

	public void setListSubProduct(ArrayList<Product> listSubProduct) {
		this.listSubProduct = listSubProduct;
	}

}
