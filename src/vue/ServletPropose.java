package vue;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;
import forms.FormUpload;

@WebServlet("/ServletPropose")
@MultipartConfig
public class ServletPropose extends HttpServlet {
	private DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private Person utilisateur = null;
	// private ArrayList<Salable> listProdSerFinal = new ArrayList<>();
	// private ArrayList<Salable> listProdSer = null;
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
		FormUpload form = null;
		Fichier fichier = null;
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");
		if (request.getSession(false) == null) {
			response.sendRedirect(request.getContextPath() + "/connexion");
		} else if (request.getSession(false) != null && action == null) {
			utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			/*
			 * listProdSer = new ArrayList<>();
			 * listProdSer.addAll(productDao.findAllProductByIdProvider(
			 * utilisateur.getId(), (long) 10, (long) 0));
			 * listProdSer.addAll(serviceDao.findAllServiceByIdProvider(
			 * utilisateur.getId(), (long) 10, (long) 0));
			 * request.setAttribute("listeDeSalable", listProdSer);
			 */
			request.setAttribute("buttonvalue", "Ajouter");
			request.setAttribute("actionvalue", "proposerProductService");
			this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);

		} else {
			if (action != null) {
				utilisateur = (Person) session.getAttribute("sessionUtilisateur");
				if (action.equals("proposerProductService") && session != null) {
					String nom = null;
					String monChoix = null;
					Double prix = null;
					String description = null;
					String code = null;
					Long idProvider = null;
					String departement = null;
					String latLng = null;
					String streetNumber = null;
					String streetName = null;
					String city = null;
					String country = null;
					String postalCode = null;
					nom = request.getParameter("nom");
					monChoix = request.getParameter("type");
					prix = Double.parseDouble(request.getParameter("prix"));
					departement = request.getParameter("dpt");
					latLng = request.getParameter("latlng");
					streetName = request.getParameter("rue");
					streetNumber = request.getParameter("num");
					city = request.getParameter("adr");
					country = request.getParameter("pays");
					postalCode = request.getParameter("cp");
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
					form = new FormUpload(getServletContext().getRealPath("/"));
					fichier = form.writeFile(request);
					if (monChoix.equals("product")) {
						Product produit = new Product();
						produit.setName(nom, false);
						produit.setPrice(prix, false);
						produit.setDescription(description, false);
						produit.setIdProvider(utilisateur.getId());
						produit.setCode(code);
						produit.setCity(city);
						produit.setDepartement(departement);
						produit.setLatLng(latLng);
						produit.setPostalCode(postalCode);
						produit.setStreetName(streetName);
						produit.setStreetNumber(streetNumber);
						produit.setCountry(country);
						if (fichier.getNom() != null) {
							produit.setMainImage(fichier.getNom());
						}
						System.out.println(produit.toString());
						Boolean success = productDao.addProduct(produit);
						if (success == true) {
							request.setAttribute("success", "Votre offre à bien été enregistrée!");
							request.setAttribute("buttonvalue", "Ajouter");
							request.setAttribute("actionvalue", "proposerProductService");
							this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request,
									response);
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
						service.setCity(city);
						service.setDepartement(departement);
						service.setLatLng(latLng);
						service.setPostalCode(postalCode);
						service.setStreetName(streetName);
						service.setStreetNumber(streetNumber);
						service.setCountry(country);
						if (fichier.getNom() != null) {
							service.setMainImage(fichier.getNom());
						}
						System.out.println(service.toString());
						Boolean success = serviceDao.addService(service);
						if (success == true) {
							request.setAttribute("success", "Votre offre à bien été enregistrée!");
							request.setAttribute("buttonvalue", "Ajouter");
							request.setAttribute("actionvalue", "proposerProductService");
							this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request,
									response);
						} else {
							request.setAttribute("success", "Votre offre n'a pas été enregistrée!");
						}
					}

				}
				/*
				 * if (action.equals("afficherSousVendables")) { begin =
				 * Long.parseLong(request.getParameter("begin")); end =
				 * Long.parseLong(request.getParameter("end")); String message =
				 * ""; String pagination = ""; Long total =
				 * productDao.countElementsByIdProvider(utilisateur.getId());
				 * total = total +
				 * serviceDao.countElementsByIdProvider(utilisateur.getId()); if
				 * (total <= 0 || total == null) { message =
				 * "Aucun sous-éléments à afficher!!"; } else { message =
				 * "Liste des éléments trouvés"; listProdSer = new
				 * ArrayList<>();
				 * listProdSer.addAll(productDao.findAllProductByIdProvider(
				 * utilisateur.getId(), end, begin));
				 * listProdSer.addAll(serviceDao.findAllServiceByIdProvider(
				 * utilisateur.getId(), end, begin));
				 * request.setAttribute("listProduits", listProdSer);
				 * request.setAttribute("total", total);
				 * 
				 * } System.out.println("Nombre d'utilisateurs dans la base : "
				 * + total); pagination = Paginateur.pagine(total, listProdSer,
				 * request, "proposer");
				 * System.out.println("Pagination effectuée!");
				 * request.setAttribute("pagination", pagination);
				 * System.out.println("Pagination settée!");
				 * request.setAttribute("total", total);
				 * request.setAttribute("listeDeSalablesCut", listProdSer);
				 * request.setAttribute("message", message);
				 * session.setAttribute("beginpr", begin);
				 * session.setAttribute("endpr", end);
				 * this.getServletContext().getRequestDispatcher(
				 * "/WEB-INF/proposer.jsp").forward(request, response);
				 * 
				 * } if (action.equals("addSalable")) { begin = (long)
				 * session.getAttribute("beginpr"); end = (long)
				 * session.getAttribute("endpr"); int indice =
				 * Integer.parseInt(request.getParameter("indice"));
				 * listProdSerFinal.add(listProdSer.get(indice));
				 * request.setAttribute("success", "Elément ajouté!");
				 * request.setAttribute("listeDeSousSalables",
				 * listProdSerFinal); utilisateur = (Person)
				 * session.getAttribute("sessionUtilisateur");
				 * listProdSer.addAll(productDao.findAllProductByIdProvider(
				 * utilisateur.getId(), end, begin));
				 * listProdSer.addAll(serviceDao.findAllServiceByIdProvider(
				 * utilisateur.getId(), end, begin));
				 * request.setAttribute("listeDeSalable", listProdSer); } if
				 * (action.equals("removeSalable")) { // begin = (long)
				 * session.getAttribute("beginpr"); // end = (long)
				 * session.getAttribute("endpr"); // int index = //
				 * Integer.parseInt(request.getParameter("index")); //
				 * listProdSerFinal.remove(index); //
				 * System.out.println("Elément retiré!"); //
				 * request.setAttribute("success", "Elément retiré!"); //
				 * request.setAttribute("listeDeSousSalables", //
				 * listProdSerFinal); // utilisateur = (Person) //
				 * session.getAttribute("sessionUtilisateur"); //
				 * listProdSer.addAll(productDao.findAllProductByIdProvider(
				 * utilisateur.getId(), // end, begin)); //
				 * listProdSer.addAll(serviceDao.findAllServiceByIdProvider(
				 * utilisateur.getId(), // end, begin)); //
				 * request.setAttribute("listeDeSalable", listProdSer); }
				 */
				if (action.equals("modify")) {
					Long target = Long.parseLong(request.getParameter("target"));
					session.setAttribute("idsalabletomofy", target);
					String gender = request.getParameter("gender");
					session.setAttribute("genderofsalabletomodify", gender);

					if (gender.equals("Produit")) {

						Product produit = productDao.findProductById(target);
						request.setAttribute("tomodify", produit.getId());
						request.setAttribute("nom", produit.getName());
						request.setAttribute("description", produit.getDescription());
						request.setAttribute("prix", produit.getPrice());
						request.setAttribute("mainimage", produit.getMainImage());
						request.setAttribute("streetnum", produit.getStreetNumber());
						request.setAttribute("streetname", produit.getStreetName());
						request.setAttribute("city", produit.getCity());
						request.setAttribute("postcod", produit.getPostalCode());
						request.setAttribute("depart", produit.getDepartement());
						request.setAttribute("country", produit.getCountry());
						request.setAttribute("latlngt", produit.getLatLng());
						request.setAttribute("typep", "produit");
						request.setAttribute("buttonvalue", "Modifier");
						request.setAttribute("actionvalue", "modiferSalable");

					}

					else {
						Service service = serviceDao.findServiceById(target);
						request.setAttribute("tomodify", service.getId());
						request.setAttribute("nom", service.getName());
						request.setAttribute("description", service.getDescription());
						request.setAttribute("prix", service.getPrice());
						request.setAttribute("typep", "service");
						request.setAttribute("streetnum", service.getStreetNumber());
						request.setAttribute("streetname", service.getStreetName());
						request.setAttribute("city", service.getCity());
						request.setAttribute("postcod", service.getPostalCode());
						request.setAttribute("depart", service.getDepartement());
						request.setAttribute("country", service.getCountry());
						request.setAttribute("latlngt", service.getLatLng());
						request.setAttribute("buttonvalue", "Modifier");
						request.setAttribute("actionvalue", "modiferSalable");
					}
					this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request, response);
				}
				if (action.equals("modiferSalable")) {
					String nom = null;
					String monChoix = null;
					Double prix = null;
					String description = null;
					String code = null;
					Long idProvider = null;
					String departement = null;
					String latLng = null;
					String streetNumber = null;
					String streetName = null;
					String city = null;
					String country = null;
					String postalCode = null;
					Long target = Long.parseLong(request.getParameter("tomodify"));
					nom = request.getParameter("nom");
					monChoix = request.getParameter("type");
					prix = Double.parseDouble(request.getParameter("prix"));
					departement = request.getParameter("dpt");
					latLng = request.getParameter("latlng");
					streetName = request.getParameter("rue");
					streetNumber = request.getParameter("num");
					city = request.getParameter("adr");
					country = request.getParameter("pays");
					postalCode = request.getParameter("cp");
					// à compléter les sous produits
					description = request.getParameter("description");
					// à récupérer : photo
					Date actuelle = new Date();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String dat = dateFormat.format(actuelle);
					code = dat;
					code = code + monChoix.substring(0, 3);
					code = code + idProvider;

					if (monChoix.equals("product")) {

						Product produit = new Product();
						produit.setId(target);
						produit.setName(nom, false);
						produit.setPrice(prix, false);
						produit.setDescription(description, false);
						produit.setIdProvider(utilisateur.getId());
						produit.setCode(code);
						produit.setCity(city);
						produit.setDepartement(departement);
						produit.setLatLng(latLng);
						produit.setPostalCode(postalCode);
						produit.setStreetName(streetName);
						produit.setStreetNumber(streetNumber);
						produit.setCountry(country);
						System.out.println(produit.toString());
						Boolean success = productDao.modifyProduct(produit);
						if (success == true) {
							request.setAttribute("success", "Votre modification a bien été effectuée!");
							request.setAttribute("buttonvalue", "Ajouter");
							request.setAttribute("actionvalue", "proposerProductService");
							this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request,
									response);

						} else {
							request.setAttribute("success", "Votre modification n'a pas été effectuée!");
							response.sendRedirect(request.getContextPath() + "/proposer?action=modify&target=" + target
									+ "&gender=Produit");
						}
					}

					else {
						Service service = new Service();
						service.setId(target);
						service.setName(nom, false);
						service.setPrice(prix, false);
						service.setDescription(description, false);
						service.setIdProvider(utilisateur.getId());
						service.setCode(code);
						service.setCity(city);
						service.setDepartement(departement);
						service.setLatLng(latLng);
						service.setPostalCode(postalCode);
						service.setStreetName(streetName);
						service.setStreetNumber(streetNumber);
						service.setCountry(country);
						System.out.println(service.toString());
						Boolean success = serviceDao.modifyService(service);
						if (success == true) {
							request.setAttribute("success", "Votre offre à bien été enregistrée!");

						} else {
							request.setAttribute("success", "Votre offre n'a pas été enregistrée!");
							request.setAttribute("buttonvalue", "Ajouter");
							request.setAttribute("actionvalue", "proposerProductService");
							this.getServletContext().getRequestDispatcher("/WEB-INF/proposer.jsp").forward(request,
									response);
							response.sendRedirect(request.getContextPath() + "/proposer?action=modify&target=" + target
									+ "&gender=Service");

						}
					}
					request.setAttribute("buttonvalue", "Ajouter");
					request.setAttribute("actionvalue", "proposerProductService");
				}
			}
		}
	}

}
