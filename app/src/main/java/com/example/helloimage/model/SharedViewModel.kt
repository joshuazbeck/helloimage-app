package com.example.helloimage.model

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _imageUri = MutableLiveData<Uri?>(null)
    val imageUri: LiveData<Uri?> get() = _imageUri;
    fun setImage(uri: Uri) {
        _imageUri.value = uri
    }

    private val _name = MutableLiveData<String?>(null);
    val name: LiveData<String?> get() = _name;
    fun setName(name: String) {
        _name.value = name;
    }

}