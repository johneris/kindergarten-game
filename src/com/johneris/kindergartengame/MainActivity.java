package com.johneris.kindergartengame;

import java.util.ArrayList;

import com.johneris.kindergartengame.common.Constants;
import com.johneris.kindergartengame.common.StoreUserProfiles;
import com.johneris.kindergartengame.common.UserProfile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle state) {
		
		super.onCreate(state);
		
		/* Create a full screen window */
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		this.setContentView(R.layout.main_layout);
		
		
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
        
        
        // load user profiles
    	ArrayList<UserProfile> lstUserProfile 
    			= StoreUserProfiles.readFromFile(getApplicationContext());
    	if(lstUserProfile == null) {
    		Constants.lstUserProfile = new ArrayList<UserProfile>();
    	} else {
    		Constants.lstUserProfile = lstUserProfile;
    	}
    	
    	// start LoginActivity and finish MainActivity
    	Intent intent = new Intent(MainActivity.this, LoginActivity.class);
    	startActivity(intent);
    	finish();
        
        
	}
	
}
