package com.example.formbuilder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ValidationError extends DialogFragment {
    private String errorMessage;


    public ValidationError() {
    }

    public static ValidationError newInstance(String error) {
        ValidationError f = new ValidationError();


        Bundle args = new Bundle();
        args.putString("error", error);
        f.setArguments(args);

        return f;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        errorMessage = "Check fields";

        if (getArguments() != null) {
            errorMessage = getArguments().getString("error");
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_dialog_validation_error, container, false);

        TextView textView = v.findViewById(R.id.error_message_validation);
        Button button = v.findViewById(R.id.btn_dismiss_error_dialog);


        textView.setText(errorMessage);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return v;
    }
}
