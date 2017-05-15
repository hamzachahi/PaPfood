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
import beans.MotherProduct;
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

	DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	DaoProductImpl produitDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

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
		String action = request.getParameter("action");
		if (action != null) {
			if (action.equals("chargerPanier")) {
				Double total = 0.0;

				HttpSession session = request.getSession();
				int i = Integer.parseInt(request.getParameter("idarticle"));
				ElementCommand article = elements.get(i);
				monPanier.add(article);
				session.setAttribute("nbrelementspanier", monPanier.size());
				request.setAttribute("articlesPanier", monPanier);
				session.setAttribute("monPanier", monPanier);

				for (int i1 = 0; i1 < monPanier.size(); i1++) {
					total += monPanier.get(i1).getmProduct().getPrice();
				}
				session.setAttribute("prixtotal", total);
			}

			if (action.equals("chercherProduit")) {
				HttpSession session = request.getSession();
				String motCle = request.getParameter("name");
				tousLesArticles.addAll(serviceDao.findServiceByKeyWord(motCle));
				tousLesArticles.addAll(produitDao.findProductByKeyWord(motCle));
				int total = tousLesArticles.size();
				for (int i = 0; i < tousLesArticles.size(); i++) {
					ElementCommand elementCom = new ElementCommand();
					elementCom.setmProduct(tousLesArticles.get(i));
					elementCom.setQuantity(0);
					elements.add(elementCom);
				}
				request.setAttribute("total", total);
				request.setAttribute("searchResults", elements);
				session.setAttribute("searchResults", elements);

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
			this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

		} else {
			if (tousLesArticles == null) {
				tousLesArticles = new ArrayList<>();
				HttpSession session = request.getSession();
				Long total = produitDao.countElements();
				total = total + serviceDao.countElements();
				tousLesArticles.addAll(produitDao.findAllProduct((long) 10, (long) 0));
				tousLesArticles.addAll(serviceDao.findAllService((long) 10, (long) 0));
				for (int i = 0; i < tousLesArticles.size(); i++) {
					ElementCommand elementCom = new ElementCommand();
					elementCom.setmProduct(tousLesArticles.get(i));
					elementCom.setQuantity(0);
					elements.add(elementCom);
				}

				request.setAttribute("searchResults", elements);
				request.setAttribute("total", total);
				session.setAttribute("searchResults", elements);
			} else {
				request.setAttribute("searchResults", tousLesArticles);
				Long total = produitDao.countElements();
				total = total + serviceDao.countElements();
				request.setAttribute("total", total);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

		}
	}
}
