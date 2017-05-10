package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class UsineDao {

    private static final String FICHIER_PROPERTIES       = "/dao/dao.properties";
    private static final String PROPERTY_URL             = "url";
    private static final String PROPERTY_DRIVER          = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
    private static final String PROPERTY_MOT_DE_PASSE    = "motdepasse";

    private String              url;
    private String              username;
    private String              password;

     public UsineDao( String url, String username, String password ) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /*
     * Méthode chargée de récupérer les informations de connexion à la base de
     * données, charger le driver JDBC et retourner une instance de la Factory
     */
    public static UsineDao getInstance() throws ConfigDaoException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );

        if ( fichierProperties == null ) {
            throw new ConfigDaoException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
        }

        try {
            properties.load( fichierProperties );
            url = properties.getProperty( PROPERTY_URL );
            driver = properties.getProperty( PROPERTY_DRIVER );
            nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
            motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
            url="jdbc:mysql://localhost:3306/papfood";
          //  url="jdbc:oracle:thin:@localhost:1521:orcl";

            driver="com.mysql.jdbc.Driver";
           // driver="oracle.jdbc.OracleDriver";

            nomUtilisateur="root";
           // nomUtilisateur="papfood";

            motDePasse="0000";
           // motDePasse="yummyshop";

            System.out.println("Url du dao.properties"+url);
            System.out.println("driver du dao.properties"+driver);
            System.out.println("NomUtilisateur dao.properties"+nomUtilisateur);
            System.out.println("Password du dao.properties"+motDePasse);
         

        } catch ( IOException e ) {
            throw new ConfigDaoException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
        }

        try {
            Class.forName( driver );
        } catch ( ClassNotFoundException e ) {
            throw new ConfigDaoException( "Le driver est introuvable dans le classpath.", e );
        }

        UsineDao instance = new UsineDao( url, nomUtilisateur, motDePasse );
        return instance;
    }

    /* Méthode chargée de fournir une connexion à la base de données */
     /* package */ Connection getConnection() throws SQLException {    	 
         System.out.println("Récupération de connexion");
			System.out.println("Driver O.K.");

        return DriverManager.getConnection( url, username, password );
    }

    /*
     * Méthodes de récupération de l'implémentation des différents DAO (un seul
     * pour le moment)
     */
    public PersonDao getUtilisateurDao() {
        return new DaoPersonImpl( this );
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static String getFichierProperties() {
		return FICHIER_PROPERTIES;
	}

	public static String getPropertyUrl() {
		return PROPERTY_URL;
	}

	public static String getPropertyDriver() {
		return PROPERTY_DRIVER;
	}

	public static String getPropertyNomUtilisateur() {
		return PROPERTY_NOM_UTILISATEUR;
	}

	public static String getPropertyMotDePasse() {
		return PROPERTY_MOT_DE_PASSE;
	}
    
}
