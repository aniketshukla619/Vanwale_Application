package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by neha on 11/10/17.
 */

public class MySharedPreferences {
    public static final String MY_PREFS_NAME = "MY_PREFS" ;
    public static SharedPreferences.Editor editor;
    SharedPreferences preferences;
    Context mContext;

    public MySharedPreferences(Context mContext) {
        this.mContext=mContext;
        preferences=mContext. getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        editor =preferences.edit();
    }

    public  void insertString(String key, String value){
        editor.putString(key,value);
        editor.commit();
    }

    public String getMyString(String key){
        String strValue = preferences.getString(key,null);
        return strValue;
    }

    public  void insertInteger(String key, int value){
        editor.putInt(key,value);
        editor.commit();
    }

    public  int getMyInteger(String key){

        int returnintvalue = preferences.getInt(key,0);
        return returnintvalue;
    }

    public  void insertBoolean(String key, boolean value){

        editor = preferences.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }

    public  boolean getMyBoolean(String key){

        boolean returnboolvalue=preferences.getBoolean(key,false);
        return returnboolvalue;
    }

    public void insertFloat(String key, float value)
    {
        editor = preferences.edit();
        editor.putFloat(key,value);
        editor.commit();
    }

    public float getMyFloat(String key){

        float returnintvalue = preferences.getFloat(key,0);
        return returnintvalue;
    }

    public void remove(String keyvalue)
    {
        editor.remove(keyvalue);
        editor.commit();
    }

    public void clearsession()
    {
        editor.clear();
        editor.commit();
    }

    public void insertUri(String key, Uri uri) {
        editor.putString(key, String.valueOf(uri));
        editor.commit();
    }

}
