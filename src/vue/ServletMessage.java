package vue;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Message;
import beans.Paginateur;
import beans.Person;
import dao.DaoMessageImpl;
import dao.DaoPersonImpl;
import dao.UsineDao;

@WebServlet("/ServletMessage")
public class ServletMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DaoMessageImpl messageDao = new DaoMessageImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));
	DaoPersonImpl personDao = new DaoPersonImpl(new UsineDao(
			"jdbc:mysql://localhost:3306/papfood?verifyServerCertificate=false&useSSL=true&autoReconnect=true", "root",
			"0000"));

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Message> listUnreadMessages = null;
		ArrayList<Message> listReadMessages = null;
		ArrayList<Message> listSendMessages = null;
		Long nbreSentMessage = null;
		Long nbreUnreadMessage = null;
		Long nbreReadMessage = null;
		Long total = null;
		Long begin = null;
		Long end = null;
		String pagination = null;
		String action = request.getParameter("action");
		Long cible = null;
		if (request.getParameter("cible") != null) {
			cible = Long.parseLong(request.getParameter("cible"));
		}
		String readOrNot = request.getParameter("readornot");
		String statut = request.getParameter("statut");
		if (request.getSession(false) != null) {
			HttpSession session = request.getSession(false);
			Person utilisateur = (Person) session.getAttribute("sessionUtilisateur");
			if (action != null) {
				if (action.equals("afficherSousVendables")) {
					begin = Long.parseLong(request.getParameter("begin"));
					end = Long.parseLong(request.getParameter("end"));
					nbreUnreadMessage = messageDao.countNbreMessageUnReadById(utilisateur.getId());
					session.setAttribute("nbremessagenonlu", nbreUnreadMessage);
					nbreReadMessage = messageDao.countNbreMessageReadById(utilisateur.getId());
					nbreSentMessage = messageDao.countNbreMessageSendById(utilisateur.getId());
					total = nbreUnreadMessage;
					if (nbreReadMessage > nbreUnreadMessage) {
						total = nbreReadMessage;
					}
					if (nbreSentMessage > nbreReadMessage) {
						total = nbreSentMessage;
					}
					String message;
					if (total <= 0 || total == null) {
						message = "Aucun sous-éléments à afficher!!";
					} else {
						message = "Liste des éléments trouvés";
						listReadMessages = messageDao.receiveMyMessage(utilisateur.getId(), end, begin);
						listUnreadMessages = messageDao.receiveMyUnreadMessage(utilisateur.getId(), end, begin);
						listSendMessages = messageDao.getMySendMessage(utilisateur.getId(), end, begin);
						if (listReadMessages.size() > 0) {
							for (int i = 0; i < listReadMessages.size(); i++) {
								listReadMessages.get(i).setRealSender(
										personDao.trouverParId(listReadMessages.get(i).getSender(), false));
								if (listReadMessages.get(i).getReceiveDate() == null
										&& listReadMessages.get(i).getReceiver() == utilisateur.getId()) {
									messageDao.receiveMessage(listReadMessages.get(i));
								}
							}
						}
						if (listUnreadMessages.size() > 0) {
							for (int i = 0; i < listUnreadMessages.size(); i++) {
								Long id = listUnreadMessages.get(i).getSender();
								System.out.println("Id :" + id);
								System.out.println(listUnreadMessages.get(i).toString());

								listUnreadMessages.get(i).setRealSender(
										personDao.trouverParId(listUnreadMessages.get(i).getSender(), false));
								if (listUnreadMessages.get(i).getReceiveDate() == null
										&& listUnreadMessages.get(i).getReceiver() == utilisateur.getId()) {
									messageDao.receiveMessage(listUnreadMessages.get(i));
								}

							}
						}
						if (listSendMessages.size() > 0) {
							for (int i = 0; i < listSendMessages.size(); i++) {
								listSendMessages.get(i).setRealSender(
										personDao.trouverParId(listSendMessages.get(i).getReceiver(), false));
								if (listSendMessages.get(i).getReceiveDate() == null
										&& listSendMessages.get(i).getReceiver() == utilisateur.getId()) {
									messageDao.receiveMessage(listSendMessages.get(i));
								}
							}

						}
						nbreUnreadMessage = messageDao.countNbreMessageUnReadById(utilisateur.getId());
						session.setAttribute("nbremessagenonlu", nbreUnreadMessage);
						nbreReadMessage = messageDao.countNbreMessageReadById(utilisateur.getId());
						nbreSentMessage = messageDao.countNbreMessageSendById(utilisateur.getId());
						total = nbreUnreadMessage;
						if (nbreReadMessage > nbreUnreadMessage) {
							total = nbreReadMessage;
						}
						if (nbreSentMessage > nbreReadMessage) {
							total = nbreSentMessage;
						}
						pagination = Paginateur.pagine(total, listSendMessages, request, "message");
						request.setAttribute("pagination", pagination);
						request.setAttribute("listReadMessages", listReadMessages);
						request.setAttribute("listUnreadMessages", listUnreadMessages);
						request.setAttribute("listSendMessages", listSendMessages);
						session.setAttribute("beginmes", begin);
						session.setAttribute("endmess", end);

					}

					pagination = Paginateur.pagine(total, listReadMessages, request, "message");
					System.out.println("Pagination effectuée!");
					request.setAttribute("pagination", pagination);
					System.out.println("Pagination settée!");
					request.setAttribute("total", total);
					request.setAttribute("message", message);
				}
				if (action.equals("contacter")) {
					Long begins = (long) session.getAttribute("beginmess");
					Long ends = (long) session.getAttribute("endmess");
					String content = request.getParameter("contentmessage");
					Long idreceiver = Long.parseLong(request.getParameter("idreceiver"));
					messageDao.sendMessage(utilisateur.getId(), idreceiver, content);

					listReadMessages = messageDao.receiveMyMessage(utilisateur.getId(), ends, begins);
					listUnreadMessages = messageDao.receiveMyUnreadMessage(utilisateur.getId(), ends, begins);
					listSendMessages = messageDao.getMySendMessage(utilisateur.getId(), ends, begins);
					if (listReadMessages.size() > 0) {
						for (int i = 0; i < listReadMessages.size(); i++) {
							listReadMessages.get(i)
									.setRealSender(personDao.trouverParId(listReadMessages.get(i).getSender(), false));
							if (listReadMessages.get(i).getReceiveDate() == null
									&& listReadMessages.get(i).getReceiver() == utilisateur.getId()) {
								messageDao.receiveMessage(listReadMessages.get(i));
							}
						}
					}
					if (listUnreadMessages.size() > 0) {
						for (int i = 0; i < listUnreadMessages.size(); i++) {
							Long id = listUnreadMessages.get(i).getSender();
							System.out.println("Id :" + id);
							System.out.println(listUnreadMessages.get(i).toString());

							listUnreadMessages.get(i).setRealSender(
									personDao.trouverParId(listUnreadMessages.get(i).getSender(), false));
							if (listUnreadMessages.get(i).getReceiveDate() == null
									&& listUnreadMessages.get(i).getReceiver() == utilisateur.getId()) {
								messageDao.receiveMessage(listUnreadMessages.get(i));
							}
						}
					}
					if (listSendMessages.size() > 0) {
						for (int i = 0; i < listSendMessages.size(); i++) {
							listSendMessages.get(i)
									.setRealSender(personDao.trouverParId(listSendMessages.get(i).getReceiver(), false));
							if (listSendMessages.get(i).getReceiveDate() == null
									&& listSendMessages.get(i).getReceiver() == utilisateur.getId()) {
								messageDao.receiveMessage(listSendMessages.get(i));
							}
						}
					}
					nbreUnreadMessage = messageDao.countNbreMessageUnReadById(utilisateur.getId());
					session.setAttribute("nbremessagenonlu", nbreUnreadMessage);
					nbreReadMessage = messageDao.countNbreMessageReadById(utilisateur.getId());
					nbreSentMessage = messageDao.countNbreMessageSendById(utilisateur.getId());
					total = nbreUnreadMessage;
					if (nbreReadMessage > nbreUnreadMessage) {
						total = nbreReadMessage;
					}
					if (nbreSentMessage > nbreReadMessage) {
						total = nbreSentMessage;
					}
					pagination = Paginateur.pagine(total, listSendMessages, request, "message");
					request.setAttribute("pagination", pagination);
					request.setAttribute("listReadMessages", listReadMessages);
					request.setAttribute("listUnreadMessages", listUnreadMessages);
					request.setAttribute("listSendMessages", listSendMessages);
				}
			} else if (cible != null) {
				Long begins = (long) session.getAttribute("beginmess");
				Long ends = (long) session.getAttribute("endmess");
				Message mess = messageDao.selectMessageById(cible);
				mess.setRealSender(personDao.trouverParId(mess.getSender(), false));
				if (readOrNot.equals("sentread")) {
					request.setAttribute("sentmessage", mess);
				} else {
					request.setAttribute("messagem", mess);
					if (statut.equals("unread")) {
						messageDao.readMessage(mess);
						System.out.println("J'ai lu le message");
					}
				}
				listReadMessages = messageDao.receiveMyMessage(utilisateur.getId(), ends, begins);
				listUnreadMessages = messageDao.receiveMyUnreadMessage(utilisateur.getId(), ends, begins);
				listSendMessages = messageDao.getMySendMessage(utilisateur.getId(), ends, begins);
				if (listReadMessages.size() > 0) {
					for (int i = 0; i < listReadMessages.size(); i++) {
						listReadMessages.get(i)
								.setRealSender(personDao.trouverParId(listReadMessages.get(i).getSender(), false));
						if (listReadMessages.get(i).getReceiveDate() == null
								&& listReadMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listReadMessages.get(i));
						}
					}
				}
				if (listUnreadMessages.size() > 0) {
					for (int i = 0; i < listUnreadMessages.size(); i++) {
						Long id = listUnreadMessages.get(i).getSender();
						System.out.println("Id :" + id);
						System.out.println(listUnreadMessages.get(i).toString());

						listUnreadMessages.get(i)
								.setRealSender(personDao.trouverParId(listUnreadMessages.get(i).getSender(), false));
						if (listUnreadMessages.get(i).getReceiveDate() == null
								&& listUnreadMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listUnreadMessages.get(i));
						}

					}
				}
				if (listSendMessages.size() > 0) {
					for (int i = 0; i < listSendMessages.size(); i++) {
						listSendMessages.get(i)
								.setRealSender(personDao.trouverParId(listSendMessages.get(i).getReceiver(), false));
						if (listSendMessages.get(i).getReceiveDate() == null
								&& listSendMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listSendMessages.get(i));
						}
					}

				}
				nbreUnreadMessage = messageDao.countNbreMessageUnReadById(utilisateur.getId());
				session.setAttribute("nbremessagenonlu", nbreUnreadMessage);
				nbreReadMessage = messageDao.countNbreMessageReadById(utilisateur.getId());
				nbreSentMessage = messageDao.countNbreMessageSendById(utilisateur.getId());
				total = nbreUnreadMessage;
				if (nbreReadMessage > nbreUnreadMessage) {
					total = nbreReadMessage;
				}
				if (nbreSentMessage > nbreReadMessage) {
					total = nbreSentMessage;
				}
				pagination = Paginateur.pagine(total, listSendMessages, request, "message");
				request.setAttribute("pagination", pagination);
				request.setAttribute("listReadMessages", listReadMessages);
				request.setAttribute("listUnreadMessages", listUnreadMessages);
				request.setAttribute("listSendMessages", listSendMessages);

			} else {
				listReadMessages = messageDao.receiveMyMessage(utilisateur.getId(), (long) 10, (long) 0);
				listUnreadMessages = messageDao.receiveMyUnreadMessage(utilisateur.getId(), (long) 10, (long) 0);
				listSendMessages = messageDao.getMySendMessage(utilisateur.getId(), (long) 10, (long) 0);
				if (listReadMessages.size() > 0) {
					for (int i = 0; i < listReadMessages.size(); i++) {
						listReadMessages.get(i)
								.setRealSender(personDao.trouverParId(listReadMessages.get(i).getSender(), false));
						if (listReadMessages.get(i).getReceiveDate() == null
								&& listReadMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listReadMessages.get(i));
						}
					}
				}
				if (listUnreadMessages.size() > 0) {
					for (int i = 0; i < listUnreadMessages.size(); i++) {
						Long id = listUnreadMessages.get(i).getSender();
						System.out.println("Id :" + id);
						System.out.println(listUnreadMessages.get(i).toString());

						listUnreadMessages.get(i)
								.setRealSender(personDao.trouverParId(listUnreadMessages.get(i).getSender(), false));
						if (listUnreadMessages.get(i).getReceiveDate() == null
								&& listUnreadMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listUnreadMessages.get(i));
						}
					}
				}
				if (listSendMessages.size() > 0) {
					for (int i = 0; i < listSendMessages.size(); i++) {
						listSendMessages.get(i)
								.setRealSender(personDao.trouverParId(listSendMessages.get(i).getReceiver(), false));
						if (listSendMessages.get(i).getReceiveDate() == null
								&& listSendMessages.get(i).getReceiver() == utilisateur.getId()) {
							messageDao.receiveMessage(listSendMessages.get(i));
						}
					}
				}
				request.setAttribute("listReadMessages", listReadMessages);
				request.setAttribute("listUnreadMessages", listUnreadMessages);
				request.setAttribute("listSendMessages", listSendMessages);
				session.setAttribute("beginmess", (long) 0);
				session.setAttribute("endmess", (long) 10);
				nbreUnreadMessage = messageDao.countNbreMessageUnReadById(utilisateur.getId());
				session.setAttribute("nbremessagenonlu", nbreUnreadMessage);
				nbreReadMessage = messageDao.countNbreMessageReadById(utilisateur.getId());
				nbreSentMessage = messageDao.countNbreMessageSendById(utilisateur.getId());
				total = nbreUnreadMessage;
				if (nbreReadMessage > nbreUnreadMessage) {
					total = nbreReadMessage;
				}
				if (nbreSentMessage > nbreReadMessage) {
					total = nbreSentMessage;
				}
				pagination = Paginateur.pagine(total, listSendMessages, request, "message");
				request.setAttribute("pagination", pagination);

			}
			request.setAttribute("totalUnreadMessages", nbreUnreadMessage);
			request.setAttribute("totalReadMessages", nbreReadMessage);
			request.setAttribute("totalSendMessages", nbreSentMessage);
			this.getServletContext().getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "PaPfood/connexion");
		}
	}
}
