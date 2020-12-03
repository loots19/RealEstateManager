package com.openclassrooms.realestatemanager

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.realestatemanager.model.Property

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
            val photoCover = itemView.findViewById(R.id.iv_detail) as ImageView
            val type = itemView.findViewById(R.id.tv_type_item_detail) as TextView
            val price = itemView.findViewById(R.id.tv_price_item_detail) as TextView
            val address = itemView.findViewById(R.id.tv_city_item_detail) as TextView
            val dateSoldOut = itemView.findViewById(R.id.tv_date_sold_out_item_detail) as TextView

            type.text = item.type
            price.text = item.price
            address.text = item.city

            dateSoldOut.text = item.dateSale
            Log.e("dateAdapter",item.price)




           // Glide.with(itemView.context).load(item.photoCover.toInt()).into(photoCover)


        }

    }
}



