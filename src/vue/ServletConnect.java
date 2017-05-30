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

@WebServlet("/Connect")
public class ServletConnect extends HttpServlet {
	
	private static final long serialVersionUID = -823867703158698451L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/connexion.jsp";
	public boolean isConnected = true;
	private PersonDao utilisateurDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.utilisateurDao = new DaoPersonImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));
		FormulaireConnexion form = new FormulaireConnexion(utilisateurDao);
		System.out.println("Rue et numéro " + request.getParameter("num") + " " + request.getParameter("rue") + " "
				+ request.getParameter("cp")+ " "+request.getParameter("adr") +" " + request.getParameter("dpt") + " "+ request.getParameter("pays"));
		Person utilisateur = null;
		try {
			utilisateur = form.connecterUtilisateur(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, utilisateur);
			if (utilisateur != null) {
				response.sendRedirect(request.getContextPath() + "/accueil");
				session.setAttribute("loggedIn", isConnected);

			} else {
				form.setResultat("Echec de la connexion!");
				this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

			}
		} else {
			form.setResultat("Echec de la connexion!");
			session.setAttribute(ATT_SESSION_USER, null);
			request.setAttribute(ATT_FORM, form);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);

		}
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_USER, utilisateur);
	}
}