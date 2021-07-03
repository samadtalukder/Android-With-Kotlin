package com.samad_talukder.androidfragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set fragment instance
        val fragmentOne = OutSideFragment()
        val insideFragment = InsideFragment()



        send_btn.setOnClickListener {
            replaceFragment(fragmentOne,"parent")
        }

        inside_btn.setOnClickListener {
            replaceFragment(insideFragment,"child")

        }


    }

    private fun replaceFragment(fragment: Fragment,tag:String) {
        // get child fragment manager instance
        val supportManager = supportFragmentManager
        // set fragment transition using support fragment manager
        val transaction = supportManager.beginTransaction()
        // replace remove the existing fragments and adds a new fragment
        transaction.replace(R.id.main_fragment, fragment,tag)
        // back to previous fragment
        transaction.addToBackStack(tag)
        // finishing transition
        transaction.commit()
    }

    private fun addFragment(fragment: Fragment,tag:String) {
        // get child fragment manager instance
        val supportManager = supportFragmentManager
        // set fragment transition using support fragment manager
        val transaction = supportManager.beginTransaction()
        // adding fragments of top of the previous fragments in the container
        transaction.add(R.id.main_fragment, fragment,tag)
        // back to previous fragment
        //transaction.addToBackStack(tag)
        // finishing transition
        transaction.commit()
    }
}