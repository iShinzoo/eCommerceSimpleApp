package com.example.ecommercesimpleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rv : RecyclerView
    private lateinit var ProductAdapter : ProductAdapter
    var listOfProducts = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv)

        val p1 = Product(
            ProductName = "Asics Shoes",
            ProductPrice = "Rs 17999",
            ProductDesc = "This is Comfortable running Shoes by Asics",
            ProductImg = ""
        )
        val p2 = Product(
            ProductName = "Asics Shoes",
            ProductPrice = "Rs 17999",
            ProductDesc = "This is Comfortable running Shoes by Asics",
            ProductImg = ""
        )
        val p3 = Product(
            ProductName = "Asics Shoes",
            ProductPrice = "Rs 17999",
            ProductDesc = "This is Comfortable running Shoes by Asics",
            ProductImg = ""
        )

        listOfProducts.add(p1)
        listOfProducts.add(p2)
        listOfProducts.add(p3)

        ProductAdapter = ProductAdapter(listOfProducts)
        rv.adapter = ProductAdapter
        rv.layoutManager = GridLayoutManager(this,2)
    }
}