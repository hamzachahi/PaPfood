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

import beans.Product;
import beans.Salable;
import beans.Service;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

/**
 * Servlet implementation class ServletAcheter
 */
@WebServlet("/ServletAcheter")
public class ServletAcheter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*HttpSession session = request.getSession();

		DaoServiceImpl serviceDao = new DaoServiceImpl(
				new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
		DaoProductImpl produitDao = new DaoProductImpl(
				new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));


		ArrayList<Salable> tousLesArticles = new ArrayList<>();
		
		tousLesArticles.addAll(produitDao.findAllProduct());
		tousLesArticles.addAll(serviceDao.findAllService());


		request.setAttribute("allArticles", tousLesArticles);
		session.setAttribute("allArticles", tousLesArticles);*/

		this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		ArrayList<Salable> salables = new ArrayList<>();

		if (action.equals("chercherProduit")) {

			String motCle = request.getParameter("name");

			DaoServiceImpl serviceDao = new DaoServiceImpl(
					new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
			DaoProductImpl produitDao = new DaoProductImpl(
					new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));

			salables.addAll(serviceDao.findServiceByKeyWord(motCle));
			salables.addAll(produitDao.findProductByKeyWord(motCle));

			request.setAttribute("searchResults", salables);

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

	}

}
