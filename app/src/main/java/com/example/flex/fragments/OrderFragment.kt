package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flex.Food
import com.example.flex.R
import com.example.flex.adapters.RecyclerViewFoodAdapter
import com.example.flex.adapters.RecyclerViewOrderAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class OrderFragment : Fragment(R.layout.fragment_order) {

    private lateinit var db:DatabaseReference
    private lateinit var recyclerView2: RecyclerView
    private lateinit var foodArrayList: ArrayList<Food>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        recyclerView2 = view.findViewById(R.id.recyclerView2)
        foodArrayList = arrayListOf<Food>()
        recyclerView2.setHasFixedSize(true)
        getFoodData()


}
    private fun getFoodData() {



        val auth = FirebaseAuth.getInstance()
        val db =  FirebaseDatabase.getInstance().getReference("Food").child(auth.currentUser?.uid!!)
        db.addValueEventListener(object :ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (bookSnapshot in snapshot.children) {

                        val food = bookSnapshot.getValue(Food::class.java)
                        foodArrayList.add(food!!)


                    }



                    recyclerView2.adapter = RecyclerViewOrderAdapter(foodArrayList)
                    recyclerView2.layoutManager = LinearLayoutManager(context)


                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }


}
