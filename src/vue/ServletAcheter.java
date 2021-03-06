package vue;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.ElementCommand;
import beans.Paginateur;
import beans.Person;
import beans.Salable;
import dao.ProductDao;
import dao.SearchDao;
import dao.ServiceDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletAcheter
 */
@WebServlet("/ServletAcheter")
public class ServletAcheter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Salable> tousLesArticles = null;
	private ArrayList<ElementCommand> monPanier = null;
	ArrayList<ElementCommand> elements = null;
	ArrayList<ElementCommand> elementsSort = null;
	Long begin = null;
	Long end = null;
	Long total = null;
	ServiceDao serviceDao;
	ProductDao productDao;
	SearchDao searchDao;
	String pagination = "";
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
		this.searchDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSearchDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("sessionUtilisateur") != null) {
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));
					String message = "";
					String pagination = "";
					Long total1 = productDao.countElements(person.getId());
					Long total2 = serviceDao.countElements(person.getId());
					total = null;
					total = total1 + total2;
					Long beginp = begin;
					Long begins = begin;
					if (begin > total1) {
						beginp = total1;
					}
					if (begin > total2) {
						begins = total2;
					}
					elements = (ArrayList<ElementCommand>) session.getAttribute("searchResults");
					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						tousLesArticles = new ArrayList<>();
						tousLesArticles.addAll(productDao.findAllProduct(person.getId(), end, beginp));
						tousLesArticles.addAll(serviceDao.findAllService(person.getId(), end, begins));
						elements = new ArrayList<>();
						for (int i = 0; i < tousLesArticles.size(); i++) {
							ElementCommand elementCom = new ElementCommand();
							elementCom.setmProduct(tousLesArticles.get(i));
							elementCom.setQuantity(0);
							elements.add(elementCom);
						}
						request.setAttribute("searchResults", elements);
						request.setAttribute("total", total);
						session.setAttribute("searchResults", elements);

					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("searchResults", elements);
					request.setAttribute("message", message);
					this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

				}
				if (action.equals("chargerPanier")) {
					Salable salable = null;
					Double totalpanier = 0.0;
					String typea = request.getParameter("typea");
					Long id = Long.parseLong(request.getParameter("codearticle"));
					if (typea.equals("Service")) {
						salable = serviceDao.findServiceById(id);
					} else {
						salable = productDao.findProductById(id);
					}
					elements = ((ArrayList<ElementCommand>) session.getAttribute("searchResults"));
					if (session.getAttribute("monPanier") != null) {
						monPanier = ((ArrayList) session.getAttribute("monPanier"));
					} else {
						monPanier = new ArrayList<>();
					}
					ElementCommand article = new ElementCommand();
					article.setmProduct(salable);
					article.setQuantity(article.getQuantity() + 1);
					monPanier.add(article);
					elementsSort = removeDoublons(monPanier);
					session.setAttribute("nbrelementspanier", monPanier.size());
					request.setAttribute("articlesPanier", elementsSort);
					session.setAttribute("monPanier", elementsSort);
					for (int i1 = 0; i1 < monPanier.size(); i1++) {
						totalpanier += monPanier.get(i1).getmProduct().getPrice();
					}
					session.setAttribute("prixtotal", totalpanier);
					request.setAttribute("searchResults", elements);
					request.setAttribute("total", total);
					session.setAttribute("searchResults", elements);
					pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
					request.setAttribute("pagination", pagination);
					this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

				}

				if (action.equals("chercherProduit")) {
					String motCle = request.getParameter("search");
					Cookie[] mesCookies = request.getCookies();
					if (mesCookies != null) {
						for (Cookie cookie : mesCookies) {
							if (cookie.getName().equals("keyWordSearch")) {
								cookie.setValue(cookie.getValue() + "|" + motCle);
							} else {
								response.addCookie(new Cookie("keyWordSearch", motCle));
							}
						}
					}

					response.sendRedirect(request.getContextPath() + "/search?types=salable&keyword=" + motCle);
				}

			} else {
				if (tousLesArticles == null) {
					tousLesArticles = new ArrayList<>();
					if (session.getAttribute("searchResults") != null) {
						elements = (ArrayList<ElementCommand>) session.getAttribute("searchResults");
					} else {
						elements = new ArrayList<>();
					}
					total = null;
					Long total1 = productDao.countElements(person.getId());
					Long total2 = serviceDao.countElements(person.getId());
					total = total1 + total2;
					tousLesArticles.addAll(productDao.findAllProduct(person.getId(), (long) 10, total1 - 5));
					tousLesArticles.addAll(serviceDao.findAllService(person.getId(), (long) 10, total2 - 5));
					for (int i = 0; i < elements.size(); i++) {
						elements.remove(i);
					}
					for (int i = 0; i < tousLesArticles.size(); i++) {
						ElementCommand elementCom = new ElementCommand();
						elementCom.setmProduct(tousLesArticles.get(i));
						elementCom.setQuantity(1);
						elements.add(elementCom);
					}

					request.setAttribute("searchResults", elements);
					request.setAttribute("total", total);
					session.setAttribute("searchResults", elements);
					pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
					request.setAttribute("pagination", pagination);
				} else {
					elements = (ArrayList<ElementCommand>) session.getAttribute("searchResults");
					request.setAttribute("searchResults", elements);
					total = null;
					Long total1 = productDao.countElements(person.getId());
					Long total2 = serviceDao.countElements(person.getId());
					total = total1 + total2;
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
					request.setAttribute("pagination", pagination);
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}

	public ArrayList<ElementCommand> removeDoublons(ArrayList<ElementCommand> memb) {
		ArrayList<ElementCommand> membA = new ArrayList<>();
		membA.addAll(memb);
		for (int i = 0; i < membA.size(); i++) {
			ElementCommand o = membA.get(i);
			for (int i1 = i + 1; i1 < membA.size(); i1++) {
				ElementCommand r = membA.get(i1);
				if (o.getmProduct().getId() == r.getmProduct().getId()
						&& o.getmProduct().getType().equals(r.getmProduct().getType())) {
					membA.remove(r);
					System.out.println("ServletOrder.removeDoublons() effectué!");
					membA.get(i).setQuantity(membA.get(i).getQuantity() + 1);
				}
			}
		}

		return membA;
	}
}
