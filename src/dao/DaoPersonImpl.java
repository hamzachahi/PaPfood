package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Command;
import beans.Person;
import beans.Product;
import beans.ResultConnexion;

public class DaoPersonImpl implements PersonDao {

	
	private UsineDao daoFactory;

	public DaoPersonImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public Person trouver(String email) throws ExceptionDao {
		return trouver(RequestRepository.getSqlSeTrouverParEmail(), email);
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public void creer(Person utilisateur) throws ExceptionDao {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getSqlInsertUser(), true, utilisateur.getEmail(),
					utilisateur.getPassword(), utilisateur.getName());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				utilisateur.setId(String.valueOf(valeursAutoGenerees.getLong(1)), false);
			} else {
				throw new ExceptionDao("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	/*
	 * Méthode générique utilisée pour retourner un utilisateur depuis la base
	 * de données, correspondant à la requête SQL donnée prenant en paramètres
	 * les objets passés en argument.
	 */
	private Person trouver(String sql, Object... objets) throws ExceptionDao {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person utilisateur = null;

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
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	private static Person map(ResultSet resultSet) throws SQLException {
		Person utilisateur = new Person();
		utilisateur.setId(resultSet.getString("id"), false);
		utilisateur.setEmail(resultSet.getString("email"), false);
		utilisateur.setPassword(resultSet.getString("mot_de_passe"), false);
		utilisateur.setName(resultSet.getString("nom"), false);
		utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"), false);
		return utilisateur;
	}

	@SuppressWarnings("static-access")
	@Override
	public ResultConnexion seConnecter(String Id, String Password) {
		// TODO Auto-generated method stub
		ResultConnexion result = null;
		result.setConnection(null);
		result.setSucceed(false);
		try {
			daoFactory.setUsername(Id);
			daoFactory.setPassword(Password);
			Connection connect = daoFactory.getConnection();
			result.setConnection(connect);
			result.setSucceed(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean Commander(Command commande, String Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> acceptCommand(ArrayList<String> listCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] Payment(ArrayList<String> listCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean modifyPersonalInformation(Person pers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAccount(Person pers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Comment(Person pers, String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Claim(String claiming) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Search(ArrayList<String> Params, String KeyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addToPanel(ArrayList<Product> listProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeToPanel(ArrayList<Product> listProducts, Boolean allornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Reservation(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendMessage(Person pers, String Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean receiveMessage(Person pers, String Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> Historique(Integer nbreJours) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Invite(String e_mailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}