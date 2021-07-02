package com.samadtalukder.androidnavigationcomponent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.samadtalukder.androidnavigationcomponent.R


class HomeFragment : Fragment(), View.OnClickListener {
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.view_favorite_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.view_search_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.back_stack_btn).setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        // view cannot be null so that's why we use !!
        when (v!!.id) {
            R.id.view_favorite_btn -> navController!!.navigate(R.id.action_homeFragment_to_favoriteFragment)
            R.id.view_search_btn -> navController!!.navigate(R.id.action_homeFragment_to_searchFragment)
            R.id.back_stack_btn -> navController!!.navigate(R.id.action_homeFragment_to_popularFragment)

        }
    }

}