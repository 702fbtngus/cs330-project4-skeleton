package com.example.pj4test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import java.util.*


class VacantActivity : AppCompatActivity() {
    private val TAG = "VacantActivity"
    var buttonInfo: Button? = null
    var buttonPIN: Button? = null
    var infoFragment: FragmentContainerView? = null
    var infoClose: Button? = null
    var pinFragment: FragmentContainerView? = null
    var pinClose: Button? = null
    var studentNums: MutableList<TextView?> = MutableList(8) { i: Int -> null }
    var vacantStartTime: Long = 0
    var vacantTimer: TextView? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vacant)
        infoFragment = findViewById<View>(R.id.infoFragmentContainerView) as FragmentContainerView
        pinFragment = findViewById<View>(R.id.pinFragmentContainerView) as FragmentContainerView
        addListenerOnButton()
        infoFragment!!.visibility = View.INVISIBLE
        vacantStartTime = Date().time
    }


    fun addListenerOnButton() {
        val context = this
        buttonInfo = findViewById<View>(R.id.vacant_button_info) as Button
        buttonPIN = findViewById<View>(R.id.vacant_button_pin) as Button
        vacantTimer = findViewById<View>(R.id.vacant_timer) as TextView
        buttonInfo!!.setOnClickListener {
            infoFragment!!.visibility = View.VISIBLE
            Log.d(TAG, "infoclose: " + infoClose!!.isClickable.toString())
            buttonInfo!!.isClickable = false
            buttonPIN!!.isClickable = false
        }
        buttonPIN!!.setOnClickListener {
            pinFragment!!.visibility = View.VISIBLE
            Log.d(TAG, "infoclose: " + infoClose!!.isClickable.toString())
            buttonInfo!!.isClickable = false
            buttonPIN!!.isClickable = false
        }
    }

    fun infoFragmentCreated() {
        infoClose = findViewById<View>(R.id.info_close) as Button
        val infoLinearLayout = findViewById<View>(R.id.infoLinearLayout) as LinearLayout
        val sharedPref = this.getSharedPreferences("prefs", 0)
        val numUsing = sharedPref.getInt("num_using", 0)!!
        Log.d("numUsing", numUsing.toString())
        val nums = sharedPref.getString("student_nums", "")!!.split(",")
        for (i: Int in 0 until 8) {
            studentNums[i] = infoLinearLayout.getChildAt(i+1) as TextView
            if (i < numUsing) {
                studentNums[i]!!.text = nums[i]
                studentNums[i]!!.visibility = View.VISIBLE
            } else {
                studentNums[i]!!.visibility = View.GONE
            }
        }
        Log.d(TAG, "infoclose set")
        infoClose!!.setOnClickListener {
            Log.d(TAG, "infoclose clicked")
            infoFragment!!.visibility = View.INVISIBLE
            buttonInfo!!.isClickable = true
            buttonPIN!!.isClickable = true
        }
    }

    fun pinFragmentCreated() {
        pinClose = findViewById<View>(R.id.pin_close) as Button
//        val infoLinearLayout = findViewById<View>(R.id.infoLinearLayout) as LinearLayout
//        val sharedPref = this.getSharedPreferences("prefs", 0)
//        val numUsing = sharedPref.getInt("num_using", 0)!!
//        Log.d("numUsing", numUsing.toString())
//        val nums = sharedPref.getString("student_nums", "")!!.split(",")
//        for (i: Int in 0 until 8) {
//            studentNums[i] = infoLinearLayout.getChildAt(i+1) as TextView
//            if (i < numUsing) {
//                studentNums[i]!!.text = nums[i]
//                studentNums[i]!!.visibility = View.VISIBLE
//            } else {
//                studentNums[i]!!.visibility = View.GONE
//            }
//        }
//        Log.d(TAG, "infoclose set")
        pinClose!!.setOnClickListener {
            Log.d(TAG, "infoclose clicked")
            pinFragment!!.visibility = View.INVISIBLE
            buttonInfo!!.isClickable = true
            buttonPIN!!.isClickable = true
        }
    }

    fun updateTime() {
        val lastTime = 900 - ((Date().time - vacantStartTime)/1000).toInt()
        vacantTimer!!.text = "%s:%s".format((lastTime/60).toString().padStart(2, '0'), (lastTime%60).toString().padStart(2, '0'))
    }

    fun makeWarningPINPage() {
        val intent = Intent(this, WarningActivity::class.java)
        intent.putExtra("status", 1)
        startActivity(intent)
    }
}