package com.johneris.kindergartengame;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class LearnItemActivity extends Activity {

	boolean continueMusic = true;

	TextView tvTopTitle;
	ImageView iv;

	Button btnPrev;
	Button btnNext;

	LinearLayout linearLayoutBot;

	String item;
	String category;

	ArrayList<String> itemsInCategory;

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

		tvTopTitle = (TextView) this
				.findViewById(R.id.globalGame_textViewTopTitle);

		iv = (ImageView) this.findViewById(R.id.learnItem_imageView);

		btnNext = (Button) this.findViewById(R.id.learnItem_buttonNext);
		btnPrev = (Button) this.findViewById(R.id.learnItem_buttonPrevious);

		linearLayoutBot = (LinearLayout) findViewById(R.id.learnItem_linearLayoutBottom);

		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			itemsInCategory = Constants.lstLetter;
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			itemsInCategory = Constants.lstWritingNumber;
		} else if (Constants.CATEGORY_COUNT_NUMBERS.equals(category)) {
			itemsInCategory = Constants.lstCountingNumber;
		} else if (Constants.CATEGORY_COLORS.equals(category)) {
			itemsInCategory = Constants.lstColor;
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			itemsInCategory = Constants.lstShape;
		}

		load();
	}

	@SuppressLint("DefaultLocale")
	private void load() {

		// remove view containing Clear
		linearLayoutBot.removeAllViews();

		// set title
		tvTopTitle.setText(item);

		// load image
		if (Constants.CATEGORY_WRITE_LETTER.equals(category)
				|| Constants.CATEGORY_WRITE_NUMBER.equals(category)) {

			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.learnItem_frameLayoutImage);
			frameLayout.removeAllViews();

			final DrawView drawView = new DrawView(getApplicationContext());

			LinearLayout linearLayout = new LinearLayout(
					getApplicationContext());

			Button btnClear = new Button(getApplicationContext());
			btnClear.setText("Clear");
			linearLayoutBot.addView(btnClear);

			linearLayout.addView(drawView);
			frameLayout.addView(iv);

			frameLayout.addView(linearLayout);

			btnClear.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					drawView.clear();
				}
			});

			String dir = "";
			if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
				dir = Constants.WRITE_LETTER_DIR;
				tvTopTitle.setText(item.toUpperCase() + " "
						+ item.toLowerCase());
			} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
				dir = Constants.WRITE_NUMBER_DIR;
			}

			try {

				InputStream ims = getAssets().open(dir + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else if (Constants.CATEGORY_COUNT_NUMBERS.equals(category)) {

			try {
				InputStream ims = getAssets().open(
						Constants.COUNT_NUMBER_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}

		} else if (Constants.CATEGORY_COLORS.equals(category)) {

			try {
				InputStream ims = getAssets().open(
						Constants.COLOR_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}

		} else if (Constants.CATEGORY_SHAPES.equals(category)) {

			try {
				InputStream ims = getAssets().open(
						Constants.SHAPE_DIR + item + ".PNG");
				Drawable d = Drawable.createFromStream(ims, null);
				iv.setImageDrawable(d);
			} catch (IOException ex) {
			}

		}

		// set button onclicks
		btnPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int i = getCurrIndex();
				i = (i - 1 < 0) ? itemsInCategory.size() - 1 : i - 1;
				item = itemsInCategory.get(i);
				load();
			}
		});

		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				int i = getCurrIndex();
				i = (i + 1 == itemsInCategory.size()) ? 0 : i + 1;
				item = itemsInCategory.get(i);
				load();
			}
		});
	}

	private int getCurrIndex() {
		int i;
		for (i = 0; i < this.itemsInCategory.size(); i++) {
			if (this.itemsInCategory.get(i).equals(item)) {
				return i;
			}
		}
		return i;
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
//		Intent intent = new Intent(LearnItemActivity.this,
//				LearnSubMenuActivity.class);
//		intent.putExtra(Keys.CATEGORY, category);
//		startActivity(intent);
//		finish();
		
		Intent intent = new Intent(LearnItemActivity.this,
				MenuActivity.class);
		intent.putExtra(Keys.MENU, Constants.MENU_LEARN);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
