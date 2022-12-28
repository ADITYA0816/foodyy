package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

//Activation mail sending process
public class EmailSender {
	public static void sendEmail(String toEmail, String subject, String message) {
		Properties properties = new Properties();
		
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(properties, new MailAuthenticator());
		
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setFrom("dholubholu1611@gmail.com");
			
			mimeMessage.setRecipients(Message.RecipientType.TO , toEmail);
			
			mimeMessage.setSubject(subject);
			
			mimeMessage.setContent(message, "text/html");
			
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

class MailAuthenticator extends Authenticator{
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("dholubholu1611@gmail.com", "a1d4i9tya@");
	}
}





