package com.smkcoding.hamurchef

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.progressBar


class SignUpActivity : AppCompatActivity() {

    val CUSTOM_PREF_NAME = "User_data"
    private val RC_SIGN_IN = 1
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()
        progressBar.visibility = View.GONE

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btn_sign_up.setOnClickListener{
            progressBar.visibility = View.VISIBLE
            processSignUp(signup_email.text.toString(), signup_password.text.toString())
        }
        google_sign_up.setOnClickListener{
            ProcessGoogleSignUp()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //RC_SIGN_IN = kode permintaan yang diberikan ke startActivityForResult
        if (requestCode == RC_SIGN_IN) {
            //Jika berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                //jika gagal
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun ProcessGoogleSignUp() {
        //Statement program for login with google
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN
        )
        progressBar.visibility = View.VISIBLE
    }

    private fun processSignUp(email: String, password: String) {

        if (email.contains("@")) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Sign in success, update UI with the signed-in user's information
                        Log.d("Success", "createUserWithEmail:success")
                        Toast.makeText(this, "Welcome to the kitchen.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                        finishAffinity()
                    } else {
                        //If sign in fails, display a message to the user
                        progressBar.visibility = View.GONE
                        Log.w("error", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Sign Up failed, please try again!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            progressBar.visibility = View.GONE
            Toast.makeText(
                this,
                "Please make sure you enter correct email address!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}
