package com.example.fehty.project1.Activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.fehty.project1.MenuFragments.AddItemFragment
import com.example.fehty.project1.MenuFragments.ListFragment
import com.example.fehty.project1.MenuFragments.ProfileFragment
import com.example.fehty.project1.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolBar)
        replaceThisFragmentTo(ListFragment())

        floatingActionButton.setOnClickListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            floatingActionButton.hide()
            replaceThisFragmentTo(AddItemFragment(this))
        }

        profileIcon.setOnClickListener {
            replaceThisFragmentTo(ProfileFragment())
        }
    }

    private fun replaceThisFragmentTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}