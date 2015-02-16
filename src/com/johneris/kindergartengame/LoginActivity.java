package com.johneris.kindergartengame;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.EAvatar;
import com.johneris.kindergartengame.common.MusicManager;

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

public class LoginActivity extends Activity {

	boolean continueMusic = true;
	
	Button btnNewUser;
	Button btnExistingUser;
	
	
	
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {
		
		super.onCreate(state);
		
		/* Create a full screen window */
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.login_layout);
		
		
		/* Background Image */
        
        // adapt the image to the size of the display
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
          getResources(),R.drawable.background_menu),size.x,size.y,true);
        
        // fill the background ImageView with the resized image
        ImageView iv_background = (ImageView) findViewById(R.id.global_imageViewBackground);
        iv_background.setImageBitmap(bmp);
        
        
        /*Initialize Views*/
        
        btnNewUser = (Button) findViewById(R.id.login_buttonNewUser);
        btnNewUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// start NewUserActivity and finish LoginActivity
		    	Intent intent = new Intent(LoginActivity.this, NewUserActivity.class);
		    	startActivity(intent);
		    	finish();
			}
        });
        
        btnExistingUser = (Button) findViewById(R.id.login_buttonExistingUser);
        btnExistingUser.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// start ExistingUserActivity and finish LoginActivity
		    	Intent intent = new Intent(LoginActivity.this, ExistingUserActivity.class);
		    	startActivity(intent);
		    	finish();
			}
        });
        
        
        // all avatar has been taken
        if(Constants.lstUserProfile.size() == EAvatar.values().length) {
        	btnNewUser.setVisibility(View.GONE);
        } else {
        	btnNewUser.setVisibility(View.VISIBLE);
        }
        
        // has existing user profile
        if(!Constants.lstUserProfile.isEmpty()) {
        	btnExistingUser.setVisibility(View.VISIBLE);
        } else {
        	btnExistingUser.setVisibility(View.GONE);
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
	
}
