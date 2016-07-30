package com.utll.global;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.croma.app.foodApp.R;
import com.croma.app.foodApp.UtilGlobalMethod;
import com.rey.material.widget.Button;

/**
 * Created by suppi on 29/06/16.
 */
public class CustomControl {
    private static final String TAG = CustomControl.class.getName();
    /***
     * Global Alert Dialog
     * @param context
     * @param title
     * @param message
     */
    public static void alertDialogShow(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setIcon(R.drawable.error);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        // Setting OK Button
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                       // UtilGlobalMethod.exitApplication();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    public static void successAlert(Context context, String title, String message) {
        final AlertDialog successDialog = new AlertDialog.Builder(context).create();
        successDialog.setTitle(title);
        successDialog.setMessage(message);
        successDialog.setIcon(R.drawable.success);
        successDialog.setCanceledOnTouchOutside(true);
        successDialog.setCancelable(true);
        // Setting OK Button
        successDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        successDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        successDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               successDialog.dismiss();
            }
        }, 1000);


    }

}
