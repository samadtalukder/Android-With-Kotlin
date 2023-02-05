package com.samad_talukder.showimagefromurlbyasynctask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.samad_talukder.showimagefromurlbyasynctask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = "https://templatevictory.com/quizapp/uploads/subcategory_1607681571.png"
        loadImage(url, binding.ivShowImage)
    }

    private fun loadImage(url: String, ivShowImage: AppCompatImageView) {
        DownloadImageFromUrl(ivShowImage, this).execute(url)
    }

}