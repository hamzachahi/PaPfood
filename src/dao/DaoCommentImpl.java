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
	private Boolean CommentProduct(Long idAuthor, Long IdProduct, String comment, boolean isSucceed) {
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
			System.out.println("Valeur de l'Id : " + id);
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getOraclesqlInsertCommentsProduct(), true, id, IdProduct);
			statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				isSucceed = false;
				System.out.println(
						"Échec de l'ajout de l'enregistrement association, aucune ligne ajoutée dans la table.");
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
	public Boolean modifiyComment(Long Id, String content) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return modifyComments(Id,content, isSucceed);
	}

	private Boolean modifyComments(Long Id, String content, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();

			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlUpdateComments(), false,
					content, Id);
			int statut = Statement.executeUpdate();
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
			connexion = daoFactory.getConnection();

			Statement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlDeleteComments(), false,
					Id);
			int statut = Statement.executeUpdate();
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
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCommentProduct(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				isSucceed = true;
				listIdCommentsProduct.add(resultSet.getLong("id_comment"));
			}
			System.out.println("Talle de la liste d'Id : " + listIdCommentsProduct.size());
			for (int i = 0; i < listIdCommentsProduct.size(); i++) {

				connexion = daoFactory.getConnection();

				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getMysqlSelectCommentById(), false, listIdCommentsProduct.get(i));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					isSucceed = true;
					commentResults.add(Map(resultSet));
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
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
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCommentService(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				isSucceed = true;
				listIdCommentsProduct.add(resultSet.getLong("id_comment"));
			}
			System.out.println("Talle de la liste d'Id : " + listIdCommentsProduct.size());
			for (int i = 0; i < listIdCommentsProduct.size(); i++) {

				connexion = daoFactory.getConnection();
				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getMysqlSelectCommentById(), false, listIdCommentsProduct.get(i));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					isSucceed = true;
					commentResults.add(Map(resultSet));
				}
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return commentResults;
	}

	private Comment Map(ResultSet result) throws SQLException {
		Comment comment = new Comment();
		comment.setId(result.getLong("Id"));
		comment.setIdAuthor(result.getLong("id_author"));
		comment.setDatePosted(result.getDate("date_posted"));
		comment.setContent(result.getString("content"));
		return comment;
	}

	@Override
	public Long selectNbreCommentsByIdService(Long IdService) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectNbreCommentsByIdService(IdService, isSucceed);
	}

	private Long selectNbreCommentsByIdService(Long IdService, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountCommentsByIdService(), false, IdService);
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
	public Long selectNbreCommentsByIdProduct(Long IdProduct) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectNbreCommentsByIdProduct(IdProduct, isSucceed);
	}

	private Long selectNbreCommentsByIdProduct(Long IdProduct, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountCommentsByIdProduct(), false, IdProduct);
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
	public Comment selectCommentById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectCommentById(Id, isSucceed);
	}

	private Comment selectCommentById(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Comment comment = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlSelectCommentById(),
					false, Id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				comment = Map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return comment;
	}
}
