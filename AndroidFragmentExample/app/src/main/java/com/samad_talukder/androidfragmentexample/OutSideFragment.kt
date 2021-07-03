package com.samad_talukder.androidfragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class OutSideFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_outside, container, false)

        val sendBtn = view.findViewById<Button>(R.id.send_btn)
        sendBtn.setOnClickListener {
            // get fragment instance
            val insideFragment = InsideFragment()
            // get child fragment manager instance
            val childFragment = childFragmentManager
            // begin the fragment transition using child fragment manager
            val transaction = childFragment.beginTransaction()

            transaction.replace(R.id.contain_inside_fragment, insideFragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

        return view
    }

}