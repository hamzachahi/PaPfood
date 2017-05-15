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
import dao.DaoPersonImpl;
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
	public static final String ATT_FORM = "form";

	public static final String VUE = "/WEB-INF/profile.jsp";
	String chemin = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormUpload form = null;
		Fichier fichier = null;
		Person utilisateur = null;
		HttpSession session = request.getSession(false);
		System.out.println("Je charge la photo");
		// chemin = this.getServletConfig().getInitParameter(CHEMIN);
		System.out.println("Chemin récupéré : " + chemin);
		this.utilisateurDao = new DaoPersonImpl(new UsineDao(
				"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true",
				"root", "0000"));
		form = new FormUpload(false);

		fichier = form.enregistrerFichier(request, chemin);

		utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		utilisateur.setAccountPicture(fichier.getNom(), false);
		utilisateurDao.modifyPersonalInformation(utilisateur);
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_FICHIER, fichier);
		request.setAttribute("courtnom", fichier.getCourtNom());
		session.setAttribute("sessionUtilisateur", utilisateur);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		chemin = getInitParameter("addressfile");
	}
}
