package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flex.Food
import com.example.flex.R
import com.example.flex.adapters.RecyclerViewFoodAdapter
import com.example.flex.adapters.RecyclerViewOrderAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var db: DatabaseReference
    private lateinit var recyclerView2: RecyclerView
    private lateinit var foodArrayList: ArrayList<Food>
    private lateinit var orderButton: Button
    private var foodPosition = 0
    private var totalSum = 0
    private lateinit var textView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView2 = view.findViewById(R.id.recyclerView2)
        foodArrayList = arrayListOf<Food>()
        recyclerView2.setHasFixedSize(true)
        getFoodData()
        textView = view.findViewById(R.id.totalSum)
        orderButton = view.findViewById(R.id.orderButton)

        orderButton.setOnClickListener {
            checkout(foodPosition)

        }


    }

    private fun getFoodData() {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance().getReference("Food").child(auth.currentUser?.uid!!)
        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (foodSnapshot in snapshot.children) {

                        val food = foodSnapshot.getValue(Food::class.java)
                        foodArrayList.add(food!!)
                    }
                    recyclerView2.adapter = RecyclerViewOrderAdapter(foodArrayList) {
                        foodPosition = it
                        removeFood(foodPosition)
                    }
                    recyclerView2.layoutManager = LinearLayoutManager(context)

                    textView.text = sumFood().toString()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


    fun sumFood(): Int {
        totalSum = 0
        if (foodArrayList.isNotEmpty()) {
            for (index in foodArrayList.indices) {
                totalSum += foodArrayList[index].price

            }
        }
        return totalSum
    }

    fun removeFood(position: Int) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance().getReference("Food")
        db.child(auth.currentUser?.uid!!).child(foodArrayList[position].id.toString())
            .removeValue()

        foodArrayList.removeAt(position)
        recyclerView2.adapter?.notifyDataSetChanged()
    }

    fun checkout(position: Int) {
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance().getReference("Food")
        db.child(auth.currentUser?.uid!!).child(foodArrayList[position].id.toString())
            .removeValue()

        foodArrayList.clear()
        recyclerView2.adapter?.notifyDataSetChanged()
        Toast.makeText(requireContext(), "გამაიწერა ჯო", Toast.LENGTH_SHORT).show()
    }

}
