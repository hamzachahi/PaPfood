package config;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

public class UseMail {

	public static boolean sendMessage(String email, String subject, String text, String destinataire,
			String copyDest) {
		boolean isSucceed=false;
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", "smtp-relay.sendinblue.com");
		properties.setProperty("mail.smtp.user", "noubijunior@gmail.com");
		properties.setProperty("mail.smtp.from", "noubijunior@yahoo.fr");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.starttls.enable", "true");


		Session session = Session.getInstance(properties);
		System.out.println(session.getProperties().toString());

		MimeMessage message = new MimeMessage(session);
		try {

			message.setText(text);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(email));
			message.addRecipients(Message.RecipientType.TO, destinataire);
			message.addRecipients(Message.RecipientType.CC, copyDest);
			System.out.println("J'ai préparé le message");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		Transport transport = null;
		try {
			transport = session.getTransport("smtp");
			transport.connect("noubijunior@gmail.com", "aZD5Y2PsU0JSy3zp");
			transport.sendMessage(message,
					new Address[] { new InternetAddress(destinataire), new InternetAddress(copyDest) });
			isSucceed=true;
			System.out.println("J'ai envoyé le message");
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return isSucceed;
	}

	
}
