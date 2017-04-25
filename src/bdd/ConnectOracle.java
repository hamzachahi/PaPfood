package bdd;

import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Administrator;
import dao.ExceptionDao;
import dao.RequestRepository;

public class ConnectOracle {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver O.K.");

			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "papfood";
			String passwd = "yummyshop";

			Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			PreparedStatement preparedStatement = null;
			ResultSet valeursAutoGenerees = null;
			Administrator utilisateur = new Administrator();
			utilisateur.setPassword("maf", false);
			utilisateur.setName("maf", false);
			utilisateur.setSurname("maf", false);
			utilisateur.setEmail("maf@maf.com", false);
			utilisateur.setFunction("admin");
			utilisateur.setPrivateKey("20170410MM021419");

			try {
				System.out.println("connexion réussie. Création de personne...");
				preparedStatement = initialisationRequetePreparee(conn, RequestRepository.getOraclesqlInsertPerson(),
						true, utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getName(), utilisateur.getFunction(), utilisateur.getPrivateKey());
				int statut = preparedStatement.executeUpdate();
				if (statut == 0) {
					throw new ExceptionDao(
							"Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
				}
				valeursAutoGenerees = preparedStatement.getGeneratedKeys();				
				/*if (valeursAutoGenerees.next()) {
					utilisateur.setId(valeursAutoGenerees.getLong(1), false);
				} else {
					throw new ExceptionDao(
							"Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
				}*/
			} catch (SQLException e) {
				throw new ExceptionDao(e);
			} finally {
				fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}