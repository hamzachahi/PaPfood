package dao;

import java.util.ArrayList;
import java.util.Map;
import beans.Commande;
import beans.Person;

public interface PersonDao {

	public void creer(Person utilisateur) throws ExceptionDao;

	public Person trouver(String email, boolean succeed) throws ExceptionDao;

	public Boolean acceptCommand(ArrayList<Commande> listCommand);

	public Boolean modifyPersonalInformation(Person pers);

	public Boolean deleteAccount(Person pers);

	public Boolean Claim(Person pers, String claiming, String subject, String emailDest);

	public Map<ArrayList<String>, ArrayList<String>> Historique(Person utilisateur, Boolean isSucceed,
			Integer nbreJours);

	public Boolean Invite(Person utilisateur, String e_mailAddress);

	public Person trouverParId(Long id, boolean succeed) throws ExceptionDao;

	public ArrayList<Person> findAllUsers(Long limit, Long offset);

	public Long countAllUsers();
}
