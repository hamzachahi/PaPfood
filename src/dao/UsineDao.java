package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UsineDao {

	private static final String FICHIER_PROPERTIES = "/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private String url;
	private String username;
	private String password;

	UsineDao(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static UsineDao getInstance() throws ConfigDaoException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

		if (fichierProperties == null) {
			throw new ConfigDaoException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch (IOException e) {
			throw new ConfigDaoException("Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ConfigDaoException("Le driver est introuvable dans le classpath.", e);
		}

		UsineDao instance = new UsineDao(url, nomUtilisateur, motDePasse);
		return instance;
	}

	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public PersonDao getUtilisateurDao() {
		return new DaoPersonImpl(this);
	}

	public CommentDao getCommentDao() {
		return new DaoCommentImpl(this);
	}

	public CommandeDao getCommandeDao() {
		return new DaoCommandeImpl(this);
	}

	public InvoiceDao getInvoiceDao() {
		return new DaoInvoiceImpl(this);
	}

	public MessageDao getMessageDao() {
		return new DaoMessageImpl(this);
	}

	public PostDao getPostDao() {
		return new DaoPostImpl(this);
	}

	public ProductDao getProductDao() {
		return new DaoProductImpl(this);
	}
	public ServiceDao getServiceDao() {
		return new DaoServiceImpl(this);
	}
	public SearchDao getSearchDao(){
		return new DaoSearchImpl(this);
	}
	public EvaluationDao getEvaluationDao(){
		return new DaoEvaluationImpl(this);
	}
}