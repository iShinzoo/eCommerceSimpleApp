package com.example.ecommercesimpleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class loginActivity : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button
    private lateinit var mauth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPass)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        mauth = Firebase.auth

        btnLogin.setOnClickListener(){
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            login(email,password)
        }

        btnSignup.setOnClickListener(){
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            signup(email,password)
        }

    }
    private fun login(email : String, password : String){
        mauth.signInWithEmailAndPassword(email , password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(
                        Intent(this,uploadProductActivity::class.java)
                    )
                    Toast.makeText(this, "logined Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun signup(email : String, password : String){
        mauth.createUserWithEmailAndPassword(email , password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(
                        Intent(this,uploadProductActivity::class.java)
                    )
                    Toast.makeText(this, "account created Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Some Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
}