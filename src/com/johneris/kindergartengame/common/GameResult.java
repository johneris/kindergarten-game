package com.johneris.kindergartengame.common;

import java.io.Serializable;
import java.util.ArrayList;

public class GameResult implements Serializable {

	private static final long serialVersionUID = 6166855997447700724L;

	/**
	 * duration of time played
	 */
	public double overallDuration;
	
	public String dateAndTimePlayed;
	
	/**
	 * list of item names
	 */
	public ArrayList<String> lstItemName;
	
	/**
	 * list of if answer is correct
	 */
	public ArrayList<Boolean> lstIsCorrect;
	
	/**
	 * list of duration item is played
	 */
	public ArrayList<Double> lstItemDuration;
	
	/**
	 * list of scores 0-3
	 * depends on itemDuration 
	 * and isSorrect
	 */
	public ArrayList<Integer> lstScore;
	
	
	public GameResult() {
		this.overallDuration = 0;
		this.dateAndTimePlayed = "";
		this.lstItemName = new ArrayList<>();
		this.lstIsCorrect = new ArrayList<>();
		this.lstItemDuration = new ArrayList<>();
		this.lstScore = new ArrayList<>();
	}
	
	
	/**
	 * generate scores based on
	 * lstItemDuration and lstIsCorrect
	 */
	public void generateScores() {
		// for all items
		for(int i = 0; i < lstItemName.size(); i++) {
			boolean isCorrect = lstIsCorrect.get(i);
			int score = 0;
			// 3 because score is up to 3
			double scoreInterval = Constants.MAX_TIME_PER_ITEM / 3;
			if(isCorrect) {
				double itemDuration = lstItemDuration.get(i);
				if(itemDuration >=0 && itemDuration <= scoreInterval) {
					score = 3;
				} else if(itemDuration >=scoreInterval && itemDuration <= (scoreInterval*2)) {
					score = 2;
				} else if(itemDuration >=(scoreInterval*2) && itemDuration <= (scoreInterval*3)) {
					score = 1;
				}
			}
			lstScore.add(score);
		}
	}
	
	
}
