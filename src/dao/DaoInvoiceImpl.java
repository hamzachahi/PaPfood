package dao;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Invoice;
import beans.Product;

public class DaoInvoiceImpl implements InvoiceDao {

	private UsineDao daoFactory;

	public DaoInvoiceImpl(UsineDao daoFactory) {

		this.daoFactory = daoFactory;
	}

	@Override
	public boolean addInvoice(Invoice facture) {
		Boolean isSucceed = false;
		return addInvoice(facture, isSucceed);
	}

	private boolean addInvoice(Invoice facture, Boolean isSucceed) {

		return isSucceed;
	}

	@Override
	public boolean removeInvoice(Invoice facture) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Invoice findById(Long id) {

		boolean isSucceed = false;
		return findInvoiceById(RequestRepository.getMysqlSelectInvoiceById(), isSucceed, id);

	}

	private Invoice findInvoiceById(String sql, Boolean isSucceed, Object... objets) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Invoice facture = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			System.out.println("Connexion récupérée!");

			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			System.out.println("Requête executée!");
			/* Parcours de la ligne de données retournée dans le ResultSet */
			if (resultSet.next()) {
				isSucceed = true;
				facture = map(resultSet);
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return facture;
	}

	@Override
	public ArrayList<Invoice> findAll(Long limit, Long offset) {
		boolean isSucceed = false;
		return findAllInvoice(RequestRepository.getMysqlSelectAllInvoice(), isSucceed, limit, offset);
	}

	private ArrayList<Invoice> findAllInvoice(String sql, Boolean isSucceed, Object... objets) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Invoice> factures = new ArrayList<>();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement une adresse email) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql, false, objets);
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données retournée dans le ResultSet */
			while (resultSet.next()) {
				isSucceed = true;
				factures.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new ExceptionDao(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return factures;
	}

	private static Invoice map(ResultSet resultSet) throws SQLException {

		Invoice facture = new Invoice();

		return facture;

	}

}
