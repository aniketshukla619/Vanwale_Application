package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Rohit K. Pawar on 13/03/2018.
 */
public class DisplayToast {

   public static void displayFailureMessage(int statusCode, Context mContext) {
        if (statusCode == 404) {
            Toast.makeText(mContext, "Requested resource not found", Toast.LENGTH_LONG).show();
        }
        // When Http response code is '500'
        else if (statusCode == 500) {
            Toast.makeText(mContext, "Something went wrong at server end", Toast.LENGTH_LONG).show();
        }
        // When Http response code other than 404, 500
        else {
            Toast.makeText(mContext, "Unexpected Error occurred! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
        }
    }
    public static void showShortToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}
