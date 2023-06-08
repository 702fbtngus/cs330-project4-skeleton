package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pj4test.InUseActivity
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentInfoInUseBinding

class InUseInfoFragment: Fragment(){
    private val TAG = "InUseInfoFragment"

    private var _fragmentInfoInUseBinding: FragmentInfoInUseBinding? = null

    private val fragmentInfoInUseBinding
        get() = _fragmentInfoInUseBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentInfoInUseBinding = FragmentInfoInUseBinding.inflate(inflater, container, false)
        return fragmentInfoInUseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as InUseActivity).infoFragmentCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}