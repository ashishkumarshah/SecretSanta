package com.phodu.secretsanta;

public class SecretSantaParticipantPair {
	private SecretSantaParticipant fromParticipant;
	private SecretSantaParticipant toParticipant;
	public SecretSantaParticipantPair(SecretSantaParticipant fromParticipant, SecretSantaParticipant toParticipant) {
		this.fromParticipant = fromParticipant;
		this.toParticipant = toParticipant;
	}
	public SecretSantaParticipant getFromParticipant() {
		return fromParticipant;
	}
	public void setFromParticipant(SecretSantaParticipant fromParticipant) {
		this.fromParticipant = fromParticipant;
	}
	public SecretSantaParticipant getToParticipant() {
		return toParticipant;
	}
	public void setToParticipant(SecretSantaParticipant toParticipant) {
		this.toParticipant = toParticipant;
	}
}
