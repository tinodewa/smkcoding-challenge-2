package com.smkcoding.hamurchef

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smkcoding.hamurchef.pref.Preferences
import com.smkcoding.hamurchef.utils.tampilToast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val CUSTOM_PREF_NAME = "User_data"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences: Preferences = Preferences(this)
        login_btn.setOnClickListener {
//            Preferences.setLog
            val username = login_name.editableText.toString()
            val password = login_password.editableText.toString()
//            sharedPreferences.saveName(, username)
//            sharedPreferences.savePass("userpass", password)
            tampilToast(this, "Data Tersimpan")
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
}

