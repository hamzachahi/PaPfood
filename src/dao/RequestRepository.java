package dao;

public class RequestRepository {
	// Début requête select
	private static final String MySQL_SE_TROUVER_PAR_ID = "SELECT * FROM person p WHERE id = ?";
	private static final String Oracle_SE_TROUVER_PAR_ID = "SELECT * FROM person p WHERE id = ?";
	private static final String MySQL_SE_TROUVER_PAR_EMAIL = "SELECT id, name, second_name, surname, second_surname, profession, date_inscription, password, email, phone_number, tel_number, facebook_id, twitter_id, instagram_id, linkedin_id, account_picture, street_number, street_name, city_name, country_name, postal_code, last_connection, function FROM person WHERE email = ?";
	private static final String OracleSQL_SE_TROUVER_PAR_EMAIL = "SELECT id, name, surname, profession, password, email FROM person WHERE email = ?";
	private static final String OracleSQL_SE_TROUVER_TOTALEMENT_PAR_EMAIL = "SELECT * FROM person WHERE email = ?";

	private static final String MySQL_SELECT_ALL = "SELECT * FROM ?";
	private static final String MySQL_SELECT_ALL_PRODUCT = "SELECT * FROM product p Limit ? offset ?";
	private static final String MySQL_SELECT_ALL_SERVICE = "SELECT * FROM service s Limit ? offset ?";
	private static final String MySQL_SELECT_ALL_COMMANDE = "SELECT * FROM commande c Limit ? offset ?";

	private static final String MySQL_SELECT_ALL_SERVICE_IMAGE_LINKS = "SELECT link FROM images_links_service WHERE id_service = ?";
	private static final String MySQL_SELECT_ALL_PRODUCT_IMAGE_LINKS = "SELECT link FROM images_links_product WHERE id_product = ?";
	private static final String MySQL_SELECT_ALL_PERSON_IMAGE_LINKS = "SELECT link FROM images_links_person WHERE id_person = ?";

	private static final String OracleSQL_LAST_ELEMENT = "SELECT *  FROM person where id=(select Max(id) person ?)";

	private static final String MySQL_SELECT_ALL_IMPORTANT = "SELECT ?,?,?,? FROM ? WHERE Id = ?";
	private static final String OracleSQL_SELECT_ALL_IMPORTANT = "SELECT ?,?,?,? FROM ? WHERE Id = ?";
	private static final String OracleSQL_SELECT_PRODUCT_BY_KEYWORD = "SELECT * FROM product WHERE name LIKE ?";
	private static final String OracleSQL_SELECT_SERVICE_BY_KEYWORD = "SELECT * FROM service WHERE name LIKE ?";

	private static final String MySQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM product p WHERE Id = ?";
	private static final String MySQL_SELECT_SERVICE_BY_ID = "SELECT * FROM service s WHERE Id = ?";
	private static final String MySQL_SELECT_INVOICE_BY_ID = "SELECT * from invoice i WHERE Id = ?";

	private static final String MySQL_SELECT_PRODUCT_BY_ID_PROVIDER = "SELECT * FROM product p WHERE id_provider = ? limit ? offset ?";
	private static final String MySQL_SELECT_SERVICE_BY_ID_PROVIDER = "SELECT * FROM service s WHERE id_provider = ? limit ? offset ?";

	private static final String MySQL_SELECT_MY_SEND_MESSAGE = "SELECT * FROM message m WHERE id_sender = ? limit ? offset ?";
	private static final String MySQL_SELECT_MY_MESSAGE = "SELECT * FROM message m WHERE id_receiver = ? limit ? offset ?";
	private static final String MySQL_SELECT_MY_UNREAD_MESSAGE = "SELECT * FROM message m WHERE read_date = null and id_receiver= ? limit ? offset ?";

	private static final String MySQL_SELECT_COMMENT_PRODUCT = "SELECT id_comment FROM comments_product cp WHERE id_product = ? limit ? offset ?";
	private static final String MySQL_SELECT_COMMENT_SERVICE = "SELECT id_comment FROM comments_service cs WHERE id_service = ? limit ? offset ?";

	private static final String MySQL_SELECT_COMMENT_BY_ID = "SELECT * FROM comments c WHERE Id = ?";

	// fin select
	// début count
	private static final String MySQL_SELECT_COUNT_PRODUCT = "select count(id) as nb from product p";
	private static final String MySQL_SELECT_COUNT_SERVICE = "select count(id) as nb from service s";

	private static final String MySQL_SELECT_COUNT_PRODUCT_BY_ID_PROVIDER = "select count(id) as nb from product p WHERE id_provider = ?";
	private static final String MySQL_SELECT_COUNT_SERVICE_BY_ID_PROVIDER = "select count(id) as nb from service s WHERE id_provider = ?";
	private static final String MySQL_SELECT_COUNT_COMMENTS_BY_ID_SERVICE = "select count(id) as nb from comments_service cs WHERE id_service = ?";
	private static final String MySQL_SELECT_COUNT_COMMENTS_BY_ID_PRODUCT = "select count(id) as nb from comments_product cs WHERE id_product = ?";

	// fin count
	// début insert

	private static final String MySQL_INSERT_PERSON = "INSERT INTO person (email, password, name, function, date_inscription) VALUES (?, ?, ?, ?, NOW())";
	private static final String OracleSQL_INSERT_PERSON = "INSERT INTO person (email, password, name, function, date_inscription) VALUES (?, ?, ?, ?, SYSTIMESTAMP)";

