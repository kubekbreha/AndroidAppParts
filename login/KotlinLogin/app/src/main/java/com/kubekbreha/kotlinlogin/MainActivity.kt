package com.kubekbreha.kotlinlogin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mGoogleApiClient: GoogleApiClient? = null
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        val googleSignInOpt = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOpt)
                .build()

        mGoogleApiClient!!.connect()

        act_main_logout.setOnClickListener{
            mAuth!!.signOut()
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback { updateUI() }
        }
    }

    private fun updateUI() {
        val accountIntent = Intent(this, AuthenticationActivity::class.java)
        startActivity(accountIntent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}
