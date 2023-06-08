package com.example.pj4test.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.pj4test.InUseActivity
import com.example.pj4test.ProjectConfiguration
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.databinding.FragmentAudioBinding

class AudioFragment: Fragment(), SnapClassifier.DetectorListener {
    private val TAG = "AudioFragment"

    private var _fragmentAudioBinding: FragmentAudioBinding? = null

    private val fragmentAudioBinding
        get() = _fragmentAudioBinding!!

    // classifiers
    lateinit var snapClassifier: SnapClassifier

    // views
    lateinit var ampView: TextView
    lateinit var token1View: ImageView
    lateinit var token2View: ImageView
    lateinit var token3View: ImageView
    lateinit var token4View: ImageView
    lateinit var token5View: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("create", "success")
        _fragmentAudioBinding = FragmentAudioBinding.inflate(inflater, container, false)

        return fragmentAudioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ampView = fragmentAudioBinding.AmplitudeView

        token1View = fragmentAudioBinding.Token1View
        token2View = fragmentAudioBinding.Token2View
        token3View = fragmentAudioBinding.Token3View
        token4View = fragmentAudioBinding.Token4View
        token5View = fragmentAudioBinding.Token5View

        snapClassifier = SnapClassifier()
        snapClassifier.initialize(requireContext())
        snapClassifier.setDetectorListener(this)
    }

    override fun onPause() {
        super.onPause()
        snapClassifier.stopInferencing()
    }

    override fun onResume() {
        super.onResume()
        snapClassifier.startInferencing()
    }

    override fun onResults(score: Float, db: Int, token: Int) {
        activity?.runOnUiThread {
            if (db != -20000) {
                ampView.text = "%3s db".format(db)
                Log.d("result", "token: $token")
                when (token) {
                    1 -> {token1View.setColorFilter(ProjectConfiguration.activeColor)
                        (activity as InUseActivity).switchPage(0)}
                    2 -> token2View.setColorFilter(ProjectConfiguration.activeColor)
                    3 -> token3View.setColorFilter(ProjectConfiguration.activeColor)
                    4 -> token4View.setColorFilter(ProjectConfiguration.activeColor)
                    5 -> {
                        token5View.setColorFilter(ProjectConfiguration.activeColor)
//                        (activity as InUseActivity).switchByToken(0)
                    }
                }
            }
        }
    }
}