	private static final String MySQL_INSERT_EVALUATION = "INSERT INTO evaluation (id_person, id_jury, note, comments) VALUES (?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_EVALUATION = "INSERT INTO evaluation (id_person, id_jury, note, comments) VALUES (?, ?, ?, ?)";

	private static final String MySQL_INSERT_COMMANDE = "INSERT INTO commande (code,id_customer,date_ordering) VALUES(?,?,NOW())";
	private static final String OracleSQL_INSERT_COMMANDE = "INSERT INTO commande (code,id_customer,date_ordering) VALUES(?,?,SYSTIMESTAMP)";

	private static final String MySQL_INSERT_CONNECTION = "INSERT INTO connexion (login_time, person_id, person_id_ip_address, person_type) VALUES (?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_CONNECTION = "INSERT INTO connexion (login_time, person_id, person_id_ip_address, person_type) VALUES (?, ?, ?, ?)";

	private static final String MySQL_INSERT_PRODUCT = "INSERT INTO product (code, name, description, price, id_provider) VALUES (?, ?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_PRODUCT = "INSERT INTO product (code, name, description, price, id_provider) VALUES (?, ?, ?, ?, ?)";

	private static final String MySQL_INSERT_INVOICE = "INSERT INTO invoice (code_invoice, creation_date, delivered_date, type, total_price, header_message, footer_message, legal_message) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_INVOICE = "INSERT INTO invoice (code_invoice, creation_date, delivered_date, type, total_price, header_message, footer_message, legal_message) VALUES (?, SYSTIMESTAMP, ?, ?, ?, ?, ?, ?)";

	private static final String MySQL_INSERT_PRODUCT_COMPONENT = "INSERT INTO product_component (id_componed, id_component) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_PRODUCT_COMPONENT = "INSERT INTO product_component (id_componed, id_component) VALUES (?, ?)";

	private static final String MySQL_INSERT_PRODUCT_SERVICE = "INSERT INTO product_service (id_product, id_service) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_PRODUCT_SERVICE = "INSERT INTO product_service (id_product, id_service) VALUES (?, ?)";

	private static final String MySQL_INSERT_INVOICE_PERSON = "INSERT INTO invoice_person (id_invoice, id_person, person_name, person_address, person_surname) VALUES (?, ?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_INVOICE_PERSON = "INSERT INTO invoice_person (id_invoice, id_person, person_name, person_address, person_surname) VALUES (?, ?, ?, ?, ?)";

	private static final String MySQL_INSERT_INVOICE_PRODUCT = "INSERT INTO invoice_product (id_invoice, id_product) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_INVOICE_PRODUCT = "INSERT INTO invoice_product (id_invoice, id_product) VALUES (?, ?)";

	private static final String MySQL_INSERT_INVOICE_SERVICE = "INSERT INTO invoice_service (id_invoice, id_service) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_INVOICE_SERVICE = "INSERT INTO invoice_service (id_invoice, id_service) VALUES (?, ?)";

	private static final String MySQL_INSERT_SERVICE = "INSERT INTO service (code, name, description, price, main_image, id_provider) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String OracleSQL_INSERT_SERVICE = "INSERT INTO service (code, name, description, price, main_image, id_provider) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String MySQL_INSERT_SERVICE_PRODUCT = "INSERT INTO service_product (id_service, id_product) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_SERVICE_PRODUCT = "INSERT INTO service_product (id_service, id_product) VALUES (?, ?)";

	private static final String MySQL_INSERT_SERVICE_COMPONENT = "INSERT INTO service_component (id_service, id_component) VALUES (?, ?)";
	private static final String OracleSQL_INSERT_SERVICE_COMPONENT = "INSERT INTO service_component (id_service, id_component) VALUES (?, ?)";

	private static final String MySQL_INSERT_COMMANDE_PRODUCT = "INSERT INTO commande_product (id_commande,id_product,quantity) VALUES(?,?,?))";
	private static final String OracleSQL_INSERT_COMMANDE_PRODUCT = "INSERT INTO commande_product (id_commande,id_product,quantity) VALUES(?,?,?))";

	private static final String MySQL_INSERT_COMMANDE_SERVICE = "INSERT INTO commande_service (id_commande,id_service,quantity) VALUES(?,?,?))";
	private static final String OracleSQL_INSERT_COMMANDE_SERVICE = "INSERT INTO commande_service (id_commande,id_service,quantity) VALUES(?,?,?))";

	private static final String OracleSQL_INSERT_MESSAGE = "INSERT INTO message (id_sender, id_receiver, content, sent_date) VALUES ( ?, ?, ?, SYSTIMESTAMP)";
	private static final String OracleSQL_INSERT_COMMENTS = "INSERT INTO comments (id_author, date_posted, content) VALUES (?, NOW(), ?)";
	private static final String OracleSQL_INSERT_COMMENTS_PRODUCT = "INSERT INTO comments_product (id_comment, id_product) VALUES ( ?, ?)";
	private static final String OracleSQL_INSERT_COMMENTS_SERVICE = "INSERT INTO comments_service (id_comment, id_service) VALUES ( ?, ?)";
	private static final String MySQL_INSERT_IMAGE_PRODUCT_LINK = "INSERT INTO images_links_product (links, id_product) VALUES (?, ?)";
	private static final String MySQL_INSERT_IMAGE_SERVICE_LINK = "INSERT INTO images_links_service (links, id_service) VALUES (?, ?)";
	private static final String MySQL_INSERT_IMAGE_PERSON_LINK = "INSERT INTO images_links_person (links, utility, id_person) VALUES (?, ?, ?)";

