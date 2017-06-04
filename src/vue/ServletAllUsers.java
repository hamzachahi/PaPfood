package vue;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Paginateur;
import beans.Person;
import dao.PersonDao;
import dao.UsineDao;

@WebServlet("/ServletAllUsers")
public class ServletAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Person> allUsers;
	private PersonDao personDao;
	Long total;
	Long begin;
	Long end;
	String pagination;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) != null) {
			if (request.getParameter("action") != null) {
				String action = request.getParameter("action");
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));

					String message = "";
					String pagination = "";
					total = personDao.countAllUsers();

					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						allUsers = personDao.findAllUsers(end, begin);
						request.setAttribute("allUsers", allUsers);
						request.setAttribute("total", total);
						request.setAttribute("message", message);
					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, allUsers, request, "allusers");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("allUsers", allUsers);
					request.setAttribute("message", message);
				}

			} else {
				allUsers = personDao.findAllUsers(Long.valueOf(10), Long.valueOf(0));
				total = personDao.countAllUsers();
				request.setAttribute("allUsers", allUsers);
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, allUsers, request, "allusers");
				request.setAttribute("pagination", pagination);
				request.setAttribute("allUsers", allUsers);
			}

			this.getServletContext().getRequestDispatcher("/WEB-INF/listUsers.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
