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
	public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.jsp";
    private PersonDao utilisateurDao ;
    
    

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		this.utilisateurDao = new DaoPersonImpl(new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));

    	/* Préparation de l'objet formulaire */
        FormulaireConnexion form = new FormulaireConnexion(utilisateurDao);

        /* Traitement de la requête et récupération du bean en résultant */
        Person utilisateur = null;
		try {
			utilisateur = form.connecterUtilisateur( request );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, utilisateur );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}