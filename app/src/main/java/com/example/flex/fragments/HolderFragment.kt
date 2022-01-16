package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flex.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HolderFragment : Fragment(R.layout.fragment_holder) {

    private lateinit var navController : NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val navView = view.findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val host = childFragmentManager.findFragmentById(R.id.nav_host_fragment_2) as NavHostFragment
        navController = host.findNavController()


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.profileFragment,
                R.id.orderFragment,
            )

        )

        setupActionBarWithNavController(requireActivity() as AppCompatActivity, navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }


}