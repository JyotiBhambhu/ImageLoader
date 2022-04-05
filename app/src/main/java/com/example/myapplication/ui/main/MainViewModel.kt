package com.example.myapplication.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel : ViewModel() {

    private val _lvBitmap = MutableLiveData<Bitmap>()
    val lvBitmap: LiveData<Bitmap> = _lvBitmap


    fun downloadBitmap(imageUrl: String){
        viewModelScope.launch(Dispatchers.IO) {
            val conn = URL(imageUrl).openConnection()
            conn.connect()
            val inStream = conn.getInputStream()
            _lvBitmap.postValue(BitmapFactory.decodeStream(inStream))
            inStream.close()
        }
    }
}