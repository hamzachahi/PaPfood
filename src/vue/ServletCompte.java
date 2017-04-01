package vue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PersonDao;

/**
 * Servlet implementation class ServletCompte
 */
@WebServlet("/ServletCompte")
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/compte.jsp";
	
	private PersonDao utilisateurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de "mon Compte */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public PersonDao getUtilisateurDao() {
		return utilisateurDao;
	}

	public void setUtilisateurDao(PersonDao utilisateurDao) {
		this.utilisateurDao = utilisateurDao;
	}

}
