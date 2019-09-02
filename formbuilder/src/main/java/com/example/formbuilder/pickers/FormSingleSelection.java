package com.example.formbuilder.pickers;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;


import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

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



        OptionsPickerView optionsPickerView =  new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {


                selectedOption.setValue(options.get(options1));

            }
        }).build();

        optionsPickerView.setPicker(options);

        optionsPickerView.show();



        /*

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
        builder.show(); */
    }


    public LiveData<String> getSelectedOption() {

        return selectedOption;


    }


}
