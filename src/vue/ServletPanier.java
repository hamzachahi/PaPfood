package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Salable;

@WebServlet("/ServletPanier")
public class ServletPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);

		String action = request.getParameter("action");
		ArrayList<Salable> tousLesArticles = new ArrayList<>();
		ArrayList<Salable> monPanier = new ArrayList<>();

		if (action.equals("chargerPanier")) {

			HttpSession session = request.getSession();
			tousLesArticles = (ArrayList) session.getAttribute("allArticles");
			String monArticle = request.getParameter("idarticle");

			for (int i = 0; i < tousLesArticles.size(); i++) {

				Salable article = tousLesArticles.get(i);

				if (article.getId().toString().equals(monArticle)) {
					monPanier.add(article);
				}
			}

		}

		else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
		}

		request.setAttribute("articlesPanier", monPanier);
		request.setAttribute("nbArticles", monPanier.size());
	}
}