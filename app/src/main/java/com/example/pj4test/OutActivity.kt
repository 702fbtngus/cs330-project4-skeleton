package com.example.pj4test

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*


class OutActivity : AppCompatActivity() {
    private val TAG = "OutActivity"
    var background: TextView? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(intent.hasExtra("status")) {
            val status = intent.getIntExtra("status", -1)
            if (status == 0) setContentView(R.layout.activity_out_noisy)
            // if (status == 1) setContentView(R.layout.activity_out_vacant)
        }
        addListenerOnView()
    }

    fun addListenerOnView() {
        val context = this
        background = findViewById<View>(R.id.noisy_seat_background) as TextView
        background!!.setOnClickListener {
            val intent = Intent(context, FreeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}