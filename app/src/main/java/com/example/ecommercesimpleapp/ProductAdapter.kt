package com.example.ecommercesimpleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val listOfProducts : List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewholder>(){

    class ProductViewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val productImg = itemView.findViewById<ImageView>(R.id.productImg)
        val productName = itemView.findViewById<TextView>(R.id.productName)
        val productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        val productDesc = itemView.findViewById<TextView>(R.id.productDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product,parent,false)
        return ProductViewholder(view)
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holder: ProductViewholder, position: Int) {
        val currentProduct = listOfProducts[position]
        holder.productName.text = currentProduct.ProductName
        holder.productDesc.text= currentProduct.ProductDesc
        holder.productPrice.text = currentProduct.ProductPrice
    }
}