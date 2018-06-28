package com.example.fehty.project1.Activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.fehty.project1.MenuFragments.AddItemFragment
import com.example.fehty.project1.MenuFragments.ListFragment
import com.example.fehty.project1.MenuFragments.ProfileFragment
import com.example.fehty.project1.R
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)

        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)

        replaceThisFragmentTo(ListFragment())

        floatingActionButton.setOnClickListener { replaceThisFragmentTo(AddItemFragment()) }

        profileIcon.setOnClickListener { replaceThisFragmentTo(ProfileFragment(this)) }
    }

    private fun replaceThisFragmentTo(fragment: Fragment) {
        if (fragment == ListFragment()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            floatingActionButton.show()
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            floatingActionButton.hide()
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}