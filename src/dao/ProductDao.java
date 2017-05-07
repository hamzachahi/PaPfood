package dao;

import java.util.ArrayList;

import beans.Product;

public interface ProductDao {
	public Boolean addProduct(Product product);

	public Boolean modifyProduct(Product product);

	public Boolean removeProduct(Product product);

	public Product findProductById(Long Id);

	public ArrayList<Product> findAllProduct();

}