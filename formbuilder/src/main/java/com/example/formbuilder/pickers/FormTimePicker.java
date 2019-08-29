package com.example.formbuilder.pickers;

import android.app.TimePickerDialog;
import android.content.Context;

import android.widget.TimePicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.formbuilder.FormField;
import java.util.Calendar;

public class FormTimePicker {


    private Context context;
    private MutableLiveData<Calendar> timeMutableLiveData;


    public FormTimePicker(FormField selectedFormField, Context context) {


        this.context = context;
        timeMutableLiveData = new MutableLiveData<>();
    }


    public void pickTime() {


        Calendar calendar = Calendar.getInstance();

        if (timeMutableLiveData.getValue() != null) {

            calendar = timeMutableLiveData.getValue();

        }

        new TimePickerDialog(context, timePickerListener, calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE), true)//Yes 24 hour time
                .show();

    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);

            timeMutableLiveData.setValue(calendar);

        }
    };

    public LiveData<Calendar> getTime() {


        return timeMutableLiveData;
    }
}
