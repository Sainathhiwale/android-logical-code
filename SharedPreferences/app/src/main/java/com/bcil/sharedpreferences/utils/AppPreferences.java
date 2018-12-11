package com.bcil.sharedpreferences.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    // shared preferences keys
    public static final String KEY_USER_NAME = "KEY_USER_NAME";
    public static final String KEY_USER_EMAIL = "KEY_USER_EMAIL";
    public static final String KEY_USER_PHONE = "KEY_USER_PHONE";
    public static final String KEY_USER_TYPE = "KEY_USER_TYPE";
    public static final String KEY_SAVE_USER = "KEY_SAVE_USER";

    private static final String PREFERENCES_NAME = "ANDROID_TUTORIALS_EXAMEN_PREFERENCES";
    private SharedPreferences sharedPreferences;

    public AppPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /*method to set string prefs
     * */

    public void setStringPrefs(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /*
     * Method to get string prefs
     * */

    public String getStringPrefs(String key) {
        return sharedPreferences.getString(key, null);

    }

    /*
     * method to set int prefs
     * */

    public void setIntPrfs(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /*method to get int prefs
    * */

    public int getIntPrefs(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    /*method to set boolean prefs
    * */

    public void setBoolenPrefs(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /*method to get boolean prefs
    * */

    public boolean getBoolenPrefs(String key) {
        return sharedPreferences.getBoolean(key, false);

    }

    /*method to set long prefs
    * */

    public void setLongPrefs(String key, long value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
    }

    /*method to get long prefs
    * */

    public long getLongPrefs(String key) {
        return sharedPreferences.getLong(key, 0L);
    }

    /*  method to set float prefs
     */

    public void setFloatPrefs(String key, float  value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /*  method to get float prefs
     */

    public float getFloatPrefs(String key){
        return sharedPreferences.getFloat(key,0.0F);
    }

    /*method to clear specific prefs
    * */

    public void clearPrefs(String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    /*method to clear all prefs
    * */
    public void clearPrefs(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
