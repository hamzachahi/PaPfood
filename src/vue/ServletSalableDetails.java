package vue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Person;
import beans.Product;
import beans.Service;
import dao.DaoPersonImpl;
import dao.DaoProductImpl;
import dao.DaoServiceImpl;
import dao.UsineDao;

@WebServlet("/ServletSalableDetails")
public class ServletSalableDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProductImpl productDao = new DaoProductImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private DaoServiceImpl serviceDao = new DaoServiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	private DaoPersonImpl personDao = new DaoPersonImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Service service = null;
		Product product = null;
		Person person = null;
		Long id = Long.parseLong(request.getParameter("cible"));
		String type = request.getParameter("type");

		if (type.equals("Service")) {
			service = serviceDao.findServiceById(id);
			System.out.println(service.toString());
			request.setAttribute("salable", service);
			person = personDao.trouverParId(service.getIdProvider(), false);
			request.setAttribute("owner", person);

		} else {
			product = productDao.findProductById(id);
			request.setAttribute("salable", product);
			System.out.println(product.toString());
			person = personDao.trouverParId(product.getIdProvider(), false);
			request.setAttribute("owner", person);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/salableDetails.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
