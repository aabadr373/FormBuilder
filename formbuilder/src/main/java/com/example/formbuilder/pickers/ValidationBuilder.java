package com.example.formbuilder.pickers;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.basgeekball.awesomevalidation.utility.custom.CustomErrorReset;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidation;
import com.basgeekball.awesomevalidation.utility.custom.CustomValidationCallback;
import com.example.formbuilder.FormField;
import com.example.formbuilder.R;
import com.example.formbuilder.ValidationError;

public class ValidationBuilder {
    private AwesomeValidation mAwesomeValidation;
    private CustomValidationCallback validationCallback;
    private CustomErrorReset customErrorReset;
    private boolean formError;


    public ValidationBuilder(AwesomeValidation mAwesomeValidation, final FragmentManager fragmentManager) {
        this.mAwesomeValidation = mAwesomeValidation;

        validationCallback = new CustomValidationCallback() {
            @Override
            public void execute(ValidationHolder validationHolder) {

                if (formError) {

                    return;
                }

             ValidationError   validationError = ValidationError.newInstance(validationHolder.getErrMsg());
                validationError.show(fragmentManager, null);
                formError = true;


            }
        };



        customErrorReset = new CustomErrorReset() {
            @Override
            public void reset(ValidationHolder validationHolder) {
                formError = false;
            }
        };




    }

    public void initiate(final FormField formField, View view) {


        if (formField.isRequired()) {


            if (formField.getType() == FormField.Type.DATE) {

                TextView dayTV = view.findViewById(R.id.field_input).findViewById(R.id.day_tv);
                mAwesomeValidation.addValidation(dayTV, new CustomValidation() {
                    @Override
                    public boolean compare(ValidationHolder validationHolder) {


                        return !((TextView) validationHolder.getView()).getText().toString().equals("Day");


                    }
                }, validationCallback, customErrorReset, "Please set Date");


            } else {


                mAwesomeValidation.addValidation(view.findViewById(R.id.field_input),
                        new CustomValidation() {
                            @Override
                            public boolean compare(ValidationHolder validationHolder) {

                                boolean valid;

                                switch (formField.getType()) {


                                    case EMAIL:

                                        valid = ((EditText) validationHolder.getView()).getText().toString().matches(Patterns.EMAIL_ADDRESS.toString());


                                        break;

                                    case PHONE:

                                        valid = ((EditText) validationHolder.getView()).getText().toString().matches(RegexTemplate.TELEPHONE);
                                        break;

                                    case PASSWORD:

                                        String passwordRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#$%^&*()\\-_+={}\\[\\]|;:\"<>,./?]).{8,}";

                                        valid = ((EditText) validationHolder.getView()).getText().toString().matches(passwordRegex);

                                        break;


                                    case TIME:

                                        valid = ((TextView) validationHolder.getView()).getText().toString().matches(RegexTemplate.NOT_EMPTY);


                                        break;

                                    case MULTIPLE_SELECTION:

                                        valid = ((TextView) validationHolder.getView()).getText().toString().matches(RegexTemplate.NOT_EMPTY);
                                        break;

                                    case SELECTION:

                                        valid = ((TextView) validationHolder.getView()).getText().toString().matches(RegexTemplate.NOT_EMPTY);

                                        break;

                                    default:

                                        valid = ((EditText) validationHolder.getView()).getText().toString().matches(RegexTemplate.NOT_EMPTY);

                                        break;

                                }


                                return valid;
                            }
                        }, validationCallback, customErrorReset, "Please set " + formField.getHeaderText());


            }
        }


    }


    public boolean validateForm(){

        return mAwesomeValidation.validate();

    }

    public void clearValidation(){

        mAwesomeValidation.clear();
    }



}
