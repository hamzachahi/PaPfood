package beans;

import java.util.ArrayList;

public interface ActionPerson {
	public Boolean seConnecter(String Id, String Password);

	public Boolean Commander(ArrayList<String> listCommand);

	public ArrayList<String> acceptCommand(ArrayList<String> listCommand);

	public String[] Payment(ArrayList<String> listCommand);

	public Boolean modifyPersonalInformation(Person pers);

	public Boolean deleteAccount(Person pers);

	public Boolean Comment(Person pers, String comment);

	public Boolean Claim(String claiming);

	public Boolean Search(ArrayList<String> Params, String KeyWord);

	public Boolean addToPanel(ArrayList<Salable> listProducts);

	public Boolean removeToPanel(ArrayList<Salable> listProducts, Boolean allornot);

	public Boolean Reservation(Salable product);

	public Boolean sendMessage(Person pers, String Message);

	public Boolean receiveMessage(Person pers, String Message);

	public ArrayList<String> Historique(Integer nbreJours);

	public Boolean Invite(String e_mailAddress);
}
