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
import dao.DaoServiceImpl;
import dao.UsineDao;


@WebServlet("/ServletServicesList")
public class ServletServicesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private ArrayList<ElementCommand> elements;
	private ArrayList<Salable> tousLesServices;
	String pagination = "";
	Long begin = null;
	Long end = null;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("afficherSousVendables")) {
				begin = Long.parseLong(request.getParameter("begin"));
				end = Long.parseLong(request.getParameter("end"));

				String message = "";
				String pagination = "";
				Long total = serviceDao.countElements();
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
					tousLesServices.addAll(serviceDao.findAllService(begin, end));
					request.setAttribute("listProduits", tousLesServices);
					request.setAttribute("total", total);
				}
				System.out.println("Nombre d'utilisateurs dans la base : " + total);
				pagination =Paginateur.pagine(total, tousLesServices, request, "produits");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("listProduits", tousLesServices);
				request.setAttribute("message", message);
			}

		} else {
			if (tousLesServices == null) {
				tousLesServices = new ArrayList<>();
				HttpSession session = request.getSession();
				Long total = serviceDao.countElements();
				tousLesServices.addAll(serviceDao.findAllService((long) 10, (long) 0));
				request.setAttribute("listProduits", tousLesServices);
				request.setAttribute("total", total);
				session.setAttribute("tousLesServices", elements);
				pagination = Paginateur.pagine(total, tousLesServices, request, "produits");
				request.setAttribute("pagination", pagination);
			} else {
				request.setAttribute("listProduits", tousLesServices);
				Long total = serviceDao.countElements();
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, tousLesServices, request, "produits");
				request.setAttribute("pagination", pagination);
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/listServices.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
