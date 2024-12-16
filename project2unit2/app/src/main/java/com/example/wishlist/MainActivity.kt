package com.example.wishlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your list and adapter
        val wishlistItems = mutableListOf<WishlistItem>()
        val wishlistAdapter = WishlistAdapter(wishlistItems)

        // Set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = wishlistAdapter

        // Initialize your EditText and Button
        val editItemName = findViewById<EditText>(R.id.editItemName)
        val editItemPrice = findViewById<EditText>(R.id.editItemPrice)
        val editItemURL = findViewById<EditText>(R.id.editItemURL)
        val btnAddItem = findViewById<MaterialButton>(R.id.btnAddItem)

        // Set the OnClickListener for clearing text in EditTexts
        editItemName.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                editItemName.text.clear()
            }
        }

        editItemPrice.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                editItemPrice.text.clear()
            }
        }

        editItemURL.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                editItemURL.text.clear()
            }
        }

        // Button click listener to add items to the wishlist
        btnAddItem.setOnClickListener {
            val name = editItemName.text.toString()
            val price = editItemPrice.text.toString()
            val url = editItemURL.text.toString()

            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                val newItem = WishlistItem(name, price, url)
                wishlistAdapter.addItem(newItem)

                // Clear input fields
                editItemName.text.clear()
                editItemPrice.text.clear()
                editItemURL.text.clear()

                editItemName.hint = "Item Name"
                editItemPrice.hint = "Ex: 5.99"
                editItemURL.hint = "Ex: https://example.example/my-item-url"
            }
        }
    }
}