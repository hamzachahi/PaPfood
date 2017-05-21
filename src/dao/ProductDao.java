package dao;

import java.util.ArrayList;

import beans.Product;

public interface ProductDao {
	public Boolean addProduct(Product product);

	public Boolean modifyProduct(Product product);

	public Boolean removeProduct(Product product);

	public Product findProductById(Long Id);

	public ArrayList<Product> findProductByKeyWord(String keyword);

	public ArrayList<Product> findAllProduct(Long limit, Long offset);

	public ArrayList<Product> findAllProductByIdProvider(Long Id, Long limit, Long offset);

	public Long countElements();

	public Long countElementsByIdProvider(Long Id);

}
