package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletDisconnect extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 161810466362514525L;
	public static final String URL_REDIRECTION = "http://localhost:2020/PaPfood/connexion";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();

        /* Redirection vers le Site du Zéro ! */
		response.sendRedirect(request.getContextPath()+"/connexion");
    }
}