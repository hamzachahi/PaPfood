package dao;

import beans.Connexion;

public interface ConnectionDao {

	public Boolean createConnexion(Long Id, String ipAddress, String personType);

	public Boolean updateConnexion(Long Id);

	public Long getLastConnexionIdByIdConnected(Long Id);

	public Connexion getConnexionById(Long Id);

}
