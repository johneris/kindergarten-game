package com.johneris.kindergartengame;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.EAvatar;
import com.johneris.kindergartengame.common.MusicManager;
import com.johneris.kindergartengame.common.UserProfile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ExistingUserActivity extends Activity {

	boolean continueMusic = true;

	ImageButton btnWinnieThePooh;
	TextView tvWinnieThePoohUserName;

	ImageButton btnMickeyMouse;
	TextView tvMickeyMouseUserName;

	ImageButton btnDonaldDuck;
	TextView tvDonaldDuckUserName;

	ImageButton btnHelloKitty;
	TextView tvHelloKittyUserName;

	ImageButton btnDora;
	TextView tvDoraUserName;

	ImageButton btnSpongebob;
	TextView tvSpongebobUserName;

	ImageButton btnPatrick;
	TextView tvPatrickUserName;

	ImageButton btnTweetyBird;
	TextView tvTweetyBirdUserName;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.existing_user_layout);

		/* Background Image */

		// adapt the image to the size of the display
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.background_menu), size.x, size.y,
				true);

		// fill the background ImageView with the resized image
		ImageView iv_background = (ImageView) findViewById(R.id.ivBackground);
		iv_background.setImageBitmap(bmp);

		/* Initialize Views */

		btnWinnieThePooh = (ImageButton) findViewById(R.id.btnWinneThePooh);
		tvWinnieThePoohUserName = (TextView) findViewById(R.id.tvWinneThePoohUserName);

		btnMickeyMouse = (ImageButton) findViewById(R.id.btnMickeyMouse);
		tvMickeyMouseUserName = (TextView) findViewById(R.id.tvMickeyMouseUserName);

		btnDonaldDuck = (ImageButton) findViewById(R.id.btnDonaldDuck);
		tvDonaldDuckUserName = (TextView) findViewById(R.id.tvDonaldDuckUserName);

		btnHelloKitty = (ImageButton) findViewById(R.id.btnHelloKitty);
		tvHelloKittyUserName = (TextView) findViewById(R.id.tvHelloKittyUserName);

		btnDora = (ImageButton) findViewById(R.id.btnDora);
		tvDoraUserName = (TextView) findViewById(R.id.tvDoraUserName);

		btnSpongebob = (ImageButton) findViewById(R.id.btnSpongebob);
		tvSpongebobUserName = (TextView) findViewById(R.id.tvSpongebobUserName);

		btnPatrick = (ImageButton) findViewById(R.id.btnPatrick);
		tvPatrickUserName = (TextView) findViewById(R.id.tvPatrickUserName);

		btnTweetyBird = (ImageButton) findViewById(R.id.btnTweetyBird);
		tvTweetyBirdUserName = (TextView) findViewById(R.id.tvTweetyBirdUserName);

		UserProfile user;

		user = Constants.getUserProfileForAvatar(EAvatar.WINNIE_THE_POOH);
		btnWinnieThePooh.setEnabled(user != null);
		tvWinnieThePoohUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.MICKEY_MOUSE);
		btnMickeyMouse.setEnabled(user != null);
		tvMickeyMouseUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.DONALD_DUCK);
		btnDonaldDuck.setEnabled(user != null);
		tvDonaldDuckUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.HELLO_KITTY);
		btnHelloKitty.setEnabled(user != null);
		tvHelloKittyUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.DORA);
		btnDora.setEnabled(user != null);
		tvDoraUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.SPONGEBOB);
		btnSpongebob.setEnabled(user != null);
		tvSpongebobUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.PATRICK);
		btnPatrick.setEnabled(user != null);
		tvPatrickUserName.setText(user != null ? user.userName : "");

		user = Constants.getUserProfileForAvatar(EAvatar.TWEETY_BIRD);
		btnTweetyBird.setEnabled(user != null);
		tvTweetyBirdUserName.setText(user != null ? user.userName : "");

		btnWinnieThePooh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.WINNIE_THE_POOH);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnMickeyMouse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.MICKEY_MOUSE);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnDonaldDuck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.DONALD_DUCK);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnHelloKitty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.HELLO_KITTY);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnDora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.DORA);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnSpongebob.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.SPONGEBOB);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnPatrick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.PATRICK);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnTweetyBird.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Constants.currUserProfile = Constants
						.getUserProfileForAvatar(EAvatar.TWEETY_BIRD);
				Intent intent = new Intent(ExistingUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (!continueMusic) {
			MusicManager.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		continueMusic = false;
		MusicManager.start(this, MusicManager.MUSIC_ALL);
	}

	public void onBackPressed() {
		// start LoginActivity and finish ExistingUserActivity
		Intent intent = new Intent(ExistingUserActivity.this,
				LoginActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
