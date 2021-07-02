package com.samadtalukder.androidnavigationcomponent.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.samadtalukder.androidnavigationcomponent.R
import com.samadtalukder.androidnavigationcomponent.data.SearchModel


class RecentFragment : Fragment() {

    lateinit var searchModel: SearchModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchModel = requireArguments().getParcelable("search_data")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchText = searchModel.input

        view.findViewById<TextView>(R.id.show_search_text).text = searchText
    }
}