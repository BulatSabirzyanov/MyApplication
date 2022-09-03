package com.example.myapplication

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authpage.db.DbManager
import com.example.myapplication.databinding.FragmentRegistrationBinding
import java.util.regex.Pattern

class RegistrationFragment : Fragment() {
    private var binding: FragmentRegistrationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.saveButton?.setOnClickListener {
            val username = binding?.registrUsername?.text.toString()
            val email = binding?.registrEmail?.text.toString()
            val password = binding?.registrPassword?.text.toString()

            val handler = context?.let { context -> DbManager(context) }
            handler?.openDb()

            if (isValidPassword(password)) {
                if (checkLogin(email)) {
                    handler?.addUser(
                        username,
                        email,
                        password
                    )
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                } else
                    Toast.makeText(context, "Username is incorrect", Toast.LENGTH_SHORT)
                        .show()
            } else Toast.makeText(context, "Password is incorrect", Toast.LENGTH_SHORT).show()
            handler?.closeDb()
        }
    }

    private fun checkLogin(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$"
        return !TextUtils.isEmpty(password) && Pattern.compile(passwordPattern).matcher(password)
            .matches()
    }
}