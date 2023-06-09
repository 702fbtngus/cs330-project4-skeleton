package com.example.pj4test

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainerView
import java.util.*


class FreeActivity : AppCompatActivity() {
    private val TAG = "FreeActivity"
    lateinit var buttonHistory: Button
    lateinit var buttonRegister: Button
    lateinit var buttonClearHistory: Button
    lateinit var historyClose: Button
    lateinit var historyFragment: FragmentContainerView
    lateinit var historyLinearLayout: LinearLayout

    var mySharedPref: MySharedPref = MySharedPref(this)


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_free)
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
        updateHistory()
        updateHistoryFragment()
    }


    fun addListenerOnButton() {
        val context = this
        buttonHistory.setOnClickListener {
            historyFragment.visibility = View.VISIBLE
            Log.d(TAG, "infoclose: " + historyClose.isClickable.toString())
            buttonHistory.isClickable = false
            buttonRegister.isClickable = false
        }
        buttonRegister.setOnClickListener {
            val intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun makeWarningSeatPage() {
        val intent = Intent(this, WarningActivity::class.java)
        intent.putExtra("status", 0)
        startActivity(intent)
    }

    fun historyFragmentCreated() {
        buttonHistory = findViewById<View>(R.id.free_button_1) as Button
        buttonRegister = findViewById<View>(R.id.free_button_2) as Button
        buttonClearHistory = findViewById<View>(R.id.button_clear_history) as Button
        historyClose = findViewById<View>(R.id.history_close) as Button
        historyFragment = findViewById<View>(R.id.historyFragmentContainerView) as FragmentContainerView
        historyLinearLayout = findViewById<View>(R.id.historyLinearLayout) as LinearLayout
        addListenerOnButton()
        val numUsing = mySharedPref.getInt("num_using")

        Log.d("numUsing", numUsing.toString())
        val nums = mySharedPref.getString("student_nums").split(",")
        Log.d(TAG, "infoclose set")
        historyClose.setOnClickListener {
            Log.d(TAG, "infoclose clicked")
            historyFragment.visibility = View.INVISIBLE
            buttonHistory.isClickable = true
            buttonRegister.isClickable = true
        }
        buttonClearHistory.setOnClickListener {
            mySharedPref.setString("history", "")
            mySharedPref.setString("student_nums", "")
            mySharedPref.setString("result", "")
            updateHistoryFragment()
        }
    }

    fun updateHistory() {
        val history = mySharedPref.getString("history")
        val studentNums = mySharedPref.getString("student_nums")
        val result = mySharedPref.getString("result")
        if (result != "") {
            mySharedPref.setString("history", "$history$studentNums$result;")
        }
    }

    fun updateHistoryFragment() {
        historyLinearLayout.removeAllViews()
        Log.d(TAG, "history: " + mySharedPref.getString("history"))
        val history = mySharedPref.getString("history").split(";")
        for (i: Int in 0 until history.size - 1) {
            View.inflate(historyLinearLayout.context, R.layout.history_team, historyLinearLayout)
            val teamView = historyLinearLayout.getChildAt(i) as ConstraintLayout
            val gridLayout = teamView.findViewById<GridLayout>(R.id.history_team_grid_layout)
            val studentNums = history[i].split(",")
            for (i: Int in 0 until studentNums.size - 1) {
                View.inflate(gridLayout.context, R.layout.history_team_student_num, gridLayout)
                (gridLayout.getChildAt(i) as TextView).text = studentNums[i]
            }
            val resultView = teamView.findViewById<GridLayout>(R.id.history_team_result) as ConstraintLayout
            val result = studentNums[studentNums.size - 1]
            when (result) {
                "returned" -> resultView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.history_returned))
                "vacant" -> resultView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.history_vacant))
                "noisy" -> resultView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.history_noisy))
            }
            (resultView.getChildAt(0) as TextView).text = result
        }
        mySharedPref.setString("result", "")
    }
}