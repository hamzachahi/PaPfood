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
import beans.Salable;
import dao.ServiceDao;
import dao.UsineDao;

@WebServlet("/ServletServicesList")
public class ServletServicesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<ElementCommand> monPanier = new ArrayList<>();
	private ServiceDao serviceDao;
	private ArrayList<ElementCommand> elements = new ArrayList<>();
	private ArrayList<ElementCommand> elementSort = null;

	private ArrayList<Salable> tousLesServices;
	String pagination = "";
	Long begin = null;
	Long end = null;
	Long total = null;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		

	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			if (action.equals("afficherSousVendables")) {
				begin = Long.parseLong(request.getParameter("begin"));
				end = Long.parseLong(request.getParameter("end"));

				String message = "";
				String pagination = "";
				total = serviceDao.countElements();
				if (total <= 0 || total == null) {
					message = "Aucun sous-éléments à afficher!!";
				} else {
					message = "Liste des éléments trouvés";
					tousLesServices.addAll(serviceDao.findAllService(begin, end));
					for (int i = 0; i < elements.size(); i++) {
						elements.remove(i);
					}
					for (int i = 0; i < tousLesServices.size(); i++) {
						ElementCommand elementCom = new ElementCommand();
						elementCom.setmProduct(tousLesServices.get(i));
						elementCom.setQuantity(0);
						elements.add(elementCom);
					}
					request.setAttribute("listProduits", tousLesServices);
					request.setAttribute("total", total);
				}
				System.out.println("Nombre d'utilisateurs dans la base : " + total);
				pagination = Paginateur.pagine(total, tousLesServices, request, "services");
				System.out.println("Pagination effectuée!");
				request.setAttribute("pagination", pagination);
				System.out.println("Pagination settée!");
				request.setAttribute("total", total);
				request.setAttribute("listProduits", tousLesServices);
				request.setAttribute("message", message);
			}
			if (action.equals("chargerPanier")) {
				Double totalpanier = 0.0;

				HttpSession session = request.getSession(false);
				if (session.getAttribute("monPanier") != null) {
					monPanier = ((ArrayList<ElementCommand>) session.getAttribute("monPanier"));
				}
				int i = Integer.parseInt(request.getParameter("idarticle"));
				ElementCommand article = elements.get(i);
				article.setQuantity(1);
				monPanier.add(article);
				elementSort=removeDoublons(monPanier);
				session.setAttribute("nbrelementspanier", monPanier.size());
				request.setAttribute("articlesPanier", elementSort);
				session.setAttribute("monPanier", elementSort);

				for (int i1 = 0; i1 < monPanier.size(); i1++) {
					totalpanier += monPanier.get(i1).getmProduct().getPrice();
				}
				request.setAttribute("listProduits", tousLesServices);
				request.setAttribute("total", totalpanier);
				session.setAttribute("tousLesServices", elements);
				pagination = Paginateur.pagine(total, tousLesServices, request, "services");
				request.setAttribute("pagination", pagination);
			}

		} else {
			if (tousLesServices == null) {
				tousLesServices = new ArrayList<>();
				HttpSession session = request.getSession();
				total = serviceDao.countElements();
				tousLesServices.addAll(serviceDao.findAllService((long) 10, (long) 0));
				for (int i = 0; i < elements.size(); i++) {
					elements.remove(i);
				}
				for (int i = 0; i < tousLesServices.size(); i++) {
					ElementCommand elementCom = new ElementCommand();
					elementCom.setmProduct(tousLesServices.get(i));
					elementCom.setQuantity(0);
					elements.add(elementCom);
				}
				request.setAttribute("listProduits", tousLesServices);
				request.setAttribute("total", total);
				session.setAttribute("tousLesServices", elements);
				pagination = Paginateur.pagine(total, tousLesServices, request, "services");
				request.setAttribute("pagination", pagination);
			} else {
				request.setAttribute("listProduits", tousLesServices);
				total = serviceDao.countElements();
				request.setAttribute("total", total);
				pagination = Paginateur.pagine(total, tousLesServices, request, "services");
				request.setAttribute("pagination", pagination);
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/listServices.jsp").forward(request, response);
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
