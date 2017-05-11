package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Person;
import dao.DaoPersonImpl;
import dao.UsineDao;
import forms.FormInscription;

@SuppressWarnings("serial")
public class ServletInscription extends HttpServlet {
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/inscription.jsp";
	private DaoPersonImpl utilisateurDao;

	public ServletInscription() {
		super();
		this.utilisateurDao = new DaoPersonImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));

		// this.utilisateurDao = new DaoPersonImpl(new
		// UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood",
		// "yummyshop"));
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		// @SuppressWarnings("unused")
		// LocationInformation location =
		// Geolocalization.getGeoLocationInformation(request);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Préparation de l'objet formulaire */
		Person utilisateur = null;
		this.utilisateurDao = new DaoPersonImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));
		// this.utilisateurDao = new DaoPersonImpl(new
		// UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood",
		// "yummyshop"));

		FormInscription form = new FormInscription(utilisateurDao);

		/* Traitement de la requête et récupération du bean en résultant */
		utilisateur = form.inscrireUtilisateur(request);
		System.out.println("Utilisateur inscrit");

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
		if (utilisateur != null) {
			response.sendRedirect(request.getContextPath()+"/connexion");
		} else {
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		}
	}
}