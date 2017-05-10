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
import forms.FormulaireConnexion;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/Connect")
public class ServletConnect extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -823867703158698451L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/connexion.jsp";
	public boolean isConnected = true;
	private PersonDao utilisateurDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de connexion */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

<<<<<<< HEAD
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		this.utilisateurDao = new DaoPersonImpl(new UsineDao("jdbc:mysql://localhost:3306/papfood", "root", "0000"));
=======
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.utilisateurDao = new DaoPersonImpl(
				new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
>>>>>>> 3f0250fce330c5715209553db8cb785a6a0ffd84

		/* Préparation de l'objet formulaire */
		FormulaireConnexion form = new FormulaireConnexion(utilisateurDao);

		/* Traitement de la requête et récupération du bean en résultant */
		Person utilisateur = null;
		try {
			utilisateur = form.connecterUtilisateur(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Récupération de la session depuis la requête */
		HttpSession session = request.getSession();

<<<<<<< HEAD
        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, utilisateur);
			if (utilisateur != null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			}else{
				form.setResultat("Echec de la connexion!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

			}
		} else {
			form.setResultat("Echec de la connexion!");
			session.setAttribute(ATT_SESSION_USER, null);
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

		}
=======
		String action = request.getParameter("action");
>>>>>>> 3f0250fce330c5715209553db8cb785a6a0ffd84

		/**
		 * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
		 * Utilisateur à la session, sinon suppression du bean de la session.
		 */
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			session.setAttribute("loggedIn", isConnected);
		} else {
			session.setAttribute(ATT_SESSION_USER, null);
		}

		/* Stockage du formulaire et du bean dans l'objet request */
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);

		if (action != null) {
			if (action.equals("logOut")) {
				session.invalidate();
				// response.sendRedirect();
			}
		}
	}
}