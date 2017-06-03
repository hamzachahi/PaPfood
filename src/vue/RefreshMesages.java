package vue;

import javax.servlet.http.HttpSession;

import beans.Person;
import dao.MessageDao;

public class RefreshMesages implements Runnable {
	MessageDao messageDao;
	HttpSession session;

	public RefreshMesages(MessageDao messageDao2, HttpSession session) {
		super();
		this.messageDao = messageDao2;
		this.session = session;
	}

	@Override
	public void run() {
		while(true){
		Person user = (Person) session.getAttribute("sessionUtilisateur");
		Long nbre = messageDao.countNbreMessageUnReadById(user.getId());
		session.setAttribute("nbremessagenonlu", nbre);
		System.out.println("RefreshMesages.run()");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

		}}
	}

}
