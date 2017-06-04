package dao;

import java.util.ArrayList;

import beans.Commande;
import beans.ElementCommand;

public interface CommandeDao {
	public Boolean Commander(Commande commande);

	public Boolean modifyCommande(Commande commande);

	public Boolean deleteCommande(Commande commande);

	public Boolean addToCommand(ArrayList<ElementCommand> listProducts, Long Id);

	public Boolean removeToCommand(ArrayList<ElementCommand> listProducts, Boolean allornot, Long Id);

	public ArrayList<Commande> findCommandeParClient(Long id, Long limit, Long offset);

	public Commande findCommandeParId(Long id);

	public Long countNbreCommandeByIdCustomer(Long Id);

	public Long countAllCommande();

	public ArrayList<Commande> findAllCommande(Long limit, Long offset);

}
