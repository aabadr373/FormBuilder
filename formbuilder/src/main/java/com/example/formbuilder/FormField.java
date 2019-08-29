package com.example.formbuilder;


import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
public class FormField {


    public enum Type {
        TEXT, EMAIL, PASSWORD, PHONE, NUMBER, URL, SPINNER, ZIP,
        SELECTION, MULTIPLE_SELECTION, DATE, TIME
    }


    private String tag;
    private int fieldId;
    private Type type;
    private String title;
    private String value;
    private String hint;
    private List<String> options; // list of options for single and multi picker
    private List<String> optionsSelected; // list of selected options for single and multi picker
    private boolean isRequired;
    private boolean isEnabled;
    private String dateFormat;
    private String timeFormat;
    private String dateTimeFormat;
    private String errorMessage;
    private LinearLayout.LayoutParams params;


    public FormField(String tag, Type type) {
        this.tag = tag;
        this.type = type;
        isEnabled = true;
        isRequired = true;
        dateFormat = "dd-MMM-yyyy";
        timeFormat = "HH:mm";
        dateTimeFormat = "ddMMyyyy HH:mm:ss";
        options = new ArrayList<String>();
        optionsSelected = new ArrayList<String>();

    }



    public FormField(String tag, Type type, boolean isRequired) {
        this.tag = tag;
        this.type = type;
        isEnabled = true;
        this.isRequired = isRequired;
        dateFormat = "ddMMyyyy";
        timeFormat = "HH:mm:ss";
        dateTimeFormat = "ddMMyyyy HH:mm:ss";
        options = new ArrayList<String>();
        optionsSelected = new ArrayList<String>();

    }



    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getOptionsSelected() {
        return optionsSelected;
    }

    public void setOptionsSelected(List<String> optionsSelected) {
        this.optionsSelected = optionsSelected;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public LinearLayout.LayoutParams getParams() {
        return params;
    }

    public void setParams(LinearLayout.LayoutParams params) {
        this.params = params;
    }


    public int getCheckedValue() {
        if (optionsSelected.size() > 0) {
            String element = optionsSelected.get(0);
            if(options.contains(element)) {
                return options.indexOf(element);
            }
        }
        return 0;
    }

    public boolean [] getCheckedValues() {
        boolean [] booleans = new boolean[options.size()];
        for (int i = 0; i < options.size(); i++) {
            String element = options.get(i);
            booleans[i] = optionsSelected.contains(element);
        }
        return booleans;
    }


}
