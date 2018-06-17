package com.example.fehty.project1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf("Russia", "England", "Spain", "Canada", "USA", "Egypt", "Australia", "Germany", "Portugal", "Brazil")

    private var adapter = RecyclerViewAdapter(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.setList(list)
    }

  fun goToFragment() {
        val intent = Intent(this@MainActivity, BackActivity::class.java)
        startActivity(intent)
    }
}
