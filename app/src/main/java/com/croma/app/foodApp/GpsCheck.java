package com.croma.app.foodApp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.utll.global.utilCommon;

/**
 * Created by suppi on 17/07/16.
 */
public class GpsCheck {
    Context context;
    LocationManager locationManager ;
    boolean GpsStatus ;
    boolean NetworkEnable;


    public boolean gpsStatus(final Context context){
        LocationManager locationManager=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        NetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(!GpsStatus){
            Log.e("off", "off");
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setIcon(R.drawable.error);
            dialog.setMessage(" GPS is Disable from your Location Settings");
            dialog.setTitle("Warning");
          //  dialog.setMessage(context.getResources().getString(R.string.gps_network_not_enabled));
            dialog.setPositiveButton(context.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    context.startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton(context.getString(R.string.cancel), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();


            return false;
        }


       else{
            Log.e("on", "message");
            return true;

        }

    }

}
