package com.example.pj4test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.media.MediaPlayer
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class WarningActivity : AppCompatActivity() {
    private val TAG = "WarningActivity"
    var background: TextView? = null
    var mediaPlayer: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent.hasExtra("status")) {
            val status = intent.getIntExtra("status", -1)
            if (status == 0) setContentView(R.layout.activity_warning_seat)
            if (status == 1) setContentView(R.layout.activity_warning_pin)
        }
        addListenerOnView()
    }

    fun addListenerOnView() {
        mediaPlayer = MediaPlayer.create(this, R.raw.clock_tick);
        mediaPlayer?.isLooping = true;
        mediaPlayer?.start();
        val context = this
        background = findViewById<View>(R.id.warning_background) as TextView
        background!!.setOnClickListener {
            mediaPlayer?.stop()
            finish()
        }
    }
}