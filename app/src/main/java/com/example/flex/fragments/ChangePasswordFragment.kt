package com.example.flex.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordFragment : Fragment(R.layout.fragment_changepassword) {

    private lateinit var editTextNewPassword: EditText
    private lateinit var buttonSetNewPassword: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        editTextNewPassword = view.findViewById(R.id.editTextNewPassword)
        buttonSetNewPassword = view.findViewById(R.id.buttonSetNewPassword)

        editTextNewPassword.setOnClickListener {
            val newPassword = editTextNewPassword.text.toString()

            if (newPassword.isEmpty()) {
                Toast.makeText(requireActivity(), "Password shouldn't be empty ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            } else if (newPassword.length < 6) {
                Toast.makeText(requireActivity(), "Password must be more than 6 symbols", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } 
            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireActivity(), "Password has succesfully changed!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}
