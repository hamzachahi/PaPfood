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
	public static final String URL_REDIRECTION = "http://localhost:2020/PaPfood/accueil";


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
			this.utilisateurDao = new DaoPersonImpl(new UsineDao("jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root", "0000"));

			//utilisateurDao = new DaoPersonImpl(
		//			new UsineDao("jdbc:oracle:thin:@localhost:1521:orcl", "papfood", "yummyshop"));
			String name = "";
			String name2 = "";
			String prenom = "";
			String prenom2 = "";
			String email = "";
			String numtel = "";
			String numfix="";
			String profession = "";
			String profil = "";
			// à changer en string dans les beans
			Integer numVoie = new Integer("0");
			String nomVoie = "";
			String ville = "";
			String country = "";
			String codePostal = "";
			String fbId = "";
			String twitterId = "";
			String instaId = "";
			String linkedId = "";
			name = request.getParameter("sname");
			name2 = request.getParameter("sname2");
			prenom = request.getParameter("surname");
			prenom2 = request.getParameter("surname2");
			email = request.getParameter("email");
			numtel = request.getParameter("numtelephone");
			numfix=request.getParameter("numfix");			
			profession = request.getParameter("profession");
			profil = request.getParameter("profil");
			// à changer en string dans les beans
			if (request.getParameter("streetnb") != null || request.getParameter("streetnb").equals("")) {
				numVoie = Integer.parseInt(request.getParameter("streetnb"));
			}else{
				numVoie=null;
			}
			nomVoie = request.getParameter("streetname");
			ville = request.getParameter("city");
			country = request.getParameter("country");
			codePostal = request.getParameter("zipcode");
			fbId = request.getParameter("fbid");
			twitterId = request.getParameter("twitterid");
			instaId = request.getParameter("instaid");
			linkedId = request.getParameter("linkedid");

			// photo à récupérer
			utilisateur.setName(name, false);
			utilisateur.setSecondName(name2, false);
			utilisateur.setSurname(prenom, false);
			utilisateur.setSecondSurname(prenom2, false);
			utilisateur.setEmail(email, false);
			utilisateur.setPhoneNumber(numtel, false);
			utilisateur.setTelNumber(numfix, false);
			utilisateur.setProfession(profession, false);
			utilisateur.setFunction(profil);
			utilisateur.setStreetNumber(numVoie, false);
			utilisateur.setStreetName(nomVoie, false);
			utilisateur.setCityName(ville, false);
			utilisateur.setCountryName(country, false);
			utilisateur.setPostalCode(codePostal, false);
			utilisateur.setFacebookId(fbId, false);
			utilisateur.setTwitterId(twitterId, false);
			utilisateur.setInstagramId(instaId, false);
			utilisateur.setLinkedinId(linkedId, false);

			session.setAttribute("utilisateur", utilisateur);

			System.out.println(utilisateur.toString());

			utilisateurDao.modifyPersonalInformation(utilisateur);
	        response.sendRedirect( URL_REDIRECTION );


		}

	}

}
