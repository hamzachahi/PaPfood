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
import beans.Person;
import dao.DaoCommandeImpl;
import dao.DaoInvoiceImpl;
import dao.UsineDao;

@WebServlet("/ServletOrder")

public class ServletOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2268529311014243749L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/order.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		ArrayList<ElementCommand> elements = new ArrayList<>();
		elements = (ArrayList<ElementCommand>) session.getAttribute("monPanier");
		String action = request.getParameter("action");
		String adresseComplete = "";
		DaoCommandeImpl commandeDao = new DaoCommandeImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));
		/*
		 * DaoInvoiceImpl invoiceDao = new DaoInvoiceImpl(new UsineDao(
		 * "jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
		 * "root", "0000"));
		 */

		Commande order = new Commande();

		if (action != null) {
			if (action.equals("changerAdresse")) {

				String pays = request.getParameter("country");
				String num = request.getParameter("num");
				String nom = request.getParameter("nom");
				String ville = request.getParameter("ville");

				adresseComplete = num + ", " + nom + ", " + ville + ", " + pays;

				session.setAttribute("adresseComplete", adresseComplete);
				System.out.println(adresseComplete);
				this.getServletContext().getRequestDispatcher("/panier").forward(request, response);

			}

			if (action.equals("validerPanier")) {

				Date aujourdhui = new Date();
				DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
				String now = mediumDateFormat.format(aujourdhui);
				now.replaceAll(" ", "");
				session.getAttribute(adresseComplete);
				if (adresseComplete == null || adresseComplete.equals("")) {
					adresseComplete = utilisateur.getStreetNumber() + ", " + utilisateur.getStreetName() + ", "
							+ utilisateur.getCityName() + ", " + utilisateur.getCountryName();
				}

				order.setAdresseExpedition(adresseComplete);
				order.setAdresseFacturation(adresseComplete);
				order.setCustomer(utilisateur);
				order.setPrice(Double.parseDouble(session.getAttribute("prixtotal").toString()));
				String code = utilisateur.getName().substring(0, 0) + "" + utilisateur.getSurname().substring(0, 0) + ""
						+ now;
				order.setCode(code);
				order.setElements(elements);
				commandeDao.Commander(order);

			}
		}

	}

}
