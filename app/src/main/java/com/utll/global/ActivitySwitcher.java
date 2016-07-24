package com.utll.global;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Handler;

import com.croma.app.foodApp.R;

/**
 * Created by suppi on 10/07/16.
 */
public class ActivitySwitcher {


    /**
     *
     * @param activity
     * @param activityClass
     */
    public static void switchActivity(Activity activity, Class activityClass){

        Intent intent = new Intent(activity,activityClass);

        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.slide_in_left,
//                R.anim.slide_in_right);
    }

    /**
     *
     * @param activity
     * @param activityClass
     * @param isFinish
     */
    public static void switchActivity(Activity activity, Class activityClass,boolean isFinish){

        Intent intent = new Intent(activity,activityClass);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
        if(isFinish){
            activity.finish();
        }
    }

    /**
     *
     * @param activity
     * @param activityClass
     */
    public static void switchActivityWithHandler(final Activity activity, final Class activityClass){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent intent = new Intent(activity,activityClass)
                       ;
               activity.startActivity(intent);
            }
        },500);

    }



}
