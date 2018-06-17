package com.example.fehty.project1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerItemTouchHelperListener {

    private val list = arrayListOf("Russia", "England", "Spain", "Canada", "USA", "Egypt", "Australia", "Germany", "Portugal", "Brazil")

    private val adapter = RecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        recyclerView.adapter = adapter

        val itemTouchHelperCallBack = RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this)
        ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView)

        adapter.setList(list)
    }

    fun goToActivity() {
        val intent = Intent(this, BackActivity::class.java)
        startActivity(intent)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        adapter.removeItem(viewHolder.adapterPosition)
    }
}