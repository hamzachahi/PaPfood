package dao;

import beans.Person;

public interface PersonDao {

    public void creer( Person utilisateur ) throws ExceptionDao;

    public Person trouver( String email ) throws ExceptionDao;

}
