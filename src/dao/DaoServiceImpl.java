package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Association;
import beans.Service;

public class DaoServiceImpl implements ServiceDao {
	private UsineDao daoFactory;
	private DaoProductImpl prodImpl;

	public DaoServiceImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean addService(Service service) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return addService(service, isSucceed);
	}

	private Boolean addService(Service service, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertService(), false,
					service.getCode(), service.getName(), service.getDescription(), service.getPrice(),
					service.getMainImage(), service.getIdProvider());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement du service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifyService(Service service) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return modifyService(service, isSucceed);
	}

	private Boolean modifyService(Service service, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlUpdateService(), false,
					service.getCode(), service.getName(), service.getDescription(), service.getPrice(),
					service.getMainImage(), service.getIdProvider(), service.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean removeService(Service service) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return removeService(service, isSucceed);
	}

	private Boolean removeService(Service service, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteService(), false,
					service.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	public Service map(ResultSet result) throws SQLException {
		this.prodImpl = new DaoProductImpl(daoFactory);
		Service service = new Service();
		service.setId(result.getLong("id"), true);
		service.setCode(result.getString("code"), true);
		service.setDescription(result.getString("description"), true);
		service.setIdProvider(result.getLong("id_provider"));
		ArrayList<Association> assoc = new ArrayList<>();
		assoc.addAll(ProductComponent.findProductComponentById(daoFactory, result.getLong("id")));
		for (int i = 0; i < assoc.size(); i++) {
			service.getListSubProduct().add(this.findServiceById(assoc.get(i).getIdFirstKey()));
		}
		assoc.removeAll(assoc);
		assoc.addAll(ProductService.findProductServiceById(daoFactory, result.getLong("id")));
		for (int i = 0; i < assoc.size(); i++) {
			service.getListSubProduct().add(prodImpl.findProductById(assoc.get(i).getIdFirstKey()));
		}
		// service.setMainImage(result.getBlob("main_image"), true);
		service.setName(result.getString("name"), true);
		service.setPrice(result.getDouble("price"), true);
		// service.setServiceListImage(listImage, true);
		return service;
	}

	@Override
	public Service findServiceById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findServiceById(RequestRepository.getOraclesqlSelectAll(), isSucceed, Id);
	}

	private Service findServiceById(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Service service = null;

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
				service = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return service;
	}

	@Override
	public ArrayList<Service> findAllService() {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		String p = "service";
		return findAllService(RequestRepository.getOraclesqlSelectAll(), isSucceed, p);
	}

	private ArrayList<Service> findAllService(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Service> service = new ArrayList<>();

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
				service.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return service;
	}

	@Override
	public ArrayList<Service> findServiceByKeyWord(String keyWord) {
		Boolean isSucceed = false;
		return findServiceByKeyWord(RequestRepository.getOraclesqlSelectServiceByKeyword(), keyWord, isSucceed);
	}

	private ArrayList<Service> findServiceByKeyWord(String sql, String keyWord, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Service> serviceResults = new ArrayList<>();

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
			while (resultSet.next()) {
				isSucceed = true;
				serviceResults.add(map(resultSet));

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return serviceResults;
	}

}
