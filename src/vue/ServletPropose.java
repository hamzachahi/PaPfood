package vue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Paginateur;
import beans.Person;
import beans.Product;
import beans.Salable;
import beans.Service;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

/**
 * Servlet implementation class ServletPropose
 */
@WebServlet("/ServletPropose")
public class ServletPropose extends HttpServlet {
	private DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private Person utilisateur = null;
	private ArrayList<Salable> listProdSerFinal = new ArrayList<>();
	private Paginateur paginateur = new Paginateur();
	private ArrayList<Salable> listProdSer = new ArrayList<>();

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (request.getSession(false) == null) {

			response.sendRedirect(request.getContextPath() + "/connexion");

		} else {
			utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			listProdSer.addAll(productDao.findAllProductById(utilisateur.getId()));
			listProdSer.addAll(serviceDao.findAllServiceById(utilisateur.getId()));
			request.setAttribute("listeDeSalable", listProdSer);
			this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Person utilisateur = null;
		String action = request.getParameter("action");
		utilisateur = (Person) session.getAttribute("sessionUtilisateur");
		String nom = null;
		String monChoix = null;
		Double prix = null;
		String description = null;
		Long idProvider = null;
		String code = null;

		if (action.equals("proposerProductService") && session != null) {

			nom = request.getParameter("nom");
			monChoix = request.getParameter("type");
			prix = Double.parseDouble(request.getParameter("prix"));
			// à compléter les sous produits
			description = request.getParameter("description");
			// à récupérer : photo

			idProvider = utilisateur.getId();
			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dat = dateFormat.format(actuelle);
			code = dat;
			code = code + monChoix.substring(0, 3);
			code = code + idProvider;

			if (monChoix.equals("product")) {

				Product produit = new Product();

				produit.setName(nom, false);
				produit.setPrice(prix, false);
				produit.setDescription(description, false);
				produit.setIdProvider(utilisateur.getId());
				produit.setCode(code);

				System.out.println(produit.toString());

				Boolean success = productDao.addProduct(produit);
				if (success == true) {
					request.setAttribute("success", "Votre offre à bien été enregistrée!");
					response.sendRedirect(request.getContextPath() + "/proposer");

				} else {
					request.setAttribute("success", "Votre offre n'a pas été enregistrée!");
					response.sendRedirect(request.getContextPath() + "/proposer");

				}

			}

			else {
				Service service = new Service();

				service.setName(nom, false);
				service.setPrice(prix, false);
				service.setDescription(description, false);
				service.setIdProvider(utilisateur.getId());
				service.setCode(code);
				System.out.println(service.toString());

				Boolean success = serviceDao.addService(service);
				if (success == true) {
					request.setAttribute("success", "Votre offre à bien été enregistrée!");
					this.getServletContext().getRequestDispatcher(request.getContextPath() + "/proposer")
							.forward(request, response);

				} else {
					request.setAttribute("success", "Votre offre n'a pas été enregistrée!");
					this.getServletContext().getRequestDispatcher(request.getContextPath() + "/proposer")
							.forward(request, response);

				}
			}

		}
		if (action.equals("afficherSousVendables")) {
			String forwardTo = "";
			String message = "";
			int begin = 0;
			int end = 10;
			String pagination = "";
			Integer total = listProdSer.size();
			if (total <= 0 || total == null) {
				message = "Aucun sous-éléments à afficher!!";
			} else {
				message = "Liste des éléments trouvés";

			}
			System.out.println("Nombre d'utilisateurs dans la base : " + total);
			begin = Integer.parseInt(request.getParameter("begin"));
			end = Integer.parseInt(request.getParameter("end"));
			pagination = paginateur.pagine(total, listProdSer, request);
			System.out.println("Pagination effectuée!");
			request.setAttribute("pagination", pagination);
			System.out.println("Pagination settée!");
			request.setAttribute("total", total);
			// forwardTo = request.getContextPath() +
			// "/proposer?action=afficherSousVendables&begin=" + begin + "&end="
			// + end + "";
			// RequestDispatcher dp = request.getRequestDispatcher(forwardTo +
			// "&message=" + message);

			// dp.forward(request, response);

		}
		if (action.equals("addSalable")) {
			Integer indice = Integer.parseInt(request.getParameter("indice"));
			listProdSerFinal.add(listProdSer.get(indice));
			request.setAttribute("success", "Elément ajouté!");

		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);

	}

}
