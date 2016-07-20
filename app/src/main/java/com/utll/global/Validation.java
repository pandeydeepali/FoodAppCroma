package com.utll.global;

import android.widget.EditText;

import com.croma.app.foodApp.R;

import java.util.regex.Pattern;

/**
 * Created by suppi on 30/06/16.
 */
public class Validation {
    // Regular Expression
    // you can change the expression based on your need
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
    // Error Messages
    private static final String REQUIRED_MSG = "Field Can Not Be Empty";
    private static final String EMAIL_MSG = "Invalid EmailAddress Found";
    private static final String PHONE_MSG = "## ## ###";
    private static final String LENGTH_MSG = "Length Should be 6 characters";

//    // call this method when you need to check email validation
//    public static boolean isEmailAddress(EditText editText, boolean required) {
//        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
//    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required) {
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    public static boolean isValidEmailAddress(EditText et, String regex, String errMsg, boolean required){
        String etext = et.getText().toString().trim();
        if (required && !Pattern.matches(regex, etext)) {
            et.setError(errMsg);
            return false;
        };
        return true;
    }

    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required) {
         String text = editText.getText().toString().trim();
         editText.setError(null);
        if ( required && !hasText(editText) )
            return false;
        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
            editText.setError(errMsg);
            return false;
        };
        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        else if(text.length()<6){
            editText.setError(LENGTH_MSG);
            return false;
        }else{
            editText.setError(null);
        }
        return true;
    }
}


