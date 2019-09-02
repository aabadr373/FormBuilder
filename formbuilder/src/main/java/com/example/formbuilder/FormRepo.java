package com.example.formbuilder;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;


import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

public class FormRepo {

    private Context context;


    FormRepo(Context context) {


        this.context = context;
    }


    View buildElement(LifecycleOwner lifecycleOwner, final FormField formField, FragmentManager fragmentManager) {

        FieldBuilderFactory fieldBuilderFactory = new FieldBuilderFactory(lifecycleOwner,context, fragmentManager);


        return fieldBuilderFactory.buildField(formField);


    }


    private void formListSharedPref(List<FormField> formFieldList) {

        if (formFieldList != null) {
            SharedPreferences appSharedPrefs = context.getSharedPreferences("formPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
            Gson gson = new Gson();
            String json = gson.toJson(formFieldList);
            prefsEditor.putString("formFieldList", json);
            prefsEditor.apply();

        }


    }


}
