package com.example.formbuilder.pickers;

import android.content.Context;
import android.content.DialogInterface;


import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class FormSingleSelection {


    private Context context;
    private List<String> options;
    private MutableLiveData<String> selectedOption;
    private int selectedPosition;

    public FormSingleSelection(List<String> options, Context context) {

        this.context = context;
        this.options = options;
        selectedOption =  new MutableLiveData<>();
    }

    public void pickDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");

        builder.setSingleChoiceItems(options.toArray(new CharSequence[options.size()]),
                -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedPosition = i;

                    }
                });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                selectedOption.setValue(options.get(selectedPosition));


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    public LiveData<String> getSelectedOption() {

        return selectedOption;


    }


}
