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
import beans.Product;
import beans.Service;
import dao.ProductDao;
import dao.ServiceDao;
import dao.UsineDao;
import forms.FormUpload;

/**
 * Servlet implementation class ServletUploadSalable
 */
@WebServlet("/ServletUploadSalable")
@MultipartConfig
public class ServletUploadSalable extends HttpServlet {
	private ServiceDao serviceDao;
	private ProductDao productDao;
	private static final long serialVersionUID = 1L;
	public static final String CHEMIN = "chemin";
	public static final String ATT_FICHIER = "fichier";
	public static final String ATT_FORM = "formfile";
	public static final String VUE = "/WEB-INF/profile.jsp";
	String chemin = null;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();

	}
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
		form = new FormUpload(getServletContext().getRealPath("/"));
		fichier = form.writeFile(request);
		utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		Long id = (Long) session.getAttribute("idsalabletomofy");
		String type = (String) session.getAttribute("genderofsalabletomodify");
		if (type.equals("Produit")) {

			Product produit = productDao.findProductById(id);
			produit.setMainImage(fichier.getNom());
			produit.setMainImage(fichier.getNom(), false);
			productDao.modifyProduct(produit);

		}

		else {
			Service service = serviceDao.findServiceById(id);
			service.setMainImage(fichier.getNom());
			service.setMainImage(fichier.getNom(), false);
			serviceDao.modifyService(service);
		}
		request.setAttribute(ATT_FORM, form);
		request.setAttribute(ATT_FICHIER, fichier);
		request.setAttribute("courtnom", fichier.getCourtNom());
		session.setAttribute("sessionUtilisateur", utilisateur);
		response.sendRedirect(request.getContextPath() + "/details?type=" + type + "&cible=" + id);
	}

}
