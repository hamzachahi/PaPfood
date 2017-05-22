package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Message;

public class DaoMessageImpl implements MessageDao {
	private UsineDao daoFactory;

	public DaoMessageImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean sendMessage(Long Id, Long id_Dest, String Message) {
		// TODO Auto-generated method stub
		boolean isSucceed = sendMessage(Id, Message, id_Dest, Message);
		return isSucceed;
	}

	private Boolean sendMessage(Long Id, String message, Long id_dest, Object... objets) {
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
					true, Id, id_dest, message);
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
	public Boolean receiveMessage(Message message) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return receiveMessage(message, isSucceed);
	}

	private Boolean receiveMessage(Message message, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getOraclesqlUpdateMessageReceiveDate(), true, message.getId());
			int statut = preparedStatement.executeUpdate();

			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("la mise à jour de l'état du message est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean readMessage(Message message) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return readMessage(message, isSucceed);
	}

	private Boolean readMessage(Message message, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getOraclesqlUpdateMessageReadDate(), true, message.getId());
			int statut = preparedStatement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("La mise à jour de l'état du message est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean deleteMessage(Message message) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return deleteMessage(message, isSucceed);
	}

	private Boolean deleteMessage(Message message, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteMessage(),
					true, message.getId());
			int statut = preparedStatement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("La suppression du message est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public ArrayList<Message> receiveMyUnreadMessage(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return receiveMyUnreadMessage(Id, isSucceed, limit, offset);
	}

	private ArrayList<Message> receiveMyUnreadMessage(Long Id, Boolean isSucceed, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Message> messageResults = new ArrayList<Message>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectMyUnreadMessage(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			while (resultSet.next()) {
				isSucceed = true;
				messageResults.add(Map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return messageResults;
	}

	@Override
	public ArrayList<Message> receiveMyMessage(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return receiveMyMessage(Id, limit, offset, isSucceed);
	}

	private ArrayList<Message> receiveMyMessage(Long Id, Long limit, Long offset, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Message> messageResults = new ArrayList<Message>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlSelectMyMessage(),
					false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				messageResults.add(Map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return messageResults;
	}

	@Override
	public ArrayList<Message> getMySendMessage(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return getMySendMessage(Id, limit, offset, isSucceed);
	}

	public ArrayList<Message> getMySendMessage(Long Id, Long limit, Long offset, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Message> messageResults = new ArrayList<Message>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectMySendMessage(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				messageResults.add(Map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return messageResults;
	}

	private Message Map(ResultSet result) throws SQLException {
		Message message = new Message();
		message.setId(result.getLong("Id"));
		message.setContent(result.getString(result.getString("content")));
		message.setSender(result.getLong("id_sender"));
		message.setReceiver(result.getLong("id_receive"));
		message.setSentDate(result.getDate("sent_date"));
		message.setReceiveDate(result.getDate("receive_date"));
		message.setReadDate(result.getDate("read_date"));
		return null;
	}
}
