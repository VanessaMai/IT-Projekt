package roomreservationservice.server;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Klasse, mit einer Methode zum Versenden einer Email. Angelehnt an: <a
 * href="https://cloud.google.com/appengine/docs/java/mail/">Google AppEngine Mail API Overview</a>.
 * 
 * @author Julius Renner
 *
 */
public class Email {

	/**
	 * Methode, um einfache Email zu versenden.
	 * 
	 * @param recipientAdress
	 *            Email-Adresse des Empfängers
	 * @param messageSubject
	 *            Betreff der Email
	 * @param messageBody
	 *            Inhalt der Email
	 */
	public static void sendEmail(String recipientAdress, String messageSubject, String messageBody) {
		try {
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("julrendev@gmail.com", "RoomReservationService"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientAdress, "User"));
			msg.setSubject(messageSubject);
			msg.setText(messageBody);
			Transport.send(msg);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}