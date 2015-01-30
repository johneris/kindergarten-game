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
	public static ArrayList<String> lstNumber;
	public static ArrayList<String> lstShape;
	public static ArrayList<String> lstColor;

	/**
	 * category constants
	 */
	public static final String CATEGORY_WRITE = "Write";
	public static final String CATEGORY_NUMBERS = "Numbers";
	public static final String CATEGORY_COLORS = "Colors";
	public static final String CATEGORY_SHAPES = "Shapes";

	public static final String LEARN_WRITE_LETTER_UPPERCASE = "LEARN_WRITE_LETTER_UPPERCASE";
	public static final String LEARN_WRITE_LETTER_LOWERCASE = "LEARN_WRITE_LETTER_LOWERCASE";
	public static final String LEARN_WRITE_NUMBER = "LEARN_WRITE_NUMBER";
	public static final String LEARN_NUMBER = "LEARN_NUMBER";
	public static final String LEARN_COLOR = "LEARN_COLOR";
	public static final String LEARN_SHAPE = "LEARN_SHAPE";

	public static final int MAX_TIME_PER_ITEM = 9;
	public static final int ITEMS_PER_GAME = 5;

	public static final String LEARN_WRITE_LETTER_UPPERCASE_DIR = "learn/write/letter/uppercase/";
	public static final String LEARN_WRITE_LETTER_LOWERCASE_DIR = "learn/write/letter/lowercase/";
	public static final String LEARN_WRITE_NUMBER_DIR = "learn/write/number/";
	public static final String LEARN_NUMBER_DIR = "learn/number/";
	public static final String LEARN_SHAPE_DIR = "play/shape/";

	public static final String ACTIVITY_GAMES = "GamesActivity";
	public static final String ACTIVITY_SCORES = "ScoresActivity";

	static {

		lstUserProfile = new ArrayList<>();

		lstLetter = new ArrayList<>();
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < letters.length(); i++) {
			lstLetter.add(letters.charAt(i) + "");
		}

		lstNumber = new ArrayList<>();
		String numbers = "0123456789";
		for (int i = 0; i < numbers.length(); i++) {
			lstNumber.add(numbers.charAt(i) + "");
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
