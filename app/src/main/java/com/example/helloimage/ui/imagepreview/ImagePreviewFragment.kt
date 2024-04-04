package com.example.helloimage.ui.imagepreview

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.helloimage.R
import com.example.helloimage.ui.SharedViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.observeOn

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
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_image_preview, container, false)
        val greetingText = view.findViewById<TextView>(R.id.greeting_text);
        greetingText.text = buildString {
            append("Hello ")
            append(viewModel.name.value)
            append("!")
        };
        viewModel.imageUri.observe(viewLifecycleOwner) { item ->
            view.findViewById<ImageView>(R.id.imageView).setImageURI(item);
        }
        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                viewModel.setImage(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }

        }

        view.findViewById<FloatingActionButton>(R.id.selectImageFab).setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        return view;
    }
}