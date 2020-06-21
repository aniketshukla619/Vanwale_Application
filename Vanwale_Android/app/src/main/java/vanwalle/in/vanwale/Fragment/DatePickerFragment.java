package vanwalle.in.vanwale.Fragment;

/**
 * Created by AAKASH on 25-01-2018.
 */

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import vanwalle.in.vanwale.R;
import vanwalle.in.vanwale.model.DateTime;
import vanwalle.in.vanwale.model.DateTimeDialog;


public class DatePickerFragment extends DialogFragment implements TimePicker.OnTimeChangedListener, DatePicker.OnDateChangedListener {

    String s,s1="";
    String month;int yearr,day;
    int month1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int monthOfYear = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        yearr=year;
        day=dayOfMonth;
        month1=monthOfYear;
       // s=year+"-"+(monthOfYear+1)+"-"+dayOfMonth+","+s1;
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
       // s1=hour+":"+minute;
        switch (monthOfYear) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sept";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;
        }

        s=year+"-"+(month)+"-"+dayOfMonth;
        DateTimeDialog myDialog = new DateTimeDialog(getActivity());

        myDialog.setTimeListener(this);
        myDialog.setDateListener(year, monthOfYear, dayOfMonth, this);
        return myDialog;
    }


    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
        // TODO Auto-generated method stub

       switch (monthOfYear) {
           case 0:
               month = "Jan";
               break;
           case 1:
               month = "Feb";
               break;
           case 2:
               month = "March";
               break;
           case 3:
               month = "April";
               break;
           case 4:
               month = "May";
               break;
           case 5:
               month = "June";
               break;
           case 6:
               month = "July";
               break;
           case 7:
               month = "Aug";
               break;
           case 8:
               month = "Sept";
               break;
           case 9:
               month = "Oct";
               break;
           case 10:
               month = "Nov";
               break;
           case 11:
               month = "Dec";
               break;
       }

       s=year+"-"+(month)+"-"+dayOfMonth;
        yearr=year;
        day=dayOfMonth;
        month1=monthOfYear;



    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        // TODO Auto-generated method stub

s1=s+","+hourOfDay+":"+minute;
        DateTime.pickup=day+"/"+(month1+1)+"/"+yearr+" "+hourOfDay+":"+minute;
        TextView tv = (TextView) getActivity().findViewById(R.id.start_time);

        tv.setText(s1);

    }
}
