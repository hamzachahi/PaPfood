package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.ElementCommand;
import beans.Paginateur;
import beans.Person;
import beans.Salable;
import dao.ProductDao;
import dao.UsineDao;

@WebServlet("/ServletProductsList")
public class ServletProductsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao productDao;
	private ArrayList<ElementCommand> elements = new ArrayList<>();
	private ArrayList<ElementCommand> monPanier = new ArrayList<>();
	private ArrayList<ElementCommand> monPanier2 = new ArrayList<>();

	private ArrayList<Salable> tousLesProduits;
	String pagination = "";
	Long begin = null;
	Long end = null;
	Long total = null;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			if (request.getParameter("action") != null) {
				String action = request.getParameter("action");
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));

					String message = "";
					String pagination = "";
					total = productDao.countElements(person.getId());
					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						tousLesProduits.addAll(productDao.findAllProduct(person.getId(), begin, end));
						for (int i = 0; i < elements.size(); i++) {
							elements.remove(i);
						}
						for (int i = 0; i < tousLesProduits.size(); i++) {
							ElementCommand elementCom = new ElementCommand();
							elementCom.setmProduct(tousLesProduits.get(i));
							elementCom.setQuantity(0);
							elements.add(elementCom);
						}
						request.setAttribute("listProduits", tousLesProduits);
						request.setAttribute("total", total);
					}
					System.out.println("Nombre d'utilisateurs dans la base : " + total);
					pagination = Paginateur.pagine(total, tousLesProduits, request, "produits");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("listProduits", tousLesProduits);
					request.setAttribute("message", message);
				}
				if (action.equals("chargerPanier")) {
					Double totalpanier = 0.0;
					if (session.getAttribute("monPanier") != null) {
						monPanier = ((ArrayList<ElementCommand>) session.getAttribute("monPanier"));
					}
					int i = Integer.parseInt(request.getParameter("idarticle"));
					ElementCommand article = elements.get(i);
					article.setQuantity(1);
					monPanier.add(article);
					monPanier2 = removeDoublons(monPanier);
					session.setAttribute("nbrelementspanier", monPanier.size());
					request.setAttribute("articlesPanier", monPanier2);
					session.setAttribute("monPanier", monPanier2);

					for (int i1 = 0; i1 < monPanier.size(); i1++) {
						totalpanier += monPanier.get(i1).getmProduct().getPrice();
					}
					request.setAttribute("listProduits", tousLesProduits);
					request.setAttribute("total", totalpanier);
					session.setAttribute("tousLesProduits", elements);
					pagination = Paginateur.pagine(total, tousLesProduits, request, "services");
					request.setAttribute("pagination", pagination);
				}

			} else {
				if (tousLesProduits == null) {
					tousLesProduits = new ArrayList<>();
					if (session.getAttribute("sessionUtilisateur") != null) {
						total = productDao.countElements(person.getId());
						tousLesProduits.addAll(productDao.findAllProduct(person.getId(), (long) 10, (long) 0));

					} else {
						total = productDao.countElements(Long.valueOf(0));
						tousLesProduits.addAll(productDao.findAllProduct(Long.valueOf(0), (long) 10, (long) 0));
					}
					for (int i = 0; i < elements.size(); i++) {
						elements.remove(i);
					}
					for (int i = 0; i < tousLesProduits.size(); i++) {
						ElementCommand elementCom = new ElementCommand();
						elementCom.setmProduct(tousLesProduits.get(i));
						elementCom.setQuantity(0);
						elements.add(elementCom);
					}
					request.setAttribute("listProduits", tousLesProduits);
					request.setAttribute("total", total);
					session.setAttribute("tousLesProduits", elements);
					pagination = Paginateur.pagine(total, tousLesProduits, request, "produits");
					request.setAttribute("pagination", pagination);
				} else {
					request.setAttribute("listProduits", tousLesProduits);
					if (session.getAttribute("sessionUtilisateur") != null) {
						total = productDao.countElements(person.getId());
					} else {
						total = productDao.countElements(Long.valueOf(0));
					}
					request.setAttribute("total", total);
					pagination = Paginateur.pagine(total, tousLesProduits, request, "produits");
					request.setAttribute("pagination", pagination);
				}
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/listProduits.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public ArrayList<ElementCommand> removeDoublons(ArrayList<ElementCommand> memb) {
		ArrayList<ElementCommand> membA = new ArrayList<>();
		membA.addAll(memb);
		for (int i = 0; i < membA.size(); i++) {
			ElementCommand o = membA.get(i);
			for (int i1 = i + 1; i1 < membA.size(); i1++) {
				ElementCommand r = membA.get(i1);
				if (o.getmProduct().getId() == r.getmProduct().getId()
						&& o.getmProduct().getType().equals(r.getmProduct().getType())) {
					membA.remove(r);
					System.out.println("ServletOrder.removeDoublons() effectué!");
					membA.get(i).setQuantity(membA.get(i).getQuantity() + 1);
				}
			}
		}

		return membA;
	}
}
