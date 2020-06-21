package vanwalle.in.vanwale.Utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by aniket on 02-Jun-17.
 */

public class CustomProgress {
    private static ProgressDialog progressDialog;

    public static void showProgress(Context context, String message)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..."+message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void setMessage(String message)
    {
        progressDialog.setMessage(message);
    }

    public static void showProgress(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
    public static void hideprogress()
    {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }
}
