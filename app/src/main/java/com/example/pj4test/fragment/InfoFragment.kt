package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.pj4test.InUseActivity
import com.example.pj4test.ProjectConfiguration
import com.example.pj4test.R
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentInfoBinding

class InfoFragment: Fragment(), SnapClassifier.DetectorListener {
    private val TAG = "InfoFragment"
    var infoClose: TextView? = null

    private var _fragmentInfoBinding: FragmentInfoBinding? = null

    private val fragmentInfoBinding
        get() = _fragmentInfoBinding!!

    // views
    lateinit var ampView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentInfoBinding = FragmentInfoBinding.inflate(inflater, container, false)
        return fragmentInfoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as InUseActivity).fragmentCreated()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onResults(score: Float, db: Int) {
//        activity?.runOnUiThread {
//            ampView.text = "%3s ".format(db)
////            Log.d("result", "dB: ${ampView.text}")
//            if (db > 20) {
//                ampView.setTextColor(ProjectConfiguration.activeTextColor)
//            } else {
//                ampView.setTextColor(ProjectConfiguration.idleTextColor)
//            }
//        }
    }
}