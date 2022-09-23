package com.example.project2_wishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var items = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val itemsRv = findViewById<RecyclerView>(R.id.ItemsRv)
        val nameEdit = findViewById<EditText>(R.id.NameEdit)
        val priceEdit = findViewById<EditText>(R.id.PriceEdit)
        val urlEdit = findViewById<EditText>(R.id.URLEdit)
        val adapter =ItemAdapter(items)
        itemsRv.adapter = adapter
        itemsRv.layoutManager = LinearLayoutManager(this)
        fun closeKeyBoard() {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        findViewById<Button>(R.id.SubmitButton).setOnClickListener {
            val name = nameEdit.text.toString()
            var price = priceEdit.text.toString()
            val url = urlEdit.text.toString()
            if (name.isNotEmpty() && price.isNotEmpty() && url.isNotEmpty()) {
                closeKeyBoard()
                price = String.format("%.2f",price.toFloat())
                nameEdit.text.clear()
                priceEdit.text.clear()
                urlEdit.text.clear()
                items.add(Item(name, price, url))
                adapter.notifyDataSetChanged()
            }
            else {
                Toast.makeText(this, "All Field Must Be Filled.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}