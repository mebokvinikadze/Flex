package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class LoginFragment : Fragment(R.layout.fragment_login) {


    private lateinit var buttonLogin: Button
    private lateinit var editTextPasswordLogin: EditText
    private lateinit var editTextEmailLogin: EditText
    private lateinit var textViewForgetPassword: TextView
    private lateinit var textViewRegisterLogin: TextView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin = view.findViewById(R.id.buttonLogin)
        editTextEmailLogin = view.findViewById(R.id.editTextEmailLogin)
        editTextPasswordLogin = view.findViewById(R.id.editTextPasswordLogin)
        textViewForgetPassword = view.findViewById(R.id.textViewForgetPassword)
        textViewRegisterLogin = view.findViewById(R.id.textViewRegisterLogin)




        buttonLogin.setOnClickListener {

            val email = editTextEmailLogin.text.toString()
            val password = editTextPasswordLogin.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_loginFragment_to_holderFragment)

                } else {

                }
            }




        }

        textViewRegisterLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        textViewForgetPassword.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoveryFragment)
        }




    }


}