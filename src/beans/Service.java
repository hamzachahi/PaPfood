package beans;

import java.awt.Image;
import java.util.ArrayList;

public class Service extends MotherProduct implements Salable {
	protected String type = "Service";

	@Override
	public Boolean setId(Long newId, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setId(newId);
		return true;
	}

	@Override
	public Boolean setCode(String newCode, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setCode(newCode);
		return null;
	}

	@Override
	public Boolean setName(String newName, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setName(newName);
		return null;
	}

	@Override
	public Boolean setPrice(Double d, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setPrice(d);
		return null;
	}

	@Override
	public Boolean setDescription(String newDescription, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setDescription(newDescription);
		return null;
	}

	@Override
	public Boolean setMainImage(Image newImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setMainImage(newImage);
		return null;
	}

	@Override
	public ArrayList<Image> getProductListImage() {
		// TODO Auto-generated method stub
		return this.getProductListImage();
	}

	@Override
	public Boolean setProductListImage(ArrayList<Image> listImage, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setProductListImage(listImage, false);
		return null;
	}

	@Override
	public Boolean setListProduct(ArrayList<Salable> listSubProduct, Boolean sqlornot) {
		// TODO Auto-generated method stub
		this.setListProduct(listSubProduct, false);
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
