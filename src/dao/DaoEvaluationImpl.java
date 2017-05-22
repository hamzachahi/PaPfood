package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Evaluation;

public class DaoEvaluationImpl implements EvaluationDao {
	private UsineDao daoFactory;

	public DaoEvaluationImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean Evaluate(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return Evaluate(evaluation, isSucceed);
	}

	private Boolean Evaluate(Evaluation evaluation, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertEvaluation(),
					false, evaluation.getIdPerson(), evaluation.getIdJury(), evaluation.getNote(),
					evaluation.getComments());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de l'enregistrement de l'évaluation");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifyEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return modifyEvaluation(evaluation, isSucceed);
	}

	private Boolean modifyEvaluation(Evaluation evaluation, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlUpdateEvaluation(),
					false, evaluation.getIdPerson(), evaluation.getIdJury(), evaluation.getNote(),
					evaluation.getComments(), evaluation.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification de l'évaluation");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean deleteEvaluation(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return deleteEvaluation(Id, isSucceed);
	}

	private Boolean deleteEvaluation(Long Id, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteEvaluation(),
					false, Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression de l'évaluation");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public ArrayList<Evaluation> getMyEvaluation(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return getMyEvaluation(Id, isSucceed, limit, offset);
	}

	private ArrayList<Evaluation> getMyEvaluation(Long Id, Boolean isSucceed, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Evaluation> evaluationResults = new ArrayList<Evaluation>();

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlSelectMyMessage(),
					false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				evaluationResults.add(Map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return evaluationResults;
	}

	private Evaluation Map(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		Evaluation eval = new Evaluation();
		eval.setId(resultSet.getLong("Id"));
		eval.setIdJury(resultSet.getLong("id_jury"));
		eval.setIdPerson(resultSet.getLong("id_person"));
		eval.setNote(resultSet.getDouble("note"));
		eval.setComments(resultSet.getString("comments"));
		return eval;
	}
}
