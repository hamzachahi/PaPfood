package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import beans.Service;

public class DaoServiceImpl implements ServiceDao {
	private UsineDao daoFactory;

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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertService(), false,
					service.getCode(), service.getName(), service.getDescription(), service.getPrice(),
					service.getMainImage(), service.getIdProvider(), service.getStreetNumber(), service.getStreetName(),
					service.getCity(), service.getPostalCode(), service.getDepartement(), service.getCountry(),
					service.getLatLng());
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateService(), false,
					service.getCode(), service.getName(), service.getDescription(), service.getPrice(),
					service.getMainImage(), service.getIdProvider(), service.getStreetNumber(), service.getStreetName(),
					service.getCity(), service.getPostalCode(), service.getDepartement(), service.getCountry(),
					service.getLatLng(), service.getId());
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
		// this.prodImpl = new DaoProductImpl(daoFactory);
		Service service = new Service();
		service.setId(result.getLong("id"), true);
		service.setCode(result.getString("code"), true);
		service.setDescription(result.getString("description"), true);
		service.setIdProvider(result.getLong("id_provider"));
		service.setAdd_date(result.getDate("add_date"));
		service.setMainImage(result.getString("main_image"));
		service.setName(result.getString("name"), true);
		service.setPrice(result.getDouble("price"), true);
		service.setStreetName(result.getString("street_name"));
		service.setStreetNumber(result.getString("street_number"));
		service.setCity(result.getString("city_name"));
		service.setPostalCode(result.getString("postal_code"));
		service.setDepartement(result.getString("departement"));
		service.setCountry(result.getString("country"));
		service.setLatLng(result.getString("latlng"));
		service.setStatut(result.getInt("statut"));

		/*
		 * ArrayList<Association> assoc = new ArrayList<>();
		 * assoc.addAll(ProductComponent.findProductComponentById(daoFactory,
		 * result.getLong("id"))); for (int i = 0; i < assoc.size(); i++) {
		 * service.getListSubProduct().add(this.findServiceById(assoc.get(i).
		 * getIdFirstKey())); } assoc.removeAll(assoc);
		 * assoc.addAll(ProductService.findProductServiceById(daoFactory,
		 * result.getLong("id"))); for (int i = 0; i < assoc.size(); i++) {
		 * service.getListSubProduct().add(prodImpl.findProductById(assoc.get(i)
		 * .getIdFirstKey())); }
		 */

		// service.setServiceListImage(listImage, true);
		return service;
	}

	@Override
	public Service findServiceById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findServiceById(RequestRepository.getMysqlSelectServiceById(), isSucceed, Id);
	}

	private Service findServiceById(String sql, Boolean isSucceed, Object... objets) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Service service = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
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
	public ArrayList<Service> findAllService(Long Id, Long limit, Long offset) {
		Boolean isSucceed = false;
		return findAllService(RequestRepository.getMysqlSelectAllService(), isSucceed, Id, limit, offset);
	}

	private ArrayList<Service> findAllService(String sql, Boolean isSucceed, Object... objets) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Service> service = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
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
	public ArrayList<Service> findAllServiceByIdProvider(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findAllServiceById(Id, limit, offset, isSucceed, RequestRepository.getMysqlSelectServiceByIdProvider());
	}

	private ArrayList<Service> findAllServiceById(Long Id, Long limit, Long offset, Boolean isSucceed, String sql) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Service> produitResults = new ArrayList<Service>();

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
			resultSet = statement.executeQuery("select count(id) as nb from service p where id_provider !=" + Id);
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
					RequestRepository.getMysqlSelectCountServiceByIdProvider(), false, Id);
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateServiceStatut(), false,
					statut1, Id);
			int statut = Statement.executeUpdate();
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
}
