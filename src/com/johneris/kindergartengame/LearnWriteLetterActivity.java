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

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class LearnWriteLetterActivity extends Activity {

	boolean continueMusic = true;

	TableLayout tbl;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.learn_choose_layout);

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

		/* Initialize views */

		tbl = (TableLayout) findViewById(R.id.learnChoose_tableLayout);

		loadTableOfLetters();
	}

	@SuppressLint("DefaultLocale")
	private void loadTableOfLetters() {

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
					Intent intent = new Intent(LearnWriteLetterActivity.this,
							PracticeWritingActivity.class);
					intent.putExtra(Keys.LEARN,
							Constants.LEARN_WRITE_LETTER_UPPERCASE);
					intent.putExtra(Keys.LEARN_ITEM, letter.toUpperCase());
					startActivity(intent);
					finish();
				}
			});

			Button btnLowercase = new Button(this.getApplicationContext());
			btnLowercase.setText(letter.toLowerCase());
			btnLowercase.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(LearnWriteLetterActivity.this,
							PracticeWritingActivity.class);
					intent.putExtra(Keys.LEARN,
							Constants.LEARN_WRITE_LETTER_LOWERCASE);
					intent.putExtra(Keys.LEARN_ITEM, letter.toLowerCase());
					startActivity(intent);
					finish();
				}
			});

			tblRow.addView(btnUppercase);
			tblRow.addView(btnLowercase);
			
			if ((((i + 1) % 5) == 0) || (i == (Constants.lstLetter.size() - 1))) {
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
		Intent intent = new Intent(LearnWriteLetterActivity.this,
				LearnWriteActivity.class);
		startActivity(intent);
		finish();

		super.onBackPressed();
	}

}