	// fin insert
	// début update
	private static final String MySQL_UPDATE_PERSON = "UPDATE person SET name = ?, second_name=?, surname=?, second_surname=?, profession=?, email=?, phone_number=?, tel_number=?, facebook_id=?, twitter_id=?, instagram_id=?, linkedin_id=?, account_picture=?, street_number=?, street_name=?, city_name=?, country_name=?, postal_code=?, function=?  WHERE ID = ?";

	private static final String MySQL_UPDATE_COMMANDE = "UPDATE commande SET code=?, id_customer = ?, date_ordering=?, date_livraison=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_COMMANDE = "UPDATE commande SET code=?, id_customer = ?, date_ordering=?, date_livraison=? WHERE ID = ?";

	private static final String MySQL_UPDATE_EVALUATION = "UPDATE evaluation SET id_person=?, id_jury = ?, note=?, comments=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_EVALUATION = "UPDATE evaluation SET id_person=?, id_jury = ?, note=?, comments=? WHERE ID = ?";

	private static final String MySQL_UPDATE_COMMANDE_PRODUCT = "UPDATE commande_product SET id_commande = ?, id_product=?, quantity=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_COMMANDE_PRODUCT = "UPDATE commande_product SET id_commande = ?, id_product=?, quantity=? WHERE ID = ?";

	private static final String MySQL_UPDATE_COMMANDE_SERVICE = "UPDATE commande_service SET id_commande = ?, id_service=?, quantity=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_COMMANDE_SERVICE = "UPDATE commande_service SET id_commande = ?, id_service=?, quantity=? WHERE ID = ?";

	private static final String MySQL_UPDATE_SERVICE_PRODUCT = "UPDATE service_product SET id_service = ?, id_product=? WHERE id_service = ? and id_product = ?";
	private static final String OracleSQL_UPDATE_SERVICE_PRODUCT = "UPDATE service_product SET id_service = ?, id_product=? WHERE id_service = ? and id_product = ?";

	private static final String MySQL_UPDATE_SERVICE_COMPONENT = "UPDATE service_component SET id_service = ?, id_component=? WHERE id_service = ? and id_component = ?";
	private static final String OracleSQL_UPDATE_SERVICE_COMPONENT = "UPDATE service_component SET id_service = ?, id_component=? WHERE id_service = ? and id_component = ?";

	private static final String MySQL_UPDATE_CONNECTION = "UPDATE connection SET logout_time = ? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_CONNECTION = "UPDATE connection SET logout_time = ? WHERE ID = ?";

	private static final String MySQL_UPDATE_PRODUCT = "UPDATE product SET code = ?, name=?, description=?, price=?, main_image=?, id_provider= ? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_PRODUCT = "UPDATE product SET code = ?, name=?, description=?, price=?, main_image=?, id_provider= ? WHERE ID = ?";

	private static final String MySQL_UPDATE_INVOICE = "UPDATE invoice SET code_invoice = ?, date_ordering=?, delivered_date=?, type=?, total_price=?, header_message=?, footer_message=?, legal_message=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_INVOICE = "UPDATE invoice SET code_invoice = ?, date_ordering=?, delivered_date=?, type=?, total_price=?, header_message=?, footer_message=?, legal_message=? WHERE ID = ?";

	private static final String MySQL_UPDATE_PRODUCT_COMPONENT = "UPDATE product_component SET id_componed = ?, id_component=? WHERE id_componed = ? and id_component = ?";
	private static final String OracleSQL_UPDATE_PRODUCT_COMPONENT = "UPDATE product_component SET id_componed = ?, id_component=? WHERE id_componed = ? and id_component = ?";

	private static final String MySQL_UPDATE_PRODUCT_SERVICE = "UPDATE product_service SET id_product = ?, id_service=? WHERE id_product = ? and id_service = ?";
	private static final String OracleSQL_UPDATE_PRODUCT_SERVICE = "UPDATE product_service SET id_product = ?, id_service=? WHERE id_product = ? and id_service = ?";

	private static final String MySQL_UPDATE_SERVICE = "UPDATE service SET code = ?, name=?, description=?, price=?, main_image=?, id_provider= ? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_SERVICE = "UPDATE service SET code = ?, name=?, description=?, price=?, main_image=?, id_provider = ? WHERE ID = ?";

	private static final String MySQL_UPDATE_INVOICE_PERSON = "UPDATE invoice_person SET id_invoice = ?, id_person=?, person_name=?, person_address=?, person_surname=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_INVOICE_PERSON = "UPDATE invoice_person SET id_invoice = ?, id_person=?, person_name=?, person_address=?, person_surname=? WHERE ID = ?";

	private static final String MySQL_UPDATE_INVOICE_PRODUCT = "UPDATE invoice_product SET id_invoice = ?, id_product=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_INVOICE_PRODUCT = "UPDATE invoice_product SET id_invoice = ?, id_product=? WHERE ID = ?";

	private static final String MySQL_UPDATE_INVOICE_SERVICE = "UPDATE invoice_person SET id_invoice = ?, id_service=? WHERE ID = ?";
	private static final String OracleSQL_UPDATE_INVOICE_SERVICE = "UPDATE invoice_person SET id_invoice = ?, id_service=? WHERE ID = ?";

	private static final String OracleSQL_UPDATE_MESSAGE_RECEIVE_DATE = "UPDATE message set receive_date=SYSTIMESTAMP WHERE ID = ?";
	private static final String OracleSQL_UPDATE_MESSAGE_READ_DATE = "UPDATE message set read_date=SYSTIMESTAMP WHERE ID = ?";

