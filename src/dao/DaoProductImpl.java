package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Product;

public class DaoProductImpl implements ProductDao {
	private UsineDao daoFactory;

	public DaoProductImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return addProduct(product, isSucceed);
	}

	private Boolean addProduct(Product product, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertProduct(), false,
					product.getCode(), product.getName(), product.getDescription(), product.getPrice(),
					product.getMainImage(), product.getIdProvider(), product.getStreetNumber(), product.getStreetName(),
					product.getCity(), product.getPostalCode(), product.getDepartement(), product.getCountry(),
					product.getLatLng());
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement du product!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifyProduct(Product product) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return modifyProduct(product, isSucceed);
	}

	private Boolean modifyProduct(Product product, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateProduct(), false,
					product.getCode(), product.getName(), product.getDescription(), product.getPrice(),
					product.getMainImage(), product.getIdProvider(), product.getStreetNumber(), product.getStreetName(),
					product.getCity(), product.getPostalCode(), product.getDepartement(), product.getCountry(),
					product.getLatLng(), product.getId());
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du product!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean removeProduct(Product product) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return removeProduct(product, isSucceed);
	}

	private Boolean removeProduct(Product product, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteProduct(), false,
					product.getId());
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du produit!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	public Product map(ResultSet result) throws SQLException {
		// this.servImpl = new DaoServiceImpl(daoFactory);
		Product product = new Product();
		product.setId(result.getLong("id"), true);
		product.setCode(result.getString("code"), true);
		product.setDescription(result.getString("description"), true);
		product.setIdProvider(result.getLong("id_provider"));
		product.setAdd_date(result.getDate("add_date"));
		product.setMainImage(result.getString("main_image"));
		product.setName(result.getString("name"), true);
		product.setPrice(result.getDouble("price"), true);
		product.setStreetName(result.getString("street_name"));
		product.setStreetNumber(result.getString("street_number"));
		product.setCity(result.getString("city_name"));
		product.setPostalCode(result.getString("postal_code"));
		product.setDepartement(result.getString("departement"));
		product.setCountry(result.getString("country"));
		product.setLatLng(result.getString("latlng"));
		product.setStatut(result.getInt("statut"));
		/*
		 * ArrayList<Association> assoc = new ArrayList<>(');
		 * assoc.addAll(ProductComponent.findProductComponentById(daoFactory,
		 * result.getLong("id"))); for (int i = 0; i < assoc.size(); i++) {
		 * product.getListSubProduct().add(this.findProductById(assoc.get(i).
		 * getIdFirstKey())); } assoc.removeAll(assoc);
		 * assoc.addAll(ProductService.findProductServiceById(daoFactory,
		 * result.getLong("id"))); for (int i = 0; i < assoc.size(); i++) {
		 * product.getListSubProduct().add(servImpl.findServiceById(assoc.get(i)
		 * .getIdFirstKey())); } product.setProductListImage(listImage, true);
		 */
		return product;
	}

	@Override
	public Product findProductById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findProductById(RequestRepository.getMysqlSelectProductById(), isSucceed, Id);
	}

	private Product findProductById(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Product product = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				product = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return product;
	}

	@Override
	public ArrayList<Product> findAllProduct(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findAllProduct(RequestRepository.getMysqlSelectAllProduct(), isSucceed, Id, limit, offset);
	}

	private ArrayList<Product> findAllProduct(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Product> product = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				product.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return product;
	}

	@Override
	public ArrayList<Product> findAllProductByIdProvider(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findAllProductById(Id, limit, offset, isSucceed, RequestRepository.getMysqlSelectProductByIdProvider());
	}

	private ArrayList<Product> findAllProductById(Long Id, Long limit, Long offset, Boolean isSucceed, String sql) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Product> produitResults = new ArrayList<Product>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				produitResults.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return produitResults;
	}

	@Override
	public Long countElements(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		Long nbre = countElements(Id, isSucceed);
		return nbre;
	}

	private Long countElements(Long Id, Boolean isSucceed) {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery("select count(id) as nb from product p where id_provider != " + Id);
			if (resultSet.next()) {
				isSucceed = true;
				nbre = resultSet.getLong("nb");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, statement, connexion);
		}
		return nbre;
	}

	@Override
	public Long countElementsByIdProvider(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return countElementsByIdProvider(Id, isSucceed);
	}

	private Long countElementsByIdProvider(Long Id, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountProductByIdProvider(), false, Id);
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
	public Boolean changeStatut(Long Id, Integer statut) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return changeStatut(Id, statut, isSucceed);
	}

	public Boolean changeStatut(Long Id, Integer statut1, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateProductStatut(), false,
					statut1, Id);
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du product!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

}
