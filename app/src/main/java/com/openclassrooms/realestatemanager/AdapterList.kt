package com.openclassrooms.realestatemanager

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*

class AdapterList(private val context: Context, private val images: List<Image>)
    : RecyclerView.Adapter<AdapterList.ViewHolder>() {


    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = images.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        val contact = images[position]
        holder.bind(contact)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image:Image) {

            Glide.with(context).load(R.drawable.dollar).into(itemView.iv_detail)
        }
    }
}