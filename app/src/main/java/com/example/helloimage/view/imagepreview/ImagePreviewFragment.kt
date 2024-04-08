package com.example.helloimage.view.imagepreview

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.helloimage.R
import com.example.helloimage.model.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ImagePreviewFragment : Fragment() {

    companion object {
        fun newInstance() = ImagePreviewFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_image_preview, container, false)

        // Capture and set the greeting text from the view model
        val greetingText = view.findViewById<TextView>(R.id.greeting_text);
        if (viewModel.name.value == null || viewModel.name.value == "") {
            greetingText.text = "Your Profile Image Below"
        } else {
            greetingText.text = buildString {
                append("Hello ")
                append(viewModel.name.value)
                append("!")
            };
        }



        // When the image URI changes, update the image view
        viewModel.imageUri.observe(viewLifecycleOwner) { item ->
            view.findViewById<ImageView>(R.id.imageView).setImageURI(item);
        }

        // Pick media activity + listener
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel =  ViewModelProvider(requireActivity()).get(SharedViewModel::class.java);
                viewModel.setImage(uri)
                viewModel.setName("")
            } else {
                Log.d("PhotoPicker", "No media selected")
                viewModel.setName("")
            }

        }

        // Launch the pick media activity on button press
        view.findViewById<FloatingActionButton>(R.id.selectImageFab).setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        return view;
    }
}