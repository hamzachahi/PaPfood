package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Person;
import beans.Product;
import beans.Service;
import dao.MessageDao;
import dao.ProductDao;
import dao.SearchDao;
import dao.ServiceDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "usinedao";
	MessageDao messageDao;
	ProductDao productDao;
	ServiceDao serviceDao;
	SearchDao searchDao;
	Thread t = new Thread();
	ArrayList<Product> listProducts;
	ArrayList<Service> listServices;
	String titleSer;
	String titlePro;

	public void init() throws ServletException {
		this.messageDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
		this.productDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getProductDao();
		this.serviceDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getServiceDao();
		this.searchDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getSearchDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (t == null || !t.isAlive()) {
			t = new Thread(new RefreshMesages(messageDao, session));
			t.start();
		}
		if (session != null && session.getAttribute("sessionUtilisateur") != null) {
			Person person = (Person) session.getAttribute("sessionUtilisateur");
			if (session.getAttribute("sessionAdr") != null) {
				String adr = (String) session.getAttribute("sessionAdr");
				listProducts = searchDao.findProductByKeyWord(adr, person.getId(), (long) 4, (long) 0);
				listServices = searchDao.findServiceByKeyWord(adr, person.getId(), (long) 4, (long) 0);
				titlePro = "Près de vous!";
				titleSer = "Près de vous!";
				request.setAttribute("titleSer", titleSer);
				request.setAttribute("titlePro", titlePro);

			} else if (!person.getCityName().equals("") && person.getCityName() != null) {
				String adr = person.getCityName();
				listProducts = searchDao.findProductByKeyWord(adr, person.getId(), (long) 4, (long) 0);
				listServices = searchDao.findServiceByKeyWord(adr, person.getId(), (long) 4, (long) 0);
				titlePro = "Près de votre domicile!";
				titleSer = "Près de votre domicile!";
				request.setAttribute("titleSer", titleSer);
				request.setAttribute("titlePro", titlePro);
			} else {
				listProducts = productDao.findAllProduct((long) 4, person.getId(), (long) 0);
				listServices = serviceDao.findAllService((long) 4, person.getId(), (long) 0);
				titlePro = "Tendances";
				titleSer = "Tendances";
				request.setAttribute("titleSer", titleSer);
				request.setAttribute("titlePro", titlePro);
			}
			if (listProducts.isEmpty()) {
				listProducts = productDao.findAllProduct(person.getId(), (long) 4, (long) 0);
				titlePro = "Tendances";
				request.setAttribute("titlePro", titlePro);
			}
			if (listServices.isEmpty()) {
				listServices = serviceDao.findAllService(person.getId(), (long) 4, (long) 0);
				titleSer = "Tendances";
				request.setAttribute("titleSer", titleSer);
			}
			request.setAttribute("listProducts", listProducts);
			request.setAttribute("listServices", listServices);

		} else {
			listProducts = productDao.findAllProduct((long) 0, (long) 4, (long) 0);
			listServices = serviceDao.findAllService((long) 0, (long) 4, (long) 0);
			request.setAttribute("listProducts", listProducts);
			request.setAttribute("listServices", listServices);
			titlePro = "Tendances";
			titleSer = "Tendances";
			request.setAttribute("titleSer", titleSer);
			request.setAttribute("titlePro", titlePro);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}
}
