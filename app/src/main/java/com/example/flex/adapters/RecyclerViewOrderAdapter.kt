package com.example.flex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flex.Food
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RecyclerViewOrderAdapter(private val list: List<Food>) :
    RecyclerView.Adapter<RecyclerViewOrderAdapter.FoodViewHolder>() {

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView
        val nameTextView: TextView
        val priceTextView: TextView
        val buttonRemove : Button



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

            buttonRemove.setOnClickListener{
                val title = food.title
                val url = food.imageUrl
                val id = food.id
                val price = food.price



                val foodInfo = Food(id,url,title,price)
                val auth = FirebaseAuth.getInstance()
                val db = FirebaseDatabase.getInstance().getReference("Orders")
                db.child(auth.currentUser?.uid!!).child(id.toString()).removeValue()



            }




        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return FoodViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = list[position]
        holder.setData(food)

    }

    override fun getItemCount() = list.size
}