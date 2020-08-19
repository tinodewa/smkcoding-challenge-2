package com.smkcoding.hamurchef

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.google_sign_in
import kotlinx.android.synthetic.main.activity_sign_in.progressBar
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignInActivity : AppCompatActivity() {

    val CUSTOM_PREF_NAME = "User_data"
    private val RC_SIGN_IN = 1
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()
        progressBar.visibility = View.GONE

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        //Cek user has login or not
        onStart()

        btn_sign_in.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            processSignIn(login_email.text.toString(), login_password.text.toString())
        }

        google_sign_in.setOnClickListener {
            processGoogleSignIn()
        }

        sign_up_cta.setOnClickListener {
            signUpIntent()
        }

        forget_password_cta.setOnClickListener {
            forgetPasswordIntent()
        }
    }

    public override fun onStart() {
        super.onStart()
        if (auth.currentUser == null) {
        } else {
            //Jika sudah login langsung dilempar ke MainActivity
            intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
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

    private fun processGoogleSignIn() {
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

    private fun processSignIn(email: String, password: String) {

        if (email.contains("@")) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Sign In success, intent to home screen
                        Log.d("success", "Sign In with email:success")
                        Toast.makeText(this, "Welcome back to the kitchen.", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        //If sign in fails, display a message to the user
                        progressBar.visibility = View.GONE
                        Log.w("error", "Sign In User With Email:failure", task.exception)
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

    private fun signUpIntent() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun forgetPasswordIntent() {
        val intent = Intent(this, ForgetPasswordActivity::class.java)
        startActivity(intent)
    }

//    private fun validateEmail() {
//        if (login_email.text.toString().isEmpty()) {
//            input_email_layout.error = "Enter your email address!"
//        } else {
//            input_email_layout.isErrorEnabled = false
//        }
//    }
//
//    private fun validatePassword() {
//        if (login_password.text.toString().isEmpty()) {
//            input_password_layout.error = "Enter your password!"
//        } else {
//            input_password_layout.isErrorEnabled = false
//        }
//    }
}

