package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.MotherProduct;
import beans.Person;
import beans.Product;
import beans.Service;

public class DaoCommentImpl implements CommentDao {
	private UsineDao daoFactory;

	public DaoCommentImpl(UsineDao daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Boolean Comment(Person pers, MotherProduct mProduct, String comment) {
		// TODO Auto-generated method stub
		boolean isSucceed = true;
		return Comment(pers, mProduct, comment, isSucceed);
	}

	@SuppressWarnings("resource")
	public Boolean Comment(Person utilisateur, MotherProduct mProduct, String comment, boolean isSucceed) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;
		Integer id = null;
		try {
			connexion = daoFactory.getConnection();
			System.out.println("connexion réussie. Enregistrement du commentaire...");
			preparedStatement = initialisationRequetePreparee(connexion, RequestRepository.getOraclesqlInsertComments(),
					true, utilisateur.getId(), comment);
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
					System.out.println("Nouveau commentaire récupéré: " + utilisateur.toString());
				}
			} else {
				isSucceed = false;
				throw new ExceptionDao("Échec de la création du commentaire en base, aucun ID auto-généré retourné.");
			}
			if (mProduct.getClass().getSuperclass().getName().toString().equals(Service.class.getName().toString())) {
				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getOraclesqlInsertCommentsService(), true, id, mProduct.getId());
				statut = preparedStatement.executeUpdate();
				if (statut == 0) {
					isSucceed = false;
					throw new ExceptionDao(
							"Échec de l'ajout de l'enregistrement association, aucune ligne ajoutée dans la table.");

				}

			} else if (mProduct.getClass().getSuperclass().getName().toString()
					.equals(Product.class.getName().toString())) {
				preparedStatement = initialisationRequetePreparee(connexion,
						RequestRepository.getOraclesqlInsertCommentsProduct(), true, id, mProduct.getId());
				statut = preparedStatement.executeUpdate();
				if (statut == 0) {
					isSucceed = false;
					throw new ExceptionDao(
							"Échec de l'ajout de l'enregistrement association, aucune ligne ajoutée dans la table.");

				}
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
	public Boolean deleteComment(beans.Comment comment) {
		// TODO Auto-generated method stub
		Boolean isSucceed = false;
		return deleteComment(comment, isSucceed);
	}

	private Boolean deleteComment(beans.Comment comment, Boolean isSucceed) {
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
					comment.getId());
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
	public Boolean selectCommentsByIdPerson(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean selectCommentsByIdProduct(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean selectCommentsByIdService(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}
}
