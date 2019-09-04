package com.example.formbuilder;


import android.content.Context;


import android.view.View;

import android.widget.LinearLayout;


import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;


import com.basgeekball.awesomevalidation.AwesomeValidation;

import com.example.formbuilder.pickers.ValidationBuilder;

import java.util.ArrayList;
import java.util.List;


import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;


public class FormBuilder {

    private LinearLayout linearLayout;
    private List<FormField> formFieldList;
    private LifecycleOwner lifecycleOwner;

    private int formFieldLayoutId;


    private ValidationBuilder validationBuilder;


    private FormRepo formRepo;


    public FormBuilder(LifecycleOwner lifecycleOwner, Context context,
                       LinearLayout linearLayout, final FragmentManager fragmentManager) {


        this.linearLayout = linearLayout;
        this.formRepo = new FormRepo(context);
        formFieldList = new ArrayList<>();


        this.lifecycleOwner = lifecycleOwner;


        validationBuilder = new ValidationBuilder(new AwesomeValidation(BASIC), fragmentManager);


    }


    public FormBuilder(LifecycleOwner lifecycleOwner, Context context,
                       LinearLayout linearLayout,final FragmentManager fragmentManager, int formFieldLayoutId) {


        this.linearLayout = linearLayout;
        this.formRepo = new FormRepo(context);
        formFieldList = new ArrayList<>();

        this.lifecycleOwner = lifecycleOwner;

        this.formFieldLayoutId = formFieldLayoutId;
        validationBuilder = new ValidationBuilder(new AwesomeValidation(BASIC), fragmentManager);


    }


    public void addField(String tag, FormField.Type type, String headerText, boolean isRequired) {

        FormField formField = new FormField(tag, type, isRequired);

        formField.setFieldId(formFieldLayoutId);
        if (type == FormField.Type.TIME) {
            formField.setFieldId(R.layout.selection_form_field);

        }


        formField.setHeaderText(headerText);
        formFieldList.add(formField);


    }

    public void addField(String tag, FormField.Type type, String headerText, boolean isRequired, int customField) {

        FormField formField = new FormField(tag, type, isRequired);
        formField.setFieldId(customField);
        formField.setHeaderText(headerText);
        formFieldList.add(formField);


    }


    public void addField(String tag, FormField.Type type, String headerText, boolean isRequired,
                         List<String> options) {

        FormField formField = new FormField(tag, type, isRequired);
        formField.setOptions(options);
        formField.setFieldId(R.layout.selection_form_field);
        formField.setHeaderText(headerText);
        formFieldList.add(formField);


    }


    public void addField(String tag, FormField.Type type, String headerText, boolean isRequired,
                         List<String> options, int customField) {

        FormField formField = new FormField(tag, type, isRequired);
        formField.setOptions(options);
        formField.setFieldId(customField);
        formField.setHeaderText(headerText);
        formFieldList.add(formField);


    }


    public void build() {


        if (formFieldList != null) {

            for (final FormField formField : formFieldList) {

                if (buildElement(formField) != null) {
                    View view = buildElement(formField);

                    linearLayout.addView(view);
                    validationBuilder.initiate(formField, view);


                }


            }


        }


    }


    private View buildElement(final FormField formField) {


        return formRepo.buildElement(lifecycleOwner, formField);


    }


    public boolean getmAwesomeValidation() {
        return validationBuilder.validateForm();
    }


    public void clearErrors() {
        validationBuilder.clearValidation();
    }


}
