package dao;

public class RequestRepository {

	private static final String SQL_SE_TROUVER_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ?";
	private static final String SQL_INSERT_ADMINISTRATOR = "INSERT INTO administrator (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_VISITOR = "INSERT INTO visitor (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_USER = "INSERT INTO registered_client (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_COMMAND = "INSERT INTO command (Code,id_customer,date_commande) VALUES(?,?,NOW())";
	private static final String SQL_INSERT_ARTISAN = "INSERT INTO artisan (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_CONNEXION = "INSERT INTO connexion (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_COOKING_RECIPE = "INSERT INTO cooking_recipe (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_FOOD_DISH = "INSERT INTO food_dish (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_INVOICE = "INSERT INTO invoice (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_MEAL = "INSERT INTO meal (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_PRODUCT_COMPONENT = "INSERT INTO product_component (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_SERVICE = "INSERT INTO service (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";

	private static final String SQL_SELECT_ALL = "SELECT * FROM ? WHERE Id > ?";
	private static final String SQL_SELECT_ALL_IMPORTANT = "SELECT ?,?,?,? FROM ? WHERE Id > ?";
	
	private static final String SQL_INSERT_COMMAND_PRODUCT = "INSERT INTO command_product (code_command,code_product,quantity) VALUES(?,?,?))";
	private static final String SQL_UPDATE_ADMINISTRATOR = "UPDATE administrator SET SALARY = ? WHERE ID = ?";
    private static final String SQL_UPDATE_USER="UPDATE registered_client SET SALARY = ? WHERE ID = ?";
    private static final String SQL_UPDATE_VISITOR="UPDATE visitor SET SALARY = ? WHERE ID = ?";
    private static final String SQL_UPDATE_ARTISAN="UPDATE artisan SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_COMMAND = "UPDATE command SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_COMMAND_PRODUCT = "UPDATE command_product SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_CONNEXION = "UPDATE connexion SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_COOKING_RECIPE = "UPDATE cooking_recipe SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_FOOD_DISH = "UPDATE food_dish SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_INVOICE = "UPDATE invoice SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_MEAL = "UPDATE meal SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_PRODUCT_COMPONENT = "UPDATE product_component SET SALARY = ? WHERE ID = ?";
	private static final String SQL_UPDATE_SERVICE = "UPDATE service SET SALARY = ? WHERE ID = ?";

	public static String getSqlSeTrouverParEmail() {
		return SQL_SE_TROUVER_PAR_EMAIL;
	}

	public static String getSqlInsertAdministrator() {
		return SQL_INSERT_ADMINISTRATOR;
	}

	public static String getSqlInsertVisitor() {
		return SQL_INSERT_VISITOR;
	}

	public static String getSqlInsertUser() {
		return SQL_INSERT_USER;
	}

	public static String getSqlInsertCommand() {
		return SQL_INSERT_COMMAND;
	}

	public static String getSqlInsertArtisan() {
		return SQL_INSERT_ARTISAN;
	}

	public static String getSqlSelectAll() {
		return SQL_SELECT_ALL;
	}

	public static String getSqlInsertCommandProduct() {
		return SQL_INSERT_COMMAND_PRODUCT;
	}

	public static String getSqlInsertConnexion() {
		return SQL_INSERT_CONNEXION;
	}

	public static String getSqlInsertCookingRecipe() {
		return SQL_INSERT_COOKING_RECIPE;
	}

	public static String getSqlInsertFoodDish() {
		return SQL_INSERT_FOOD_DISH;
	}

	public static String getSqlInsertInvoice() {
		return SQL_INSERT_INVOICE;
	}

	public static String getSqlInsertMeal() {
		return SQL_INSERT_MEAL;
	}

	public static String getSqlInsertProductComponent() {
		return SQL_INSERT_PRODUCT_COMPONENT;
	}

	public static String getSqlInsertService() {
		return SQL_INSERT_SERVICE;
	}

	public static String getSqlSelectAllImportant() {
		return SQL_SELECT_ALL_IMPORTANT;
	}

	public static String getSqlUpdateAdministrator() {
		return SQL_UPDATE_ADMINISTRATOR;
	}

	public static String getSqlUpdateUser() {
		return SQL_UPDATE_USER;
	}

	public static String getSqlUpdateVisitor() {
		return SQL_UPDATE_VISITOR;
	}

	public static String getSqlUpdateArtisan() {
		return SQL_UPDATE_ARTISAN;
	}

	public static String getSqlUpdateCommand() {
		return SQL_UPDATE_COMMAND;
	}

	public static String getSqlUpdateCommandProduct() {
		return SQL_UPDATE_COMMAND_PRODUCT;
	}

	public static String getSqlUpdateConnexion() {
		return SQL_UPDATE_CONNEXION;
	}

	public static String getSqlUpdateCookingRecipe() {
		return SQL_UPDATE_COOKING_RECIPE;
	}

	public static String getSqlUpdateFoodDish() {
		return SQL_UPDATE_FOOD_DISH;
	}

	public static String getSqlUpdateInvoice() {
		return SQL_UPDATE_INVOICE;
	}

	public static String getSqlUpdateMeal() {
		return SQL_UPDATE_MEAL;
	}

	public static String getSqlUpdateProductComponent() {
		return SQL_UPDATE_PRODUCT_COMPONENT;
	}

	public static String getSqlUpdateService() {
		return SQL_UPDATE_SERVICE;
	}

}
