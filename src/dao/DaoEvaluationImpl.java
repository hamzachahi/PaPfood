package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	public Boolean deleteEvaluation(Evaluation evaluation) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return deleteEvaluation(evaluation, isSucceed);
	}

	private Boolean deleteEvaluation(Evaluation evaluation, Boolean isSucceed) {
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
					false, evaluation.getId());
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
}
