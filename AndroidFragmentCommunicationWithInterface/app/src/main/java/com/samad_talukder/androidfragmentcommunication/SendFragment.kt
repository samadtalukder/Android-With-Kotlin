package com.samad_talukder.androidfragmentcommunication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.samad_talukder.androidfragmentcommunication.callbacks.FragmentOnClickListener
import kotlinx.android.synthetic.main.fragment_send.*
import kotlinx.android.synthetic.main.fragment_send.view.*
import java.lang.ClassCastException


class SendFragment : Fragment() {

    private lateinit var fragmentOnClickListener: FragmentOnClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send, container, false)

        val btnSend = view.findViewById<Button>(R.id.btnSend)


        btnSend.setOnClickListener {
            if (editTextName.text.isEmpty()) {
                return@setOnClickListener
            }
            fragmentOnClickListener.fragmentOnClick(editTextName.text.trim().toString())

        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        fragmentOnClickListener = activity as FragmentOnClickListener

    }


}