	private static final String OracleSQL_UPDATE_COMMANDE_STATE = "UPDATE commande SET state=1 WHERE ID= ?";
	private static final String OracleSQL_UPDATE_COMMENTS = "UPDATE comments SET content = ?, date_posted = NOW() WHERE ID= ?";

	private static final String MySQL_UPDATE_IMAGE_SERVICE_LINK = "UPDATE images_links_service SET link = ?  WHERE link = ? and id_service = ?";
	private static final String MySQL_UPDATE_IMAGE_PRODUCT_LINK = "UPDATE images_links_product SET link = ?  WHERE link = ? and id_product = ?";
	private static final String MySQL_UPDATE_IMAGE_PERSON_LINK = "UPDATE images_links_person SET link = ?  WHERE link = ? and id_person = ?";

	// fin update
	// début update

	private static final String MySQL_DELETE_PERSON = "DELETE FROM person WHERE ID = ?";
	private static final String OracleSQL_DELETE_PERSON = "DELETE FROM person WHERE ID = ?";

	private static final String MySQL_DELETE_COMMANDE = "DELETE FROM commande WHERE ID = ?";
	private static final String OracleSQL_DELETE_COMMANDE = "DELETE FROM commande WHERE ID = ?";

	private static final String MySQL_DELETE_CONNECTION = "DELETE FROM connection WHERE ID = ?";
	private static final String OracleSQL_DELETE_CONNECTION = "DELETE FROM connection WHERE ID = ?";

	private static final String MySQL_DELETE_PRODUCT = "DELETE FROM product WHERE ID = ?";
	private static final String OracleSQL_DELETE_PRODUCT = "DELETE FROM product WHERE ID = ?";

	private static final String MySQL_DELETE_INVOICE = "DELETE FROM invoice WHERE ID = ?";
	private static final String OracleSQL_DELETE_INVOICE = "DELETE FROM invoice WHERE ID = ?";

	private static final String MySQL_DELETE_PRODUCT_COMPONENT = "DELETE FROM product_component WHERE id_componed = ? and id_component = ?";
	private static final String OracleSQL_DELETE_PRODUCT_COMPONENT = "DELETE FROM product_component WHERE id_componed = ? and id_component = ?";

	private static final String MySQL_DELETE_PRODUCT_SERVICE = "DELETE FROM product_service WHERE id_product = ? and id_service = ?";
	private static final String OracleSQL_DELETE_PRODUCT_SERVICE = "DELETE FROM product_service WHERE id_product = ? and id_service = ?";

	private static final String MySQL_DELETE_COMMANDE_PRODUCT = "DELETE FROM commande_product WHERE id_commande = ? and id_product= ?";
	private static final String OracleSQL_DELETE_COMMANDE_PRODUCT = "DELETE FROM commande_product WHERE id_commande = ? and id_product= ?";

	private static final String MySQL_DELETE_SERVICE_PRODUCT = "DELETE FROM service_product WHERE id_service = ? and id_product = ?";
	private static final String OracleSQL_DELETE_SERVICE_PRODUCT = "DELETE FROM service_product WHERE id_service = ? and id_product = ?";

	private static final String MySQL_DELETE_SERVICE_COMPONENT = "DELETE FROM service_component WHERE id_service = ? and id_component = ?";
	private static final String OracleSQL_DELETE_SERVICE_COMPONENT = "DELETE FROM service_component WHERE id_service = ? and id_component = ?";

	private static final String MySQL_DELETE_COMMANDE_SERVICE = "DELETE FROM commande_service WHERE id_commande = ? and id_service= ?";
	private static final String OracleSQL_DELETE_COMMANDE_SERVICE = "DELETE FROM commande_service WHERE id_commande = ? and id_service= ?";

	private static final String MySQL_DELETE_INVOICE_SERVICE = "DELETE FROM invoice_service WHERE ID = ?";
	private static final String OracleSQL_DELETE_INVOICE_SERVICE = "DELETE FROM invoice_service WHERE ID = ?";

	private static final String MySQL_DELETE_INVOICE_PRODUCT = "DELETE FROM invoice_product WHERE ID = ?";
	private static final String OracleSQL_DELETE_INVOICE_PRODUCT = "DELETE FROM invoice_product WHERE ID = ?";

	private static final String MySQL_DELETE_INVOICE_PERSON = "DELETE FROM invoice_person WHERE ID = ?";
	private static final String OracleSQL_DELETE_INVOICE_PERSON = "DELETE FROM invoice_person WHERE ID = ?";

	private static final String MySQL_DELETE_SERVICE = "DELETE FROM service WHERE ID = ?";
	private static final String OracleSQL_DELETE_SERVICE = "DELETE FROM service WHERE ID = ?";

	private static final String MySQL_DELETE_EVALUATION = "DELETE FROM evaluation WHERE ID = ?";
	private static final String OracleSQL_DELETE_EVALUATION = "DELETE FROM evaluation WHERE ID = ?";
	private static final String OracleSQL_DELETE_COMMENTS = "DELETE FROM comments WHERE ID = ?";
	private static final String OracleSQL_DELETE_COMMENTS_PRODUCT = "DELETE FROM comments_product WHERE ID = ?";
	private static final String OracleSQL_DELETE_COMMENTS_SERVICE = "DELETE FROM comments_service WHERE ID = ?";
	private static final String OracleSQL_DELETE_MESSAGE = "DELETE FROM message WHERE ID = ?";

