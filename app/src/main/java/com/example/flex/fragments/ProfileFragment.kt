package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var buttonLogout: Button
    private lateinit var emailTextView: TextView
    private lateinit var buttonChangePassword: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        buttonLogout = view.findViewById(R.id.buttonLogout)
        emailTextView = view.findViewById(R.id.textView)
        buttonChangePassword = view.findViewById(R.id.buttonChangePassword)

        buttonChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_changePasswordFragment)

        }

        logoutListeners()

        emailTextView.text = FirebaseAuth.getInstance().currentUser?.email
    }


    private fun logoutListeners() {
        buttonLogout.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_mainActivity)

        }


    }
}


