package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMainOracle {
	 
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver O.K.");

			String url = "jdbc:oracle:thin:@localhost:1521:PROJET";
			String user = "system";
			String passwd = "Dbamanager1";

		@SuppressWarnings("unused")
		Connection conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("Connexion effective !");
			
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