	private static final String OracleSQL_SELECT_FROM_COMMANDE_SERVICE = "SELECT * from commande_service where id_commande = ?";
	private static final String OracleSQL_SELECT_FROM_COMMANDE_PRODUCT = " SELECT * from commande_product where id_commande = ?";
	private static final String OracleSQL_SELECT_FROM_COMMANDE_BY_CUSTOMER = "SELECT * from commande WHERE id_customer = ?";
	private static final String OracleSQL_SELECT_FROM_COMMANDE_BY_ID = "SELECT * from commande WHERE id = ?";
	private static final String OracleSQL_SELECT_FROM_PRODUCT_SERVICE = "SELECT * from product_service where id_product = ?";
	private static final String OracleSQL_SELECT_FROM_SERVICE_COMPONENT = "SELECT * from service_component where id_service = ?";
	private static final String OracleSQL_SELECT_FROM_SERVICE_PRODUCT = "SELECT * from service_product where id_service = ?";
	private static final String OracleSQL_SELECT_FROM_PRODUCT_COMPONENT = "SELECT * from product_component where id_componed = ?";
	private static final String OracleSQL_SELECT_SERVICE_BY_ID = "SELECT * FROM service WHERE ID = ?";
	private static final String OracleSQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE ID = ?";
	private static final String MySQL_DELETE_IMAGE_PRODUCT_LINK = "DELETE FROM images_links_product WHERE link = ? and id_product = ?";
	private static final String MySQL_DELETE_IMAGE_SERVICE_LINK = "DELETE FROM images_links_service WHERE link = ? and id_service = ?";
	private static final String MySQL_DELETE_IMAGE_PERSON_LINK = "DELETE FROM images_links_person WHERE link = ? and id_person = ?";

	// fin update
	public static String getMysqlSeTrouverParEmail() {
		return MySQL_SE_TROUVER_PAR_EMAIL;
	}

	public static String getOraclesqlSeTrouverParEmail() {
		return OracleSQL_SE_TROUVER_PAR_EMAIL;
	}

	public static String getMysqlSelectAll() {
		return MySQL_SELECT_ALL;
	}

	public static String getMysqlSelectAllImportant() {
		return MySQL_SELECT_ALL_IMPORTANT;
	}

	public static String getOraclesqlSelectAllImportant() {
		return OracleSQL_SELECT_ALL_IMPORTANT;
	}

	public static String getMysqlInsertPerson() {
		return MySQL_INSERT_PERSON;
	}

	public static String getOraclesqlInsertPerson() {
		return OracleSQL_INSERT_PERSON;
	}

	public static String getMysqlInsertEvaluation() {
		return MySQL_INSERT_EVALUATION;
	}

	public static String getOraclesqlInsertEvaluation() {
		return OracleSQL_INSERT_EVALUATION;
	}

	public static String getMysqlInsertCommande() {
		return MySQL_INSERT_COMMANDE;
	}

	public static String getOraclesqlInsertCommande() {
		return OracleSQL_INSERT_COMMANDE;
	}

	public static String getMysqlInsertConnection() {
		return MySQL_INSERT_CONNECTION;
	}

	public static String getOraclesqlInsertConnection() {
		return OracleSQL_INSERT_CONNECTION;
	}

	public static String getMysqlInsertProduct() {
		return MySQL_INSERT_PRODUCT;
	}

	public static String getOraclesqlInsertProduct() {
		return OracleSQL_INSERT_PRODUCT;
	}

	public static String getMysqlInsertInvoice() {
		return MySQL_INSERT_INVOICE;
	}

	public static String getOraclesqlInsertInvoice() {
		return OracleSQL_INSERT_INVOICE;
	}

	public static String getMysqlInsertProductComponent() {
		return MySQL_INSERT_PRODUCT_COMPONENT;
	}

	public static String getOraclesqlInsertProductComponent() {
		return OracleSQL_INSERT_PRODUCT_COMPONENT;
	}

	public static String getMysqlInsertProductService() {
		return MySQL_INSERT_PRODUCT_SERVICE;
	}

	public static String getOraclesqlInsertProductService() {
		return OracleSQL_INSERT_PRODUCT_SERVICE;
	}

	public static String getMysqlInsertInvoicePerson() {
		return MySQL_INSERT_INVOICE_PERSON;
	}

	public static String getOraclesqlInsertInvoicePerson() {
		return OracleSQL_INSERT_INVOICE_PERSON;
	}

	public static String getMysqlInsertInvoiceProduct() {
		return MySQL_INSERT_INVOICE_PRODUCT;
	}

	public static String getOraclesqlInsertInvoiceProduct() {
		return OracleSQL_INSERT_INVOICE_PRODUCT;
	}

	public static String getMysqlInsertInvoiceService() {
		return MySQL_INSERT_INVOICE_SERVICE;
	}

	public static String getOraclesqlInsertInvoiceService() {
		return OracleSQL_INSERT_INVOICE_SERVICE;
	}

	public static String getMysqlInsertService() {
		return MySQL_INSERT_SERVICE;
	}

	public static String getOraclesqlInsertService() {
		return OracleSQL_INSERT_SERVICE;
	}

	public static String getMysqlInsertServiceProduct() {
		return MySQL_INSERT_SERVICE_PRODUCT;
	}

	public static String getOraclesqlInsertServiceProduct() {
		return OracleSQL_INSERT_SERVICE_PRODUCT;
	}

	public static String getMysqlInsertServiceComponent() {
		return MySQL_INSERT_SERVICE_COMPONENT;
	}

	public static String getOraclesqlInsertServiceComponent() {
		return OracleSQL_INSERT_SERVICE_COMPONENT;
	}

