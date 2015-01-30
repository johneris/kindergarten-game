package com.johneris.kindergartengame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class LearnActivity extends Activity {

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	Button buttonWrite;
	Button buttonNumbers;
	Button buttonColors;
	Button buttonShapes;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.learn_layout);

		/* Background Image */

		// adapt the image to the size of the display
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.background_menu), size.x, size.y,
				true);

		// fill the background ImageView with the resized image
		ImageView iv_background = (ImageView) findViewById(R.id.learn_imageViewBackground);
		iv_background.setImageBitmap(bmp);

		/* Initialize Views */

		buttonWrite = (Button) findViewById(R.id.learn_buttonWrite);
		buttonWrite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LearnActivity.this, LearnWriteActivity.class);
				intent.putExtra(Keys.LEARN, Constants.LEARN_WRITE_LETTER_UPPERCASE);
				startActivity(intent);
				finish();
			}
		});

		buttonNumbers = (Button) findViewById(R.id.learn_buttonNumbers);
		buttonNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LearnActivity.this, LearnNumberActivity.class);
				startActivity(intent);
				finish();
			}
		});

		buttonColors = (Button) findViewById(R.id.learn_buttonColors);
		buttonColors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			}
		});

		buttonShapes = (Button) findViewById(R.id.learn_buttonShapes);
		buttonShapes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(LearnActivity.this, LearnShapeActivity.class);
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
		// start UserProfile activity and finish LearnActivity
		Intent intent = new Intent(LearnActivity.this,
				UserProfileActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
