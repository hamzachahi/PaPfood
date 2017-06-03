package vue;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Comment;
import beans.ElementCommand;
import beans.Paginateur;
import beans.Person;
import beans.Product;
import beans.Salable;
import beans.Service;
import dao.CommentDao;
import dao.PersonDao;
import dao.ProductDao;
import dao.ServiceDao;
import dao.UsineDao;

@WebServlet("/ServletSalableDetails")
public class ServletSalableDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao ;
	private ServiceDao serviceDao;
	private PersonDao personDao ;
	private CommentDao commentDao;
	Long total = null;
	ArrayList<Comment> listComments = new ArrayList<>();
	private ArrayList<ElementCommand> monPanier;
	private String pagination;
	private long begin;
	private long end;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
		this.commentDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommentDao();
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("boutonvalue", "Enregistrez l'avis");
		request.setAttribute("actionvalue", "commenter");
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	@SuppressWarnings("unchecked")
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Service service = null;
		Product product = null;
		Person person = null;
		String avis = null;
		String type = null;
		Long id = null;
		if (request.getParameter("action") != null && !request.getParameter("action").equals("afficherSousVendables")) {
			id = Long.parseLong(request.getParameter("cible"));
			type = request.getParameter("type");
		}
		String action = request.getParameter("action");
		Comment comment = new Comment();
		Salable salable = null;
		if (action != null && session != null) {
			Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			if (action.equals("commenter")) {
				if (type.equals("Service")) {
					service = serviceDao.findServiceById(id);
					salable = service;
					avis = request.getParameter("avis");
					commentDao.CommentProduct(utilisateur.getId(), service.getId(), avis);
					comment.setContent(avis);
					comment.setAuthor(utilisateur);
					comment.setDatePosted(new Date(System.currentTimeMillis()));
					comment.setIdAuthor(utilisateur.getId());
					if (listComments.size() != 0) {
						listComments.remove(0);
						listComments.add(comment);
					} else {
						listComments.add(comment);
					}
					listComments.remove(0);
					listComments.add(comment);
					request.setAttribute("salable", salable);
					service = serviceDao.findServiceById(id);
					listComments.addAll(commentDao.selectCommentsByIdService(service.getId(), (long) 10, (long) 0));
					System.out.println(service.toString());
					request.setAttribute("salable", service);
					person = personDao.trouverParId(service.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdService(service.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);

				} else {
					product = productDao.findProductById(id);
					salable = product;
					avis = request.getParameter("avis");
					commentDao.CommentProduct(utilisateur.getId(), product.getId(), avis);
					comment.setContent(avis);
					comment.setAuthor(utilisateur);
					comment.setDatePosted(new Date(System.currentTimeMillis()));
					comment.setIdAuthor(utilisateur.getId());
					if (listComments.size() != 0) {
						listComments.remove(0);
						listComments.add(comment);
					} else {
						listComments.add(comment);
					}
					request.setAttribute("salable", salable);
					product = productDao.findProductById(id);
					listComments.addAll(commentDao.selectCommentsByIdService(product.getId(), (long) 10, (long) 0));
					request.setAttribute("salable", product);
					System.out.println(product.toString());
					person = personDao.trouverParId(product.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdProduct(product.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				}
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);
			}
			if (action.equals("chargerPanier")) {
				Double totalpanier = 0.0;
				String typep = request.getParameter("type");
				Long idp = Long.parseLong(request.getParameter("cible"));
				if (session.getAttribute("monPanier") != null) {
					monPanier = ((ArrayList<ElementCommand>) session.getAttribute("monPanier"));
				}else{
					monPanier= new ArrayList<>();
				}
				if (typep.equals("Service")) {
					salable = serviceDao.findServiceById(idp);
					listComments = commentDao.selectCommentsByIdService(salable.getId(), (long) 10, (long) 0);
					total = commentDao.selectNbreCommentsByIdService(salable.getId());

				} else {
					salable = productDao.findProductById(idp);
					listComments = commentDao.selectCommentsByIdProduct(salable.getId(), (long) 10, (long) 0);
					total = commentDao.selectNbreCommentsByIdProduct(salable.getId());

				}
				ElementCommand article = new ElementCommand();
				article.setmProduct(salable);
				article.setQuantity(1);
				monPanier.add(article);
				session.setAttribute("nbrelementspanier", monPanier.size());
				request.setAttribute("articlesPanier", monPanier);
				session.setAttribute("monPanier", monPanier);

				for (int i1 = 0; i1 < monPanier.size(); i1++) {
					totalpanier += monPanier.get(i1).getmProduct().getPrice();
				}
				session.setAttribute("prixtotal", totalpanier);
				request.setAttribute("pagination", pagination);
				request.setAttribute("salable", salable);
				System.out.println(salable.toString());
				request.setAttribute("salable", salable);
				person = personDao.trouverParId(salable.getIdProvider(), false);
				request.setAttribute("owner", person);
				for (int i = 0; i < listComments.size(); i++) {
					listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
				}
				request.setAttribute("totalCommentaire", total);
				request.setAttribute("listComments", listComments);
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);
			}
			if (action.equals("supprimer")) {
				Long idcom = Long.parseLong(request.getParameter("ciblecom"));
				commentDao.deleteComment(idcom);
				if (type.equals("Service")) {
					service = serviceDao.findServiceById(id);
					listComments = commentDao.selectCommentsByIdService(service.getId(), (long) 10, (long) 0);
					System.out.println(service.toString());
					request.setAttribute("salable", service);
					person = personDao.trouverParId(service.getIdProvider(), false);
					request.setAttribute("owner", person);
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				} else {
					product = productDao.findProductById(id);
					listComments = commentDao.selectCommentsByIdProduct(product.getId(), (long) 10, (long) 0);
					request.setAttribute("salable", product);
					System.out.println(product.toString());
					person = personDao.trouverParId(product.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdProduct(product.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				}
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);

			}
			if (action.equals("modification")) {
				Long idcom = Long.parseLong(request.getParameter("ciblecom"));
				request.setAttribute("recup", commentDao.selectCommentById(idcom).getContent());
				request.setAttribute("idcomt", idcom);
				request.setAttribute("boutonvalue", "Modifier l'avis");
				request.setAttribute("actionvalue", "modifier");
				if (type.equals("Service")) {
					service = serviceDao.findServiceById(id);
					listComments = commentDao.selectCommentsByIdService(service.getId(), (long) 10, (long) 0);
					System.out.println(service.toString());
					request.setAttribute("salable", service);
					person = personDao.trouverParId(service.getIdProvider(), false);
					request.setAttribute("owner", person);
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				} else {
					product = productDao.findProductById(id);
					listComments = commentDao.selectCommentsByIdProduct(product.getId(), (long) 10, (long) 0);
					request.setAttribute("salable", product);
					System.out.println(product.toString());
					person = personDao.trouverParId(product.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdProduct(product.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				}
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);
			}
			if (action.equals("modifier")) {
				Long idcom = Long.parseLong(request.getParameter("idcom"));
				String content = request.getParameter("avis");
				commentDao.modifiyComment(idcom, content);
				request.setAttribute("boutonvalue", "Enregistrez l'avis");
				request.setAttribute("actionvalue", "commenter");
				if (type.equals("Service")) {
					service = serviceDao.findServiceById(id);
					listComments = commentDao.selectCommentsByIdService(service.getId(), (long) 10, (long) 0);
					System.out.println(service.toString());
					request.setAttribute("salable", service);
					person = personDao.trouverParId(service.getIdProvider(), false);
					request.setAttribute("owner", person);
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				} else {
					product = productDao.findProductById(id);
					listComments = commentDao.selectCommentsByIdProduct(product.getId(), (long) 10, (long) 0);
					request.setAttribute("salable", product);
					System.out.println(product.toString());
					person = personDao.trouverParId(product.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdProduct(product.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				}
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);
			}
			if (action.equals("afficherSousVendables")) {
				begin = Long.parseLong(request.getParameter("begin"));
				end = Long.parseLong(request.getParameter("end"));
				Long sessionIdSalable = (long) session.getAttribute("sessionIdSalable");
				String sessionTypeSalable = (String) session.getAttribute("sessionTypeSalable");
				if (sessionTypeSalable.equals("Service")) {
					service = serviceDao.findServiceById(sessionIdSalable);
					listComments = commentDao.selectCommentsByIdService(service.getId(), end, begin);
					System.out.println(service.toString());
					request.setAttribute("salable", service);
					person = personDao.trouverParId(service.getIdProvider(), false);
					request.setAttribute("owner", person);
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				} else {
					product = productDao.findProductById(sessionIdSalable);
					listComments = commentDao.selectCommentsByIdProduct(product.getId(), end, begin);
					request.setAttribute("salable", product);
					System.out.println(product.toString());
					person = personDao.trouverParId(product.getIdProvider(), false);
					request.setAttribute("owner", person);
					total = commentDao.selectNbreCommentsByIdProduct(product.getId());
					for (int i = 0; i < listComments.size(); i++) {
						listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
					}
					request.setAttribute("totalCommentaire", total);
					request.setAttribute("listComments", listComments);
				}
				String message = "";
				String pagination = "";
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
				}
				pagination = Paginateur.pagine(total, listComments, request, "details");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("message", message);
			}

		} else {
			id = Long.parseLong(request.getParameter("cible"));
			type = request.getParameter("type");
			if (type.equals("Service")) {
				service = serviceDao.findServiceById(id);
				listComments = commentDao.selectCommentsByIdService(service.getId(), (long) 10, (long) 0);
				System.out.println(service.toString());
				request.setAttribute("salable", service);
				person = personDao.trouverParId(service.getIdProvider(), false);
				request.setAttribute("owner", person);
				total = commentDao.selectNbreCommentsByIdService(service.getId());
				for (int i = 0; i < listComments.size(); i++) {
					listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
				}
				request.setAttribute("totalCommentaire", total);
				request.setAttribute("listComments", listComments);
			} else {
				product = productDao.findProductById(id);
				listComments = commentDao.selectCommentsByIdProduct(product.getId(), (long) 10, (long) 0);
				request.setAttribute("salable", product);
				System.out.println(product.toString());
				person = personDao.trouverParId(product.getIdProvider(), false);
				request.setAttribute("owner", person);
				total = commentDao.selectNbreCommentsByIdProduct(product.getId());
				for (int i = 0; i < listComments.size(); i++) {
					listComments.get(i).setAuthor(personDao.trouverParId(listComments.get(i).getIdAuthor(), false));
				}
				request.setAttribute("totalCommentaire", total);
				request.setAttribute("listComments", listComments);
			}
			session.setAttribute("sessionIdSalable", id);
			session.setAttribute("sessionTypeSalable", type);
			String message = "";
			String pagination = "";
			if (total <= 0 || total == null) {
				message = "Aucun sous-éléments à afficher!!";
			} else {
				message = "Liste des éléments trouvés";
			}
			pagination = Paginateur.pagine(total, listComments, request, "details");
			System.out.println("Pagination effectuée!");
			request.setAttribute("pagination", pagination);
			System.out.println("Pagination settée!");
			request.setAttribute("total", total);
			request.setAttribute("message", message);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/salableDetails.jsp").forward(request, response);

	}
}