	public static String getMysqlInsertCommandeProduct() {
		return MySQL_INSERT_COMMANDE_PRODUCT;
	}

	public static String getOraclesqlInsertCommandeProduct() {
		return OracleSQL_INSERT_COMMANDE_PRODUCT;
	}

	public static String getMysqlInsertCommandeService() {
		return MySQL_INSERT_COMMANDE_SERVICE;
	}

	public static String getOraclesqlInsertCommandeService() {
		return OracleSQL_INSERT_COMMANDE_SERVICE;
	}

	public static String getMysqlUpdatePerson() {
		return MySQL_UPDATE_PERSON;
	}

	public static String getMysqlUpdateCommande() {
		return MySQL_UPDATE_COMMANDE;
	}

	public static String getOraclesqlUpdateCommande() {
		return OracleSQL_UPDATE_COMMANDE;
	}

	public static String getMysqlUpdateEvaluation() {
		return MySQL_UPDATE_EVALUATION;
	}

	public static String getOraclesqlUpdateEvaluation() {
		return OracleSQL_UPDATE_EVALUATION;
	}

	public static String getMysqlUpdateCommandeProduct() {
		return MySQL_UPDATE_COMMANDE_PRODUCT;
	}

	public static String getOraclesqlUpdateCommandeProduct() {
		return OracleSQL_UPDATE_COMMANDE_PRODUCT;
	}

	public static String getMysqlUpdateCommandeService() {
		return MySQL_UPDATE_COMMANDE_SERVICE;
	}

	public static String getOraclesqlUpdateCommandeService() {
		return OracleSQL_UPDATE_COMMANDE_SERVICE;
	}

	public static String getMysqlUpdateServiceProduct() {
		return MySQL_UPDATE_SERVICE_PRODUCT;
	}

	public static String getOraclesqlUpdateServiceProduct() {
		return OracleSQL_UPDATE_SERVICE_PRODUCT;
	}

	public static String getMysqlUpdateServiceComponent() {
		return MySQL_UPDATE_SERVICE_COMPONENT;
	}

	public static String getOracleUpdatesqlServiceComponent() {
		return OracleSQL_UPDATE_SERVICE_COMPONENT;
	}

	public static String getMysqlUpdateConnection() {
		return MySQL_UPDATE_CONNECTION;
	}

	public static String getOraclesqlUpdateConnection() {
		return OracleSQL_UPDATE_CONNECTION;
	}

	public static String getMysqlUpdateProduct() {
		return MySQL_UPDATE_PRODUCT;
	}

	public static String getOraclesqlUpdateProduct() {
		return OracleSQL_UPDATE_PRODUCT;
	}

	public static String getMysqlUpdateInvoice() {
		return MySQL_UPDATE_INVOICE;
	}

	public static String getOraclesqlUpdateInvoice() {
		return OracleSQL_UPDATE_INVOICE;
	}

	public static String getMysqlUpdateProductComponent() {
		return MySQL_UPDATE_PRODUCT_COMPONENT;
	}

	public static String getOraclesqlUpdateProductComponent() {
		return OracleSQL_UPDATE_PRODUCT_COMPONENT;
	}

	public static String getMysqlUpdateProductService() {
		return MySQL_UPDATE_PRODUCT_SERVICE;
	}

	public static String getOraclesqlUpdateProductService() {
		return OracleSQL_UPDATE_PRODUCT_SERVICE;
	}

	public static String getMysqlUpdateService() {
		return MySQL_UPDATE_SERVICE;
	}

	public static String getOraclesqlUpdateService() {
		return OracleSQL_UPDATE_SERVICE;
	}

	public static String getMysqlUpdateInvoicePerson() {
		return MySQL_UPDATE_INVOICE_PERSON;
	}

	public static String getOraclesqlUpdateInvoicePerson() {
		return OracleSQL_UPDATE_INVOICE_PERSON;
	}

	public static String getMysqlUpdateInvoiceProduct() {
		return MySQL_UPDATE_INVOICE_PRODUCT;
	}

	public static String getOraclesqlUpdateInvoiceProduct() {
		return OracleSQL_UPDATE_INVOICE_PRODUCT;
	}

	public static String getMysqlUpdateInvoiceService() {
		return MySQL_UPDATE_INVOICE_SERVICE;
	}

	public static String getOraclesqlUpdateInvoiceService() {
		return OracleSQL_UPDATE_INVOICE_SERVICE;
	}

	public static String getMysqlDeletePerson() {
		return MySQL_DELETE_PERSON;
	}

	public static String getOraclesqlDeletePerson() {
		return OracleSQL_DELETE_PERSON;
	}

	public static String getMysqlDeleteCommande() {
		return MySQL_DELETE_COMMANDE;
	}

	public static String getOraclesqlDeleteCommande() {
		return OracleSQL_DELETE_COMMANDE;
	}

	public static String getMysqlDeleteConnection() {
		return MySQL_DELETE_CONNECTION;
	}

	public static String getOraclesqlDeleteConnection() {
		return OracleSQL_DELETE_CONNECTION;
	}

	public static String getMysqlDeleteProduct() {
		return MySQL_DELETE_PRODUCT;
	}

	public static String getOraclesqlDeleteProduct() {
		return OracleSQL_DELETE_PRODUCT;
	}

	public static String getMysqlDeleteInvoice() {
		return MySQL_DELETE_INVOICE;
	}

	public static String getOraclesqlDeleteInvoice() {
		return OracleSQL_DELETE_INVOICE;
	}

	public static String getMysqlDeleteProductComponent() {
		return MySQL_DELETE_PRODUCT_COMPONENT;
	}

