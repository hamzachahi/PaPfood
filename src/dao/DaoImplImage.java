package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoImplImage implements ImageDao {
	private UsineDao daoFactory;

	public DaoImplImage(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean imageProductLinkRegister(String link, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return imageProductLinkRegister(link, Id, isSucceed);
	}

	private Boolean imageProductLinkRegister(String link, Long Id, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertImageProductLink(), false,
					link, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement du lien de l'image du produit!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean imageServiceLinkRegister(String link, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return imageServiceLinkRegister(link, Id, isSucceed);
	}

	private Boolean imageServiceLinkRegister(String link, Long Id, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertImageServiceLink(), false,
					link, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement du lien de l'image du service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifiyProductImage(String ImageLien, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return modifiyProductImage(ImageLien, Id, isSucceed);
	}

	private Boolean modifiyProductImage(String ImageLien, Long Id, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateImageProductLink(), false,
					ImageLien, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification de l'image du produit!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean deleteProductImage(Long idProduct) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return deleteProductImage(idProduct, isSucceed);
	}

	private Boolean deleteProductImage(Long idProduct, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeleteImageProductLink(), false,
					idProduct);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du lien de l'image du produit!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifiyServiceImage(String ImageLien, Long idProdcut) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return modifiyServiceImage(ImageLien, idProdcut, isSucceed);
	}

	private Boolean modifiyServiceImage(String ImageLien, Long idProdcut, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateImageServiceLink(), false,
					ImageLien, idProdcut);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du lien de l'image du service service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean deleteServiceImage(String ImageLien, Long idProduct) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return deleteServiceImage(ImageLien, idProduct, isSucceed);
	}

	private Boolean deleteServiceImage(String ImageLien, Long idProduct, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeleteImageServiceLink(), false,
					idProduct);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du lien de l'image du service!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public ArrayList<String> selectImagesByIdPerson(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return selectImagesByIdPerson(Id, RequestRepository.getMysqlSelectAllPersonImageLinks(), isSucceed);
	}

	private ArrayList<String> selectImagesByIdPerson(Long Id, String sql, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> listLinks = new ArrayList<String>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, Id);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			while (resultSet.next()) {
				isSucceed = true;
				listLinks.add(resultSet.getString("link"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao("Aucun élément trouvé ou erreur");
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return listLinks;
	}

	@Override
	public ArrayList<String> selectImagesByIdProduct(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return selectImagesByIdProduct(Id, RequestRepository.getMysqlSelectAllProductImageLinks(), isSucceed);
	}

	private ArrayList<String> selectImagesByIdProduct(Long Id, String sql, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> listLinks = new ArrayList<String>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, Id);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			while (resultSet.next()) {
				isSucceed = true;
				listLinks.add(resultSet.getString("link"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao("Aucun élément trouvé ou erreur");
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return listLinks;
	}

	@Override
	public ArrayList<String> selectImagesByIdService(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return selectImagesByIdService(Id, RequestRepository.getMysqlSelectAllServiceImageLinks(), isSucceed);
	}

	private ArrayList<String> selectImagesByIdService(Long Id, String sql, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> listLinks = new ArrayList<String>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, Id);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			while (resultSet.next()) {
				isSucceed = true;
				listLinks.add(resultSet.getString("link"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao("Aucun élément trouvé ou erreur");
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return listLinks;
	}

	@Override
	public Boolean imagePersonLinkRegister(String link, Long Id, String Utility) {
		// TODO Auto-generated method stub
		Boolean isSucceed=false;
		return imagePersonLinkRegister(link, Id, isSucceed, Utility);
	}
	private Boolean imagePersonLinkRegister(String link, Long Id, Boolean isSucceed, String Utility) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertImagePersonLink(), false,
					link, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement du lien de l'image du produit!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifiyPersonImage(String ImageLien, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed=false;		
		return modifiyPersonImage(ImageLien, Id, isSucceed);
	}
	private Boolean modifiyPersonImage(String ImageLien, Long Id, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateImagePersonLink(), false,
					ImageLien, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification de l'image de la personne!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean deletePersonImage(String ImageLien, Long idProduct) {
		// TODO Auto-generated method stub
		Boolean isSucceed=false;
		return deletePersonImage(ImageLien, idProduct, isSucceed);
	}
	private Boolean deletePersonImage(String ImageLien, Long idProduct, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeleteImagePersonLink(), false,
					idProduct);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du lien de l'image de la personne!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

}
