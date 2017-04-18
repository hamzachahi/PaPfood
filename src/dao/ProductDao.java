package dao;

import beans.Product;

public interface ProductDao {
	public Boolean addProduct(Product product);

	public Boolean modifyProduct(Product product);

	public Boolean removeProduct(Product product);
}