	public static String getOraclesqlDeleteProductComponent() {
		return OracleSQL_DELETE_PRODUCT_COMPONENT;
	}

	public static String getMysqlDeleteProductService() {
		return MySQL_DELETE_PRODUCT_SERVICE;
	}

	public static String getOraclesqlDeleteProductService() {
		return OracleSQL_DELETE_PRODUCT_SERVICE;
	}

	public static String getMysqlDeleteCommandeProduct() {
		return MySQL_DELETE_COMMANDE_PRODUCT;
	}

	public static String getOraclesqlDeleteCommandeProduct() {
		return OracleSQL_DELETE_COMMANDE_PRODUCT;
	}

	public static String getMysqlDeleteServiceProduct() {
		return MySQL_DELETE_SERVICE_PRODUCT;
	}

	public static String getOraclesqlDeleteServiceProduct() {
		return OracleSQL_DELETE_SERVICE_PRODUCT;
	}

	public static String getMysqlDeleteServiceComponent() {
		return MySQL_DELETE_SERVICE_COMPONENT;
	}

	public static String getOraclesqlDeleteServiceComponent() {
		return OracleSQL_DELETE_SERVICE_COMPONENT;
	}

	public static String getMysqlDeleteCommandeService() {
		return MySQL_DELETE_COMMANDE_SERVICE;
	}

	public static String getOraclesqlDeleteCommandeService() {
		return OracleSQL_DELETE_COMMANDE_SERVICE;
	}

	public static String getMysqlDeleteInvoiceService() {
		return MySQL_DELETE_INVOICE_SERVICE;
	}

	public static String getOraclesqlDeleteInvoiceService() {
		return OracleSQL_DELETE_INVOICE_SERVICE;
	}

	public static String getMysqlDeleteInvoiceProduct() {
		return MySQL_DELETE_INVOICE_PRODUCT;
	}

	public static String getOraclesqlDeleteInvoiceProduct() {
		return OracleSQL_DELETE_INVOICE_PRODUCT;
	}

	public static String getMysqlDeleteInvoicePerson() {
		return MySQL_DELETE_INVOICE_PERSON;
	}

	public static String getOraclesqlDeleteInvoicePerson() {
		return OracleSQL_DELETE_INVOICE_PERSON;
	}

	public static String getMysqlDeleteService() {
		return MySQL_DELETE_SERVICE;
	}

	public static String getOraclesqlDeleteService() {
		return OracleSQL_DELETE_SERVICE;
	}

	public static String getMysqlDeleteEvaluation() {
		return MySQL_DELETE_EVALUATION;
	}

	public static String getOraclesqlDeleteEvaluation() {
		return OracleSQL_DELETE_EVALUATION;
	}

	public static String getOraclesqlSeTrouverTotalementParEmail() {
		return OracleSQL_SE_TROUVER_TOTALEMENT_PAR_EMAIL;
	}

	public static String getOraclesqlLastElement() {
		return OracleSQL_LAST_ELEMENT;
	}

	public static String getOraclesqlUpdateCommandeState() {
		return OracleSQL_UPDATE_COMMANDE_STATE;
	}

	public static String getOraclesqlInsertComments() {
		return OracleSQL_INSERT_COMMENTS;
	}

	public static String getOraclesqlInsertCommentsProduct() {
		return OracleSQL_INSERT_COMMENTS_PRODUCT;
	}

	public static String getOraclesqlInsertCommentsService() {
		return OracleSQL_INSERT_COMMENTS_SERVICE;
	}

	public static String getOraclesqlUpdateComments() {
		return OracleSQL_UPDATE_COMMENTS;
	}

	public static String getOraclesqlDeleteComments() {
		return OracleSQL_DELETE_COMMENTS;
	}

	public static String getOraclesqlDeleteCommentsProduct() {
		return OracleSQL_DELETE_COMMENTS_PRODUCT;
	}

	public static String getOraclesqlDeleteCommentsService() {
		return OracleSQL_DELETE_COMMENTS_SERVICE;
	}

	public static String getOraclesqlInsertMessage() {
		return OracleSQL_INSERT_MESSAGE;
	}

	public static String getOraclesqlUpdateMessageReceiveDate() {
		return OracleSQL_UPDATE_MESSAGE_RECEIVE_DATE;
	}

	public static String getOraclesqlUpdateMessageReadDate() {
		return OracleSQL_UPDATE_MESSAGE_READ_DATE;
	}

	public static String getOraclesqlDeleteMessage() {
		return OracleSQL_DELETE_MESSAGE;
	}

	public static String getMysqlSeTrouverParId() {
		return MySQL_SE_TROUVER_PAR_ID;
	}

	public static String getOraclesqlSeTrouverParId() {
		return Oracle_SE_TROUVER_PAR_ID;
	}

	public static String getOracleSeTrouverParId() {
		return Oracle_SE_TROUVER_PAR_ID;
	}

	public static String getOraclesqlSelectFromCommandeService() {
		return OracleSQL_SELECT_FROM_COMMANDE_SERVICE;
	}

	public static String getOraclesqlSelectFromCommandeProduct() {
		return OracleSQL_SELECT_FROM_COMMANDE_PRODUCT;
	}

	public static String getOraclesqlSelectFromCommandeByCustomer() {
		return OracleSQL_SELECT_FROM_COMMANDE_BY_CUSTOMER;
	}

	public static String getOraclesqlSelectFromCommandeById() {
		return OracleSQL_SELECT_FROM_COMMANDE_BY_ID;
	}

