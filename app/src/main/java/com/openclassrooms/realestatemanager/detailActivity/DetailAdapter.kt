package com.openclassrooms.realestatemanager.detailActivity

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.R
import kotlinx.android.synthetic.main.item_rv_detail.view.*

class DetailAdapter(
        val imageList: ArrayList<Image>,
        val context: Context,
        private val listener: (Image) -> Unit
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_rv_detail, parent, false)
        return ViewHolder(v)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return imageList.size
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(imageList[position])
        val item = imageList[position]
        holder.itemView.setOnClickListener { listener(item)

            val dImageView : Int = item.image

            val intent = Intent(context, FullScreenActivity::class.java)
            intent.putExtra("iImage",dImageView)
            context.startActivity(intent)

        }

    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Image) {
            val ivPhoto = itemView.findViewById(R.id.iv_item_detail) as ImageView
            val etText = itemView.findViewById(R.id.tv_item_detail) as TextView

            etText.text = item.text
            Glide.with(ivPhoto).load(item.image).into(itemView.iv_item_detail)


        }

    }
}