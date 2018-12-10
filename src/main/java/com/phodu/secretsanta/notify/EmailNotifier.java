package com.phodu.secretsanta.notify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.phodu.secretsanta.SecretSantaParticipant;
import com.phodu.secretsanta.SecretSantaParticipantPair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailNotifier implements Notifier {
	final static Logger logger = LogManager.getLogger(EmailNotifier.class);
	private Properties smtpConfig;
	private Transport transport;
	private Session session;
	private String FROM = null;
	private String FROMNAME = null;
	private String SMTP_USERNAME = null;
	private String SMTP_PASSWORD = null;
	private String SMTP_HOST = null;
	private int SMTP_PORT = 587;
	private String MAIL_TEXT = "";
	private String SUBJECT = "";

	public void connect() throws MessagingException {
		logger.debug("Connecting");
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the specified
		// properties.
		session = Session.getDefaultInstance(props);
		transport = session.getTransport();
		logger.debug("Connecting to {} with username {}", SMTP_HOST, SMTP_USERNAME);
		transport.connect(SMTP_HOST, SMTP_USERNAME, SMTP_PASSWORD);

	}

	public void close() {
		try {
			transport.close();
		} catch (MessagingException e) {
			logger.error(e.getMessage());
		}
	}

	public EmailNotifier(String smtpConfigFileName) throws MessagingException {
		logger.debug("Reading Config from {}", smtpConfigFileName);
		FileReader reader = null;
		try {
			reader = new FileReader(smtpConfigFileName);
			smtpConfig = new Properties();
			smtpConfig.load(reader);
			FROM = smtpConfig.getProperty("fromEmail");
			FROMNAME = smtpConfig.getProperty("fromName");
			SMTP_USERNAME = smtpConfig.getProperty("smtpUsername");
			SMTP_PASSWORD = smtpConfig.getProperty("smtpPassword");
			SMTP_HOST = smtpConfig.getProperty("smtpHost");
			SMTP_PORT = Integer.parseInt(smtpConfig.getProperty("smtpPort"));
			SUBJECT = smtpConfig.getProperty("subject");
			MAIL_TEXT = smtpConfig.getProperty("mailText");
		} catch (FileNotFoundException e) {
			logger.catching(e);
			logger.error(e.toString());
		} catch (IOException e) {
			logger.catching(e);
			logger.error(e.toString());
		}
		connect();

	}

	public void sendMail(String pToAddress, String pMessage) throws UnsupportedEncodingException, MessagingException {

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(pToAddress));
		msg.setSubject(SUBJECT);
		msg.setContent(pMessage, "text/html");
		logger.debug("Sending...");
		transport.sendMessage(msg, msg.getAllRecipients());
		logger.debug("Email sent!");

	}

	@Override
	public void notify(SecretSantaParticipantPair pParticipantPair) {
		SecretSantaParticipant fromParticipant = pParticipantPair.getFromParticipant();
		SecretSantaParticipant toParticipant = pParticipantPair.getToParticipant();
		String message = String.format(MAIL_TEXT, toParticipant.getEmail());
		logger.debug("Sending {} to {}", message, fromParticipant.getEmail());
		try {
			sendMail(fromParticipant.getEmail(), message);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}

	}
}
