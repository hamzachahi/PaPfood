package vue;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Person;
import dao.MessageDao;
import dao.PersonDao;
import dao.UsineDao;

@WebServlet("/ServletProfile")
@MultipartConfig
public class ServletProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PersonDao utilisateurDao;
	public static final String CONF_DAO_FACTORY = "usinedao";
	public MessageDao messageDao;

	public void init() throws ServletException {
		this.utilisateurDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		this.messageDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Person utilisateur = null;
		if (session != null) {
			utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			String function = utilisateur.getFunction();
			String realChemin = utilisateur.getAccountPicture();
			String action = request.getParameter("action");
			if (action != null) {
				if (action.equals("completeProfile")) {

					// Formulaire pour le contrôle des erreurs dans la
					// modification
					// d'informations
					String name = "";
					String name2 = "";
					String prenom = "";
					String prenom2 = "";
					String email = "";
					String numtel = "";
					String numfix = "";
					String profession = "";
					String profil = "";
					String account_picture = "";
					String numVoie = "";
					String nomVoie = "";
					String ville = "";
					String country = "";
					String codePostal = "";
					String fbId = "";
					String twitterId = "";
					String instaId = "";
					String linkedId = "";
					String departement = "";
					String latLng = "";
					name = request.getParameter("sname");
					name2 = request.getParameter("sname2");
					prenom = request.getParameter("surname");
					prenom2 = request.getParameter("surname2");
					email = request.getParameter("email");
					numtel = request.getParameter("numtelephone");
					numfix = request.getParameter("numfix");
					profession = request.getParameter("profession");
					profil = request.getParameter("profil");
					numVoie = request.getParameter("streetnb");
					nomVoie = request.getParameter("streetname");
					account_picture = request.getParameter("accountPicture");
					ville = request.getParameter("city");
					country = request.getParameter("country");
					codePostal = request.getParameter("zipcode");
					fbId = request.getParameter("fbid");
					twitterId = request.getParameter("twitterid");
					instaId = request.getParameter("instaid");
					linkedId = request.getParameter("linkedid");
					departement = request.getParameter("departement");
					latLng = request.getParameter("latlng");
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
					utilisateur.setDepartement(departement);
					utilisateur.setLatLng(latLng);
					utilisateur.setAccountPicture(account_picture, false);
					utilisateur.setPostalCode(codePostal, false);
					utilisateur.setFacebookId(fbId, false);
					utilisateur.setTwitterId(twitterId, false);
					utilisateur.setInstagramId(instaId, false);
					utilisateur.setLinkedinId(linkedId, false);
					utilisateur.setAccountPicture(realChemin, false);

					session.setAttribute("utilisateur", utilisateur);

					System.out.println(utilisateur.toString());

					utilisateurDao.modifyPersonalInformation(utilisateur);
					if (function == null && profil != null && profil.equals("pro")) {
						messageDao.sendMessage(utilisateur.getId(), (long) 4,
								"Je déclare, moi " + utilisateur.toString() + " être un professionnel!");
					}
					response.sendRedirect(request.getContextPath() + "/account");

				}

			}
		} else {
			response.sendRedirect(request.getContextPath() + "/connexion");
		}
	}
}
