package com.example.fehty.project1.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fehty.project1.R
import gr.net.maroulis.library.EasySplashScreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = EasySplashScreen(this@SplashActivity)
                .withFullScreen()
                .withTargetActivity(MainActivity::class.java)
                .withSplashTimeOut(2000)
                .withLogo(R.drawable.unicorn)
                .create()

        setContentView(config)
    }
}