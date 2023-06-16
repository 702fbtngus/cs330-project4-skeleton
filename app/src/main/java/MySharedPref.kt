package com.example.pj4test

import android.content.Context

class MySharedPref(context: Context) {
    var mContext: Context = context

    public final fun getInt(name: String): Int {
        return mContext.getSharedPreferences("prefs", 0).getInt(name, 0)
    }
    public final fun getString(name: String): String {
        return mContext.getSharedPreferences("prefs", 0).getString(name, "")!!
    }
    public final fun setInt(name: String, i: Int) {
        val sharedPref = mContext.getSharedPreferences("prefs", 0)
        val editor = sharedPref.edit()
        editor.putInt(name, i).apply()
    }
    public final fun setString(name: String, str: String) {
        val sharedPref = mContext.getSharedPreferences("prefs", 0)
        val editor = sharedPref.edit()
        editor.putString(name, str).apply()
    }
}
