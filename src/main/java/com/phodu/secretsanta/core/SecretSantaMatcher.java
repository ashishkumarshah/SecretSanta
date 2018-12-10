package com.phodu.secretsanta.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.phodu.secretsanta.SecretSantaParticipant;
import com.phodu.secretsanta.SecretSantaParticipantPair;

public class SecretSantaMatcher {
	/**
	 * 
	 * @param pParticipants
	 * @return
	 */
	public static List<SecretSantaParticipantPair> match(List<SecretSantaParticipant> pParticipants) {
		List<SecretSantaParticipantPair> matches = new LinkedList<SecretSantaParticipantPair>();
		List<Integer> matchingIndices = generateMatches(pParticipants.size());
		for (int i = 0; i < pParticipants.size(); i++) {
			matches.add(
					new SecretSantaParticipantPair(pParticipants.get(i), pParticipants.get(matchingIndices.get(i))));
		}
		return matches;
	}

	/**
	 * 
	 * @param pCount
	 * @return
	 */

	private static List<Integer> generateMatches(int pCount) {
		Integer[] array = new Integer[pCount];
		for (int i = 0; i < pCount; i++) {
			array[i] = i;
		}
		Random random = new Random();
		// Loop over array.
		for (int i = 0; i < array.length -1; i++) {
			// Get a random index of the array past the current index.
			// ... The argument is an exclusive bound.
			// It will not go past the array's end.
			int randomValue = i + random.nextInt(pCount - i);
			// Swap the random element with the present element.
			int randomElement = array[randomValue];
			array[randomValue] = array[i];
			array[i] = randomElement;
		}
		return Arrays.asList(array);
	}
}
