package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Rohit Pawar on 13/03/2018.
 */
public class Methods {
    public static final String EXTRA_ACTIVITY = "activityFlag";
    public static final String EXTRA_ACTIVITY_HOME = "activityHome";

//    public static void setFont(Context context, ViewGroup vg) {
//        final String FONT_NAME = context.getString(R.string.font_opensans_regular);
//        for (int i = 0; i < vg.getChildCount(); i++) {
//            View v = vg.getChildAt(i);
//            if (v instanceof ViewGroup)
//                setFont(context, (ViewGroup) v);
//            else if (v instanceof TextView) {
//                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            } else if (v instanceof EditText) {
//                ((EditText) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            } else if (v instanceof Button) {
//                ((Button) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            } else if (v instanceof CheckBox) {
//                ((CheckBox) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            } else if (v instanceof RadioButton) {
//                ((RadioButton) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            } else if (v instanceof Spinner) {
//                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), FONT_NAME));
//            }
//        }
//    }


    // TODO Check Device Network Connection ...
    public static boolean isOnline(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static String getLocalIpAddress(Context context) throws SocketException {
        // WifiManager wifiMgr = (WifiManager) ApplicationController.getInstance().getSystemService(this.WIFI_SERVICE);
        WifiManager wifiMgr = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        if (wifiMgr.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            int ip = wifiInfo.getIpAddress();
            String wifiIpAddress = String.format("%d.%d.%d.%d",
                    (ip & 0xff),
                    (ip >> 8 & 0xff),
                    (ip >> 16 & 0xff),
                    (ip >> 24 & 0xff));

            return wifiIpAddress;
        }

        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                InetAddress inetAddress = enumIpAddr.nextElement();
                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                    return inetAddress.getHostAddress();
                }

            }
        }
        return null;
    }
}
