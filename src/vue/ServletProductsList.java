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
import beans.Paginateur;
import beans.Salable;
import dao.DaoProductImpl;
import dao.UsineDao;

@WebServlet("/ServletProductsList")
public class ServletProductsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private ArrayList<ElementCommand> elements;
	private ArrayList<Salable> tousLesProduits;
	String pagination = "";
	Long begin = null;
	Long end = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("afficherSousVendables")) {
				begin = Long.parseLong(request.getParameter("begin"));
				end = Long.parseLong(request.getParameter("end"));

				String message = "";
				String pagination = "";
				Long total = productDao.countElements();
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
					tousLesProduits.addAll(productDao.findAllProduct(begin, end));
					request.setAttribute("listProduits", tousLesProduits);
					request.setAttribute("total", total);
				}
				System.out.println("Nombre d'utilisateurs dans la base : " + total);
				pagination =Paginateur.pagine(total, tousLesProduits, request, "produits");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("listProduits", tousLesProduits);
				request.setAttribute("message", message);
			}

		} else {
			if (tousLesProduits == null) {
				tousLesProduits = new ArrayList<>();
				HttpSession session = request.getSession();
				Long total = productDao.countElements();
				tousLesProduits.addAll(productDao.findAllProduct((long) 10, (long) 0));
				request.setAttribute("listProduits", tousLesProduits);
				request.setAttribute("total", total);
				session.setAttribute("tousLesProduits", elements);
				pagination = Paginateur.pagine(total, tousLesProduits, request, "produits");
				request.setAttribute("pagination", pagination);
			} else {
				request.setAttribute("listProduits", tousLesProduits);
				Long total = productDao.countElements();
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, tousLesProduits, request, "produits");
				request.setAttribute("pagination", pagination);
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/listProduits.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
