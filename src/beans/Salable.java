package beans;

import java.sql.Date;
import java.util.ArrayList;

public interface Salable {
	public Long getId();

	public Boolean setId(Long newId, Boolean sqlornot);

	public String getCode();

	public Boolean setCode(String newCode, Boolean sqlornot);

	public String getName();

	public Boolean setName(String newName, Boolean sqlornot);

	public Double getPrice();

	public Boolean setPrice(Double d, Boolean sqlornot);

	public String getDescription();

	public Boolean setDescription(String newDescription, Boolean sqlornot);

	public String getMainImage();

	public Boolean setMainImage(String newImage, Boolean sqlornot);

	public ArrayList<String> getProductListImage();

	public Boolean setProductListImage(ArrayList<String> listImage, Boolean sqlornot);

	public ArrayList<Salable> getListSubProduct();

	public Boolean setListProduct(ArrayList<Salable> listSubProduct, Boolean sqlornot);

	public Long getIdProvider();

	public void setIdProvider(Long Id);

	public String getType();

	public Date getAddDate();

	public Boolean setAddDate(Date date);
}
