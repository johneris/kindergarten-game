package com.johneris.kindergartengame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.Keys;
import com.johneris.kindergartengame.common.MusicManager;

public class ScoresActivity extends Activity {
	
	/**
	 * boolean to continue playing music 
	 */
	boolean continueMusic = true;
	
	Button buttonWrite;
	
	Button buttonNumbers;
	
	Button buttonColors;
	
	Button buttonShapes;
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {
		
		super.onCreate(state);
		
		
		/* Create a full screen window */
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.scores_layout);
		
		
		/* Background Image */
        
        // adapt the image to the size of the display
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
          getResources(),R.drawable.background_game),size.x,size.y,true);
        
        // fill the background ImageView with the resized image
        ImageView iv_background = (ImageView) findViewById(R.id.scores_imageViewBackground);
        iv_background.setImageBitmap(bmp);
        
        
        /* Initialize Views */
        
        buttonWrite = (Button) findViewById(R.id.scores_buttonWrite);
        buttonWrite.setEnabled(!Constants.currUserProfile.lstWriteGameResult.isEmpty());
        buttonWrite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoresActivity.this, ScoresPreviewActivity.class);
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_WRITE);
				intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
				startActivity(intent);
				finish();
			}
        });
    	
    	buttonNumbers = (Button) findViewById(R.id.scores_buttonNumbers);
    	buttonNumbers.setEnabled(!Constants.currUserProfile.lstNumberGameResult.isEmpty());
    	buttonNumbers.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoresActivity.this, ScoresPreviewActivity.class);
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_NUMBERS);
				intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
				startActivity(intent);
				finish();
			}
        });
    	
    	buttonColors = (Button) findViewById(R.id.scores_buttonColors);
    	buttonColors.setEnabled(!Constants.currUserProfile.lstColorGameResult.isEmpty());
    	buttonColors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoresActivity.this, ScoresPreviewActivity.class);
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_COLORS);
				intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
				startActivity(intent);
				finish();
			}
        });
    	
    	buttonShapes = (Button) findViewById(R.id.scores_buttonShapes);
    	buttonShapes.setEnabled(!Constants.currUserProfile.lstShapeGameResult.isEmpty());
    	buttonShapes.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(ScoresActivity.this, ScoresPreviewActivity.class);
				intent.putExtra(Keys.CATEGORY, Constants.CATEGORY_SHAPES);
				intent.putExtra(Keys.ACTIVITY, Constants.ACTIVITY_SCORES);
				startActivity(intent);
				finish();
			}
        });
        
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
		// start UserProfile activity and finish ScoresActivity
		Intent intent = new Intent(ScoresActivity.this, UserProfileActivity.class);
		startActivity(intent);
		finish();
		
		super.onBackPressed();
	}
	
}
