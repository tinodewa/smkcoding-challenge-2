package com.smkcoding.hamurchef

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.smkcoding.hamurchef.pref.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    val CUSTOM_PREF_NAME = "User_data"
    private var auth: FirebaseAuth? = null
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        supportActionBar?.hide()
        progressBar.visibility = View.GONE

        val sharedPreferences: Preferences = Preferences(this)
//        login_btn.setOnClickListener {
//            Preferences.setLog
//            val username = login_name.editableText.toString()
//            val password = login_password.editableText.toString()
//            sharedPreferences.saveName(, username)
//            sharedPreferences.savePass("userpass", password)
//            tampilToast(this, "Data Tersimpan")
//        }

        google_sign_in.setOnClickListener(this)
        auth = FirebaseAuth.getInstance() //Mendapakan Instance Firebase Auth

        //Cek apakah sudah login atau belum
        if (auth!!.currentUser == null) {
        } else {
            //Jika sudah login langsung dilempar ke MainActivity
            intent = Intent(applicationContext, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        sign_up_text.setOnClickListener{
            signUpIntent()
        }

        forget_password_text.setOnClickListener {
            ForgetPasswordIntent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //RC_SIGN_IN = kode permintaan yang diberikan ke startActivityForResult
        if (requestCode == RC_SIGN_IN) {
            //Jika berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                //jika gagal
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_LONG).show()
            }
        }
    }
//    override fun onClick(v: View?) {
//        val pref = customPrefrence(this, CUSTOM_PREF_NAME)
//        when (v?.id) {
//            R.id.login_btn -> {
//                pref.userName = login_name.text.toString()
//                pref.password = login_password.text.toString()
//            }
//            R.id.btnClear -> {
//                prefs.clearValues
//
//            }
//            R.id.btnShow -> {
//                inUserId.setText(prefs.userId.toString())
//                inPassword.setText(prefs.password)
//            }
//            R.id.btnShowDefault -> {
//
//                val defaultPrefs = defaultPreference(this)
//                inUserId.setText(defaultPrefs.userId.toString())
//                inPassword.setText(defaultPrefs.password)
//            }
//        }
//    }

    private fun signUpIntent() {
        intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun ForgetPasswordIntent() {
        intent = Intent(this, ForgetPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun validateEmail() {
        if(input_email.text.toString().isEmpty()) {
            input_email_layout.error = "Enter your email address"
        } else {
            input_email_layout.isErrorEnabled = false
        }
    }

    private fun validatePassword() {
        if(input_password.text.toString().isEmpty()) {
            input_password_layout.error = "Enter your password"
        } else {
            input_password_layout.isErrorEnabled = false
        }
    }

    override fun onClick(v: View?) {
        //Statement program untuk login/masuk
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)
        progressBar.visibility = View.VISIBLE
    }
}

