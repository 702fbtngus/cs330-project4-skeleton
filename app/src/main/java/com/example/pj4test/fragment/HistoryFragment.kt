package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pj4test.FreeActivity
import com.example.pj4test.databinding.FragmentHistoryBinding

class HistoryFragment: Fragment() {
    private val TAG = "HistoryFragment"

    private var _fragmentHistoryBinding: FragmentHistoryBinding? = null

    private val fragmentHistoryBinding
        get() = _fragmentHistoryBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false)
        return fragmentHistoryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as FreeActivity).historyFragmentCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}