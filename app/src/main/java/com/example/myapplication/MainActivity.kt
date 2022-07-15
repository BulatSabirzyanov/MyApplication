package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    private lateinit var  registration_layout:Button
    private lateinit var login_layout:Button
    private lateinit var home_layout:LinearLayout
    private lateinit var registration:Button
    private lateinit var login:Button
    private  lateinit var save:Button
    private  lateinit var login_button:Button
    private lateinit var profile_layout:Button

    private lateinit var handler: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registration_layout = findViewById(R.id.registration_layout)
        login_layout = findViewById(R.id.login_layout)
        home_layout = findViewById(R.id.home_ll)
        registration = findViewById(R.id.registration)
        login = findViewById(R.id.login)
        handler = DatabaseHelper(this)
        login_button = findViewById(R.id.login_button)

        showHome()

        registration.setOnClickListener{
            showRegistration()
        }


        login.setOnClickListener{
            showLogIN()
        }
        save.setOnClickListener{
            val name = findViewById<EditText>(R.id.registr_name)
            val email = findViewById<EditText>(R.id.registr_email)
            val password = findViewById<EditText>(R.id.login_password)
            handler.insertUserData(name.text.toString(),email.text.toString(),password.text.toString())
            showHome()
        }

        login_button.setOnClickListener{
            val email = findViewById<EditText>(R.id.login_email)
            val password = findViewById<EditText>(R.id.login_password)
            if(handler.userPresent(email.text.toString(),password.text.toString()))
                Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this,"Username or password is incorrect",Toast.LENGTH_SHORT).show()

        }
    }
    private fun showRegistration(){
        registration_layout.visibility = View.VISIBLE
        login_layout.visibility = View.GONE
        home_layout.visibility = View.GONE
        profile_layout.visibility = View.GONE
    }
    private fun showLogIN(){
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.VISIBLE
        home_layout.visibility = View.GONE
        profile_layout.visibility = View.GONE
    }
    private fun showHome(){
        registration_layout.visibility = View.GONE
        login_layout.visibility = View.GONE
        home_layout.visibility = View.VISIBLE
        profile_layout.visibility = View.GONE
    }
    private fun showProfile(){
        registration_layout.visibility=View.GONE
        login_layout.visibility=View.GONE
        home_layout.visibility=View.GONE
        profile_layout.visibility = View.VISIBLE
    }
}