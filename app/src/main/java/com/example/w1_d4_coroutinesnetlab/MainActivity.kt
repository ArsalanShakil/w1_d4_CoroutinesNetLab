package com.example.w1_d4_coroutinesnetlab

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val imgUrl = URL(
                "https://placedog.net/640/480?random"
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




