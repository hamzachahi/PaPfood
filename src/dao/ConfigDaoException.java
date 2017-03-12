package dao;

public class ConfigDaoException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5222954978880439771L;

	/*
     * Constructeurs
     */
    public ConfigDaoException( String message ) {
        super( message );
    }

    public ConfigDaoException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ConfigDaoException( Throwable cause ) {
        super( cause );
    }
}
