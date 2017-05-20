package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ElementCommand;

@WebServlet("/ServletPanier")
public class ServletPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<ElementCommand> monPanier = new ArrayList<>();

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
			if (action.equals("removeSalable")) {
				HttpSession session = request.getSession();
				int i = Integer.parseInt(request.getParameter("indice"));
				monPanier.remove(i);
				request.setAttribute("articlesPanier", monPanier);
				session.setAttribute("monPanier", monPanier);
				for (int i1 = 0; i1 < monPanier.size(); i1++) {
					total = total + monPanier.get(i1).getmProduct().getPrice();
				}
				request.setAttribute("total", total);
				session.setAttribute("prixtotal", total);
				this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
			}
		} else {
			HttpSession session = request.getSession();
			monPanier = (ArrayList) session.getAttribute("monPanier");
			request.setAttribute("articlesPanier", monPanier);
			for (int i = 0; i < monPanier.size(); i++) {
				total = total + monPanier.get(i).getmProduct().getPrice();
			}
			request.setAttribute("total", total);
			session.setAttribute("prixtotal", total);
			this.getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
		}

	}
}