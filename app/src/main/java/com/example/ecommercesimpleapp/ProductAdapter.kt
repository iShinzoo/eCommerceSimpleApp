package com.example.ecommercesimpleapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(
    private var listOfProducts : List<Product>,
    private val context : Context

) : RecyclerView.Adapter<ProductAdapter.ProductViewholder>(){

    class ProductViewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var productImg = itemView.findViewById<ImageView>(R.id.productImg)
        var productName = itemView.findViewById<TextView>(R.id.productName)
        var productPrice = itemView.findViewById<TextView>(R.id.productPrice)
        var productDesc = itemView.findViewById<TextView>(R.id.productDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product,parent,false)
        return ProductViewholder(view)
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holder: ProductViewholder, position: Int) {
        var currentProduct = listOfProducts[position]
        holder.productName.text = currentProduct.ProductName
        holder.productDesc.text= currentProduct.ProductDesc
        holder.productPrice.text = currentProduct.ProductPrice
        Glide.with(context)
            .load(currentProduct.ProductImage)
            .into(holder.productImg)
    }
}