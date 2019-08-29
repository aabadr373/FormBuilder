package com.example.formbuilder.pickers;

import android.content.Context;
import android.content.DialogInterface;


import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



import java.util.ArrayList;
import java.util.List;

public class FormMulitpleSelection {

    private List<String> opttions;
    private Context context;
    private ArrayList<String> selection;
    private MutableLiveData<List<String>> selectionList;


    public FormMulitpleSelection(List<String> opttions, Context context) {

        this.context = context;
        this.opttions = opttions;
        if (selection == null) {
            selection = new ArrayList<>();

        }


        selectionList = new MutableLiveData<>();

    }

    public void pickMultipleDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("");




        builder.setMultiChoiceItems(opttions.
                        toArray(new CharSequence[opttions.size()]), null,

                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {

                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selection.add(opttions.get(which));
                        } else if (selection.contains(opttions.get(which))) {
                            // Else, if the item is already in the array, remove it
                            selection.remove(which);
                        }


                    }
                });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectionList.setValue(selection);

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


    public LiveData<List<String>> getSelectedList() {

        return selectionList;

    }


}
