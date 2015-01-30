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
import android.widget.TextView;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.MusicManager;

public class UserProfileActivity extends Activity {

	boolean continueMusic = true;
	
	ImageView ivAvatar;
	TextView tvUserName;
	
	Button btnLearn;
	Button btnPlay;
	Button btnScores;
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {
		
		super.onCreate(state);
		
		/* Create a full screen window */
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.user_profile_layout);
		
		
		/* Background Image */
        
        // adapt the image to the size of the display
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
          getResources(),R.drawable.background_menu),size.x,size.y,true);
        
        // fill the background ImageView with the resized image
        ImageView iv_background = (ImageView) findViewById(R.id.ivBackground);
        iv_background.setImageBitmap(bmp);
        
        
        /* Initialize Views */
        
        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);
        ivAvatar.setImageResource(Constants.currUserProfile.avatar.getImageResource());
        
    	tvUserName = (TextView) findViewById(R.id.tvUserName);
    	tvUserName.setText(Constants.currUserProfile.userName);
    	
    	btnLearn = (Button) findViewById(R.id.btnLearn);
    	btnLearn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(UserProfileActivity.this, LearnActivity.class);
		    	startActivity(intent);
		    	finish();
			}
    	});
    	
    	btnPlay = (Button) findViewById(R.id.btnPlay);
    	btnPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(UserProfileActivity.this, PlayActivity.class);
		    	startActivity(intent);
		    	finish();
			}
    	});
    	
    	btnScores = (Button) findViewById(R.id.btnScores);
    	btnScores.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(UserProfileActivity.this, ScoresActivity.class);
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
    	Intent intent = new Intent(UserProfileActivity.this, LoginActivity.class);
    	startActivity(intent);
    	finish();
		
		super.onBackPressed();
	}
	
}
