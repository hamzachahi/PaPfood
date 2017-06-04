package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.Person;
import beans.Product;
import beans.Service;

public class DaoSearchImpl implements SearchDao {
	private UsineDao daoFactory;

	public DaoSearchImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Product> findProductByKeyWord(String keyword, Long Id, Long limit, Long offset) {
		Boolean isSucceed = false;
		return findProductByKeyWord("%" + keyword + "%", Id, limit, offset, isSucceed);
	}

	private ArrayList<Product> findProductByKeyWord(String keyWord, Long Id, Long limit, Long offset,
			Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Product> produitResults = new ArrayList<Product>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectProductByKeyword(), false, Id, keyWord, keyWord, keyWord, limit,
					offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				produitResults.add(mapProduct(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return produitResults;
	}

	@Override
	public ArrayList<Service> findServiceByKeyWord(String keyWord, Long Id, Long limit, Long offset) {
		Boolean isSucceed = false;
		return findServiceByKeyWord(Id, "%" + keyWord + "%", limit, offset, isSucceed);
	}

	public ArrayList<Service> findServiceByKeyWord(Long Id, String keyWord, Long limit, Long offset,
			Boolean isSucceed) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Service> serviceResults = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectServiceByKeyword(), false, Id, keyWord, keyWord, keyWord, limit,
					offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				serviceResults.add(mapService(resultSet));

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return serviceResults;
	}

	@Override
	public ArrayList<Person> findPersonByKeyWord(String keyWord, Long Id, Long limit, Long offset) {
		Boolean isSucceed = false;

		return findPersonByKeyWord(Id, "%" + keyWord + "%", limit, offset, isSucceed);
	}

	private ArrayList<Person> findPersonByKeyWord(Long Id, String keyWord, Long limit, Long offset, Boolean isSucceed) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Person> personResults = new ArrayList<Person>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectPersonByKeyword(), false, keyWord, keyWord, keyWord, keyWord,
					keyWord, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				personResults.add(mapPerson(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return personResults;
	}

	@Override
	public Product mapProduct(ResultSet result) throws SQLException {
		Boolean isSucceed = false;
		return mapProduct(result, isSucceed);
	}

	private Product mapProduct(ResultSet result, Boolean isSucceed) throws SQLException {
		Product product = new Product();
		product.setId(result.getLong("id"), true);
		product.setCode(result.getString("code"), true);
		product.setDescription(result.getString("description"), true);
		product.setIdProvider(result.getLong("id_provider"));
		product.setAdd_date(result.getDate("add_date"));
		// product.setMainImage(result.getBlob("main_image"), true);
		product.setName(result.getString("name"), true);
		product.setPrice(result.getDouble("price"), true);
		// product.setProductListImage(listImage, true);
		return product;
	}

	@Override
	public Service mapService(ResultSet result) throws SQLException {
		Boolean isSucceed = false;
		return mapService(result, isSucceed);
	}

	private Service mapService(ResultSet result, Boolean isSucceed) throws SQLException {
		Service service = new Service();
		service.setId(result.getLong("id"), true);
		service.setCode(result.getString("code"), true);
		service.setDescription(result.getString("description"), true);
		service.setIdProvider(result.getLong("id_provider"));
		service.setAdd_date(result.getDate("add_date"));
		// service.setMainImage(result.getBlob("main_image"), true);
		service.setName(result.getString("name"), true);
		service.setPrice(result.getDouble("price"), true);
		// service.setServiceListImage(listImage, true);
		return service;
	}

	@Override
	public Person mapPerson(ResultSet result) throws SQLException {
		Boolean isSucceed = false;
		return mapPerson(result, isSucceed);
	}

	private Person mapPerson(ResultSet result, Boolean isSucceed) throws SQLException {
		Person utilisateur = new Person();
		utilisateur.setId(result.getLong("id"), false);
		utilisateur.setEmail(result.getString("email"), false);
		utilisateur.setPassword(result.getString("password"), false);
		utilisateur.setName(result.getString("name"), false);
		utilisateur.setSurname(result.getString("surname"), false);
		utilisateur.setSecondName(result.getString("second_name"), false);
		utilisateur.setDateInscription(result.getTimestamp("date_inscription"), false);
		utilisateur.setSecondSurname(result.getString("second_surname"), false);
		utilisateur.setProfession(result.getString("profession"), false);
		utilisateur.setPhoneNumber(result.getString("phone_number"), false);
		utilisateur.setTelNumber(result.getString("tel_number"), false);
		utilisateur.setFacebookId(result.getString("facebook_id"), false);
		utilisateur.setTwitterId(result.getString("twitter_id"), false);
		utilisateur.setInstagramId(result.getString("instagram_id"), false);
		utilisateur.setLinkedinId(result.getString("linkedin_id"), false);
		utilisateur.setStreetNumber(result.getString("street_number"), false);
		utilisateur.setStreetName(result.getString("street_name"), false);
		utilisateur.setCityName(result.getString("city_name"), false);
		utilisateur.setCountryName(result.getString("country_name"), false);
		utilisateur.setPostalCode(result.getString("postal_code"), false);
		utilisateur.setLastConnexion(result.getLong("last_connection"));
		utilisateur.setFunction(result.getString("function"));
		utilisateur.setAccountPicture(result.getString("account_picture"), false);
		return utilisateur;
	}

	@Override
	public Long countProductByKeyWord(Long Id, String keyWord) {
		Boolean isSucceed = false;
		return countProductByKeyWord(Id, "%" + keyWord + "%", isSucceed);
	}

	private Long countProductByKeyWord(Long Id, String keyWord, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountProductByKeyword(), false, Id, keyWord, keyWord, keyWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				nbre = resultSet.getLong("nb");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return nbre;
	}

	@Override
	public Long countServiceByKeyWord(Long Id, String keyWord) {
		Boolean isSucceed = false;
		return countServiceByKeyWord(Id, "%" + keyWord + "%", isSucceed);
	}

	private Long countServiceByKeyWord(Long Id, String keyWord, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountServiceByKeyword(), false, Id, keyWord, keyWord, keyWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				nbre = resultSet.getLong("nb");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return nbre;
	}

	@Override
	public Long countPersonByKeyWord(Long Id, String keyWord) {
		Boolean isSucceed = false;
		return countPersonByKeyWord(Id, "%" + keyWord + "%", isSucceed);
	}

	private Long countPersonByKeyWord(Long Id, String keyWord, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountPersonByKeyword(), false, Id, keyWord, keyWord, keyWord,
					keyWord, keyWord);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				nbre = resultSet.getLong("nb");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return nbre;
	}
}
