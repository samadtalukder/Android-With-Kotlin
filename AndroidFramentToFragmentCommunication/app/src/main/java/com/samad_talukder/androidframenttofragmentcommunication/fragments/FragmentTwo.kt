package com.samad_talukder.androidframenttofragmentcommunication.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.samad_talukder.androidframenttofragmentcommunication.R
import com.samad_talukder.androidframenttofragmentcommunication.callbacks.FragmentOnClickListener


class FragmentTwo : Fragment() {

    private var message = ""
    private lateinit var fragmentOnClickListener: FragmentOnClickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        message = requireArguments().getString("bundle_message")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        val showText = view.findViewById<TextView>(R.id.tvShowText)
        showText.text = message
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentOnClickListener = activity as FragmentOnClickListener
    }

}