package dao;

public class RequestRepository {

	private static final String SQL_SE_TROUVER_PAR_EMAIL = "SELECT Id, name, surname, profession, email FROM registered_client WHERE email = ?";
	private static final String SQL_INSERT_ADMINISTRATOR = "INSERT INTO administrator (email, password, name, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_VISITOR = "INSERT INTO visitor (email, password, name, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_USER = "INSERT INTO registered_client (email, password, name, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_COMMAND = "INSERT INTO command (code,id_customer,date_commande) VALUES(?,?,NOW())";
	private static final String SQL_INSERT_ARTISAN = "INSERT INTO artisan (email, password, name, date_inscription) VALUES (?, ?, ?, NOW())";
	private static final String SQL_INSERT_CONNEXION = "INSERT INTO connexion (login_time, user_id, user_id_ip_address, user_type) VALUES (?, ?, ?, ?)";
	private static final String SQL_INSERT_COOKING_RECIPE = "INSERT INTO cooking_recipe (code, name, description, price, main_image) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_FOOD_DISH = "INSERT INTO food_dish (code, name, description, price, main_image) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_INVOICE = "INSERT INTO invoice (code_invoice, creation_date, type, total_price, person_name, society_name, phone_number, website, header_message, footer_message, legal_message, person_address, destination_address) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_MEAL = "INSERT INTO meal (code, name, description, price, main_image) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_PRODUCT_COMPONENT = "INSERT INTO product_component (id_componed, id_component) VALUES (?, ?)";
	private static final String SQL_INSERT_SERVICE = "INSERT INTO service (code, name, description, price, main_image) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_SELECT_ALL = "SELECT * FROM ? WHERE Id > ?";
	private static final String SQL_SELECT_ALL_IMPORTANT = "SELECT ?,?,?,? FROM ? WHERE Id > ?";
	
	private static final String SQL_INSERT_COMMAND_PRODUCT = "INSERT INTO command_product (id_command,id_product,quantity) VALUES(?,?,?))";
	private static final String SQL_UPDATE_ADMINISTRATOR = "UPDATE administrator SET name = ?, second_name=?, surname=?, second_surname=?, profession=?, date_inscription=?, password=?, email=?, phone_number=?, tel_number=?, facebook_id=?, twitter_id=?, instagram_id=?, linkedin_id=?, account_picture=?, street_number=?, street_name=?, city_name=?, country_name=?, postal_code=?, last_connexion=?  WHERE ID = ?";
    private static final String SQL_UPDATE_USER="UPDATE registered_client SET name = ?, second_name=?, surname=?, second_surname=?, profession=?, date_inscription=?, password=?, email=?, phone_number=?, tel_number=?, facebook_id=?, twitter_id=?, instagram_id=?, linkedin_id=?, account_picture=?, street_number=?, street_name=?, city_name=?, country_name=?, postal_code=?, last_connexion=?  WHERE ID = ?";
    private static final String SQL_UPDATE_VISITOR="UPDATE visitor SET name = ?, second_name=?, surname=?, second_surname=?, profession=?, date_inscription=?, password=?, email=?, phone_number=?, tel_number=?, facebook_id=?, twitter_id=?, instagram_id=?, linkedin_id=?, account_picture=?, street_number=?, street_name=?, city_name=?, country_name=?, postal_code=?, last_connexion=?  WHERE ID = ?";
    private static final String SQL_UPDATE_ARTISAN="UPDATE artisan SET name = ?, second_name=?, surname=?, second_surname=?, profession=?, date_inscription=?, password=?, email=?, phone_number=?, tel_number=?, facebook_id=?, twitter_id=?, instagram_id=?, linkedin_id=?, account_picture=?, street_number=?, street_name=?, city_name=?, country_name=?, postal_code=?, last_connexion=?  WHERE ID = ?";
	private static final String SQL_UPDATE_COMMAND = "UPDATE command SET code=?, id_customer = ?, date_commande=?, date_livraison=? WHERE ID = ?";
	private static final String SQL_UPDATE_COMMAND_PRODUCT = "UPDATE command_product SET id_command = ?, id_product=?, quantity=? WHERE ID = ?";
	private static final String SQL_UPDATE_CONNEXION = "UPDATE connexion SET logout_time = ? WHERE ID = ?";
	private static final String SQL_UPDATE_COOKING_RECIPE = "UPDATE cooking_recipe SET code = ?, name=?, description=?, price=?, main_image=? WHERE ID = ?";
	private static final String SQL_UPDATE_FOOD_DISH = "UPDATE food_dish SET code = ?, name=?, description=?, price=?, main_image=? WHERE ID = ?";
	private static final String SQL_UPDATE_INVOICE = "UPDATE invoice SET code_invoice = ?, delivered_date=?, type=?, total_price=?, person_name=?, society_name=?, phone_number=?, website=?, header_message=?, footer_message=?, legal_message=?, person_address=?, destinator_address=? WHERE ID = ?";
	private static final String SQL_UPDATE_MEAL = "UPDATE meal SET code = ?, name=?, description=?, price=?, main_image=? WHERE ID = ?";
	private static final String SQL_UPDATE_PRODUCT_COMPONENT = "UPDATE product_component SET id_componed = ?, id_component=? WHERE ID = ?";
	private static final String SQL_UPDATE_SERVICE = "UPDATE service SET code = ?, name=?, description=?, price=?, main_image=? WHERE ID = ?";

	private static final String SQL_DELETE_ADMINISTRATOR = "DELETE FROM administrator WHERE ID = ?";
	private static final String SQL_DELETE_VISITOR = "DELETE FROM visitor WHERE ID = ?";
	private static final String SQL_DELETE_USER = "DELETE FROM registered_client WHERE ID = ?";
	private static final String SQL_DELETE_COMMAND = "DELETE FROM command WHERE ID = ?";
	private static final String SQL_DELETE_ARTISAN = "DELETE FROM artisan WHERE ID = ?";
	private static final String SQL_DELETE_CONNEXION = "DELETE FROM connexion WHERE ID = ?";
	private static final String SQL_DELETE_COOKING_RECIPE = "DELETE FROM cooking_recipe WHERE ID = ?";
	private static final String SQL_DELETE_FOOD_DISH = "DELETE FROM food_dish WHERE ID = ?";
	private static final String SQL_DELETE_INVOICE = "DELETE FROM invoice WHERE ID = ?";
	private static final String SQL_DELETE_MEAL = "DELETE FROM meal WHERE ID = ?";
	private static final String SQL_DELETE_PRODUCT_COMPONENT = "DELETE FROM product_component WHERE ID = ?";
	private static final String SQL_DELETE_SERVICE = "DELETE FROM service WHERE ID = ?";

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

	public static String getSqlDeleteAdministrator() {
		return SQL_DELETE_ADMINISTRATOR;
	}

	public static String getSqlDeleteVisitor() {
		return SQL_DELETE_VISITOR;
	}

	public static String getSqlDeleteUser() {
		return SQL_DELETE_USER;
	}

	public static String getSqlDeleteCommand() {
		return SQL_DELETE_COMMAND;
	}

	public static String getSqlDeleteArtisan() {
		return SQL_DELETE_ARTISAN;
	}

	public static String getSqlDeleteConnexion() {
		return SQL_DELETE_CONNEXION;
	}

	public static String getSqlDeleteCookingRecipe() {
		return SQL_DELETE_COOKING_RECIPE;
	}

	public static String getSqlDeleteFoodDish() {
		return SQL_DELETE_FOOD_DISH;
	}

	public static String getSqlDeleteInvoice() {
		return SQL_DELETE_INVOICE;
	}

	public static String getSqlDeleteMeal() {
		return SQL_DELETE_MEAL;
	}

	public static String getSqlDeleteProductComponent() {
		return SQL_DELETE_PRODUCT_COMPONENT;
	}

	public static String getSqlDeleteService() {
		return SQL_DELETE_SERVICE;
	}

}
