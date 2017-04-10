package dao;

import java.util.ArrayList;

import beans.Commande;
import beans.Person;
import beans.Product;
import beans.ResultConnexion;

public interface PersonDao {

	public void creer(Person utilisateur) throws ExceptionDao;

	public Person trouver(String email,boolean succeed) throws ExceptionDao;

	public ResultConnexion seConnecter(String Id, String Password);

	public Boolean Commander(Commande commande,String Id);

	public ArrayList<String> acceptCommand(ArrayList<String> listCommand);

	public String[] Payment(ArrayList<String> listCommand);

	public Boolean modifyPersonalInformation(Person pers);

	public Boolean deleteAccount(Person pers);

	public Boolean Comment(Person pers, String comment);

	public Boolean Claim(String claiming);

	public Boolean Search(ArrayList<String> Params, String KeyWord);

	public Boolean addToPanel(ArrayList<Product> listProducts);

	public Boolean removeToPanel(ArrayList<Product> listProducts, Boolean allornot);

	public Boolean Reservation(Product product);

	public Boolean sendMessage(Person pers, String Message);

	public Boolean receiveMessage(Person pers, String Message);

	public ArrayList<String> Historique(Integer nbreJours);

	public Boolean Invite(String e_mailAddress);
}
