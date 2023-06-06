package com.example.pj4test

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // permissions
    private val permissions = arrayOf(RECORD_AUDIO, CAMERA)
    private val PERMISSIONS_REQUEST = 0x0000001;

    private val remainMinutesTextView: TextView by lazy {
        findViewById(R.id.TimerMinView)
    }
    private val remainSecondsTextView: TextView by lazy {
        findViewById(R.id.TimerSecView)
    }
    private var currentCountDownTimer: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermissions() // check permissions
        bindViews()
    }

    private fun checkPermissions() {
        if (permissions.all{ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
            Log.d(TAG, "All Permission Granted")
        }
        else{
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }

    private fun setRemainTime(remainMillis: Long) {
        val remainSec = remainMillis/1000
        remainMinutesTextView.text = "%02d".format(remainSec/60)
        remainSecondsTextView.text = "%02d".format(remainSec%60)
    }

    private fun createCountDownTimer(initialMillis: Long) : CountDownTimer =
        object : CountDownTimer(initialMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                setRemainTime(millisUntilFinished)
            }
            override fun onFinish() {
                setRemainTime(0)
            }
        }

    private fun bindViews() {
        remainMinutesTextView.setOnEditorActionListener(
            object: TextView.OnEditorActionListener {
                override fun onEditorAction(remainMinutesTextView: TextView?) {
                    currentCountDownTimer?.cancel()
                    currentCountDownTimer = null
                }
                override fun onStopTrackingTouch(remainMinutesTextView: TextView?) {
                    currentCountDownTimer = createCountDownTimer(30000)
                    currentCountDownTimer?.start()
                }
            }
        )
    }
}