package com.example.formbuilderapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.formbuilder.FormBuilder
import com.example.formbuilder.FormField
import java.util.ArrayList

class TestFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_test, container, false)

        //Dummy list
        val list = ArrayList<String>()
        list.add("option 1")
        list.add("option 2")
        list.add("option 3")
        list.add("option 4")
        list.add("option 5")
        list.add("option 6")
        list.add("option 7")
        list.add("no more options")






        // LinearLayout for wrapping form
        val linearLayout = view.findViewById<LinearLayout>(R.id.myLinear)

        /*
        LifecycleOwner is passed to livedata to update form fields onchange,
        FragmentManager for validation warning dialog
        */

        val formBuilder = FormBuilder(this, activity,
                linearLayout, fragmentManager)




        formBuilder.addField("select_visa_holder", FormField.Type.SELECTION, "Visa Holder", true, list)
        formBuilder.addField("select_app_type", FormField.Type.SELECTION, "Application Type", true, list)
        formBuilder.addField("visa_number", FormField.Type.NUMBER, "Visa Number", true)
        formBuilder.addField("date_issue", FormField.Type.DATE, "Issue Date", true)
        formBuilder.addField("date_expiry", FormField.Type.DATE, "Expiry Date", true)
        formBuilder.addField("select_issue_place", FormField.Type.SELECTION, "Issue Place", true, list)
        formBuilder.addField("uinified_number", FormField.Type.NUMBER, "Unified Number", false)


        formBuilder.build()


        val button = view.findViewById<Button>(R.id.validate_btn)


        button.setOnClickListener(View.OnClickListener {
            if (!formBuilder.getmAwesomeValidation()) {

                formBuilder.clearErrors()


            } else {


                Toast.makeText(activity, "Form is valid", Toast.LENGTH_SHORT).show()
            }
        })


        return view
    }
}