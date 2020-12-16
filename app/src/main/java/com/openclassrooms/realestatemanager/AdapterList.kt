package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.model.Property
import com.bumptech.glide.request.RequestOptions



class AdapterList(private val listener: (Property) -> Unit)
    : RecyclerView.Adapter<AdapterList.ViewHolder>() {

    private var properties: List<Property> = ArrayList()


    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
    }

    // Returns the total count of items in the list
    override fun getItemCount() = properties.size



    fun setProperties(properties: List<Property>) {
        this.properties = properties
        notifyDataSetChanged()
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(properties[position])
        val item = properties[position]
        holder.itemView.setOnClickListener {
            listener(item)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(item: Property) {
            val photoCover = itemView.findViewById(R.id.iv_item_list) as ImageView
            val type = itemView.findViewById(R.id.tv_type_item_list) as TextView
            val price = itemView.findViewById(R.id.tv_price_item_list) as TextView
            val address = itemView.findViewById(R.id.tv_city_item_list) as TextView
            val dateSoldOut = itemView.findViewById(R.id.tv_date_sold_out_item_list) as TextView
            val nbrBed = itemView.findViewById(R.id.tv_nbr_bed_item_list) as TextView
            val nbrBath = itemView.findViewById(R.id.Tv_nbr_bath_item_list) as TextView
            val resources: Resources = itemView.resources

            type.text = item.type
            price.text = item.price + "$"
            address.text = item.address + " " + item.city
            dateSoldOut.text = item.dateSale
            nbrBed.text = item.nbrBedRoom.toString()
            nbrBath.text = item.nbrBathRoom.toString()


            if(item.photoCover.contains("images")){
            Glide.with(itemView.context)
                    .load(item.photoCover)
                    .centerCrop()
                    .into(photoCover)

        }else{
            // this because drawable in my fakePropertyApi
            val photo = resources.getIdentifier(item.photoCover,"drawable","")
            Glide.with(itemView.context)
                    .load(photo)
                    .centerCrop()
                    .into(photoCover)
        }

    }

    }
}



