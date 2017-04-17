package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Person;

public class DaoMessageImpl implements MessageDao{
	private UsineDao daoFactory;

	public DaoMessageImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean sendMessage(Person pers, Long id_Dest, String Message) {
		// TODO Auto-generated method stub
		boolean isSucceed = sendMessage(pers, Message, id_Dest, Message);
		return isSucceed;
	}

	private Boolean sendMessage(Person utilisateur, String message, Long id_dest, Object... objets) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Boolean succeed = false;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertMessage(),
					true, utilisateur.getId(), id_dest, message);
			int statut = preparedStatement.executeUpdate();

			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				succeed = true;
			} else {
				throw new ExceptionDao("Echec de l'envoi du message!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

		return succeed;
	}

	@Override
	public Boolean receiveMessage() {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return receiveMessage(isSucceed);
	}

	private Boolean receiveMessage(Boolean isSucceed) {
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
			int statut = Statement.executeUpdate(RequestRepository.getOraclesqlUpdateMessageReceiveDate());
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("la mise à jour de l'état du message est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean readMessage() {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return readMessage(isSucceed);
	}

	private Boolean readMessage(Boolean isSucceed) {
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
			int statut = Statement.executeUpdate(RequestRepository.getOraclesqlUpdateMessageReadDate());
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("La mise à jour de l'état du message est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean deleteMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
