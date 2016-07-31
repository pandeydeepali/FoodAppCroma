package com.croma.app.foodApp;

import android.content.Context;
import android.content.SharedPreferences;


//Shared Preference Class for preference file
public final class SharedPrefUtil {


    public static final String TCS = "TCS";


    private SharedPrefUtil(){
        throw new Error("Do n" +
                "ot need instantiate!");
    }
    /**
     * @param key
     * @param value
     * @param context
     */
    public static void putBoolean(String key, boolean value, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }




    /**
     * @param key
     * @param value
     * @param context
     */
    public static void putInt(String key, int value, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }



    /**
     * @param key
     * @param value
     * @param context
     */
    public static void putLong(String key, long value, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * @param key
     * @param defaultValue
     * @param context
     */
    public static void putString(String key, String defaultValue, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, defaultValue);
        editor.commit();
    }

    /**
     * @param key
     * @param defValue
     * @param context
     * @return
     */
    public static long getLong(String key, long defValue, Context context) {
        return getSharedPreferences(context).getLong(key, defValue);
    }

    /**
     * @param key
     * @param defValue
     * @param context
     * @return
     */
    public static String getString(String key, String defValue, Context context) {
        return getSharedPreferences(context).getString(key, defValue);
    }



    /**
     *
     * @param key
     * @param context
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue, Context context) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }


        /**
         * @param key
         * @param context
         */
    public static void remove(String key, Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(key);
        editor.commit();
    }


    public static void clear(Context context){
        SharedPreferences.Editor editor=getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }



    /**
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(SharedPrefUtil.TCS, Context.MODE_PRIVATE);
        return pref;
    }
}
