package com.samad_talukder.androidframenttofragmentcommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.samad_talukder.androidframenttofragmentcommunication.callbacks.FragmentOnClickListener
import com.samad_talukder.androidframenttofragmentcommunication.fragments.MainFragment
import com.samad_talukder.androidframenttofragmentcommunication.fragments.FragmentTwo

class MainActivity : AppCompatActivity(), FragmentOnClickListener {
    private val fragmentOne = FragmentTwo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentMain = MainFragment()


        replaceFragmentOnFragmentContainerView(fragmentMain, "MainFragment", true,"")


    }

    private fun replaceFragmentOnFragmentContainerView(
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean,
        message: String
    ) {
        val supportManager = supportFragmentManager
        val transaction = supportManager.beginTransaction()

        transaction.replace(R.id.fragmentContainer, fragment, tag)

        if (message != "") {
            val bundle = Bundle()
            bundle.putString("bundle_message", message)
            fragment.arguments = bundle
        }
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }

        transaction.commit()

    }

    override fun inflateFragment(tag: String, message: String) {
        when (tag) {
            "FragmentOne" -> replaceFragmentOnFragmentContainerView(fragmentOne, tag, true, message)
        }
    }


}