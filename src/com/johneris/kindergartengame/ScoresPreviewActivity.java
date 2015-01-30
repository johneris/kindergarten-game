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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
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

	TextView textViewGameCategory;

	TextView textViewTimesPlayed;

	TableLayout tableLayout;

	TextView textViewDateAndTime;

	TextView textViewTotalScoreVal;

	Button buttonPrevious;

	Button buttonNext;

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
		ImageView iv_background = (ImageView) findViewById(R.id.scorespreview_imageViewBackground);
		iv_background.setImageBitmap(bmp);

		/* Get extras */

		Bundle extras = getIntent().getExtras();
		if (extras == null) {
		} else {
			if (extras.containsKey(Keys.CATEGORY)) {
				// get category
				category = extras.getString(Keys.CATEGORY);
				if (category.equals(Constants.CATEGORY_WRITE)) {
					lstGameResult = Constants.currUserProfile.lstWriteGameResult;
				} else if (category.equals(Constants.CATEGORY_NUMBERS)) {
					lstGameResult = Constants.currUserProfile.lstNumberGameResult;
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

		textViewGameCategory = (TextView) findViewById(R.id.scorespreview_textViewGameCategory);
		textViewGameCategory.setText(category);

		textViewTimesPlayed = (TextView) findViewById(R.id.scorespreview_textViewTimesPlayed);
		textViewTimesPlayed.setText(" (" + lstGameResult.size()
				+ " times played)");

		tableLayout = (TableLayout) findViewById(R.id.scorespreview_tableLayout);

		textViewDateAndTime = (TextView) findViewById(R.id.scorespreview_textViewDateAndTimeVal);

		textViewTotalScoreVal = (TextView) findViewById(R.id.scorespreview_textViewTotalScoreVal);

		buttonPrevious = (Button) findViewById(R.id.scorespreview_buttonPrevious);
		buttonPrevious.setOnClickListener(new OnClickListener() {
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

		buttonNext = (Button) findViewById(R.id.scorespreview_buttonNext);
		buttonNext.setOnClickListener(new OnClickListener() {
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
			buttonNext.setEnabled(false);
		} else {
			buttonNext.setEnabled(true);
		}
		// in 0 index
		if (currIndex == 0) {
			buttonPrevious.setEnabled(false);
		} else {
			buttonPrevious.setEnabled(true);
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
			TextView textViewItemDuration = new TextView(getApplicationContext());
			textViewItemDuration.setText("(" + result.lstItemDuration.get(i) + "sec/s)");
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
		textViewDateAndTime.setText("" + result.dateAndTimePlayed);
		textViewTotalScoreVal.setText("" + totalScore() + " ("
				+ result.overallDuration + "secs)");
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
			// start Play activity and finish ScoresPreview
			Intent intent = new Intent(ScoresPreviewActivity.this,
					PlayActivity.class);
			startActivity(intent);
			finish();
		} else {
			// start Scores activity and finish ScoresPreview
			Intent intent = new Intent(ScoresPreviewActivity.this,
					ScoresActivity.class);
			startActivity(intent);
			finish();
		}

		super.onBackPressed();
	}

}
