package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Person;
import dao.DaoPersonImpl;
import dao.PersonDao;
import dao.UsineDao;

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
		Person utilisateur = null;

		utilisateur = (Person) session.getAttribute("sessionUtilisateur");

		if (action.equals("completeProfile")) {
		utilisateurDao=	new DaoPersonImpl(new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
			String name = "";
			String name2 = "";
			String prenom = "";
			String prenom2 ="";
			String email = "";
			String numtel = "";
			String profession = "";
			String profil ="";
			// à changer en string dans les beans
			Integer numVoie = new Integer("0");
			String nomVoie = "";
			String ville = "";
			String country = "";
			String codePostal = "";
			 name = request.getParameter("sname");
			 name2 = request.getParameter("sname2");
			 prenom = request.getParameter("surname");
			 prenom2 = request.getParameter("surname2");
			 email = request.getParameter("email");
			 numtel = request.getParameter("numtelephone");
			 profession = request.getParameter("profession");
			 profil = request.getParameter("profil");
			// à changer en string dans les beans
			 numVoie = Integer.parseInt(request.getParameter("streetnb"));
			 nomVoie = request.getParameter("streetname");
			 ville = request.getParameter("city");
			 country = request.getParameter("country");
			 codePostal = request.getParameter("zipcode");

			// photo à récupérer
			utilisateur.setName(name, false);
			utilisateur.setSecondName(name2, false);
			utilisateur.setSurname(prenom, false);
			utilisateur.setSecondSurname(prenom2, false);
			utilisateur.setEmail(email, false);
			utilisateur.setPhoneNumber(numtel, false);
			utilisateur.setProfession(profession, false);
			utilisateur.setFunction(profil);
			utilisateur.setStreetNumber(numVoie, false);
			utilisateur.setStreetName(nomVoie, false);
			utilisateur.setCityName(ville, false);
			utilisateur.setCountryName(country, false);
			utilisateur.setPostalCode(codePostal, false);

			session.setAttribute("utilisateur", utilisateur);

			System.out.println(utilisateur.toString());

			utilisateurDao.modifyPersonalInformation(utilisateur);

		}

	}

}
