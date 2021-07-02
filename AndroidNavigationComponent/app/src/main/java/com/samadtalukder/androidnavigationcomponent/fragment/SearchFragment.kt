package com.samadtalukder.androidnavigationcomponent.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.samadtalukder.androidnavigationcomponent.R
import com.samadtalukder.androidnavigationcomponent.data.SearchModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var receiveText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveText = requireArguments().getString("receive_data")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.search_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_button).setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.search_btn -> {
                if (!TextUtils.isEmpty(text_input.text.toString())) {

                    val searchText = SearchModel(input = text_input.text.toString())
                    val bundle: Bundle = bundleOf(
                        "search_data" to searchText,
                        "receive_data" to receiveText
                        )

                    navController.navigate(
                        R.id.action_searchFragment_to_recentFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(context, "Enter Something", Toast.LENGTH_SHORT).show()
                }

            }
            R.id.cancel_button -> requireActivity().onBackPressed()
        }
    }

}