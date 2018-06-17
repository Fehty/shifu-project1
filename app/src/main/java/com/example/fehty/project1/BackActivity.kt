package com.example.fehty.project1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_back.*

class BackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back)

        buttonBack.setOnClickListener {
            val intent = Intent(this@BackActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}