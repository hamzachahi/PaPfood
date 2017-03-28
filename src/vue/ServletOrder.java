package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Command;
import forms.FormOrder;

@WebServlet("/ServletOrder")

public class ServletOrder extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2268529311014243749L;
	public static final String ATT_USER = "utilisateur";
	public static final String ATT_FORM = "form";
	public static final String VUE = "/WEB-INF/order.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Affichage de la page de commande */
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FormOrder formulaire = new FormOrder();

		@SuppressWarnings("unused")
		Command commande = formulaire.order(request);

		@SuppressWarnings("unused")
		HttpSession maSession = request.getSession();

		// A TERMINER
	}

}
