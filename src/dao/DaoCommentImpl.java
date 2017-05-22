package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Comment;

public class DaoCommentImpl implements CommentDao {
	private UsineDao daoFactory;

	public DaoCommentImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean CommentProduct(Long idAuthor, Long IdProduct, String comment) {
		// TODO Auto-generated method stub
		boolean isSucceed = true;
		return CommentProduct(idAuthor, IdProduct, comment, isSucceed);
	}

	@SuppressWarnings("resource")
	public Boolean CommentProduct(Long idAuthor, Long IdProduct, String comment, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		Integer id = null;
		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. Enregistrement du commentaire...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertComments(),
					true, idAuthor, comment);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				isSucceed = false;
				throw new ExceptionDao("Échec de l'ajout du commentaire, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				Statement Statement2 = connexion.createStatement();
				ResultSet resultSet2 = null;
				resultSet2 = Statement2
						.executeQuery("SELECT id  FROM comments where id=(select Max(id) from comments)");
				if (resultSet2.next()) {
					id = resultSet2.getInt("id");
				}
			} else {
				isSucceed = false;
				throw new ExceptionDao("Échec de la création du commentaire en base, aucun ID auto-généré retourné.");
			}

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getOraclesqlInsertCommentsProduct(), true, id, IdProduct);
			statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				isSucceed = false;
				throw new ExceptionDao(
						"Échec de l'ajout de l'enregistrement association, aucune ligne ajoutée dans la table.");

			}
		} catch (SQLException e) {
			isSucceed = false;
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean CommentService(Long idAuthor, Long IdService, String comment) {
		// TODO Auto-generated method stub
		boolean isSucceed = true;
		return CommentProduct(idAuthor, IdService, comment, isSucceed);
	}

	@SuppressWarnings("resource")
	public Boolean CommentService(Long idAuthor, Long IdService, String comment, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		Long id = null;
		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. Enregistrement du commentaire...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertComments(),
					true, idAuthor, comment);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				isSucceed = false;
				throw new ExceptionDao("Échec de l'ajout du commentaire, aucune ligne ajoutée dans la table.");
			}
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				Statement Statement2 = connexion.createStatement();
				ResultSet resultSet2 = null;
				resultSet2 = Statement2
						.executeQuery("SELECT id  FROM comments where id=(select Max(id) from comments)");
				if (resultSet2.next()) {
					id = resultSet2.getLong("id");
				}
			} else {
				isSucceed = false;
				throw new ExceptionDao("Échec de la création du commentaire en base, aucun ID auto-généré retourné.");
			}

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getOraclesqlInsertCommentsService(), true, id, IdService);
			statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				isSucceed = false;
				throw new ExceptionDao(
						"Échec de l'ajout de l'enregistrement association, aucune ligne ajoutée dans la table.");

			}
		} catch (SQLException e) {
			isSucceed = false;
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
		return isSucceed;
	}

	@Override
	public Boolean modifiyComment(beans.Comment comment) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return modifyComments(comment, isSucceed);
	}

	private Boolean modifyComments(beans.Comment comment, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlUpdateComments(), false,
					comment.getContent(), comment.getId());
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du commentaire");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean deleteComment(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return deleteComment(Id, isSucceed);
	}

	private Boolean deleteComment(Long Id, Boolean isSucceed) {
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
			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteComments(), false,
					Id);
			int statut = Statement.executeUpdate();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du commentaire");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public ArrayList<Comment> selectCommentsByIdProduct(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectCommentsByIdProduct(Id, isSucceed, limit, offset);
	}

	private ArrayList<Comment> selectCommentsByIdProduct(Long Id, Boolean isSucceed, Long limit, Long offset) {
		// TODO Auto-generated method stub
		ArrayList<Long> listIdCommentsProduct = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Comment> commentResults = new ArrayList<Comment>();
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCommentProduct(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			while (resultSet.next()) {
				isSucceed = true;
				listIdCommentsProduct.add(resultSet.getLong("id_comment"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		for (int i = 0; i < listIdCommentsProduct.size(); i++) {

			try {
				/* Récupération d'une connexion depuis la Factory */
				connexion = daoFactory.getConnection();
				/*
				 * Préparation de la requête avec les objets passés en arguments
				 * (ici, uniquement une adresse email) et exécution.
				 */
				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getMysqlSelectCommentById(), false, Id);
				resultSet = preparedStatement.executeQuery();
				/*
				 * Parcours de la ligne de données retournée dans le ResultSet
				 */
				while (resultSet.next()) {
					isSucceed = true;
					commentResults.add(Map(resultSet));
				}
			} catch (SQLException e) {
				throw new ExceptionDao(e);
			} finally {
				fermeturesSilencieuses(resultSet, preparedStatement, connexion);
			}
		}
		return commentResults;

	}

	@Override
	public ArrayList<Comment> selectCommentsByIdService(Long Id, Long limit, Long offset) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;

		return selectCommentsByIdService(Id, isSucceed, limit, offset);
	}

	private ArrayList<Comment> selectCommentsByIdService(Long Id, Boolean isSucceed, Long limit, Long offset) {
		// TODO Auto-generated method stub

		ArrayList<Long> listIdCommentsProduct = new ArrayList<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Comment> commentResults = new ArrayList<Comment>();
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCommentService(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			while (resultSet.next()) {
				isSucceed = true;
				listIdCommentsProduct.add(resultSet.getLong("id_comment"));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		for (int i = 0; i < listIdCommentsProduct.size(); i++) {

			try {
				/* Récupération d'une connexion depuis la Factory */
				connexion = daoFactory.getConnection();
				/*
				 * Préparation de la requête avec les objets passés en arguments
				 * (ici, uniquement une adresse email) et exécution.
				 */
				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getMysqlSelectCommentById(), false, Id);
				resultSet = preparedStatement.executeQuery();
				/*
				 * Parcours de la ligne de données retournée dans le ResultSet
				 */
				while (resultSet.next()) {
					isSucceed = true;
					commentResults.add(Map(resultSet));
				}
			} catch (SQLException e) {
				throw new ExceptionDao(e);
			} finally {
				fermeturesSilencieuses(resultSet, preparedStatement, connexion);
			}
		}
		return commentResults;
	}

	private Comment Map(ResultSet result) throws SQLException {
		Comment comment = new Comment();
		comment.setId(result.getLong("Id"));
		comment.setAuthor(result.getLong("id_author"));
		comment.setDatePosted(result.getDate("date_posted"));
		comment.setContent(result.getString("content"));
		return comment;
	}
}
