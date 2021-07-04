package com.samad_talukder.androidframenttofragmentcommunication.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.samad_talukder.androidframenttofragmentcommunication.R
import com.samad_talukder.androidframenttofragmentcommunication.callbacks.FragmentOnClickListener


class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentOnClickListener: FragmentOnClickListener
    var spinnerValue = ""

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val languages = resources.getStringArray(R.array.language)

        val spinner = view.findViewById<Spinner>(R.id.spinnerLanguage)
        val sendBtnOne = view.findViewById<Button>(R.id.sendBtnOne)
        val sendBtnTwo = view.findViewById<Button>(R.id.sendBtnTwo)

        val fragmentOne = FragmentTwo()

        if (spinner != null) {
            val spinnerAdapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                languages
            )
            spinner.adapter = spinnerAdapter

        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                spinnerValue = languages[position]
                Log.e("", spinnerValue)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        sendBtnOne.setOnClickListener(this)

        return view
    }


    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.sendBtnOne -> fragmentOnClickListener.inflateFragment("FragmentOne", spinnerValue)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentOnClickListener = activity as FragmentOnClickListener
    }


}