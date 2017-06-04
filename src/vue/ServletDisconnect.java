package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnectionDao;
import dao.UsineDao;

public class ServletDisconnect extends HttpServlet {
	private ConnectionDao connectionDao;
	public static final String CONF_DAO_FACTORY = "usinedao";
	private static final long serialVersionUID = 161810466362514525L;
	public static final String URL_REDIRECTION = "http://localhost:2020/PaPfood/connexion";

	public void init() throws ServletException {
		this.connectionDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getConnectiontionDao();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			connectionDao.updateConnexion((long) session.getAttribute("idConnexion"));
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/connexion");
	}
}