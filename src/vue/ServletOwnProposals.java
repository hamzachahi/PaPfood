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
import dao.ProductDao;
import dao.ServiceDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletOwnProducts
 */
@WebServlet("/ServletOwnProposals")
public class ServletOwnProposals extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDao serviceDao;
	private ProductDao productDao;
	private ArrayList<Salable> allMyOwnSalables;
	String pagination = "";
	Long begin = null;
	Long end = null;
	Long total = null;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();

	}

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
					total = null;
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
					session.setAttribute("propbegin", begin);
					session.setAttribute("propend", end);
				}

			} else if (request.getParameter("statut") != null) {
				begin = (long) session.getAttribute("propbegin");
				end = (long) session.getAttribute("propend");
				Integer statut = Integer.parseInt(request.getParameter("statut"));
				Long cible = Long.parseLong(request.getParameter("cible"));
				String type = request.getParameter("type");
				Integer newStatut;
				if (statut == 0) {
					newStatut = 1;
				} else {
					newStatut = 0;
				}
				if (type.equals("Service")) {
					serviceDao.changeStatut(cible, newStatut);
				} else {
					productDao.changeStatut(cible, newStatut);
				}
				String message = "";
				String pagination = "";
				total = null;
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
				session.setAttribute("propbegin", begin);
				session.setAttribute("propend", end);

			} else {
				if (allMyOwnSalables == null) {
					allMyOwnSalables = new ArrayList<>();
					total = null;
					total = serviceDao.countElementsByIdProvider(person.getId());
					total = total + productDao.countElementsByIdProvider(person.getId());
					allMyOwnSalables.addAll(serviceDao.findAllServiceByIdProvider(person.getId(), (long) 10, (long) 0));
					allMyOwnSalables.addAll(productDao.findAllProductByIdProvider(person.getId(), (long) 10, (long) 0));
					request.setAttribute("listSalables", allMyOwnSalables);
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, allMyOwnSalables, request, "mespropositions");
					request.setAttribute("pagination", pagination);
				} else {
					begin = Long.valueOf(0);
					end = Long.valueOf(0);
					if (session.getAttribute("propbegin") != null) {
						begin = (long) session.getAttribute("propbegin");
						end = (long) session.getAttribute("propend");
					}
					;
					end = (long) session.getAttribute("propend");
					allMyOwnSalables = new ArrayList<>();
					allMyOwnSalables.addAll(serviceDao.findAllServiceByIdProvider(person.getId(), end, begin));
					allMyOwnSalables.addAll(productDao.findAllProductByIdProvider(person.getId(), end, begin));
					request.setAttribute("listSalables", allMyOwnSalables);
					total = null;
					total = serviceDao.countElementsByIdProvider(person.getId());
					total = total + productDao.countElementsByIdProvider(person.getId());
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, allMyOwnSalables, request, "mespropositions");
					request.setAttribute("pagination", pagination);
				}
				session.setAttribute("propbegin", (long) 0);
				session.setAttribute("propend", (long) 10);

			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/myProposals.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}
}
