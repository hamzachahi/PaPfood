package beans;

import java.util.ArrayList;

public class Administrator extends Person implements ActionPerson {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8172344454082890306L;

	@SuppressWarnings("static-access")
	@Override
	public Boolean seConnecter(String Id, String Password) {
		this.setResultConnexion(this.getDaopi().seConnecter(Id, Password));
		return this.getResultConnexion().isSucceed();
	}

	@Override
	public Boolean Commander(ArrayList<String> listCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> acceptCommand(ArrayList<String> listCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] Payment(ArrayList<String> listCommand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean modifyPersonalInformation(Person pers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteAccount(Person pers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Comment(Person pers, String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Claim(String claiming) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Search(ArrayList<String> Params, String KeyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addToPanel(ArrayList<Product> listProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeToPanel(ArrayList<Product> listProducts, Boolean allornot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Reservation(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sendMessage(Person pers, String Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean receiveMessage(Person pers, String Message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> Historique(Integer nbreJours) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean Invite(String e_mailAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
