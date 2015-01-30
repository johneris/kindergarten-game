package com.johneris.kindergartengame.common;

import java.io.Serializable;
import java.util.ArrayList;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = -4100080627867712305L;

	/**
	 * userName of user
	 */
	public String userName;
	
	/**
	 * avatar of user
	 */
	public EAvatar avatar;
	
	/**
	 * list of write game result
	 */
	public ArrayList<GameResult> lstWriteGameResult;
	
	/**
	 * list of number game result
	 */
	public ArrayList<GameResult> lstNumberGameResult;
	
	/**
	 * list of shape game result
	 */
	public ArrayList<GameResult> lstShapeGameResult;
	
	/**
	 * list of color game result
	 */
	public ArrayList<GameResult> lstColorGameResult;
	
	/**
	 * list of add and subtract game result
	 */
	public ArrayList<GameResult> lstAddSubtractGameResult;
	
	public UserProfile() {
		this.userName = "";
		this.avatar = null;
		this.lstWriteGameResult = new ArrayList<>();
		this.lstNumberGameResult = new ArrayList<>();
		this.lstShapeGameResult = new ArrayList<>();
		this.lstColorGameResult = new ArrayList<>();
		this.lstAddSubtractGameResult = new ArrayList<>();
	}
	
}
