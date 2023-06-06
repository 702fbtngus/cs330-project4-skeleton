package com.example.pj4test

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*


class InUseActivity : AppCompatActivity() {
    private val TAG = "InUseActivity"
    var button: Button? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_use)
        addListenerOnButton()
    }


    fun addListenerOnButton() {
        val context = this
        button = findViewById<View>(R.id.in_use_button_2) as Button
        button!!.setOnClickListener {
            val intent = Intent(context, FreeActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }
}