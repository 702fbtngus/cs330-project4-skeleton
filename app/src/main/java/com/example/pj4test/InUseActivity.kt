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


class InUseActivity : AppCompatActivity() {
    private val TAG = "InUseActivity"
    var button1: Button? = null
    var button2: Button? = null
    var infoFragment: FragmentContainerView? = null
    var infoClose: Button? = null
    var studentNums: MutableList<TextView?> = MutableList(8) { i: Int -> null }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_use)
        infoFragment = findViewById<View>(R.id.infoFragmentContainerView) as FragmentContainerView
        addListenerOnButton()
        infoFragment!!.visibility = View.INVISIBLE
    }


    fun addListenerOnButton() {
        val context = this
        button1 = findViewById<View>(R.id.in_use_button_1) as Button
        button2 = findViewById<View>(R.id.in_use_button_2) as Button
        button1!!.setOnClickListener {
            infoFragment!!.visibility = View.VISIBLE
            Log.d(TAG, "infoclose: " + infoClose!!.isClickable.toString())
            button1!!.isClickable = false
            button2!!.isClickable = false
        }
        button2!!.setOnClickListener {
//            val intent = Intent(context, FreeActivity::class.java)
//            startActivity(intent)
            finish()
        }
    }

    fun switchPage(status: Int) {
        when (status) {
            0 -> {
                val intent = Intent(this, VacantActivity::class.java)
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this, OutActivity::class.java)
                intent.putExtra("cause", 0)
                startActivity(intent)
                finish()
            }
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
            button1!!.isClickable = true
            button2!!.isClickable = true
        }
    }
}