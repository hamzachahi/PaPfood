package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Person;
import dao.DaoPersonImpl;
import dao.DaoProductImpl;
import dao.UsineDao;

@WebServlet("/ServletAllUsers")
public class ServletAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoPersonImpl personDao = new DaoPersonImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

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