	public static String getOraclesqlSelectFromProductService() {
		return OracleSQL_SELECT_FROM_PRODUCT_SERVICE;
	}

	public static String getOraclesqlSelectFromServiceComponent() {
		return OracleSQL_SELECT_FROM_SERVICE_COMPONENT;
	}

	public static String getOraclesqlSelectFromServiceProduct() {
		return OracleSQL_SELECT_FROM_SERVICE_PRODUCT;
	}

	public static String getOraclesqlSelectFromProductComponent() {
		return OracleSQL_SELECT_FROM_PRODUCT_COMPONENT;
	}

	public static String getOraclesqlUpdateServiceComponent() {
		return OracleSQL_UPDATE_SERVICE_COMPONENT;
	}

	public static String getOraclesqlSelectServiceById() {
		return OracleSQL_SELECT_SERVICE_BY_ID;
	}

	public static String getOraclesqlSelectProductById() {
		return OracleSQL_SELECT_PRODUCT_BY_ID;
	}

	public static String getOraclesqlSelectProductByKeyword() {
		return OracleSQL_SELECT_PRODUCT_BY_KEYWORD;
	}

	public static String getOraclesqlSelectServiceByKeyword() {
		return OracleSQL_SELECT_SERVICE_BY_KEYWORD;
	}

	public static String getMysqlSelectProductById() {
		return MySQL_SELECT_PRODUCT_BY_ID;
	}

	public static String getMysqlSelectServiceById() {
		return MySQL_SELECT_SERVICE_BY_ID;
	}

	public static String getMysqlSelectAllProduct() {
		return MySQL_SELECT_ALL_PRODUCT;
	}

	public static String getMysqlSelectAllService() {
		return MySQL_SELECT_ALL_SERVICE;
	}

	public static String getMysqlSelectCountProduct() {
		return MySQL_SELECT_COUNT_PRODUCT;
	}

	public static String getMysqlSelectCountService() {
		return MySQL_SELECT_COUNT_SERVICE;
	}

	public static String getMysqlSelectAllCommande() {
		return MySQL_SELECT_ALL_COMMANDE;
	}

	public static String getMysqlSelectAllServiceImageLinks() {
		return MySQL_SELECT_ALL_SERVICE_IMAGE_LINKS;
	}

	public static String getMysqlSelectAllProductImageLinks() {
		return MySQL_SELECT_ALL_PRODUCT_IMAGE_LINKS;
	}

	public static String getMysqlSelectAllPersonImageLinks() {
		return MySQL_SELECT_ALL_PERSON_IMAGE_LINKS;
	}

	public static String getMysqlInsertImageProductLink() {
		return MySQL_INSERT_IMAGE_PRODUCT_LINK;
	}

	public static String getMysqlInsertImageServiceLink() {
		return MySQL_INSERT_IMAGE_SERVICE_LINK;
	}

	public static String getMysqlInsertImagePersonLink() {
		return MySQL_INSERT_IMAGE_PERSON_LINK;
	}

	public static String getMysqlUpdateImageServiceLink() {
		return MySQL_UPDATE_IMAGE_SERVICE_LINK;
	}

	public static String getMysqlUpdateImageProductLink() {
		return MySQL_UPDATE_IMAGE_PRODUCT_LINK;
	}

	public static String getMysqlUpdateImagePersonLink() {
		return MySQL_UPDATE_IMAGE_PERSON_LINK;
	}

	public static String getMysqlDeleteImageProductLink() {
		return MySQL_DELETE_IMAGE_PRODUCT_LINK;
	}

	public static String getMysqlDeleteImageServiceLink() {
		return MySQL_DELETE_IMAGE_SERVICE_LINK;
	}

	public static String getMysqlDeleteImagePersonLink() {
		return MySQL_DELETE_IMAGE_PERSON_LINK;
	}

	public static String getMysqlSelectProductByIdProvider() {
		return MySQL_SELECT_PRODUCT_BY_ID_PROVIDER;
	}

	public static String getMysqlSelectServiceByIdProvider() {
		return MySQL_SELECT_SERVICE_BY_ID_PROVIDER;
	}

	public static String getMysqlSelectCountProductByIdProvider() {
		return MySQL_SELECT_COUNT_PRODUCT_BY_ID_PROVIDER;
	}

	public static String getMysqlSelectCountServiceByIdProvider() {
		return MySQL_SELECT_COUNT_SERVICE_BY_ID_PROVIDER;
	}

	public static String getMysqlSelectMySendMessage() {
		return MySQL_SELECT_MY_SEND_MESSAGE;
	}

	public static String getMysqlSelectMyMessage() {
		return MySQL_SELECT_MY_MESSAGE;
	}

	public static String getMysqlSelectMyUnreadMessage() {
		return MySQL_SELECT_MY_UNREAD_MESSAGE;
	}

	public static String getMysqlSelectCommentProduct() {
		return MySQL_SELECT_COMMENT_PRODUCT;
	}

	public static String getMysqlSelectCommentService() {
		return MySQL_SELECT_COMMENT_SERVICE;
	}

	public static String getMysqlSelectCommentById() {
		return MySQL_SELECT_COMMENT_BY_ID;
	}

	public static String getMysqlSelectInvoiceById() {
		return MySQL_SELECT_INVOICE_BY_ID;

	}

	public static String getMysqlSelectCountCommentsByIdService() {
		return MySQL_SELECT_COUNT_COMMENTS_BY_ID_SERVICE;
	}

	public static String getMysqlSelectCountCommentsByIdProduct() {
		return MySQL_SELECT_COUNT_COMMENTS_BY_ID_PRODUCT;
	}

}
