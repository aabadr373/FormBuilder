package com.example.formbuilder.pickers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.formbuilder.FormField;


import java.util.Calendar;


public class FormDatePicker {


    private FormField selectedFormField;
    private Context context;
    private MutableLiveData<Calendar> mutableLiveData;


    public FormDatePicker(FormField selectedFormField, Context context) {

        this.selectedFormField = selectedFormField;
        this.context = context;
        mutableLiveData = new MutableLiveData<>();
    }

    public void pickDate() {

        Calendar calendar = Calendar.getInstance();

        if(mutableLiveData.getValue() != null){

            calendar = mutableLiveData.getValue();

        }


            new DatePickerDialog(context, datePickerListener, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();

    }



    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {


        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


            mutableLiveData.setValue(calendar);




            }


    };


    public LiveData<Calendar> getMutableLiveData() {
        return mutableLiveData;
    }
}
