package com.example.fehty.project1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gr.net.maroulis.library.EasySplashScreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = EasySplashScreen(this@SplashActivity)
                .withFullScreen()
                .withTargetActivity(MainActivity::class.java)
                .withSplashTimeOut(5000)
                .withLogo(R.drawable.unicorn)
                .create()

        setContentView(config)
    }
}