package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Person;
import dao.PersonDao;
import dao.UsineDao;
import forms.FormInscription;

@SuppressWarnings("serial")
public class ServletInscription extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "usinedao";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/inscription.jsp";
	private PersonDao personDao;

	
	public void init() throws ServletException {
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();		

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Person utilisateur = null;
	

		FormInscription form = new FormInscription(personDao);

		utilisateur = form.inscrireUtilisateur(request);
		System.out.println("Utilisateur inscrit");

		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		if (utilisateur != null) {
			response.sendRedirect(request.getContextPath()+"/connexion");
		} else {
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
}