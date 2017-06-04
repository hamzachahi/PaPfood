package vue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Person;
import dao.ConnectionDao;
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
	public static final String CONF_DAO_FACTORY = "usinedao";

	private PersonDao utilisateurDao;
	private ConnectionDao connectionDao;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormulaireConnexion form = new FormulaireConnexion(utilisateurDao);

		System.out.println("Rue et numéro " + request.getParameter("num") + " " + request.getParameter("rue") + " "
				+ request.getParameter("cp") + " " + request.getParameter("adr") + " " + request.getParameter("dpt")
				+ " " + request.getParameter("pays"));
		Person utilisateur = null;
		HttpSession session = request.getSession();
		try {
			utilisateur = form.connecterUtilisateur(request);
			connectionDao.createConnexion(utilisateur.getId(), request.getRemoteAddr(), utilisateur.getFunction());
			Long idConnexion = connectionDao.getLastConnexionIdByIdConnected(utilisateur.getId());
			utilisateur.setLastConnexion(idConnexion);
			utilisateurDao.modifyPersonalInformation(utilisateur);
			session.setAttribute("idConnexion", idConnexion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (form.getErreurs().isEmpty()) {
			session.setAttribute(ATT_SESSION_USER, utilisateur);
			request.setAttribute(ATT_FORM, form);
			request.setAttribute(ATT_USER, utilisateur);
			String num = request.getParameter("num");
			String rue = request.getParameter("rue");
			String cp = request.getParameter("cp");
			String adr = request.getParameter("adr");
			String dpt = request.getParameter("dpt");
			String pays = request.getParameter("pays");
			session.setAttribute("sessionNum", num);
			session.setAttribute("sessionRue", rue);
			session.setAttribute("sessionCp", cp);
			session.setAttribute("sessionAdr", adr);
			session.setAttribute("sessionDpt", dpt);
			session.setAttribute("sessionPays", pays);
			request.setAttribute("num", request.getParameter("num"));
			request.setAttribute("rue", request.getParameter("rue"));
			request.setAttribute("cp", request.getParameter("cp"));
			request.setAttribute("adr", request.getParameter("adr"));
			request.setAttribute("dpt", request.getParameter("dpt"));
			request.setAttribute("pays", request.getParameter("pays"));
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

	public void init() throws ServletException {
		this.utilisateurDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		this.connectionDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getConnectiontionDao();
	}

}