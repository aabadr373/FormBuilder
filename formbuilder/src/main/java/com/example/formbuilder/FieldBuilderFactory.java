package com.example.formbuilder;

import android.content.Context;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.Locale;


class FieldBuilderFactory {
    private Context context;
    private LifecycleOwner lifecycleOwner;


    FieldBuilderFactory(LifecycleOwner lifecycleOwner, Context context
    ) {


        this.context = context;
        this.lifecycleOwner = lifecycleOwner;


    }


    View buildField(final FormField formField) {


        int layoutID = R.layout.form_field;
        boolean hasHeader = formField.isHasHeader();
        FormField.Type type = formField.getType();
        boolean isRequired = formField.isRequired();

        LayoutInflater inflater = LayoutInflater.
                from(context);


        if (type == FormField.Type.DATE) {


            View view1 = inflater.inflate(R.layout.date_form_field, new LinearLayout(context), true);


            if (!hasHeader) {
                view1.findViewById(R.id.header_layout).setVisibility(View.GONE);


            } else {

                if(!isRequired){

                    view1.findViewById(R.id.is_required).setVisibility(View.INVISIBLE);


                }


                ((ImageView) view1.findViewById(R.id.field_header_icon))
                        .setImageResource(R.drawable.ic_calendar);

                ((TextView) view1.findViewById(R.id.form_header_text))
                        .setText(formField.getHeaderText());



            }

            LinearLayout linearLayout;
            final TextView day, month, year;

            ImageView imageView = view1.findViewById(R.id.field_header_icon);
            imageView.setImageResource(R.drawable.ic_calendar);
            linearLayout = view1.findViewById(R.id.field_input);
            day = linearLayout.findViewById(R.id.day_tv);
            month = linearLayout.findViewById(R.id.month_tv);
            year = linearLayout.findViewById(R.id.year_tv);


            linearLayout.setFocusable(false);
            linearLayout.setClickable(true);
            final FormDatePicker datePicker = new FormDatePicker(formField, context);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    datePicker.pickDate();
                }
            });

            datePicker.getMutableLiveData().observe(lifecycleOwner, new Observer<Calendar>() {
                @Override
                public void onChanged(Calendar calendar) {


                    String da = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                    String mon = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                    String ye = String.valueOf(calendar.get(Calendar.YEAR));
                    day.setText(da);
                    month.setText(mon);
                    year.setText(ye);


                }
            });


            return view1;

        }


        if (formField.getFieldId() != 0) {

            layoutID = formField.getFieldId();

        }

        View view = inflater.inflate(layoutID, new LinearLayout(context), true);


        TextView headerTextView = view.findViewById(R.id.form_header_text);

        ImageView header_icon = view.findViewById(R.id.field_header_icon);


        if (!hasHeader) {

            view.findViewById(R.id.header_layout).setVisibility(View.GONE);


        }


        if (hasHeader) {
            headerTextView.setText(formField.getHeaderText());

            if(!isRequired){

                view.findViewById(R.id.is_required).setVisibility(View.INVISIBLE);


            } else {

                view.findViewById(R.id.is_required).setVisibility(View.VISIBLE);

            }

        }


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


                case TIME:
                    if (hasHeader) {
                        header_icon.setImageResource(R.drawable.ic_calendar);
                    }

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


                case SELECTION:

                    if (hasHeader) {
                        header_icon.setImageResource(R.drawable.ic_reload);

                    }

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
                            if (s != null && s.length() > 0) {
                                ((TextView) formFieldView).setText(s);
                            }
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
