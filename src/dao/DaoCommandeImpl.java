package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import beans.Commande;
import beans.ElementCommand;
import beans.Product;
import beans.Service;

public class DaoCommandeImpl implements CommandeDao {
	private UsineDao daoFactory;
	private DaoPersonImpl persImpl;
	private DaoServiceImpl servImpl;
	private DaoProductImpl prodImpl;

	public DaoCommandeImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
		this.persImpl = new DaoPersonImpl(daoFactory);
		this.servImpl = new DaoServiceImpl(daoFactory);
		this.prodImpl = new DaoProductImpl(daoFactory);

	}

	@Override
	public Boolean Commander(Commande commande) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return Commander(commande, isSucceed);
	}

	private Boolean Commander(Commande commande, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. Création de la commande...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertCommande(),
					true, commande.getCode(), commande.getCustomer().getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao("Échec de la création de la commande, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				Statement Statement2 = connexion.createStatement();
				ResultSet resultSet2 = null;
				resultSet2 = Statement2
						.executeQuery("SELECT id  FROM commande where id=(select Max(id) from commande)");
				if (resultSet2.next()) {
					commande.setId(resultSet2.getLong("id"));
					System.out.println("Nouvel(le) utilisateur(rice) récupéré(e) : " + commande.toString());
					addToCommand(commande.getElements(), commande.getId());
				}
			} else {
				throw new ExceptionDao("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifyCommande(Commande commande) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return modifyCommande(commande, isSucceed);
	}

	private Boolean modifyCommande(Commande commande, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		Statement Statement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = connexion.createStatement();
			int statut = Statement.executeUpdate(RequestRepository.getOraclesqlUpdateCommande());
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
				addToCommand(commande.getToAddElements(), isSucceed, commande.getId());
				removeToCommand(commande.getToDeleteElements(), false, isSucceed, commande.getId());
			} else {
				throw new ExceptionDao("la mise à jour de l'état de la commande est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean deleteCommande(Commande commande) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return deleteCommande(commande, isSucceed);
	}

	private Boolean deleteCommande(Commande commande, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. suppression de la commande...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteCommande(),
					true, commande.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao("Échec de la suppression de la commande, aucune ligne supprimée dans la table.");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean addToCommand(ArrayList<ElementCommand> listProducts, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return addToCommand(listProducts, isSucceed, Id);
	}

	private Boolean addToCommand(ArrayList<ElementCommand> listProducts, Boolean isSucceed, Long Id) {
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
			for (Iterator<ElementCommand> iterator = listProducts.iterator(); iterator.hasNext();) {
				ElementCommand elementCommand = (ElementCommand) iterator.next();
				if (elementCommand.getmProduct().getClass().getName().equals(Service.class.getName())) {
					Statement = initialisationRequetePreparee(connexion,
							RequestRepository.getOraclesqlInsertCommandeService(), false, Id,
							elementCommand.getmProduct().getId(), elementCommand.getQuantity());
					int statut = Statement.executeUpdate();
					/*
					 * Parcours de la ligne de données retournée dans le
					 * ResultSet
					 */
					if (statut != 0) {
						isSucceed = true;
					} else {
						isSucceed = false;
						throw new ExceptionDao("l'enregistrement est incomplet");

					}
				} else if (elementCommand.getmProduct().getClass().getSuperclass().getName()
						.equals(Product.class.getName())) {
					Statement = initialisationRequetePreparee(connexion,
							RequestRepository.getOraclesqlInsertCommandeProduct(), false, Id,
							elementCommand.getmProduct().getId(), elementCommand.getQuantity());
					int statut = Statement.executeUpdate();
					/*
					 * Parcours de la ligne de données retournée dans le
					 * ResultSet
					 */
					if (statut != 0) {
						isSucceed = true;
					} else {
						isSucceed = false;
						throw new ExceptionDao("l'enregistrement est incomplet");

					}
				}
			}

		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean removeToCommand(ArrayList<ElementCommand> listProducts, Boolean allornot, Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return removeToCommand(listProducts, allornot, isSucceed, Id);
	}

	private Boolean removeToCommand(ArrayList<ElementCommand> listProducts, Boolean allornot, Boolean isSucceed,
			Long Id) {
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
			if (allornot == true) {

			} else {
				for (Iterator<ElementCommand> iterator = listProducts.iterator(); iterator.hasNext();) {
					ElementCommand elementCommand = (ElementCommand) iterator.next();
					if (elementCommand.getmProduct().getClass().getName().equals(Service.class.getName())) {
						Statement = initialisationRequetePreparee(connexion,
								RequestRepository.getOraclesqlDeleteCommandeService(), false, Id,
								elementCommand.getmProduct().getId());
						int statut = Statement.executeUpdate();
						/*
						 * Parcours de la ligne de données retournée dans le
						 * ResultSet
						 */
						if (statut != 0) {
							isSucceed = true;
						} else {
							isSucceed = false;
							throw new ExceptionDao("la suppression est incomplète");

						}
					} else if (elementCommand.getmProduct().getClass().getSuperclass().getName()
							.equals(Product.class.getName())) {
						Statement = initialisationRequetePreparee(connexion,
								RequestRepository.getOraclesqlDeleteCommandeProduct(), false, Id,
								elementCommand.getmProduct().getId());
						int statut = Statement.executeUpdate();
						/*
						 * Parcours de la ligne de données retournée dans le
						 * ResultSet
						 */
						if (statut != 0) {
							isSucceed = true;
						} else {
							isSucceed = false;
							throw new ExceptionDao("la suppression est incomplète");

						}
					}
				}

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	private Commande map(ResultSet result) throws SQLException {
		ArrayList<ElementCommand> listCommandeProduct = new ArrayList<>();
		ArrayList<ElementCommand> listCommandeService = new ArrayList<>();
		Commande commande = new Commande();
		commande.setId(result.getLong("id"));
		commande.setCode(result.getString("code"));
		commande.getCustomer().setId(result.getLong("id_customer"), true);
		commande.setDateLivraison(result.getDate("date_delivered"));
		commande.setDateCommande(result.getDate("date_ordering"));
		commande.setState(result.getInt("state"));
		commande.setAdresseFacturation(result.getString("billing_address"));
		commande.setAdresseExpedition(result.getString("shipping_address"));
		listCommandeProduct = this.findCommande_ProductParId(RequestRepository.getOraclesqlSelectFromCommandeProduct(),
				false, result.getLong("id"));
		listCommandeService = this.findCommande_ServiceParId(RequestRepository.getOraclesqlSelectFromCommandeService(),
				false, result.getLong("id"));
		for (int i = 0; i < listCommandeProduct.size(); i++) {

			ElementCommand long1 = listCommandeProduct.get(i);
			commande.getElements().add(long1);
			commande.getElements().get(i).setmProduct(prodImpl.findProductById(long1.getmProduct().getId()));
		}

		for (int i = 0; i < listCommandeService.size(); i++) {

			ElementCommand long1 = listCommandeProduct.get(i);

			commande.getElements().add(long1);
			commande.getElements().get(i).setmProduct(servImpl.findServiceById(long1.getmProduct().getId()));
		}
		commande.setCustomer(persImpl.trouverParId(result.getLong("id"), false));
		return commande;
	}

	@Override
	public Commande findCommandeParClient(Long id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findCommandeParClient(RequestRepository.getOraclesqlSelectFromCommandeByCustomer(), isSucceed, id);
	}

	private Commande findCommandeParClient(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Commande commande = null;

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
				commande = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commande;
	}

	@Override
	public Commande findCommandeParId(Long id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findCommandeParId(RequestRepository.getOraclesqlSelectFromCommandeByCustomer(), isSucceed, id);
	}

	private Commande findCommandeParId(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Commande commande = null;

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
				commande = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commande;
	}

	@Override
	public ArrayList<Commande> findAllCommande() {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findAllCommande(RequestRepository.getOraclesqlSelectAll(), isSucceed, "commande");
	}

	private ArrayList<Commande> findAllCommande(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Commande> commande = new ArrayList<>();

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
				commande.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commande;
	}

	private ArrayList<ElementCommand> findCommande_ProductParId(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<ElementCommand> commandeProduct = new ArrayList<>();

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
			int i = 0;
			while (resultSet.next()) {
				isSucceed = true;
				commandeProduct.get(i).getmProduct().setId((resultSet.getLong("id_product")));
				commandeProduct.get(i).setQuantity(resultSet.getInt("quantity"));
				i = i + 1;
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commandeProduct;
	}

	private ArrayList<ElementCommand> findCommande_ServiceParId(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<ElementCommand> commandeProduct = new ArrayList<>();

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
			int i = 0;
			while (resultSet.next()) {
				isSucceed = true;
				commandeProduct.get(i).getmProduct().setId((resultSet.getLong("id_service")));
				commandeProduct.get(i).setQuantity(resultSet.getInt("quantity"));
				i = i + 1;
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commandeProduct;
	}
}
