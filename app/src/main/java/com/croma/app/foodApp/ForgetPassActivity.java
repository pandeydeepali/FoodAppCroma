package com.croma.app.foodApp;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.utll.global.CustomControl;
import com.utll.global.Validation;
import com.utll.global.utilCommon;

/**
 * Created by suppi on 02/07/16.
 */
public class ForgetPassActivity extends AppCompatActivity implements GlobalInterFace {
    private Button forgetButton;
    private  EditText forgetEmail;
    private  ImageView forgetBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        findViewById();
        setOnClickListener();
    }
    /**
     * Interface definition which are declared from Global Interface
     *
     */
    @Override
    public void findViewById() {
        forgetButton = (Button) findViewById(R.id.forgetButton);
        forgetEmail = (EditText) findViewById(R.id.forgetEmail);
        forgetBack = (ImageView) findViewById(R.id.forgetBack);

    }

    @Override
    public void setOnClickListener() {
        forgetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });
        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (!Validation.hasText(forgetEmail)){

                    }else if(!Validation.isValidEmailAddress(forgetEmail, Validation.EMAIL_REGEX, "InValid Email Address", true)){

                    } else {

                        forgetEmail.setError(null);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                CustomControl.successAlert(ForgetPassActivity.this, "Success", "Email Send SUCCESSFUL");
                                //Do something after 100ms
                            }
                        }, 1000);
                    }

             }
        });
    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

}
