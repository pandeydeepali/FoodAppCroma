package com.croma.app.foodApp;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.utll.global.ActivitySwitcher;
import com.utll.global.CustomControl;
import com.utll.global.Validation;

/**
 * Created by suppi on 29/06/16.
 */
public class SignUpActivity extends AppCompatActivity implements GlobalInterFace{
     //----SignUp View Control
    private Button RegButton;
    private ImageView regBack;
    private EditText regName, regEmail, regPass, regcPass, regPhone, regAddress;
    private ProgressDialog progressDialog = null;
    String registrationAddress;
    private DatabaseHandler databaseHandler;

    //-------------------------//
    private static final String TAG = SignUpActivity.class.getSimpleName();
    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private DatabaseHandler dbHandler = new DatabaseHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ...............................");
        //show error dialog if GoolglePlayServices not available
        setContentView(R.layout.activity_registration);
        findViewById();
        setOnClickListener();


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                registrationAddress= null;
            } else {
                registrationAddress= extras.getString("my_address");
                regAddress.setText(registrationAddress);
                regAddress.setEnabled(false);
              }
        }
    }

    @Override
    public void findViewById() {
        regBack = (ImageView) findViewById(R.id.regBack);
        regName = (EditText) findViewById(R.id.regName);
        regEmail = (EditText) findViewById(R.id.regEmail);
        regPass = (EditText) findViewById(R.id.regPass);
        regcPass = (EditText) findViewById(R.id.regcPass);
        regPhone = (EditText) findViewById(R.id.regPhone);
        regAddress = (EditText) findViewById(R.id.regAddress);
        RegButton = (Button) findViewById(R.id.RegButton);
     }

    @Override
    public void setOnClickListener() {
        regBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.switchActivity(SignUpActivity.this, LoginActivity.class);

            }
        });
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Validation.hasText(regName) && !Validation.hasText(regEmail) && !Validation.hasText(regPass) &&
                        !Validation.hasText(regcPass) && !Validation.hasText(regPhone) && !Validation.hasText(regAddress)) {
                } else if (!Validation.isValidEmailAddress(regEmail, Validation.EMAIL_REGEX, "InValid Email Address", true)) {

                } else if (!Validation.isValid(regPass, Validation.PASSWORD_REGEX, "Password Should be alphanumeric with specialCharacter", true)) {

                } else if (!Validation.isValid(regcPass, Validation.PASSWORD_REGEX, "Password Should be alphanumeric with specialCharacter", true)) {

                } else if (!regPass.getText().toString().trim().equals(regcPass.getText().toString().trim())) {
                    regPass.setError("Password and Confirm Password Should be matched");
                    regcPass.setError(null);
                } else if (regPhone.getText().toString().trim().length() != 10) {
                    regPhone.setError(" 10 characters required");
                } else if (!Validation.isPhoneNumber(regPhone, false)) {


                } else {
                    //UtilGlobalMethod.removeFocusOnRegControl();
                    progressDialog = ProgressDialog.show(
                            SignUpActivity.this, "PLEASE WAIT.",
                            "REGISTERING...", true);
                    regAddress.setError(null);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                insertDataintoSharedPreference();
                               // Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                CustomControl.successAlert(SignUpActivity.this, "Success", "Thank You For Registration, Record Inserted Successfully");
                                regName.setText("");
                                regEmail.setText("");
                                regPass.setText("");
                                regcPass.setText("");
                                regPhone.setText("");
                                ActivitySwitcher.switchActivity(SignUpActivity.this, LoginActivity.class);
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                            progressDialog.dismiss();
                            overridePendingTransition(R.anim.slide_in_left,
                                    R.anim.slide_out_right);
                        }
                    }, 5000);
                }
            }
        });
    }

    private void insertDataintoSharedPreference(){
        SharedPrefUtil.putString("Reg_UserName", regName.getText().toString(), SignUpActivity.this);
        SharedPrefUtil.putString("Reg_Email", regEmail.getText().toString(), SignUpActivity.this);
        SharedPrefUtil.putString("Reg_Password", regPass.getText().toString(), SignUpActivity.this);
        SharedPrefUtil.putString("Reg_CPassword", regcPass.getText().toString(), SignUpActivity.this);
        SharedPrefUtil.putString("Reg_Phone", regPhone.getText().toString(), SignUpActivity.this);
        SharedPrefUtil.putString("Reg_Address", regAddress.getText().toString(), SignUpActivity.this);
    }


    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void stopLocationUpdates() {
        Log.d(TAG, "Location update stopped .......................");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
