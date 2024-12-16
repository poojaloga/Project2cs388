package com.example.wishlist

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val items: MutableList<WishlistItem>) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNAME: TextView = itemView.findViewById(R.id.itemName)
        val itemPRICE: TextView = itemView.findViewById(R.id.itemPrice)
        val itemURL: TextView = itemView.findViewById(R.id.itemURL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.wishlist_item, parent, false)
        return WishlistViewHolder(view)

    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val item = items[position]
        holder.itemNAME.text = item.name
        holder.itemPRICE.text = item.price
        holder.itemURL.text = item.url

        holder.itemView.setOnClickListener {
            // Extract the URL from the item
            val url = item.url
            if (url.isNotEmpty()) {
                // Create an Intent to open the URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)

                // Start the activity using the context of the view holder's itemView
                holder.itemView.context.startActivity(intent)
            }
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { dialog, which ->
                    items.removeAt(position)

                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem(item: WishlistItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
}
