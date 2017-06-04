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
	private PersonDao persImpl;
	private ServiceDao servImpl;
	private ProductDao prodImpl;

	public DaoCommandeImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
		this.persImpl = daoFactory.getUtilisateurDao();
		this.servImpl = daoFactory.getServiceDao();
		this.prodImpl = daoFactory.getProductDao();

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
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertCommande(),
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
			connexion = daoFactory.getConnection();
			Statement = connexion.createStatement();
			int statut = Statement.executeUpdate(RequestRepository.getMysqlUpdateCommande());
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
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeleteCommande(),
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
			connexion = daoFactory.getConnection();
			for (Iterator<ElementCommand> iterator = listProducts.iterator(); iterator.hasNext();) {
				ElementCommand elementCommand = (ElementCommand) iterator.next();
				if (elementCommand.getmProduct().getType().equals("Service")) {
					Statement = initialisationRequetePreparee(connexion,
							RequestRepository.getMysqlInsertCommandeService(), false, Id,
							elementCommand.getmProduct().getId(), elementCommand.getQuantity());
					int statut = Statement.executeUpdate();
					if (statut != 0) {
						isSucceed = true;
					} else {
						isSucceed = false;
						throw new ExceptionDao("l'enregistrement est incomplet");

					}
				} else if (elementCommand.getmProduct().getType().equals("Produit")) {
					Statement = initialisationRequetePreparee(connexion,
							RequestRepository.getMysqlInsertCommandeProduct(), false, Id,
							elementCommand.getmProduct().getId(), elementCommand.getQuantity());
					int statut = Statement.executeUpdate();
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
			connexion = daoFactory.getConnection();
			if (allornot == true) {

			} else {
				for (Iterator<ElementCommand> iterator = listProducts.iterator(); iterator.hasNext();) {
					ElementCommand elementCommand = (ElementCommand) iterator.next();
					if (elementCommand.getmProduct().getClass().getName().equals(Service.class.getName())) {
						Statement = initialisationRequetePreparee(connexion,
								RequestRepository.getMysqlDeleteCommandeService(), false, Id,
								elementCommand.getmProduct().getId());
						int statut = Statement.executeUpdate();
						if (statut != 0) {
							isSucceed = true;
						} else {
							isSucceed = false;
							throw new ExceptionDao("la suppression est incomplète");

						}
					} else if (elementCommand.getmProduct().getClass().getSuperclass().getName()
							.equals(Product.class.getName())) {
						Statement = initialisationRequetePreparee(connexion,
								RequestRepository.getMysqlDeleteCommandeProduct(), false, Id,
								elementCommand.getmProduct().getId());
						int statut = Statement.executeUpdate();
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
		commande.setCustomer(persImpl.trouverParId(result.getLong("id_customer"), false));
		commande.setDateLivraison(result.getDate("date_delivered"));
		commande.setDateCommande(result.getDate("date_ordering"));
		commande.setState(result.getInt("state"));
		listCommandeProduct = this.findCommande_ProductParId(RequestRepository.getMysqlSelectFromCommandeProduct(),
				false, result.getLong("id"));
		listCommandeService = this.findCommande_ServiceParId(RequestRepository.getMysqlSelectFromCommandeService(),
				false, result.getLong("id"));
		commande.setElements(listCommandeProduct);
		commande.getElements().addAll(listCommandeService);
		return commande;
	}

	@Override
	public ArrayList<Commande> findCommandeParClient(Long id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findCommandeParClient(RequestRepository.getMysqlSelectFromCommandeByCustomer(), isSucceed, id, limit,
				offset);
	}

	private ArrayList<Commande> findCommandeParClient(String sql, Boolean isSucceed, Object... objets) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Commande> commande = new ArrayList<Commande>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
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

	@Override
	public Commande findCommandeParId(Long id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findCommandeParId(RequestRepository.getMysqlSelectFromCommandeById(), isSucceed, id);
	}

	private Commande findCommandeParId(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Commande commande = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
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
	public ArrayList<Commande> findAllCommande(Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return findAllCommande(RequestRepository.getMysqlSelectAllCommande(), isSucceed, limit, offset);
	}

	private ArrayList<Commande> findAllCommande(String sql, Boolean isSucceed, Object... objets) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Commande> commande = new ArrayList<>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
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
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				isSucceed = true;
				ElementCommand el = new ElementCommand();
				Product p = prodImpl.findProductById(resultSet.getLong("id_product"));
				el.setmProduct(p);
				el.setQuantity(resultSet.getInt("quantity"));
				commandeProduct.add(el);
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
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				isSucceed = true;
				ElementCommand el = new ElementCommand();
				Service p = servImpl.findServiceById(resultSet.getLong("id_service"));
				el.setmProduct(p);
				el.setQuantity(resultSet.getInt("quantity"));
				commandeProduct.add(el);
				i = i + 1;
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commandeProduct;
	}

	@Override
	public Long countNbreCommandeByIdCustomer(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return countNbreCommandeByIdCustomer(Id, isSucceed);
	}

	private Long countNbreCommandeByIdCustomer(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountCommandeByIdCustomer(), false, Id);
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
	public Long countAllCommande() {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return countAllCommande(isSucceed);
	}

	private Long countAllCommande(Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery("select count(id) as nb from commande m");
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
}
