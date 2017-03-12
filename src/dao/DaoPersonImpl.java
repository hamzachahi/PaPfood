package dao;
import static dao.UtilitaireDao.fermeturesSilencieuses;
import static dao.UtilitaireDao.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Person;

public class DaoPersonImpl 	implements PersonDao {

	    private static final String SQL_SELECT_PAR_EMAIL = "SELECT id, email, nom, mot_de_passe, date_inscription FROM Utilisateur WHERE email = ?";
	    private static final String SQL_INSERT           = "INSERT INTO Utilisateur (email, mot_de_passe, nom, date_inscription) VALUES (?, ?, ?, NOW())";

	    private UsineDao         daoFactory;

	    DaoPersonImpl( UsineDao daoFactory ) {
	        this.daoFactory = daoFactory;
	    }

	    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	    @Override
	    public Person trouver( String email ) throws ExceptionDao {
	        return trouver( SQL_SELECT_PAR_EMAIL, email );
	    }

	    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
	    @Override
	    public void creer( Person utilisateur ) throws ExceptionDao {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet valeursAutoGenerees = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, utilisateur.getEmail(), utilisateur.getPassword(), utilisateur.getName() );
	            int statut = preparedStatement.executeUpdate();
	            if ( statut == 0 ) {
	                throw new ExceptionDao( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	            }
	            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	            if ( valeursAutoGenerees.next() ) {
	                utilisateur.setId(String.valueOf( valeursAutoGenerees.getLong( 1 ) ),false);
	            } else {
	                throw new ExceptionDao( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	            }
	        } catch ( SQLException e ) {
	            throw new ExceptionDao( e );
	        } finally {
	            fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	        }
	    }

	    /*
	     * Méthode générique utilisée pour retourner un utilisateur depuis la base
	     * de données, correspondant à la requête SQL donnée prenant en paramètres
	     * les objets passés en argument.
	     */
	    private Person trouver( String sql, Object... objets ) throws ExceptionDao {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;
	        Person utilisateur = null;

	        try {
	            /* Récupération d'une connexion depuis la Factory */
	            connexion = daoFactory.getConnection();
	            /*
	             * Préparation de la requête avec les objets passés en arguments
	             * (ici, uniquement une adresse email) et exécution.
	             */
	            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
	            resultSet = preparedStatement.executeQuery();
	            /* Parcours de la ligne de données retournée dans le ResultSet */
	            if ( resultSet.next() ) {
	                utilisateur = map( resultSet );
	            }
	        } catch ( SQLException e ) {
	            throw new ExceptionDao( e );
	        } finally {
	            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	        }

	        return utilisateur;
	    }

	    /*
	     * Simple méthode utilitaire permettant de faire la correspondance (le
	     * mapping) entre une ligne issue de la table des utilisateurs (un
	     * ResultSet) et un bean Utilisateur.
	     */
	    private static Person map( ResultSet resultSet ) throws SQLException {
	        Person utilisateur = new Person();
	        utilisateur.setId( resultSet.getString( "id" ),false );
	        utilisateur.setEmail( resultSet.getString( "email" ),false );
	        utilisateur.setPassword( resultSet.getString( "mot_de_passe" ),false );
	        utilisateur.setName( resultSet.getString( "nom" ),false );
	        utilisateur.setDateInscription( resultSet.getTimestamp( "date_inscription" ),false );
	        return utilisateur;
	    }
	   
	}