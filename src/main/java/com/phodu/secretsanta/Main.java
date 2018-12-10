package com.phodu.secretsanta;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.phodu.secretsanta.core.SecretSantaMatcher;
import com.phodu.secretsanta.notify.EmailNotifier;
import com.phodu.secretsanta.notify.Notifier;

public class Main {
	final static Logger logger = LogManager.getLogger(Main.class);
	private final static String DEFAULT_PARTICIPANTS_FILE = "participants.properties";
	private final static String DEFAULT_SMTP_CONFIG = "smtp.properties";
	private static final String PARTICIPANTS_KEY = "participants";

	public static void main(String[] args) {
		String participantsFileName = args.length >= 2 ? args[0] : DEFAULT_PARTICIPANTS_FILE;
		String smtpConfigFileName = args.length >= 2 ? args[1] : DEFAULT_SMTP_CONFIG;
		logger.debug("Participants file path {}", participantsFileName);
		logger.debug("SMTP Config file path {}", smtpConfigFileName);
		String[] participants = readParticipants(participantsFileName);
		List<SecretSantaParticipant> secretSantaParticipants = new LinkedList<SecretSantaParticipant>();
		for (String participant : participants) {
			secretSantaParticipants.add(new SecretSantaParticipant(participant));
		}
		List<SecretSantaParticipantPair> matches = SecretSantaMatcher.match(secretSantaParticipants);
		Notifier notifier = new EmailNotifier(smtpConfigFileName);
		for (SecretSantaParticipantPair match : matches) {
			notifier.notify(match);
		}
	}

	private static String[] readParticipants(String pParticipantsFileLocation) {

		FileReader reader = null;
		try {
			reader = new FileReader(pParticipantsFileLocation);
			Properties p = new Properties();
			p.load(reader);
			if (!p.containsKey(PARTICIPANTS_KEY)) {
				logger.error("No properties field in the given properties file");
			}
			String commaSeperatedParticipants = p.getProperty(PARTICIPANTS_KEY);
			return commaSeperatedParticipants.split(",");
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return null;
	}
}