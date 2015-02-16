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
	TableRow tblRow;

	String menu;

	@SuppressLint({ "NewApi", "DefaultLocale" })
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

		tvTopTitle = (TextView) this
				.findViewById(R.id.globalGame_textViewTopTitle);
		tvTopTitle.setText(menu);

		tbl = (TableLayout) this.findViewById(R.id.menu_tableLayout);
		tblRow = new TableRow(getApplicationContext());

		Resources res = getResources();

		btnWriteLetters = (Button) getLayoutInflater().inflate(
				R.layout.menu_button, tblRow, false);
		btnWriteLetters
				.setText(res.getString(R.string.menu_buttonWriteLetters));
		btnWriteLetters.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, Constants.lstLetter.get(0)
							.toUpperCase());
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_LETTER);
					startActivity(intent);
					finish();
					// Intent intent = new Intent(MenuActivity.this,
					// LearnSubMenuActivity.class);
					// intent.putExtra(Keys.CATEGORY,
					// Constants.CATEGORY_WRITE_LETTER);
					// startActivity(intent);
					// finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {
					Intent intent = new Intent(MenuActivity.this,
							WriteGameActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_LETTER);
					startActivity(intent);
					finish();
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

		btnWriteNumbers = (Button) getLayoutInflater().inflate(
				R.layout.menu_button, tblRow, false);
		btnWriteNumbers
				.setText(res.getString(R.string.menu_buttonWriteNumbers));
		btnWriteNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM,
							Constants.lstWritingNumber.get(0));
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_NUMBER);
					startActivity(intent);
					finish();
					// Intent intent = new Intent(MenuActivity.this,
					// LearnSubMenuActivity.class);
					// intent.putExtra(Keys.CATEGORY,
					// Constants.CATEGORY_WRITE_NUMBER);
					// startActivity(intent);
					// finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {
					Intent intent = new Intent(MenuActivity.this,
							WriteGameActivity.class);
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_WRITE_NUMBER);
					startActivity(intent);
					finish();
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

		btnCountNumbers = (Button) getLayoutInflater().inflate(
				R.layout.menu_button, tblRow, false);
		btnCountNumbers
				.setText(res.getString(R.string.menu_buttonCountNumbers));
		btnCountNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM,
							Constants.lstCountingNumber.get(0));
					intent.putExtra(Keys.CATEGORY,
							Constants.CATEGORY_COUNT_NUMBERS);
					startActivity(intent);
					finish();
					// Intent intent = new Intent(MenuActivity.this,
					// LearnSubMenuActivity.class);
					// intent.putExtra(Keys.CATEGORY,
					// Constants.CATEGORY_COUNT_NUMBERS);
					// startActivity(intent);
					// finish();
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

		btnColors = (Button) getLayoutInflater().inflate(R.layout.menu_button,
				tblRow, false);
		btnColors.setText(res.getString(R.string.menu_buttonColors));
		btnColors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, Constants.lstColor.get(0));
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
					startActivity(intent);
					finish();
					// Intent intent = new Intent(MenuActivity.this,
					// LearnSubMenuActivity.class);
					// intent.putExtra(Keys.CATEGORY,
					// Constants.CATEGORY_COLORS);
					// startActivity(intent);
					// finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {
					Intent intent = new Intent(MenuActivity.this,
							ColorsShapesGameActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
					startActivity(intent);
					finish();
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

		btnShapes = (Button) getLayoutInflater().inflate(R.layout.menu_button,
				tblRow, false);
		btnShapes.setText(res.getString(R.string.menu_buttonShapes));
		btnShapes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (menu.equals(Constants.MENU_LEARN)) {
					Intent intent = new Intent(MenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, Constants.lstShape.get(0));
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
					startActivity(intent);
					finish();
					// Intent intent = new Intent(MenuActivity.this,
					// LearnSubMenuActivity.class);
					// intent.putExtra(Keys.CATEGORY,
					// Constants.CATEGORY_SHAPES);
					// startActivity(intent);
					// finish();
				} else if (menu.equals(Constants.MENU_PLAY)) {
					Intent intent = new Intent(MenuActivity.this,
							ColorsShapesGameActivity.class);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
					startActivity(intent);
					finish();
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

		displayMenuButtons();

		if (menu.equals(Constants.MENU_SCORES)) {
			btnWriteLetters
					.setVisibility(!Constants.currUserProfile.lstWriteLetterGameResult
							.isEmpty() ? View.VISIBLE : View.GONE);
			btnWriteNumbers
					.setVisibility(!Constants.currUserProfile.lstWriteNumberGameResult
							.isEmpty() ? View.VISIBLE : View.GONE);
			btnCountNumbers
					.setVisibility(!Constants.currUserProfile.lstCountNumberGameResult
							.isEmpty() ? View.VISIBLE : View.GONE);
			btnColors
					.setVisibility(!Constants.currUserProfile.lstColorGameResult
							.isEmpty() ? View.VISIBLE : View.GONE);
			btnShapes
					.setVisibility(!Constants.currUserProfile.lstShapeGameResult
							.isEmpty() ? View.VISIBLE : View.GONE);
		}

	}

	private void displayMenuButtons() {
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
