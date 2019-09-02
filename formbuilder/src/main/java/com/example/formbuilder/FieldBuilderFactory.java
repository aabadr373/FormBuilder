package com.example.formbuilder;

import android.content.Context;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.formbuilder.pickers.FormDatePicker;
import com.example.formbuilder.pickers.FormMulitpleSelection;
import com.example.formbuilder.pickers.FormSingleSelection;
import com.example.formbuilder.pickers.FormTimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


 class FieldBuilderFactory {
    private Context context;
    private LifecycleOwner lifecycleOwner;
    private FragmentManager fragmentManager;



    FieldBuilderFactory(LifecycleOwner lifecycleOwner,Context context
    , FragmentManager fragmentManager) {


        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;




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


        View view1 = view.findViewById(R.id.header_layout);

        TextView textView = view.findViewById(R.id.form_header_text);

        textView.setText(formField.getTag());


        final Object formFieldView = view.findViewById(R.id.field_input);


        if (formFieldView instanceof TextView) {



            ((TextView) formFieldView).setEnabled(formField.isEnabled());

            ((TextView) formFieldView).setText(formField.getValue());

            switch (type) {


                case TEXT:

                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);


                    break;


                case EMAIL:

                    ((TextView) formFieldView).setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    break;

                case PHONE:


                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_PHONE);
                    break;

                case PASSWORD:

                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);
                    ((TextView) formFieldView).setTransformationMethod(PasswordTransformationMethod.getInstance());
                    break;

                case NUMBER:

                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_NUMBER);

                    break;
                case URL:

                    ((TextView) formFieldView).setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);

                    break;

                case ZIP:
                    ((TextView) formFieldView).setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);

                    break;

                case DATE:



                    ((TextView) formFieldView).setFocusable(false);
                    ((TextView) formFieldView).setClickable(true);
                    final FormDatePicker datePicker = new FormDatePicker(formField, context);
                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);
                    ((TextView) formFieldView).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            datePicker.pickDate();
                        }
                    });

                    datePicker.getMutableLiveData().observe(lifecycleOwner, new Observer<Calendar>() {
                        @Override
                        public void onChanged(Calendar calendar) {

                            SimpleDateFormat sdf = new SimpleDateFormat(formField.getDateFormat());


                            ((TextView) formFieldView).setText(sdf.format(calendar.getTime()));


                        }
                    });

                    break;


                case TIME:


                    ((TextView) formFieldView).setFocusable(false);
                    ((TextView) formFieldView).setClickable(true);
                    final FormTimePicker timePicker = new FormTimePicker(formField, context);
                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);
                    ((TextView) formFieldView).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            timePicker.pickTime();
                        }
                    });


                    timePicker.getTime().observe(lifecycleOwner, new Observer<Calendar>() {
                        @Override
                        public void onChanged(Calendar calendar) {

                            SimpleDateFormat sdf = new SimpleDateFormat(formField.getTimeFormat());
                            ((TextView) formFieldView).setText(sdf.format(calendar.getTime()));

                        }
                    });


                    break;

                case MULTIPLE_SELECTION:




                    ((TextView) formFieldView).setFocusable(false);
                    ((TextView) formFieldView).setClickable(true);
                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);


                    final FormMulitpleSelection formMulitpleSelection = new FormMulitpleSelection(formField.getOptions(), context, fragmentManager);

                    ((TextView) formFieldView).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            formMulitpleSelection.pickMultipleDialog();


                        }
                    });


                    formMulitpleSelection.getSelectedList().observe(lifecycleOwner, new Observer<List<String>>() {
                        @Override
                        public void onChanged(List<String> strings) {

                            ((TextView) formFieldView).setText(strings.toString());
                        }
                    });


                    break;

                case SELECTION:



                    final FormSingleSelection formSingleSelection = new FormSingleSelection(formField.getOptions(), context);

                    ((TextView) formFieldView).setFocusable(false);
                    ((TextView) formFieldView).setClickable(true);

                    ((TextView) formFieldView).setInputType(InputType.TYPE_CLASS_TEXT);


                    ((TextView) formFieldView).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            formSingleSelection.pickDialog();

                        }
                    });


                    formSingleSelection.getSelectedOption().observe(lifecycleOwner, new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            ((TextView) formFieldView).setText(s);
                        }
                    });


                    break;
                default:

                    return null;

            }

        }
        return view;

    }


}
