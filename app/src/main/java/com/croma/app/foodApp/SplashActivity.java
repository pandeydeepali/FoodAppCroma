package com.croma.app.foodApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.os.Handler;

import com.utll.global.ActivitySwitcher;
import com.utll.global.CustomControl;
import com.utll.global.utilCommon;

/**
 * Created by suppi on 26/06/16.
 * This App is useful for order a food Via Mobile App according to our current location within particular radius
 * This App as well used a google Geolocation
 *
 */
public class SplashActivity extends AppCompatActivity{
    /**
     * Static No Instance means Globally Used
     */
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private static final String TAG = SplashActivity.class.getSimpleName();
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        relativeLayout = (RelativeLayout) findViewById(R.id.fullscreen_content_controls);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        makeActivityFullScreen();
        checkNetworkInDevice();

    }

    // make full screen activity
    private void makeActivityFullScreen() {
        relativeLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void checkNetworkInDevice() {
        try {
            if (utilCommon.isNetworkAvailable(SplashActivity.this)) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        checkUserExistenceOnLogin();
//
//                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//                        startActivity(i);
//                        finish();
//                        overridePendingTransition(R.anim.slide_in_left,
//                               R.anim.slide_out_right);
      }
                }, SPLASH_DISPLAY_LENGTH);
            } else {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CustomControl.alertDialogShow(SplashActivity.this, "Error", "NETWORK NOT AVAILABLE");
                    }
                }, 500);
            }
        } catch (Exception e) {

        }
    }


    private void checkUserExistenceOnLogin(){
        SharedPrefUtil.getString("loggedBoolean", "", SplashActivity.this);
        if(SharedPrefUtil.getString("loggedBoolean", "", SplashActivity.this)!=null && SharedPrefUtil.getString("loggedBoolean", "", SplashActivity.this)!="" && SharedPrefUtil.getString("loggedBoolean", "", SplashActivity.this)!="undefined"){
            ActivitySwitcher.switchActivity(SplashActivity.this, NavigationActivity.class);
            overridePendingTransition(R.anim.slide_in_left,
                               R.anim.slide_out_right);
            finish();
        }else{
            ActivitySwitcher.switchActivity(SplashActivity.this, LoginActivity.class);
            overridePendingTransition(R.anim.slide_in_left,
                              R.anim.slide_out_right);
            finish();

        }




    }




}

