package com.johneris.kindergartengame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.EAvatar;
import com.johneris.kindergartengame.common.MusicManager;
import com.johneris.kindergartengame.common.StoreUserProfiles;
import com.johneris.kindergartengame.common.UserProfile;

public class NewUserActivity extends Activity {

	boolean continueMusic = true;

	ImageButton btnWinnieThePooh;
	ImageButton btnMickeyMouse;
	ImageButton btnDonaldDuck;
	ImageButton btnHelloKitty;
	ImageButton btnDora;
	ImageButton btnSpongebob;
	ImageButton btnPatrick;
	ImageButton btnTweetyBird;

	EditText etName;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.new_user_layout);

		/* Background Image */

		// adapt the image to the size of the display
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.background_menu), size.x, size.y,
				true);

		// fill the background ImageView with the resized image
		ImageView iv_background = (ImageView) findViewById(R.id.global_imageViewBackground);
		iv_background.setImageBitmap(bmp);

		/* Initialize views */

		btnWinnieThePooh = (ImageButton) findViewById(R.id.global_buttonWinneThePooh);
		btnMickeyMouse = (ImageButton) findViewById(R.id.global_buttonMickeyMouse);
		btnDonaldDuck = (ImageButton) findViewById(R.id.global_buttonDonaldDuck);
		btnHelloKitty = (ImageButton) findViewById(R.id.global_buttonHelloKitty);
		btnDora = (ImageButton) findViewById(R.id.global_buttonDora);
		btnSpongebob = (ImageButton) findViewById(R.id.global_buttonSpongebob);
		btnPatrick = (ImageButton) findViewById(R.id.global_buttonPatrick);
		btnTweetyBird = (ImageButton) findViewById(R.id.global_buttonTweetyBird);
		
		btnWinnieThePooh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.WINNIE_THE_POOH;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnMickeyMouse.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.MICKEY_MOUSE;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnDonaldDuck.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.DONALD_DUCK;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnHelloKitty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.HELLO_KITTY;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnDora.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.DORA;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnSpongebob.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.SPONGEBOB;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnPatrick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.PATRICK;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		btnTweetyBird.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UserProfile newUserProfile = new UserProfile();
				newUserProfile.avatar = EAvatar.TWEETY_BIRD;
				newUserProfile.userName = etName.getText().toString();
				Constants.currUserProfile = newUserProfile;
				Constants.lstUserProfile.add(newUserProfile);
				StoreUserProfiles.saveToFile(getApplicationContext());
				Intent intent = new Intent(NewUserActivity.this,
						UserProfileActivity.class);
				startActivity(intent);
				finish();
			}
		});

		etName = (EditText) findViewById(R.id.newUser_editTextName);
		etName.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void onTextChanged(CharSequence text, int arg1, int arg2,
					int arg3) {
				boolean isEditTextEmpty = text.toString().equals("");
				btnWinnieThePooh.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnMickeyMouse.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnDonaldDuck.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnHelloKitty.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnDora.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnSpongebob.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnPatrick.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				btnTweetyBird.setVisibility(!isEditTextEmpty ? View.VISIBLE : View.GONE);
				manageEnableButtons();
			}
		});

		btnWinnieThePooh.setVisibility(View.GONE);
		btnMickeyMouse.setVisibility(View.GONE);
		btnDonaldDuck.setVisibility(View.GONE);
		btnHelloKitty.setVisibility(View.GONE);
		btnDora.setVisibility(View.GONE);
		btnSpongebob.setVisibility(View.GONE);
		btnPatrick.setVisibility(View.GONE);
		btnTweetyBird.setVisibility(View.GONE);

	}

	private void manageEnableButtons() {
		UserProfile user;

		user = Constants.getUserProfileForAvatar(EAvatar.WINNIE_THE_POOH);
		if(user != null) {
			btnWinnieThePooh.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.MICKEY_MOUSE);
		if(user != null) {
			btnMickeyMouse.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.DONALD_DUCK);
		if(user != null) {
			btnDonaldDuck.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.HELLO_KITTY);
		if(user != null) {
			btnHelloKitty.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.DORA);
		if(user != null) {
			btnDora.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.SPONGEBOB);
		if(user != null) {
			btnSpongebob.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.PATRICK);
		if(user != null) {
			btnPatrick.setVisibility(View.GONE);
		}

		user = Constants.getUserProfileForAvatar(EAvatar.TWEETY_BIRD);
		if(user != null) {
			btnTweetyBird.setVisibility(View.GONE);
		}

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
		// start LoginActivity and finish NewUserActivity
		Intent intent = new Intent(NewUserActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
