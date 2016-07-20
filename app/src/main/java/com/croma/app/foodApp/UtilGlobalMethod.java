package com.croma.app.foodApp;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by suppi on 03/07/16.
 */
public class UtilGlobalMethod {
    public static void exitApplication(){
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}
