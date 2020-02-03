package com.samad_talukder.androidrecyclerviewwithkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AndroidVersionAdapter(private val androidVersion: ArrayList<AndroidVersion>) :
    RecyclerView.Adapter<AndroidVersionAdapter.AndroidVersionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndroidVersionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return AndroidVersionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return androidVersion.size
    }

    override fun onBindViewHolder(holder: AndroidVersionViewHolder, position: Int) {

        holder.tvVersionName.text = androidVersion[position].versionName

        holder.tvVersion.text = androidVersion[position].version.toString()

        holder.itemView.setOnClickListener { v: View? ->
            Toast.makeText(
                v?.context,
                "ClickID: $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    class AndroidVersionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvVersionName = itemView.findViewById<TextView>(R.id.tv_android_name)!!
        val tvVersion = itemView.findViewById<TextView>(R.id.tv_android_version)!!

    }


}
