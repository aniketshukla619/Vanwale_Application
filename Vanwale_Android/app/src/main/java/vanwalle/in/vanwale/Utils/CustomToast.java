package vanwalle.in.vanwale.Utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {

    public static void show_neutral(Context context, String message) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        TextView text = new TextView(context);
        text.setText(message);
        text.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_dark));
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        text.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        text.setPadding(15, 15, 15, 15);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 25);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, text.getId());
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, text.getId());
        relativeLayout.addView(text, lp);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(relativeLayout);
        toast.show();

    }


    public static void showerror(Context context, String message) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        TextView text = new TextView(context);
        text.setText(message);
        text.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        text.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        text.setPadding(20, 20, 20, 20);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, text.getId());
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, text.getId());
        relativeLayout.addView(text, lp);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(relativeLayout);
        toast.show();
    }

    public static void showsuccess(Context context, String message) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        TextView text = new TextView(context);
        text.setText(message);
        text.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_green_dark));
        text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        text.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        text.setPadding(20, 20, 20, 20);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, text.getId());
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, text.getId());
        relativeLayout.addView(text, lp);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(relativeLayout);
        toast.show();
    }

}


