package com.johneris.kindergartengame;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
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

public class LearnSubMenuActivity extends Activity {

	boolean continueMusic = true;

	TextView tvTopTitle;
	TableLayout tbl;

	String category;

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
		category = extras.getString(Keys.CATEGORY);

		/* Initialize Views */

		tvTopTitle = (TextView) this.findViewById(R.id.global_textViewTopTitle);
		tvTopTitle.setText(category);

		tbl = (TableLayout) this.findViewById(R.id.menu_tableLayout);

		loadMenuButtons();
	}

	private void loadMenuButtons() {
		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			loadWriteLetterOptions();
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			loadWriteNumberOptions();
		} else if (Constants.CATEGORY_COUNT_NUMBERS.equals(category)) {
			loadCountNumberOptions();
		} else if (Constants.CATEGORY_COLORS.equals(category)) {
			loadColorOptions();
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			loadShapeOptions();
		}
	}
	
	@SuppressLint("DefaultLocale")
	private void loadWriteLetterOptions() {
		ArrayList<TableRow> tblRows = new ArrayList<>();
		tblRows.add(new TableRow(getApplicationContext()));
		
		TableRow tblRow = tblRows.get(0);

		for (int i = 0; i < Constants.lstLetter.size(); i++) {
			final String letter = Constants.lstLetter.get(i);

			Button btnUppercase = new Button(getApplicationContext());
			btnUppercase.setText(letter.toUpperCase());
			btnUppercase.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, letter.toUpperCase());
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE_LETTER);
					startActivity(intent);
					finish();
				}
			});

			Button btnLowercase = new Button(this.getApplicationContext());
			btnLowercase.setText(letter.toLowerCase());
			btnLowercase.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, letter.toLowerCase());
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE_LETTER);
					startActivity(intent);
					finish();
				}
			});

			tblRow.addView(btnUppercase);
			tblRow.addView(btnLowercase);
			
			if ((((i + 1) % 3) == 0) || (i == (Constants.lstLetter.size() - 1))) {
				tblRow.setGravity(Gravity.CENTER);
				tbl.addView(tblRow);
				tblRows.add(new TableRow(getApplicationContext()));
				tblRow = tblRows.get(tblRows.size()-1);
			}
			
		}
	}
	
	private void loadWriteNumberOptions() {
		ArrayList<TableRow> tblRows = new ArrayList<>();
		tblRows.add(new TableRow(getApplicationContext()));
		
		TableRow tblRow = tblRows.get(0);
		
		for (int i = 0; i < Constants.lstWritingNumber.size(); i++) {
			final String number = Constants.lstWritingNumber.get(i);

			Button btnNumber = new Button(getApplicationContext());
			btnNumber.setText(number);
			btnNumber.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, number);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE_NUMBER);
					startActivity(intent);
					finish();
				}
			});

			tblRow.addView(btnNumber);
			
			if ((((i + 1) % 5) == 0) || (i == (Constants.lstWritingNumber.size() - 1))) {
				tblRow.setGravity(Gravity.CENTER);
				tbl.addView(tblRow);
				tblRows.add(new TableRow(getApplicationContext()));
				tblRow = tblRows.get(tblRows.size()-1);
			}
		}
	}
	
	
	
	private void loadCountNumberOptions() {

		ArrayList<String> countingNumbers = Constants.lstCountingNumber;
		
		ArrayList<TableRow> tblRows = new ArrayList<>();
		tblRows.add(new TableRow(getApplicationContext()));

		TableRow tblRow = tblRows.get(0);

		for (int i = 0; i < countingNumbers.size(); i++) {
			final String number = countingNumbers.get(i);

			Button btnNumber = new Button(getApplicationContext());
			btnNumber.setText(number);
			btnNumber.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, number);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COUNT_NUMBERS);
					startActivity(intent);
					finish();
				}
			});

			tblRow.addView(btnNumber);

			if ((((i + 1) % 5) == 0) || (i == (countingNumbers.size() - 1))) {
				tblRow.setGravity(Gravity.CENTER);
				tbl.addView(tblRow);
				tblRows.add(new TableRow(getApplicationContext()));
				tblRow = tblRows.get(tblRows.size() - 1);
			}
		}
	}
	
	
	
	private void loadShapeOptions() {
		
		ArrayList<TableRow> tblRows = new ArrayList<>();
		tblRows.add(new TableRow(getApplicationContext()));
		
		TableRow tblRow = tblRows.get(0);

		for (int i = 0; i < Constants.lstShape.size(); i++) {
			final String shape = Constants.lstShape.get(i);

			Button btnShape = new Button(getApplicationContext());
			btnShape.setText(shape);
			btnShape.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, shape);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
					startActivity(intent);
					finish();
				}
			});
			
			tblRow.addView(btnShape);
			
			if ((((i + 1) % 3) == 0) || (i == (Constants.lstShape.size() - 1))) {
				tblRow.setGravity(Gravity.CENTER);
				tbl.addView(tblRow);
				tblRows.add(new TableRow(getApplicationContext()));
				tblRow = tblRows.get(tblRows.size()-1);
			}
			
		}
		
	}
	
	
	
	private void loadColorOptions() {
		
		ArrayList<TableRow> tblRows = new ArrayList<>();
		tblRows.add(new TableRow(getApplicationContext()));
		
		TableRow tblRow = tblRows.get(0);

		for (int i = 0; i < Constants.lstColor.size(); i++) {
			final String color = Constants.lstColor.get(i);

			Button btnColor = new Button(getApplicationContext());
			btnColor.setText(color);
			btnColor.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnSubMenuActivity.this,
							LearnItemActivity.class);
					intent.putExtra(Keys.LEARN_ITEM, color);
					intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
					startActivity(intent);
					finish();
				}
			});
			
			tblRow.addView(btnColor);
			
			if ((((i + 1) % 3) == 0) || (i == (Constants.lstColor.size() - 1))) {
				tblRow.setGravity(Gravity.CENTER);
				tbl.addView(tblRow);
				tblRows.add(new TableRow(getApplicationContext()));
				tblRow = tblRows.get(tblRows.size()-1);
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
		Intent intent = new Intent(LearnSubMenuActivity.this,
				MenuActivity.class);
		intent.putExtra(Keys.MENU, Constants.MENU_LEARN);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
