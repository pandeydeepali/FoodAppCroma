package com.croma.app.foodApp;

import android.app.Activity;

import com.rey.material.widget.SnackBar;

/**
 * Created by Shani Gupta on 1/2/16.
 */
public class MaterialSnackBar {

    // SnackBar
    public static SnackBar mSnackBar;

    public static SnackBar showNetworkSnackBar(Activity activity) {
        dissmissSnackBar();
        // Create Snack Bar
        mSnackBar = SnackBar.make(activity);
        mSnackBar.duration(0);
        mSnackBar.applyStyle(R.style.AppTheme);
        mSnackBar.text(activity.getResources().getString(R.string.internet));
        mSnackBar.actionText(activity.getResources().getString(R.string.refresh));
        mSnackBar.show(activity);

        return mSnackBar;
    }

    public static SnackBar showCustomSnackBar(Activity activity, String sText, String sActionText) {
        dissmissSnackBar();
        // Create Snack Bar
        mSnackBar = SnackBar.make(activity);
        mSnackBar.duration(0);
        mSnackBar.applyStyle(R.style.Material_App);
        mSnackBar.backgroundColor(R.color.colorAccent);
        mSnackBar.maxLines(1);
        mSnackBar.actionTextColor(R.color.splash_color);
        mSnackBar.textColor(R.color.splash_color);
        mSnackBar.text(sText);
        mSnackBar.actionText(sActionText);
        mSnackBar.show(activity);

        return mSnackBar;
    }

    public static void showMessageSnackBar(Activity activity, String sText) {
        dissmissSnackBar();
        // Create Snack Bar
        mSnackBar = SnackBar.make(activity);
        mSnackBar.duration(0);
        mSnackBar.applyStyle(R.style.AppTheme);
        mSnackBar.text(sText);
        mSnackBar.actionText(activity.getResources().getString(R.string.internet));
        mSnackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar sb, int actionId) {
                mSnackBar.dismiss();
            }
        });
        mSnackBar.show(activity);
    }

    public static void showMessageSnackBarForShortTime(Activity activity, String sText) {
        dissmissSnackBar();
        // Create Snack Bar
        mSnackBar = SnackBar.make(activity);
        mSnackBar.duration(1500L);
        mSnackBar.applyStyle(R.style.AppTheme);
        mSnackBar.text(sText);
        mSnackBar.actionText(activity.getResources().getString(R.string.internet));
        mSnackBar.actionClickListener(new SnackBar.OnActionClickListener() {
            @Override
            public void onActionClick(SnackBar sb, int actionId) {
                mSnackBar.dismiss();
            }
        });
        mSnackBar.show(activity);
    }

    // Dissmis Snack Bar
    public static void dissmissSnackBar() {
        if (mSnackBar != null) {
            mSnackBar.dismiss();
        }

    }
}




