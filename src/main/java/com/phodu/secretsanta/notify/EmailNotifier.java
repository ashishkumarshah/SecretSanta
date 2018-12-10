package com.phodu.secretsanta.notify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.phodu.secretsanta.SecretSantaParticipant;
import com.phodu.secretsanta.SecretSantaParticipantPair;

public class EmailNotifier implements Notifier {
	final static Logger logger = LogManager.getLogger(EmailNotifier.class);

	public EmailNotifier(String smtpConfigFileName) {
	}

	@Override
	public void notify(SecretSantaParticipantPair pParticipantPair) {
		SecretSantaParticipant fromParticipant = pParticipantPair.getFromParticipant();
		SecretSantaParticipant toParticipant = pParticipantPair.getToParticipant();
		logger.debug("From {} to {}", fromParticipant.getEmail(), toParticipant.getEmail());
	}
}
