package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Paginateur;
import beans.Person;
import beans.Salable;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

/**
 * Servlet implementation class ServletOwnProducts
 */
@WebServlet("/ServletOwnProposals")
public class ServletOwnProposals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private ArrayList<Salable> allMyOwnSalables;
	String pagination = "";
	Long begin = null;
	Long end = null;
	Long total = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			if (request.getParameter("action") != null) {
				String action = request.getParameter("action");
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));

					String message = "";
					String pagination = "";
					total = serviceDao.countElementsByIdProvider(person.getId());
					total = total + productDao.countElementsByIdProvider(person.getId());

					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						allMyOwnSalables = new ArrayList<>();
						message = "Liste des éléments trouvés";
						allMyOwnSalables.addAll(serviceDao.findAllServiceByIdProvider(person.getId(), end, begin));
						allMyOwnSalables.addAll(productDao.findAllProductByIdProvider(person.getId(), end, begin));

						request.setAttribute("listSalables", allMyOwnSalables);
						request.setAttribute("total", total);
						request.setAttribute("message", message);
					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, allMyOwnSalables, request, "mespropositions");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("listSalables", allMyOwnSalables);
					request.setAttribute("message", message);
				}

			} else {
				if (allMyOwnSalables == null) {
					allMyOwnSalables = new ArrayList<>();
					total = serviceDao.countElementsByIdProvider(person.getId());
					total = total + productDao.countElementsByIdProvider(person.getId());
					allMyOwnSalables.addAll(serviceDao.findAllServiceByIdProvider(person.getId(), (long) 10, (long) 0));
					allMyOwnSalables.addAll(productDao.findAllProductByIdProvider(person.getId(), (long) 10, (long) 0));
					request.setAttribute("listSalables", allMyOwnSalables);
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, allMyOwnSalables, request, "mespropositions");
					request.setAttribute("pagination", pagination);
				} else {
					request.setAttribute("listSalables", allMyOwnSalables);
					total = serviceDao.countElementsByIdProvider(person.getId());
					total = total + productDao.countElementsByIdProvider(person.getId());
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, allMyOwnSalables, request, "mespropositions");
					request.setAttribute("pagination", pagination);
				}
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/myProposals.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}
}
