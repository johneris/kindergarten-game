package com.johneris.kindergartengame;

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

public class NumberGameActivity extends Activity {

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	ProgressBar progressBar;

	TextView[] textViewN;

	Button[] buttonOption;

	int iteration;
	int progressBarUpdateInterval;

	ArrayList<Integer> lstRandIndex;

	GameResult gameResult;
	double overallDuration;

	CountDownTimer countDownTimer;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.number_game_layout);

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

		/* Initialize views */

		((TextView) findViewById(R.id.global_textViewTopTitle))
				.setText("Guess the missing number");

		progressBar = (ProgressBar) findViewById(R.id.globalGame_progressBar);

		textViewN = new TextView[3];

		textViewN[0] = (TextView) findViewById(R.id.numberGame_textViewN1);
		textViewN[1] = (TextView) findViewById(R.id.numberGame_textViewN2);
		textViewN[2] = (TextView) findViewById(R.id.numberGame_textViewN3);

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

		lstRandIndex = Randomizer.getRandomIndexes(
				Constants.lstCountingNumber.size(), Constants.ITEMS_PER_GAME);

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

			Constants.currUserProfile.lstCountNumberGameResult.add(gameResult);
			StoreUserProfiles.saveToFile(getApplicationContext());

			// end game show results
			Intent intent = new Intent(NumberGameActivity.this,
					ScoresPreviewActivity.class);
			intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COUNT_NUMBERS);
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
		String strNumber = Constants.lstCountingNumber.get(randIndex);

		// add to correct answers
		gameResult.lstItemName.add(strNumber);

		int number = Integer.parseInt(strNumber);

		// get 3 random index for position 0-2 (textViews)
		int randomPosition;

		if (number == 1) {
			// first position
			randomPosition = 0;
		} else if (number == 2) {
			// middle position
			randomPosition = 1;
		} else if (number == 9) {
			// middle position
			randomPosition = 1;
		} else if (number == 10) {
			// last position
			randomPosition = 2;
		} else {
			// random position
			randomPosition = Math.abs(new Random().nextInt()) % 3;
		}

		// initialize text Views
		for (int i = 0; i < 3; i++) {
			int n = (number - randomPosition) + i;
			if (n == number) {
				textViewN[i].setText("_");
			} else {
				textViewN[i].setText("" + n);
			}
		}

		// get 3 random index for position 0-2 (button options)
		randomPosition = Math.abs(new Random().nextInt()) % 3;
		ArrayList<String> lstOption = new ArrayList<>();

		// answer
		lstOption.add(strNumber);
		buttonOption[randomPosition].setText(strNumber);
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
				while (lstOption
						.contains(randForOption = Constants.lstCountingNumber
								.get(Math.abs(new Random().nextInt())
										% Constants.lstCountingNumber.size())))
					;
				lstOption.add(randForOption);
				buttonOption[i].setText(randForOption);
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
