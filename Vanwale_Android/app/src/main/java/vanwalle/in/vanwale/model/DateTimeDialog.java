package vanwalle.in.vanwale.model;

/**
 * Created by AAKASH on 17-02-2018.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.util.Calendar;

import vanwalle.in.vanwale.R;


public class DateTimeDialog extends Dialog implements android.view.View.OnClickListener{

    private TimePicker mTime;
    private DatePicker mDate;

    public DateTimeDialog(Context context) {
        super(context);
        setContentView(R.layout.date_time);

        mTime = (TimePicker)findViewById(R.id.timePicker);
        mDate = (DatePicker)findViewById(R.id.datePicker);
        final Calendar c = Calendar.getInstance();
mDate.setMinDate(c.getTimeInMillis());
        Button done = (Button)findViewById(R.id.done);
        done.setOnClickListener(this);

        Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.done:
                dismiss();
            case R.id.cancel:
                dismiss();
        }

    }

    public void setTimeListener(OnTimeChangedListener time){
        mTime.setOnTimeChangedListener(time);
    }

    public void setDateListener(int year, int monthOfYear, int dayOfMonth, OnDateChangedListener date){
        mDate.init(year, monthOfYear, dayOfMonth, date);
    }
}
