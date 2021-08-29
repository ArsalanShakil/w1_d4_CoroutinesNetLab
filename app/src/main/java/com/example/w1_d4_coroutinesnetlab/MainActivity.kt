package com.example.w1_d4_coroutinesnetlab

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val imgUrl = URL(
                "https://images.pexels.com/photos/730344/" +
                "pexels-photo-730344.jpeg?" +
                        "auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
            )
            lifecycleScope.launch(Dispatchers.Main) {
                val myImg = async(Dispatchers.IO) { getImg(imgUrl) }
                myImg.await()?.let { showRes(it) }
            }
    }

    private suspend fun getImg(url: URL): Bitmap?{
        return try {
            textView.text = url.toString()
            BitmapFactory.decodeStream(url.openStream())
        } catch (e:IOException){
            textView.text = "connect to internet and reload app :)"
            null
        }
    }
    private fun showRes(serverImg: Bitmap){
        imageView.setImageBitmap(serverImg)
    }

}




