package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pj4test.VacantActivity
import com.example.pj4test.databinding.FragmentInfoVacantBinding
import com.example.pj4test.databinding.FragmentPinVacantBinding

class VacantPinFragment: Fragment(){
    private val TAG = "VacantPinFragment"

    private var _fragmentPinVacantBinding: FragmentPinVacantBinding? = null

    private val fragmentPinVacantBinding
        get() = _fragmentPinVacantBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentPinVacantBinding = FragmentPinVacantBinding.inflate(inflater, container, false)
        return fragmentPinVacantBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as VacantActivity).pinFragmentCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }
}