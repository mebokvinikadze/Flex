package com.example.flex.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth

class RecoveryFragment : Fragment(R.layout.fragment_recovery) {

    private lateinit var editTextEmailReset: EditText
    private lateinit var buttonResetPassword: Button
    private lateinit var textViewLoginRecovery: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextEmailReset = view.findViewById(R.id.editTextEmailReset)
        buttonResetPassword = view.findViewById(R.id.buttonResetPassword)
        textViewLoginRecovery = view.findViewById(R.id.textViewLoginRecovery)

        buttonResetPassword.setOnClickListener {


            val email = editTextEmailReset.text.toString()
            if (email.isEmpty()) {
                editTextEmailReset.setError("Empty!")
                return@setOnClickListener

            } else if (!isEmailValid(editTextEmailReset.text.toString())) {
                editTextEmailReset.setError("Please Enter Valid Email")
                return@setOnClickListener
            }


            textViewLoginRecovery.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                findNavController().navigate(R.id.action_recoveryFragment_to_loginFragment)

            }


            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            requireActivity(),
                            "Check Your Email!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show()
                    }


                }
        }
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}





