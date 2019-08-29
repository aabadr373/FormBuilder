package com.example.formbuilder;

import android.content.Context;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.formbuilder.pickers.FormDatePicker;
import com.example.formbuilder.pickers.FormMulitpleSelection;
import com.example.formbuilder.pickers.FormSingleSelection;
import com.example.formbuilder.pickers.FormTimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class FieldBuilderFactory {
    private Context context;
    private LifecycleOwner lifecycleOwner;


     FieldBuilderFactory(LifecycleOwner lifecycleOwner, LinearLayout linearLayout, Context context) {


        this.context = context;
        this.lifecycleOwner = lifecycleOwner;


    }


     View buildField(final FormField formField) {

        int layoutID = R.layout.form_field;
        FormField.Type type = formField.getType();

        LayoutInflater inflater = LayoutInflater.
                from(context);

        if (formField.getFieldId() != 0) {

            layoutID = formField.getFieldId();

        }

        View view = inflater.inflate(layoutID, new LinearLayout(context), true);


        final EditText formFieldView = (EditText) view.findViewById(R.id.field_input);


        View view1 = view.findViewById(R.id.header_layout);

        TextView textView = view1.findViewById(R.id.form_header_text);

        textView.setText(formField.getTag());

        formFieldView.setEnabled(formField.isEnabled());

        formFieldView.setText(formField.getValue());

        switch (type) {




            case TEXT:

                formFieldView.setInputType(InputType.TYPE_CLASS_TEXT);


                break;


            case EMAIL:

                formFieldView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;

            case PHONE:


                formFieldView.setInputType(InputType.TYPE_CLASS_PHONE);
                break;

            case PASSWORD:

                formFieldView.setInputType(InputType.TYPE_CLASS_TEXT);
                formFieldView.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;

            case NUMBER:

                formFieldView.setInputType(InputType.TYPE_CLASS_NUMBER);

                break;
            case URL:

                formFieldView.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);

                break;

            case ZIP:
                formFieldView.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);

                break;

            case DATE:

                final TextView dateTV = (EditText) view.findViewById(R.id.field_input);

                dateTV.setFocusable(false);
                dateTV.setClickable(true);
                final FormDatePicker datePicker = new FormDatePicker(formField, context);
                dateTV.setInputType(InputType.TYPE_CLASS_TEXT);
                dateTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        datePicker.pickDate();
                    }
                });

                datePicker.getMutableLiveData().observe(lifecycleOwner, new Observer<Calendar>() {
                    @Override
                    public void onChanged(Calendar calendar) {

                        SimpleDateFormat sdf = new SimpleDateFormat(formField.getDateFormat());


                        dateTV.setText(sdf.format(calendar.getTime()));


                    }
                });

                break;


            case TIME:
                final TextView timeTV = (EditText) view.findViewById(R.id.field_input);

                timeTV.setFocusable(false);
                timeTV.setClickable(true);
                final FormTimePicker timePicker = new FormTimePicker(formField, context);
                timeTV.setInputType(InputType.TYPE_CLASS_TEXT);
                timeTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        timePicker.pickTime();
                    }
                });


                timePicker.getTime().observe(lifecycleOwner, new Observer<Calendar>() {
                    @Override
                    public void onChanged(Calendar calendar) {

                        SimpleDateFormat sdf = new SimpleDateFormat(formField.getTimeFormat());
                        timeTV.setText(sdf.format(calendar.getTime()));

                    }
                });


                break;

            case MULTIPLE_SELECTION:

                final TextView multipleTV = (EditText) view.findViewById(R.id.field_input);


                multipleTV.setFocusable(false);
                multipleTV.setClickable(true);
                multipleTV.setInputType(InputType.TYPE_CLASS_TEXT);


                final FormMulitpleSelection formMulitpleSelection = new FormMulitpleSelection(formField.getOptions(), context);

                formFieldView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        formMulitpleSelection.pickMultipleDialog();


                    }
                });


                formMulitpleSelection.getSelectedList().observe(lifecycleOwner, new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> strings) {

                        multipleTV.setText(strings.toString());
                    }
                });


                break;

            case SELECTION:

                final TextView singleTV = (EditText) view.findViewById(R.id.field_input);

                final FormSingleSelection formSingleSelection = new FormSingleSelection(formField.getOptions(), context);

                singleTV.setFocusable(false);
                singleTV.setClickable(true);

                singleTV.setInputType(InputType.TYPE_CLASS_TEXT);


                singleTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        formSingleSelection.pickDialog();

                    }
                });


                formSingleSelection.getSelectedOption().observe(lifecycleOwner, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        singleTV.setText(s);
                    }
                });


                break;
            default:

                return null;

        }


        return view;

    }


}
