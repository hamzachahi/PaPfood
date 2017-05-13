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
	private ArrayList<Salable> monPanier = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Double total = 0.0;

		if (action != null) {

		} else {
			HttpSession session = request.getSession();
			monPanier = (ArrayList) session.getAttribute("monPanier");
			request.setAttribute("articlesPanier", monPanier);
			for (int i = 0; i < monPanier.size(); i++) {
				total=total+monPanier.get(i).getPrice();
			}
			request.setAttribute("total", total);
			this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
		}
	}
}