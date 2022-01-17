package com.example.flex.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flex.R
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var editTextEmailRegister: EditText
    private lateinit var editTextPasswordRegister: EditText
    private lateinit var editTextRepeatPasswordRegister: EditText
    private lateinit var buttonRegister: Button



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonRegister = view.findViewById(R.id.buttonRegister)
        editTextEmailRegister = view.findViewById(R.id.editTextEmailRegister)
        editTextPasswordRegister = view.findViewById(R.id.editTextPasswordRegister)
        editTextRepeatPasswordRegister = view.findViewById(R.id.editTextRepeatPasswordRegister)

        buttonRegister.setOnClickListener {
            if (validateInput()) {

                val email = editTextEmailRegister.text.toString()
                val password = editTextPasswordRegister.text.toString()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Toast.makeText(requireActivity(), "Error!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    }
    fun validateInput(): Boolean {

        // checking if email isn't empty
        if (editTextEmailRegister.text.toString() == "") {
            editTextEmailRegister.error = "Please Enter Email"
            return false
        }

        // checking if password isn't empty
        if (editTextEmailRegister.text.toString() == "") {
            editTextEmailRegister.error = "Please Enter Password"
            return false
        }

        // checking if password isn't empty
        if (editTextRepeatPasswordRegister.text.toString() == "") {
            editTextRepeatPasswordRegister.error = "Repeat Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(editTextEmailRegister.text.toString())) {
            editTextEmailRegister.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (editTextPasswordRegister.text.length < 6) {
            editTextPasswordRegister.error = "Password Length must be more than " + 6 + "characters"
            return false
        }

        // password must contain
        if (!isValidPassword(editTextPasswordRegister.text.toString())) {
            editTextPasswordRegister.error = "Password must contain a-z , 0-9"
            return false
        }

        // Checking if repeat password is the same
        if (editTextRepeatPasswordRegister.text.toString() != editTextPasswordRegister.text.toString()) {
            editTextRepeatPasswordRegister.error = "Password does not match"
            return false
        }
        return true
    }

    // valid email function
    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // extension function to validate password rules/patterns
    fun isValidPassword(password: String?): Boolean {
        password?.let {
            val passwordPattern = "(?=.*[a-z])(?=.*[0-9])"
            val passwordMatcher = Regex(passwordPattern)
            return passwordMatcher.find(password) != null
        }
            ?: return false
    }
}





