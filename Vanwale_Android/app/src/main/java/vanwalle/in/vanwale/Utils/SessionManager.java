package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;


/**
 * Created by Rohit K. Pawar on 13/03/2018.
 */

public class SessionManager {
    public static final String PREF_NAME = "session_user";
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String USER_ID = "id";
    public static final String USER_NAME = "username";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_STATE = "state";
    public static final String USER_CITY = "city";
    public static final String USER_AREA = "area";
    public static final String USER_PINCODE = "pincode";
    public static final String USER_LOGIN_TYPE = "loginType";
    public static final String USER_IS_ACTIVE = "isActive";
    public static final String USER_TYPE = "userType";
    public static final String USER_PROFILE_IMG = "profileImage";
    public static final String IS_FIRST_TIME = "isFirstTime";

    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     */
    public void createSession(String loginType, String userId,
                              String email, String phone, String userName, String state,
                              String city, String area, String pincode,
                              String isActive, String userType, String profileImg) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(USER_LOGIN_TYPE, loginType);
        editor.putString(USER_NAME, userName);
        editor.putString(USER_ID, userId);
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_PHONE, phone);
        editor.putString(USER_STATE, state);
        editor.putString(USER_CITY, city);
        editor.putString(USER_AREA, area);
        editor.putString(USER_PINCODE, pincode);
        editor.putString(USER_IS_ACTIVE, isActive);
        editor.putString(USER_TYPE, userType);
        editor.putString(USER_PROFILE_IMG, profileImg);
        editor.commit();
    }
//
//    public void checkLogin() {
//        if (!this.isLoggedIn()) {
//            Intent i = new Intent(_context, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
//        }
//    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(USER_LOGIN_TYPE, pref.getString(USER_LOGIN_TYPE, ""));
        user.put(USER_NAME, pref.getString(USER_NAME, ""));
        user.put(USER_ID, pref.getString(USER_ID, ""));
        user.put(USER_EMAIL, pref.getString(USER_EMAIL, ""));
        user.put(USER_PHONE, pref.getString(USER_PHONE, ""));
        user.put(USER_STATE, pref.getString(USER_STATE, ""));
        user.put(USER_CITY, pref.getString(USER_CITY, ""));
        user.put(USER_AREA, pref.getString(USER_AREA, ""));
        user.put(USER_PINCODE, pref.getString(USER_PINCODE, ""));
        user.put(USER_IS_ACTIVE, pref.getString(USER_IS_ACTIVE, ""));
        user.put(USER_TYPE, pref.getString(USER_TYPE, ""));
        user.put(USER_PROFILE_IMG, pref.getString(USER_PROFILE_IMG, ""));
        return user;
    }

    /**
     * Clear session details
     */
//    public void logoutUser() {
//        clearSession();
//        Intent i = new Intent(_context, LoginActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        _context.startActivity(i);
//    }

    public void clearSession() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }

    /**
     * Quick check for login
     * *
     */
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public String getUserId() {
        return pref.getString(USER_ID, "");
    }

    public String getUserName() {
        return pref.getString(USER_NAME, " ");
    }

    public String getIsFristTime() {
        return pref.getString(IS_FIRST_TIME, "");
    }

    public void setIsFristTime(String flg) {
        editor.putString(IS_FIRST_TIME, flg);
        editor.commit();
    }


}
