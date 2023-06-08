package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pj4test.InUseActivity
import com.example.pj4test.VacantActivity
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentInfoVacantBinding

class VacantInfoFragment: Fragment(){
    private val TAG = "VacantInfoFragment"

    private var _fragmentInfoVacantBinding: FragmentInfoVacantBinding? = null

    private val fragmentInfoVacantBinding
        get() = _fragmentInfoVacantBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentInfoVacantBinding = FragmentInfoVacantBinding.inflate(inflater, container, false)
        return fragmentInfoVacantBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as VacantActivity).infoFragmentCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}