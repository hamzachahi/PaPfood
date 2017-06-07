package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Post;

public class DaoPostImpl implements PostDao {
	private UsineDao daoFactory;

	public DaoPostImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean Post( Long IdAuthor, String content, String Title) {
		// TODO Auto-generated method stub
		boolean isSucceed = true;
		return Post(IdAuthor, content, Title, isSucceed);
	}

	private Boolean Post(Long IdAuthor, String content, String Title, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. Enregistrement du postaire...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlInsertPost(), true,
					IdAuthor, content, Title);
			int statut = preparedStatement.executeUpdate();
			if (statut != 0) {
				isSucceed = false;
				throw new ExceptionDao("Échec de l'ajout du postaire, aucune ligne ajoutée dans la table.");
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
	public Boolean modifiyPost(Long Id, String content, String Title) {
		// TODO Auto-generated method stub
		boolean isSucceed = false;
		return modifyPosts(Id, content, Title, isSucceed);
	}

	private Boolean modifyPosts(Long Id, String content, String Title, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();

			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlUpdatePosts(), false,
					content, Title, Id);
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la modification du postaire");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public Boolean deletePost(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return deletePost(Id, isSucceed);
	}

	private Boolean deletePost(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement Statement = null;
		try {
			connexion = daoFactory.getConnection();

			Statement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlDeletePost(), false,
					Id);
			int statut = Statement.executeUpdate();
			if (statut != 0) {
				isSucceed = true;
			} else {
				throw new ExceptionDao("échec de la suppression du postaire");

			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(Statement, connexion);
		}

		return isSucceed;
	}

	@Override
	public ArrayList<Post> selectPostsByIdAuthor(Long Id, Integer finalf, Integer startf) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectPostsByIdAuthor(Id, isSucceed, finalf, startf);
	}

	private ArrayList<Post> selectPostsByIdAuthor(Long Id, Boolean isSucceed, Integer limit, Integer offset) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Post> postResults = new ArrayList<Post>();
		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectPostsByIdAuthor(), false, Id, limit, offset);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				isSucceed = true;
				postResults.add(Map(resultSet));
			}
			
			
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return postResults;

	}

	private Post Map(ResultSet result) throws SQLException {
		Post post = new Post();
		post.setId(result.getLong("Id"));
		post.setIdAuthor(result.getLong("id_author"));
		post.setDatePosted(result.getDate("date_posted"));
		post.setContent(result.getString("content"));
		post.setTitle(result.getString("title"));

		return post;
	}

	@Override
	public Long selectNbrePostsByIdAuthor(Long IdAuthor) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectNbrePostsByIdAuthor(IdAuthor, isSucceed);
	}

	private Long selectNbrePostsByIdAuthor(Long IdAuthor, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Long nbre = (long) 0;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					RequestRepository.getMysqlSelectCountPostByIdAuthor(), false, IdAuthor);
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
	public Post selectPostById(Long Id) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return selectPostById(Id, isSucceed);
	}

	private Post selectPostById(Long Id, Boolean isSucceed) {
		// TODO Auto-generated method stub
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Post post = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getMysqlSelectPostById(),
					false, Id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isSucceed = true;
				post = Map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return post;
	}
}
