package com.phodu.secretsanta.core;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.phodu.secretsanta.SecretSantaParticipant;
import com.phodu.secretsanta.SecretSantaParticipantPair;
/**
 * Class to Generate Secret Santa Matches
 * @author ashish
 *
 */
public class SecretSantaMatcher {
	/**
	 * Function to find a secret santa match for the given list of participants
	 * @param pParticipants The list of secret santa participants
	 * @return A list of secret santa participant pairs
	 */
	public static List<SecretSantaParticipantPair> match(List<SecretSantaParticipant> pParticipants) {
		List<SecretSantaParticipantPair> matches = new LinkedList<SecretSantaParticipantPair>();
		//This is the core logic
		int[] matchingIndices = getShuffledArray(pParticipants.size());
		for (int i = 0; i < pParticipants.size(); i++) {
			matches.add(new SecretSantaParticipantPair(pParticipants.get(i), pParticipants.get(matchingIndices[i])));
		}
		return matches;
	}

	/**
	 * Function to return an array containing shuffled elements from 0 to pSize -1
	 * 
	 * @param pSize The size of the shuffled array
	 * @return The shuffled Array
	 */

	private static int[] getShuffledArray(int pSize) {
		int[] shuffledArray = new int[pSize];
		for (int i = 0; i < pSize; i++) {
			shuffledArray[i] = i;
		}
		Random random = new Random();
		for (int i = 0; i < shuffledArray.length - 1; i++) {
			int randomIndex = i + 1 + random.nextInt(pSize - 1 - i);
			int randomElement = shuffledArray[randomIndex];
			shuffledArray[randomIndex] = shuffledArray[i];
			shuffledArray[i] = randomElement;
		}
		return shuffledArray;
	}
}
