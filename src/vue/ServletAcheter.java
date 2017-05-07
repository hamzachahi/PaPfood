package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Product;
import dao.DaoProductImpl;

/**
 * Servlet implementation class ServletAcheter
 */
@WebServlet("/ServletAcheter")
public class ServletAcheter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * DaoProductImpl productDao = null;
		 * 
		 * ArrayList<Product> tousLesProduits = new ArrayList<Product>();
		 * tousLesProduits = productDao.findAllProduct();
		 * 
		 * request.setAttribute("allProducts", tousLesProduits);
		 */

		this.getServletContext().getRequestDispatcher("/WEB-INF/plats.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
