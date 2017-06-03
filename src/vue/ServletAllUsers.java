package vue;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Person;
import dao.PersonDao;
import dao.UsineDao;

@WebServlet("/ServletAllUsers")
public class ServletAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PersonDao personDao;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Person> allUsers = new ArrayList<>();

		allUsers = personDao.findAllUsers();

		request.setAttribute("allUsers", allUsers);

		this.getServletContext().getRequestDispatcher("/WEB-INF/listUsers.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
