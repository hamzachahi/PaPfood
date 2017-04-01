package bdd;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectOracle {
public static void main(String[] args) {      
  try {
    Class.forName("oracle.jdbc.OracleDriver");
    System.out.println("Driver O.K.");

    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    String user = "papfood";
    String passwd = "yummyshop";

    @SuppressWarnings("unused")
	Connection conn = DriverManager.getConnection(url, user, passwd);
    System.out.println("Connexion effective !");


   
  } catch (Exception e) {
    e.printStackTrace();
  }      
}
}