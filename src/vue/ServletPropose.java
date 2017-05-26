package vue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private ArrayList<Salable> listProdSer = null;
	Long begin = null;
	Long end = null;
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (request.getSession(false) == null) {

			response.sendRedirect(request.getContextPath() + "/connexion");
		} else if (request.getSession(false) != null && listProdSer == null) {
			listProdSer = new ArrayList<>();
			utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			listProdSer.addAll(productDao.findAllProductByIdProvider(utilisateur.getId(), (long) 10, (long) 0));
			listProdSer.addAll(serviceDao.findAllServiceByIdProvider(utilisateur.getId(), (long) 10, (long) 0));
			request.setAttribute("listeDeSalable", listProdSer);
		} else {
			if (request.getParameter("action") != null) {
				String action = request.getParameter("action");

				if (action.equals("proposerProductService") && session != null) {
					String nom = null;
					String monChoix = null;
					Double prix = null;
					String description = null;
					String code = null;
					Long idProvider = null;					
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
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));
					String message = "";
					String pagination = "";
					Long total = productDao.countElementsByIdProvider(utilisateur.getId());
					total = total + serviceDao.countElementsByIdProvider(utilisateur.getId());
					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						listProdSer=new ArrayList<>();
						listProdSer.addAll(
								productDao.findAllProductByIdProvider(utilisateur.getId(), end, begin));
						listProdSer.addAll(
								serviceDao.findAllServiceByIdProvider(utilisateur.getId(), end, begin));
						request.setAttribute("listProduits", listProdSer);
						request.setAttribute("total", total);

					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, listProdSer, request, "proposer");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("listeDeSalablesCut", listProdSer);
					request.setAttribute("message", message);
				}
				if (action.equals("addSalable")) {
					int indice = Integer.parseInt(request.getParameter("indice"));
					listProdSerFinal.add(listProdSer.get(indice));
					request.setAttribute("success", "Elément ajouté!");
					request.setAttribute("listeDeSousSalables", listProdSerFinal);
				}
				if (action.equals("removeSalable")) {
					int index = Integer.parseInt(request.getParameter("index"));
					listProdSerFinal.remove(index);
					System.out.println("Elément retiré!");
					request.setAttribute("success", "Elément retiré!");
					request.setAttribute("listeDeSousSalables", listProdSerFinal);
				}

			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);
	}

}
