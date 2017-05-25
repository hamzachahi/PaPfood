package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Commande;
import dao.DaoCommandeImpl;
import dao.UsineDao;

@WebServlet("/ServletAllOrders")
public class ServletAllOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoCommandeImpl commandeDao = new DaoCommandeImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Commande> allOrders = new ArrayList<>();

		allOrders = commandeDao.findAllCommande(Long.valueOf(20), Long.valueOf(0));

		request.setAttribute("allOrders", allOrders);

		this.getServletContext().getRequestDispatcher("/WEB-INF/listOrders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
