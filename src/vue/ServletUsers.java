package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Person;
import dao.DaoPersonImpl;
import dao.PersonDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletUsers
 */
@WebServlet("/ServletUsers")
public class ServletUsers extends HttpServlet {
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

		HttpSession session = request.getSession();
		Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		PersonDao personDao = new DaoPersonImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));

		/*
		 * String action = request.getParameter("action"); Long id =
		 * Long.parseLong(request.getParameter("id"));
		 * 
		 * Person user = personDao.trouverParId(id, true);
		 * request.setAttribute("user", user);
		 */

	}
}
