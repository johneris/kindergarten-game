package com.johneris.kindergartengame;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.GameResult;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;
import com.johneris.kindergartengame.common.Randomizer;
import com.johneris.kindergartengame.common.StoreUserProfiles;

public class ColorsShapesGameActivity extends Activity {

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	ProgressBar progressBar;

	ImageView imageView;

	Button[] buttonOption;

	int iteration;
	int progressBarUpdateInterval;

	ArrayList<Integer> lstRandIndex;

	GameResult gameResult;
	double overallDuration;

	CountDownTimer countDownTimer;

	String category;
	ArrayList<String> lstItem;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.colors_shapes_game_layout);

		/* Background Image */

		// adapt the image to the size of the display
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
				getResources(), R.drawable.background_game), size.x, size.y,
				true);

		// fill the background ImageView with the resized image
		ImageView iv_background = (ImageView) findViewById(R.id.global_imageViewBackground);
		iv_background.setImageBitmap(bmp);

		/* Get extras */

		Bundle extras = getIntent().getExtras();
		category = extras.getString(Keys.CATEGORY);

		/* Initialize views */

		String topTitle = "";
		if (Constants.CATEGORY_COLORS.equals(category)) {
			topTitle = "What color is this?";
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			topTitle = "What shape is this?";
		}
		((TextView) findViewById(R.id.global_textViewTopTitle))
				.setText(topTitle);

		progressBar = (ProgressBar) findViewById(R.id.globalGame_progressBar);

		imageView = (ImageView) findViewById(R.id.globalGame_imageView);

		buttonOption = new Button[3];

		buttonOption[0] = (Button) findViewById(R.id.globalGame_buttonOption1);
		buttonOption[1] = (Button) findViewById(R.id.globalGame_buttonOption2);
		buttonOption[2] = (Button) findViewById(R.id.globalGame_buttonOption3);

		/* Initialize game components */
		gameResult = new GameResult();

		iteration = 0;
		overallDuration = 0;

		progressBarUpdateInterval = 180;
		progressBar.setMax(Constants.MAX_TIME_PER_ITEM * 1000);

		if (Constants.CATEGORY_COLORS.equals(category)) {
			lstItem = Constants.lstColor;
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			lstItem = Constants.lstShape;
		}

		lstRandIndex = Randomizer.getRandomIndexes(lstItem.size(),
				Constants.ITEMS_PER_GAME);

		game();
	}

	private void game() {
		if (countDownTimer != null) {
			countDownTimer.cancel();
		}

		progressBar.setProgress(0);

		if (iteration == Constants.ITEMS_PER_GAME) {
			gameResult.generateScores();
			gameResult.overallDuration = overallDuration;
			gameResult.dateAndTimePlayed = DateFormat.getDateTimeInstance()
					.format(Calendar.getInstance().getTime());

			if (Constants.CATEGORY_COLORS.equals(category)) {
				Constants.currUserProfile.lstColorGameResult.add(gameResult);
			} else if (Constants.CATEGORY_SHAPES.equals(category)) {
				Constants.currUserProfile.lstShapeGameResult.add(gameResult);
			}

			StoreUserProfiles.saveToFile(getApplicationContext());

			// end game show results
			Intent intent = new Intent(ColorsShapesGameActivity.this,
					ScoresPreviewActivity.class);

			if (Constants.CATEGORY_COLORS.equals(category)) {
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
			} else if (Constants.CATEGORY_SHAPES.equals(category)) {
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
			}

			intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_GAMES);
			startActivity(intent);
			finish();

		} else {
			initGameItem();
			countDownTimer = new CountDownTimer(
			// 9 seconds
					Constants.MAX_TIME_PER_ITEM * 1000,
					// progressBarUpdateInterval interval
					(Constants.MAX_TIME_PER_ITEM * 1000)
							/ progressBarUpdateInterval) {

				@Override
				public void onFinish() {
					progressBar.setProgress(progressBar.getMax());
					gameResult.lstIsCorrect.add(false);

					double time = ((double) progressBar.getProgress()) / 1000;
					gameResult.lstItemDuration.add(time);

					overallDuration += time;
					iteration++;
					game();
				}

				@Override
				public void onTick(long millisUntilFinished) {
					progressBar
							.setProgress((int) (progressBar.getMax() - millisUntilFinished));
				}

			};
			countDownTimer.start();
		}
	}

	private void initGameItem() {

		// get random index
		int randIndex = lstRandIndex.get(iteration);

		// get the correct answer
		String strAnswer = "";
		if (Constants.CATEGORY_COLORS.equals(category)) {
			strAnswer = Constants.lstColor.get(randIndex);
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			strAnswer = Constants.lstShape.get(randIndex);
		}

		// add to correct answers
		gameResult.lstItemName.add(strAnswer);

		// set imageView
		String image = "";
		if (Constants.CATEGORY_COLORS.equals(category)) {
			image = Constants.COLOR_DIR + strAnswer + "/"
					// random 1-5
					+ ((Math.abs(new Random().nextInt()) % 5) + 1) 
					+ ".PNG";
		} else if (Constants.CATEGORY_SHAPES.equals(category)) {
			image = Constants.SHAPE_DIR + strAnswer + "/"
					// random 1-5
					+ ((Math.abs(new Random().nextInt()) % 5) + 1) 
					+ ".PNG";
		}
		try {
			InputStream ims = getAssets().open(image);
			Drawable d = Drawable.createFromStream(ims, null);
			imageView.setImageDrawable(d);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// get 3 random index for position 0-2 (button options)
		int randomPosition = Math.abs(new Random().nextInt()) % 3;
		ArrayList<String> lstOption = new ArrayList<>();

		// answer
		lstOption.add(strAnswer);
		buttonOption[randomPosition].setText(strAnswer);
		buttonOption[randomPosition].setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				gameResult.lstIsCorrect.add(true);

				double time = ((double) progressBar.getProgress()) / 1000;
				gameResult.lstItemDuration.add(time);

				overallDuration += time;
				iteration++;
				game();
			}
		});

		// init button options
		for (int i = 0; i < 3; i++) {
			if (i == randomPosition) {
			} else {
				String randForOption;
				while (lstOption.contains(randForOption = lstItem.get(Math
						.abs(new Random().nextInt()) % lstItem.size())))
					;
				lstOption.add(randForOption);
				buttonOption[i].setText("" + randForOption);
				buttonOption[i].setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						gameResult.lstIsCorrect.add(false);

						double time = ((double) progressBar.getProgress()) / 1000;
						gameResult.lstItemDuration.add(time);

						overallDuration += time;
						iteration++;
						game();
					}
				});
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
	}

}
