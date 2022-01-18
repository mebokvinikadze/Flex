package com.example.flex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flex.Food
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RecyclerViewOrderAdapter(private val list: List<Food>, val onClickDelete: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerViewOrderAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView
        val nameTextView: TextView
        val priceTextView: TextView
        val buttonRemove: Button

        init {
            imageView = itemView.findViewById(R.id.imageViewFood2)
            nameTextView = itemView.findViewById(R.id.textViewFoodName2)
            priceTextView = itemView.findViewById(R.id.textViewPrice2)
            buttonRemove = itemView.findViewById(R.id.buttonRemove)
        }

        fun setData(food: Food) {
            Glide.with(itemView.context)
                .load(food.imageUrl)
                .into(imageView)
            nameTextView.text = food.title
            priceTextView.text = food.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.setData(list[position])
        holder.buttonRemove.setOnClickListener {
            onClickDelete(position)
        }
    }

    override fun getItemCount() = list.size
}