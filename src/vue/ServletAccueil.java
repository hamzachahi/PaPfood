package vue;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MessageDao;
import dao.UsineDao;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MessageDao messageDao ;
		Thread t = new Thread();
		public static final String CONF_DAO_FACTORY = "usinedao";

		public void init() throws ServletException {
			this.messageDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
			

		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (t == null||!t.isAlive()) {
			HttpSession session = request.getSession(false);
			t = new Thread(new RefreshMesages(messageDao, session));
			t.start();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
