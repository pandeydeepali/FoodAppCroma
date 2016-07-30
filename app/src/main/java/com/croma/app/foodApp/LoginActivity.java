package com.croma.app.foodApp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.utll.global.ActivitySwitcher;
import com.utll.global.utilCommon;
import com.utll.global.CustomControl;
import com.utll.global.Validation;

/**
 * Created by suppi on 26/06/16.
 * Login Activity Class
 * We are using a interface here for optimizatio of source code
 * Inteface: Declaration in interface
 * and definaition in class files
 *
 */
    public class LoginActivity extends AppCompatActivity implements GlobalInterFace,View.OnClickListener, TextWatcher {
    private static final String TAG = LoginActivity.class.getName();
    private  EditText loginUName;
    private  EditText loginPass;
    private  Button loginButton;
    private  TextView loginRegLink;
    private  TextView loginForgetLink;
    private  CheckBox loginshowPassCB;

    //----progress dialog
    private ProgressDialog progressDialog=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        setOnClickListener();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displaySharedPreferenceValue();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if(loginUName.getText().length()==0 && loginPass.getText().length()==0 ){
            loginUName.setError("Field Can Not Be Empty");
            loginPass.setError("Field Can Not be empty");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void displaySharedPreferenceValue(){
        SharedPreferences myPrefs = getSharedPreferences(GlobalsharedPreference.loginsharedPREFERENCES, MODE_PRIVATE);
        String loginUserName=myPrefs.getString("userName", "");
        String loginPassword=myPrefs.getString("userPassword", "");
        loginUName.setText(loginUserName);
        loginPass.setText(loginPassword);
    }

    /**
     * Interface Method Definition and declaration in GlobalInterface Idenfied by I icon in package
     *Method: setOnClickListener and findViewById
     */

    @Override
    public void findViewById() {
        loginButton = (Button) findViewById(R.id.loginButton);
        loginUName = (EditText) findViewById(R.id.loginUname);
        loginPass = (EditText) findViewById(R.id.loginPass);
        loginRegLink = (TextView) findViewById(R.id.loginRegLink);
        loginForgetLink = (TextView) findViewById(R.id.loginForgetLink);
        loginshowPassCB=(CheckBox)findViewById(R.id.loginCheckBox);
     }

    @Override
    public void setOnClickListener() {
        //---this is useful for current activity.. we can also write here loginActivity.this--//
        loginButton.setOnClickListener(this);
        loginForgetLink.setOnClickListener(this);
        loginRegLink.setOnClickListener(this);
        loginshowPassCB.setOnClickListener(this);
        loginUName.addTextChangedListener(this);
        loginPass.addTextChangedListener(this);
    }



    private void validateUserOnLoginButton() {
        try {
            if (!Validation.hasText(loginUName) && !Validation.hasText(loginPass) || !Validation.hasText(loginUName) || !Validation.hasText(loginPass)) {

            } else if (!Validation.isValid(loginPass, Validation.PASSWORD_REGEX, "Password Should be alphanumeric with specialCharacter", true)) {

            }else {
                progressDialog=ProgressDialog.show(this, "Please wait","Logging...", true );
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        try {
                            SharedPrefUtil.getString("Reg_UserName", " Foody ", LoginActivity.this);
                            SharedPrefUtil.getString("Reg_Password", " Foody ", LoginActivity.this);
                            if(loginUName.getText().toString().equals(SharedPrefUtil.getString("Reg_UserName", "", LoginActivity.this))
                                 && loginPass.getText().toString().equals(SharedPrefUtil.getString("Reg_Password", "", LoginActivity.this))){
                                 CustomControl.successAlert(LoginActivity.this, "Success", "LOGIN SUCCESSFUL");
                                 SharedPrefUtil.putString("loggedBoolean", "1", LoginActivity.this);
                                 ActivitySwitcher.switchActivity(LoginActivity.this, NavigationActivity.class);
                            }else{
                                CustomControl.alertDialogShow(LoginActivity.this, "Error", "Login Failed");
                            }
                        } catch (Exception e) {
                            //Dismiss The Dialog
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                        overridePendingTransition(R.anim.slide_in_left,
                                R.anim.slide_out_right);
                    }
                }, 3000);
                if (utilCommon.isNetworkAvailable(LoginActivity.this)) {
                    //----hit web service from here
                }
              }
        }catch (Exception e){
            e.printStackTrace();

        }
    }


    private void registerUserOnRegLink(){
                Intent intent = new Intent(LoginActivity.this, PagerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);

    }


    private void sendPassOnForgetPasswordLink(){
        Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
    }

    private void showPassword(){
        if(!loginshowPassCB.isChecked()){
            Log.e("err", "encrypted");
            loginPass.setInputType(129);
        }else{
            Log.e("succ", "visible");
            //-----visible password alphabet charactre
             loginPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:{
                validateUserOnLoginButton();
                break;
            }

            case R.id.loginForgetLink:{
                sendPassOnForgetPasswordLink();
                break;
            }

            case R.id.loginRegLink:{
                registerUserOnRegLink();
                break;
            }

            case R.id.loginCheckBox:{
                showPassword();
                break;
            }
         }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(loginUName.length()>0){
            loginUName.setError(null);
        }
        if(loginUName.length()==0){
            loginUName.setError("Field Can Not Be Empty");
        }
        if(loginPass.length()>0){
            loginPass.setError(null);

        }
        if(loginPass.length()==0){
            loginPass.setError("Field Can Not Be Empty");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
