package com.example.pj4test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
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

    fun updateButton() {
        val num = numPersonView.text!!.toString().toInt()
        for (i: Int in 0 until num) {
            inputs[i] = linearLayout.getChildAt(i) as TextInputLayout
            val et = inputs[i]!!.editText!!
            if (et.text!!.length != 8) {
                button.isEnabled = false
                return
            }
        }
        if (pinInput!!.editText!!.text!!.length != 4) {
            button.isEnabled = false
            return
        }
        button.isEnabled = true
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
            val et = inputs[i]!!.editText!!
            et.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int ) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (et.text!!.isEmpty()) {
                        inputs[i]!!.error = "Please enter student ID"
                    } else if (et.text!!.length != 8) {
                        inputs[i]!!.error = "Student ID should be 8 digits"
                    } else {
                        inputs[i]!!.error = null
                    }
                    updateButton()
                }
            })
        }
        upArrow = findViewById<View>(R.id.register_up_arrow) as TextView
        upArrow.setOnClickListener {
            val num_person = numPersonView.text.toString().toInt() + 1
            if (num_person < 9) {
                updateLayout(num_person)
            }
            updateButton()
        }
        downArrow = findViewById<View>(R.id.register_down_arrow) as TextView
        downArrow.setOnClickListener {
            val num_person = numPersonView.text.toString().toInt() - 1
            if (num_person > 0) {
                updateLayout(num_person)
            }
            updateButton()
        }
        pinInput = findViewById<View>(R.id.pin_input) as TextInputLayout
        val et = pinInput.editText!!
        et.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int ) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (et.text!!.isEmpty()) {
                    pinInput.error = "Please enter PIN"
                } else if (et.text!!.length != 4) {
                    pinInput.error = "PIN should be 4 digits"
                } else {
                    pinInput.error = null
                }
                updateButton()
            }
        })
    }

    fun makeWarningSeatPage() {
        val intent = Intent(this, WarningActivity::class.java)
        intent.putExtra("status", 0)
        startActivity(intent)
    }
}