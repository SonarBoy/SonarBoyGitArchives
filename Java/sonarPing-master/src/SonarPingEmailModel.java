import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
//import static org.junit.Assert.assertEquals;

/**
 * This class deals with the sending email 
 * @author Joshua, Pyojoon
 * @version 2.0
 * @since 2016-01-10
 */
public class SonarPingEmailModel implements Runnable {
	private String to;
	private String from;
	private String username;
	private String password;
	private String host;
	private Properties props;
	private Session session;

	/**
	 * This constructs a SonarPingEmailModel
	 * @param to		email to
	 * @param from		email from
	 * @param username	user name who sends the email
	 * @param password  password which relates with the user name
	 */
	public SonarPingEmailModel(String to, String from, String username, String password){
		this.to = to;
		this.from = from;
		this.username = username;
		this.password = password;
		
		this.props = new Properties();
		this.props.put("mail.smtp.auth", "true");
		this.props.put("mail.smtp.starttls.enable", "true");
		this.props.put("mail.smtp.host", "smtp.gmail.com");
		this.props.put("mail.smtp.port", "587");
	}
	
	/**
	 * This method initialize a session
	 */
	public void sessionInitialize(){
		// Get the Session object.
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	/**
	 * This method deals with sending a email 
	 */
	public void run(){
		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Testing Subject");

			// Now set the actual message
			message.setText("HEELO EVERYBODY, THIS IS GOING TO BE THE FINAL TEST FROM SONARPINGMODEL.");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
