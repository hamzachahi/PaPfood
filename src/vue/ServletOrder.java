package vue;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Commande;
import beans.ElementCommand;
import beans.Invoice;
import beans.Person;
import dao.CommandeDao;
import dao.InvoiceDao;
import dao.MessageDao;
import dao.UsineDao;

@WebServlet("/ServletOrder")

public class ServletOrder extends HttpServlet {

	private static final long serialVersionUID = 2268529311014243749L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/order.jsp";
	CommandeDao commandeDao;
	MessageDao messageDao;
	InvoiceDao invoiceDao;
	ArrayList<ElementCommand> elements ;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.commandeDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDao();
		this.invoiceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getInvoiceDao();
		this.messageDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adresseComplete = "";
		Commande order = new Commande();
		Invoice facture = new Invoice();
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("sessionUtilisateur") != null) {
			Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			elements = (ArrayList<ElementCommand>) session.getAttribute("monPanier");
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equals("changerAdresse")) {

					String pays = request.getParameter("country");
					String num = request.getParameter("num");
					String nom = request.getParameter("nom");
					String ville = request.getParameter("ville");

					adresseComplete = num + ", " + nom + ", " + ville + "," + pays;

					session.setAttribute("adresseComplete", adresseComplete);
					System.out.println(adresseComplete);
					this.getServletContext().getRequestDispatcher("/panier").forward(request, response);

				}

				if (action.equals("validerPanier")) {

					Date aujourdhui = new Date();
					DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
					String now = mediumDateFormat.format(aujourdhui);
					now.replaceAll(" ", "");
					adresseComplete = (String) session.getAttribute(adresseComplete);
					if (adresseComplete == null || adresseComplete.equals("")) {
						adresseComplete = utilisateur.getStreetNumber() + ", " + utilisateur.getStreetName() + ", "
								+ utilisateur.getCityName() + ", " + utilisateur.getCountryName();
					}

					// Construction de la commande

					order.setAdresseExpedition(adresseComplete);
					order.setAdresseFacturation(adresseComplete);
					order.setCustomer(utilisateur);
					order.setPrice(Double.parseDouble(session.getAttribute("prixtotal").toString()));
					String code = utilisateur.getName().substring(0, 0) + "" + utilisateur.getSurname().substring(0, 0)
							+ "" + now;
					order.setCode(code);
					ArrayList<ElementCommand> sort = removeDoublons(elements);
					order.setElements(sort);
					boolean isSucceed = commandeDao.Commander(order);
					for (int i = 0; i < sort.size(); i++) {
						messageDao.sendMessage(utilisateur.getId(), sort.get(i).getmProduct().getIdProvider(),
								utilisateur.getSurname() + " " + utilisateur.getName() + " souhaite obtenir de vous "
										+ sort.get(i).toString());
					}
					request.setAttribute("articlesPanier", elements); // Construction
																		// de la
					// facture

					/*
					 * ArrayList<Salable> listeArticles = new ArrayList<>();
					 * 
					 * for (int i = 0; i < elements.size(); i++) {
					 * 
					 * listeArticles.add(elements.get(i).getmProduct());
					 * 
					 * }
					 * 
					 * String codeFacture = utilisateur.getCityName() + code +
					 * session.getAttribute("prixtotal").toString() +
					 * elements.get(0).toString().substring(0, 2);
					 * 
					 * facture.setPersonName(utilisateur.getName(), false);
					 * facture.setTotalPrice(Double.parseDouble(session.
					 * getAttribute ("prixtotal").toString()), false);
					 * facture.setListProduct(listeArticles, false);
					 * facture.setCodeInvoice(codeFacture, false);
					 * facture.setPhoneNumber(utilisateur.getPhoneNumber(),
					 * false); facture.setDestinatorAddress(adresseComplete,
					 * false);
					 */

					// boolean invoiceDone = invoiceDao.addInvoice(facture);
					if (isSucceed = true) {
						session.setAttribute("monPanier", null);
						this.getServletContext().getRequestDispatcher("/WEB-INF/facture.jsp").forward(request,
								response);

					} else {
						this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);

					}
				}
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}

	public ArrayList<ElementCommand> removeDoublons(ArrayList<ElementCommand> membA) {
		for (int i = 0; i < membA.size(); i++) {
			ElementCommand o = membA.get(i);
			for (int i1 = i + 1; i1 < membA.size(); i1++) {
				ElementCommand r = membA.get(i1);
				if (o.getmProduct().getId() == r.getmProduct().getId()) {
					membA.remove(r);
					System.out.println("ServletOrder.removeDoublons() effectué!");
					membA.get(i).setQuantity(membA.get(i).getQuantity() + 1);
				}
			}
		}

		return membA;
	}

}
