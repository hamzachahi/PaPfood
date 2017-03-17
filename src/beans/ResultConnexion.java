package beans;

import java.sql.Connection;

public class ResultConnexion {
	public static Connection connection;
	public static boolean succeed;

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		ResultConnexion.connection = connection;
	}

	public static boolean isSucceed() {
		return succeed;
	}

	public static void setSucceed(boolean succeed) {
		ResultConnexion.succeed = succeed;
	}

}
