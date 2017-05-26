package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Invoice;
import dao.DaoInvoiceImpl;
import dao.UsineDao;

@WebServlet("/ServletAllInvoices")
public class ServletAllInvoices extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoInvoiceImpl invoiceDao = new DaoInvoiceImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Invoice> allInvoices = new ArrayList<>();

		allInvoices = invoiceDao.findAll(Long.valueOf(20), Long.valueOf(0));

		request.setAttribute("allInvoices", allInvoices);

		this.getServletContext().getRequestDispatcher("/WEB-INF/listInvoices.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
