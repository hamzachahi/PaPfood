package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import beans.Commande;
import beans.Person;
import beans.ResultConnexion;
import config.UseMail;

public class DaoPersonImpl implements PersonDao {

	private UsineDao daoFactory;

	public DaoPersonImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	@Override
	public Person trouver(String email, boolean succeed) throws ExceptionDao {
		return trouver(RequestRepository.getOraclesqlSeTrouverTotalementParEmail(), succeed, email);
	}

	@Override
	public Person trouverParId(Long id, boolean succeed) throws ExceptionDao {
		return trouverParId(RequestRepository.getOraclesqlSeTrouverParId(), succeed, id);
	}

	/* Implémentation de la méthode définie dans l'interface UtilisateurDao */

	@Override
	public void creer(Person utilisateur) throws ExceptionDao {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			utilisateur.setFunction("Perculiar");
			System.out.println("connexion réussie. Création de personne...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertPerson(), true,
					utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getName(),
					utilisateur.getFunction());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				Statement Statement2 = connexion.createStatement();
				ResultSet resultSet2 = null;
				resultSet2 = Statement2.executeQuery("SELECT *  FROM person where id=(select Max(id) from person)");
				if (resultSet2.next()) {
					utilisateur = map(resultSet2);
					System.out.println("Nouvel(le) utilisateur(rice) récupéré(e) : " + utilisateur.toString());
				}
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
	private Person trouver(String sql, boolean succeed, Object... objets) throws ExceptionDao {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person utilisateur = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println("Connexion récupérée!");

			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			System.out.println("Requête executée!");
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet.next()) {
				succeed = true;
				utilisateur = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return utilisateur;
	}

	private Person trouverParId(String sql, boolean succeed, Object... objets) throws ExceptionDao {
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
				succeed = true;
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
		utilisateur.setId(resultSet.getLong("id"), false);
		utilisateur.setEmail(resultSet.getString("email"), false);
		utilisateur.setPassword(resultSet.getString("password"), false);
		utilisateur.setName(resultSet.getString("name"), false);
		utilisateur.setSurname(resultSet.getString("surname"), false);
		utilisateur.setSecondName(resultSet.getString("second_name"), false);
		utilisateur.setDateInscription(resultSet.getTimestamp("date_inscription"), false);
		utilisateur.setSecondSurname(resultSet.getString("second_surname"), false);
		utilisateur.setProfession(resultSet.getString("profession"), false);
		utilisateur.setPhoneNumber(resultSet.getString("phone_number"), false);
		utilisateur.setTelNumber(resultSet.getString("tel_number"), false);
		utilisateur.setFacebookId(resultSet.getString("facebook_id"), false);
		utilisateur.setTwitterId(resultSet.getString("twitter_id"), false);
		utilisateur.setInstagramId(resultSet.getString("instagram_id"), false);
		utilisateur.setLinkedinId(resultSet.getString("linkedin_id"), false);
		utilisateur.setStreetNumber(resultSet.getString("street_number"), false);
		utilisateur.setStreetName(resultSet.getString("street_name"), false);
		utilisateur.setCityName(resultSet.getString("city_name"), false);
		utilisateur.setCountryName(resultSet.getString("country_name"), false);
		utilisateur.setPostalCode(resultSet.getString("postal_code"), false);
		utilisateur.setLastConnexion(resultSet.getInt("last_connection"));
		utilisateur.setFunction(resultSet.getString("function"));
		utilisateur.setAccountPicture(resultSet.getString("account_picture"), false);
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
	public Boolean acceptCommand(ArrayList<Commande> listCommand) {
		// TODO Auto-generated method stub
		boolean succeed = true;
		boolean test = true;
		for (int i = 0; i < listCommand.size(); i++) {
			test = acceptCommand(listCommand.get(i));
			if (test == false) {
				succeed = false;
			}
		}
		return succeed;
	}

	private Boolean acceptCommand(Commande commande) {
		Boolean isSucceed = false;
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
			int statut = Statement.executeUpdate(RequestRepository.getOraclesqlUpdateCommandeState());
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("erreur dans la validation de la commande!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean modifyPersonalInformation(Person pers) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return modifyPersonalInformation(pers, isSucceed);
	}

	private Boolean modifyPersonalInformation(Person utilisateur, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. mise à jour de personne...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdatePerson(), true,
					utilisateur.getName(), utilisateur.getSecondName(), utilisateur.getSurname(),
					utilisateur.getSecondSurname(), utilisateur.getProfession(), utilisateur.getEmail(),
					utilisateur.getPhoneNumber(), utilisateur.getTelNumber(), utilisateur.getFacebookId(),
					utilisateur.getTwitterId(), utilisateur.getInstagramId(), utilisateur.getLinkedinId(),
					utilisateur.getAccountPicture(), utilisateur.getStreetNumber(), utilisateur.getStreetName(),
					utilisateur.getCityName(), utilisateur.getCountryName(), utilisateur.getPostalCode(),
					utilisateur.getFunction(), utilisateur.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao(
						"Échec de la mise à jour des informations de l'utilisateur, aucune ligne modifiée dans la table.");
			} else {
				System.out.println("Modification effectuée!");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean deleteAccount(Person pers) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return deletePersonalInformation(pers, isSucceed);
	}

	private Boolean deletePersonalInformation(Person utilisateur, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. suppression du compte...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeletePerson(),
					true, utilisateur.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new ExceptionDao(
						"Échec de la suppression du compte de l'utilisateur, aucune ligne modifiée dans la table.");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean Claim(Person pers, String claiming, String subject, String emailDest) {
		// TODO Auto-generated method stub
		return Invitation(pers, subject, claiming, emailDest, "maildereclamation");
	}

	@Override
	public Boolean Search(ArrayList<String> Params, String KeyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<ArrayList<String>, ArrayList<String>> Historique(Person utilisateur, Boolean isSucceed,
			Integer nbreConnexions) {

		// TODO Auto-generated method stub
		return Historique(utilisateur, isSucceed, "");

	}

	@SuppressWarnings("null")
	private Map<ArrayList<String>, ArrayList<String>> Historique(Person utilisateur, Boolean isSucceed,
			Object... objects) {
		Connection connexion = null;
		Statement Statement = null;
		ResultSet resultSet = null;
		ArrayList<String> listeConnexion = new ArrayList<String>();
		ArrayList<String> listeDeconnexion = new ArrayList<String>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = connexion.createStatement();
			resultSet = Statement.executeQuery(
					"SELECT login_time, logout_time from connection where person_id=" + utilisateur.getId() + " ");
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet != null) {
				while (resultSet.next()) {
					isSucceed = true;
					listeConnexion.add(resultSet.getTimestamp("login_time").toString());
					listeDeconnexion.add(resultSet.getTimestamp("logout_time").toString());
				}
			} else {
				throw new ExceptionDao("Pas d'enregistrement!");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, Statement, connexion);
		}
		Map<ArrayList<String>, ArrayList<String>> map = null;
		map.put(listeConnexion, listeDeconnexion);
		return map;
	}

	@Override
	public Boolean Invite(Person utilisateur, String e_mailAddress) {
		// TODO Auto-generated method stub
		String i = "";
		String c = "";
		return Invitation(utilisateur, i, c, e_mailAddress, "");
	}

	private Boolean Invitation(Person utilisateur, String subject, String content, String e_mailAddress,
			String copyDest) {
		subject = "Invitation à utiliser PaPfood";
		copyDest = utilisateur.getEmail();
		content = utilisateur.getSurname() + " " + utilisateur.getName()
				+ " vous invite à utiliser la plate_forme de partage communautaire PaPfood ";
		boolean isSucceed = UseMail.sendMessage(utilisateur.getEmail(), subject, content, e_mailAddress, copyDest);
		return isSucceed;
	}
}