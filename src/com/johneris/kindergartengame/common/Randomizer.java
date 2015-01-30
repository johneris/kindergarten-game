package com.johneris.kindergartengame.common;

import java.util.ArrayList;
import java.util.Random;

public class Randomizer {

	/**
	 * get randNumCt random indexes
	 * from array with array size arraySize
	 */
	public static ArrayList<Integer> getRandomIndexes(int arraySize, int randNumCt) {
		ArrayList<Integer> lstIndex = new ArrayList<>();
		Random randomizer = new Random();
		int ctr = 0;
		while(ctr != randNumCt) {
			int randIndex;
			do {
				randIndex = Math.abs(randomizer.nextInt()) % arraySize;
			}while(lstIndex.contains(randIndex));
			lstIndex.add(randIndex);
			ctr++;
		}
		return lstIndex;
	}
	
}
