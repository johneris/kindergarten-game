package com.johneris.kindergartengame.common;

import java.io.Serializable;

import com.johneris.kindergartengame.R;

public enum EAvatar implements Serializable {

	WINNIE_THE_POOH, MICKEY_MOUSE, HELLO_KITTY, DONALD_DUCK,
	SPONGEBOB, PATRICK, DORA, TWEETY_BIRD;

	public int getImageResource() {
		switch (this) {
		case WINNIE_THE_POOH:
			return R.drawable.avatar_winnie_the_pooh;
		case MICKEY_MOUSE:
			return R.drawable.avatar_mickey_mouse;
		case HELLO_KITTY:
			return R.drawable.avatar_hello_kitty;
		case DONALD_DUCK:
			return R.drawable.avatar_donald_duck;
		case SPONGEBOB:
			return R.drawable.avatar_spongebob;
		case PATRICK:
			return R.drawable.avatar_patrick;
		case DORA:
			return R.drawable.avatar_dora;
		case TWEETY_BIRD:
			return R.drawable.avatar_tweety_bird;
		default:
			return 0;
		}
	}

}
