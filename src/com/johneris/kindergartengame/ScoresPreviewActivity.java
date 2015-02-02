package com.johneris.kindergartengame;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
import android.widget.RatingBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.GameResult;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class ScoresPreviewActivity extends Activity {

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	TextView tvGameCategory;

	TextView tvTimesPlayed;

	TableLayout tableLayout;

	TextView tvDateAndTime;

	TextView tvTotalScore;

	Button btnPrevious;

	Button btnNext;

	String category;

	ArrayList<GameResult> lstGameResult;

	int currIndex;

	String fromActivity;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.scores_preview_layout);

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
		if (extras == null) {
		} else {
			if (extras.containsKey(Keys.CATEGORY)) {
				// get category
				category = extras.getString(Keys.CATEGORY);
				if (category.equals(Constants.CATEGORY_WRITE_LETTER)) {
					lstGameResult = Constants.currUserProfile.lstWriteLetterGameResult;
				} else if (category.equals(Constants.CATEGORY_WRITE_NUMBER)) {
					lstGameResult = Constants.currUserProfile.lstWriteNumberGameResult;
				} else if (category.equals(Constants.CATEGORY_COUNT_NUMBERS)) {
					lstGameResult = Constants.currUserProfile.lstCountNumberGameResult;
				} else if (category.equals(Constants.CATEGORY_COLORS)) {
					lstGameResult = Constants.currUserProfile.lstColorGameResult;
				} else if (category.equals(Constants.CATEGORY_SHAPES)) {
					lstGameResult = Constants.currUserProfile.lstShapeGameResult;
				}
			}

			if (extras.containsKey(Keys.ACTIVITY)) {
				fromActivity = extras.getString(Keys.ACTIVITY);
			}
		}

		/* Initialize Views */

		tvGameCategory = (TextView) findViewById(R.id.scoresPreview_textViewGameCategory);
		tvGameCategory.setText(category);

		tvTimesPlayed = (TextView) findViewById(R.id.scoresPreview_textViewTimesPlayed);
		tvTimesPlayed.setText(" (" + lstGameResult.size() + " times played)");

		tableLayout = (TableLayout) findViewById(R.id.scoresPreview_tableLayout);

		tvDateAndTime = (TextView) findViewById(R.id.scoresPreview_textViewDateAndTimeVal);

		tvTotalScore = (TextView) findViewById(R.id.scoresPreview_textViewTotalScoreVal);

		btnPrevious = (Button) findViewById(R.id.scoresPreview_buttonPrevious);
		btnPrevious.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				currIndex--;
				if (currIndex < 0) {
					currIndex = 0;
				} else {
					loadTable();
				}
				manageEnableOfButtons();
			}
		});

		btnNext = (Button) findViewById(R.id.scoresPreview_buttonNext);
		btnNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				currIndex++;
				if (currIndex >= lstGameResult.size()) {
					currIndex = lstGameResult.size() - 1;
				} else {
					loadTable();
				}
				manageEnableOfButtons();
			}
		});

		// set curr index to last played
		currIndex = lstGameResult.size() - 1;

		manageEnableOfButtons();
		loadTable();
	}

	/**
	 * manageButtons
	 */
	private void manageEnableOfButtons() {
		// in last index
		if (currIndex == lstGameResult.size() - 1) {
			btnNext.setEnabled(false);
		} else {
			btnNext.setEnabled(true);
		}
		// in 0 index
		if (currIndex == 0) {
			btnPrevious.setEnabled(false);
		} else {
			btnPrevious.setEnabled(true);
		}
	}

	private void loadTable() {
		tableLayout.removeAllViews();

		GameResult result = lstGameResult.get(currIndex);

		// fill table with results
		for (int i = 0; i < result.lstItemName.size(); i++) {

			// item name
			TextView textViewItem = new TextView(getApplicationContext());
			textViewItem.setText(result.lstItemName.get(i));
			textViewItem.setTextColor(Color.BLACK);

			// score
			RatingBar ratingBarScore = new RatingBar(getApplicationContext());
			ratingBarScore.setEnabled(false);
			ratingBarScore.setNumStars(3);
			ratingBarScore.setRating(result.lstScore.get(i));

			// item name
			TextView textViewItemDuration = new TextView(
					getApplicationContext());
			textViewItemDuration.setText("(" + result.lstItemDuration.get(i)
					+ "sec/s)");
			textViewItemDuration.setTextColor(Color.BLACK);

			// create a table row
			TableRow tableRow = new TableRow(getApplicationContext());

			tableRow.addView(textViewItem);
			tableRow.addView(ratingBarScore);
			tableRow.addView(textViewItemDuration);
			tableRow.setGravity(Gravity.CENTER);

			// add the row to table
			tableLayout.addView(tableRow);
		}
		tvDateAndTime.setText("" + result.dateAndTimePlayed);
		tvTotalScore.setText("" + totalScore() + " (" + result.overallDuration
				+ "secs)");
	}

	/**
	 * return the total score
	 */
	public int totalScore() {
		int total = 0;
		for (int s : this.lstGameResult.get(currIndex).lstScore) {
			total += s;
		}
		return total;
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
		if (Constants.ACTIVITY_GAMES.equals(fromActivity)) {
			Intent intent = new Intent(ScoresPreviewActivity.this,
					MenuActivity.class);
			intent.putExtra(Keys.MENU, Constants.MENU_PLAY);
			startActivity(intent);
			finish();
		} else {
			Intent intent = new Intent(ScoresPreviewActivity.this,
					MenuActivity.class);
			intent.putExtra(Keys.MENU, Constants.MENU_SCORES);
			startActivity(intent);
			finish();
		}

		super.onBackPressed();
	}

}
