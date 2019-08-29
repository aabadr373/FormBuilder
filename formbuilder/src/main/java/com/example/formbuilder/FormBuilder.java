package com.example.formbuilder;

import android.content.Context;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.lifecycle.LifecycleOwner;


import com.basgeekball.awesomevalidation.AwesomeValidation;

import java.util.ArrayList;

import java.util.List;


import static com.basgeekball.awesomevalidation.ValidationStyle.COLORATION;

public class FormBuilder {

    private LinearLayout linearLayout;
    private List<FormField> formFieldList;
    private LifecycleOwner lifecycleOwner;
    private AwesomeValidation mAwesomeValidation;


    private FormRepo formRepo;


    public FormBuilder(LifecycleOwner lifecycleOwner, Context context,
                       LinearLayout linearLayout) {


        this.linearLayout = linearLayout;
        this.formRepo = new FormRepo(context);
        formFieldList = new ArrayList<>();
        this.lifecycleOwner = lifecycleOwner;
        mAwesomeValidation = new AwesomeValidation(COLORATION);


    }


    public void addField(String tag, FormField.Type type, boolean isRequired) {

        FormField formField = new FormField(tag, type);
        formFieldList.add(formField);


    }

    public void addField(String tag, FormField.Type type, boolean isRequired, int customField) {

        FormField formField = new FormField(tag, type);
        formField.setFieldId(customField);
        formFieldList.add(formField);


    }


    public void addSelectionField(String tag, FormField.Type type, boolean isRequired,
                                  List<String> options) {

        FormField formField = new FormField(tag, type);
        formField.setOptions(options);
        formFieldList.add(formField);


    }


    public void build() {


        if (formFieldList != null) {

            for (FormField formField : formFieldList) {

                if (buildElement(formField, linearLayout) != null) {
                    View view = buildElement(formField, linearLayout);

                    linearLayout.addView(view);

                    if (formField.isRequired()) {

                        mAwesomeValidation.addValidation((EditText) view.findViewById(R.id.field_input), "[a-zA-Z0-9_-]+",
                                formField.getTag().concat(" is required."));
                    }


                }


            }


        }


    }


    private View buildElement(final FormField formField, LinearLayout linearLayout) {


        return formRepo.buildElement(lifecycleOwner, linearLayout, formField);


    }


    public boolean getmAwesomeValidation() {
        return mAwesomeValidation.validate();
    }
}
