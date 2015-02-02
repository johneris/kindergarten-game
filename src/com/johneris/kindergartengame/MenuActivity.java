package com.johneris.kindergartengame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class MenuActivity extends Activity {

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	TextView tvTopTitle;

	Button btnWriteLetters;
	Button btnWriteNumbers;
	Button btnCountNumbers;
	Button btnColors;
	Button btnShapes;

	TableLayout tbl;

	String menu;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.menu_layout);

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
		menu = extras.getString(Keys.MENU);

		/* Initialize Views */

		tvTopTitle = (TextView) this.findViewById(R.id.global_textViewTopTitle);
		tvTopTitle.setText(menu);

		tbl = (TableLayout) this.findViewById(R.id.menu_tableLayout);

		Resources res = getResources();

		btnWriteLetters = new Button(getApplicationContext());
		btnWriteLetters
				.setText(res.getString(R.string.menu_buttonWriteLetters));
		btnWriteLetters.setWidth(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareWidth));
		btnWriteLetters.setHeight(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareHeight));
		btnWriteLetters.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnSubMenuActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_LETTER);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {

				} else if (menu.equals(Constants.MENU_SCORES)) {
					Intent intent = new Intent(MenuActivity.this,
							ScoresPreviewActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_LETTER);
					intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
					startActivity(intent);
					finish();
				}
			}
		});

		btnWriteNumbers = new Button(getApplicationContext());
		btnWriteNumbers
				.setText(res.getString(R.string.menu_buttonWriteNumbers));
		btnWriteNumbers.setWidth(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareWidth));
		btnWriteNumbers.setHeight(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareHeight));
		btnWriteNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnSubMenuActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_NUMBER);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {

				} else if (menu.equals(Constants.MENU_SCORES)) {
					Intent intent = new Intent(MenuActivity.this,
							ScoresPreviewActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_NUMBER);
					intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
					startActivity(intent);
					finish();
				}
			}
		});

		btnCountNumbers = new Button(getApplicationContext());
		btnCountNumbers
				.setText(res.getString(R.string.menu_buttonCountNumbers));
		btnCountNumbers.setWidth(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareWidth));
		btnCountNumbers.setHeight(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareHeight));
		btnCountNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnSubMenuActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_COUNT_NUMBERS);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {
					Intent intent = new Intent(MenuActivity.this,
							NumberGameActivity.class);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_SCORES)) {
					Intent intent = new Intent(MenuActivity.this,
							ScoresPreviewActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_COUNT_NUMBERS);
					intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
					startActivity(intent);
					finish();
				}
			}
		});

		btnColors = new Button(getApplicationContext());
		btnColors.setText(res.getString(R.string.menu_buttonColors));
		btnColors.setWidth(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareWidth));
		btnColors.setHeight(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareHeight));
		btnColors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnSubMenuActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {

				} else if (menu.equals(Constants.MENU_SCORES)) {
					Intent intent = new Intent(MenuActivity.this,
							ScoresPreviewActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
					intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
					startActivity(intent);
					finish();
				}
			}
		});

		btnShapes = new Button(getApplicationContext());
		btnShapes.setText(res.getString(R.string.menu_buttonShapes));
		btnShapes.setWidth(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareWidth));
		btnShapes.setHeight(res
				.getDimensionPixelSize(R.dimen.global_buttonSquareHeight));
		btnShapes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnSubMenuActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
					startActivity(intent);
					finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {

				} else if (menu.equals(Constants.MENU_SCORES)) {
					Intent intent = new Intent(MenuActivity.this,
							ScoresPreviewActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
					intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
					startActivity(intent);
					finish();
				}
			}
		});

		
		if (menu.equals(Constants.MENU_SCORES)) {
			btnWriteLetters
					.setEnabled(!Constants.currUserProfile.lstWriteLetterGameResult
							.isEmpty());
			btnWriteNumbers
					.setEnabled(!Constants.currUserProfile.lstWriteNumberGameResult
							.isEmpty());
			btnCountNumbers
					.setEnabled(!Constants.currUserProfile.lstCountNumberGameResult
							.isEmpty());
			btnColors
					.setEnabled(!Constants.currUserProfile.lstColorGameResult
							.isEmpty());
			btnShapes
					.setEnabled(!Constants.currUserProfile.lstShapeGameResult
							.isEmpty());
		}
		
		displayMenuButtons();
	}

	private void displayMenuButtons() {
		TableRow tblRow = new TableRow(getApplicationContext());
		tblRow.addView(btnWriteLetters);
		tblRow.addView(btnWriteNumbers);
		tblRow.addView(btnCountNumbers);
		tblRow.addView(btnColors);
		tblRow.addView(btnShapes);
		tblRow.setGravity(Gravity.CENTER);
		tbl.addView(tblRow);
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
		Intent intent = new Intent(MenuActivity.this, UserProfileActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
