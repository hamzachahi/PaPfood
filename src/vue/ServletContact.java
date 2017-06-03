package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.UseMail;
import forms.FormContact;
import forms.ValidExceptionForm;

@SuppressWarnings("unused")
@WebServlet("/ServletContact")

public class ServletContact extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ATT_EMAIL = "email";
	public static final String ATT_FORM = "form";
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String VUE = "/WEB-INF/contact.jsp";
	private String erreur = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (testValue(request)) {
				UseMail.sendMessage(request.getParameter("email"), request.getParameter("subject"),
						request.getParameter("message") + "\n Numéro de téléphone : "
								+ request.getParameter("phonenumber"),
						"noubijunior@gmail.com", "ferchichi.youssef94@gmail.com ");
				request.setAttribute("erreur",
						"Votre messge a bien été envoyé. Nous reviendrons vers vous au plus tot");
				this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
			}
		} catch (ValidExceptionForm e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			erreur = e.getMessage();
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

		}

	}

	private boolean testValue(HttpServletRequest request) throws ValidExceptionForm {
		boolean test = true;
		String email = request.getParameter("email");
		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				test = false;
				throw new ValidExceptionForm("Merci de saisir une adresse mail valide.");
			} else if (email.equals("")) {
				test = false;
				throw new ValidExceptionForm("Merci de saisir une adresse mail.");
			}
		}
		if (request.getParameter("subject") == null) {
			test = false;
			throw new ValidExceptionForm("Merci de saisir un sujet.");
		}
		if (request.getParameter("message") == null) {
			test = false;
			throw new ValidExceptionForm("Merci de saisir un message.");
		}
		return test;
	}
}
