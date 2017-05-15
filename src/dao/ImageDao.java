package dao;

import java.util.ArrayList;

public interface ImageDao {
	public Boolean imageProductLinkRegister(String link, Long Id);

	public Boolean imageServiceLinkRegister(String link, Long Id);

	public Boolean imagePersonLinkRegister(String link, Long Id, String Utility);

	public Boolean modifiyProductImage(String ImageLien, Long Id);

	public Boolean deleteProductImage(Long idProduct);

	public Boolean modifiyServiceImage(String ImageLien, Long idProdcut);

	public Boolean deleteServiceImage(String ImageLien, Long idProduct);

	public Boolean modifiyPersonImage(String ImageLien, Long Id);

	public Boolean deletePersonImage(String ImageLien, Long idProduct);

	public ArrayList<String> selectImagesByIdPerson(Long Id);

	public ArrayList<String> selectImagesByIdProduct(Long Id);

	public ArrayList<String> selectImagesByIdService(Long Id);
}
