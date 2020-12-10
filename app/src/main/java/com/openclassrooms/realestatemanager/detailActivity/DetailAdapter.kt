package com.openclassrooms.realestatemanager.detailActivity

import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.model.Photo

class DetailAdapter(
        private var photoList: List<Photo>,
        private val listener: (Photo) -> Unit
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {



    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_detail, parent, false)
        return ViewHolder(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        Log.e("size", "" + photoList.size)
        return photoList.size

    }

    fun setProperties(photo: List<Photo>) {
        this.photoList = photo
        notifyDataSetChanged()
    }


    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(photoList[position])
        val item = photoList[position]
        holder.itemView.setOnClickListener {
            listener(item)

        }
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Photo) {
            val ivPhoto = itemView.findViewById(R.id.iv_item_detail) as ImageView
            val etText = itemView.findViewById(R.id.tv_item_detail) as TextView
            val resources: Resources = itemView.resources
            Log.e("listSize", "" + item.name)


            etText.text = item.name

            if (item.urlPhoto.contains("images")) {
                Glide.with(itemView.context)
                        .load(item.urlPhoto)
                        .fitCenter()
                        .into(ivPhoto)

            } else {
                // this because drawable in my fakePropertyApi
                val photo = resources.getIdentifier(item.urlPhoto, "drawable", "")
                Glide.with(itemView.context)
                        .load(photo)
                        .fitCenter()
                        .into(ivPhoto)
            }

        }


    }

}