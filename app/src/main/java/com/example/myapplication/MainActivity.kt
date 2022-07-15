package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class MainActivity : AppCompatActivity() {
    private lateinit var  registration_layout:LinearLayout
    private lateinit var login_layout:LinearLayout
    private lateinit var home_layout:LinearLayout
    private lateinit var profile_layout:LinearLayout
    private lateinit var registration:Button
    private lateinit var login_button_on_home:Button
    private  lateinit var save:Button
    private  lateinit var login_button_on_login:Button


    private lateinit var handler: Databasemanager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registration_layout = findViewById(R.id.registration_layout)
        login_layout = findViewById(R.id.login_layout)
        home_layout = findViewById(R.id.home_ll)
        profile_layout = findViewById(R.id.profile_layout)
        registration = findViewById(R.id.registration_button)
        save = findViewById(R.id.save_button)
        login_button_on_home = findViewById(R.id.login_button_in_home_layout)
        login_button_on_login = findViewById(R.id.login_button_in_login_layout)
        val profileUsername = findViewById<TextView>(R.id.usernametextView)
        val profileEmail = findViewById<TextView>(R.id.emailtextView)
        val profileName = findViewById<TextView>(R.id.nametextView)
        val profileSurname = findViewById<TextView>(R.id.surnametextView)

        handler = Databasemanager(this)


        showHome()

        registration.setOnClickListener{
            showRegistration()
        }


        login_button_on_home.setOnClickListener{
            showLogIN()
        }
        save.setOnClickListener{
            val username = findViewById<EditText>(R.id.registr_username)
            val name = findViewById<EditText>(R.id.registr_name)
            val surname = findViewById<EditText>(R.id.registr_surname)
            val email = findViewById<EditText>(R.id.registr_email)
            val password = findViewById<EditText>(R.id.login_password)

            if (isValidPassword(password.text.toString())) {
                if (cheakinglogin(email.text.toString())) {
                    handler.createUser(
                        username.text.toString(),
                        name.text.toString(),
                        surname.text.toString(),
                        email.text.toString(),
                        password.text.toString()
                    )
                    handler.closeDb()
                    showLogIN()
                }
                else
                    Toast.makeText(this,"Username or password is incorrect",Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Username or password is incorrect",Toast.LENGTH_SHORT).show()
        }

        login_button_on_login.setOnClickListener{
            val email = findViewById<EditText>(R.id.login_email)
            val password = findViewById<EditText>(R.id.login_password)
            val data = handler.getUserData(email.text.toString())
            val password_from_db = data[6]
            if (password.text.toString() == password_from_db)
                showProfile()
            else
                Toast.makeText(this,"Username or password is incorrect",Toast.LENGTH_SHORT).show()
        }
    }
    private fun cheakinglogin(email:String) : Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    internal fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
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