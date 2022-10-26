package com.samad_talukder.androidsqlitecrudexample.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samad_talukder.androidsqlitecrudexample.AddOrEditActivity
import com.samad_talukder.androidsqlitecrudexample.databinding.ItemFavouriteBinding
import com.samad_talukder.androidsqlitecrudexample.db.model.FavouriteEntity

class FavouriteItemAdapter(
    favList: List<FavouriteEntity>,
    private var context: Context
) :
    RecyclerView.Adapter<FavouriteItemAdapter.FavouriteViewHolder>() {

    private var favList: List<FavouriteEntity> = ArrayList()

    init {
        this.favList = favList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = ItemFavouriteBinding.inflate(LayoutInflater.from(context), parent, false)

        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bindData(favList[position], position)
    }

    override fun getItemCount(): Int {
        return favList.size
    }

    inner class FavouriteViewHolder(private val view: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bindData(fav: FavouriteEntity, position: Int) {
            view.tvName.text = fav.title
            view.tvDesc.text = fav.description

            view.listItem.setOnClickListener {
                val i = Intent(context, AddOrEditActivity::class.java)
                i.putExtra("operation", "Edit")
                i.putExtra("Id", position + 1)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(i)
            }
        }

    }
}