package com.example.pj4test

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
    lateinit var num_person_view: TextView
    lateinit var person_view: TextView
    lateinit var up_arrow: TextView
    lateinit var down_arrow: TextView
    lateinit var register_layout: ScrollView
    lateinit var linear_layout: LinearLayout
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
            linear_layout.removeAllViews()
            for (i: Int in 0 until n) {
                linear_layout.addView(inputs[i])
            }
            num_person_view.text = n.toString()
        }
        if (n == 1) {
            person_view.text = "person"
        } else {
            person_view.text = "people"
        }
    }

    fun addListeners() {
        val context = this
        button = findViewById<View>(R.id.register_button) as Button
        button.setOnClickListener {
            val intent = Intent(context, InUseActivity::class.java)
            startActivity(intent)
        }

        num_person_view = findViewById<View>(R.id.register_textview_2) as TextView
        person_view = findViewById<View>(R.id.register_textview_3) as TextView
        register_layout = findViewById<View>(R.id.register_layout_2) as ScrollView
        linear_layout = findViewById<View>(R.id.register_linear_layout) as LinearLayout
        for (i: Int in 0..7) {
            inputs[i] = linear_layout.getChildAt(i) as TextInputLayout
        }
        up_arrow = findViewById<View>(R.id.register_up_arrow) as TextView
        up_arrow.setOnClickListener {
            val num_person = num_person_view.text.toString().toInt() + 1
            if (num_person < 9) {
                updateLayout(num_person)
            }
        }
        down_arrow = findViewById<View>(R.id.register_down_arrow) as TextView
        down_arrow.setOnClickListener {
            val num_person = num_person_view.text.toString().toInt() - 1
            if (num_person > 0) {
                updateLayout(num_person)
            }
        }
    }
}