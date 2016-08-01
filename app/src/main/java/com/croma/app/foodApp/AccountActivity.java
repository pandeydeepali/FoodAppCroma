package com.croma.app.foodApp;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountActivity extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    private EditText accountUname;
    private EditText accountEmail;
    private EditText accountMobile;
    private ImageView accountBack;
    private TextView accountEdit;
    private Button accountSettingsBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        findViewById();
        setOnClickListener();
        disabledEdittext();


    }

    @Override
    public void findViewById() {
        accountBack=(ImageView)findViewById(R.id.accountBack);
        accountUname=(EditText)findViewById(R.id.accountName);
        accountEmail=(EditText)findViewById(R.id.accountEmail);
        accountMobile=(EditText)findViewById(R.id.accountMobile);
        accountEdit=(TextView)findViewById(R.id.edit_text);
        accountSettingsBtn=(Button)findViewById(R.id.accountButton);
        accountSettingsBtn.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        accountUname.setText(SharedPrefUtil.getString("Reg_UserName","", AccountActivity.this ));
        accountEmail.setText(SharedPrefUtil.getString("Reg_Email","", AccountActivity.this ));
        accountMobile.setText(SharedPrefUtil.getString("Reg_Phone","", AccountActivity.this ));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        accountEdit.setText(R.string.ED);
        accountUname.setEnabled(false);
        accountEmail.setEnabled(false);
        accountMobile.setEnabled(false);

    }

    @Override
    public void setOnClickListener() {
        accountBack.setOnClickListener(this);
        accountEdit.setOnClickListener(this);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

    private void disabledEdittext(){
        accountUname.setEnabled(false);
        accountEmail.setEnabled(false);
        accountMobile.setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accountBack:{
                finish();
            }

            case R.id.edit_text:{
                if(accountEdit.getText().equals("Edit")){
                    accountEdit.setText(R.string.apply);
                    accountUname.setEnabled(true);
                    accountEmail.setEnabled(true);
                    accountMobile.setEnabled(true);
                }

                else{
                    accountEdit.setText(R.string.ED);
                    accountUname.setEnabled(false);
                    accountEmail.setEnabled(false);
                    accountMobile.setEnabled(false);

                }

            }

            case R.id.accountButton:{
                progressDialog = ProgressDialog.show(
                        AccountActivity.this, "Please Wait...",
                        "Data Saving...", true);
                SharedPrefUtil.putString("Reg_UserName", accountUname.getText().toString(), AccountActivity.this);
                SharedPrefUtil.putString("Reg_Email", accountEmail.getText().toString(), AccountActivity.this);
                SharedPrefUtil.putString("Reg_Reg_Phone", accountEmail.getText().toString(), AccountActivity.this);
                accountUname.setText(SharedPrefUtil.getString("Reg_UserName","", AccountActivity.this ));
                accountEmail.setText(SharedPrefUtil.getString("Reg_Email","", AccountActivity.this ));
                accountMobile.setText(SharedPrefUtil.getString("Reg_Phone","", AccountActivity.this ));
                progressDialog.dismiss();
            }
        }
    }

}
