package vue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Fichier;
import beans.Person;
import dao.PersonDao;
import dao.UsineDao;
import forms.FormUpload;

/**
 * Servlet implementation class ServletUpload
 */
@WebServlet("/ServletUpload")
@MultipartConfig
public class ServletUpload extends HttpServlet {
	private PersonDao utilisateurDao = null;

	private static final long serialVersionUID = 1L;
	public static final String CHEMIN = "chemin";
	public static final String ATT_FICHIER = "fichier";
	public static final String ATT_FORM = "formfile";
	public static final String VUE = "/WEB-INF/profile.jsp";
	String chemin = null;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.utilisateurDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormUpload form = null;
		Fichier fichier = null;
		Person utilisateur = null;
		HttpSession session = request.getSession(false);
		form = new FormUpload(getServletContext().getRealPath("/"));
		fichier = form.writeFile(request);
		utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		utilisateur.setAccountPicture(fichier.getNom(), false);
		utilisateurDao.modifyPersonalInformation(utilisateur);
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_FICHIER, fichier);
		request.setAttribute("courtnom", fichier.getCourtNom());
		session.setAttribute("sessionUtilisateur", utilisateur);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
}
