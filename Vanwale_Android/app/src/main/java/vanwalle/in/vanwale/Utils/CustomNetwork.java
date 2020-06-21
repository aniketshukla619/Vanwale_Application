package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by aniket on 02-Jun-17.
 */

public class CustomNetwork {
public static String TAG="TAG";
    public static  boolean  isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}


