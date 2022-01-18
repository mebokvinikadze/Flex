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
                "https://static.wikia.nocookie.net/ronaldmcdonald/images/f/fc/Royal_McChicken.png/revision/latest/scale-to-width-down/250?cb=20160108163801",
                "Royal Burger",
                9
            )
        )

        list.add(
            Food(
                2,
                "https://www.seekpng.com/png/full/68-689158_pizza-png-high-quality-image-png-promoes-de.png",
                "Pizza",
                11
            )
        )
        list.add(
            Food(
                3,
                "https://www.pngall.com/wp-content/uploads/4/French-Fries-PNG-Image-1.png",
                "French fries",
                4
            )
        )

        list.add(
            Food(
                4,
                "https://www.cfacdn.com/img/order/COM/Menu_Refresh/Drinks/Drinks%20PDP/_0000s_0022_Feed_Menu_0000_Drinks_Coca-cola.png",
                "Coca-cola",
                2
            )
        )
        list.add(
            Food(
                5,
                "https://upload.wikimedia.org/wikipedia/commons/1/11/Cheeseburger.png",
                "Cheeseburger",
                6
            )
        )
        list.add(
            Food(
                6,
                "https://purepng.com/public/uploads/large/purepng.com-fresh-applefoodsweettastyhealthyfruitappleleaf-981524677946vfurf.png",
                "Apple",
                2
            )
        )

        list.add(
            Food(
                7,
                "https://cdn.picpng.com/cucumber/painting-cucumber-28148.png",
                "Shusha Kitri",
                2
            )
        )

        list.add(
            Food(
                9,
                "https://www.seekpng.com/png/full/237-2372204_gold-nugget-png-chicken-nuggets-four-chicken-breast.png",
                "Nuggets",
                8
            )
        )
        list.add(
            Food(
                10,
                "https://www.pngall.com/wp-content/uploads/11/Ketchup-PNG-Clipart.png",
                "Ketchup",
                2
            )
        )


        return list
    }

}