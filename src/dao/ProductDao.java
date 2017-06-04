package dao;

import java.util.ArrayList;

import beans.Product;

public interface ProductDao {
	public Boolean addProduct(Product product);

	public Boolean modifyProduct(Product product);

	public Boolean removeProduct(Product product);

	public Product findProductById(Long Id);

	public ArrayList<Product> findAllProduct(Long Id, Long limit, Long offset);

	public ArrayList<Product> findAllProductByIdProvider(Long Id, Long limit, Long offset);

	public Long countElements(Long Id);

	public Long countElementsByIdProvider(Long Id);

	public Boolean changeStatut(Long Id, Integer statut);

}
