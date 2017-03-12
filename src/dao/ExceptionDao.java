package dao;

public class ExceptionDao extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3580432894334159618L;

	/*
	 * Constructeurs
	 */
	public ExceptionDao( String message ) {
        super( message );
    }

	public ExceptionDao( String message, Throwable cause ) {
        super( message, cause );
    }

	public ExceptionDao( Throwable cause ) {
        super( cause );
    }
}
