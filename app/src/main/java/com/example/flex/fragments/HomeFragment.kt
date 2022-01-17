package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flex.Food
import com.example.flex.R
import com.example.flex.adapters.RecyclerViewFoodAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewFoodAdapter: RecyclerViewFoodAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerViewFoodAdapter = RecyclerViewFoodAdapter(getData())
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = recyclerViewFoodAdapter


    }

    private fun getData(): List<Food> {

        val list = ArrayList<Food>()

        list.add(
            Food(
                1,
                "https://media-cdn.tripadvisor.com/media/photo-s/04/59/4d/7f/restaurant-burger-royal.jpg",
                "Royal Burger",
                12
            )
        )

        list.add(
            Food(
                2,
                "https://www.vegrecipesofindia.com/wp-content/uploads/2020/11/pizza-recipe-2-500x500.jpg",
                "Pizza",
                14
            )
        )
        list.add(
            Food(
                3,
                "https://gemrielia.ge/media/__sized__/images/kartofili-fri-crop-c0-5__0-5-450x301-70.jpg",
                "French fries",
                5
            )
        )

        list.add(
            Food(
                4,
                "https://www.cfacdn.com/img/order/COM/Menu_Refresh/Drinks/Drinks%20PDP/_0000s_0022_Feed_Menu_0000_Drinks_Coca-cola.png",
                "Coca-cola",
                3
            )
        )
        list.add(
            Food(
                5,
                "https://www.foodrepublic.com/wp-content/uploads/2012/03/033_FR11785.jpg",
                "Cheeseburger",
                6
            )
        )
        return list
    }

}