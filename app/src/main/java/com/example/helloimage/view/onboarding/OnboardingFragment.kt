package com.example.helloimage.view.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.helloimage.R
import com.example.helloimage.model.SharedViewModel

class OnboardingFragment : Fragment() {

    companion object {
        fun newInstance() = OnboardingFragment()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Make sure that the viewModel is here due to initialization concerns of accessing the sharedActivity()
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java);
        val view = inflater.inflate(R.layout.fragment_onboarding, container, false)

        // Attach to the text field, listen to changes, and update the view model accordingly
        val textField = view.findViewById<EditText>(R.id.enter_name_field);
        textField.doOnTextChanged { text, _, _, _->
            viewModel.setName(text?.toString() ?: "");
        }

        // Attach the navigation responder to the next button
        val nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_open_image_preview)}

        return view;
    }
}