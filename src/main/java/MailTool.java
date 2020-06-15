package main.java;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTool {
	
	private static String errorField = "";
	public static void addToErrors(String e){
		if(errorField.equals("")) errorField+=e;
		else errorField+="\n"+e;
	}

	public static void check(String host, String storeType, String user, String password) {
		try {
			Properties properties = new Properties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore("pop3s");

			store.connect(host, user, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);


			Message message = messages[messages.length - 1];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (messages.length));
			System.out.println("Subject: " + message.getSubject());
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("Text: " + message.getContent().toString());

			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean checkIf(String host, String storeType, String user, String password, String emailTitle) {
		try {
			Properties properties = new Properties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", "995");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);

			Store store = emailSession.getStore("pop3s");
			store.connect(host, user, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();

			Message message = messages[messages.length - 1];
			String titleSeen = message.getSubject();
			emailFolder.close(false);
			store.close();
			return titleSeen.equals(emailTitle);

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void sendEmail(String from, String to, String username, String password) {
		if (!errorField.equals("")) {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.sapo.pt");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("Errors in your website");
				message.setText(errorField);
				Transport.send(message);
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}
	}

}