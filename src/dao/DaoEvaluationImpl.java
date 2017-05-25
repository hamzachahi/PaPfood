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
	public Boolean Evaluate(Long IdPerson, Long Jury, Double Note, String Comments) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return Evaluate(IdPerson, Jury, Note, Comments, isSucceed);
	}

	private Boolean Evaluate(Long IdPerson, Long Jury, Double Note, String Comments, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
		
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertEvaluation(),
					false, IdPerson, Jury, Note, Comments);
			int statut = Statement.executeUpdate();
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
	public Boolean modifyEvaluation(Long Id, Double Note, String Comments) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return modifyEvaluation(Id, Note, Comments, isSucceed);
	}

	private Boolean modifyEvaluation(Long Id, Double Note, String Comments, Boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();
			
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdateEvaluation(),
					false, Note, Comments, Id);
			int statut = Statement.executeUpdate();
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
			connexion = daoFactory.getConnection();			
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeleteEvaluation(),
					false, Id);
			int statut = Statement.executeUpdate();
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
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlSelectMyEvaluationByIdPerson(),
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
		eval.setDate_posted(resultSet.getDate("date_posted"));

		return eval;
	}
	@Override
	public Long selectNbreEvaluationByIdPerson(Long IdPerson) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectNbreEvaluationByIdPerson(IdPerson, isSucceed);
	}

	private Long selectNbreEvaluationByIdPerson(Long IdPerson, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountEvaluationByIdPerson(), false, IdPerson);
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
	public Double selectMoyenneEvaluationByIdPerson(Long IdPerson) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectMoyenneEvaluationByIdPerson(IdPerson, isSucceed);
	}

	private Double selectMoyenneEvaluationByIdPerson(Long IdPerson, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Double moyenne = 20.0;
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectMoyenneEvaluationByIdPerson(), false, IdPerson);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				moyenne = resultSet.getDouble("moy");
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return moyenne;
	}
}
