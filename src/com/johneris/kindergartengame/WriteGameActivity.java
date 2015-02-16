package com.johneris.kindergartengame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.johneris.kindergartengame.DrawView.Point;
import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.GameResult;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;
import com.johneris.kindergartengame.common.Randomizer;
import com.johneris.kindergartengame.common.StoreUserProfiles;

public class WriteGameActivity extends Activity {

	public static final String TAG = "WriteGameActivity";

	/**
	 * boolean to continue playing music
	 */
	boolean continueMusic = true;

	ProgressBar progressBar;

	ImageView imageView;
	DrawView drawView;

	Button btnSubmit;
	Button btnClear;

	int iteration;
	int progressBarUpdateInterval;

	ArrayList<Integer> lstRandIndex;

	GameResult gameResult;
	double overallDuration;

	CountDownTimer countDownTimer;

	String category;
	ArrayList<String> lstItem;

	Bitmap bmpAnswer;
	Bitmap bmpCorrect;
	Bitmap bmpDiff;

	private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {
				Log.i(TAG, "OpenCV loaded successfully");
			}
				break;
			default: {
				super.onManagerConnected(status);
			}
				break;
			}
		}
	};

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {

		super.onCreate(state);

		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_10, this,
				mLoaderCallback);

		/* Create a full screen window */

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.write_game_layout);

		/* Background Image */

		// adapt the image to the size of the display
		Display display = getWindowManager().getDefaultDisplay();
		android.graphics.Point size = new android.graphics.Point();
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
		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			topTitle = "Write the letter";
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			topTitle = "Write the number";
		}
		((TextView) findViewById(R.id.global_textViewTopTitle))
				.setText(topTitle);

		progressBar = (ProgressBar) findViewById(R.id.globalGame_progressBar);

		imageView = (ImageView) findViewById(R.id.writeGame_imageView);
		drawView = (DrawView) findViewById(R.id.writeGame_drawView);

		btnSubmit = (Button) findViewById(R.id.writeGame_buttonSubmit);
		btnClear = (Button) findViewById(R.id.writeGame_buttonClear);

		btnSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				gameResult.lstIsCorrect.add(checkAnswer());

				double time = ((double) progressBar.getProgress()) / 1000;
				gameResult.lstItemDuration.add(time);

				overallDuration += time;
				iteration++;
				game();
			}
		});

		btnClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// clear canvas
				drawView.clear();
				drawView.init();
			}
		});

		/* Initialize game components */
		gameResult = new GameResult();

		iteration = 0;
		overallDuration = 0;

		progressBarUpdateInterval = Constants.MAX_TIME_PER_ITEM * 20;
		progressBar.setMax(Constants.MAX_TIME_PER_ITEM * 1000);

		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			lstItem = Constants.lstLetter;
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			lstItem = Constants.lstWritingNumber;
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

			if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
				Constants.currUserProfile.lstWriteLetterGameResult
						.add(gameResult);
			} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
				Constants.currUserProfile.lstWriteNumberGameResult
						.add(gameResult);
			}

			StoreUserProfiles.saveToFile(getApplicationContext());

			// end game show results
			Intent intent = new Intent(WriteGameActivity.this,
					ScoresPreviewActivity.class);

			if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE_LETTER);
			} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE_NUMBER);
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
					gameResult.lstIsCorrect.add(checkAnswer());

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
		// clear canvas
		drawView.clear();
		drawView.init();

		// get random index
		int randIndex = lstRandIndex.get(iteration);

		// get the correct answer
		String strAnswer = "";
		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			strAnswer = Constants.lstLetter.get(randIndex);
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			strAnswer = Constants.lstWritingNumber.get(randIndex);
		}

		// add to correct answers
		gameResult.lstItemName.add(strAnswer);

		// set imageView
		String image = "";
		String ansImg = "";
		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			image = Constants.WRITE_LETTER_DIR + strAnswer + ".PNG";
			ansImg = Constants.WRITE_LETTER_DIR + "correct/" + strAnswer
					+ ".PNG";
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			image = Constants.WRITE_NUMBER_DIR + strAnswer + ".PNG";
			ansImg = Constants.WRITE_NUMBER_DIR + "correct/" + strAnswer
					+ ".PNG";
		}
		final String fImage = image;
		// correct
		try {
			InputStream ims = getAssets().open(ansImg);
			Drawable d = Drawable.createFromStream(ims, null);
			imageView.setImageDrawable(d);
			ViewTreeObserver vto = imageView.getViewTreeObserver();
			vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				public boolean onPreDraw() {
					// Remove after the first run so it doesn't fire forever
					imageView.getViewTreeObserver().removeOnPreDrawListener(
							this);

					imageView.setDrawingCacheEnabled(true);
					imageView.buildDrawingCache(true);
					bmpCorrect = imageView.getDrawingCache().copy(
							Config.ARGB_8888, false);
					imageView.setDrawingCacheEnabled(false);

					// trace
					try {
						InputStream ims = getAssets().open(fImage);
						Drawable d = Drawable.createFromStream(ims, null);
						imageView.setImageDrawable(d);
					} catch (IOException e) {
						e.printStackTrace();
					}

					return true;
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkAnswer() {

		Mat matCorrect = new Mat();
		Utils.bitmapToMat(bmpCorrect, matCorrect);
		Imgproc.cvtColor(matCorrect, matCorrect, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(matCorrect, matCorrect, 128, 255,
				Imgproc.THRESH_BINARY);

		imageView.setDrawingCacheEnabled(true);
		imageView.buildDrawingCache(true);
		bmpAnswer = imageView.getDrawingCache().copy(Config.ARGB_8888, true);
		imageView.setDrawingCacheEnabled(false);
		Canvas canvas = new Canvas(bmpAnswer);
		for (Point point : drawView.points) {
			canvas.drawCircle(point.x, point.y, 5, drawView.paint);
		}

		Mat matAnswer = new Mat();
		Utils.bitmapToMat(bmpAnswer, matAnswer);
		Imgproc.cvtColor(matAnswer, matAnswer, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(matAnswer, matAnswer, 128, 255, Imgproc.THRESH_BINARY);

		Mat matDiff = new Mat();
		Core.absdiff(matAnswer, matCorrect, matDiff);

		// to emphasize difference
		Core.add(matDiff, new Scalar(0, 0, 0, 255), matDiff);

		// convert to bitmap
		bmpCorrect = Bitmap.createBitmap(matDiff.cols(), matDiff.rows(),
				Bitmap.Config.ARGB_8888);
		Utils.matToBitmap(matCorrect, bmpCorrect);

		// convert to bitmap
		bmpAnswer = Bitmap.createBitmap(matDiff.cols(), matDiff.rows(),
				Bitmap.Config.ARGB_8888);
		Utils.matToBitmap(matAnswer, bmpAnswer);

		// convert to bitmap
		bmpDiff = Bitmap.createBitmap(matDiff.cols(), matDiff.rows(),
				Bitmap.Config.ARGB_8888);
		Utils.matToBitmap(matDiff, bmpDiff);

		Scalar result = Core.sumElems(matDiff);
		double sum = result.val[0];
		double similarityPercentage = 100 - (((sum / (matDiff.width() * matDiff
				.height())) / 255) * 1000);

		String extr = Environment.getExternalStorageDirectory().toString();
		File mFolder = new File(extr + "/K");

		if (!mFolder.exists()) {
			mFolder.mkdir();
		}

		try {
			String s;
			File f;
			FileOutputStream fos = null;

			s = "" + iteration + "_ans.png";
			f = new File(mFolder.getAbsolutePath(), s);
			fos = new FileOutputStream(f);
			bmpAnswer.compress(Bitmap.CompressFormat.PNG, 70, fos);

			s = "" + iteration + "_correct.png";
			f = new File(mFolder.getAbsolutePath(), s);
			fos = new FileOutputStream(f);
			bmpCorrect.compress(Bitmap.CompressFormat.PNG, 70, fos);

			s = "" + iteration + "_diff.png";
			f = new File(mFolder.getAbsolutePath(), s);
			fos = new FileOutputStream(f);
			bmpDiff.compress(Bitmap.CompressFormat.PNG, 70, fos);

			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		double minSimilarity = 0;

		if (Constants.CATEGORY_WRITE_LETTER.equals(category)) {
			String currLetter = gameResult.lstItemName
					.get(gameResult.lstItemName.size() - 1);
			if ("IT".contains(currLetter)) {
				minSimilarity = 80;
			} else if ("LPYFZ".contains(currLetter)) {
				minSimilarity = 65;
			} else if ("SACXVRGKEJK".contains(currLetter)) {
				minSimilarity = 60;
			} else if ("BHGNDOQ".contains(currLetter)) {
				minSimilarity = 50;
			} else if ("MW".contains(currLetter)) {
				minSimilarity = 30;
			} else {
				minSimilarity = 60;
			}
		} else if (Constants.CATEGORY_WRITE_NUMBER.equals(category)) {
			String currNumber = gameResult.lstItemName
					.get(gameResult.lstItemName.size() - 1);
			if ("1".contains(currNumber)) {
				minSimilarity = 80;
			} else {
				minSimilarity = 60;
			}
		}

		boolean isCorrect = (similarityPercentage >= minSimilarity);

		Toast.makeText(getApplicationContext(),
				(isCorrect ? "Correct!" : "Sorry. That's Wrong."),
				// + " " + similarityPercentage,
				Toast.LENGTH_SHORT).show();

		return isCorrect;
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
