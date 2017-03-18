package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Person;
import dao.DaoPersonImpl;
import dao.PersonDao;
import dao.UsineDao;
import forms.FormInscription;

@SuppressWarnings("serial")
public class ServletInscription extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/inscription.jsp";
	private PersonDao utilisateurDao;


	public ServletInscription() {
		super();
		this.utilisateurDao = new DaoPersonImpl(new UsineDao("jdbc:mysql://localhost:3306/papfood", "root", "0000"));

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		this.utilisateurDao = new DaoPersonImpl(new UsineDao("jdbc:mysql://localhost:3306/papfood", "root", "0000"));

		FormInscription form = new FormInscription(utilisateurDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Person utilisateur = form.inscrireUtilisateur(request);

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}