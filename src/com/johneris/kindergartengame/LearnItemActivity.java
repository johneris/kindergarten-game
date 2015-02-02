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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class LearnItemActivity extends Activity {

	boolean continueMusic = true;

	TextView tvTopTitle;
	ImageView iv;

	String item;
	String category;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.learn_item_layout);

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

		/* Get extras */

		Bundle extras = getIntent().getExtras();
		item = extras.getString(Keys.LEARN_ITEM);
		category = extras.getString(Keys.CATEGORY);

		/* Initialize Views */

		tvTopTitle = (TextView) this.findViewById(R.id.global_textViewTopTitle);
		tvTopTitle.setText(item);

		iv = (ImageView) this.findViewById(R.id.learnContent_imageView);

		loadImage();
	}

	
	
	private void loadImage() {

		if (Constants.CATEGORY_WRITE_LETTER.equals(category) ||
				Constants.CATEGORY_WRITE_NUMBER.equals(category)) {

			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.learnItem_frameLayoutImage);

			DrawView drawView = new DrawView(getApplicationContext());

			LinearLayout linearLayout = new LinearLayout(
					getApplicationContext());

			LayoutParams params = new LayoutParams(frameLayout.getLayoutParams().width,
					frameLayout.getLayoutParams().height, 1.0f);
			linearLayout.setLayoutParams(params);

			linearLayout.addView(drawView);
			frameLayout.addView(linearLayout);

			try {
				String dir = "";
				if(Constants.CATEGORY_WRITE_LETTER.equals(category) &&
						Character.isLowerCase(item.charAt(0))) {
					dir = Constants.LEARN_WRITE_LETTER_LOWERCASE_DIR;
				} else if(Constants.CATEGORY_WRITE_LETTER.equals(category) &&
						Character.isUpperCase(item.charAt(0))) {
					dir = Constants.LEARN_WRITE_LETTER_UPPERCASE_DIR;
				} else if(Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
					dir = Constants.LEARN_WRITE_NUMBER_DIR;
				}
				
				InputStream ims = getAssets()
						.open(dir + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else if (Constants.CATEGORY_COUNT_NUMBERS.equals(category)) {

			try {
				InputStream ims = getAssets().open(
						Constants.LEARN_COUNT_NUMBER_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}

		} else if (Constants.CATEGORY_COLORS.equals(category)) {
			
			try {
				InputStream ims = getAssets().open(
						Constants.LEARN_COLOR_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}
			
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {

			try {
				InputStream ims = getAssets().open(
						Constants.LEARN_SHAPE_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}

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
		Intent intent = new Intent(LearnItemActivity.this,
				LearnSubMenuActivity.class);
		intent.putExtra(Keys.CATEGORY, category);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}
	
}
