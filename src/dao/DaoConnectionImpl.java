package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Connexion;

public class DaoConnectionImpl implements ConnectionDao {
	private UsineDao daoFactory;

	public DaoConnectionImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean createConnexion(Long Id, String ipAddress, String personType) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return createConnexion(Id, ipAddress, personType, isSucceed);
	}

	private Boolean createConnexion(Long Id, String ipAddress, String personType, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		Boolean succeed = false;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertConnection(),
					true, Id, ipAddress, personType);
			int statut = preparedStatement.executeUpdate();
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
	public Boolean updateConnexion(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return updateConnexion(Id, isSucceed);
	}

	private Boolean updateConnexion(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateConnection(),
					true, Id);
			int statut = preparedStatement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("la mise à jour de l'état de la connexion est incomplète!");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Long getLastConnexionIdByIdConnected(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return getLastConnexionIdByIdConnected(Id, isSucceed);
	}

	private Long getLastConnexionIdByIdConnected(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery("select Max(id) as nb from connection c where person_id =" + Id);
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
	public Connexion getConnexionById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return getConnexionById(Id, isSucceed);
	}

	private Connexion getConnexionById(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		beans.Connexion conn = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectConnexionById(), false, Id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				conn = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return conn;
	}

	private Connexion map(ResultSet result) throws SQLException {
		Connexion conn = new Connexion();
		conn.setId(result.getLong("id"));
		conn.setIdConnected(result.getLong("person_id"));
		conn.setLogicAddress(result.getString("person_id_ip_address"));
		conn.setLoginTime(result.getDate("login_time"));
		conn.setLogoutTime(result.getDate("logout_time"));
		conn.setPersonT(result.getString("person_type"));
		return conn;
	}
}
