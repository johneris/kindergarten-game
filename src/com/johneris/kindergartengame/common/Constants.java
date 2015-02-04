package com.johneris.kindergartengame.common;

import java.util.ArrayList;

public class Constants {

	/**
	 * filename to store user profiles
	 */
	public static final String userProfileFile = "user_profile.txt";

	/**
	 * list of user profiles
	 */
	public static ArrayList<UserProfile> lstUserProfile;

	/**
	 * user profile of current user
	 */
	public static UserProfile currUserProfile;

	public static ArrayList<String> lstLetter;
	public static ArrayList<String> lstWritingNumber;
	public static ArrayList<String> lstCountingNumber;
	public static ArrayList<String> lstShape;
	public static ArrayList<String> lstColor;

	/**
	 * category constants
	 */
	public static final String CATEGORY_WRITE_LETTER = "Writing Letters";
	public static final String CATEGORY_WRITE_NUMBER = "Writing Numbers";
	public static final String CATEGORY_COUNT_NUMBERS = "Counting Numbers";
	public static final String CATEGORY_COLORS = "Colors";
	public static final String CATEGORY_SHAPES = "Shapes";
	
	public static final String MENU_LEARN = "LEARN";
	public static final String MENU_PLAY = "PLAY";
	public static final String MENU_SCORES = "SCORES";

	public static final int MAX_TIME_PER_ITEM = 9;
	public static final int ITEMS_PER_GAME = 5;

	public static final String LEARN_WRITE_LETTER_UPPERCASE_DIR = "learn/write/letter/uppercase/";
	public static final String LEARN_WRITE_LETTER_LOWERCASE_DIR = "learn/write/letter/lowercase/";
	public static final String LEARN_WRITE_NUMBER_DIR = "learn/write/number/";
	public static final String LEARN_COUNT_NUMBER_DIR = "learn/number/";
	public static final String LEARN_SHAPE_DIR = "learn/shape/";
	public static final String LEARN_COLOR_DIR = "learn/color/";
	
	public static final String PLAY_WRITE_LETTER_UPPERCASE_DIR = "play/write/letter/uppercase/";
	public static final String PLAY_WRITE_LETTER_LOWERCASE_DIR = "play/write/letter/lowercase/";
	public static final String PLAY_WRITE_NUMBER_DIR = "play/write/number/";
	public static final String PLAY_SHAPE_DIR = "play/shape/";
	public static final String PLAY_COLOR_DIR = "play/color/";
	
	public static final String ACTIVITY_GAMES = "GamesActivity";
	public static final String ACTIVITY_SCORES = "ScoresActivity";
	
	static {

		lstUserProfile = new ArrayList<>();

		lstLetter = new ArrayList<>();
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < letters.length(); i++) {
			lstLetter.add(letters.charAt(i) + "");
		}

		lstWritingNumber = new ArrayList<>();
		String numbers = "0123456789";
		for (int i = 0; i < numbers.length(); i++) {
			lstWritingNumber.add(numbers.charAt(i) + "");
		}
		
		lstCountingNumber = new ArrayList<>();
		for (int ctr = 1; ctr <= 10; ctr++) {
			lstCountingNumber.add(ctr + "");
		}
		
		lstShape = new ArrayList<>();
		lstShape.add("circle");
		lstShape.add("diamond");
		lstShape.add("heart");
		lstShape.add("oblong");
		lstShape.add("octagon");
		lstShape.add("rectangle");
		lstShape.add("star");
		lstShape.add("triangle");
		
		lstColor = new ArrayList<>();
		lstColor.add("black");
		lstColor.add("blue");
		lstColor.add("brown");
		lstColor.add("gray");
		lstColor.add("green");
		lstColor.add("orange");
		lstColor.add("pink");
		lstColor.add("purple");
		lstColor.add("red");
		lstColor.add("white");
		lstColor.add("yellow");
		
	}

	public static UserProfile getUserProfileForAvatar(EAvatar avatar) {
		for (UserProfile user : Constants.lstUserProfile) {
			if (user.avatar == avatar) {
				return user;
			}
		}
		// no user profile
		return null;
	}

}
