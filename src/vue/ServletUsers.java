package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Person;
import dao.PersonDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletUsers
 */
@WebServlet("/ServletUsers")
public class ServletUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "usinedao";
	PersonDao personDao;
	public void init() throws ServletException {
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		

	}

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

		HttpSession session = request.getSession();
		@SuppressWarnings("unused")
		Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");

		/*
		 * String action = request.getParameter("action"); Long id =
		 * Long.parseLong(request.getParameter("id"));
		 * 
		 * Person user = personDao.trouverParId(id, true);
		 * request.setAttribute("user", user);
		 */

	}
}
