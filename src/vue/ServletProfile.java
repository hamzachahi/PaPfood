package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Person;
import dao.PersonDao;

@WebServlet("/ServletProfile")
public class ServletProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PersonDao utilisateurDao = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		Person utilisateur = new Person();

		// utilisateur = (Person) session.getAttribute("utilisateur");

		if (action.equals("completeProfile")) {

			String name2 = request.getParameter("sname");
			String prenom = request.getParameter("surname");
			String prenom2 = request.getParameter("surname2");
			String numtel = request.getParameter("numtelephone");
			String profession = request.getParameter("profession");
			String profil = request.getParameter("profil");

			// à changer en string dans les beans
			Integer numVoie = Integer.parseInt(request.getParameter("streetnb"));

			String nomVoie = request.getParameter("streetname");
			String ville = request.getParameter("city");
			String codePostal = request.getParameter("zipcode");

			// photo à récupérer

			utilisateur.setSecondName(name2, false);
			utilisateur.setSurname(prenom, false);
			utilisateur.setSecondSurname(prenom2, false);
			utilisateur.setPhoneNumber(numtel, false);
			utilisateur.setProfession(profession, false);
			utilisateur.setFunction(profil);
			utilisateur.setStreetNumber(numVoie, false);
			utilisateur.setStreetName(nomVoie, false);
			utilisateur.setCityName(ville, false);
			utilisateur.setPostalCode(codePostal, false);

			session.setAttribute("utilisateur", utilisateur);

			System.out.println(utilisateur.toString());

			// utilisateurDao.modifyPersonalInformation(utilisateur);

		}

	}

}
