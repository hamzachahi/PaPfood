package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.Evaluation;
import beans.Paginateur;
import beans.Person;
import dao.ConnectionDao;
import dao.EvaluationDao;
import dao.MessageDao;
import dao.PersonDao;
import dao.PostDao;
import dao.UsineDao;

@WebServlet("/ServletCompte")
public class ServletCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/WEB-INF/compte.jsp";
	private PersonDao personDao;
	private MessageDao messageDao;
	private EvaluationDao evaluationDao;
	private PostDao postDao;
	private ConnectionDao connexionDao;
	public static final String CONF_DAO_FACTORY = "usinedao";

	public void init() throws ServletException {
		this.personDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getUtilisateurDao();
		this.messageDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDao();
		this.postDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getPostDao();
		this.evaluationDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getEvaluationDao();
		this.connexionDao = ((UsineDao) getServletContext().getAttribute(CONF_DAO_FACTORY)).getConnectiontionDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@SuppressWarnings("unused")
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message;
		String pagination;
		Long begin = null;
		Long end = null;
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");
		Long cible = null;
		Double note = null;
		ArrayList<Evaluation> listEvaluations = null;
		Person owner = null;
		String content = null;
		Long totalEval = null;
		Long ownerId = null;
		if (session != null) {
			Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");

			if (action != null && session != null) {
				if (request.getParameter("ownerid") != null) {
					ownerId = Long.parseLong(request.getParameter("ownerid"));
				}
				if (action.equals("contacter")) {
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(ownerId, finalf, startf));
					session.setAttribute("finali", finali);
					session.setAttribute("starti", starti);
					session.setAttribute("finalf", finalf);
					session.setAttribute("startf", startf);
					content = (String) request.getParameter("contentmessage");
					owner = personDao.trouverParId(ownerId, false);
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
					messageDao.sendMessage(utilisateur.getId(), owner.getId(), content);
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("nbrenotes", totalEval);
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("owner", owner);
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}
				if (action.equals("poster")) {
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(ownerId, finalf, startf));
					session.setAttribute("finali", finali);
					session.setAttribute("starti", starti);
					session.setAttribute("finalf", finalf);
					session.setAttribute("startf", startf);
					String title = request.getParameter("sujet");
					content = (String) request.getParameter("contentpost");
					System.out.println("contenu du post : " + content);
					owner = personDao.trouverParId(ownerId, false);
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
					postDao.Post(owner.getId(), content, title);
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("nbrenotes", totalEval);
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("owner", owner);
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}
				if (action.equals("noter")) {
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(ownerId, finalf, startf));
					session.setAttribute("finali", finali);
					session.setAttribute("starti", starti);
					session.setAttribute("finalf", finalf);
					request.setAttribute("startf", startf);
					content = (String) request.getParameter("contentnote");
					note = Double.parseDouble(request.getParameter("note"));
					owner = personDao.trouverParId(ownerId, false);
					evaluationDao.Evaluate(owner.getId(), utilisateur.getId(), note, content);
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("nbrenotes", totalEval);
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("owner", owner);
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}
				if (action.equals("afficherSousVendables")) {
					ownerId = (long) session.getAttribute("ownerid");
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(ownerId, finalf, startf));
					session.setAttribute("finali", finali);
					session.setAttribute("starti", starti);
					session.setAttribute("finalf", finalf);
					session.setAttribute("startf", startf);
					owner = personDao.trouverParId(ownerId, false);
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					request.setAttribute("nbrenotes", totalEval);
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("owner", owner);
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}

			} else if (request.getParameter("naviguer") != null) {
				owner = personDao.trouverParId(Long.parseLong(request.getParameter("member")), false);
				request.setAttribute("moyennenotes", evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
				String naviguer = request.getParameter("naviguer");
				if (naviguer.equals("previouspost")) {
					Long idOwner = Long.parseLong(request.getParameter("member"));
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(idOwner, finali, starti));
					session.setAttribute("finali", 1);
					session.setAttribute("starti", starti - (Integer) 1);
					session.setAttribute("finalf", 1);
					session.setAttribute("startf", startf - (Integer) 1);
					request.setAttribute("owner", owner);
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("nbrenotes", totalEval);
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);

				}
				if (naviguer.equals("nextpost")) {
					Integer finali = null;
					Integer starti = null;
					Integer finalf = null;
					Integer startf = null;
					if (request.getParameter("finali") != null) {
						finali = (Integer) session.getAttribute("finali");
						starti = (Integer) session.getAttribute("starti");
					} else {
						finali = (Integer) 1;
						starti = (Integer) session.getAttribute("startf") - (Integer) 1;
					}
					if (request.getParameter("finalf") != null) {
						finalf = (Integer) session.getAttribute("finalf");
						startf = (Integer) session.getAttribute("startf");
					} else {
						finalf = (Integer) 1;
						startf = (Integer) session.getAttribute("starti") + (Integer) 1;
					}
					Long idOwner = Long.parseLong(request.getParameter("member"));
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(idOwner, finalf, startf));
					session.setAttribute("finali", 1 + (Integer) 1);
					session.setAttribute("starti", starti + (Integer) 1);
					session.setAttribute("finalf", 1);
					session.setAttribute("startf", startf + (Integer) 1);
					request.setAttribute("owner", owner);
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("nbrenotes", totalEval);
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}
			} else {
				if (request.getParameter("cible") != null) {
					cible = Long.parseLong(request.getParameter("cible"));
					session.setAttribute("ownerid", cible);
					session.setAttribute("finali", (Integer) 1);
					session.setAttribute("starti", (Integer) 0);
					session.setAttribute("finalf", (Integer) 1);
					session.setAttribute("startf", (Integer) 1);
					owner = personDao.trouverParId(cible, false);
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(owner.getId()));
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(owner.getId(), 1, 0));
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(owner.getId()));
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(personDao.trouverParId(owner.getId(), false).getLastConnexion())
									.getLoginTime());
					request.setAttribute("owner", owner);
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(owner.getId());
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("nbrenotes", totalEval);
					listEvaluations = evaluationDao.getMyEvaluation(owner.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);

				} else {
					session.setAttribute("finali", (Integer) 1);
					session.setAttribute("starti", (Integer) 0);
					session.setAttribute("finalf", (Integer) 1);
					session.setAttribute("startf", (Integer) 1);
					request.setAttribute("owner", utilisateur);
					session.setAttribute("ownerid", utilisateur.getId());
					request.setAttribute("postReal", postDao.selectPostsByIdAuthor(utilisateur.getId(), 1, 0));
					request.setAttribute("numberpost", postDao.selectNbrePostsByIdAuthor(utilisateur.getId()));
					request.setAttribute("lastconnexion",
							connexionDao
									.getConnexionById(
											personDao.trouverParId(utilisateur.getId(), false).getLastConnexion())
									.getLoginTime());
					request.setAttribute("moyennenotes",
							evaluationDao.selectMoyenneEvaluationByIdPerson(utilisateur.getId()));
					totalEval = evaluationDao.selectNbreEvaluationByIdPerson(utilisateur.getId());
					request.setAttribute("nbrenotes", totalEval);
					request.setAttribute("totalEval", totalEval);
					listEvaluations = evaluationDao.getMyEvaluation(utilisateur.getId(), (long) 10, (long) 0);
					for (int i = 0; i < listEvaluations.size(); i++) {
						listEvaluations.get(i)
								.setAuthor(personDao.trouverParId(listEvaluations.get(i).getIdJury(), false));
					}
					request.setAttribute("listEvaluations", listEvaluations);
					if (totalEval <= 0 || totalEval == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
					}
					pagination = Paginateur.pagine(totalEval, listEvaluations, request, "account");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("totalEval", totalEval);
					request.setAttribute("message", message);
				}

			}
			this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
		} else {
			response.sendRedirect("/connexion");
		}
	}
}
