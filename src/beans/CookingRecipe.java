package beans;

import java.awt.Image;
import java.util.ArrayList;

public class CookingRecipe extends MotherProduct implements Product  {

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return Code;
	}

	@Override
	public Boolean setCode(String newCode, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return Name;
	}

	@Override
	public Boolean setName(String newName, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPrice() {
		// TODO Auto-generated method stub
		return Price;
	}

	@Override
	public Boolean setPrice(Integer newPrice, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return Description;
	}

	@Override
	public Boolean setDescription(String newDescription, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image getProductMainImage() {
		// TODO Auto-generated method stub
		return mainImage;
	}

	@Override
	public Boolean setProductMainImage(Image newImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Image> getProductListImage() {
		// TODO Auto-generated method stub
		return listImage;
	}

	@Override
	public Boolean setProductListImage(ArrayList<Image> listImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> getListSubProduct() {
		// TODO Auto-generated method stub
		return listSubProduct;
	}

	@Override
	public Boolean setListProduct(ArrayList<Product> listSubProduct, Boolean sqlornot) {
		// TODO Auto-generated method stub
		return null;
	}

}
