package com.example.fehty.project1.MenuFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.Activity.MainActivity
import com.example.fehty.project1.R
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("ValidFragment")
class ProfileFragment(private val mainActivity: MainActivity?) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            exitFromAdditionItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exitFromAdditionItems() {
        mainActivity!!.floatingActionButton.show()
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        fragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.container, ListFragment())
                ?.commit()
    }
}