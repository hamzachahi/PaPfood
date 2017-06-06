package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Commande;
import beans.Paginateur;
import beans.Person;
import dao.CommandeDao;
import dao.UsineDao;

@WebServlet("/ServletOwnOrders")
public class ServletOwnOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommandeDao commandeDao;
	public static final String CONF_DAO_FACTORY = "usinedao";
	String pagination;
	ArrayList<Commande> allOrders;
	Long total;
	Long begin;
	Long end;

	public void init() throws ServletException {
		this.commandeDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("sessionUtilisateur") != null) {
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			if (request.getParameter("action") != null) {
				String action = request.getParameter("action");
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));

					String message = "";
					String pagination = "";
					total = commandeDao.countNbreCommandeByIdCustomer(person.getId());

					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						allOrders = commandeDao.findAllCommande(end, begin);
						request.setAttribute("allOrders", allOrders);
						request.setAttribute("total", total);
						request.setAttribute("message", message);
					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, allOrders, request, "mescommandes");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("allOrders", allOrders);
					request.setAttribute("message", message);
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/myOrders.jsp").forward(request, response);

			} else {
				allOrders = commandeDao.findCommandeParClient(person.getId(), Long.valueOf(10), Long.valueOf(0));
				total = commandeDao.countNbreCommandeByIdCustomer(person.getId());
				request.setAttribute("allOrders", allOrders);
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, allOrders, request, "mescommandes");
				request.setAttribute("pagination", pagination);
				this.getServletContext().getRequestDispatcher("/WEB-INF/myOrders.jsp").forward(request, response);
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}

	}
}
