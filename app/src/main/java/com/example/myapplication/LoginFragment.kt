package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.authpage.db.DbManager
import com.example.myapplication.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loginButton?.setOnClickListener {
            val email = binding?.loginEmail?.text.toString()
            val password = binding?.loginPassword?.text.toString()
            val handler = context?.let { context -> DbManager(context) }
            handler?.openDb()
            val data = handler?.getUserData(email)
            val passwordFromDb = data?.get(2)
            val usernameFromDb = data?.get(0)
            var bundle = Bundle()
            bundle.putString("username",usernameFromDb)
            bundle.putString("email",email)
            if (password == passwordFromDb)
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment,bundle)
            else
                Toast.makeText(
                    context,
                    "Username or password is incorrect",
                    Toast.LENGTH_SHORT
                ).show()
            handler?.closeDb()
        }
    }

}