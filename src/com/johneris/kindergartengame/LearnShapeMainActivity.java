package com.johneris.kindergartengame;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class LearnShapeMainActivity extends Activity {

	boolean continueMusic = true;
	
	ImageView ivShape;
	TextView tvShape;
	
	String learnItem;

	@SuppressLint({ "NewApi", "DefaultLocale" })
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.learn_shape_main_layout);

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

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
		} else {
			if (extras.containsKey(Keys.LEARN_ITEM)) {
				learnItem = extras.getString(Keys.LEARN_ITEM);
			}
		}

		/* Initialize Views */

		ivShape = (ImageView) findViewById(R.id.ivShape);
		tvShape = (TextView) findViewById(R.id.tvShape);

		try {
			InputStream ims = getAssets().open(
					Constants.LEARN_SHAPE_DIR + 
					learnItem + ".PNG");
			Drawable d = Drawable.createFromStream(ims, null);
			ivShape.setImageDrawable(d);
		} catch (IOException ex) {
		}
		tvShape.setText(learnItem);

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
		Intent intent = new Intent(LearnShapeMainActivity.this,
				LearnShapeActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
