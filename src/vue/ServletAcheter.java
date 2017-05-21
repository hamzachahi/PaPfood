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
import beans.Salable;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

/**
 * Servlet implementation class ServletAcheter
 */
@WebServlet("/ServletAcheter")
public class ServletAcheter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Salable> tousLesArticles = null;
	private ArrayList<ElementCommand> monPanier = new ArrayList<>();
	ArrayList<ElementCommand> elements = new ArrayList<>();
	Long begin = null;
	Long end = null;
	Long total = null;
	DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	String pagination = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("afficherSousVendables")) {
				HttpSession session = request.getSession();
				begin = Long.parseLong(request.getParameter("begin"));
				end = Long.parseLong(request.getParameter("end"));
				String message = "";
				String pagination = "";
				total = productDao.countElements();
				total = total + serviceDao.countElements();
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					for (int i = 0; i < elements.size(); i++) {
						elements.remove(i);
					}
					message = "Liste des éléments trouvés";
					tousLesArticles = new ArrayList<>();
					tousLesArticles.addAll(productDao.findAllProduct(end, begin));
					tousLesArticles.addAll(serviceDao.findAllService(end, begin));
					for (int i = 0; i < elements.size(); i++) {
						elements.remove(i);
					}
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
			}
			if (action.equals("chargerPanier")) {
				Double totalpanier = 0.0;

				HttpSession session = request.getSession(false);
				if (session.getAttribute("monPanier") != null) {
					monPanier=((ArrayList<ElementCommand>) session.getAttribute("monPanier"));
				}
				int i = Integer.parseInt(request.getParameter("idarticle"));
				ElementCommand article = elements.get(i);
				monPanier.add(article);
				session.setAttribute("nbrelementspanier", monPanier.size());
				request.setAttribute("articlesPanier", monPanier);
				session.setAttribute("monPanier", monPanier);

				for (int i1 = 0; i1 < monPanier.size(); i1++) {
					totalpanier += monPanier.get(i1).getmProduct().getPrice();
				}
				session.setAttribute("prixtotal", totalpanier);
				request.setAttribute("searchResults", elements);
				request.setAttribute("total", total);
				session.setAttribute("searchResults", elements);
				pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
				request.setAttribute("pagination", pagination);
			}

			if (action.equals("chercherProduit")) {
				HttpSession session = request.getSession();
				String motCle = request.getParameter("name");
				tousLesArticles = new ArrayList<>();
				tousLesArticles.addAll(serviceDao.findServiceByKeyWord(motCle));
				tousLesArticles.addAll(productDao.findProductByKeyWord(motCle));
				total = (long) tousLesArticles.size();
				for (int i = 0; i < elements.size(); i++) {
					elements.remove(i);
				}
				for (int i = 0; i < tousLesArticles.size(); i++) {
					ElementCommand elementCom = new ElementCommand();
					elementCom.setmProduct(tousLesArticles.get(i));
					elementCom.setQuantity(0);
					elements.add(elementCom);
				}
				request.setAttribute("total", total);
				request.setAttribute("searchResults", elements);
				session.setAttribute("searchResults", elements);
				pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
				request.setAttribute("pagination", pagination);
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
			}

		} else {
			if (tousLesArticles == null) {
				tousLesArticles = new ArrayList<>();
				HttpSession session = request.getSession();
				total = productDao.countElements();
				total = total + serviceDao.countElements();
				tousLesArticles.addAll(productDao.findAllProduct((long) 10, (long) 0));
				tousLesArticles.addAll(serviceDao.findAllService((long) 10, (long) 0));
				for (int i = 0; i < elements.size(); i++) {
					elements.remove(i);
				}
				for (int i = 0; i < tousLesArticles.size(); i++) {
					ElementCommand elementCom = new ElementCommand();
					elementCom.setmProduct(tousLesArticles.get(i));
					elementCom.setQuantity(0);
					elements.add(elementCom);
				}

				request.setAttribute("searchResults", elements);
				request.setAttribute("total", total);
				session.setAttribute("searchResults", elements);
				pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
				request.setAttribute("pagination", pagination);
			} else {
				request.setAttribute("searchResults", elements);
				total = productDao.countElements();
				total = total + serviceDao.countElements();
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, tousLesArticles, request, "acheter");
				request.setAttribute("pagination", pagination);
			}

		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

	}
}
