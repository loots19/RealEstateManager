package com.openclassrooms.realestatemanager.detailActivity

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R
import com.openclassrooms.realestatemanager.model.Photo
import kotlinx.android.synthetic.main.item_rv_create.view.*

class DetailAdapter(

        private var photoList: ArrayList<Photo>,
        private val listener: (Photo) -> Unit
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_detail, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photoList.size    }

    fun setProperties(photo: ArrayList<Photo>) {
        this.photoList = photo
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(photoList[position])
        val item = photoList[position]
        holder.itemView.setOnClickListener {
            listener(item)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Photo) {
            val ivPhoto = itemView.findViewById(R.id.iv_item_detail) as ImageView
            val etText = itemView.findViewById(R.id.tv_item_detail) as TextView
            val resources: Resources = itemView.resources

            etText.text = item.name

            if (item.urlPhoto.contains("images") || (item.urlPhoto.contains("Pictures"))) {
                Glide.with(itemView.context)
                        .load(item.urlPhoto)
                        .centerCrop()
                        .into(ivPhoto)

            } else {
                // this because drawable in my fakePropertyApi
                val photo = resources.getIdentifier(item.urlPhoto, "drawable", "")
                Glide.with(itemView.context)
                        .load(photo)
                        .centerCrop()
                        .into(ivPhoto)
            }


        }


    }



}