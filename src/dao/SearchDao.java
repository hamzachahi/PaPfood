package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Product;
import beans.Service;
import beans.Person;

public interface SearchDao {
	public ArrayList<Product> findProductByKeyWord(String keyword, Long limit, Long offset);

	public ArrayList<Service> findServiceByKeyWord(String keyWord, Long limit, Long offset);

	public ArrayList<Person> findPersonByKeyWord(String keyWord, Long limit, Long offset);

	public Product mapProduct(ResultSet result) throws SQLException;

	public Service mapService(ResultSet result) throws SQLException;

	public Person mapPerson(ResultSet result) throws SQLException;

	public Long countProductByKeyWord(String keyWord);

	public Long countServiceByKeyWord(String keyWord);

	public Long countPersonByKeyWord(String keyWord);
}
