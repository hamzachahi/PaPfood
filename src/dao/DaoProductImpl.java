package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Association;
import beans.Product;

public class DaoProductImpl implements ProductDao {
	private UsineDao daoFactory;
	private DaoServiceImpl servImpl;

	public DaoProductImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
		this.servImpl = new DaoServiceImpl(daoFactory);
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
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertProduct(), false,
					product.getCode(), product.getName(), product.getDescription(), product.getPrice(),
					product.getMainImage(), product.getIdProvider());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
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
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlUpdateProduct(), false,
					product.getCode(), product.getName(), product.getDescription(), product.getPrice(),
					product.getMainImage(), product.getIdProvider(), product.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
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
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteProduct(), false,
					product.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
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
		this.servImpl = new DaoServiceImpl(daoFactory);
		Product product = new Product();
		product.setId(result.getLong("id"), true);
		product.setCode(result.getString("code"), true);
		product.setDescription(result.getString("description"), true);
		product.setIdProvider(result.getLong("id_provider"));
		ArrayList<Association> assoc = new ArrayList<>();
		assoc.addAll(ProductComponent.findProductComponentById(daoFactory, result.getLong("id")));
		for (int i = 0; i < assoc.size(); i++) {
			product.getListSubProduct().add(this.findProductById(assoc.get(i).getIdFirstKey()));
		}
		assoc.removeAll(assoc);
		assoc.addAll(ProductService.findProductServiceById(daoFactory, result.getLong("id")));
		for (int i = 0; i < assoc.size(); i++) {
			product.getListSubProduct().add(servImpl.findServiceById(assoc.get(i).getIdFirstKey()));
		}
		// product.setMainImage(result.getBlob("main_image"), true);
		product.setName(result.getString("name"), true);
		product.setPrice(result.getDouble("price"), true);
		// product.setProductListImage(listImage, true);
		return product;
	}

	@Override
	public Product findProductById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findProductById(RequestRepository.getOraclesqlSelectAll(), isSucceed, Id);
	}

	private Product findProductById(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Product product = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
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
	public ArrayList<Product> findAllProduct() {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		String p = "product";
		return findAllProduct(RequestRepository.getOraclesqlSelectAll(), isSucceed, p);
	}

	private ArrayList<Product> findAllProduct(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Product> product = new ArrayList<>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
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
	public ArrayList<Product> findProductByKeyWord(String keyWord) {
		Boolean isSucceed = false;
		return findProductByKeyWord(RequestRepository.getOraclesqlSelectServiceByKeyword(), keyWord, isSucceed);
	}

	private ArrayList<Product> findProductByKeyWord(String sql, String keyWord, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Product> produitResults = new ArrayList<Product>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, keyWord);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet.next()) {
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
}
