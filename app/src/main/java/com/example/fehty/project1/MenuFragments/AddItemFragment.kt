package com.example.fehty.project1.MenuFragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.fehty.project1.R
import kotlinx.android.synthetic.main.activity_main.*

class AddItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_add_item, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            activity!!.floatingActionButton.show()
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ListFragment())
                    ?.commit()
        }
        return super.onOptionsItemSelected(item)
    }
}
