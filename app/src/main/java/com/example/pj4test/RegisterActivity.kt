package com.example.pj4test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity"
    lateinit var button: Button
    lateinit var numPersonView: TextView
    lateinit var personView: TextView
    lateinit var upArrow: TextView
    lateinit var downArrow: TextView
    lateinit var registerLayout: ScrollView
    lateinit var linearLayout: LinearLayout
    lateinit var pinInput: TextInputLayout
    var mySharedPref: MySharedPref = MySharedPref(this)
    var inputs: MutableList<TextInputLayout?> = MutableList(8) { i: Int -> null }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        addListeners()
        updateLayout(1)
    }

    fun updateLayout(n: Int) {
        if (n in 1..8) {
            linearLayout.removeAllViews()
            for (i: Int in 0 until n) {
                linearLayout.addView(inputs[i])
            }
            numPersonView.text = n.toString()
        }
        if (n == 1) {
            personView.text = "person"
        } else {
            personView.text = "people"
        }
    }

    fun addListeners() {
        val context = this
        button = findViewById<View>(R.id.register_button) as Button
        button.setOnClickListener {
//            val sharedPref = this.getSharedPreferences("prefs", 0)
//            val editor = sharedPref.edit()
            val numPerson = numPersonView.text.toString().toInt()
//            editor.putInt("num_using", numPerson).apply()
            mySharedPref.setInt("num_using", numPerson)
            var snum = ""
            for (i: Int in 0 until numPerson) {
                snum += inputs[i]!!.editText!!.text.toString()
                snum += ","
            }
//            editor.putString("student_nums", snum).apply()
            mySharedPref.setString("student_nums", snum)
            Log.d(TAG, "snum: " + snum)
            val pin = pinInput.editText!!.text.toString()
//            editor.putString("pin", pin).apply()
            mySharedPref.setString("pin", pin)
//            val numUsing = sharedPref.getInt("num_using", 0)!!
            val intent = Intent(context, InUseActivity::class.java)
            startActivity(intent)
            finish()
        }

        numPersonView = findViewById<View>(R.id.register_textview_2) as TextView
        personView = findViewById<View>(R.id.register_textview_3) as TextView
        registerLayout = findViewById<View>(R.id.register_layout_2) as ScrollView
        linearLayout = findViewById<View>(R.id.register_linear_layout) as LinearLayout
        for (i: Int in 0..7) {
            inputs[i] = linearLayout.getChildAt(i) as TextInputLayout
        }
        upArrow = findViewById<View>(R.id.register_up_arrow) as TextView
        upArrow.setOnClickListener {
            val num_person = numPersonView.text.toString().toInt() + 1
            if (num_person < 9) {
                updateLayout(num_person)
            }
        }
        downArrow = findViewById<View>(R.id.register_down_arrow) as TextView
        downArrow.setOnClickListener {
            val num_person = numPersonView.text.toString().toInt() - 1
            if (num_person > 0) {
                updateLayout(num_person)
            }
        }
        pinInput = findViewById<View>(R.id.pin_input) as TextInputLayout
    }

    fun makeWarningSeatPage() {
        val intent = Intent(this, WarningActivity::class.java)
        intent.putExtra("status", 0)
        startActivity(intent)
    }
}