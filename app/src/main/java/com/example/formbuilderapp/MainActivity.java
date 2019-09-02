package com.example.formbuilderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.formbuilder.FormBuilder;
import com.example.formbuilder.FormField;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout linearLayout = findViewById(R.id.myLinear);


        final FormBuilder formBuilder = new FormBuilder(this, this,
                linearLayout, getSupportFragmentManager());


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("again");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("again");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("again");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("again");



        formBuilder.addField("Text", FormField.Type.TEXT, true);
        formBuilder.addField("Number", FormField.Type.NUMBER, true);
        formBuilder.addField("Date", FormField.Type.DATE, true);
        formBuilder.addField("Time", FormField.Type.TIME, true);


        formBuilder.addSelectionField("Selection", FormField.Type.SELECTION, true, list);
        formBuilder.addSelectionField("MultipleSelection", FormField.Type.MULTIPLE_SELECTION, true, list);
        formBuilder.addField("URL", FormField.Type.URL, false);

        formBuilder.build();


        Button button = findViewById(R.id.validate_btn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                formBuilder.getmAwesomeValidation();

            }
        });


    }
}
