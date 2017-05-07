package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Product;
import beans.Service;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

/**
 * Servlet implementation class ServletPropose
 */
@WebServlet("/ServletPropose")
public class ServletPropose extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute("utilisateur");
		String action = request.getParameter("action");
		String nom = null;
		String monChoix = null;
		Double prix = null;
		String description = null;

		if (action.equals("proposerProductService")) {

			nom = request.getParameter("nom");
			monChoix = request.getParameter("type");
			prix = Double.parseDouble(request.getParameter("prix"));
			// à compléter les sous produits
			description = request.getParameter("description");
			// à récupérer : photo

			// à récupérer id provider du user de la session

		}

		if (monChoix.equals("product")) {

			Product produit = new Product();

			produit.setName(nom, false);
			produit.setPrice(prix, false);
			produit.setDescription(description, false);

			System.out.println(produit.toString());

			/*
			 * DaoProductImpl productDao = new DaoProductImpl( new
			 * UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood",
			 * "yummyshop"));
			 * 
			 * Boolean success = productDao.addProduct(produit);
			 * request.setAttribute("success", success);
			 */
		}

		else {
			Service service = new Service();

			service.setName(nom, false);
			service.setPrice(prix, false);
			service.setDescription(description, false);

			System.out.println(service.toString());

			/*DaoServiceImpl serviceDao = new DaoServiceImpl(
					new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
			Boolean success = serviceDao.addService(service);

			request.setAttribute("success", success);*/

		}

	}

}
